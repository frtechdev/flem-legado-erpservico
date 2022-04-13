package br.org.flem.sac.web.struts.action;


import br.org.flem.fwe.exception.AplicacaoException;
import br.org.flem.fwe.hibernate.util.HibernateUtil;
import br.org.flem.fwe.util.Data;
import br.org.flem.fwe.util.Valores;
import br.org.flem.fwe.web.tag.MensagemTag;
import br.org.flem.fwe.web.util.MensagemTagUtil;
import br.org.flem.sac.bo.AditivoBO;
import br.org.flem.sac.bo.ContratoBO;
import br.org.flem.sac.bo.ParcelaBO;
import br.org.flem.sac.dto.contratos.ContratoDTO;
import br.org.flem.sac.negocio.Aditivo;
import br.org.flem.sac.negocio.Contrato;
import br.org.flem.sac.negocio.Parcela;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author mccosta
 */
public class ParcelaAction extends DispatchAction {

    private final String STRING_CONTRATO_SESSAO = "parcelaAction_contrato_sessao";

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String idContrato = request.getParameter("idContrato");
        if (idContrato == null || idContrato.isEmpty()) {
            idContrato = (String) request.getSession().getAttribute(STRING_CONTRATO_SESSAO);
        }
        try {
            if (GenericValidator.isInt(idContrato)) {
                Contrato contrato = new ContratoBO().obterPorPk(Integer.valueOf(idContrato));
                
                BigDecimal totalPago = new ParcelaBO().obterTotalPorContrato(contrato);
                if(totalPago == null){
                    totalPago = new BigDecimal(0);
                }
                
                ContratoDTO contratoToList = new ContratoDTO();
                contratoToList.setId(contrato.getId());
                contratoToList.setNumero(contrato.getNumero());
                contratoToList.setNomeFornecedor(contrato.getNomeFornecedor());
                contratoToList.setInicioVigencia(contrato.getInicioVigencia());
                contratoToList.setFimVigencia(contrato.getFimVigencia());
                contratoToList.setValor(contrato.getValor());
                
                Aditivo aditivo = new AditivoBO().obterUltimoPorContrato(contrato);
                if(aditivo != null){
                    contratoToList.setInicioVigencia(aditivo.getInicioVigencia());
                    contratoToList.setFimVigencia(aditivo.getFimVigencia());
                    contratoToList.setValor(new AditivoBO().obterValorTotalAditivos(contrato) + contrato.getValor());
                }
                contratoToList.setSaldoAPagar(contratoToList.getValor() - totalPago.doubleValue() );
                
                if(totalPago != null && totalPago.doubleValue() > 0d){
                    //contratoToList.subtrairDeSaldoAPagar(totalPago.doubleValue());
                    request.setAttribute("totalPago", totalPago.doubleValue());
                }else{
                    request.setAttribute("totalPago", 0);
                }
                
                List<Parcela> parcelas = new ParcelaBO().obterPorContrato(contrato);             
                request.setAttribute("parcelas", parcelas);
                request.setAttribute("contrato", contratoToList);
                request.getSession().setAttribute(STRING_CONTRATO_SESSAO, idContrato);
            } 
        } catch (Exception ex) {
            MensagemTagUtil.adicionarMensagem(request.getSession(), ex.getMessage(), "erro", this.getClass().getName(), ex);
            Logger.getLogger(ParcelaAction.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return mapping.findForward("lista");
    }

    public ActionForward novo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        String idContrato = request.getParameter("idContrato");
        dyna.set("idContrato", idContrato);
        return mapping.findForward("novo");
    }

    public ActionForward adicionar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        try {
            if (GenericValidator.isInt(dyna.getString("idContrato"))) {
                Contrato contrato = new Contrato();
                contrato.setId(Integer.valueOf(dyna.getString("idContrato")));
                Parcela parcela = new Parcela();
                parcela.setDataPagamento(Data.formataData(dyna.getString("dataPagamento")));
                parcela.setNotaFiscal(dyna.getString("notaFiscal"));
                parcela.setValor(Valores.desformataValorBigDecimal(dyna.getString("valor")));
                
                parcela.setContrato(contrato);
                parcela.setValor(Valores.desformataValorBigDecimal(dyna.getString("valor")));
                new ParcelaBO().inserir(parcela);
                MensagemTagUtil.adicionarMensagem(request.getSession(), "Parcela adicionada com sucesso");
            }
        } catch (Exception ex) {
            MensagemTagUtil.adicionarMensagem(request.getSession(), ex.getMessage(), "erro", this.getClass().getName(), ex);
            Logger.getLogger(ParcelaAction.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return mapping.findForward("redirect");
    }

    public ActionForward selecionar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        String id = request.getParameter("id");
        try {
            if (GenericValidator.isInt(id)) {
                Parcela parcela = new ParcelaBO().obterPorPk(Integer.valueOf(id));
                dyna.set("dataPagamento", Data.formataData(parcela.getDataPagamento()));
                dyna.set("notaFiscal", parcela.getNotaFiscal());
                dyna.set("valor", parcela.getValor().toString());
                dyna.set("idContrato", parcela.getContrato().getId().toString());
                dyna.set("id", parcela.getId().toString());
            }
        } catch (Exception ex) {
            MensagemTagUtil.adicionarMensagem(request.getSession(), ex.getMessage(), "erro", this.getClass().getName(), ex);
            Logger.getLogger(ParcelaAction.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return mapping.findForward("alterar");
    }

    public ActionForward alterar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        try {
            if (GenericValidator.isInt(dyna.getString("idContrato")) && GenericValidator.isInt(dyna.getString("id"))) {
                Contrato contrato = new Contrato();
                contrato.setId(Integer.valueOf(dyna.getString("idContrato")));
                Parcela parcela = new Parcela();
                parcela.setDataPagamento(Data.formataData(dyna.getString("dataPagamento")));
                parcela.setNotaFiscal(dyna.getString("notaFiscal"));
                parcela.setContrato(contrato);
                parcela.setValor(Valores.desformataValorBigDecimal(dyna.getString("valor")));
                parcela.setId(Integer.valueOf(dyna.getString("id")));
                new ParcelaBO().alterar(parcela);
                MensagemTagUtil.adicionarMensagem(request.getSession(), "Parcela alterada com sucesso");
            }
        } catch (Exception ex) {
            MensagemTagUtil.adicionarMensagem(request.getSession(), ex.getMessage(), "erro", this.getClass().getName(), ex);
            Logger.getLogger(ParcelaAction.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return mapping.findForward("redirect");
    }
    
    public ActionForward excluir(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ArrayList erros = new ArrayList();
        try {
            String[] id = new String[0];
            if (request.getParameterValues("ids_exclusao") != null) {
                id = request.getParameterValues("ids_exclusao");
            }
            HibernateUtil.beginTransaction();
            for (int i = 0; i < id.length; i++) {
                ParcelaBO parcelaBO = new ParcelaBO();
                Parcela parcela = parcelaBO.obterPorPk(Integer.valueOf(id[i]));
                try {
                    parcelaBO.excluir(parcela);
                } 
                catch (Exception ex) {
                    HibernateUtil.rollbackTransaction();
                    erros.add("A parcela não pode ser excluída!");
                    request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS, erros);
                    break;
                }
            }
            HibernateUtil.commitTransaction();

        } 
        catch (AplicacaoException ex) {
            Logger.getLogger(ContratoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (erros.size() <= 0) {
            erros.add("Exclusão realizada com sucesso!");
            request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS, erros);
        }

        return mapping.findForward("redirect");
    }
}

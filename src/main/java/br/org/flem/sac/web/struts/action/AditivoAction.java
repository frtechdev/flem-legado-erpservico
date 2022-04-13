package br.org.flem.sac.web.struts.action;

import br.org.flem.fwe.exception.AplicacaoException;
import br.org.flem.fwe.hibernate.util.HibernateUtil;
import br.org.flem.fwe.util.Data;
import br.org.flem.fwe.util.Valores;
import br.org.flem.fwe.web.tag.MensagemTag;
import br.org.flem.fwe.web.util.MensagemTagUtil;
import br.org.flem.sac.bo.ContratoBO;
import br.org.flem.sac.bo.AditivoBO;
import br.org.flem.sac.bo.ParcelaBO;
import br.org.flem.sac.dto.contratos.ContratoDTO;
import br.org.flem.sac.negocio.Contrato;
import br.org.flem.sac.negocio.Aditivo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
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
public class AditivoAction extends DispatchAction {

    private final String STRING_CONTRATO_SESSAO = "aditivoAction_contrato_sessao";

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
                Double total =  new AditivoBO().obterValorTotalAditivos(contrato);
                if(aditivo != null){
                    contratoToList.setInicioVigencia(aditivo.getInicioVigencia());
                    contratoToList.setFimVigencia(aditivo.getFimVigencia());
                    contratoToList.setValor(total.doubleValue() + contrato.getValor());
                }
                contratoToList.setSaldoAPagar(contratoToList.getValor() - totalPago.doubleValue());
                /*
                if(totalPago != null && totalPago.doubleValue() > 0d){
                    contratoToList.adicionarDeSaldoAPagar(totalPago.doubleValue());
                }
                */
                Collection<Aditivo> aditivos = new AditivoBO().obterPorContrato(contrato);
                request.setAttribute("aditivos", aditivos);
                request.setAttribute("contrato", contratoToList);
                request.setAttribute(idContrato, log);
                request.getSession().setAttribute(STRING_CONTRATO_SESSAO, idContrato);
            }
        } catch (Exception ex) {
            MensagemTagUtil.adicionarMensagem(request.getSession(), ex.getMessage(), "erro", this.getClass().getName(), ex);
            Logger.getLogger(AditivoAction.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
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
            if (GenericValidator.isInt(dyna.getString("idContrato")) && GenericValidator.isDate(dyna.getString("dataInicioVigencia"), "dd/MM/yyyy", true)) {
                Contrato contrato = new Contrato();
                contrato.setId(Integer.valueOf(dyna.getString("idContrato")));
                Aditivo aditivo = new Aditivo();
                aditivo.setInicioVigencia(Data.formataData(dyna.getString("dataInicioVigencia")));
                aditivo.setFimVigencia(Data.formataData(dyna.getString("dataFimVigencia")));
                aditivo.setValor(Valores.desformataValor(dyna.getString("valor")));
                aditivo.setContrato(contrato);
                aditivo.setValor(Valores.desformataValor(dyna.getString("valor")));
                
                new AditivoBO().inserir(aditivo);
                MensagemTagUtil.adicionarMensagem(request.getSession(), "Aditivo adicionado com sucesso");
            }
        } catch (Exception ex) {
            MensagemTagUtil.adicionarMensagem(request.getSession(), ex.getMessage(), "erro", this.getClass().getName(), ex);
            Logger.getLogger(AditivoAction.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return mapping.findForward("redirect");
    }

    public ActionForward selecionar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        String id = request.getParameter("id");
        try {
            if (GenericValidator.isInt(id)) {
                Aditivo aditivo = new AditivoBO().obterPorPk(Integer.valueOf(id));
                dyna.set("dataInicioVigencia", Data.formataData(aditivo.getInicioVigencia()));
                dyna.set("dataFimVigencia", Data.formataData(aditivo.getFimVigencia()));
                dyna.set("valor", aditivo.getValor().toString());
                dyna.set("idContrato", aditivo.getContrato().getId().toString());
                dyna.set("id", aditivo.getId().toString());
            }
        } catch (Exception ex) {
            MensagemTagUtil.adicionarMensagem(request.getSession(), ex.getMessage(), "erro", this.getClass().getName(), ex);
            Logger.getLogger(AditivoAction.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return mapping.findForward("alterar");
    }

    public ActionForward alterar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        try {
            if (GenericValidator.isInt(dyna.getString("idContrato")) && GenericValidator.isInt(dyna.getString("id"))
                    && GenericValidator.isDate(dyna.getString("dataInicioVigencia"), "dd/MM/yyyy", true)) {
                Contrato contrato = new Contrato();
                contrato.setId(Integer.valueOf(dyna.getString("idContrato")));
                Aditivo aditivo = new Aditivo();
                aditivo.setInicioVigencia(Data.formataData(dyna.getString("dataInicioVigencia")));
                aditivo.setFimVigencia(Data.formataData(dyna.getString("dataFimVigencia")));
                aditivo.setContrato(contrato);
                aditivo.setId(Integer.valueOf(dyna.getString("id")));
                aditivo.setValor(Valores.desformataValor(dyna.getString("valor")));
                new AditivoBO().alterar(aditivo);
                MensagemTagUtil.adicionarMensagem(request.getSession(), "Aditivo alterado com sucesso");
            }
        } catch (Exception ex) {
            MensagemTagUtil.adicionarMensagem(request.getSession(), ex.getMessage(), "erro", this.getClass().getName(), ex);
            Logger.getLogger(AditivoAction.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
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
                AditivoBO aditivoBO = new AditivoBO();
                Aditivo aditivo = aditivoBO.obterPorPk(Integer.valueOf(id[i]));
                try {
                    aditivoBO.excluir(aditivo);
                } catch (Exception ex) {
                    HibernateUtil.rollbackTransaction();
                    erros.add("O aditivo não pode ser excluído!");
                    request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS, erros);
                    break;
                }
            }
            HibernateUtil.commitTransaction();

        } catch (AplicacaoException ex) {
            Logger.getLogger(ContratoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (erros.size() <= 0) {
            erros.add("Exclusão realizada com sucesso!");
            request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS, erros);
        }

        return mapping.findForward("redirect");
    }
}

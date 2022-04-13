package br.org.flem.sac.web.struts.action;

import br.org.flem.fw.persistencia.dto.Entidade;
import br.org.flem.fw.persistencia.dto.Funcionario;
import br.org.flem.fw.service.GEM;
import br.org.flem.fw.service.IEntidade;
import br.org.flem.fw.service.impl.GEMImpl;
import br.org.flem.fw.service.impl.RHServico;
import br.org.flem.fwe.exception.AcessoDadosException;
import br.org.flem.fwe.exception.AplicacaoException;
import br.org.flem.fwe.hibernate.util.HibernateUtil;
import br.org.flem.fwe.util.Data;
import br.org.flem.fwe.util.Valores;
import br.org.flem.fwe.web.tag.MensagemTag;
import br.org.flem.sac.bo.AditivoBO;
import br.org.flem.sac.bo.ContratoBO;
import br.org.flem.sac.bo.ParcelaBO;
import br.org.flem.sac.dto.contratos.ContratoDTO;
import br.org.flem.sac.negocio.Aditivo;
import br.org.flem.sac.negocio.Contrato;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.commons.beanutils.BeanUtils;
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
public class ContratoAction extends DispatchAction {

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            AditivoBO aditivoBO = new AditivoBO();
            ParcelaBO parcelaBO = new ParcelaBO();
            String contrato = (String) request.getSession().getAttribute("filtro_contrato");
            String fornecedor = (String) request.getSession().getAttribute("filtro_fornecedor");
            Date dataInicioVigencia = (Date) request.getSession().getAttribute("dataInicioVigencia");
            Date dataFimVigencia = (Date) request.getSession().getAttribute("dataFimVigencia");
            Collection<ContratoDTO> contratos = new ArrayList<ContratoDTO>();
            BigDecimal totalPago = new BigDecimal(0);
            Double total = 0d;

            if (contrato != null || fornecedor != null || dataInicioVigencia != null || dataFimVigencia != null) {
                for (Contrato c : new ContratoBO().obterPorFiltro(contrato, fornecedor, dataInicioVigencia, dataFimVigencia)) {
                    Aditivo aditivo = aditivoBO.obterUltimoPorContrato(c);
                    total = new AditivoBO().obterValorTotalAditivos(c);
                    if (total == null) {
                        total = 0d;
                    }

                    totalPago = parcelaBO.obterTotalPorContrato(c);

                    if (totalPago == null) {
                        totalPago = new BigDecimal(0);
                    }

                    ContratoDTO contratoToList = new ContratoDTO();

                    contratoToList.setId(c.getId());
                    contratoToList.setNumero(c.getNumero());
                    contratoToList.setNomeFornecedor(c.getNomeFornecedor());
                    contratoToList.setInicioVigencia(c.getInicioVigencia());
                    contratoToList.setFimVigencia(c.getFimVigencia());
                    contratoToList.setValor(total + c.getValor());

                    if (aditivo != null) {
                        contratoToList.setInicioVigencia(aditivo.getInicioVigencia());
                        //if(aditivo.getFimVigencia().equals(dataFimVigencia)){
                        contratoToList.setFimVigencia(aditivo.getFimVigencia());
                        //}
                        contratoToList.setValor(total + c.getValor());
                    }

                    contratoToList.setSaldoAPagar(contratoToList.getValor() - totalPago.doubleValue());

                    contratos.add(contratoToList);
                }
            } else {
                for (Contrato c : new ContratoBO().obterTodos()) {
                    total = new AditivoBO().obterValorTotalAditivos(c);
                    if (total == null) {
                        total = 0d;
                    }

                    totalPago = parcelaBO.obterTotalPorContrato(c);

                    if (totalPago == null) {
                        totalPago = new BigDecimal(0);
                    }

                    ContratoDTO contratoToList = new ContratoDTO();

                    contratoToList.setId(c.getId());
                    contratoToList.setNumero(c.getNumero());
                    contratoToList.setNomeFornecedor(c.getNomeFornecedor());
                    contratoToList.setInicioVigencia(c.getInicioVigencia());
                    contratoToList.setFimVigencia(c.getFimVigencia());
                    contratoToList.setValor(total + c.getValor());

                    contratoToList.setSaldoAPagar(contratoToList.getValor() - totalPago.doubleValue());

                    contratos.add(contratoToList);
                }
            }
            request.setAttribute("lista", contratos);
        } catch (AplicacaoException ex) {
            Logger.getLogger(ContratoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapping.findForward("lista");
    }

    public ActionForward filtrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            DynaActionForm dyna = (DynaActionForm) form;
            Date dataInicioVigencia = null, dataFimVigencia = null;
            String contrato = (String) dyna.get("contrato");
            request.getSession().setAttribute("filtro_contrato", contrato);
            String fornecedor = (String) dyna.get("fornecedor");
            request.getSession().setAttribute("filtro_fornecedor", fornecedor);
            if (GenericValidator.isDate(dyna.getString("dataInicioVigencia"), "dd/MM/yyyy", false)) {
                dataInicioVigencia = Data.formataData(dyna.getString("dataInicioVigencia"));
            }
            if (GenericValidator.isDate(dyna.getString("dataFimVigencia"), "dd/MM/yyyy", false)) {
                dataFimVigencia = Data.formataData(dyna.getString("dataFimVigencia"));
            }
            request.getSession().setAttribute("dataInicioVigencia", dataInicioVigencia);
            request.getSession().setAttribute("dataFimVigencia", dataFimVigencia);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unspecified(mapping, form, request, response);
    }

    public ActionForward novo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            GEM gem = new GEMImpl();
            
            List<IEntidade> fornecedoresExternos = gem.obterFornecedoresExternos();
            
            List<Funcionario> todosDominio = new RHServico().obterFuncionariosAtivos();
            
            List<IEntidade> remove = new ArrayList<IEntidade>();
            
            for(IEntidade c : fornecedoresExternos){
                for(Funcionario f : todosDominio){
                    if(c.getCodigo().equals(f.getCpf())){
                        remove.add(c);
                    }
                }
            }
            
            fornecedoresExternos.removeAll(remove);
            
            request.getSession().setAttribute("fornecedores", fornecedoresExternos);
        } catch (Exception ex) {
            Logger.getLogger(ContratoAction.class.getName()).log(Level.SEVERE, null, ex);
            request.getSession().setAttribute("fornecedores", new ArrayList());
        }

        return mapping.findForward("novo");
    }

    public ActionForward adicionar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        try {
            Contrato contrato = new Contrato();
            BeanUtils.copyProperties(contrato, dyna);

            contrato.setInicioVigencia(new SimpleDateFormat("dd/MM/yyyy").parse(dyna.getString("dataInicioVigencia")));
            contrato.setFimVigencia(new SimpleDateFormat("dd/MM/yyyy").parse(dyna.getString("dataFimVigencia")));
            contrato.setCentroCusto(dyna.getString("centroCusto"));
            contrato.setNumeroContrato(dyna.getString("numeroContrato"));
            contrato.setNumeroSD(dyna.getString("numeroSD"));
            contrato.setSituacao(dyna.getString("situacao"));

            contrato.setAviso(false);
            if (dyna.getString("aviso").equals("1")) {
                contrato.setAviso(true);
            }

            if (!dyna.getString("dataLimiteAviso").isEmpty() || !dyna.getString("dataLimiteAviso").equals("")) {
                contrato.setLimiteAviso(new SimpleDateFormat("dd/MM/yyyy").parse(dyna.getString("dataLimiteAviso")));
            }

            Entidade entidade = new Entidade();
            entidade.setCodigo(dyna.getString("idFornecedor"));

            entidade = (Entidade) ((List) request.getSession().getAttribute("fornecedores")).get(((List) request.getSession().getAttribute("fornecedores")).indexOf(entidade));

            contrato.setNomeFornecedor(entidade.getNomeAbreviado());
            contrato.setValor(Valores.desformataValor(dyna.getString("valor")));
            new ContratoBO().inserir(contrato);

            List<String> mensagens = new ArrayList<String>();
            mensagens.add("Contrato inserido com sucesso.");
            request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS, mensagens);
        } catch (Exception ex) {
            ex.printStackTrace();
            List<String> mensagens = new ArrayList<String>();
            mensagens.add("Erro: Ocorreu um erro ao tentar inserir o Contrato.");
            mensagens.add("Erro: "+ex.getMessage());
            request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS, mensagens);
        }
        request.setAttribute("acao", "Contrato.do");
        return mapping.findForward("redirect");
    }

    public ActionForward alterar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        try {
            Contrato contrato = new ContratoBO().obterPorPk(Integer.valueOf(dyna.getString("id")));

            BeanUtils.copyProperties(contrato, dyna);

            contrato.setInicioVigencia(new SimpleDateFormat("dd/MM/yyyy").parse(dyna.getString("dataInicioVigencia")));
            contrato.setFimVigencia(new SimpleDateFormat("dd/MM/yyyy").parse(dyna.getString("dataFimVigencia")));
            contrato.setCentroCusto(dyna.getString("centroCusto"));
            contrato.setNumeroContrato(dyna.getString("numeroContrato"));
            contrato.setNumeroSD(dyna.getString("numeroSD"));
            contrato.setAviso(false);
            contrato.setSituacao(dyna.getString("situacao"));
            if (dyna.getString("aviso").equals("1")) {
                contrato.setAviso(true);
            }

            contrato.setLimiteAviso(null);
            if (!dyna.getString("dataLimiteAviso").isEmpty() || !dyna.getString("dataLimiteAviso").equals("")) {
                contrato.setLimiteAviso(new SimpleDateFormat("dd/MM/yyyy").parse(dyna.getString("dataLimiteAviso")));
            }

            Entidade entidade = new Entidade();
            entidade.setCodigo(dyna.getString("idFornecedor"));

            entidade = (Entidade) ((List) request.getSession().getAttribute("fornecedores")).get(((List) request.getSession().getAttribute("fornecedores")).indexOf(entidade));

            contrato.setNomeFornecedor(entidade.getNomeAbreviado());
            contrato.setValor(Valores.desformataValor(dyna.getString("valor")));
            new ContratoBO().alterar(contrato);

            List<String> mensagens = new ArrayList<String>();
            mensagens.add("Contrato alterado com sucesso.");
            request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS, mensagens);
        } catch (Exception ex) {
            ex.printStackTrace();

            List<String> mensagens = new ArrayList<String>();
            mensagens.add("Ocorreu um erro ao tentar alterar o Contrato.");
            request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS, mensagens);
        }
        request.setAttribute("acao", "Contrato.do");
        return mapping.findForward("redirect");
    }

    public ActionForward selecionar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        String id = request.getParameter("id");
        GEM gem = new GEMImpl();
        try {
            if (GenericValidator.isInt(id)) {
                Contrato contrato = new ContratoBO().obterPorPk(Integer.valueOf(id));
                BeanUtils.copyProperties(dyna, contrato);
                dyna.set("dataInicioVigencia", new SimpleDateFormat("dd/MM/yyyy").format(contrato.getInicioVigencia()));
                dyna.set("dataFimVigencia", new SimpleDateFormat("dd/MM/yyyy").format(contrato.getFimVigencia()));
                if (contrato.getAviso()) {
                    dyna.set("dataLimiteAviso", new SimpleDateFormat("dd/MM/yyyy").format(contrato.getLimiteAviso()));
                    dyna.set("aviso", "1");
                }
            }

            request.getSession().setAttribute("fornecedores", gem.obterFornecedoresNovos());

            return mapping.findForward("alterar");
        } catch (Exception ex) {
            ex.printStackTrace();
            List<String> mensagens = new ArrayList<String>();
            mensagens.add("Ocorreu um erro ao tentar selecionar o Contrato.");
            request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS, mensagens);
        }
        return unspecified(mapping, form, request, response);
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
                ContratoBO contratoBO = new ContratoBO();
                Contrato contrato = contratoBO.obterPorPk(Integer.valueOf(id[i]));
                try {
                    contratoBO.excluir(contrato);
                } catch (Exception ex) {
                    HibernateUtil.rollbackTransaction();
                    erros.add("O contrato \"" + contrato.getNumero() + "\" está associado. Não pode ser excluído!");
                    request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS, erros);
                    break;
                }
            }
            HibernateUtil.commitTransaction();

        } catch (AcessoDadosException ex) {
            Logger.getLogger(ContratoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (erros.size() <= 0) {
            erros.add("Exclusão realizada com sucesso!");
            request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS, erros);
        }

        return unspecified(mapping, form, request, response);
    }

    public ActionForward imprimir(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("id");
        if (GenericValidator.isInt(id)) {
            try {
                AditivoBO aditivoBO = new AditivoBO();
                ParcelaBO parcelaBO = new ParcelaBO();
                ContratoDTO dto = new ContratoDTO();
                
                ServletOutputStream servletOutputStream = response.getOutputStream();
                response.setContentType("application/save");

                response.setHeader("Content-disposition", "attachment; filename=" + "FlemWeb_Relatorio.pdf");
                
                Contrato contrato = new ContratoBO().obterPorPk(Integer.valueOf(id));
                if(contrato != null ){
                    Aditivo aditivo = aditivoBO.obterUltimoPorContrato(contrato);
                    Double valorTotal = aditivoBO.obterValorTotalAditivos(contrato);
                    
                    if (valorTotal == null) {
                        valorTotal = 0d;
                    }

                    BigDecimal totalPago = parcelaBO.obterTotalPorContrato(contrato);

                    if (totalPago == null) {
                        totalPago = new BigDecimal(0);
                    }
                    
                    dto.setCentroCusto(contrato.getCentroCusto());
                    if( aditivo != null ){
                        dto.setFimVigencia(aditivo.getFimVigencia());
                    }else{
                        dto.setFimVigencia(contrato.getFimVigencia());
                    }
                    
                    dto.setInicioVigencia(contrato.getInicioVigencia());
                    dto.setNomeFornecedor(contrato.getNomeFornecedor());
                    dto.setIdFornecedor(contrato.getIdFornecedor());
                    dto.setNumero(contrato.getNumero());
                    dto.setNumeroSD(contrato.getNumeroSD());
                    dto.setNumeroContrato(contrato.getNumeroContrato());
                    dto.setValor(contrato.getValor() + valorTotal);
                    dto.setSaldoAPagar(dto.getValor() - totalPago.doubleValue());
                    dto.setObjeto(contrato.getObjeto());
                    List<ContratoDTO> listaImpressao = new ArrayList<ContratoDTO>();
                    listaImpressao.add(dto);

                    Map<String, Object> parametros = new HashMap();
                    parametros.put("logo", request.getSession().getServletContext().getRealPath("/img/flemlogo.gif"));

                    JasperReport report = JasperCompileManager.compileReport(request.getSession().getServletContext().getRealPath("/relatorio/pgtoDespContrato.jrxml"));
                    JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(listaImpressao));

//                    JRPdfExporter exporter = new JRPdfExporter();
//                    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//                    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(servletOutputStream));
//                    exporter.exportReport();
                    servletOutputStream.flush();
                    servletOutputStream.close();
                }
                
            } catch (JRException jre) {
                ArrayList mensagens = new ArrayList();
                mensagens.add("Ocorreu um erro na geração do relatório.\n" + jre.getMessage());
                request.setAttribute(MensagemTag.LISTA_MENSAGENS, mensagens);
            } catch (AcessoDadosException ex) {
                Logger.getLogger(ContratoAction.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ContratoAction.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AplicacaoException ex) {
                Logger.getLogger(ContratoAction.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return mapping.getInputForward();
    }
}

/*
 * RelatorioAction.java
 *
 * Created on 20 de Junho de 2007, 09:36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package br.org.flem.sac.web.struts.action;

import br.org.flem.fw.persistencia.dto.AtivoFixo;
import br.org.flem.fw.persistencia.dto.Departamento;
import br.org.flem.fw.persistencia.dto.RelatorioDemitidos;
import br.org.flem.fw.service.impl.RHServico;
import br.org.flem.fwe.exception.RelatorioSemDadosException;
import br.org.flem.fwe.util.Data;
import br.org.flem.fwe.web.tag.MensagemTag;
import br.org.flem.sac.relatorio.ERPServicoCriadorRelatorio;
import br.org.flem.sac.util.SituacaoUtil;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author dbbarreto
 */
public class RelatorioAction extends DispatchAction {

    public ActionForward listagemAtivoFixo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String codigo = (String) request.getSession().getAttribute("AtivoFixoAction_codigo");
        String descricao = (String) request.getSession().getAttribute("AtivoFixoAction_descricao");
        String responsavel = (String) request.getSession().getAttribute("AtivoFixoAction_responsavel");
        String detentor = (String) request.getSession().getAttribute("AtivoFixoAction_detentor");
        String tombo = (String) request.getSession().getAttribute("AtivoFixoAction_tombo");
        String situacao = (String) request.getSession().getAttribute("AtivoFixoAction_situacao");
        String localizacao = (String) request.getSession().getAttribute("AtivoFixoAction_localizacao");
        String serial = (String) request.getSession().getAttribute("AtivoFixoAction_serial");

        AtivoFixo ativoFixo = new AtivoFixo();
        ativoFixo.setCodigo(codigo == null ? "" : codigo);
        ativoFixo.setDescricao(descricao == null ? "" : descricao);
        ativoFixo.setDetentor(detentor == null ? "" : detentor);
        ativoFixo.setLocalizacao(localizacao == null ? "" : localizacao);
        ativoFixo.setResponsavel(responsavel == null ? "" : responsavel);
        ativoFixo.setSituacao(situacao == null ? "" : situacao);
        ativoFixo.setSerial(serial == null ? "" : serial);
        ativoFixo.setTombo(tombo == null ? "" : tombo);

        //Collection ativos = new GEMImpl().obterAtivoFixoFiltro(ativoFixo);//está retornando sempre null
        Collection ativos = null;

        new SituacaoUtil().atualizaSituacoes(ativos);

        String arquivoRelatorio = new String("/relatorio/relListagem.jasper");

        Map parametros = new HashMap();
        ERPServicoCriadorRelatorio criadorRelatorio = new ERPServicoCriadorRelatorio();

        try {
            criadorRelatorio.exportaRelatorioPDF(request, response, arquivoRelatorio, parametros, ativos);
        } catch (JRException jre) {
            ArrayList mensagens = new ArrayList();
            mensagens.add("Ocorreu um erro na geração do relatório.\n" + jre.getMessage());
            request.setAttribute(MensagemTag.LISTA_MENSAGENS, mensagens);
        } catch (RelatorioSemDadosException rsde) {
            ArrayList mensagens = new ArrayList();
            mensagens.add("Não existem dados a serem exibidos nesse relatório");
            request.setAttribute(MensagemTag.LISTA_MENSAGENS, mensagens);
        }
        return new AtivoFixoAction().unspecified(mapping, form, request, response);
    }

    public ActionForward listaDemitidos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        List<Departamento> listaFinal = new ArrayList<Departamento>();
        RHServico rh = new RHServico();
        List<Departamento> todosDepartamentos = rh.obterDepartamentos();
        List<Departamento> listadepartamentosEscolhidos = new ArrayList<Departamento>();

        String[] departamentosEscolhidosArray = (String[]) request.getSession().getAttribute("departamentos");
        List<String> departamentosEscolhidos = Arrays.asList(departamentosEscolhidosArray);
        Date dataInicial = (Date) request.getSession().getAttribute("dataInicial");
        Date dataFinal = (Date) request.getSession().getAttribute("dataFinal");

        if (departamentosEscolhidos != null && dataInicial != null && dataFinal != null) {
            List<RelatorioDemitidos> demitidos = rh.obterDemitidosPorLotacao(departamentosEscolhidosArray, dataInicial, dataFinal);
            request.setAttribute("demitidos", demitidos);
        }

        for (Departamento departamento : todosDepartamentos) {
            if (!departamentosEscolhidos.contains(departamento.getCodigoDominio().toString())) {
                listaFinal.add(departamento);
            }else{
                listadepartamentosEscolhidos.add(departamento);
            }
        }
  
        request.setAttribute("listaDepartamentos", listaFinal);
        request.setAttribute("departamentosEscolhidos", listadepartamentosEscolhidos);
        return mapping.findForward("relatorioDemitidos");
    }

    public ActionForward filtrarDemitidos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        try {
            String[] departamentos = dyna.getStrings("departamentosEscolhidos");
            Date dataInicial = null, dataFinal = null;
            if (GenericValidator.isDate(dyna.getString("dataInicial"), "dd/MM/yyyy", false)) {
                dataInicial = Data.formataData(dyna.getString("dataInicial"));
            }
            if (GenericValidator.isDate(dyna.getString("dataFinal"), "dd/MM/yyyy", false)) {
                dataFinal = Data.formataData(dyna.getString("dataFinal"));
            }
            request.getSession().setAttribute("departamentos", departamentos);
            request.getSession().setAttribute("dataInicial", dataInicial);
            request.getSession().setAttribute("dataFinal", dataFinal);
        } catch (ParseException ex) {
            Logger.getLogger(RelatorioAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaDemitidos(mapping, form, request, response);
    }
}

/*
 * AtivoFixoAction.java
 *
 * Created on 19/09/2007, 18:50:28
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.flem.sac.web.struts.action;

import br.org.flem.fw.persistencia.dto.Compromisso;
import br.org.flem.fw.service.Colaboradores;
import br.org.flem.fw.service.GEM;
import br.org.flem.fw.service.impl.ColaboradoresImpl;
import br.org.flem.fw.service.impl.GEMImpl;
import br.org.flem.sac.dto.receitas.ProjetoReceitaAnoDTO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author mjpereira
 */
public class ReceitaProjetosAction extends DispatchAction {

    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String ano = (String) request.getSession().getAttribute("ano");
        GEM servico = new GEMImpl();
        Collection<Compromisso> lista = servico.obterReceitasProjetos(ano);
        Colaboradores servico2 = new ColaboradoresImpl();
        Map<String, String> ccproj = servico2.obterMapCentroDeCustoProjeto();
        Map<String, ProjetoReceitaAnoDTO> sintetica = new TreeMap<String, ProjetoReceitaAnoDTO>();
        Calendar calendario = Calendar.getInstance();
        for (Compromisso c : lista) {
            String nome = "";
            String cc = "";
            if (c.getCentroCusto().substring(0, 5).equals("20208")) {
                nome = "Evento Externo - Centro de Eventos";
                cc = c.getCentroCusto().substring(0, 5);
            } else {
                String s = ccproj.get(c.getCentroCusto().substring(0, 5) + "0000");
                if (s != null && !s.trim().isEmpty()) {
                    nome = s;
                    cc = c.getCentroCusto().substring(0, 5);
                } else {
                    String s2 = ccproj.get(c.getCentroCusto());
                    if (s2 != null && !s2.trim().isEmpty()) {
                        nome = s2;
                        cc = c.getCentroCusto();
                    }
                }
            }

            ProjetoReceitaAnoDTO projeto = sintetica.get(nome);
            if (projeto == null) {
                projeto = new ProjetoReceitaAnoDTO();
                projeto.setProjeto(nome);
                projeto.setCentroCusto(cc);
                calendario.setTime(c.getData());
                int mes = calendario.get(Calendar.MONTH);
                projeto.setValorMes(mes, c.getValor().doubleValue());
                projeto.setTotal(c.getValor().doubleValue());
                sintetica.put(projeto.getProjeto(), projeto);
            } else {
                calendario.setTime(c.getData());
                int mes = calendario.get(Calendar.MONTH);
                projeto.setValorMes(mes, projeto.getValorMes(mes) + c.getValor().doubleValue());
                projeto.setTotal(projeto.getTotal() + c.getValor().doubleValue());
            }
        }
        request.setAttribute("lista", new ArrayList<ProjetoReceitaAnoDTO>(sintetica.values()));
        return mapping.findForward("lista");
    }

    public ActionForward filtrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        String ano = (String) dyna.get("ano");
        if (ano != null && !ano.equals("")) {
            request.getSession().setAttribute("ano", ano);
        } else {
            request.getSession().setAttribute("ano", null);
        }
        return unspecified(mapping, form, request, response);
    }

    public ActionForward detalhe(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Collection<Compromisso> lista = new ArrayList<Compromisso>();
        String cc2 = request.getParameter("centrocusto");
        request.setAttribute("centrocusto", cc2);
        if (cc2 != null && !cc2.equals("")) {
            GEM servico = new GEMImpl();
            lista = servico.obterReceitasProjetosPorCentroCusto(cc2);
        }
        request.setAttribute("lista", lista);
        return mapping.findForward("detalhe");
    }
}

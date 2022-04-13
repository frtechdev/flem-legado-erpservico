/*
 * ProcessoAction.java
 *
 * Created on 28/08/2007, 17:04:47
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.web.struts.action;

import br.org.flem.fwe.util.Data;
import br.org.flem.sac.bo.ControleProcessoBO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author mjpereira
 */
public class ProcessoAction extends DispatchAction {

    public ProcessoAction() {
    }

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("lista", new ArrayList());
        request.setAttribute("anos", Data.obterAnosAteAtual(2005));
        
        return mapping.findForward("lista");
    }

    public ActionForward listar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mes = request.getParameter("mes");
        String ano = request.getParameter("ano");
        request.setAttribute("lista", new ControleProcessoBO().obterConsolidacaoProcessoMesAno(mes, ano));
        request.setAttribute("anos", Data.obterAnosAteAtual(2005));

        return mapping.findForward("lista");
    }
}

/*
 * AutonomoAction.java
 *
 * Created on 16 de Novembro de 2006, 17:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.sac.web.struts.action;

import br.org.flem.sac.bo.AutonomosBO;
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
public class AutonomoAction extends DispatchAction {

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("lista", new AutonomosBO().obterAutonomosConsistentes());
        return mapping.findForward("lista");
    }

    public ActionForward opcoes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("autonomo");
    }

    public ActionForward inconsistentes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("lista", new AutonomosBO().obterAutonomosInconsistentes());
        return mapping.findForward("inconsistentes");
    }
}

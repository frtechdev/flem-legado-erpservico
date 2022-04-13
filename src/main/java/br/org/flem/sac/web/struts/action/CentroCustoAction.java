/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.flem.sac.web.struts.action;

import br.org.flem.fw.persistencia.dao.legado.gem.CompromissoDAO;
import br.org.flem.fwe.util.Data;
import br.org.flem.fwe.web.util.MensagemTagUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author AJLima
 */
public class CentroCustoAction extends DispatchAction {
    
      public CentroCustoAction() {
    }
      
      public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("lista", new ArrayList());
        request.setAttribute("codigo", "");
        return mapping.findForward("lista");
    }

    public ActionForward listar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String codigoFonteRecurso = request.getParameter("codigo");
        String dataInicio = "",dataFim = "";

        try{
            dataInicio = Data.retornaDataInversa(request.getParameter("dataInicio"));
        }catch(NullPointerException e){
            MensagemTagUtil.adicionarMensagem(request.getSession(), "Informar uma data de pagamento inicial válida");
            return mapping.findForward("lista");
        }

        try{
            dataFim = Data.retornaDataInversa(request.getParameter("dataFim"));
        }catch(NullPointerException e){
            MensagemTagUtil.adicionarMensagem(request.getSession(), "Informar uma data de pagamento final válida");
            return mapping.findForward("lista");
        }

        request.setAttribute("lista", new CompromissoDAO().obterCompromissosPorFonteRecurso(codigoFonteRecurso,dataInicio,dataFim));
        return mapping.findForward("lista");
    }
}

/*
 * ValoresAutonomoAction.java
 *
 * Created on 16 de Novembro de 2006, 17:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.sac.web.struts.action;

import br.org.flem.sac.bo.AutonomosBO;
import br.org.flem.sac.dto.autonomo.ValoresAnoDTO;
import java.io.File;
import java.util.Arrays;
import java.util.List;
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
public class ValoresAnoAutonomoAction extends DispatchAction{
    
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ano = (String) request.getSession().getAttribute("ano");
        DynaActionForm dyna = (DynaActionForm) form;
        if(ano == null || ano == ""){
            ano = dyna.getString("ano");
        }
        List<ValoresAnoDTO> lista = new AutonomosBO().obterAutonomosComImpostos(ano);
        request.setAttribute("lista",lista);
        request.getSession().setAttribute("ano",ano);
        String cp = getServlet().getServletContext().getRealPath("/") + "temp" + File.separator;
        List<File> listaArquivos = Arrays.asList(br.org.flem.fwe.util.Arquivo.listarArquivosDiretorio(cp));
        request.setAttribute("listaArquivos", listaArquivos);

        return mapping.findForward("lista");
    }
    
    public ActionForward opcoes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("valores");
    }



    
    public ActionForward lista(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ano = "";
        DynaActionForm dyna = (DynaActionForm) form;
        //ano = (String) request.getSession().getAttribute("ano");
        ano = dyna.getString("ano");
        /*if(ano == null || ano == ""){
            ano = dyna.getString("ano");
        }*/
        request.setAttribute("lista",new AutonomosBO().obterAutonomosComImpostos(ano));
        request.getSession().setAttribute("ano",ano);
        return mapping.findForward("lista");
    }
    
   
    
    
}

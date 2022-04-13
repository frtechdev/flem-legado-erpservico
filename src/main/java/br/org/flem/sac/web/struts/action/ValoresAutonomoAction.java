/*
 * ValoresAutonomoAction.java
 *
 * Created on 16 de Novembro de 2006, 17:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package br.org.flem.sac.web.struts.action;

import br.org.flem.fwe.util.Data;
import br.org.flem.sac.bo.AutonomosBO;
import java.io.File;
import java.io.FileFilter;
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
public class ValoresAutonomoAction extends DispatchAction {

    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ano = (String) request.getSession().getAttribute("ano");
        String mes = (String) request.getSession().getAttribute("mes");
        List lista = new AutonomosBO().obterAutonomosComImpostos(mes, ano);
        request.setAttribute("lista", lista);
        request.setAttribute("anos", Data.obterAnosAteAtual(2005));
        return mapping.findForward("lista");
    }

    public static File[] listarArquivosDiretorio(String local) {
        File f = new File(local);
        File[] arquivos = f.listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                String arquivo = pathname.getName().toLowerCase();
                return arquivo.endsWith(".txt");
            }
        });
        return arquivos;
    }

    public ActionForward opcoes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cp = getServlet().getServletContext().getRealPath("/") + "temp/";
        List<File> listaArquivos = Arrays.asList(listarArquivosDiretorio(cp));
        request.setAttribute("anos", Data.obterAnosAteAtual(2005));
        request.setAttribute("listaArquivos", listaArquivos);

        return mapping.findForward("valores");
    }

    public ActionForward listar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaActionForm dyna = (DynaActionForm) form;
        String mes = dyna.getString("mes");
        String ano = dyna.getString("ano");
        
        //System.out.println(mes + " / " + ano);
        
        List lista = new AutonomosBO().obterAutonomosComImpostos(mes, ano);
        request.setAttribute("lista", lista);
        request.getSession().setAttribute("ano", ano);
        request.getSession().setAttribute("mes", mes);
        return mapping.findForward("lista");
    }

    public ActionForward listaArquivos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cp = getServlet().getServletContext().getRealPath("/") + "temp/";
        List<File> listaArquivos = Arrays.asList(listarArquivosDiretorio(cp));
        request.setAttribute("listaArquivos", listaArquivos);
        return mapping.findForward("listaarquivos");
    }
}

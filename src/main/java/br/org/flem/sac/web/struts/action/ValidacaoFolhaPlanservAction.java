package br.org.flem.sac.web.struts.action;

import br.org.flem.fwe.web.tag.MensagemTag;
import br.org.flem.sac.bo.PlanservBO;
import br.org.flem.sac.dto.planserv.validacaoFolha.ValidacaoFolha;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author emsilva
 */
public class ValidacaoFolhaPlanservAction extends DispatchAction{
    
    

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("validacaoFolha");
    }
    
    public ActionForward gerarXmlEnvioValidacao(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mesGeracaoArquivo = request.getParameter("mes");
        String anoGeracaoArquivo = request.getParameter("ano");
        try{
            ValidacaoFolha arquivo =  new PlanservBO().obterValidacaoFolha(mesGeracaoArquivo, anoGeracaoArquivo);
            request.setAttribute("validacaoFolha",arquivo);
            response.setContentType("text/xml");
            response.setHeader("Content-Disposition","attachment; filename=FolhaPlanserv."+arquivo.getMesReferencia()+".xml");
            return mapping.findForward("xmlEnvioValidacao");
        }catch(Exception e){
            e.printStackTrace();
            List<String> mensagens = new ArrayList<String>();
            mensagens.add(e.getMessage());
            request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS, mensagens);
            return mapping.findForward("validacaoFolha");
        }
        
    }
    
}

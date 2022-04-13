package br.org.flem.sac.web.struts.action;

import br.org.flem.fw.persistencia.dao.legado.gem.CompromissoDAO;
import br.org.flem.fw.persistencia.dto.Compromisso;
import br.org.flem.fw.persistencia.dto.Detalhe;
import br.org.flem.fw.service.GEM;
import br.org.flem.fwe.util.Data;
import br.org.flem.fwe.web.util.MensagemTagUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author MCCosta
 */
public class FonteRecursoAction extends DispatchAction {


    public FonteRecursoAction() {
    }

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("lista", new ArrayList());
        request.setAttribute("codigo", "");
        return mapping.findForward("lista");
    }

    public ActionForward listar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String codigoFonteRecurso = request.getParameter("codigo");
        String dataInicio = "",dataFim = "";
        GEM gem = null;
        Collection<Compromisso> compromissos =  new ArrayList<Compromisso>();

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
        
         try{
            compromissos = (List<Compromisso>) new CompromissoDAO().obterCompromissosPorFonteRecurso(codigoFonteRecurso,dataInicio,dataFim);
            //Collection<Compromisso> adiantamentos = gem.obterDevolucoesViagemPorContaDataInicio(1, null, null, null);
           // compromissos.addAll(adiantamentos);
            
        }catch(NullPointerException e){
            MensagemTagUtil.adicionarMensagem(request.getSession(), "Erro ao buscar os compromissos.");
            return mapping.findForward("lista");
        }
        
        

        request.setAttribute("lista", compromissos);
        return mapping.findForward("lista");
    }
    
    
    public ActionForward detalhar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String apdId = request.getParameter("apdId");
        String apdTp = request.getParameter("apdTp");
       
            Collection<Detalhe> detalhes = new ArrayList<Detalhe>();
        try{
             detalhes = new CompromissoDAO().obterDetalhePorApdIdEApdId(apdId, apdTp);
        }catch(NullPointerException e){
            MensagemTagUtil.adicionarMensagem(request.getSession(), "Informar uma data de pagamento inicial válida");
            return mapping.findForward("lista");
        }


        request.setAttribute("lista",detalhes);
        return mapping.findForward("detalhe");
    }
    
}

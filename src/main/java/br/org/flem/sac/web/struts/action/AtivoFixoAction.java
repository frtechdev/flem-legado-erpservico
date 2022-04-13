/*
 * AtivoFixoAction.java
 *
 * Created on 19/09/2007, 18:50:28
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.web.struts.action;

import br.org.flem.fw.persistencia.dto.AtivoFixo;
import br.org.flem.fw.service.impl.GEMImpl;
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
public class AtivoFixoAction extends DispatchAction{

    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
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
        
        /*
          Ao invés de chamar esse "obterTodos", tem que chamar um "obterProFiltro"
          que ainda vai ser implementado no FlemBase.
        */
        //request.setAttribute("lista", new GEMImpl().obterAtivoFixoFiltro(ativoFixo));// esse método está retornando sempre null.
        request.setAttribute("lista", null);// esse método retorna sempre null.
        
        return mapping.findForward("lista");
    }
    
    public ActionForward filtrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        
        String codigo = (String) dyna.get("codigo");
        String descricao = (String) dyna.get("descricao");
        String responsavel = (String) dyna.get("responsavel");
        String detentor = (String) dyna.get("detentor");
        String tombo = (String) dyna.get("tombo");
        String situacao = (String) dyna.get("situacao");
        String localizacao = (String) dyna.get("localizacao");
        String serial = (String) dyna.get("serial");
        
        if (codigo != null && !codigo.equals("")) {
            request.getSession().setAttribute("AtivoFixoAction_codigo", codigo);
        }
        else {
            request.getSession().setAttribute("AtivoFixoAction_codigo", null);
        }
        
        if (descricao != null && !descricao.equals("")) {
            request.getSession().setAttribute("AtivoFixoAction_descricao", descricao);
        }
        else {
            request.getSession().setAttribute("AtivoFixoAction_descricao", null);
        }
        
        if (responsavel != null && !responsavel.equals("")) {
            request.getSession().setAttribute("AtivoFixoAction_responsavel", responsavel);
        }
        else {
            request.getSession().setAttribute("AtivoFixoAction_responsavel", null);
        }
        
        if (detentor != null && !detentor.equals("")) {
            request.getSession().setAttribute("AtivoFixoAction_detentor", detentor);
        }
        else {
            request.getSession().setAttribute("AtivoFixoAction_detentor", null);
        }
        
        if (tombo != null && !tombo.equals("")) {
            request.getSession().setAttribute("AtivoFixoAction_tombo", tombo);
        }
        else {
            request.getSession().setAttribute("AtivoFixoAction_tombo", null);
        }
        
        if (situacao != null && !situacao.equals("")) {
            request.getSession().setAttribute("AtivoFixoAction_situacao", situacao);
        }
        else {
            request.getSession().setAttribute("AtivoFixoAction_situacao", null);
        }
        
        if (localizacao != null && !localizacao.equals("")) {
            request.getSession().setAttribute("AtivoFixoAction_localizacao", localizacao);
        }
        else {
            request.getSession().setAttribute("AtivoFixoAction_localizacao", null);
        }
        
        if (serial != null && !serial.equals("")) {
            request.getSession().setAttribute("AtivoFixoAction_serial", serial);
        }
        else {
            request.getSession().setAttribute("AtivoFixoAction_serial", null);
        }
        
      

        
        return unspecified(mapping, form, request, response);
    }

}

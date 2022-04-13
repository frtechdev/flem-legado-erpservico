/*
 * EbalAction.java
 *
 * Created on 21 de Agosto de 2007, 11:31
 */

package br.org.flem.sac.web.struts.action;

import br.org.flem.fwe.exception.AcessoDadosException;
import br.org.flem.fwe.exception.AplicacaoException;
import br.org.flem.fwe.web.tag.MensagemTag;
import br.org.flem.sac.arquivo.Arquivo;
import br.org.flem.sac.bo.EbalBO;
import br.org.flem.sac.negocio.ArquivoCredicesta;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author fcsilva
 */
public class EbalAction extends DispatchAction {

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("lista", new EbalBO().obterTodosArquivoCredicestaOrdenado());
        }

        catch (AcessoDadosException ex) {
            ex.printStackTrace();
            List erros = new ArrayList();
            erros.add("Erro ao listar: " + ex.getMessage());
            request.setAttribute(MensagemTag.LISTA_MENSAGENS, erros);
        }

        return mapping.findForward("lista");
    }

    public ActionForward gravarArquivoCredicesta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ParseException {

        //Limpa a lista de mensagens da sessao
        request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS,null);
        
        //variavel que contem a lista de mensagens
        List mensagem = new ArrayList();
        DynaActionForm dyna = (DynaActionForm) form;
        FormFile arquivo = (FormFile) dyna.get("arquivoCredicesta");
        
        if(arquivo.getFileName().equals("")){            
            mensagem.add("O arquivo não foi informado.");
            request.setAttribute(MensagemTag.LISTA_MENSAGENS, mensagem);
            return unspecified(mapping, form, request, response);
        }
        
        try {
            ArquivoCredicesta arquivoCredicesta = new ArquivoCredicesta();
            arquivoCredicesta.setArquivo(arquivo.getFileData());
            new EbalBO().inserirArquivoCredicesta(arquivoCredicesta);
        }

        catch (AplicacaoException ade) {
            ade.printStackTrace();            
            mensagem.add("O arquivo não pôde ser gravado: " + ade.getMessage());
            request.setAttribute(MensagemTag.LISTA_MENSAGENS, mensagem);
            return unspecified(mapping, form, request, response);
        }

        catch (IOException fnfe) {
            fnfe.printStackTrace();            
            mensagem.add("Erro ao ler arquivo:" + fnfe.getMessage());
            request.setAttribute(MensagemTag.LISTA_MENSAGENS, mensagem);
            return unspecified(mapping, form, request, response);
        }
        
        mensagem.add("Arquivo importado com sucesso.");
        request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS, mensagem);

        //indirecao para evitar problema do submit multiplo
        request.setAttribute("acao", "Ebal.do");
        return mapping.findForward("redirect");
    }

    public ActionForward gerarArquivoBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaActionForm dyna = (DynaActionForm) form;
        EbalBO ebalBO = new EbalBO();
        Integer idArquivoCredicesta = new Integer(dyna.getString("idArquivoCredicesta"));
        ArquivoCredicesta arquivoCredicesta = ebalBO.obterArquivoCredicestaPorPk(idArquivoCredicesta);
        
        File file = ebalBO.gerarArquivoBatch(arquivoCredicesta);

        //transforma o arquivo num array de bytes
        byte[] arquivo = new Arquivo().lerArquivo(file);
        
        String nomeArquivo = file.getName();

        response.setContentType("application/save");
        response.setHeader("Content-Disposition", "attachment; filename=" + nomeArquivo);

        //escreve o arquivo no buffer de saida
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(arquivo, 0, arquivo.length);

        outStream.flush();
        outStream.close();   
        
        return unspecified(mapping, form, request, response);
    }
    
    public ActionForward gerarArquivoRetornoConciliacao(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaActionForm dyna = (DynaActionForm) form;
        EbalBO ebalBO = new EbalBO();
        Integer idArquivoCredicesta = new Integer(dyna.getString("idArquivoCredicesta"));
        ArquivoCredicesta arquivoCredicesta = ebalBO.obterArquivoCredicestaPorPk(idArquivoCredicesta);
        
        File file = ebalBO.gerarArquivoRetornoConciliacao(arquivoCredicesta);

        //transforma o arquivo num array de bytes
        byte[] arquivo = new Arquivo().lerArquivo(file);
        
        String nomeArquivo = file.getName();

        response.setContentType("application/save");
        response.setHeader("Content-Disposition", "attachment; filename=" + nomeArquivo);

        //escreve o arquivo no buffer de saida
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(arquivo, 0, arquivo.length);

        outStream.flush();
        outStream.close();   
        
        return unspecified(mapping, form, request, response);
    }    

    public ActionForward gerarArquivoCadastrosLimites(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //recupera o arquivo gerado a partir do layout
        File file = new EbalBO().gerarArquivoCadastrosLimites();

        //transforma o arquivo num array de bytes
        byte[] arquivo = new Arquivo().lerArquivo(file);

        String nomeArquivo = file.getName();

        response.setContentType("application/save");
        response.setHeader("Content-Disposition", "attachment; filename=" + nomeArquivo);

        //escreve o arquivo no buffer de saida
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(arquivo, 0, arquivo.length);

        outStream.flush();
        outStream.close();

        return unspecified(mapping, form, request, response);
    }
    
    public ActionForward excluirArquivoCredicesta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaActionForm dyna = (DynaActionForm) form;
        EbalBO ebalBO = new EbalBO();
        Integer idArquivoCredicesta = new Integer(dyna.getString("idArquivoCredicesta"));
        ArquivoCredicesta arquivo = ebalBO.obterArquivoCredicestaPorPk(idArquivoCredicesta);
        ebalBO.excluirArquivoCredicesta(arquivo);
        List mensagem = new ArrayList();
        mensagem.add("Arquivo excluído com sucesso.");
        request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS, mensagem);

        //indirecao para evitar problema do submit multiplo
        request.setAttribute("acao", "Ebal.do");
        return mapping.findForward("redirect");
    }
}

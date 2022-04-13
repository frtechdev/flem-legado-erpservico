/*
 * ERPServicoCriadorRelatorio.java
 * 
 * Created on 01/10/2007, 09:58:17
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.relatorio;

import br.org.flem.fwe.web.relatorio.CriadorRelatorio;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mjpereira
 */
public class ERPServicoCriadorRelatorio extends CriadorRelatorio {

    public ERPServicoCriadorRelatorio() {
    }

       
    /**
     * M�todo que monta o relat�rio com os par�metros e a cole��o de objetos. Este m�todo � privado para que o usu�rio da classe escolha um dos m�todos para a exporta��o desse relat�rio.
     * @param request O request utilizado no momento, pela action.
     * @param localArquivo Local do arquivo, relativo ao contexto da aplica��o.
     * @param parametros Lista de par�metros definida para o relat�rio.
     * @param resultado Cole��o de objetos que ser� utilizada como DataSource do relat�rio.
     * @throws net.sf.jasperreports.engine.JRException Exce��o lan�ada, caso o relat�rio apresente algum problema.
     * @return Relat�rio pronto, que ser� utilizado por algum m�todo de exporta��o.
     */
    protected void montaRelatorio(HttpServletRequest request, Map parametros) {
        parametros.put("logo", request.getSession().getServletContext().getRealPath("/img/flemlogo.gif"));
    }
    
}

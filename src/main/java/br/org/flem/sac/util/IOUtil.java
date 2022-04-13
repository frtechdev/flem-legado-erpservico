/*
 * IOUtil.java
 *
 *Arquivo Utilitario para função de exportação de arquivo.
 *
 * Created on 24 de Outubro de 2006, 17:04
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.sac.util;

import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mjpereira
 */
public class IOUtil {
    
    /** Creates a new instance of IOUtil */
    public IOUtil() {
    }
    public static StringBuffer linhaVazia(int tam){
        StringBuffer sb = new StringBuffer(tam);
        for(int i=0;i<tam;i++){
            sb.append(" ");
        }
        return sb;
        
    }
    
    /**
     * Disponibiliza o 
     * 
     */
    public static void download(HttpServletResponse response){
        
    }
     
    
            
}

/*
 * Propriedades.java
 *
 * Created on 20 de Novembro de 2006, 09:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.sac.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author mjpereira
 */
public class Propriedades {
    
    private Properties props = new Properties();
    private static final Propriedades instancia = new Propriedades();
    
    
    private Propriedades(){
        String arquivo = this.getClass().getClassLoader().getResource("config-erp.properties").getPath();
	try {
		props.load(new FileInputStream(arquivo));
        }catch (IOException e) {
            e.printStackTrace();
	} 
    }
    
    public static Propriedades getInstancia(){
        return instancia;
    }
    
    public String get(String key){
        return props.getProperty(key);
    }
    
}

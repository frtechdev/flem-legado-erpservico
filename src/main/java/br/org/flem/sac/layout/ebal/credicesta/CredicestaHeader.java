/*
 * EbalSaebHeader.java
 *
 * Created on 22 de Agosto de 2007, 09:27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.sac.layout.ebal.credicesta;

import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.util.IOUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fcsilva
 */
public class CredicestaHeader implements Layout{
    private SimpleDateFormat formatoDiaMesAno = new SimpleDateFormat("ddMMyyyy");
    private String constante;
    private int brancos;
    private Date data;
    private String constante2;
    private int brancos2;
    private String constante3;


    public int getBrancos() {
        return brancos;
    }

    public void setBrancos(int brancos) {
        this.brancos = brancos;
    }

    public int getBrancos2() {
        return brancos2;
    }

    public void setBrancos2(int brancos2) {
        this.brancos2 = brancos2;
    }

    public String getConstante() {
        return constante;
    }

    public void setConstante(String constante) {
        this.constante = constante;
    }

    public String getConstante2() {
        return constante2;
    }

    public void setConstante2(String constante2) {
        this.constante2 = constante2;
    }

    public String getConstante3() {
        return constante3;
    }

    public void setConstante3(String constante3) {
        this.constante3 = constante3;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }    
    
    public String toLayout() {
        StringBuffer sb = IOUtil.linhaVazia(122);
        sb.replace(0,28,StringUtil.formatarCampoCaracter(getConstante(), 29));
        sb.replace(29,48,StringUtil.formatarBrancos(getBrancos()));
        sb.replace(49,57,formatoDiaMesAno.format(getData()));
        sb.replace(57,70,StringUtil.formatarCampoCaracter(getConstante2(), 14));
        sb.replace(71,115,StringUtil.formatarBrancos(getBrancos2()));
        return sb.substring(0,122);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
    
}

/*
 * RegistroTipo1.java
 *
 * Created on 22 de Agosto de 2007, 15:50
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.sac.layout.ebal.batch;

/** 
 *
 * @author fcsilva
 */
import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.util.IOUtil; 
import java.util.List;

public class RegistroTipo1 implements Layout{
   
    private int tipoRegistro = 1;
    private String nomeLote = "";
    private int filler1;
    private String empresa = "";
    private int filler2;
    private double valor = 0.0;
    private double referencia;    
    private int filler3;
    private int filler4;

    public int getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(int tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public String getNomeLote() {
        return nomeLote;
    }

    public void setNomeLote(String nomeLote) {
        this.nomeLote = nomeLote;
    }

    public int getFiller1() {
        return filler1;
    }

    public void setFiller1(int filler1) {
        this.filler1 = filler1;
    }    

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getReferencia() {
        return referencia;
    }

    public void setReferencia(double referencia) {
        this.referencia = referencia;
    }

    public int getFiller2() {
        return filler2;
    }

    public void setFiller2(int filler2) {
        this.filler2 = filler2;
    }

    public int getFiller3() {
        return filler3;
    }

    public void setFiller3(int filler3) {
        this.filler3 = filler3;
    }
    

    public int getFiller4() {
        return filler4;
    }

    public void setFiller4(int filler4) {
        this.filler4 = filler4;
    }    
    
    public String toLayout(){
        StringBuffer sb = IOUtil.linhaVazia(82);
        sb.replace(0,0,String.valueOf(this.getTipoRegistro()));
        sb.replace(1,10,StringUtil.formatarCampoCaracter(this.getNomeLote(),10));
        sb.replace(11,14,StringUtil.formatarBrancos(this.getFiller1()));
        sb.replace(15,17,StringUtil.formatarCampoCaracter(this.getEmpresa(),3));
        sb.replace(18,42,StringUtil.formatarBrancos(this.getFiller2()));
        sb.replace(43,59,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValor()),17));
        sb.replace(60,70,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getReferencia(),4),11));
        sb.replace(71,74,StringUtil.formatarBrancos(this.getFiller3()));
        sb.replace(75,81,StringUtil.formatarZeros(this.getFiller4()));
        return sb.substring(0,82);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

}

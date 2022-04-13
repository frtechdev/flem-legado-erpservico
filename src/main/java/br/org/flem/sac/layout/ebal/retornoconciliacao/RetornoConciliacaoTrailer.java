/*
 * ArquivoTrailer.java
 * 
 * Created on 08/08/2007, 15:22:18
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.layout.ebal.retornoconciliacao;

import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.util.IOUtil;
import java.util.List;

/**
 *
 * @author mjpereira
 */
public class RetornoConciliacaoTrailer implements Layout{
    private int indicadorRegistro;
    private double valorArquivo;
    private int brancos;
    private int contador;

    public int getIndicadorRegistro() {
        return indicadorRegistro;
    }

    public void setIndicadorRegistro(int indicadorRegistro) {
        this.indicadorRegistro = indicadorRegistro;
    }

    public double getValorArquivo() {
        return valorArquivo;
    }

    public void setValorArquivo(double valorArquivo) {
        this.valorArquivo = valorArquivo;
    }

    public int getBrancos() {
        return brancos;
    }

    public void setBrancos(int brancos) {
        this.brancos = brancos;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
    
    public String toLayout() {
        StringBuffer sb = IOUtil.linhaVazia(67);
        sb.replace(0,0,String.valueOf(this.getIndicadorRegistro()));
        sb.replace(1,10,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorArquivo()),10));
        sb.replace(11,60,StringUtil.formatarBrancos(this.getBrancos()));
        sb.replace(61,66,StringUtil.formatarCampoNumerico(this.getContador(),6));
        return sb.substring(0,67);        
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

    
}

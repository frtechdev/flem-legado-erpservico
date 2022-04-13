/*
 * RegistroTipo9.java
 *
 * Created on 23 de Agosto de 2007, 09:21
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

public class RegistroTipo9 implements Layout{
    
    private int tipoRegistro = 9;
    private int filler = 74;
    private int numeroRegistros;

    public int getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(int tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public int getFiller() {
        return filler;
    }

    public void setFiller(int filler) {
        this.filler = filler;
    }

    public int getNumeroRegistros() {
        return numeroRegistros;
    }

    public void setNumeroRegistros(int numeroRegistros) {
        this.numeroRegistros = numeroRegistros;
    }    
    
    public String toLayout(){
        StringBuffer sb = IOUtil.linhaVazia(82);
        sb.replace(0,0,StringUtil.formatarCampoNumerico(this.getTipoRegistro(),1));
        sb.replace(1,74,StringUtil.formatarBrancos(this.getFiller()));
        sb.replace(75,81,StringUtil.formatarCampoNumerico(this.getNumeroRegistros(),7));        
        return sb.substring(0,82);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

    
}

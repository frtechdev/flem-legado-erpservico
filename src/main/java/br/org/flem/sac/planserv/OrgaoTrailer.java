/*
 * OrgaoTrailer.java
 *
 * Created on 08/08/2007, 15:22:34
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.planserv;

import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.util.IOUtil;
import java.util.List;

/**
 *
 * @author mjpereira
 */
public class OrgaoTrailer implements Layout {

    private int tipoRegistro = 4;
    private int codigoOrgao = 97;
    private int totalTitulares = 0;
    private int totalDependentes = 0;
    private int nsr = 0;

    public OrgaoTrailer() {
    }


    public int getCodigoOrgao() {
        return codigoOrgao;
    }

    public void setCodigoOrgao(int codigoOrgao) {
        this.codigoOrgao = codigoOrgao;
    }

    public int getNsr() {
        return nsr;
    }

    public void setNsr(int nsr) {
        this.nsr = nsr;
    }

    public int getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(int tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public int getTotalDependentes() {
        return totalDependentes;
    }

    public void setTotalDependentes(int totalDependentes) {
        this.totalDependentes = totalDependentes;
    }

    public int getTotalTitulares() {
        return totalTitulares;
    }

    public void setTotalTitulares(int totalTitulares) {
        this.totalTitulares = totalTitulares;
    }


    public String toLayout() {
            StringBuffer sb = IOUtil.linhaVazia(27);
            sb.replace(0,0,String.valueOf(this.getTipoRegistro()));
            sb.replace(1,2,String.valueOf(this.getCodigoOrgao()));
            sb.replace(3,9,StringUtil.formatarCampoNumerico(String.valueOf(this.getTotalTitulares()), 7)); 
            sb.replace(10,16,StringUtil.formatarCampoNumerico(String.valueOf(this.getTotalDependentes()), 7)); 
            sb.replace(17,26,StringUtil.formatarCampoNumerico(String.valueOf(this.getNsr()), 10));
            return sb.substring(0,27);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

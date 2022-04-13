package br.org.flem.sac.planserv;

import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.util.IOUtil;
import java.util.List;

/**
 *
 * @author mjpereira
 */
public class OrgaoHeader implements Layout {

    private int tipoRegistro = 2;
    private int codigoOrgao = 97;
    private int nsr = 2;

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
    
    public OrgaoHeader() {
    }

    @Override
    public String toLayout() {
            StringBuffer sb = IOUtil.linhaVazia(19);
            sb.replace(0,0,String.valueOf(this.getTipoRegistro()));
            sb.replace(1,2,StringUtil.formatarCampoNumerico(this.getCodigoOrgao() > 99 ? 0 : this.getCodigoOrgao(), 2));//Orgão com de 2 digito
            sb.replace(3,12,StringUtil.formatarCampoNumerico(String.valueOf(this.getNsr()), 10));
            sb.replace(13,17,StringUtil.formatarCampoNumerico(this.getCodigoOrgao() > 99 ? this.getCodigoOrgao() : 0, 5));//Orgão com mais de 2 digito
            return sb.substring(0,18);
    }

    @Override
    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

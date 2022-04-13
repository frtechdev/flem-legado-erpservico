package br.org.flem.sac.planserv;

import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.dao.PropriedadeMensalDAO;
import br.org.flem.sac.negocio.PropriedadeMensal;
import br.org.flem.sac.util.IOUtil;
import java.util.List;

/**
 *
 * @author mccosta
 */
public class TrailerArquivoMensal implements Layout {

    private int tipoRegistro = 9;
    private int totalTitulares = 0;
    private int numeroRegistro = 0;



    public TrailerArquivoMensal() {
    }

    public int getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(int tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public int getTotalTitulares() {
        return totalTitulares;
    }

    public void setTotalTitulares(int totalTitulares) {
        this.totalTitulares = totalTitulares;
    }

    public int getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(int numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String toLayout() {
        StringBuffer sb = IOUtil.linhaVazia(13);
        sb.replace(0,0,String.valueOf(this.getTipoRegistro()));
        sb.replace(1,7,StringUtil.formatarCampoNumerico(String.valueOf(this.getTotalTitulares()), 7));
        sb.replace(8,17,StringUtil.formatarCampoNumerico(String.valueOf(this.getNumeroRegistro()), 10));
        return sb.substring(0,18);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

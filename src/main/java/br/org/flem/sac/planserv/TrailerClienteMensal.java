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
public class TrailerClienteMensal implements Layout {

    private int tipoRegistro = 4;
    private int codigoCliente = 97;
    private String filler = "";
    private int numeroRegistro = 0;



    public TrailerClienteMensal() {
    }

    public int getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(int tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }
    
    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
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
        sb.replace(1,2,String.valueOf(this.getCodigoCliente()));
        sb.replace(3,16,StringUtil.formatarCampoCaracter(String.valueOf(this.getFiller()), 14));
        sb.replace(17,26,StringUtil.formatarCampoNumerico(String.valueOf(this.getNumeroRegistro()), 10));
        return sb.substring(0,27);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

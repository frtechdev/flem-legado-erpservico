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
public class ClienteHeaderMensal implements Layout {

    private int tipoRegistro = 2;
    private int codigoCliente = 97;
    private int numeroRegistro = 0;

    public ClienteHeaderMensal() {
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
            sb.replace(3,12,StringUtil.formatarCampoNumerico(String.valueOf(this.getNumeroRegistro()), 10));
            return sb.substring(0,13);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

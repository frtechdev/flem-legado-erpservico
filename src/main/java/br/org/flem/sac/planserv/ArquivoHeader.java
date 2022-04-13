package br.org.flem.sac.planserv;

import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.bo.ArquivosPlanservBO;
import br.org.flem.sac.util.IOUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mjpereira
 */
public class ArquivoHeader implements Layout {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private SimpleDateFormat shf = new SimpleDateFormat("hhmmss");
    private int tipoRegistro = 1;
    private String stemaOrigem = "FLEM";
    private Date dataGravacao = new Date();
    private int nsr = 1;

    public Date getDataGravacao() {
        return dataGravacao;
    }

    public void setDataGravacao(Date dataGravacao) {
        this.dataGravacao = dataGravacao;
    }

    public int getNsa() {
        try {
            return new ArquivosPlanservBO().getUltimoArquivo().getNumeroRegistro()+1;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }


    public int getNsr() {
        return nsr;
    }

    public void setNsr(int nsr) {
        this.nsr = nsr;
    }

    public String getStemaOrigem() {
        return stemaOrigem;
    }

    public void setStemaOrigem(String stemaOrigem) {
        this.stemaOrigem = stemaOrigem;
    }

    public int getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(int tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public ArquivoHeader() {
    }

    public String toLayout() {
            StringBuffer sb = IOUtil.linhaVazia(40);
            sb.replace(0,0,String.valueOf(this.getTipoRegistro()));
            sb.replace(1,5, this.getStemaOrigem());
            sb.replace(6,13, sdf.format(this.getDataGravacao()));
            sb.replace(14,19,shf.format(this.getDataGravacao()));
            sb.replace(20,29,StringUtil.formatarCampoNumerico(String.valueOf(this.getNsa()), 10));        
            sb.replace(30,39,StringUtil.formatarCampoNumerico(String.valueOf(this.getNsr()), 10));
            return sb.substring(0,40);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

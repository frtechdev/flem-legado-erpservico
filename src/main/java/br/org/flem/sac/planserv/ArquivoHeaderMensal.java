package br.org.flem.sac.planserv;

import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.dao.PropriedadeMensalDAO;
import br.org.flem.sac.negocio.PropriedadeMensal;
import br.org.flem.sac.util.IOUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mccosta
 */
public class ArquivoHeaderMensal implements Layout {
    private String tipoRegistro = "1";
    private String siglaOrgao = "FLEM";
    private Date dataGravacao = new Date();
    private SimpleDateFormat dataGeracaoArquivo = new SimpleDateFormat("yyyyMMdd");
    private SimpleDateFormat horaGeracaoArquivo = new SimpleDateFormat("hhmmss");
    private String filler = "";
    private int numeroRegistro = 0;


    public ArquivoHeaderMensal() {
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public String getSiglaOrgao() {
        return siglaOrgao;
    }

    public void setSiglaOrgao(String siglaOrgao) {
        this.siglaOrgao = siglaOrgao;
    }

    public Date getDataGravacao() {
        return dataGravacao;
    }

    public void setDataGravacao(Date dataGravacao) {
        this.dataGravacao = dataGravacao;
    }

    public SimpleDateFormat getDataGeracaoArquivo() {
        return dataGeracaoArquivo;
    }

    public void setDataGeracaoArquivo(SimpleDateFormat dataGeracaoArquivo) {
        this.dataGeracaoArquivo = dataGeracaoArquivo;
    }

    public SimpleDateFormat getHoraGeracaoArquivo() {
        return horaGeracaoArquivo;
    }

    public void setHoraGeracaoArquivo(SimpleDateFormat horaGeracaoArquivo) {
        this.horaGeracaoArquivo = horaGeracaoArquivo;
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
        StringBuffer sb = IOUtil.linhaVazia(40);
        sb.replace(0,0,this.getTipoRegistro());
        sb.replace(1,5, StringUtil.formatarCampoCaracter(this.getSiglaOrgao(),5));
        sb.replace(6,13, dataGeracaoArquivo.format(this.getDataGravacao()));
        sb.replace(14,19,horaGeracaoArquivo.format(this.getDataGravacao()));
        sb.replace(20,21, StringUtil.formatarCampoCaracter(this.getFiller(),2));
        sb.replace(22,31,StringUtil.formatarCampoNumerico(String.valueOf(this.getNumeroRegistro()), 10));
        return sb.substring(0,32);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

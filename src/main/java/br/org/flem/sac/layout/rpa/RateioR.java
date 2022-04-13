package br.org.flem.sac.layout.rpa;

import br.org.flem.sac.arquivo.Layout;
import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.util.IOUtil;
import java.util.List;


/**
 *
 * @author MCCosta
 */
public class RateioR implements Layout{
    String percent[];
    
    // Linha 07 - (R) Rateio
    private String tipoRegistroR = "600";   //A 03 1 - 3 Fixo "600"
    private Integer seqRegistroRateioR;      //N 05 4 - 8
    private String tipoItemR;               //A 01 9 - 9 (0 - Serviço, 1 - Material, 2 ? Despesas/Outros Pagtos, 9 - Ajuste)
    private Integer seqItemDocR;             //N 05 10 - 14
    private Integer seqRateioR;              //N 05 15 - 19
    private Double valorR;                  //N 18 20 - 37
    private Double percentualR;             //N 3,7 38 - 47
    private Double valorAbsolutoR;          //N 18 48 - 65
    private String campoUsuario1R;          //A 60 66 - 125
    private String campoUsuario2R;          //A 60 126 - 185
    private String campoUsuario3R;          //A 60 186 - 245
    private String campoUsuario4R;          //A 60 246 - 305
    private String campoUsuario5R;          //A 60 306 - 365
    
    public RateioR() {
    
    }

    public String getCampoUsuario1R() {
        return campoUsuario1R;
    }

    public void setCampoUsuario1R(String campoUsuario1R) {
        this.campoUsuario1R = campoUsuario1R;
    }

    public String getCampoUsuario2R() {
        return campoUsuario2R;
    }

    public void setCampoUsuario2R(String campoUsuario2R) {
        this.campoUsuario2R = campoUsuario2R;
    }

    public String getCampoUsuario3R() {
        return campoUsuario3R;
    }

    public void setCampoUsuario3R(String campoUsuario3R) {
        this.campoUsuario3R = campoUsuario3R;
    }

    public String getCampoUsuario4R() {
        return campoUsuario4R;
    }

    public void setCampoUsuario4R(String campoUsuario4R) {
        this.campoUsuario4R = campoUsuario4R;
    }

    public String getCampoUsuario5R() {
        return campoUsuario5R;
    }

    public void setCampoUsuario5R(String campoUsuario5R) {
        this.campoUsuario5R = campoUsuario5R;
    }

    public Double getPercentualR() {
        return percentualR;
    }

    public void setPercentualR(Double percentualR) {
        this.percentualR = percentualR;
    }

    public Integer getSeqItemDocR() {
        return seqItemDocR;
    }

    public void setSeqItemDocR(Integer seqItemDocR) {
        this.seqItemDocR = seqItemDocR;
    }

    public Integer getSeqRateioR() {
        return seqRateioR;
    }

    public void setSeqRateioR(Integer seqRateioR) {
        this.seqRateioR = seqRateioR;
    }

    public Integer getSeqRegistroRateioR() {
        return seqRegistroRateioR;
    }

    public void setSeqRegistroRateioR(Integer seqRegistroRateioR) {
        this.seqRegistroRateioR = seqRegistroRateioR;
    }

    public String getTipoItemR() {
        return tipoItemR;
    }

    public void setTipoItemR(String tipoItemR) {
        this.tipoItemR = tipoItemR;
    }

    public String getTipoRegistroR() {
        return tipoRegistroR;
    }

    public void setTipoRegistroR(String tipoRegistroR) {
        this.tipoRegistroR = tipoRegistroR;
    }

    public Double getValorAbsolutoR() {
        return valorAbsolutoR;
    }

    public void setValorAbsolutoR(Double valorAbsolutoR) {
        this.valorAbsolutoR = valorAbsolutoR;
    }

    public Double getValorR() {
        return valorR;
    }

    public void setValorR(Double valorR) {
        this.valorR = valorR;
    }
    
    
    
    public String toLayout() {
        StringBuffer linha = IOUtil.linhaVazia(365);
        
        linha.replace(0,2,String.valueOf(this.getTipoRegistroR()));
        linha.replace(3,7,StringUtil.formatarCampoNumerico(String.valueOf(this.getSeqRegistroRateioR()),5));
        linha.replace(8,8,String.valueOf(this.getTipoItemR()));
        linha.replace(9,13,StringUtil.formatarCampoNumerico(String.valueOf(this.getSeqItemDocR()),5));
        linha.replace(14,18,StringUtil.formatarCampoNumerico(String.valueOf(this.getSeqRateioR()),5));
        linha.replace(19,36,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorR()),18));
        percent = String.valueOf(this.getPercentualR()).split("\\.");
        linha.replace(37,39,StringUtil.formatarCampoNumerico(percent[0],3));
        linha.replace(40,46,StringUtil.formatarCampoNumerico(percent[1],7));
        linha.replace(47,64,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorAbsolutoR()),18));
        linha.replace(65,124,StringUtil.formatarCampoCaracter(this.getCampoUsuario1R(),60));
        linha.replace(125,184,StringUtil.formatarCampoCaracter(this.getCampoUsuario2R(),60));
        linha.replace(185,244,StringUtil.formatarCampoCaracter(this.getCampoUsuario3R(),60));
        linha.replace(245,304,StringUtil.formatarCampoCaracter(this.getCampoUsuario4R(),60));
        linha.replace(305,364,StringUtil.formatarCampoCaracter(this.getCampoUsuario5R(),60));

        return linha.substring(0,364);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

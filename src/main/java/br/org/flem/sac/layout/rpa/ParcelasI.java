package br.org.flem.sac.layout.rpa;

import br.org.flem.sac.arquivo.Layout;
import java.text.SimpleDateFormat;
import java.util.Date;
import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.util.IOUtil;
import java.util.List;

/**
 *
 * @author MCCosta
 */
public class ParcelasI implements Layout{
    
    // Linha 09 - (I) - Parcelas 
    private String tipoRegistroI = "700";   //A 03 1 - 3 Fixo "700"
    private Integer seqRegistroParcelaI;     //N 05 4 - 8
    private Date dtaVencimentoI;            //N 08 9 - 16
    private Date dtaPagamentoI;             //N 08 17 - 24
    private Double valorI;                  //N 18 25 - 42
    private Double valorLiquidoParcelaI;    //N 18 43 - 60
    private String identPortadorI;          //A 06 61 - 66
    private String numDocPortadorI;         //A 16 67 - 82
    private String tipoCodBarrasI;          //A 06 83 - 88
    private String identCodBarrasI;         //A 48 89 - 136
    private String codBarrasI;              //A 48 137 - 184

    public ParcelasI() {
        
    }

    public String getCodBarrasI() {
        return codBarrasI;
    }

    public void setCodBarrasI(String codBarrasI) {
        this.codBarrasI = codBarrasI;
    }

    public Date getDtaPagamentoI() {
        return dtaPagamentoI;
    }

    public void setDtaPagamentoI(Date dtaPagamentoI) {
        this.dtaPagamentoI = dtaPagamentoI;
    }

    public Date getDtaVencimentoI() {
        return dtaVencimentoI;
    }

    public void setDtaVencimentoI(Date dtaVencimentoI) {
        this.dtaVencimentoI = dtaVencimentoI;
    }

    public String getIdentCodBarrasI() {
        return identCodBarrasI;
    }

    public void setIdentCodBarrasI(String identCodBarrasI) {
        this.identCodBarrasI = identCodBarrasI;
    }

    public String getIdentPortadorI() {
        return identPortadorI;
    }

    public void setIdentPortadorI(String identPortadorI) {
        this.identPortadorI = identPortadorI;
    }

    public String getNumDocPortadorI() {
        return numDocPortadorI;
    }

    public void setNumDocPortadorI(String numDocPortadorI) {
        this.numDocPortadorI = numDocPortadorI;
    }

    public Integer getSeqRegistroParcelaI() {
        return seqRegistroParcelaI;
    }

    public void setSeqRegistroParcelaI(Integer seqRegistroParcelaI) {
        this.seqRegistroParcelaI = seqRegistroParcelaI;
    }

    public String getTipoCodBarrasI() {
        return tipoCodBarrasI;
    }

    public void setTipoCodBarrasI(String tipoCodBarrasI) {
        this.tipoCodBarrasI = tipoCodBarrasI;
    }

    public String getTipoRegistroI() {
        return tipoRegistroI;
    }

    public void setTipoRegistroI(String tipoRegistroI) {
        this.tipoRegistroI = tipoRegistroI;
    }

    public Double getValorI() {
        return valorI;
    }

    public void setValorI(Double valorI) {
        this.valorI = valorI;
    }

    public Double getValorLiquidoParcelaI() {
        return valorLiquidoParcelaI;
    }

    public void setValorLiquidoParcelaI(Double valorLiquidoParcelaI) {
        this.valorLiquidoParcelaI = valorLiquidoParcelaI;
    }
    
    public String toLayout() {
        SimpleDateFormat formatoAnoMesDia = new SimpleDateFormat("yyyyMMdd");
        StringBuffer linha = IOUtil.linhaVazia(184);
        
        linha.replace(0,2,String.valueOf(this.getTipoRegistroI()));
        linha.replace(3,7,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getSeqRegistroParcelaI()),5));
        linha.replace(8,15,String.valueOf(formatoAnoMesDia.format(this.getDtaVencimentoI())));
        linha.replace(16,23,String.valueOf(formatoAnoMesDia.format(this.getDtaPagamentoI())));
        linha.replace(24,41,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorI()),18));
        linha.replace(42,59,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorLiquidoParcelaI()),18));
        linha.replace(60,65,StringUtil.formatarCampoCaracter(this.getIdentPortadorI(),6));
        linha.replace(66,81,StringUtil.formatarCampoCaracter(this.getNumDocPortadorI(),16));
        linha.replace(82,87,StringUtil.formatarCampoCaracter(this.getTipoCodBarrasI(),6));
        linha.replace(88,135,StringUtil.formatarCampoCaracter(this.getIdentCodBarrasI(),48));
        linha.replace(136,183,StringUtil.formatarCampoCaracter(this.getCodBarrasI(),48));
        
        return linha.substring(0,183);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

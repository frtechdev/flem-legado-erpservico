package br.org.flem.sac.layout.rpa;

import br.org.flem.sac.arquivo.Layout;
import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.util.IOUtil;
import java.util.List;

/**
 *
 * @author MCCosta
 */
public class ParcelasITAX implements Layout{
    
    // Linha 10 - (ITAX) Parcelas Tributos
    private String tipoRegistroT = "710";   //A 03 1 - 3 Fixo "710"
    private Integer seqRegistroParcelaT;     //N 05 4 - 8
    private Double valorTributadoT;         //N 18 9 - 26
    private Double baseCalcPIST;            //N 18 27 - 44
    private Double valorPIST;               //N 18 45 - 62
    private Double baseCalcCOFINST;         //N 18 63 - 80
    private Double valorCOFINST;            //N 18 81 - 98
    private Double baseCalcCSLLT;           //N 18 99 - 116
    private Double valorCSLLT;              //N 18 117 - 134
    private Double baseCalcIRT;             //N 18 135 - 152
    private Double valorIRT;                //N 18 153 - 170
    private Double baseCalcINSST;           //N 18 171 - 188
    private Double valorINSST;              //N 18 189 - 206
    private Double baseCalcISST;            //N 18 207 - 224
    private Double valorISST;               //N 18 225 - 242


    public ParcelasITAX() {
        
    }

    public Double getBaseCalcCOFINST() {
        return baseCalcCOFINST;
    }

    public void setBaseCalcCOFINST(Double baseCalcCOFINST) {
        this.baseCalcCOFINST = baseCalcCOFINST;
    }

    public Double getBaseCalcCSLLT() {
        return baseCalcCSLLT;
    }

    public void setBaseCalcCSLLT(Double baseCalcCSLLT) {
        this.baseCalcCSLLT = baseCalcCSLLT;
    }

    public Double getBaseCalcINSST() {
        return baseCalcINSST;
    }

    public void setBaseCalcINSST(Double baseCalcINSST) {
        this.baseCalcINSST = baseCalcINSST;
    }

    public Double getBaseCalcIRT() {
        return baseCalcIRT;
    }

    public void setBaseCalcIRT(Double baseCalcIRT) {
        this.baseCalcIRT = baseCalcIRT;
    }

    public Double getBaseCalcISST() {
        return baseCalcISST;
    }

    public void setBaseCalcISST(Double baseCalcISST) {
        this.baseCalcISST = baseCalcISST;
    }

    public Double getBaseCalcPIST() {
        return baseCalcPIST;
    }

    public void setBaseCalcPIST(Double baseCalcPIST) {
        this.baseCalcPIST = baseCalcPIST;
    }

    public Integer getSeqRegistroParcelaT() {
        return seqRegistroParcelaT;
    }

    public void setSeqRegistroParcelaT(Integer seqRegistroParcelaT) {
        this.seqRegistroParcelaT = seqRegistroParcelaT;
    }

    public String getTipoRegistroT() {
        return tipoRegistroT;
    }

    public void setTipoRegistroT(String tipoRegistroT) {
        this.tipoRegistroT = tipoRegistroT;
    }

    public Double getValorCOFINST() {
        return valorCOFINST;
    }

    public void setValorCOFINST(Double valorCOFINST) {
        this.valorCOFINST = valorCOFINST;
    }

    public Double getValorCSLLT() {
        return valorCSLLT;
    }

    public void setValorCSLLT(Double valorCSLLT) {
        this.valorCSLLT = valorCSLLT;
    }

    public Double getValorINSST() {
        return valorINSST;
    }

    public void setValorINSST(Double valorINSST) {
        this.valorINSST = valorINSST;
    }

    public Double getValorIRT() {
        return valorIRT;
    }

    public void setValorIRT(Double valorIRT) {
        this.valorIRT = valorIRT;
    }

    public Double getValorISST() {
        return valorISST;
    }

    public void setValorISST(Double valorISST) {
        this.valorISST = valorISST;
    }

    public Double getValorPIST() {
        return valorPIST;
    }

    public void setValorPIST(Double valorPIST) {
        this.valorPIST = valorPIST;
    }

    public Double getValorTributadoT() {
        return valorTributadoT;
    }

    public void setValorTributadoT(Double valorTributadoT) {
        this.valorTributadoT = valorTributadoT;
    }
    
    public String toLayout() {
        StringBuffer linha = IOUtil.linhaVazia(184);
        
        linha.replace(0,2,String.valueOf(this.getTipoRegistroT()));
        linha.replace(3,7,StringUtil.formatarCampoNumerico(String.valueOf(this.getSeqRegistroParcelaT()),5));
        linha.replace(8,25,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorTributadoT()),18));
        linha.replace(26,43,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getBaseCalcPIST()),18));
        linha.replace(44,61,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorPIST()),18));
        
        linha.replace(62,79,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getBaseCalcCOFINST()),18));
        linha.replace(80,97,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorCOFINST()),18));
        linha.replace(98,115,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getBaseCalcCSLLT()),18));
        linha.replace(116,133,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorCSLLT()),18));
        linha.replace(134,151,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getBaseCalcIRT()),18));
        linha.replace(152,169,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorIRT()),18));
        linha.replace(170,187,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getBaseCalcINSST()),18));
        linha.replace(188,205,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorINSST()),18));
        linha.replace(206,223,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getBaseCalcISST()),18));
        linha.replace(224,241,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorISST()),18));

        return linha.substring(0,241);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

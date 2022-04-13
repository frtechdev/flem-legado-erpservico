package br.org.flem.sac.layout.rpa;

import br.org.flem.sac.arquivo.Layout;
import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.util.IOUtil;
import java.util.List;

/**
 *
 * @author MCCosta
 */
public class ServicosL1TAX implements Layout {
    String percent[];
    
    // Linha 05 - (L1TAX ) Serviços - Tributos  
    private String tipoRegistroT = "110";   //A 03 1 - 3 Fixo "110"
    private Integer seqRegistroServicoT;     //N 05 4 - 8 
    private Double aliquotaIRRetencaoT;     //N 3,7 9 - 18
    private Double baseCalcIRRetencaoT;     //N 18 19 - 36
    private Double reducaoBaseIRRetencaoT;  //N 18 37 - 54
    private Double valorIRRetencaoT;        //N 18 55 - 72
    private Double aliquotaINSSRetencaoT;     //N 3,7 73 - 82
    private Double baseCalcINSSRetencaoT;     //N 18 83 - 100
    private Double reducaoBaseINSSRetencaoT;  //N 18 101 - 118
    private Double valorINSSRetencaoT;        //N 18 119 - 136
    private Double aliquotaPISRetencaoT;     //N 3,7 137 - 146
    private Double baseCalcPISRetencaoT;     //N 18 147 - 164
    private Double reducaoBasePISRetencaoT;  //N 18 165 - 182
    private Double valorPISRetencaoT;        //N 18 183 - 200
    private Double aliquotaCOFINSRetencaoT;     //N 3,7 201 - 210
    private Double baseCalcCOFINSRetencaoT;     //N 18 211 - 228
    private Double reducaoBaseCOFINSRetencaoT;  //N 18 229 - 246
    private Double valorCOFINSRetencaoT;        //N 18 247 - 264
    private Double aliquotaCSLLRetencaoT;     //N 3,7 265 - 274
    private Double baseCalcCSLLRetencaoT;     //N 18 275 - 292
    private Double reducaoBaseCSLLRetencaoT;  //N 18 293 - 310
    private Double valorCSLLRetencaoT;        //N 18 311 - 328
    private Double aliquotaISSRetencaoT;     //N 3,7 329 - 338
    private Double baseCalcISSRetencaoT;     //N 18 339 - 356
    private Double reducaoBaseISSRetencaoT;  //N 18 357 - 374
    private Double valorISSRetencaoT;        //N 18 375 - 392
    
    public ServicosL1TAX() {
    
    }

    public Double getAliquotaCOFINSRetencaoT() {
        return aliquotaCOFINSRetencaoT;
    }

    public void setAliquotaCOFINSRetencaoT(Double aliquotaCOFINSRetencaoT) {
        this.aliquotaCOFINSRetencaoT = aliquotaCOFINSRetencaoT;
    }

    public Double getAliquotaCSLLRetencaoT() {
        return aliquotaCSLLRetencaoT;
    }

    public void setAliquotaCSLLRetencaoT(Double aliquotaCSLLRetencaoT) {
        this.aliquotaCSLLRetencaoT = aliquotaCSLLRetencaoT;
    }

    public Double getAliquotaINSSRetencaoT() {
        return aliquotaINSSRetencaoT;
    }

    public void setAliquotaINSSRetencaoT(Double aliquotaINSSRetencaoT) {
        this.aliquotaINSSRetencaoT = aliquotaINSSRetencaoT;
    }

    public Double getAliquotaIRRetencaoT() {
        return aliquotaIRRetencaoT;
    }

    public void setAliquotaIRRetencaoT(Double aliquotaIRRetencaoT) {
        this.aliquotaIRRetencaoT = aliquotaIRRetencaoT;
    }

    public Double getAliquotaISSRetencaoT() {
        return aliquotaISSRetencaoT;
    }

    public void setAliquotaISSRetencaoT(Double aliquotaISSRetencaoT) {
        this.aliquotaISSRetencaoT = aliquotaISSRetencaoT;
    }

    public Double getAliquotaPISRetencaoT() {
        return aliquotaPISRetencaoT;
    }

    public void setAliquotaPISRetencaoT(Double aliquotaPISRetencaoT) {
        this.aliquotaPISRetencaoT = aliquotaPISRetencaoT;
    }

    public Double getBaseCalcCOFINSRetencaoT() {
        return baseCalcCOFINSRetencaoT;
    }

    public void setBaseCalcCOFINSRetencaoT(Double baseCalcCOFINSRetencaoT) {
        this.baseCalcCOFINSRetencaoT = baseCalcCOFINSRetencaoT;
    }

    public Double getBaseCalcCSLLRetencaoT() {
        return baseCalcCSLLRetencaoT;
    }

    public void setBaseCalcCSLLRetencaoT(Double baseCalcCSLLRetencaoT) {
        this.baseCalcCSLLRetencaoT = baseCalcCSLLRetencaoT;
    }

    public Double getBaseCalcINSSRetencaoT() {
        return baseCalcINSSRetencaoT;
    }

    public void setBaseCalcINSSRetencaoT(Double baseCalcINSSRetencaoT) {
        this.baseCalcINSSRetencaoT = baseCalcINSSRetencaoT;
    }

    public Double getBaseCalcIRRetencaoT() {
        return baseCalcIRRetencaoT;
    }

    public void setBaseCalcIRRetencaoT(Double baseCalcIRRetencaoT) {
        this.baseCalcIRRetencaoT = baseCalcIRRetencaoT;
    }

    public Double getBaseCalcISSRetencaoT() {
        return baseCalcISSRetencaoT;
    }

    public void setBaseCalcISSRetencaoT(Double baseCalcISSRetencaoT) {
        this.baseCalcISSRetencaoT = baseCalcISSRetencaoT;
    }

    public Double getBaseCalcPISRetencaoT() {
        return baseCalcPISRetencaoT;
    }

    public void setBaseCalcPISRetencaoT(Double baseCalcPISRetencaoT) {
        this.baseCalcPISRetencaoT = baseCalcPISRetencaoT;
    }

    public String[] getPercent() {
        return percent;
    }

    public void setPercent(String[] percent) {
        this.percent = percent;
    }

    public Double getReducaoBaseCOFINSRetencaoT() {
        return reducaoBaseCOFINSRetencaoT;
    }

    public void setReducaoBaseCOFINSRetencaoT(Double reducaoBaseCOFINSRetencaoT) {
        this.reducaoBaseCOFINSRetencaoT = reducaoBaseCOFINSRetencaoT;
    }

    public Double getReducaoBaseCSLLRetencaoT() {
        return reducaoBaseCSLLRetencaoT;
    }

    public void setReducaoBaseCSLLRetencaoT(Double reducaoBaseCSLLRetencaoT) {
        this.reducaoBaseCSLLRetencaoT = reducaoBaseCSLLRetencaoT;
    }

    public Double getReducaoBaseINSSRetencaoT() {
        return reducaoBaseINSSRetencaoT;
    }

    public void setReducaoBaseINSSRetencaoT(Double reducaoBaseINSSRetencaoT) {
        this.reducaoBaseINSSRetencaoT = reducaoBaseINSSRetencaoT;
    }

    public Double getReducaoBaseIRRetencaoT() {
        return reducaoBaseIRRetencaoT;
    }

    public void setReducaoBaseIRRetencaoT(Double reducaoBaseIRRetencaoT) {
        this.reducaoBaseIRRetencaoT = reducaoBaseIRRetencaoT;
    }

    public Double getReducaoBaseISSRetencaoT() {
        return reducaoBaseISSRetencaoT;
    }

    public void setReducaoBaseISSRetencaoT(Double reducaoBaseISSRetencaoT) {
        this.reducaoBaseISSRetencaoT = reducaoBaseISSRetencaoT;
    }

    public Double getReducaoBasePISRetencaoT() {
        return reducaoBasePISRetencaoT;
    }

    public void setReducaoBasePISRetencaoT(Double reducaoBasePISRetencaoT) {
        this.reducaoBasePISRetencaoT = reducaoBasePISRetencaoT;
    }

    public Integer getSeqRegistroServicoT() {
        return seqRegistroServicoT;
    }

    public void setSeqRegistroServicoT(Integer seqRegistroServicoT) {
        this.seqRegistroServicoT = seqRegistroServicoT;
    }

    public String getTipoRegistroT() {
        return tipoRegistroT;
    }

    public void setTipoRegistroT(String tipoRegistroT) {
        this.tipoRegistroT = tipoRegistroT;
    }

    public Double getValorCOFINSRetencaoT() {
        return valorCOFINSRetencaoT;
    }

    public void setValorCOFINSRetencaoT(Double valorCOFINSRetencaoT) {
        this.valorCOFINSRetencaoT = valorCOFINSRetencaoT;
    }

    public Double getValorCSLLRetencaoT() {
        return valorCSLLRetencaoT;
    }

    public void setValorCSLLRetencaoT(Double valorCSLLRetencaoT) {
        this.valorCSLLRetencaoT = valorCSLLRetencaoT;
    }

    public Double getValorINSSRetencaoT() {
        return valorINSSRetencaoT;
    }

    public void setValorINSSRetencaoT(Double valorINSSRetencaoT) {
        this.valorINSSRetencaoT = valorINSSRetencaoT;
    }

    public Double getValorIRRetencaoT() {
        return valorIRRetencaoT;
    }

    public void setValorIRRetencaoT(Double valorIRRetencaoT) {
        this.valorIRRetencaoT = valorIRRetencaoT;
    }

    public Double getValorISSRetencaoT() {
        return valorISSRetencaoT;
    }

    public void setValorISSRetencaoT(Double valorISSRetencaoT) {
        this.valorISSRetencaoT = valorISSRetencaoT;
    }

    public Double getValorPISRetencaoT() {
        return valorPISRetencaoT;
    }

    public void setValorPISRetencaoT(Double valorPISRetencaoT) {
        this.valorPISRetencaoT = valorPISRetencaoT;
    }
    
    
    public String toLayout() {
        StringBuffer linha = IOUtil.linhaVazia(392);
        
        linha.replace(0,2,String.valueOf(this.getTipoRegistroT()));
        linha.replace(3,7,StringUtil.formatarCampoNumerico(String.valueOf(this.getSeqRegistroServicoT()),5));
        percent = String.valueOf(this.getAliquotaIRRetencaoT()).split("\\.");
        linha.replace(8,10,StringUtil.formatarCampoNumerico(percent[0],3));
        linha.replace(11,17,StringUtil.formatarCampoNumerico(percent[1],7));
        linha.replace(18,35,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getBaseCalcIRRetencaoT()),18));
        linha.replace(36,53,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getReducaoBaseIRRetencaoT()),18));
        linha.replace(54,71,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorIRRetencaoT()),18));
        percent = String.valueOf(this.getAliquotaINSSRetencaoT()).split("\\.");
        linha.replace(72,74,StringUtil.formatarCampoNumerico(percent[0],3));
        linha.replace(75,81,StringUtil.formatarCampoNumerico(percent[1],7));
        linha.replace(82,99,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getBaseCalcINSSRetencaoT()),18));
        linha.replace(100,117,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getReducaoBaseINSSRetencaoT()),18));
        linha.replace(118,135,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorINSSRetencaoT()),18));
        percent = String.valueOf(this.getAliquotaPISRetencaoT()).split("\\.");
        linha.replace(136,138,StringUtil.formatarCampoNumerico(percent[0],3));
        linha.replace(139,145,StringUtil.formatarCampoNumerico(percent[1],7));
        linha.replace(146,163,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getBaseCalcPISRetencaoT()),18));
        linha.replace(164,181,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getReducaoBasePISRetencaoT()),18));
        linha.replace(182,199,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorPISRetencaoT()),18));
        percent = String.valueOf(this.getAliquotaCOFINSRetencaoT()).split("\\.");
        linha.replace(200,202,StringUtil.formatarCampoNumerico(percent[0],3));
        linha.replace(203,209,StringUtil.formatarCampoNumerico(percent[1],7));
        linha.replace(210,227,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getBaseCalcCOFINSRetencaoT()),18));
        linha.replace(228,245,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getReducaoBaseCOFINSRetencaoT()),18));
        linha.replace(246,263,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorCOFINSRetencaoT()),18));
        percent = String.valueOf(this.getAliquotaCSLLRetencaoT()).split("\\.");
        linha.replace(264,266,StringUtil.formatarCampoNumerico(percent[0],3));
        linha.replace(267,273,StringUtil.formatarCampoNumerico(percent[1],7));
        linha.replace(274,291,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getBaseCalcCSLLRetencaoT()),18));
        linha.replace(292,309,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getReducaoBaseCSLLRetencaoT()),18));
        linha.replace(310,327,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorCSLLRetencaoT()),18));
        percent = String.valueOf(this.getAliquotaISSRetencaoT()).split("\\.");
        linha.replace(328,330,StringUtil.formatarCampoNumerico(percent[0],3));
        linha.replace(331,337,StringUtil.formatarCampoNumerico(percent[1],7));
        linha.replace(338,355,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getBaseCalcISSRetencaoT()),18));
        linha.replace(356,373,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getReducaoBaseISSRetencaoT()),18));
        linha.replace(374,391,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorISSRetencaoT()),18));
        
        return linha.substring(0,391);
        
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

package br.org.flem.sac.layout.rpa;

import br.org.flem.sac.arquivo.Layout;
import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.util.IOUtil;
import java.util.List;

/**
 *
 * @author MCCosta
 */
public class RateioRACT implements Layout{
    
    // Linha 08 - (RACT) Rateio Contábil / Financeiro / Orçamentário
    private String tipoRegistroCFO = "610"; //A 03 1 - 3 Fixo "610"
    private Integer seqRegistroRateioCFO;    //N 05 4 - 8
    private String tipoItemCFO;             //A 01 9 - 9 (0 - Serviço, 1 - Material, 2 ? Despesas/Outros Pagtos, 9 - Ajuste)
    private Integer seqItemDocCFO;           //N 05 10 - 14
    private Integer seqRateioCFO;            //N 05 15 - 19
    private String contaContabilCFO;        //A 16 20 - 35
    private String contaContabil1CFO;       //A 12 36 - 47
    private String contaContabil2CFO;       //A 12 48 - 59
    private String contaContabil3CFO;       //A 12 60 - 71
    private String contaContabil4CFO;       //A 12 72 - 83
    private String contaContabilOrcamentariaCFO;    //A 16 84 - 99
    private String centroOrcamentaria1CFO;  //A 12 100 - 111
    private String centroOrcamentaria2CFO;  //A 12 112 - 123
    private String centroOrcamentaria3CFO;  //A 12 124 - 135
    private String centroOrcamentaria4CFO;  //A 12 136 - 147


    public RateioRACT() {
    
    }

    public String getCentroOrcamentaria1CFO() {
        return centroOrcamentaria1CFO;
    }

    public void setCentroOrcamentaria1CFO(String centroOrcamentaria1CFO) {
        this.centroOrcamentaria1CFO = centroOrcamentaria1CFO;
    }

    public String getCentroOrcamentaria2CFO() {
        return centroOrcamentaria2CFO;
    }

    public void setCentroOrcamentaria2CFO(String centroOrcamentaria2CFO) {
        this.centroOrcamentaria2CFO = centroOrcamentaria2CFO;
    }

    public String getCentroOrcamentaria3CFO() {
        return centroOrcamentaria3CFO;
    }

    public void setCentroOrcamentaria3CFO(String centroOrcamentaria3CFO) {
        this.centroOrcamentaria3CFO = centroOrcamentaria3CFO;
    }

    public String getCentroOrcamentaria4CFO() {
        return centroOrcamentaria4CFO;
    }

    public void setCentroOrcamentaria4CFO(String centroOrcamentaria4CFO) {
        this.centroOrcamentaria4CFO = centroOrcamentaria4CFO;
    }

    public String getContaContabil1CFO() {
        return contaContabil1CFO;
    }

    public void setContaContabil1CFO(String contaContabil1CFO) {
        this.contaContabil1CFO = contaContabil1CFO;
    }

    public String getContaContabil2CFO() {
        return contaContabil2CFO;
    }

    public void setContaContabil2CFO(String contaContabil2CFO) {
        this.contaContabil2CFO = contaContabil2CFO;
    }

    public String getContaContabil3CFO() {
        return contaContabil3CFO;
    }

    public void setContaContabil3CFO(String contaContabil3CFO) {
        this.contaContabil3CFO = contaContabil3CFO;
    }

    public String getContaContabil4CFO() {
        return contaContabil4CFO;
    }

    public void setContaContabil4CFO(String contaContabil4CFO) {
        this.contaContabil4CFO = contaContabil4CFO;
    }

    public String getContaContabilCFO() {
        return contaContabilCFO;
    }

    public void setContaContabilCFO(String contaContabilCFO) {
        this.contaContabilCFO = contaContabilCFO;
    }

    public String getContaContabilOrcamentariaCFO() {
        return contaContabilOrcamentariaCFO;
    }

    public void setContaContabilOrcamentariaCFO(String contaContabilOrcamentariaCFO) {
        this.contaContabilOrcamentariaCFO = contaContabilOrcamentariaCFO;
    }

    public Integer getSeqItemDocCFO() {
        return seqItemDocCFO;
    }

    public void setSeqItemDocCFO(Integer seqItemDocCFO) {
        this.seqItemDocCFO = seqItemDocCFO;
    }

    public Integer getSeqRateioCFO() {
        return seqRateioCFO;
    }

    public void setSeqRateioCFO(Integer seqRateioCFO) {
        this.seqRateioCFO = seqRateioCFO;
    }

    public Integer getSeqRegistroRateioCFO() {
        return seqRegistroRateioCFO;
    }

    public void setSeqRegistroRateioCFO(Integer seqRegistroRateioCFO) {
        this.seqRegistroRateioCFO = seqRegistroRateioCFO;
    }

    public String getTipoItemCFO() {
        return tipoItemCFO;
    }

    public void setTipoItemCFO(String tipoItemCFO) {
        this.tipoItemCFO = tipoItemCFO;
    }

    public String getTipoRegistroCFO() {
        return tipoRegistroCFO;
    }

    public void setTipoRegistroCFO(String tipoRegistroCFO) {
        this.tipoRegistroCFO = tipoRegistroCFO;
    }
    
    
    
    public String toLayout() {
        StringBuffer linha = IOUtil.linhaVazia(147);
        
        linha.replace(0,2,String.valueOf(this.getTipoRegistroCFO()));
        linha.replace(3,7,StringUtil.formatarCampoNumerico(String.valueOf(this.getSeqRegistroRateioCFO()),5));
        linha.replace(8,8,String.valueOf(this.getTipoItemCFO()));
        linha.replace(9,13,StringUtil.formatarCampoNumerico(String.valueOf(this.getSeqItemDocCFO()),5));
        linha.replace(14,18,StringUtil.formatarCampoNumerico(String.valueOf(this.getSeqRateioCFO()),5));
        linha.replace(19,34,StringUtil.formatarCampoCaracter(this.getContaContabilCFO(),16));
        linha.replace(35,46,StringUtil.formatarCampoCaracter(this.getContaContabil1CFO(),12));
        linha.replace(47,58,StringUtil.formatarCampoCaracter(this.getContaContabil2CFO(),12));
        linha.replace(59,70,StringUtil.formatarCampoCaracter(this.getContaContabil3CFO(),12));
        linha.replace(71,82,StringUtil.formatarCampoCaracter(this.getContaContabil4CFO(),12));
        linha.replace(83,98,StringUtil.formatarCampoCaracter(this.getContaContabilOrcamentariaCFO(),16));
        linha.replace(99,110,StringUtil.formatarCampoCaracter(this.getCentroOrcamentaria1CFO(),12));
        linha.replace(111,122,StringUtil.formatarCampoCaracter(this.getCentroOrcamentaria2CFO(),12));
        linha.replace(123,134,StringUtil.formatarCampoCaracter(this.getCentroOrcamentaria3CFO(),12));
        linha.replace(135,146,StringUtil.formatarCampoCaracter(this.getCentroOrcamentaria4CFO(),12));
        
        return linha.substring(0,146);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

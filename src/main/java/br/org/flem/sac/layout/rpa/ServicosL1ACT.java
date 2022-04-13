package br.org.flem.sac.layout.rpa;

import br.org.flem.sac.arquivo.Layout;
import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.util.IOUtil;
import java.util.List;

/**
 *
 * @author MCCosta
 */
public class ServicosL1ACT implements Layout{
    
    // Linha 06 - (L1ACT) Serviços - Contábil / Financeiro
    private String tipoRegistroCF = "120";  //A 03 1 - 3 Fixo "120"
    private Integer seqRegistroServicoCF;    //N 05 4 - 8 
    private String contaFinanceiraCF;       //A 16 9 - 24 
    private String codEventoContabilCF;     //A 06 25 - 30 
    private String tipoRateioCF;            //A 01 31 - 31 (0 - Percentual, 1 - Valor, 2 ? Proporcional)

    
    public ServicosL1ACT() {
    
    }

    public String getCodEventoContabilCF() {
        return codEventoContabilCF;
    }

    public void setCodEventoContabilCF(String codEventoContabilCF) {
        this.codEventoContabilCF = codEventoContabilCF;
    }

    public String getContaFinanceiraCF() {
        return contaFinanceiraCF;
    }

    public void setContaFinanceiraCF(String contaFinanceiraCF) {
        this.contaFinanceiraCF = contaFinanceiraCF;
    }

    public Integer getSeqRegistroServicoCF() {
        return seqRegistroServicoCF;
    }

    public void setSeqRegistroServicoCF(Integer seqRegistroServicoCF) {
        this.seqRegistroServicoCF = seqRegistroServicoCF;
    }

    public String getTipoRateioCF() {
        return tipoRateioCF;
    }

    public void setTipoRateioCF(String tipoRateioCF) {
        this.tipoRateioCF = tipoRateioCF;
    }

    public String getTipoRegistroCF() {
        return tipoRegistroCF;
    }

    public void setTipoRegistroCF(String tipoRegistroCF) {
        this.tipoRegistroCF = tipoRegistroCF;
    }
    
    public String toLayout() {
        StringBuffer linha = IOUtil.linhaVazia(31);
        
        linha.replace(0,2,String.valueOf(this.getTipoRegistroCF()));
        linha.replace(3,7,StringUtil.formatarCampoNumerico(String.valueOf(this.getSeqRegistroServicoCF()),5));
        linha.replace(8,23,StringUtil.formatarCampoCaracter(this.getContaFinanceiraCF(),16));
        linha.replace(24,29,StringUtil.formatarCampoCaracter(this.getCodEventoContabilCF(),06));
        linha.replace(30,30,this.getTipoRateioCF());

        return linha.substring(0,30);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

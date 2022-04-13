package br.org.flem.sac.layout.rpa;

import br.org.flem.sac.arquivo.Layout;
import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.util.IOUtil;
import java.util.List;

/**
 *
 * @author MCCosta
 */
public class RegistroHeaderHTAX implements Layout{
    // Linha 03 - (HTAX) Header - Tributos
    private String tipoRegistroT = "060";    //A 03 1 - 3 Fixo "060"
    private String indicadorTributosT;       //A 01 4 - 4 Calcular todos os impostos, 1 ? XXXXXXX, 3 ?Informar todos os impostos
    private Double inssPatronalT;            //N 18 5 - 22
    private Double valorPisT;                //N 18 23 - 40
    private Double valorCofinT;              //N 18 41 - 58
    
    public RegistroHeaderHTAX() {
    
    }

    public String getIndicadorTributosT() {
        return indicadorTributosT;
    }

    public void setIndicadorTributosT(String indicadorTributosT) {
        this.indicadorTributosT = indicadorTributosT;
    }

    public Double getInssPatronalT() {
        return inssPatronalT;
    }

    public void setInssPatronalT(Double inssPatronalT) {
        this.inssPatronalT = inssPatronalT;
    }

    public String getTipoRegistroT() {
        return tipoRegistroT;
    }

    public void setTipoRegistroT(String tipoRegistroT) {
        this.tipoRegistroT = tipoRegistroT;
    }

    public Double getValorCofinT() {
        return valorCofinT;
    }

    public void setValorCofinT(Double valorCofinT) {
        this.valorCofinT = valorCofinT;
    }

    public Double getValorPisT() {
        return valorPisT;
    }

    public void setValorPisT(Double valorPisT) {
        this.valorPisT = valorPisT;
    }    
    
    public String toLayout() {
        StringBuffer linha = IOUtil.linhaVazia(58);
        
        linha.replace(0,2,String.valueOf(this.getTipoRegistroT()));
        linha.replace(3,3,String.valueOf(this.getIndicadorTributosT()));
        linha.replace(4,21,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getInssPatronalT()),18));
        linha.replace(22,39,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorPisT()),18));
        linha.replace(40,57,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorCofinT()),18));
        
        return linha.substring(0,57);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

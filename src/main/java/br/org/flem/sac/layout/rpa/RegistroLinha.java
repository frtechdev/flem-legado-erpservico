/*
 * RegistroLinhaDespesa.java
 * 
 * Created on 29/08/2007, 10:26:25
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.layout.rpa;

import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.util.IOUtil;
import java.util.List;

/**
 *
 * @author lbrito
 */
public class RegistroLinha implements Layout{
private String companhia; // Companhia deverá estar parametrizada
private String grupoCP;
private String entidade; // Entidade deverá estar ativa
private String local;
private String tipoDocumentoReferencia;
private String documentoReferencia;
private String tipoRegistro;
private String tipoLinha; // Deverá ser preenchido com "01" para débito e "02" para crédito
private String descricao; // Obrigatória
private String descricao1; // Opcional
private String descricao2; // Opcional
private Double valorLinha;
private String contaContabil;
private String centroContabil1;
private String centroContabil2;
private String centroContabil3;
private String centroContabil4;
private String eventoContabil;
private String codigoTributo;
private String codigoRetencao;
private String categoriaDespesa;

    public String getCategoriaDespesa() {
        return categoriaDespesa;
    }

    public void setCategoriaDespesa(String categoriaDespesa) {
        this.categoriaDespesa = categoriaDespesa;
    }

    public String getCodigoRetencao() {
        return codigoRetencao;
    }

    public void setCodigoRetencao(String codigoRetencao) {
        this.codigoRetencao = codigoRetencao;
    }

    public String getCodigoTributo() {
        return codigoTributo;
    }

    public void setCodigoTributo(String codigoTributo) {
        this.codigoTributo = codigoTributo;
    }

    public String getCompanhia() {
        return companhia;
    }

    public void setCompanhia(String companhia) {
        this.companhia = companhia;
    }

    public String getContaContabil() {
        return contaContabil;
    }

    public void setContaContabil(String contaContabil) {
        this.contaContabil = contaContabil;
    }

    public String getCentroContabil1() {
        return centroContabil1;
    }

    public String getEventoContabil() {
        return eventoContabil;
    }

    public void setEventoContabil(String eventoContabil) {
        this.eventoContabil = eventoContabil;
    }

    public void setCentroContabil1(String contaContabil1) {
        this.centroContabil1 = contaContabil1;
    }

    public String getCentroContabil2() {
        return centroContabil2;
    }

    public void setCentroContabil2(String centroContabil2) {
        this.centroContabil2 = centroContabil2;
    }

    public String getCentroContabil3() {
        return centroContabil3;
    }

    public void setCentroContabil3(String centroContabil3) {
        this.centroContabil3 = centroContabil3;
    }

    public String getCentroContabil4() {
        return centroContabil4;
    }

    public void setCentroContabil4(String centroContabil4) {
        this.centroContabil4 = centroContabil4;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao1() {
        return descricao1;
    }

    public void setDescricao1(String descricao1) {
        this.descricao1 = descricao1;
    }

    public String getDescricao2() {
        return descricao2;
    }

    public void setDescricao2(String descricao2) {
        this.descricao2 = descricao2;
    }

    public String getDocumentoReferencia() {
        return documentoReferencia;
    }

    public void setDocumentoReferencia(String documentoReferencia) {
        this.documentoReferencia = documentoReferencia;
    }

    public String getEntidade() {
        return entidade;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    public String getGrupoCP() {
        return grupoCP;
    }

    public void setGrupoCP(String grupoCP) {
        this.grupoCP = grupoCP;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTipoDocumentoReferencia() {
        return tipoDocumentoReferencia;
    }

    public void setTipoDocumentoReferencia(String tipoDocumentoReferencia) {
        this.tipoDocumentoReferencia = tipoDocumentoReferencia;
    }

    public String getTipoLinha() {
        return tipoLinha;
    }

    public void setTipoLinha(String tipoLinha) {
        this.tipoLinha = tipoLinha;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public Double getValorLinha() {
        return valorLinha;
    }

    public void setValorLinha(Double valorLinha) {
        this.valorLinha = valorLinha;
    }

    public RegistroLinha() {
    }

    
    public String toLayout() {
        StringBuffer sb = IOUtil.linhaVazia(916);
        
        sb.replace(0,3,String.valueOf(this.getCompanhia()));
        sb.replace(4,7,String.valueOf(this.getGrupoCP()));
        sb.replace(8,23,StringUtil.formatarCampoCaracter(this.getEntidade(),16));
        sb.replace(24,27,StringUtil.formatarCampoCaracter(this.getLocal(),4));
        sb.replace(28,33,StringUtil.formatarCampoCaracter(this.getTipoDocumentoReferencia(),6));
        sb.replace(34,49,StringUtil.formatarCampoCaracter(this.getDocumentoReferencia(),16));
        sb.replace(50,51,String.valueOf(this.getTipoRegistro()));
        sb.replace(52,53,StringUtil.formatarCampoCaracter(this.getTipoLinha(),2));
        sb.replace(54,303,StringUtil.formatarCampoCaracter(this.getDescricao().replace("\r\n", " "),250));
        
        sb.replace(304,553,StringUtil.formatarCampoCaracter(this.getDescricao1().replace("\r\n", " "),250));
        sb.replace(554,803,StringUtil.formatarCampoCaracter(this.getDescricao2().replace("\r\n", " "),250));
        sb.replace(804,821,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorLinha()),18));
        if ( this.getEventoContabil() == null || this.getEventoContabil().equalsIgnoreCase("") ) 
            sb.replace(822,827,StringUtil.formatarCampoCaracter("",6));
        else
            sb.replace(822,827,StringUtil.formatarCampoCaracter(this.getEventoContabil(),6));
        if ( this.getContaContabil() == null )
            sb.replace(828,843,StringUtil.formatarCampoCaracter("",16));
        else
            sb.replace(828,843,StringUtil.formatarCampoCaracter(this.getContaContabil(),16));
        /*
         * O intervalo de 844 a 855 � referente ao CentroContabil1
         * O campo CentroContabil1 � derivado do primeiro caracter do CentroContabil2
         */
        if ( this.getCentroContabil2() == null || this.getCentroContabil2().equalsIgnoreCase("") ) 
            sb.replace(844,855,StringUtil.formatarCampoCaracter("",12));
        else
            sb.replace(844,855,StringUtil.formatarCampoCaracter(this.getCentroContabil2().substring(0,1),12));
        if ( this.getCentroContabil2() == null )
            sb.replace(856,867,StringUtil.formatarCampoCaracter("",12));
        else
            sb.replace(856,867,StringUtil.formatarCampoCaracter(this.getCentroContabil2(),12));
        if ( this.getCentroContabil3() == null )
            sb.replace(868,879,StringUtil.formatarCampoCaracter("",12));
        else
            sb.replace(868,879,StringUtil.formatarCampoCaracter(this.getCentroContabil3(),12));
        if ( this.getCentroContabil4() == null )
            sb.replace(880,891,StringUtil.formatarCampoCaracter("",12));
        else
            sb.replace(880,891,StringUtil.formatarCampoCaracter(this.getCentroContabil4(),12));
        sb.replace(892,895,StringUtil.formatarCampoCaracter(this.getCodigoTributo(),4));
        sb.replace(896,899,StringUtil.formatarCampoCaracter(this.getCodigoRetencao(),4));
        sb.replace(900,915,StringUtil.formatarCampoCaracter(this.getCategoriaDespesa(),4));
        return sb.substring(0,916);

    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }



}

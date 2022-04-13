/*
 * EbalSaebDetalhe.java
 *
 * Created on 22 de Agosto de 2007, 09:27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.sac.layout.ebal.credicesta;

import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.util.IOUtil;
import java.util.List;

/**
 *
 * @author fcsilva
 */
public class SaebDetalhe implements Layout {

    private Integer indicadorRegistro;
    private String orgao;
    private Integer constante;
    private Integer matricula;    
    private Integer branco;
    private String constante2;
    private Double valorDescontado;
    private Integer beneficiario;
    private String constante3;
    private String constante4;
    private Integer branco2;
    private String flagEnvio;    



    public Integer getBranco() {
        return branco;
    }

    public void setBranco(Integer branco) {
        this.branco = branco;
    }

    public Integer getBranco2() {
        return branco2;
    }

    public void setBranco2(Integer branco2) {
        this.branco2 = branco2;
    }

    public Integer getConstante() {
        return constante;
    }

    public void setConstante(Integer constante) {
        this.constante = constante;
    }

    public String getConstante2() {
        return constante2;
    }

    public void setConstante2(String constante2) {
        this.constante2 = constante2;
    }

    public Integer getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Integer beneficiario) {
        this.beneficiario = beneficiario;
    }    

    public String getConstante3() {
        return constante3;
    }

    public void setConstante3(String constante3) {
        this.constante3 = constante3;
    }

    public String getConstante4() {
        return constante4;
    }

    public void setConstante4(String constante4) {
        this.constante4 = constante4;
    }
    
    public String getFlagEnvio() {
        return flagEnvio;
    }

    public void setFlagEnvio(String flagEnvio) {
        this.flagEnvio = flagEnvio;
    }


    public Integer getIndicadorRegistro() {
        return indicadorRegistro;
    }

    public void setIndicadorRegistro(Integer indicadorRegistro) {
        this.indicadorRegistro = indicadorRegistro;
    }

    public String getOrgao() {
        return orgao;
    }

    public void setOrgao(String orgao) {
        this.orgao = orgao;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Double getValorDescontado() {
        return valorDescontado;
    }

    public void setValorDescontado(Double valorDescontado) {
        this.valorDescontado = valorDescontado;
    }

    public String toLayout() {
        StringBuffer sb = IOUtil.linhaVazia(125);
        sb.replace(0,0, String.valueOf(getIndicadorRegistro()));
        sb.replace(1,2, String.valueOf(getOrgao()));
        sb.replace(3,6, StringUtil.formatarZeros(getConstante()));
        sb.replace(7,14, String.valueOf(StringUtil.formatarCampoNumerico(getMatricula(), 9)));        
        sb.replace(16,45, StringUtil.formatarBrancos(getBranco()));
        sb.replace(46,49, StringUtil.formatarCampoNumerico(getConstante2(), 4));
        sb.replace(50,60, StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorDescontado()),10));
        sb.replace(61,64, StringUtil.formatarCampoNumerico(this.getBeneficiario(),4));
        sb.replace(65,84, StringUtil.formatarCampoNumerico(getConstante3(), 20));
        sb.replace(85,86, StringUtil.formatarCampoNumerico(getConstante4(), 2));
        sb.replace(87,123,StringUtil.formatarBrancos(getBranco2()));
        sb.replace(124,125, String.valueOf(getFlagEnvio()));
        return sb.substring(0, 125);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

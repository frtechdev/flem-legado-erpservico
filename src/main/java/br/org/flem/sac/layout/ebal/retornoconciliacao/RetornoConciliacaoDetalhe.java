/*
 * RetornoConciliacaoDetalhe.java
 *
 * Created on 21/08/2007, 15:21:32
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.layout.ebal.retornoconciliacao;

import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.util.IOUtil;
import java.util.List;

/**
 *
 * @author fcsilva
 */
public class RetornoConciliacaoDetalhe implements Layout {


    private int indicadorRegistro = 1;
    private int gestor;
    private int orgao;
    private int matricula;
    private int beneficiario;
    private double valorDescontado = 0.0;
    private int codigoErro;
    private int codigoSituacaoFuncional;
    private int brancos;
    private int contador = 0;
    
    public int getIndicadorRegistro() {
        return indicadorRegistro;
    }

    public void setIndicadorRegistro(int indicadorRegistro) {
        this.indicadorRegistro = indicadorRegistro;
    }

    public int getGestor() {
        return gestor;
    }

    public void setGestor(int gestor) {
        this.gestor = gestor;
    }

    public int getOrgao() {
        return orgao;
    }

    public void setOrgao(int orgao) {
        this.orgao = orgao;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(int beneficiario) {
        this.beneficiario = beneficiario;
    }

    public double getValorDescontado() {
        return valorDescontado;
    }

    public void setValorDescontado(double valorDescontado) {
        this.valorDescontado = valorDescontado;
    }

    public int getCodigoErro() {
        return codigoErro;
    }

    public void setCodigoErro(int codigoErro) {
        this.codigoErro = codigoErro;
    }

    public int getCodigoSituacaoFuncional() {
        return codigoSituacaoFuncional;
    }

    public void setCodigoSituacaoFuncional(int codigoSituacaoFuncional) {
        this.codigoSituacaoFuncional = codigoSituacaoFuncional;
    }

    public int getBrancos() {
        return brancos;
    }

    public void setBrancos(int brancos) {
        this.brancos = brancos;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
    
    public String toLayout(){                    
            StringBuffer sb = IOUtil.linhaVazia(67);
            sb.replace(0,0,String.valueOf(this.getIndicadorRegistro()));
            sb.replace(1,4,StringUtil.formatarCampoNumerico(this.getGestor(),4));
            sb.replace(5,7,StringUtil.formatarCampoNumerico(this.getOrgao(),3));
            sb.replace(8,16,String.valueOf(StringUtil.formatarCampoNumerico(this.getMatricula(),9)));
            sb.replace(17,20, StringUtil.formatarCampoNumerico(this.getBeneficiario(),4));
            sb.replace(21,30,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorDescontado()),10));
            sb.replace(31,33,StringUtil.formatarCampoNumerico(this.getCodigoErro(),3));
            sb.replace(34,36,StringUtil.formatarCampoNumerico(this.getCodigoSituacaoFuncional(),3));
            sb.replace(37,60,StringUtil.formatarBrancos(this.getBrancos()));
            sb.replace(61,66,StringUtil.formatarCampoNumerico(this.getContador(),6));
            
            return sb.substring(0,67);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
  
    
}

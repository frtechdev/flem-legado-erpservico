/*
 * Beneficiario.java
 *
 * Created on 14/08/2007, 10:28:03
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.dto.planserv;

import br.org.flem.fw.service.IFuncionario;
import java.io.Serializable;
import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;

/**
 *
 * @author mjpereira
 */
@DataTransferObject
public class Beneficiario implements Serializable, Comparable {

    @RemoteProperty
    private String cdPlano;
    @RemoteProperty
    private String nomePlano;
    @RemoteProperty
    private String matricula;
    @RemoteProperty
    private String cdParentesco="00";
    @RemoteProperty
    private String nomeParentesco;
    @RemoteProperty
    private String cdOperacao;
    @RemoteProperty
    private String nomeOperacao;
    @RemoteProperty
    private String dataOperacao;
    @RemoteProperty
    private String dataCasamento;
    @RemoteProperty
    private String dataBeneficio;
    @RemoteProperty
    private String nome;
    @RemoteProperty
    private String beneficiario;
    @RemoteProperty
    private String sexo;
    @RemoteProperty
    private String dataNascimento;
    @RemoteProperty
    private boolean titular = true;
    @RemoteProperty
    private int rdp;

    public int getRdp() {
        return rdp;
    }

    public void setRdp(int rdp) {
        this.rdp = rdp;
    }
    
    private IFuncionario funcionario;

    public IFuncionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(IFuncionario funcionario) {
        this.funcionario = funcionario;
    }

    public boolean isTitular() {
        return titular;
    }

    public void setTitular(boolean titular) {
        this.titular = titular;
    }
    
    
    
    public Beneficiario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }



    public String getNomePlano() {
        return nomePlano;
    }

    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
    }

    public String getDataBeneficio() {
        return dataBeneficio;
    }

    public void setDataBeneficio(String dataBeneficio) {
        this.dataBeneficio = dataBeneficio;
    }

    public String getDataCasamento() {
        return dataCasamento;
    }

    public void setDataCasamento(String dataCasamento) {
        this.dataCasamento = dataCasamento;
    }

    public String getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(String dataOperacao) {
        this.dataOperacao = dataOperacao;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCdOperacao() {
        return cdOperacao;
    }

    public void setCdOperacao(String operacao) {
        this.cdOperacao = operacao;
    }
    
    public String getNomeOperacao() {
        return nomeOperacao;
    }

    public void setNomeOperacao(String operacao) {
        this.nomeOperacao = operacao;
    }

    public String getCdParentesco() {
        return cdParentesco;
    }

    public void setCdParentesco(String cdParentesco) {
        this.cdParentesco = cdParentesco;
    }

    public String getNomeParentesco() {
        return nomeParentesco;
    }

    public void setNomeParentesco(String nomeParentesco) {
        this.nomeParentesco = nomeParentesco;
    }



    public String getCdPlano() {
        return cdPlano;
    }

    public void setCdPlano(String plano) {
        this.cdPlano = plano;
    }

    @Override
    public boolean equals(Object obj) {
        Beneficiario b = (Beneficiario) obj;
        if (this.nome.equals(b.getNome()) && this.matricula.equals(b.getMatricula())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.matricula != null ? this.matricula.hashCode() : 0;
        hash = 59 * hash + this.nome != null ? this.nome.hashCode() : 0;
        return hash;
    }


    @Override
    public int compareTo(Object o) {
        Beneficiario b = (Beneficiario) o;
        return this.getNome().compareToIgnoreCase(b.getNome());
    }
    
    
    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
   
}

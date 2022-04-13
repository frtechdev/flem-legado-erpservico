package br.org.flem.sac.dto.planserv.validacaoFolha;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emsilva
 */
public class GrupoFamiliar {
    private Double baseCalculo;
    private String numAssociadoResponsavel;
    private String cpf;
    private char aposentado = 'N';
    private List<Associado> associados = new ArrayList<Associado>();

    public Double getBaseCalculo() {
        return baseCalculo;
    }

    public void setBaseCalculo(Double baseCalculo) {
        this.baseCalculo = baseCalculo;
    }

    public String getNumAssociadoResponsavel() {
        return numAssociadoResponsavel;
    }

    public void setNumAssociadoResponsavel(String numAssociadoResponsavel) {
        this.numAssociadoResponsavel = numAssociadoResponsavel;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public char getAposentado() {
        return aposentado;
    }

    public void setAposentado(char aposentado) {
        this.aposentado = aposentado;
    }

    public List<Associado> getAssociados() {
        return associados;
    }

    public void setAssociados(List<Associado> associados) {
        this.associados = associados;
    }

}

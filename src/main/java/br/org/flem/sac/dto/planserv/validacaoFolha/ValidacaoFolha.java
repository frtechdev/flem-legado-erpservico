package br.org.flem.sac.dto.planserv.validacaoFolha;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emsilva
 */
public class ValidacaoFolha {
    private int codOrgao = 97;
    private Double valorTotalOrgao;
    private String mesReferencia;
    private List<GrupoFamiliar> gruposFamiliares = new ArrayList<GrupoFamiliar>();

    public int getCodOrgao() {
        return codOrgao;
    }

    public void setCodOrgao(int codOrgao) {
        this.codOrgao = codOrgao;
    }

    public Double getValorTotalOrgao() {
        return valorTotalOrgao;
    }

    public void setValorTotalOrgao(Double valorTotalOrgao) {
        this.valorTotalOrgao = valorTotalOrgao;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public List<GrupoFamiliar> getGruposFamiliares() {
        return gruposFamiliares;
    }

    public void setGruposFamiliares(List<GrupoFamiliar> gruposFamiliares) {
        this.gruposFamiliares = gruposFamiliares;
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.negocio;

import br.org.flem.fwe.hibernate.dto.base.BaseDTOAb;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mccosta
 */
@Entity
@Table(name="centroresponsabilidade")
public class CentroResponsabilidadeERP extends BaseDTOAb{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="cod_cresp")
    private String cod_cresp;

    private String nom_cresp;
    private String tip_cresp;
    private String tip_ativo;
    private String nom_cresp_alter;

    @Override
    public Serializable getPk() {
        return this.cod_cresp;
    }

    public String getCod_cresp() {
        return cod_cresp;
    }

    public void setCod_cresp(String cod_cresp) {
        this.cod_cresp = cod_cresp;
    }

    public String getNom_cresp() {
        return nom_cresp;
    }

    public void setNom_cresp(String nom_cresp) {
        this.nom_cresp = nom_cresp;
    }

    public String getNom_cresp_alter() {
        return nom_cresp_alter;
    }

    public void setNom_cresp_alter(String nom_cresp_alter) {
        this.nom_cresp_alter = nom_cresp_alter;
    }

    public String getTip_ativo() {
        return tip_ativo;
    }

    public void setTip_ativo(String tip_ativo) {
        this.tip_ativo = tip_ativo;
    }

    public String getTip_cresp() {
        return tip_cresp;
    }

    public void setTip_cresp(String tip_cresp) {
        this.tip_cresp = tip_cresp;
    }
}

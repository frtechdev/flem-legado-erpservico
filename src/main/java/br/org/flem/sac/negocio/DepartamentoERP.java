package br.org.flem.sac.negocio;

import br.org.flem.fwe.hibernate.dto.base.BaseDTOAb;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author mccosta
 */
@Entity
@Table(name="departamento")
public class DepartamentoERP extends BaseDTOAb {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_departamento")
    private Integer id;

    private String cod_ccusto;
    private String cod_lotacao_hr;
    private Integer cod_lotacao_dominio;
    private String nom_dept;

    @Lob
    @Column(columnDefinition="NTEXT")
    private String des_competencia;
    private String cod_cresp;

    @Override
    public Serializable getPk() {
        return this.id;
    }

    public String getCod_cresp() {
        return cod_cresp;
    }

    public void setCod_cresp(String cod_cresp) {
        this.cod_cresp = cod_cresp;
    }

    public String getCod_ccusto() {
        return cod_ccusto;
    }

    public void setCod_ccusto(String cod_ccusto) {
        this.cod_ccusto = cod_ccusto;
    }

    public Integer getCod_lotacao_dominio() {
        return cod_lotacao_dominio;
    }

    public void setCod_lotacao_dominio(Integer cod_lotacao_dominio) {
        this.cod_lotacao_dominio = cod_lotacao_dominio;
    }

    public String getCod_lotacao_hr() {
        return cod_lotacao_hr;
    }

    public void setCod_lotacao_hr(String cod_lotacao_hr) {
        this.cod_lotacao_hr = cod_lotacao_hr;
    }

    public String getDes_competencia() {
        return des_competencia;
    }

    public void setDes_competencia(String des_competencia) {
        this.des_competencia = des_competencia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom_dept() {
        return nom_dept;
    }

    public void setNom_dept(String nom_dept) {
        this.nom_dept = nom_dept;
    }
}
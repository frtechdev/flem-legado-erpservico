/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.flem.sac.negocio;

import br.org.flem.fwe.hibernate.dto.base.BaseDTOAb;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author ILFernandes
 */
@Entity
public class ConfiguracaoPlanserv extends BaseDTOAb{
    @Id
    private Integer id;
    private Double valorAgregadoMaior;
    private Double valorAgregadoMenor;
    private Double valorEspecial;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValorAgregadoMaior() {
        return valorAgregadoMaior;
    }

    public void setValorAgregadoMaior(Double valorAgregadoMaior) {
        this.valorAgregadoMaior = valorAgregadoMaior;
    }

    public Double getValorAgregadoMenor() {
        return valorAgregadoMenor;
    }

    public void setValorAgregadoMenor(Double valorAgregadoMenor) {
        this.valorAgregadoMenor = valorAgregadoMenor;
    }

    public Double getValorEspecial() {
        return valorEspecial;
    }

    public void setValorEspecial(Double valorEspecial) {
        this.valorEspecial = valorEspecial;
    }
    
    
    
    @Override
    public Serializable getPk() {
        return this.getPk();
    }
    
}

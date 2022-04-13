/*
 * Propriedades.java
 * 
 * Created on 17/08/2007, 08:07:49
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.negocio;

import br.org.flem.fwe.hibernate.dto.base.BaseDTOAb;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mjpereira
 */
@Entity
@Table(name="propriedade")
public class Propriedade extends BaseDTOAb implements java.io.Serializable{

    @Id
    @Column(name = "chave")
    private String chave;

    @Column(name="valor")
    private String valor;

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public Propriedade() {
    }

    public Serializable getPk() {
        return this.chave;
    }

    

}

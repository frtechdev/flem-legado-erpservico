package br.org.flem.sac.negocio;

import br.org.flem.fwe.hibernate.dto.base.BaseDTOAb;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mccosta
 */
@Entity
@Table(name="propriedadeMensal")
public class PropriedadeMensal extends BaseDTOAb implements java.io.Serializable{

    @Id
    @Column(name = "chave")
    private String chave;

    @Column(name="valor")
    private String valor;



    public PropriedadeMensal() {
    }

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

    public Serializable getPk() {
        return this.chave;
    }
}

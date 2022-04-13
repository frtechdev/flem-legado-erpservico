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
 * @author mjpereira
 */
@Entity
@Table(name="autonomos")
public class Autonomo extends BaseDTOAb {

    @Id
    @Column(name = "id_autonomo")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    //codigo gem
    private String codigo;

    private String nome;

    //REND. BR AUT
    private Double base;

    //ISS RET AUTO   5,0000
    private Double iss;

    //INSS CT INDI  11,0000
    private Double insd;

    //INSS EMP TOT  20,0000
    private Double insn;

    //IRPF
    private Double ir;

    //MES
    private Integer mes;

    //ANO
    private Integer ano;

    //codigo cpf
    private String cpf;

    //codigo pis
    private String pis;


    @Override
    public Serializable getPk() {
       return this.getId();
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the base
     */
    public Double getBase() {
        return base;
    }

    /**
     * @param base the base to set
     */
    public void setBase(Double base) {
        this.base = base;
    }

    /**
     * @return the iss
     */
    public Double getIss() {
        return iss;
    }

    /**
     * @param iss the iss to set
     */
    public void setIss(Double iss) {
        this.iss = iss;
    }

    /**
     * @return the insd
     */
    public Double getInsd() {
        return insd;
    }

    /**
     * @param insd the insd to set
     */
    public void setInsd(Double insd) {
        this.insd = insd;
    }

    /**
     * @return the insn
     */
    public Double getInsn() {
        return insn;
    }

    /**
     * @param insn the insn to set
     */
    public void setInsn(Double insn) {
        this.insn = insn;
    }

    /**
     * @return the ir
     */
    public Double getIr() {
        return ir;
    }

    /**
     * @param ir the ir to set
     */
    public void setIr(Double ir) {
        this.ir = ir;
    }

    /**
     * @return the mes
     */
    public Integer getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(Integer mes) {
        this.mes = mes;
    }

    /**
     * @return the ano
     */
    public Integer getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(Integer ano) {
        this.ano = ano;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the pis
     */
    public String getPis() {
        return pis;
    }

    /**
     * @param pis the pis to set
     */
    public void setPis(String pis) {
        this.pis = pis;
    }

}

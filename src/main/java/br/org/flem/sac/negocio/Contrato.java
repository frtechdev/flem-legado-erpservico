package br.org.flem.sac.negocio;

import br.org.flem.fwe.hibernate.dto.base.BaseDTOAb;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mccosta
 */
@Entity
public class Contrato extends BaseDTOAb {
    
    @Id
    @Column(name = "id_contrato")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Temporal(TemporalType.DATE)
    private Date inicioVigencia;
    
    @Temporal(TemporalType.DATE)
    private Date fimVigencia;
    
    @Temporal(TemporalType.DATE)
    private Date limiteAviso;
    
    private Boolean aviso = false;
    
    @Column(unique=true)
    private String numero;
    
    private String idFornecedor;
    @Column(name="documentoFornecedor")
    private String codigoAntigo;
    
    private String nomeFornecedor;
    
    private Double valor;
    
    @Column(length=300)
    private String objeto;
    
    private String classificacao;

    private String observacao;
    
    private String numeroContrato;
    private String numeroSD;
    private String centroCusto;
    
    @OneToMany(mappedBy="contrato")
    private List<Parcela> parcelas = new ArrayList<Parcela>();
    @OneToMany(mappedBy="contrato")
    private List<Aditivo> aditivos = new ArrayList<Aditivo>();
    
    private String situacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Serializable getPk() {
        return this.id;
    }

    public Date getFimVigencia() {
        return fimVigencia;
    }

    public void setFimVigencia(Date fimVigencia) {
        this.fimVigencia = fimVigencia;
    }

    public Date getLimiteAviso() {
        return limiteAviso;
    }

    public void setLimiteAviso(Date limiteAviso) {
        this.limiteAviso = limiteAviso;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(String idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Date getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(Date inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<Aditivo> getAditivos() {
        return aditivos;
    }

    public void setAditivos(List<Aditivo> aditivos) {
        this.aditivos = aditivos;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getCodigoAntigo() {
        return codigoAntigo;
    }

    public void setCodigoAntigo(String documentoFornecedor) {
        this.codigoAntigo = documentoFornecedor;
    }

    public Boolean getAviso() {
        return aviso;
    }

    public void setAviso(Boolean aviso) {
        this.aviso = aviso;
    }
    
    public String getDescricaoCompleta(){
        return "\nNúmero processo: " + this.numero + "\nFornecedor: " + this.idFornecedor + " - " + this.nomeFornecedor;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getNumeroSD() {
        return numeroSD;
    }

    public void setNumeroSD(String numeroSD) {
        this.numeroSD = numeroSD;
    }

    public String getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(String centroCusto) {
        this.centroCusto = centroCusto;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    
}

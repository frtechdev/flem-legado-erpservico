package br.org.flem.sac.dto.contratos;

import java.util.Date;

/**
 *
 * @author MCCosta
 */
public class ContratoDTO {
    
    private Integer id;
    private Date inicioVigencia;
    private Date fimVigencia;
    private String numero;
    private String idFornecedor;
    private String codigoAntigo;
    private String nomeFornecedor;
    private Double valor;
    private Double saldoAPagar;
    private String objeto;
    private String classificacao;
    private String observacao;
    private String numeroContrato;
    private String numeroSD;
    private String centroCusto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(Date inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public Date getFimVigencia() {
        return fimVigencia;
    }

    public void setFimVigencia(Date fimVigencia) {
        this.fimVigencia = fimVigencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(String idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getCodigoAntigo() {
        return codigoAntigo;
    }

    public void setCodigoAntigo(String codigoAntigo) {
        this.codigoAntigo = codigoAntigo;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getSaldoAPagar() {
        return saldoAPagar;
    }

    public void setSaldoAPagar(Double saldoAPagar) {
        this.saldoAPagar = saldoAPagar;
    }
    
    public void subtrairDeSaldoAPagar(Double saldoAPagar) {
        this.saldoAPagar =  saldoAPagar - this.saldoAPagar;
    }
    
    public void adicionarDeSaldoAPagar(Double saldoAPagar) {
        this.saldoAPagar =  saldoAPagar + this.saldoAPagar;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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
    
}

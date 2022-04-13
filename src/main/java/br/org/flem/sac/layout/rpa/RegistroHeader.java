/*
 * RegistroHeader.java
 * 
 * Created on 28/08/2007, 10:49:40
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.layout.rpa;

import br.org.flem.sac.arquivo.Layout;
import java.text.SimpleDateFormat;
import java.util.Date;
import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.util.IOUtil;
import java.util.List;

/**
 *
 * @author lbrito
 */
public class RegistroHeader implements Layout{

private String companhia; // Companhia deverá estar parametrizada
private String grupoCP;
private String entidade; // Entidade deverá estar ativa
private String local;
private String tipoDocumentoReferencia;
private String documentoReferencia;
private String tipoRegistro;
private Date dataEmissao; // Formato AAAAMMDD
private Date dataRecebimento; // Formato AAAAMMDD
private Date dataContabil; // Formato AAAAMMDD
private String condicaoPagto; // Se não informada será igual a definida para a Conta Fornecedor
private String moeda; // Moeda do documento
private String descricao; // Obrigatória
private String descricao1; // Opcional
private String descricao2; // Opcional
private String centroResponsabilidade;
private String contaBancariaForn;
private String centroPagamento;
private String metodoPagto;
private String indicadorPagamento; // Indicador do pagamento em separado
private Double valorTotal; // Devera estar na moeda do documento
private String categoriaDespesa;
private String campoUsuario1;
private String campoUsuario2;
private String campoUsuario3;
private String situacaoParcela;
private String motivoBloqueio;  // Obrigat�rio para caso de Situacao Bloqueado (parcela)
private String nomeFornEventual; // Nome do Fornecedor Eventual. Obrigtório se for fornecedor eventual
private String enderecoFornEventual; // Endereço do Fornecedor Eventual. Opcional
private String identificadorFornEventual; // Número do CNPJ ou CPF do Fornecedor Eventual. Opcional
private String bancoFornEventual; // Código do Banco do Fornecedor Eventual. Opcional
private String agenciaFornEventual; // Código da Agência do Fornecedor Eventual. Opcional
private String contaFornEventual; // Número da Conta Bancária do Fornecedor Eventual. Opcional
private String situacaoDocumento;
private String tipoDocumentoReferencia2;
private String documentoReferencia2;

    public String getCampoUsuario1() {
        return campoUsuario1;
    }

    public void setCampoUsuario1(String campoUsuario1) {
        this.campoUsuario1 = campoUsuario1;
    }

    public String getCampoUsuario2() {
        return campoUsuario2;
    }

    public void setCampoUsuario2(String campoUsuario2) {
        this.campoUsuario2 = campoUsuario2;
    }

    public String getCampoUsuario3() {
        return campoUsuario3;
    }

    public void setCampoUsuario3(String campoUsuario3) {
        this.campoUsuario3 = campoUsuario3;
    }

    public String getIndicadorPagamento() {
        return indicadorPagamento;
    }

    public void setIndicadorPagamento(String indicadorPagamento) {
        this.indicadorPagamento = indicadorPagamento;
    }

    public String getMotivoBloqueio() {
        return motivoBloqueio;
    }

    public void setMotivoBloqueio(String motivoBloqueio) {
        this.motivoBloqueio = motivoBloqueio;
    }

    public String getSituacaoParcela() {
        return situacaoParcela;
    }

    public void setSituacaoParcela(String situacaoParcela) {
        this.situacaoParcela = situacaoParcela;
    }

    public String getAgenciaFornEventual() {
        return agenciaFornEventual;
    }

    public void setAgenciaFornEventual(String agenciaFornEventual) {
        this.agenciaFornEventual = agenciaFornEventual;
    }

    public String getBancoFornEventual() {
        return bancoFornEventual;
    }

    public void setBancoFornEventual(String bancoFornEventual) {
        this.bancoFornEventual = bancoFornEventual;
    }

    public String getCategoriaDespesa() {
        return categoriaDespesa;
    }

    public void setCategoriaDespesa(String categoriaDespesa) {
        this.categoriaDespesa = categoriaDespesa;
    }

    public String getCentroPagamento() {
        return centroPagamento;
    }

    public void setCentroPagamento(String centroPagamento) {
        this.centroPagamento = centroPagamento;
    }

    public String getCentroResponsabilidade() {
        return centroResponsabilidade;
    }

    public void setCentroResponsabilidade(String centroResponsabilidade) {
        this.centroResponsabilidade = centroResponsabilidade;
    }

    public String getCompanhia() {
        return companhia;
    }

    public void setCompanhia(String companhia) {
        this.companhia = companhia;
    }

    public String getCondicaoPagto() {
        return condicaoPagto;
    }

    public void setCondicaoPagto(String condicaoPagto) {
        this.condicaoPagto = condicaoPagto;
    }

    public String getContaBancariaForn() {
        return contaBancariaForn;
    }

    public void setContaBancariaForn(String contaBancariaForn) {
        this.contaBancariaForn = contaBancariaForn;
    }

    public String getContaFornEventual() {
        return contaFornEventual;
    }

    public void setContaFornEventual(String contaFornEventual) {
        this.contaFornEventual = contaFornEventual;
    }

    public Date getDataContabil() {
        return dataContabil;
    }

    public void setDataContabil(Date dataContabil) {
        this.dataContabil = dataContabil;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao1() {
        return descricao1;
    }

    public void setDescricao1(String descricao1) {
        this.descricao1 = descricao1;
    }

    public String getDescricao2() {
        return descricao2;
    }

    public void setDescricao2(String descricao2) {
        this.descricao2 = descricao2;
    }

    public String getDocumentoReferencia() {
        return documentoReferencia;
    }

    public void setDocumentoReferencia(String documentoReferencia) {
        this.documentoReferencia = documentoReferencia;
    }

    public String getDocumentoReferencia2() {
        return documentoReferencia2;
    }

    public void setDocumentoReferencia2(String documentoReferencia2) {
        this.documentoReferencia2 = documentoReferencia2;
    }

    public String getEnderecoFornEventual() {
        return enderecoFornEventual;
    }

    public void setEnderecoFornEventual(String enderecoFornEventual) {
        this.enderecoFornEventual = enderecoFornEventual;
    }

    public String getEntidade() {
        return entidade;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    public String getGrupoCP() {
        return grupoCP;
    }

    public void setGrupoCP(String grupoCP) {
        this.grupoCP = grupoCP;
    }

    public String getIdentificadorFornEventual() {
        return identificadorFornEventual;
    }

    public void setIdentificadorFornEventual(String identificadorFornEventual) {
        this.identificadorFornEventual = identificadorFornEventual;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getMetodoPagto() {
        return metodoPagto;
    }

    public void setMetodoPagto(String metodoPagto) {
        this.metodoPagto = metodoPagto;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public String getNomeFornEventual() {
        return nomeFornEventual;
    }

    public void setNomeFornEventual(String nomeFornEventual) {
        this.nomeFornEventual = nomeFornEventual;
    }

    public String getSituacaoDocumento() {
        return situacaoDocumento;
    }

    public void setSituacaoDocumento(String situacaoDocumento) {
        this.situacaoDocumento = situacaoDocumento;
    }

    public String getTipoDocumentoReferencia() {
        return tipoDocumentoReferencia;
    }

    public void setTipoDocumentoReferencia(String tipoDocumentoReferencia) {
        this.tipoDocumentoReferencia = tipoDocumentoReferencia;
    }

    public String getTipoDocumentoReferencia2() {
        return tipoDocumentoReferencia2;
    }

    public void setTipoDocumentoReferencia2(String tipoDocumentoReferencia2) {
        this.tipoDocumentoReferencia2 = tipoDocumentoReferencia2;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public RegistroHeader() {
    }
    
    public String toLayout() {
        SimpleDateFormat formatoAnoMesDia = new SimpleDateFormat("yyyyMMdd");
        StringBuffer sb = IOUtil.linhaVazia(1162);
        
        sb.replace(0,3,String.valueOf(this.getCompanhia()));
        sb.replace(4,7,String.valueOf(this.getGrupoCP()));
        sb.replace(8,23,StringUtil.formatarCampoCaracter(this.getEntidade(),16));
        sb.replace(24,27,String.valueOf(this.getLocal()));
        sb.replace(28,33,StringUtil.formatarCampoCaracter(this.getTipoDocumentoReferencia(),6));
        sb.replace(34,49,StringUtil.formatarCampoCaracter(this.getDocumentoReferencia(),16));
        sb.replace(50,51,String.valueOf(this.getTipoRegistro()));
        sb.replace(52,59,String.valueOf(formatoAnoMesDia.format(this.getDataEmissao())));
        sb.replace(60,67,String.valueOf(formatoAnoMesDia.format(this.getDataRecebimento())));
        sb.replace(68,75,String.valueOf(formatoAnoMesDia.format(this.getDataContabil())));
        
        //sb.replace(76,83,String.valueOf(formatoAnoMesDia.format(this.getDataVencimento())));
        sb.replace(76,78,String.valueOf(this.getCondicaoPagto()));
        sb.replace(79,83,StringUtil.formatarCampoCaracter(this.getMoeda(),5));
        sb.replace(84,333,StringUtil.formatarCampoCaracter(this.getDescricao().replace("\r\n", " "),250));
        sb.replace(334,583,StringUtil.formatarCampoCaracter(this.getDescricao1().replace("\r\n", " "),250));
        sb.replace(584,833,StringUtil.formatarCampoCaracter(this.getDescricao2().replace("\r\n", " "),250));
        sb.replace(834,845,StringUtil.formatarCampoCaracter(this.getCentroResponsabilidade(),12));
        sb.replace(846,848,StringUtil.formatarCampoCaracter(this.getContaBancariaForn(),3));
        sb.replace(849,851,StringUtil.formatarCampoCaracter(this.getCentroPagamento(),3));
        sb.replace(852,854,StringUtil.formatarCampoCaracter(this.getMetodoPagto(),3));
        sb.replace(855,855,String.valueOf(this.getIndicadorPagamento()));
        sb.replace(856,873,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorTotal()),18));
        sb.replace(874,889,StringUtil.formatarCampoCaracter(this.getCategoriaDespesa(),16));
        sb.replace(890,909,StringUtil.formatarCampoCaracter(this.getCampoUsuario1(),20));
        sb.replace(910,929,StringUtil.formatarCampoCaracter(this.getCampoUsuario2(),20));
        sb.replace(930,949,StringUtil.formatarCampoCaracter(this.getCampoUsuario3(),20));
        sb.replace(950,950,String.valueOf(this.getSituacaoParcela()));
        sb.replace(951,953,StringUtil.formatarCampoCaracter(this.getMotivoBloqueio(),3));
        sb.replace(954,954,String.valueOf(this.getSituacaoDocumento()));
        sb.replace(955,960,StringUtil.formatarCampoCaracter(this.getTipoDocumentoReferencia2(),6));
        sb.replace(961,976,StringUtil.formatarCampoCaracter(this.getDocumentoReferencia2(),16));        
        sb.replace(977,1016,StringUtil.formatarCampoCaracter(this.getNomeFornEventual(),40));
        sb.replace(1017,1116,StringUtil.formatarCampoCaracter(this.getEnderecoFornEventual(),100));
        sb.replace(1117,1132,StringUtil.formatarCampoCaracter(this.getIdentificadorFornEventual(),16));
        sb.replace(1133,1136,StringUtil.formatarCampoCaracter(this.getBancoFornEventual(),4));
        sb.replace(1137,1141,StringUtil.formatarCampoCaracter(this.getAgenciaFornEventual(),5));
        sb.replace(1142,1161,StringUtil.formatarCampoCaracter(this.getContaFornEventual(),20));
        
        return sb.substring(0,1162);
        
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

 
    
}

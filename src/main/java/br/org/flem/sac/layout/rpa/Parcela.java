/*
 * Parcela.java
 * 
 * Created on 29/11/2007, 13:31:57
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.layout.rpa;

import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.util.IOUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lbrito
 */
public class Parcela implements Layout {
private String companhia; // Companhia devera estar parametrizada
private String grupoCP;
private String entidade; // Entidade devera estar ativa
private String local;
private String tipoDocumentoReferencia;
private String documentoReferencia;
private String tipoRegistro; // O valor padrão é "03" conforme Layout do sistema
private Date dataVencimento;
private Double valorParcela;
private String tipoDocumentoCobranca;
private String codigoBarra;
private String nomeFavorecido;
private String documentoIdentFavorecido;
private String bancoFavororecido;
private String agenciaFavorecido;
private String contaFavorecido;
private String comentario1;
private String comentario2;
private String comentario3;

    public String getAgenciaFavorecido() {
        return agenciaFavorecido;
    }

    public void setAgenciaFavorecido(String agenciaFavorecido) {
        this.agenciaFavorecido = agenciaFavorecido;
    }

    public String getBancoFavororecido() {
        return bancoFavororecido;
    }

    public void setBancoFavororecido(String bancoFavororecido) {
        this.bancoFavororecido = bancoFavororecido;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public String getComentario1() {
        return comentario1;
    }

    public void setComentario1(String comentario1) {
        this.comentario1 = comentario1;
    }

    public String getComentario2() {
        return comentario2;
    }

    public void setComentario2(String comentario2) {
        this.comentario2 = comentario2;
    }

    public String getComentario3() {
        return comentario3;
    }

    public void setComentario3(String comentario3) {
        this.comentario3 = comentario3;
    }

    public String getCompanhia() {
        return companhia;
    }

    public void setCompanhia(String companhia) {
        this.companhia = companhia;
    }

    public String getContaFavorecido() {
        return contaFavorecido;
    }

    public void setContaFavorecido(String contaFavorecido) {
        this.contaFavorecido = contaFavorecido;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getDocumentoIdentFavorecido() {
        return documentoIdentFavorecido;
    }

    public void setDocumentoIdentFavorecido(String documentoIdentFavorecido) {
        this.documentoIdentFavorecido = documentoIdentFavorecido;
    }

    public String getDocumentoReferencia() {
        return documentoReferencia;
    }

    public void setDocumentoReferencia(String documentoReferencia) {
        this.documentoReferencia = documentoReferencia;
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getNomeFavorecido() {
        return nomeFavorecido;
    }

    public void setNomeFavorecido(String nomeFavorecido) {
        this.nomeFavorecido = nomeFavorecido;
    }

    public String getTipoDocumentoCobranca() {
        return tipoDocumentoCobranca;
    }

    public void setTipoDocumentoCobranca(String tipoDocumentoCobranca) {
        this.tipoDocumentoCobranca = tipoDocumentoCobranca;
    }

    public String getTipoDocumentoReferencia() {
        return tipoDocumentoReferencia;
    }

    public void setTipoDocumentoReferencia(String tipoDocumentoReferencia) {
        this.tipoDocumentoReferencia = tipoDocumentoReferencia;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public Double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(Double valorParcela) {
        this.valorParcela = valorParcela;
    }


    public Parcela() {
        tipoDocumentoCobranca = ""; // Opcional, conforme layout de carga
        codigoBarra = ""; // Opcional, conforme layout de carga
        nomeFavorecido = ""; // Opcional, conforme layout de carga
        documentoIdentFavorecido = ""; // Opcional, conforme layout de carga
        bancoFavororecido = "";
        agenciaFavorecido = "";
        contaFavorecido = "";
        comentario1 = "";
        comentario2 = "";
        comentario3 = "";

    }

    public String toLayout() {
        StringBuffer sb = IOUtil.linhaVazia(416);
        SimpleDateFormat formatoVencimento = new SimpleDateFormat("yyyyMMdd");
        
        sb.replace(0,3,StringUtil.formatarCampoCaracter(getCompanhia(),4) );
        sb.replace(4,7,StringUtil.formatarCampoCaracter( getGrupoCP(),4 ));
        sb.replace(8,23,StringUtil.formatarCampoCaracter( getEntidade(),16 ));
        sb.replace(24,27,StringUtil.formatarCampoCaracter( getLocal(),4 ));
        sb.replace(28,33,StringUtil.formatarCampoCaracter( getTipoDocumentoReferencia(),6 ));
        sb.replace(34,49,StringUtil.formatarCampoCaracter( getDocumentoReferencia(),16 ));
        sb.replace(50,51,String.valueOf( getTipoRegistro() ));
        sb.replace(52,59,StringUtil.formatarCampoCaracter( formatoVencimento.format(getDataVencimento()),8 ));
        sb.replace(60,77,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString( getValorParcela()),18));
        sb.replace(78,83,StringUtil.formatarCampoCaracter( getTipoDocumentoCobranca(),6 ));
        sb.replace(84,131,StringUtil.formatarCampoCaracter( getCodigoBarra(),48 ));
        sb.replace(132,191,StringUtil.formatarCampoCaracter( getNomeFavorecido(),60 ));
        sb.replace(192,206,StringUtil.formatarCampoCaracter( getDocumentoIdentFavorecido(),15 ));
        sb.replace(207,210,StringUtil.formatarCampoCaracter( getBancoFavororecido(),4 ));
        sb.replace(211,215,StringUtil.formatarCampoCaracter( getAgenciaFavorecido(),5 ));
        sb.replace(216,235,StringUtil.formatarCampoCaracter( getContaFavorecido(),5 ));
        sb.replace(236,295,StringUtil.formatarCampoCaracter( getComentario1(),60 ));
        sb.replace(296,355,StringUtil.formatarCampoCaracter( getComentario2(),60 ));
        sb.replace(356,415,StringUtil.formatarCampoCaracter( getComentario3(),60 ));
        


        return sb.substring(0,416);

    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }



}

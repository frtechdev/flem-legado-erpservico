/*
 * RegistroTipo2.java
 *
 * Created on 22 de Agosto de 2007, 16:49
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.sac.layout.ebal.batch;

/**
 *
 * @author fcsilva
 */
import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.util.IOUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RegistroTipo2 implements Layout {
    
    private int tipoRegistro = 2;
    private String nomeLote = "";
    private int estabelecimento;
    private String empresa;
    private int prontuario;
    private Date dataCompetencia = new Date();
    private Date dataBase = new Date();
    private int codigoVerba;
    private double valor;
    private double referencia;
    private int calculo;
    private int numeroParcelas;
    private int filler;
    
    private SimpleDateFormat formatoMesAno = new SimpleDateFormat("MMyyyy");      
    
    public int getTipoRegistro() {
        return tipoRegistro;
    }
    
    public void setTipoRegistro(int tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }
    
    public String getNomeLote() {
        return nomeLote;
    }
    
    public void setNomeLote(String nomeLote) {
        this.nomeLote = nomeLote;
    }
    
    public int getEstabelecimento() {
        return estabelecimento;
    }
    
    public void setEstabelecimento(int estabelecimento) {
        this.estabelecimento = estabelecimento;
    }
    
    public int getProntuario() {
        return prontuario;
    }
    
    public void setProntuario(int prontuario) {
        this.prontuario = prontuario;
    }
    
    public Date getDataCompetencia() {
        return dataCompetencia;
    }
    
    public void setDataCompetencia(Date dataCompetencia) {
        this.dataCompetencia = dataCompetencia;
    }
    
    public Date getDataBase() {
        return dataBase;
    }
    
    public void setDataBase(Date dataBase) {
        this.dataBase = dataBase;
    }
    
    public int getCodigoVerba() {
        return codigoVerba;
    }
    
    public void setCodigoVerba(int codigoVerba) {
        this.codigoVerba = codigoVerba;
    }
    
    public double getValor() {
        return valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public double getReferencia() {
        return referencia;
    }
    
    public void setReferencia(double referencia) {
        this.referencia = referencia;
    }
    
    public int getCalculo() {
        return calculo;
    }
    
    public void setCalculo(int calculo) {
        this.calculo = calculo;
    }
    
    public int getNumeroParcelas() {
        return numeroParcelas;
    }
    
    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }
    
    public int getFiller() {
        return filler;
    }
    
    public void setFiller(int filler) {
        this.filler = filler;
    }
    
    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }    
    
    public String toLayout(){
        StringBuffer sb = IOUtil.linhaVazia(82);
        sb.replace(0,0,String.valueOf(this.getTipoRegistro()));
        sb.replace(1,10,StringUtil.formatarCampoCaracter(this.getNomeLote(),10));
        sb.replace(11,14,StringUtil.formatarCampoNumerico(this.getEstabelecimento(),4));
        sb.replace(15,17,StringUtil.formatarCampoCaracter(this.getEmpresa(),3));
        sb.replace(18,27,StringUtil.formatarCampoNumerico(this.getProntuario(),10));
        sb.replace(28,33,String.valueOf(formatoMesAno.format(this.getDataCompetencia())));
        sb.replace(34,39,String.valueOf(formatoMesAno.format(this.getDataBase())));
        sb.replace(40,42,StringUtil.formatarCampoNumerico(this.getCodigoVerba(),3));        
        sb.replace(43,59,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValor()),17));
        sb.replace(60,70,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getReferencia(),4),11));
        sb.replace(71,72,StringUtil.formatarCampoNumerico(this.getCalculo(),2));       
        sb.replace(73,74,StringUtil.formatarCampoNumerico(this.getNumeroParcelas(),2));        
        sb.replace(75,81,StringUtil.formatarZeros(this.getFiller()));
        return sb.substring(0,82);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

}

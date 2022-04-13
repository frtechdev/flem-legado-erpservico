/*
 * RetornoConciliacaoHeader.java
 *
 * Created on 21/08/2007, 15:18:36
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.layout.ebal.retornoconciliacao;

import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.util.IOUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fcsilva
 */
public class RetornoConciliacaoHeader implements Layout {
    
    private int indicadorRegistro;
    private String descritivoDestino;
    private String constante;
    private String tipoRetorno;
    private Date folhaReferencia;
    private Date dataGeracaoArquivo;
    private int contador;
    
    public int getIndicadorRegistro() {
        return indicadorRegistro;
    }
    
    public void setIndicadorRegistro(int indicadorRegistro) {
        this.indicadorRegistro = indicadorRegistro;
    }
    
    public String getDescritivoDestino() {
        return descritivoDestino;
    }
    
    public void setDescritivoDestino(String descritivoDestino) {
        this.descritivoDestino = descritivoDestino;
    }
    
    public String getConstante() {
        return constante;
    }
    
    public void setConstante(String constante) {
        this.constante = constante;
    }
    
    public String getTipoRetorno() {
        return tipoRetorno;
    }
    
    public void setTipoRetorno(String tipoRetorno) {
        this.tipoRetorno = tipoRetorno;
    }
    
    public Date getFolhaReferencia() {
        return folhaReferencia;
    }
    
    public void setFolhaReferencia(Date folhaReferencia) {
        this.folhaReferencia = folhaReferencia;
    }
    
    public Date getDataGeracaoArquivo() {
        return dataGeracaoArquivo;
    }
    
    public void setDataGeracaoArquivo(Date dataGeracaoArquivo) {
        this.dataGeracaoArquivo = dataGeracaoArquivo;
    }
    
    public int getContador() {
        return contador;
    }
    
    public void setContador(int contador) {
        this.contador = contador;
    }
    
    public String toLayout() {
        SimpleDateFormat formatoMesAno = new SimpleDateFormat("MMyyyy");
        SimpleDateFormat formatoDiaMesAno = new SimpleDateFormat("ddMMyyyy");
        StringBuffer sb = IOUtil.linhaVazia(67);
        sb.replace(0,0,String.valueOf(this.getIndicadorRegistro()));
        sb.replace(1,40,String.valueOf(this.getDescritivoDestino()));
        sb.replace(41,45,String.valueOf(this.getConstante()));
        sb.replace(46,46,String.valueOf(this.getTipoRetorno()));
        sb.replace(47,52,String.valueOf(formatoMesAno.format(this.getFolhaReferencia())));
        sb.replace(53,60,String.valueOf(formatoDiaMesAno.format(this.getDataGeracaoArquivo())));
        sb.replace(61,66,StringUtil.formatarCampoNumerico(this.getContador(),6));
        return sb.substring(0,67);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

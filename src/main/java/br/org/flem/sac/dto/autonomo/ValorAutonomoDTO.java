/*
 * ValorAutonomoDTO.java
 *
 * Created on 27 de Outubro de 2006, 10:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.sac.dto.autonomo;

import br.org.flem.fw.service.IEntidade;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.util.IOUtil;
import java.io.Serializable;
import java.util.List;

/**
 *codigo autonomo = codAutonomo
 *cnfp do contratante
 *
 *
 * @author mjpereira
 */
public class ValorAutonomoDTO implements Layout, Serializable {

    private String dataReferencia = "012000";
    private String valorRemuneracao;
    private String contribuicao;

    private String cnpjEmpresa = "03037070000102";
    private String indicadorDelecao = " ";
    private String indicadorAdicao = " ";
    private String ocorrenciaGFIP = "05";

    private String nome = "";
    private double base = 0.0;
    private double iss = 0.0;
    private double insn = 0.0;
    private double insd = 0.0;
    private double ir = 0.0;

    private IEntidade entidade;

    /** Creates a new instance of ValoresAutonomosDTO */
    public ValorAutonomoDTO() {
    }

    public IEntidade getEntidade() {
        return entidade;
    }

    public void setEntidade(IEntidade entidade) {
        this.entidade = entidade;
    }




    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public String getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(String dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public String getValorRemuneracao() {
        return valorRemuneracao;
    }

    public void setValorRemuneracao(String valorRemuneracao) {
        this.valorRemuneracao = valorRemuneracao;
    }

    public String getIndicadorDelecao() {
        return indicadorDelecao;
    }

    public void setIndicadorDelecao(String indicadorDelecao) {
        this.indicadorDelecao = indicadorDelecao;
    }

    public String getIndicadorAdicao() {
        return indicadorAdicao;
    }

    public void setIndicadorAdicao(String indicadorAdicao) {
        this.indicadorAdicao = indicadorAdicao;
    }

    public String getContribuicao() {
        return contribuicao;
    }

    public void setContribuicao(String contribuicao) {
        this.contribuicao = contribuicao;
    }

    public String getOcorrenciaGFIP() {
        return ocorrenciaGFIP;
    }

    public void setOcorrenciaGFIP(String ocorrenciaGFIP) {
        this.ocorrenciaGFIP = ocorrenciaGFIP;
    }

    /**
     * Layout:
     *
     *Arquivo Sequiencial.
     *Tamanho registro: 116
     *Descricao dos campos (obrigatorios) preenchidos.
     *-----------------------------------------------------
     *CAMPO                 : FORMATO : TAM : POS INI : POS FIM : DESCRICAO
     *CODIGO DO AUTONOMO    :    A    :  6  :   01    :   06    : CODIGO DO AUTONO P...
     *CNPJ DO CONTRATANTE   :    A    : 14  :   07    :   20    : FIXO - CNPJ FLEM
     *DATA DE REFERENCIA    :    A    :  6  :   21    :   26    : MES E ANO (MMAAAA)
     *CPF DO FORNECEDOR     :    A    : 11  :   27    :   37    : CPF DO AUTONOMO
     *VALOR DA REMUNERACAO  :    A    : 17  :   38    :   54    : VALOR DA REMUNERACAO DO AUTONOMO
     *INDICADOR DE DELECAO  :    A    :  1  :   55    :   55    : S / N OU "BRANCO"- USAR "BRANCO"
     *INDICADOR DE ADICAO   :    A    :  1  :   56    :   56    : S / N OU "BRANCO"
     *CNPJ DO FORNECEDOR    :    A    : 14  :   57    :   70    : FIXO EM BRANCO
     *NOTA FISCAL           :    A    : 10  :   71    :   80    : FIXO EM BRANCO
     *VL CONT AUTONOMO      :    A    : 17  :   81    :   97    : VALOR INSS RETIDO
     *VL CONT ADICIONAL COOP:    A    : 17  :   98    :  114    : --- ? ----
     *OCORRENCIA GFIP       :    A    :  2  :  115    :  116    : FIXO 05
     */
    public String toLayout() {
        StringBuffer sb = IOUtil.linhaVazia(116);
        //sb.replace(0, 5, this.entidade.getCodigo().substring(1));
        if(this.entidade.getCodigo().substring(0, 1).equals("P")){
            sb.replace(0, 5, this.entidade.getCodigo().substring(1));
        }else{
            if(this.entidade.getCodigoAntigo().length() > 0 && this.entidade.getCodigoAntigo().substring(0, 1).equals("P")){
                sb.replace(0, 5, this.entidade.getCodigoAntigo().substring(1));
            }else{
                sb.replace(0, 5, "      ");
            }
        }
        sb.replace(6, 19, this.getCnpjEmpresa());
        sb.replace(20, 25, this.getDataReferencia());
        sb.replace(26, 36, this.entidade.getCpf());
        String base = StringUtil.converteNumeroString(this.entidade.getBase());
        sb.replace(37, 53, StringUtil.formatarCampoCaracter(base, 17));
        sb.replace(54, 54, this.getIndicadorDelecao());
        sb.replace(55, 65, this.getIndicadorAdicao());
        String insd = StringUtil.converteNumeroString(this.entidade.getInsd());
        sb.replace(80, 96, StringUtil.formatarCampoCaracter(insd, 17));
        sb.replace(114, 115, this.getOcorrenciaGFIP());
        return sb.substring(0, 116);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getIss() {
        return iss;
    }

    public void setIss(double iss) {
        this.iss = iss;
    }

    public double getInsn() {
        return insn;
    }

    public void setInsn(double insn) {
        this.insn = insn;
    }

    public double getInsd() {
        return insd;
    }

    public void setInsd(double insd) {
        this.insd = insd;
    }

    public double getIr() {
        return ir;
    }

    public void setIr(double ir) {
        this.ir = ir;
    }


    public void setImposto(String tipo, double valor) {
        if ("IR".equals(tipo)) {
            this.ir = valor;
        } else if ("ISS".equals(tipo)) {
            this.iss = valor;
        } else if ("INSN".equals(tipo)) {
            this.insn = valor;
        } else if ("INSD".equals(tipo)) {
            this.insd = valor;
        }
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

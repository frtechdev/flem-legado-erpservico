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
import br.org.flem.fwe.util.Valores;
import br.org.flem.sac.util.IOUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *codigo autonomo = codAutonomo
 *cnfp do contratante
 *
 *
 * @author mjpereira
 */
public class FolhaAutonomoDTO implements Layout, Serializable {

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
    public FolhaAutonomoDTO() {
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
AUT        9999 NÚBIA MARIA                    01/01/2009  00/00/0000   301      1            1,00     M      200,00   0      0

 1 -FOLHA DE PAGAMENTO CLT

 009 REND. BR AUT                13.687,50   394 INSS CT INDI  11,0000          354,08   900 INSS EMP TOT  20,0000        2.737,50
                                             395 ISS RET AUTO   5,0000            1,00   901 REND. LQ. AU                   314,16
                                             396 IRRF                         3.764,06
     */
    public List toLayoutCollection() {

        List lista = new ArrayList();
        StringBuffer sb = IOUtil.linhaVazia(133);
        sb.replace(0, 3,   StringUtil.formatarCampoCaracter(" AUT",4));
        sb.replace(5, 14,  StringUtil.formatarCampoCaracterAlinhadoDireita(this.entidade.getCodigo().substring(1),10));
        sb.replace(16, 45, StringUtil.formatarCampoCaracter(this.getEntidade().getNomeExtenso(),30));
        sb.replace(47, 56, StringUtil.formatarCampoCaracter("01/01/2009",10));
        sb.replace(70, 74, StringUtil.formatarCampoCaracterAlinhadoDireita("302",5));
        sb.replace(81, 81, StringUtil.formatarCampoCaracterAlinhadoDireita("1",1));
        sb.replace(94, 98, StringUtil.formatarCampoCaracterAlinhadoDireita("1,00",4));
        sb.replace(103, 103, StringUtil.formatarCampoCaracter("M",1) );
        sb.replace(110, 116, StringUtil.formatarCampoCaracter("200,00",6)  );
        sb.replace(119, 120, StringUtil.formatarCampoCaracter("0",1)  );
        sb.replace(126, 127,StringUtil.formatarCampoCaracter("0",1)  );
        lista.add(sb);
        sb = new StringBuffer();
        lista.add(sb);
        sb = new StringBuffer();
        sb.append(" 1 -FOLHA DE PAGAMENTO AUT ");
        lista.add(sb);
        sb = new StringBuffer();
        lista.add(sb);
        sb = IOUtil.linhaVazia(133);
        sb.replace(0, 17,"  009 REND. BR AUT");
        sb.replace(28,42,  StringUtil.formatarCampoCaracterAlinhadoDireita(Valores.formataMoeda(this.entidade.getBase())+"",15));

        sb.replace(46, 70,"394 INSS CT INDI  11,0000");
        sb.replace(72,86,  StringUtil.formatarCampoCaracterAlinhadoDireita(Valores.formataMoeda(this.entidade.getInsd()),15));

        sb.replace(90, 114,"900 INSS EMP TOT  20,0000");
        sb.replace(116,130,  StringUtil.formatarCampoCaracterAlinhadoDireita(Valores.formataMoeda(this.entidade.getInsn()),15));

        lista.add(sb);
        sb = IOUtil.linhaVazia(133);

        sb.replace(46, 70,"395 ISS RET AUTO   5,0000");
        sb.replace(72,86,  StringUtil.formatarCampoCaracterAlinhadoDireita(Valores.formataMoeda(this.entidade.getIss()),15));

        sb.replace(90, 114,"901 REND. LQ. AU         ");
        double rendimentoLiquido = this.entidade.getBase() - (this.entidade.getIss() + this.entidade.getInsd() + this.entidade.getIr());
        sb.replace(116,130,  StringUtil.formatarCampoCaracterAlinhadoDireita(Valores.formataMoeda(rendimentoLiquido),15));
        lista.add(sb);
        
        sb = IOUtil.linhaVazia(133);

        sb.replace(46, 70,"396 IRRF                 ");
        sb.replace(72,86,  StringUtil.formatarCampoCaracterAlinhadoDireita(Valores.formataMoeda(this.entidade.getIr()),15));

        lista.add(sb);
        sb = IOUtil.linhaVazia(133);
        sb.replace(0, 132, StringUtil.repetirTexto("-", 133));
        lista.add(sb);
        
        return lista;
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

    public String toLayout() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

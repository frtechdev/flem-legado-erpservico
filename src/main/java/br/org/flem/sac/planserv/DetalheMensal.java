package br.org.flem.sac.planserv;

import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.util.IOUtil;
import java.util.List;

/**
 *
 * @author mccosta
 */
public class DetalheMensal implements Layout {

    //private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    //private SimpleDateFormat shf = new SimpleDateFormat("hhmmss");

    private int tipoRegistro = 3; //fixo
    private int codigoEmpresa = 62704; //fixo
    private int numeroMatriculaTopSaude = 0;
    private int codigoDominio;
    private int relacaoDependencia = 0;
    private String nomeTitular = "";
    private int codigoCliente = 97;//fixo
    private String cpf = "";
    private String filler = "";
    private Double baseCalculo = 0.0;
    private int qtdDependente = 0;
    private Double valorDependente = 0.0;
    private int qtdAgregadoMenor = 0;// 18 a 24 anos
    private Double valorDescontoAgregadoMenor = 0.0;
    private int qtdAgregadoMaior = 0;// maior de 24 anos
    private Double valorDescontoAgregadoMaior = 0.0;
    private Double valorTitular = 0.0;
    private Double valorEspecial = 0.0;
    private String filler2 = "";
    //campos novos: atualização feita em 20/09/2011
    //INI
    private Double valorDependenteRetroativo = 0.0;
    private Double valorDescontoAgregadoMenorRetroativo = 0.0;
    private Double valorDescontoAgregadoMaiorRetroativo = 0.0;
    private Double valorTitularRetroativo = 0.0;
    private Double valorEspecialRetroativo = 0.0;
    private Double valorConjuge = 0.0;
    private Double valorConjugeRetroativo = 0.0;
    private Double valorCoParticipacao = 0.0;
    //FIM
    // campos novos: atualização de layour feita em 12/05/2021
    //INÍCIO
    private Double valorTitularParcelaRisco = 0.0;
    private Double valorConjugeParcelaRisco = 0.0;
    private Double valorDependenteParcelaRisco = 0.0;
    private Double valorAgregadoMenorParcelaRisco = 0.0;
    private Double valorAgregadoMaiorParcelaRisco = 0.0;
    //FIM
    private int numeroRegistro = 0;
    
    
    private Double totalDescontos = 0.0;
    private Double saldo = 0.0;

    public DetalheMensal() {
    }

    public int getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(int tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public Double getBaseCalculo() {
        return baseCalculo;
    }

    public void setBaseCalculo(Double baseCalculo) {
        this.baseCalculo = baseCalculo;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public int getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(int codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public String getFiller2() {
        return filler2;
    }

    public void setFiller2(String filler2) {
        this.filler2 = filler2;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public int getNumeroMatriculaTopSaude() {
        return numeroMatriculaTopSaude;
    }

    public void setNumeroMatriculaTopSaude(int numeroMatriculaTopSaude) {
        this.numeroMatriculaTopSaude = numeroMatriculaTopSaude;
    }

    public int getCodigoDominio() {
        return codigoDominio;
    }

    public void setCodigoDominio(int codigoDominio) {
        this.codigoDominio = codigoDominio;
    }

    public int getQtdAgregadoMaior() {
        return qtdAgregadoMaior;
    }

    public void setQtdAgregadoMaior(int qtdAgregadoMaior) {
        this.qtdAgregadoMaior = qtdAgregadoMaior;
    }

    public int getQtdAgregadoMenor() {
        return qtdAgregadoMenor;
    }

    public void setQtdAgregadoMenor(int qtdAgregadoMenor) {
        this.qtdAgregadoMenor = qtdAgregadoMenor;
    }

    public int getQtdDependente() {
        return qtdDependente;
    }

    public void setQtdDependente(int qtdDependente) {
        this.qtdDependente = qtdDependente;
    }

    public int getRelacaoDependencia() {
        return relacaoDependencia;
    }

    public void setRelacaoDependencia(int relacaoDependencia) {
        this.relacaoDependencia = relacaoDependencia;
    }

    public Double getValorDependente() {
        return valorDependente;
    }

    public void setValorDependente(Double valorDependente) {
        this.valorDependente = valorDependente;
    }

    public Double getValorDescontoAgregadoMaior() {
        return valorDescontoAgregadoMaior;
    }

    public void setValorDescontoAgregadoMaior(Double valorDescontoAgregadoMaior) {
        this.valorDescontoAgregadoMaior = valorDescontoAgregadoMaior;
    }

    public Double getValorDescontoAgregadoMenor() {
        return valorDescontoAgregadoMenor;
    }

    public void setValorDescontoAgregadoMenor(Double valorDescontoAgregadoMenor) {
        this.valorDescontoAgregadoMenor = valorDescontoAgregadoMenor;
    }

    public Double getValorEspecial() {
        return valorEspecial;
    }

    public void setValorEspecial(Double valorEspecial) {
        this.valorEspecial = valorEspecial;
    }

    public Double getValorTitular() {
        return valorTitular;
    }

    public void setValorTitular(Double valorTitular) {
        this.valorTitular = valorTitular;
    }

    public Double getValorCoParticipacao() {
        return valorCoParticipacao;
    }

    public void setValorCoParticipacao(Double valorCoParticipacao) {
        this.valorCoParticipacao = valorCoParticipacao;
    }

    public Double getValorConjuge() {
        return valorConjuge;
    }

    public void setValorConjuge(Double valorConjuge) {
        this.valorConjuge = valorConjuge;
    }

    public Double getValorConjugeRetroativo() {
        return valorConjugeRetroativo;
    }

    public void setValorConjugeRetroativo(Double valorConjugeRetroativo) {
        this.valorConjugeRetroativo = valorConjugeRetroativo;
    }

    public Double getValorDependenteRetroativo() {
        return valorDependenteRetroativo;
    }

    public void setValorDependenteRetroativo(Double valorDependenteRetroativo) {
        this.valorDependenteRetroativo = valorDependenteRetroativo;
    }

    public Double getValorDescontoAgregadoMaiorRetroativo() {
        return valorDescontoAgregadoMaiorRetroativo;
    }

    public void setValorDescontoAgregadoMaiorRetroativo(Double valorDescontoAgregadoMaiorRetroativo) {
        this.valorDescontoAgregadoMaiorRetroativo = valorDescontoAgregadoMaiorRetroativo;
    }

    public Double getValorDescontoAgregadoMenorRetroativo() {
        return valorDescontoAgregadoMenorRetroativo;
    }

    public void setValorDescontoAgregadoMenorRetroativo(Double valorDescontoAgregadoMenorRetroativo) {
        this.valorDescontoAgregadoMenorRetroativo = valorDescontoAgregadoMenorRetroativo;
    }

    public Double getValorEspecialRetroativo() {
        return valorEspecialRetroativo;
    }

    public void setValorEspecialRetroativo(Double valorEspecialRetroativo) {
        this.valorEspecialRetroativo = valorEspecialRetroativo;
    }

    public Double getValorTitularRetroativo() {
        return valorTitularRetroativo;
    }

    public void setValorTitularRetroativo(Double valorTitularRetroativo) {
        this.valorTitularRetroativo = valorTitularRetroativo;
    }

    public int getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(int numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public Double getTotalDescontos() {
        return totalDescontos;
    }

    public void setTotalDescontos(Double totalDescontos) {
        this.totalDescontos = totalDescontos;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    
    //GETTERS E SETTERS  DO NOVO LAYOUT DA PARCELA DE RISCO
    //IMPLEMENTADO EM 12/05/2021
    //INICIO
    
    
     public Double getValorTitularParcelaRisco() {
        return valorTitularParcelaRisco;
    }

    public void setValorTitularParcelaRisco(Double valorTitularParcelaRisco) {
        this.valorTitularParcelaRisco = valorTitularParcelaRisco;
    }
    
    public Double getValorConjugeParcelaRisco() {
        return valorConjugeParcelaRisco;
    }

    public void setValorConjugeParcelaRisco(Double valorConjugeParcelaRisco) {
        this.valorConjugeParcelaRisco = valorConjugeParcelaRisco;
    }
    
    public Double getValorDependenteParcelaRisco() {
        return valorDependenteParcelaRisco;
    }

    public void setValorDependenteParcelaRisco(Double valorDependenteParcelaRisco) {
        this.valorDependenteParcelaRisco = valorDependenteParcelaRisco;
    }
    
    public Double getValorAgregadoMenorParcelaRisco() {
        return valorAgregadoMenorParcelaRisco;
    }

    public void setValorAgregadoMenorParcelaRisco(Double valorAgregadoMenorParcelaRisco) {
        this.valorAgregadoMenorParcelaRisco = valorAgregadoMenorParcelaRisco;
    }
    
    public Double getValorAgregadoMaiorParcelaRisco() {
        return valorAgregadoMaiorParcelaRisco;
    }

    public void setValorAgregadoMaiorParcelaRisco(Double valorAgregadoMaiorParcelaRisco) {
        this.valorAgregadoMaiorParcelaRisco = valorAgregadoMaiorParcelaRisco;
    }
    
    //FIM
    
    
    public String toLayout() {
        //StringBuffer sb = IOUtil.linhaVazia(378);
        StringBuffer sb = IOUtil.linhaVazia(250);
        sb.replace(0, 0, String.valueOf(this.getTipoRegistro()));
        sb.replace(1, 5, StringUtil.formatarCampoNumerico(this.getCodigoEmpresa(),5));
        sb.replace(6, 14, String.valueOf(this.getCodigoCliente()) + StringUtil.formatarCampoNumerico(String.valueOf(this.getNumeroMatriculaTopSaude()),6)+"0");
        sb.replace(15, 17, StringUtil.formatarCampoNumerico(this.getRelacaoDependencia(),3));
        sb.replace(18, 67, StringUtil.formatarCampoCaracter(this.getNomeTitular(), 50));
        sb.replace(68, 69, StringUtil.formatarCampoNumerico(this.getCodigoCliente(),2));
        sb.replace(70, 80, StringUtil.formatarCampoNumerico(this.getCpf(), 11));
        sb.replace(81, 95, StringUtil.formatarCampoCaracter(this.getFiller(), 15));
        sb.replace(96,103, StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getBaseCalculo()), 8));
        sb.replace(104,105,StringUtil.formatarCampoNumerico(this.getQtdDependente(), 2));
        sb.replace(106,113,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorDependente()), 8));
        sb.replace(114,115,StringUtil.formatarCampoNumerico(this.getQtdAgregadoMenor(), 2));
        sb.replace(116,123,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorDescontoAgregadoMenor()), 8));
        sb.replace(124,125,StringUtil.formatarCampoNumerico(this.getQtdAgregadoMaior(), 2));
        sb.replace(126,133,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorDescontoAgregadoMaior()), 8));
        sb.replace(134,141,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorTitular()), 8));
        sb.replace(142,149,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorEspecial()), 8));
        sb.replace(150, 157, StringUtil.formatarCampoCaracter(this.getFiller2(), 8));
        //campos novos: atualização feita em 20/09/2011
        //INI
        //CÓDIGO COMENTADO ABAIXO POR DANIEL AUGUSTO ALMEIDA EM 06-05-2021
        //sb.replace(158,163,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorDependenteRetroativo()), 6));
        //sb.replace(164,169,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorDescontoAgregadoMenorRetroativo()), 6));
        //sb.replace(170,175,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorDescontoAgregadoMaiorRetroativo()), 6));
        //sb.replace(176,181,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorTitularRetroativo()), 6));
        //sb.replace(182,187,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorEspecialRetroativo()), 6));
        //sb.replace(188,195,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorConjuge()), 8));
        //sb.replace(196,201,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorConjugeRetroativo()), 6));
        //sb.replace(202,209,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorCoParticipacao()), 8));
        //FIM
        //CÓDIGO COMENTADO ACIMA POR DANIEL AUGUSTO ALMEIDA EM 06-05-2021
        
        //CAMPO DE 30 ZEROS PROVISÓRIO, IMPLEMENTADO EM 06/05/2021
        ////////////// sb.replace(158,187, StringUtil.formatarCampoNumerico(000000000000000000000000000000, 30));
        
        sb.replace(158,163,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorDependenteRetroativo()), 6));
        sb.replace(164,169,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorDescontoAgregadoMenorRetroativo()), 6));
        sb.replace(170,175,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorDescontoAgregadoMaiorRetroativo()), 6));
        sb.replace(176,181,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorTitularRetroativo()), 6));
        sb.replace(182,187,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorEspecialRetroativo()), 6));
        sb.replace(188,195,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorConjuge()), 8));
        sb.replace(196,201,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorConjugeRetroativo()), 6));
        sb.replace(202,209,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorCoParticipacao()), 8));
        
        // CAMPOS NOVOS DO LAYOUT NOVO, IMPLEMENTADO EM 12/05/2021
        //INICIO
        sb.replace(210,215,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorTitularParcelaRisco()), 6));
        sb.replace(216,221,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorConjugeParcelaRisco()), 6));
        sb.replace(222,227,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorDependenteParcelaRisco()), 6));
        sb.replace(228,233,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorAgregadoMenorParcelaRisco()), 6));
        sb.replace(234,239,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorAgregadoMaiorParcelaRisco()), 6));
        //FIM
        
        //sb.replace(158, 167, StringUtil.formatarCampoNumerico(this.getNumeroRegistro(), 10));
        
        
        
        sb.replace(240, 249, StringUtil.formatarCampoNumerico(this.getNumeroRegistro(), 10));
        return sb.substring(0, 250);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

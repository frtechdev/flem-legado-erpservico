package br.org.flem.sac.planserv;

import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.dto.planserv.SituacaoFuncional;
import br.org.flem.sac.util.IOUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mjpereira
 */
public class Detalhe implements Layout {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private SimpleDateFormat shf = new SimpleDateFormat("hhmmss");
    private String dataNula = "        ";

    private int tipoRegistro = 3; //fixo
    private String tipoOperacao = "2";
    private int codigoEmpresa = 62704; //fixo
    private int matricula = 0;
    private int rdp = 0;
    private int dv = 0; //fixo
    private String nome = "";
    private Date nascimento = new Date();
    private String sexo = "";
    private Date dataOperacao = new Date();
    private String grauDependencia = "00";
    private SituacaoFuncional situacaoFuncional = SituacaoFuncional.FUNCIONARIO_EM_ATIVIDADE;
    private Date admissao = new Date();
    private int codigoPlano = 0;
    private int codigoOrgao = 97; //fixo;
    private String codigoLocalTrabalho = "";
    private String cpf = "";
    private String logradouro = "";
    private String numero = "";
    private String complemento = "";
    private String bairro = "";
    private String municipio = "";
    private String codigoMunicipio = "";
    private String cep = "";
    private String codigoUnidade = "";
    private String ddd ="";
    private String telefone ="";
    private Date casamento = null;
    private Date beneficio = null;
    private Date retornoBeneficio = null;
    private String indicadorCartao = "";
    private String nomeMae ="";
    private String codigoExclusao ="";
    private int nsr = 0;

    public Detalhe() {
    }

    public Date getAdmissao() {
        return admissao;
    }

    public void setAdmissao(Date admissao) {
        this.admissao = admissao;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Date getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(Date beneficio) {
        this.beneficio = beneficio;
    }

    public Date getCasamento() {
        return casamento;
    }

    public void setCasamento(Date casamento) {
        this.casamento = casamento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(int codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public String getCodigoExclusao() {
        return codigoExclusao;
    }

    public void setCodigoExclusao(String codigoExclusao) {
        this.codigoExclusao = codigoExclusao;
    }

    public String getCodigoLocalTrabalho() {
        return codigoLocalTrabalho;
    }

    public void setCodigoLocalTrabalho(String codigoLocalTrabalho) {
        this.codigoLocalTrabalho = codigoLocalTrabalho;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public int getCodigoOrgao() {
        return codigoOrgao;
    }

    public void setCodigoOrgao(int codigoOrgao) {
        this.codigoOrgao = codigoOrgao;
    }

    public int getCodigoPlano() {
        return codigoPlano;
    }

    public void setCodigoPlano(int codigoPlano) {
        this.codigoPlano = codigoPlano;
    }

    public String getCodigoUnidade() {
        return codigoUnidade;
    }

    public void setCodigoUnidade(String codigoUnidade) {
        this.codigoUnidade = codigoUnidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(Date dataOperacao) {
        this.dataOperacao = dataOperacao;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public int getDv() {
        return dv;
    }

    public void setDv(int dv) {
        this.dv = dv;
    }

    public String getGrauDependencia() {
        return grauDependencia;
    }

    public void setGrauDependencia(String grauDependencia) {
        this.grauDependencia = grauDependencia;
    }

    public String getIndicadorCartao() {
        return indicadorCartao;
    }

    public void setIndicadorCartao(String indicadorCartao) {
        this.indicadorCartao = indicadorCartao;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public int getNsr() {
        return nsr;
    }

    public void setNsr(int nsr) {
        this.nsr = nsr;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getRdp() {
        return rdp;
    }

    public void setRdp(int rdp) {
        this.rdp = rdp;
    }

    public Date getRetornoBeneficio() {
        return retornoBeneficio;
    }

    public void setRetornoBeneficio(Date retornoBeneficio) {
        this.retornoBeneficio = retornoBeneficio;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public SituacaoFuncional getSituacaoFuncional() {
        return situacaoFuncional;
    }

    public void setSituacaoFuncional(SituacaoFuncional situacaoFuncional) {
        this.situacaoFuncional = situacaoFuncional;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public int getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(int tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    @Override
    public String toLayout() {
        int pos = 0;
        StringBuffer sb = IOUtil.linhaVazia(382);
        sb.replace(0, 0, String.valueOf(this.getTipoRegistro()));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(1, 1, this.getTipoOperacao());
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(2, 6, String.valueOf(this.getCodigoEmpresa()));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        //Codigo do orgão 97 + matricula (numerico) + 0 (Devido a compatibilidade com versões anteriores)
        sb.replace(7, 15, String.valueOf(this.getCodigoOrgao()) + StringUtil.formatarCampoNumerico(String.valueOf(this.getMatricula()),6)+"0");
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(16, 17, StringUtil.formatarCampoNumerico(String.valueOf(this.getRdp()), 2));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(18, 18, String.valueOf(this.getDv()));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(19, 78, StringUtil.formatarCampoCaracter(this.getNome(), 60));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(79,86, sdf.format(this.getNascimento()));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(87,87, this.getSexo() == null ? "" : this.getSexo());
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(88,95, sdf.format(this.getDataOperacao()));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(96,97, String.valueOf(this.getGrauDependencia()));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(98,98, StringUtil.formatarCampoNumerico(this.getSituacaoFuncional().getCodigo() > 9 ? 0 : this.getSituacaoFuncional().getCodigo() , 1));//Situação Funcional com 1 digito
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(99,106, sdf.format(this.getAdmissao()));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(107,110, String.valueOf(this.getCodigoPlano()));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(111,112,String.valueOf(this.getCodigoOrgao()));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(113,122,StringUtil.formatarCampoNumerico(this.getCodigoLocalTrabalho(), 10));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(123,133,StringUtil.formatarCampoNumerico(this.getCpf(), 11));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(134,173,formatarCampoCaracterNullSafe(this.getLogradouro(), 40));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(174,183,formatarCampoCaracterNullSafe(this.getNumero(), 10));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(184,203,formatarCampoCaracterNullSafe(this.getComplemento(), 20));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(204,233,formatarCampoCaracterNullSafe(this.getBairro(), 30));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(234,258,formatarCampoCaracterNullSafe(this.getMunicipio(), 25));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(259,263,formatarCampoCaracterNullSafe(this.getCodigoMunicipio(),5));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(264,271,formatarCampoCaracterNullSafe(this.getCep(),8));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(272,278,formatarCampoCaracterNullSafe(this.getCodigoUnidade(),7));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(279,282,formatarCampoCaracterNullSafe(this.getDdd(),4));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(283,290,formatarCampoCaracterNullSafe(this.getTelefone(),8));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(291,298,getCasamento() != null ? sdf.format(this.getCasamento()) : dataNula);
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(299,306,getBeneficio() != null? sdf.format(this.getBeneficio())  : dataNula);
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(307,314,getRetornoBeneficio() != null ? sdf.format(this.getRetornoBeneficio()) : dataNula);
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(315,315,this.getIndicadorCartao());
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(316, 365, StringUtil.formatarCampoCaracter(this.getNomeMae(), 50));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(366, 367, StringUtil.formatarCampoCaracter(this.getCodigoExclusao(), 2));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(368,377,StringUtil.formatarCampoNumerico(String.valueOf(this.getNsr()), 10));
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        sb.replace(378,382,StringUtil.formatarCampoNumerico(this.getSituacaoFuncional().getCodigo() > 9 ? this.getSituacaoFuncional().getCodigo() : 0, 5));//Situação Funcional com mais de 1 digito
        System.out.println(System.out.format("Tamanho %d na linha %d", sb.length(),pos++));
        return sb.substring(0, 383);
    }

    @Override
    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private String formatarCampoCaracterNullSafe(String string,int tamCampo){
        return StringUtil.formatarCampoCaracter(string == null ?  "" : string,tamCampo);
    }
}

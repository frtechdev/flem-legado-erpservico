
package br.org.flem.sac.layout.rpa;

import java.util.List;
import br.org.flem.fwe.util.IOUtil;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import br.org.flem.sac.arquivo.Layout;

@Entity
public class LayoutCaixa implements Layout
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;
    private String nomeReduzido;
    private String cpf;
    private String pis;
    private String dataNascimento;
    private String localNascimento;
    private String ufNascimento;
    private String estadocivil;
    private String nomeConjugue;
    private String cpfConjugue;
    private String nomePai;
    private String nomeMae;
    private String sexo;
    private String tipoDocumento;
    private String numeroDocumento;
    private String orgaoEmissorDocumento;
    private String ufOrgaoEmissorDocumento;
    private String dataEmissaoDocumento;
    private String ocupacao;
    private String dataAdmissao;
    private String tipoLogradouro;
    private String endereco;
    private String numeroEndereco;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String ddd;
    private String telefone;
    private String email;
    private String grauInstrucao;
    private String rendaLiquida;
    private String rendaBruta;
    private String conta;
    private String op;
    private String agencia;
    private String codBanco;
    private String digito;
    private String documento;
    private String espacamento10;
    private String espacamento2;
    private String complemento;
    private String celular;
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public String getNomeCompleto() {
        return this.nomeCompleto;
    }
    
    public void setNomeCompleto(final String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
    
    public String getNomeReduzido() {
        return this.nomeReduzido;
    }
    
    public void setNomeReduzido(final String nomeReduzido) {
        this.nomeReduzido = nomeReduzido;
    }
    
    public String getCpf() {
        return this.cpf;
    }
    
    public void setCpf(final String cpf) {
        this.cpf = cpf;
    }
    
    public String getPis() {
        return this.pis;
    }
    
    public void setPis(final String pis) {
        this.pis = pis;
    }
    
    public String getDataNascimento() {
        return this.dataNascimento;
    }
    
    public void setDataNascimento(final String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    public String getLocalNascimento() {
        return this.localNascimento;
    }
    
    public void setLocalNascimento(final String localNascimento) {
        this.localNascimento = localNascimento;
    }
    
    public String getUfNascimento() {
        return this.ufNascimento;
    }
    
    public void setUfNascimento(final String ufNascimento) {
        this.ufNascimento = ufNascimento;
    }
    
    public String getEstadocivil() {
        return this.estadocivil;
    }
    
    public void setEstadocivil(final String estadocivil) {
        this.estadocivil = estadocivil;
    }
    
    public String getNomeConjugue() {
        return this.nomeConjugue;
    }
    
    public void setNomeConjugue(final String nomeConjugue) {
        this.nomeConjugue = nomeConjugue;
    }
    
    public String getCpfConjugue() {
        return this.cpfConjugue;
    }
    
    public void setCpfConjugue(final String cpfConjugue) {
        this.cpfConjugue = cpfConjugue;
    }
    
    public String getNomePai() {
        return this.nomePai;
    }
    
    public void setNomePai(final String nomePai) {
        this.nomePai = nomePai;
    }
    
    public String getNomeMae() {
        return this.nomeMae;
    }
    
    public void setNomeMae(final String nomeMae) {
        this.nomeMae = nomeMae;
    }
    
    public String getSexo() {
        return this.sexo;
    }
    
    public void setSexo(final String sexo) {
        this.sexo = sexo;
    }
    
    public String getTipoDocumento() {
        return this.tipoDocumento;
    }
    
    public void setTipoDocumento(final String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
    public String getNumeroDocumento() {
        return this.numeroDocumento;
    }
    
    public void setNumeroDocumento(final String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
    
    public String getOrgaoEmissorDocumento() {
        return this.orgaoEmissorDocumento;
    }
    
    public void setOrgaoEmissorDocumento(final String orgaoEmissorDocumento) {
        this.orgaoEmissorDocumento = orgaoEmissorDocumento;
    }
    
    public String getUfOrgaoEmissorDocumento() {
        return this.ufOrgaoEmissorDocumento;
    }
    
    public void setUfOrgaoEmissorDocumento(final String ufOrgaoEmissorDocumento) {
        this.ufOrgaoEmissorDocumento = ufOrgaoEmissorDocumento;
    }
    
    public String getDataEmissaoDocumento() {
        return this.dataEmissaoDocumento;
    }
    
    public void setDataEmissaoDocumento(final String dataEmissaoDocumento) {
        this.dataEmissaoDocumento = dataEmissaoDocumento;
    }
    
    public String getOcupacao() {
        return this.ocupacao;
    }
    
    public void setOcupacao(final String ocupacao) {
        this.ocupacao = ocupacao;
    }
    
    public String getDataAdmissao() {
        return this.dataAdmissao;
    }
    
    public void setDataAdmissao(final String dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }
    
    public String getTipoLogradouro() {
        return this.tipoLogradouro;
    }
    
    public void setTipoLogradouro(final String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }
    
    public String getEndereco() {
        return this.endereco;
    }
    
    public void setEndereco(final String endereco) {
        this.endereco = endereco;
    }
    
    public String getNumeroEndereco() {
        return this.numeroEndereco;
    }
    
    public void setNumeroEndereco(final String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }
    
    public String getBairro() {
        return this.bairro;
    }
    
    public void setBairro(final String bairro) {
        this.bairro = bairro;
    }
    
    public String getCidade() {
        return this.cidade;
    }
    
    public void setCidade(final String cidade) {
        this.cidade = cidade;
    }
    
    public String getUf() {
        return this.uf;
    }
    
    public void setUf(final String uf) {
        this.uf = uf;
    }
    
    public String getCep() {
        return this.cep;
    }
    
    public void setCep(final String cep) {
        this.cep = cep;
    }
    
    public String getDdd() {
        return this.ddd;
    }
    
    public void setDdd(final String ddd) {
        this.ddd = ddd;
    }
    
    public String getTelefone() {
        return this.telefone;
    }
    
    public void setTelefone(final String telefone) {
        this.telefone = telefone;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public String getGrauInstrucao() {
        return this.grauInstrucao;
    }
    
    public void setGrauInstrucao(final String grauInstrucao) {
        this.grauInstrucao = grauInstrucao;
    }
    
    public String getConta() {
        return this.conta;
    }
    
    public void setConta(final String conta) {
        this.conta = conta;
    }
    
    public String getOp() {
        return this.op;
    }
    
    public void setOp(final String op) {
        this.op = op;
    }
    
    public String getAgencia() {
        return this.agencia;
    }
    
    public void setAgencia(final String agencia) {
        this.agencia = agencia;
    }
    
    public String getDocumento() {
        return this.documento;
    }
    
    public void setDocumento(final String documento) {
        this.documento = documento;
    }
    
    public String getEspacamento10() {
        return this.espacamento10;
    }
    
    public void setEspacamento10(final String espacamento10) {
        this.espacamento10 = espacamento10;
    }
    
    public String getEspacamento2() {
        return this.espacamento2;
    }
    
    public void setEspacamento2(final String espacamento2) {
        this.espacamento2 = espacamento2;
    }
    
    public String getCodBanco() {
        return this.codBanco;
    }
    
    public void setCodBanco(final String codBanco) {
        this.codBanco = codBanco;
    }
    
    public String getDigito() {
        return this.digito;
    }
    
    public void setDigito(final String digito) {
        this.digito = digito;
    }
    
    public String getRendaLiquida() {
        return this.rendaLiquida;
    }
    
    public void setRendaLiquida(final String rendaLiquida) {
        this.rendaLiquida = rendaLiquida;
    }
    
    public String getRendaBruta() {
        return this.rendaBruta;
    }
    
    public void setRendaBruta(final String rendaBruta) {
        this.rendaBruta = rendaBruta;
    }
    
    public String getComplemento() {
        return this.complemento;
    }
    
    public void setComplemento(final String complemento) {
        this.complemento = complemento;
    }
    
    public String getCelular() {
        return this.celular;
    }
    
    public void setCelular(final String celular) {
        this.celular = celular;
    }
    
    public String toLayout() {
        final StringBuffer linha = IOUtil.linhaVazia(900);
        linha.replace(0, 69, String.valueOf(this.nomeCompleto));
        linha.replace(70, 101, String.valueOf(this.nomeReduzido));
        linha.replace(102, 113, String.valueOf(this.cpf));
        linha.replace(113, 124, String.valueOf(this.pis));
        linha.replace(124, 133, String.valueOf(this.dataNascimento));
        linha.replace(132, 157, String.valueOf(this.localNascimento));
        linha.replace(157, 159, String.valueOf(this.uf));
        linha.replace(159, 160, String.valueOf(this.estadocivil));
        linha.replace(161, 200, String.valueOf(this.nomeConjugue));
        linha.replace(200, 211, String.valueOf(this.cpfConjugue));
        linha.replace(211, 243, String.valueOf(this.nomePai));
        linha.replace(243, 275, String.valueOf(this.nomeMae));
        linha.replace(275, 276, String.valueOf(this.sexo));
        linha.replace(276, 277, String.valueOf(this.espacamento2));
        linha.replace(278, 279, String.valueOf(this.tipoDocumento));
        linha.replace(279, 294, String.valueOf(this.documento));
        linha.replace(294, 298, String.valueOf(this.orgaoEmissorDocumento));
        linha.replace(299, 300, String.valueOf(this.ufOrgaoEmissorDocumento));
        linha.replace(301, 308, String.valueOf(this.dataEmissaoDocumento));
        linha.replace(309, 324, String.valueOf(this.espacamento10));
        linha.replace(325, 327, String.valueOf(this.ocupacao));
        linha.replace(328, 335, String.valueOf(this.dataAdmissao));
        linha.replace(336, 338, String.valueOf(this.tipoLogradouro));
        linha.replace(339, 360, String.valueOf(this.endereco));
        linha.replace(361, 365, String.valueOf(this.numeroEndereco));
        linha.replace(366, 380, String.valueOf(this.complemento));
        linha.replace(381, 394, String.valueOf(this.bairro));
        linha.replace(395, 419, String.valueOf(this.cidade));
        linha.replace(420, 421, String.valueOf(this.uf));
        linha.replace(422, 429, String.valueOf(this.cep));
        linha.replace(430, 432, String.valueOf(this.ddd));
        linha.replace(433, 441, String.valueOf(this.telefone));
        linha.replace(442, 444, String.valueOf(this.ddd));
        linha.replace(445, 453, String.valueOf(this.celular));
        linha.replace(454, 503, String.valueOf(this.email));
        linha.replace(504, 505, String.valueOf(this.grauInstrucao));
        linha.replace(506, 525, String.valueOf(this.rendaBruta));
        linha.replace(525, 543, String.valueOf(this.rendaLiquida));
        linha.replace(544, 546, this.codBanco);
        linha.replace(547, 550, this.agencia);
        linha.replace(551, 554, this.op);
        linha.replace(555, 567, this.conta);
        linha.replace(568, 570, this.digito);
        return linha.substring(0, 570);
    }
    
    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

/*
 * CadastrosLimitesDetalhe.java
 *
 * Created on 22 de Agosto de 2007, 09:42
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.sac.layout.ebal.cadastroslimites;

import br.org.flem.fw.persistencia.dto.Endereco;
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
public class CadastrosLimitesDetalhe implements Layout {
    
    private SimpleDateFormat formatoDiaMesAno = new SimpleDateFormat("ddMMyyyy");    
    
    private Integer gestor;
    private Integer orgao;
    private Integer matricula;
    private Integer beneficiario;
    private String nome;
    private Date nascimento;
    private String sexo;
    private String Cpf;
    private String nomePai;
    private String nomeMae;
    private String rg;
    private String emissorRg;
    private String emissorUf;
    private Endereco endereco; // IFuncionario      
    private String telefone;
    private Date admissao;
    private String email;
    private String unidade;//
    private String municipio;//
    private String localTrabalho;//flem
    private Double valorLimiteCompras;
   
    public Integer getGestor() {
        return gestor;
    }
    
    public void setGestor(Integer gestor) {
        this.gestor = gestor;
    }
    
    public Integer getOrgao() {
        return orgao;
    }
    
    public void setOrgao(Integer orgao) {
        this.orgao = orgao;
    }
    
    public Integer getMatricula() {
        return matricula;
    }
    
    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }
    
    public Integer getBeneficiario() {
        return beneficiario;
    }
    
    public void setBeneficiario(Integer beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getNome_curto() {
        return nome;
    }

    public void setNome_curto(String nome) {
        this.nome = nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }    
    
    public String getEmissorRg() {
        return emissorRg;
    }

    public void setEmissorRg(String emissorRg) {
        this.emissorRg = emissorRg;
    }

    public String getEmissorUf() {
        return emissorUf;
    }

    public void setEmissorUf(String emissorUf) {
        this.emissorUf = emissorUf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getAdmissao() {
        return admissao;
    }

    public void setAdmissao(Date admissao) {
        this.admissao = admissao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getLocalTrabalho() {
        return localTrabalho;
    }

    public void setLocalTrabalho(String localTrabalho) {
        this.localTrabalho = localTrabalho;
    }

    public Double getValorLimiteCompras() {
        return valorLimiteCompras;
    }

    public void setValorLimiteCompras(Double valorLimiteCompras) {
        this.valorLimiteCompras = valorLimiteCompras;
    }

    public String toLayout(){
        StringBuffer sb = IOUtil.linhaVazia(426);        
        sb.replace(0,3,StringUtil.formatarCampoNumerico(this.getGestor(),4));
        sb.replace(4,6,StringUtil.formatarCampoNumerico(this.getOrgao(),3));
        sb.replace(7,15,String.valueOf(StringUtil.formatarCampoNumerico(this.getMatricula(),9)));
        sb.replace(16,19, StringUtil.formatarCampoNumerico(this.getBeneficiario(),4));
        sb.replace(20,59, StringUtil.formatarCampoCaracter(this.getNome_curto(),40));
        sb.replace(60,67,String.valueOf(formatoDiaMesAno.format(this.getNascimento())));
        sb.replace(68,68,String.valueOf(StringUtil.formatarSexo(this.getSexo())));
        sb.replace(69,79,StringUtil.formatarCampoNumerico(this.getCpf(),11));
        sb.replace(80,119,StringUtil.formatarCampoCaracter(this.getNomePai() == null ? "" : this.getNomePai(), 40));
        sb.replace(120,159,StringUtil.formatarCampoCaracter(this.getNomeMae(),40));        
        sb.replace(160,174,StringUtil.formatarCampoNumerico(this.getRg(), 15));
        sb.replace(175,184,StringUtil.formatarCampoCaracter(this.getEmissorRg(),10));                
        sb.replace(185,190,StringUtil.formatarCampoCaracter(this.getEmissorUf(),2));
        sb.replace(191,235,StringUtil.formatarCampoCaracter(this.getEndereco().getDescricao(),45));
        sb.replace(236,265,StringUtil.formatarCampoCaracter(this.getEndereco().getBairro(),30));
        sb.replace(266,273,StringUtil.formatarCampoCaracter(this.getEndereco().getCep(),8));
        sb.replace(274,303,StringUtil.formatarCampoCaracter(this.getEndereco().getCidade(), 30));
        sb.replace(304,305,StringUtil.formatarCampoCaracter(this.getEndereco().getUf(),2));
        sb.replace(306,325,StringUtil.formatarCampoCaracter(this.getEndereco().getComplemento(),20));
        sb.replace(326,335,StringUtil.formatarCampoCaracter(this.getTelefone(),10));
        sb.replace(336,343,String.valueOf(formatoDiaMesAno.format(this.getAdmissao())));
        sb.replace(344,393,StringUtil.formatarCampoCaracter(this.getEmail() == null ? "" : this.getEmail(),50));
        sb.replace(394,401,StringUtil.formatarCampoCaracter(this.getUnidade() == null ? "": this.getUnidade(),8));
        sb.replace(402,406,StringUtil.formatarCampoCaracter(this.getMunicipio() == null ? "": this.getMunicipio() ,5));
        sb.replace(407,414,StringUtil.formatarCampoCaracter(this.getLocalTrabalho() == null ? "": this.getLocalTrabalho(),8));        
        sb.replace(415,424,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorLimiteCompras()==null ? 0:this.getValorLimiteCompras()),10) +"A");

        return sb.substring(0,426);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

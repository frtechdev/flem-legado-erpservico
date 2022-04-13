/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.negocio;

import br.org.flem.fwe.hibernate.dto.base.BaseDTOAb;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mccosta
 */
@Entity
@Table(name="Titular")
public class Titular extends BaseDTOAb {

    @Id
    private Integer Idt_Titular;

    private boolean Flg_Alt_20041104;
    private boolean Flg_Alt_20041125;
    private int Cod_Mat_Titular;
    private String Nom_Curto_Titular;
    private String Nom_Titular;
    private String Num_empresa;

    @Temporal(TemporalType.TIMESTAMP)
    private Date Dat_Admissao_Titular;

    private String Cod_Setor;
    private String Tip_Sexo_Titular;
    private String Tip_EstCivil_Titular;
    private String Cod_CPF_Titular;
    private String Cod_Banco_Titular;
    private String Cod_Agencia_Titular;
    private String Cod_Digito_Agencia_Titular;
    private String Cod_CC_Titular;
    private String Des_Endereco_Titular;
    private String Des_Logradouro_End;
    private String Des_Numero_End;
    private String Des_Complem_End;
    private String Nom_Bairro_End;
    private String Nom_Cidade_End;
    private String Cod_CEP_Titular;

    @Temporal(TemporalType.TIMESTAMP)
    private Date Dat_Nasc_Titular;

    private String Tip_Opcao;
    private String Tip_Plano;

    @Temporal(TemporalType.TIMESTAMP)
    private Date Dat_Casamento_Titular;

    private boolean tip_processado;
    private boolean tip_alteracao;
    private boolean tip_exclusao_titular;
    private String dat_opcao_plano;
    private boolean tip_ja_excluido;
    private boolean tip_temp;
    private String tip_operacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dat_operacao;

    private String tip_situacao_titular;
    private String cod_matricula;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dat_beneficio_ini;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dat_beneficio_fim;
    
    private Integer flg_emite_carteira;
    private String num_tel_titular;



    @Override
    public Serializable getPk() {
        return this.getIdt_Titular();
    }

    public String getCod_Agencia_Titular() {
        return Cod_Agencia_Titular;
    }

    public void setCod_Agencia_Titular(String Cod_Agencia_Titular) {
        this.Cod_Agencia_Titular = Cod_Agencia_Titular;
    }

    public String getCod_Banco_Titular() {
        return Cod_Banco_Titular;
    }

    public void setCod_Banco_Titular(String Cod_Banco_Titular) {
        this.Cod_Banco_Titular = Cod_Banco_Titular;
    }

    public String getCod_CC_Titular() {
        return Cod_CC_Titular;
    }

    public void setCod_CC_Titular(String Cod_CC_Titular) {
        this.Cod_CC_Titular = Cod_CC_Titular;
    }

    public String getCod_CEP_Titular() {
        return Cod_CEP_Titular;
    }

    public void setCod_CEP_Titular(String Cod_CEP_Titular) {
        this.Cod_CEP_Titular = Cod_CEP_Titular;
    }

    public String getCod_CPF_Titular() {
        return Cod_CPF_Titular;
    }

    public void setCod_CPF_Titular(String Cod_CPF_Titular) {
        this.Cod_CPF_Titular = Cod_CPF_Titular;
    }

    public String getCod_Digito_Agencia_Titular() {
        return Cod_Digito_Agencia_Titular;
    }

    public void setCod_Digito_Agencia_Titular(String Cod_Digito_Agencia_Titular) {
        this.Cod_Digito_Agencia_Titular = Cod_Digito_Agencia_Titular;
    }

    public int getCod_Mat_Titular() {
        return Cod_Mat_Titular;
    }

    public void setCod_Mat_Titular(int Cod_Mat_Titular) {
        this.Cod_Mat_Titular = Cod_Mat_Titular;
    }

    public String getCod_Setor() {
        return Cod_Setor;
    }

    public void setCod_Setor(String Cod_Setor) {
        this.Cod_Setor = Cod_Setor;
    }

    public Date getDat_Admissao_Titular() {
        return Dat_Admissao_Titular;
    }

    public void setDat_Admissao_Titular(Date Dat_Admissao_Titular) {
        this.Dat_Admissao_Titular = Dat_Admissao_Titular;
    }

    public Date getDat_Casamento_Titular() {
        return Dat_Casamento_Titular;
    }

    public void setDat_Casamento_Titular(Date Dat_Casamento_Titular) {
        this.Dat_Casamento_Titular = Dat_Casamento_Titular;
    }

    public Date getDat_Nasc_Titular() {
        return Dat_Nasc_Titular;
    }

    public void setDat_Nasc_Titular(Date Dat_Nasc_Titular) {
        this.Dat_Nasc_Titular = Dat_Nasc_Titular;
    }

    public String getDes_Complem_End() {
        return Des_Complem_End;
    }

    public void setDes_Complem_End(String Des_Complem_End) {
        this.Des_Complem_End = Des_Complem_End;
    }

    public String getDes_Endereco_Titular() {
        return Des_Endereco_Titular;
    }

    public void setDes_Endereco_Titular(String Des_Endereco_Titular) {
        this.Des_Endereco_Titular = Des_Endereco_Titular;
    }

    public String getDes_Logradouro_End() {
        return Des_Logradouro_End;
    }

    public void setDes_Logradouro_End(String Des_Logradouro_End) {
        this.Des_Logradouro_End = Des_Logradouro_End;
    }

    public String getDes_Numero_End() {
        return Des_Numero_End;
    }

    public void setDes_Numero_End(String Des_Numero_End) {
        this.Des_Numero_End = Des_Numero_End;
    }

    public boolean isFlg_Alt_20041104() {
        return Flg_Alt_20041104;
    }

    public void setFlg_Alt_20041104(boolean Flg_Alt_20041104) {
        this.Flg_Alt_20041104 = Flg_Alt_20041104;
    }

    public boolean isFlg_Alt_20041125() {
        return Flg_Alt_20041125;
    }

    public void setFlg_Alt_20041125(boolean Flg_Alt_20041125) {
        this.Flg_Alt_20041125 = Flg_Alt_20041125;
    }

    public Integer getIdt_Titular() {
        return Idt_Titular;
    }

    public void setIdt_Titular(Integer Idt_Titular) {
        this.Idt_Titular = Idt_Titular;
    }

    public String getNom_Bairro_End() {
        return Nom_Bairro_End;
    }

    public void setNom_Bairro_End(String Nom_Bairro_End) {
        this.Nom_Bairro_End = Nom_Bairro_End;
    }

    public String getNom_Cidade_End() {
        return Nom_Cidade_End;
    }

    public void setNom_Cidade_End(String Nom_Cidade_End) {
        this.Nom_Cidade_End = Nom_Cidade_End;
    }

    public String getNom_Curto_Titular() {
        return Nom_Curto_Titular;
    }

    public void setNom_Curto_Titular(String Nom_Curto_Titular) {
        this.Nom_Curto_Titular = Nom_Curto_Titular;
    }

    public String getNom_Titular() {
        return Nom_Titular;
    }

    public void setNom_Titular(String Nom_Titular) {
        this.Nom_Titular = Nom_Titular;
    }

    public String getNum_empresa() {
        return Num_empresa;
    }

    public void setNum_empresa(String Num_empresa) {
        this.Num_empresa = Num_empresa;
    }

    public String getTip_EstCivil_Titular() {
        return Tip_EstCivil_Titular;
    }

    public void setTip_EstCivil_Titular(String Tip_EstCivil_Titular) {
        this.Tip_EstCivil_Titular = Tip_EstCivil_Titular;
    }

    public String getTip_Opcao() {
        return Tip_Opcao;
    }

    public void setTip_Opcao(String Tip_Opcao) {
        this.Tip_Opcao = Tip_Opcao;
    }

    public String getTip_Plano() {
        return Tip_Plano;
    }

    public void setTip_Plano(String Tip_Plano) {
        this.Tip_Plano = Tip_Plano;
    }

    public String getTip_Sexo_Titular() {
        return Tip_Sexo_Titular;
    }

    public void setTip_Sexo_Titular(String Tip_Sexo_Titular) {
        this.Tip_Sexo_Titular = Tip_Sexo_Titular;
    }

    public String getCod_matricula() {
        return cod_matricula;
    }

    public void setCod_matricula(String cod_matricula) {
        this.cod_matricula = cod_matricula;
    }

    public Date getDat_beneficio_fim() {
        return dat_beneficio_fim;
    }

    public void setDat_beneficio_fim(Date dat_beneficio_fim) {
        this.dat_beneficio_fim = dat_beneficio_fim;
    }

    public Date getDat_beneficio_ini() {
        return dat_beneficio_ini;
    }

    public void setDat_beneficio_ini(Date dat_beneficio_ini) {
        this.dat_beneficio_ini = dat_beneficio_ini;
    }

    public String getDat_opcao_plano() {
        return dat_opcao_plano;
    }

    public void setDat_opcao_plano(String dat_opcao_plano) {
        this.dat_opcao_plano = dat_opcao_plano;
    }

    public Date getDat_operacao() {
        return dat_operacao;
    }

    public void setDat_operacao(Date dat_operacao) {
        this.dat_operacao = dat_operacao;
    }

    public Integer getFlg_emite_carteira() {
        return flg_emite_carteira;
    }

    public void setFlg_emite_carteira(Integer flg_emite_carteira) {
        this.flg_emite_carteira = flg_emite_carteira;
    }

    public String getNum_tel_titular() {
        return num_tel_titular;
    }

    public void setNum_tel_titular(String num_tel_titular) {
        this.num_tel_titular = num_tel_titular;
    }

    public boolean isTip_alteracao() {
        return tip_alteracao;
    }

    public void setTip_alteracao(boolean tip_alteracao) {
        this.tip_alteracao = tip_alteracao;
    }

    public boolean isTip_exclusao_titular() {
        return tip_exclusao_titular;
    }

    public void setTip_exclusao_titular(boolean tip_exclusao_titular) {
        this.tip_exclusao_titular = tip_exclusao_titular;
    }

    public boolean isTip_ja_excluido() {
        return tip_ja_excluido;
    }

    public void setTip_ja_excluido(boolean tip_ja_excluido) {
        this.tip_ja_excluido = tip_ja_excluido;
    }

    public String getTip_operacao() {
        return tip_operacao;
    }

    public void setTip_operacao(String tip_operacao) {
        this.tip_operacao = tip_operacao;
    }

    public boolean isTip_processado() {
        return tip_processado;
    }

    public void setTip_processado(boolean tip_processado) {
        this.tip_processado = tip_processado;
    }

    public String getTip_situacao_titular() {
        return tip_situacao_titular;
    }

    public void setTip_situacao_titular(String tip_situacao_titular) {
        this.tip_situacao_titular = tip_situacao_titular;
    }

    public boolean isTip_temp() {
        return tip_temp;
    }

    public void setTip_temp(boolean tip_temp) {
        this.tip_temp = tip_temp;
    }
}

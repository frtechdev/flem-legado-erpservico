package br.org.flem.sac.layout.rpa;

import br.org.flem.sac.arquivo.Layout;
import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.util.IOUtil;
import java.util.List;

/**
 *
 * @author MCCosta
 */
public class RegistroHeaderHPAY implements Layout{
    // Linha 02 - (HPAY) Header - Contas a Pagar
    private String companhiaCP = "040";       //A 03 1 - 3 Fixo "040"
    private String grupoFornecedorCP;         //A 04 4 - 7
    private String centroResponsabilidadeCP;  //A 12 8 - 19
    private String centroPagamentoCP;         //A 03 20 - 22
    private String metodoPagtoCP;             //A 03 23 - 25
    private String condicaoPagtoCP;           //A 03 26 - 28 Se não informada será igual a definida para a Conta Fornecedor
    private String descricaoUsuario1CP;       //A 60 29 - 88
    private String descricaoUsuario2CP;       //A 60 89 - 148 Opcional
    private String descricaoUsuario3CP;       //A 60 149 - 208
    private String bancoDepositoCP;           //A 04 209 - 212
    private String agenciaDepositoCP;         //A 05 213 - 217
    private String contaDepositoCP;           //A 20 218 - 237
    
    public RegistroHeaderHPAY() {
    
    }

    public String getAgenciaDepositoCP() {
        return agenciaDepositoCP;
    }

    public void setAgenciaDepositoCP(String agenciaDepositoCP) {
        this.agenciaDepositoCP = agenciaDepositoCP;
    }

    public String getBancoDepositoCP() {
        return bancoDepositoCP;
    }

    public void setBancoDepositoCP(String bancoDepositoCP) {
        this.bancoDepositoCP = bancoDepositoCP;
    }

    public String getCentroPagamentoCP() {
        return centroPagamentoCP;
    }

    public void setCentroPagamentoCP(String centroPagamentoCP) {
        this.centroPagamentoCP = centroPagamentoCP;
    }

    public String getCentroResponsabilidadeCP() {
        return centroResponsabilidadeCP;
    }

    public void setCentroResponsabilidadeCP(String centroResponsabilidadeCP) {
        this.centroResponsabilidadeCP = centroResponsabilidadeCP;
    }

    public String getCompanhiaCP() {
        return companhiaCP;
    }

    public void setCompanhiaCP(String companhiaCP) {
        this.companhiaCP = companhiaCP;
    }

    public String getCondicaoPagtoCP() {
        return condicaoPagtoCP;
    }

    public void setCondicaoPagtoCP(String condicaoPagtoCP) {
        this.condicaoPagtoCP = condicaoPagtoCP;
    }

    public String getContaDepositoCP() {
        return contaDepositoCP;
    }

    public void setContaDepositoCP(String contaDepositoCP) {
        this.contaDepositoCP = contaDepositoCP;
    }

    public String getDescricaoUsuario1CP() {
        return descricaoUsuario1CP;
    }

    public void setDescricaoUsuario1CP(String descricaoUsuario1CP) {
        this.descricaoUsuario1CP = descricaoUsuario1CP;
    }

    public String getDescricaoUsuario2CP() {
        return descricaoUsuario2CP;
    }

    public void setDescricaoUsuario2CP(String descricaoUsuario2CP) {
        this.descricaoUsuario2CP = descricaoUsuario2CP;
    }

    public String getDescricaoUsuario3CP() {
        return descricaoUsuario3CP;
    }

    public void setDescricaoUsuario3CP(String descricaoUsuario3CP) {
        this.descricaoUsuario3CP = descricaoUsuario3CP;
    }

    public String getGrupoFornecedorCP() {
        return grupoFornecedorCP;
    }

    public void setGrupoFornecedorCP(String grupoFornecedorCP) {
        this.grupoFornecedorCP = grupoFornecedorCP;
    }

    public String getMetodoPagtoCP() {
        return metodoPagtoCP;
    }

    public void setMetodoPagtoCP(String metodoPagtoCP) {
        this.metodoPagtoCP = metodoPagtoCP;
    }
    
    public String toLayout() {
        StringBuffer linha = IOUtil.linhaVazia(237);
        
        linha.replace(0,2,String.valueOf(this.getCompanhiaCP()));
        linha.replace(3,6,StringUtil.formatarCampoCaracter(this.getGrupoFornecedorCP(),4));
        linha.replace(7,18,StringUtil.formatarCampoCaracter(this.getCentroResponsabilidadeCP(),12));
        linha.replace(19,21,StringUtil.formatarCampoCaracter(this.getCentroPagamentoCP(),3));
        linha.replace(22,24,StringUtil.formatarCampoCaracter(this.getMetodoPagtoCP(),3));
        linha.replace(25,27,StringUtil.formatarCampoCaracter(this.getCondicaoPagtoCP(),3));
        linha.replace(28,87,StringUtil.formatarCampoCaracter(this.getDescricaoUsuario1CP(),60));
        linha.replace(88,147,StringUtil.formatarCampoCaracter(this.getDescricaoUsuario2CP(),60));
        linha.replace(148,207,StringUtil.formatarCampoCaracter(this.getDescricaoUsuario3CP(),60));
        linha.replace(208,211,StringUtil.formatarCampoCaracter(this.getBancoDepositoCP(),4));
        linha.replace(212,216,StringUtil.formatarCampoCaracter(this.getAgenciaDepositoCP(),5));
        linha.replace(217,236,StringUtil.formatarCampoCaracter(this.getContaDepositoCP(),20));
        
        return linha.substring(0,236);
        
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

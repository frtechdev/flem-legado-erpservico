/*
 * AutonomoDTO.java
 *
 * Created on 24 de Outubro de 2006, 15:42
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
 *
 * @author mjpereira
 */
public class AutonomoDTO implements Layout,Serializable{
    
    /**
     * FIXO
     */
    private String codEmpresa="FLE";
    private String codCategoria="13";
    private String cbo="233225";
    private String cnpjEmpresa="03037070000102";
    private String ativo="S";
    
    private IEntidade entidade;

    public IEntidade getEntidade() {
        return entidade;
    }

    public void setEntidade(IEntidade entidade) {
        this.entidade = entidade;
    }

    /** Creates a new instance of AutonomoDTO */
    public AutonomoDTO() {
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(String codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getCbo() {
        return cbo;
    }

    public void setCbo(String cbo) {
        this.cbo = cbo;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    
    /**
     * Layout:
     *
     *Arquivo Sequiencial.
     *Tamanho registro: 933
     *Descri��o dos campos (obrigatorios) preenchidos.
     *-----------------------------------------------------
     *CAMPO                : FORMATO : TAM : POS INI : POS FIM : DESCRICAO
     *CD_EMPRESA           :    A    :  3  :   01    :   03    : FIXO - FLEM
     *CD_AUTONOMO          :    A    :  6  :   04    :   09    : CODIGO DO AUTONO P...
     *CPF                  :    N    : 11  :   10    :   20    : CPF DO AUTONOMO
     *NM-ABREVIADO         :    A    : 30  :   21    :   50    : NOME ABREVIADO AUTONOMO
     *NM-EXTENSO           :    A    : 80  :   51    :  130    : NOME EXTENSO AUTONOMO
     *CD-CONTRIB_INDIVIDUAL:    A    : 11  :  656    :  666    : NUMERO INSS/PIS/PASEP
     *CD_CAT-TRABALHADOR   :    A    :  2  :  679    :  680    : FIXO - 13 (AUTONOMO COM CONTRIBUI��O SOBRE A REMUNERA��O
     *CBO                  :    A    : 06  :  681    :  686    :
     *CNPJ-EMP-CONTRATANTE :    A    : 14  :  717    :  730    : FIXO - CNPJ FLEM
     *IN-ATIVO             :    A    : 01  :  783    :  783    : FIXO - S
     */
    public String toLayout() {
            StringBuffer sb = IOUtil.linhaVazia(993);
            sb.replace(0,2,this.getCodEmpresa());
            sb.replace(3,8,this.getEntidade().getCodigo().substring(1));
            sb.replace(9,19,this.getEntidade().getCpf());
            String nomeAbrev = StringUtil.removeAcentosECaracteresEspecias(this.getEntidade().getNomeExtenso());
            String nomeExt = StringUtil.removeAcentosECaracteresEspecias(this.getEntidade().getNomeExtenso());
            sb.replace(20,49,StringUtil.formatarCampoCaracter(nomeAbrev,30));
            sb.replace(50,129,StringUtil.formatarCampoCaracter(nomeExt,80));
            sb.replace(655,665,this.getEntidade().getCodigoContribuicao());
            sb.replace(678,679,this.getCodCategoria());
            sb.replace(680,685,this.getCbo());
            sb.replace(716,729,this.getCnpjEmpresa());
            sb.replace(782,782,this.getAtivo());
            return sb.substring(0,993);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
      
     
      
}

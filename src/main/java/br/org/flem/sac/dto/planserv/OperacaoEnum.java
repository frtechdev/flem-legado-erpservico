package br.org.flem.sac.dto.planserv;

/**
 *
 * @author mjpereira
 */
public enum OperacaoEnum {
    INCUSAO(1,"Inclusão de Plano"),
    ALTERACAO_DADOS(2,"Alteração Dados Cadastrais"),
    ALTERACAO_PLANO(3,"Alteração de Plano"),
    EXCLUSAO_PLANO(4,"Excusão do Plano"),
    REATIVACAO_PLANO(5,"Reativação do Plano");

    private final int codigo;
    private final String descricao;
    

    OperacaoEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }
    
}

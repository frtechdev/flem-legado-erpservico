package br.org.flem.sac.dto.planserv;

/**
 *
 * @author mjpereira
 */
public enum OperacaoEnum {
    INCUSAO(1,"Inclus�o de Plano"),
    ALTERACAO_DADOS(2,"Altera��o Dados Cadastrais"),
    ALTERACAO_PLANO(3,"Altera��o de Plano"),
    EXCLUSAO_PLANO(4,"Excus�o do Plano"),
    REATIVACAO_PLANO(5,"Reativa��o do Plano");

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

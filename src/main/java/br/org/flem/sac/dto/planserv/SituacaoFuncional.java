package br.org.flem.sac.dto.planserv;

/**
 *
 * @author emsilva
 */
public enum SituacaoFuncional {
    FUNCIONARIO_EM_ATIVIDADE(1,"Funcionário em atividade"),
    APOSENTADO(2,"Aposentado"),
    PENSIONISTA_DE_SERVIDOR_DO_ESTADO(3,"Pensionista de servidor do estado"),
    ATIVO_SEM_DEPENDENTE(13,"Ativo sem dependente");//Funcionários do Primeiro Emprego

    private int codigo;
    private String descricao;

    SituacaoFuncional(int codigo,String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
package br.org.flem.sac.dto.planserv.validacaoFolha;

/**
 *
 * @author emsilva
 */
public enum TipoPagamentoPlanserv {

    PRINCIPAL(1,"Principal"),
    RETROATIVO(2,"Retroativo"),
    DEVOLUCAO(3,"Devolução"),
    COPARTICIPACAO(4,"Cooparticipação");
    
    private int codigo;
    private String descricao;
    
    private TipoPagamentoPlanserv(int codigo,String descricao){
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

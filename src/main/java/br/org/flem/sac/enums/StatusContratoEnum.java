
package br.org.flem.sac.enums;

/**
 * Descreva Sua Classe
 * Classe StatusContratoEnum
 * @author <code>tscortes@flem.org.br</code>
 * 15/02/2019
 * @version 1.0
 */
public enum StatusContratoEnum {
    VIGENTE(1,"Vigente"),
    CANCELADO(2,"Cancelado"),
    FINALIZADO(3,"Finalizado");

    private final int codigo;
    private final String descricao;

    private StatusContratoEnum(int codigo, String descricao) {
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

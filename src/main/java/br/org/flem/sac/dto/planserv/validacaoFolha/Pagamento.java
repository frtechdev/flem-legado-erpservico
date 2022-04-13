package br.org.flem.sac.dto.planserv.validacaoFolha;

/**
 *
 * @author emsilva
 */
public class Pagamento {
    private TipoPagamentoPlanserv tipoPagamento;
    private Double valor;
    
    public Pagamento(){}
    
    public Pagamento(TipoPagamentoPlanserv tipoPagamento,Double valor){
       this.tipoPagamento = tipoPagamento;
       this.valor = valor;
    }

    public TipoPagamentoPlanserv getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamentoPlanserv tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}

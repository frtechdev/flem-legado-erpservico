package br.org.flem.sac.dto.planserv.validacaoFolha;

import java.util.List;

/**
 *
 * @author emsilva
 */
public class Associado {
    private String numAssociado;
    private String grauDependencia;
    private List<Pagamento> pagamentos;

    public String getNumAssociado() {
        return numAssociado;
    }

    public void setNumAssociado(String numAssociado) {
        this.numAssociado = numAssociado;
    }

    public String getGrauDependencia() {
        return grauDependencia;
    }

    public void setGrauDependencia(String grauDependencia) {
        this.grauDependencia = grauDependencia;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }
    
}

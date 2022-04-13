package br.org.flem.sac.bo;

import br.org.flem.fwe.exception.AplicacaoException;
import br.org.flem.fwe.bo.BaseBOAb;
import br.org.flem.sac.negocio.Parcela;
import br.org.flem.sac.dao.ParcelaDAO;
import br.org.flem.sac.negocio.Contrato;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mccosta
 */
public class ParcelaBO extends BaseBOAb<Parcela> {

    public ParcelaBO() throws AplicacaoException {
        super(new ParcelaDAO());
    }

    public List<Parcela> obterPorContrato(Contrato contrato) {
        return ((ParcelaDAO) this.dao).obterPorContrato(contrato);
    }
    
    public BigDecimal obterTotalPorContrato(Contrato contrato) {
        return ((ParcelaDAO) this.dao).obterTotalPorContrato(contrato);
    }

    public static void main(String[] args) {
        BigDecimal teste = new BigDecimal("19580.85");
        System.out.println(teste);
    }
}

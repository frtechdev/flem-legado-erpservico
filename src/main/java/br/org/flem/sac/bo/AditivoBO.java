package br.org.flem.sac.bo;

import br.org.flem.fwe.exception.AplicacaoException;
import br.org.flem.fwe.bo.BaseBOAb;
import br.org.flem.sac.negocio.Aditivo;
import br.org.flem.sac.dao.AditivoDAO;
import br.org.flem.sac.negocio.Contrato;
import java.util.Collection;


/**
 *
 * @author mccosta
 */

public class AditivoBO extends BaseBOAb<Aditivo> {
    
    public AditivoBO() throws AplicacaoException {
        super(new AditivoDAO());
    }
    
    public Collection<Aditivo> obterPorContrato(Contrato contrato) {
        return ((AditivoDAO) this.dao).obterPorContrato(contrato);
    }
    
    public Aditivo obterUltimoPorContrato(Contrato contrato) {
        return ((AditivoDAO) this.dao).obterUltimoPorContrato(contrato);
    }
    
    public Double obterValorTotalAditivos(Contrato contrato){
        
        return ((AditivoDAO) this.dao).obterTotalPorContrato(contrato);
    }
}

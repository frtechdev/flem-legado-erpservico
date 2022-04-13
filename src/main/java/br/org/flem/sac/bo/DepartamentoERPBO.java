package br.org.flem.sac.bo;

import br.org.flem.fwe.bo.BaseBOAb;
import br.org.flem.fwe.exception.AplicacaoException;
import br.org.flem.sac.dao.DepartamentoERPDAO;
import br.org.flem.sac.negocio.DepartamentoERP;

/**
 *
 * @author mccosta
 */
public class DepartamentoERPBO extends BaseBOAb<DepartamentoERP>{

    public DepartamentoERPBO() throws AplicacaoException{
        super(new DepartamentoERPDAO());
    }

}

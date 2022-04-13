package br.org.flem.sac.dao;

import br.org.flem.fwe.exception.AcessoDadosException;
import br.org.flem.fwe.hibernate.dao.base.BaseDAOAb;
import br.org.flem.sac.negocio.DepartamentoERP;

/**
 *
 * @author mccosta
 */
public class DepartamentoERPDAO extends BaseDAOAb<DepartamentoERP>{

    public DepartamentoERPDAO() throws AcessoDadosException{

    }

    @Override
    protected Class<DepartamentoERP> getClasseDto() {
        return DepartamentoERP.class;
    }
}

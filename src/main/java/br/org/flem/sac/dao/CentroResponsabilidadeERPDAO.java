package br.org.flem.sac.dao;

import br.org.flem.fwe.exception.AcessoDadosException;
import br.org.flem.fwe.hibernate.dao.base.BaseDAOAb;
import br.org.flem.sac.negocio.CentroResponsabilidadeERP;

/**
 *
 * @author mccosta
 */
public class CentroResponsabilidadeERPDAO extends BaseDAOAb<CentroResponsabilidadeERP>{

    public CentroResponsabilidadeERPDAO() throws AcessoDadosException{

    }

    @Override
    protected Class<CentroResponsabilidadeERP> getClasseDto() {
        return CentroResponsabilidadeERP.class;
    }
}

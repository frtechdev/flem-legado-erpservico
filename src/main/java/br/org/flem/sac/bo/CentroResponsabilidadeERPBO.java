package br.org.flem.sac.bo;

import br.org.flem.fwe.bo.BaseBOAb;
import br.org.flem.fwe.exception.AplicacaoException;
import br.org.flem.sac.dao.CentroResponsabilidadeERPDAO;
import br.org.flem.sac.negocio.CentroResponsabilidadeERP;

/**
 *
 * @author mccosta
 */
public class CentroResponsabilidadeERPBO extends BaseBOAb<CentroResponsabilidadeERP>{

    public CentroResponsabilidadeERPBO() throws AplicacaoException{
        super(new CentroResponsabilidadeERPDAO());
    }
}

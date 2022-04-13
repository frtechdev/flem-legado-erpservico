package br.org.flem.sac.bo;

import br.org.flem.fwe.exception.AplicacaoException;
import br.org.flem.fwe.hibernate.dao.base.BaseDAOAb;
import br.org.flem.sac.dao.CaixaDAO;
import br.org.flem.sac.negocio.Caixa;
import br.org.flem.fwe.bo.BaseBOAb;

public class CaixaBO extends BaseBOAb<Caixa>
{
    public CaixaBO() throws AplicacaoException {
        super((BaseDAOAb)new CaixaDAO());
    }
}
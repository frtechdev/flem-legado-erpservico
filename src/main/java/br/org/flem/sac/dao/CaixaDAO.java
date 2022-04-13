package br.org.flem.sac.dao;

import br.org.flem.fwe.exception.AcessoDadosException;
import br.org.flem.sac.negocio.Caixa;
import br.org.flem.fwe.hibernate.dao.base.BaseDAOAb;

public class CaixaDAO extends BaseDAOAb<Caixa>
{
    public CaixaDAO() throws AcessoDadosException {
    }
    
    protected Class<Caixa> getClasseDto() {
        return Caixa.class;
    }
}
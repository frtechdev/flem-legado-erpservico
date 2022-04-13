package br.org.flem.sac.dao;

import br.org.flem.fwe.hibernate.dao.base.BaseDAOAb;
import br.org.flem.sac.negocio.ArquivosPlanserv;
import br.org.flem.fwe.exception.AcessoDadosException;
import java.util.List;

/**
 *
 * @author mccosta
 */
public class ArquivosPlanservDAO extends BaseDAOAb<ArquivosPlanserv>{

    public ArquivosPlanservDAO() throws AcessoDadosException{
    }

    protected Class<ArquivosPlanserv> getClasseDto() {
        return ArquivosPlanserv.class;
    }

    public List<ArquivosPlanserv> obterTodosOrdenado() throws AcessoDadosException {
        return session.createQuery("FROM ArquivosPlanserv a ORDER BY a.data, a.nome ASC").list();
    }

    public ArquivosPlanserv getUltimoArquivo(){
        return (ArquivosPlanserv) session.createQuery("FROM ArquivosPlanserv ORDER BY id DESC").setMaxResults(1).uniqueResult();
    }
}
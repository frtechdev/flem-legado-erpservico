package br.org.flem.sac.dao;

import br.org.flem.fwe.exception.AcessoDadosException;
import br.org.flem.fwe.hibernate.dao.base.BaseDAOAb;
import br.org.flem.sac.negocio.Aditivo;
import br.org.flem.sac.negocio.Contrato;
import java.util.Collection;

/**
 *
 * @author mccosta
 */
public class AditivoDAO extends BaseDAOAb<Aditivo> {

    public AditivoDAO() throws AcessoDadosException {
    }

    @Override
    protected Class<Aditivo> getClasseDto() {
        return Aditivo.class;
    }

    public Collection<Aditivo> obterPorContrato(Contrato contrato) {
        return session.createQuery("from Aditivo where contrato = :contrato").setEntity("contrato", contrato).list();
    }

    public Aditivo obterUltimoPorContrato(Contrato contrato) {
        return (Aditivo) session.createQuery("FROM Aditivo WHERE contrato = :contrato ORDER BY id DESC").setEntity("contrato", contrato).setMaxResults(1).uniqueResult();
    }
    
    public Double obterTotalPorContrato(Contrato contrato) {
        return (Double) session.createSQLQuery("SELECT SUM(valor) FROM Aditivo WHERE id_contrato = :contrato").setInteger("contrato", contrato.getId()).uniqueResult();
    }
}

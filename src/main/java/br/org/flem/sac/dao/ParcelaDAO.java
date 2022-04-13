/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.flem.sac.dao;

import br.org.flem.fwe.exception.AcessoDadosException;
import br.org.flem.fwe.hibernate.dao.base.BaseDAOAb;
import br.org.flem.sac.negocio.Contrato;
import br.org.flem.sac.negocio.Parcela;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;


/**
 *
 * @author mccosta
 */

public class ParcelaDAO extends BaseDAOAb<Parcela> {
    
    public ParcelaDAO() throws AcessoDadosException {
    }
    
    @Override
    protected Class<Parcela> getClasseDto() {
        return Parcela.class;
    }
    
    public List<Parcela> obterPorContrato(Contrato contrato) {
        return session.createQuery("from Parcela where contrato = :contrato ORDER BY dataPagamento desc ").setEntity("contrato", contrato).list();
    }
    
    public BigDecimal obterTotalPorContrato(Contrato contrato) {
        return (BigDecimal) session.createSQLQuery("SELECT SUM(valor) FROM Parcela WHERE id_contrato = :contrato").setInteger("contrato", contrato.getId()).uniqueResult();
    }
}

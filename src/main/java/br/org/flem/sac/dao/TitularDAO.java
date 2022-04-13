/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.dao;

import br.org.flem.fwe.exception.AcessoDadosException;
import br.org.flem.fwe.hibernate.dao.base.BaseDAOAb;
import br.org.flem.sac.negocio.Titular;
import java.util.Collection;

/**
 *
 * @author mccosta
 */
public class TitularDAO extends BaseDAOAb<Titular>{

    @Override
    protected Class<Titular> getClasseDto() {
        return Titular.class;
    }

    public TitularDAO() throws  AcessoDadosException{
    }

    public Collection<Titular> obterTitularAtivo() {
        return (Collection<Titular>)  session.createQuery("FROM Titular WHERE (tip_ja_excluido = 0) AND (tip_exclusao_titular = 0) ORDER BY Cod_Mat_Titular").list();
    }
    
    public Titular obterPorMatricula(Integer matricula) {
        return (Titular) session.createQuery("from Titular where Cod_Mat_Titular = :matricula").setInteger("matricula", matricula).setMaxResults(1).uniqueResult();
    }
}
/*
 * ArquivoCredicestaDAO.java
 * 
 * Created on 27/08/2007, 15:15:40
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.dao;

import br.org.flem.fwe.hibernate.dao.base.BaseDAOAb;
import br.org.flem.sac.negocio.ArquivoCredicesta;
import br.org.flem.fwe.exception.AcessoDadosException;
import java.util.List;
 
/**
 *
 * @author FCSilva
 */
public class ArquivoCredicestaDAO extends BaseDAOAb<ArquivoCredicesta>{

    public ArquivoCredicestaDAO() throws AcessoDadosException{
    }

    public List<ArquivoCredicesta> obterTodosOrdenado() throws AcessoDadosException {

        
        return session.createQuery("from ArquivoCredicesta a order by a.dataGravacao desc, a.nome asc").list();        
        //return session.createQuery("from ArquivoCredicesta a order by a.dataGravacao desc").list();        
        
    }

    protected Class<ArquivoCredicesta> getClasseDto() {
        return ArquivoCredicesta.class;
    }

}

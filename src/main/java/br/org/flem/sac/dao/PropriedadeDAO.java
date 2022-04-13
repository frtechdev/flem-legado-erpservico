/*
 * PropriedadeDAO.java
 * 
 * Created on 17/08/2007, 09:07:27
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.dao;

import br.org.flem.fwe.exception.AcessoDadosException;
import br.org.flem.fwe.hibernate.dao.base.BaseDAOAb;
import br.org.flem.sac.negocio.Propriedade;

/** 
 *
 * @author mjpereira
 */
public class PropriedadeDAO extends BaseDAOAb<Propriedade>{

    public static final String SEQUENCIAL_ARQUIVO_PLANSERV = "SEQUENCIAL_ARQUIVO_PLANSERV";
    
    public PropriedadeDAO() throws  AcessoDadosException{

    }

    protected Class<Propriedade> getClasseDto() {
        return Propriedade.class;
    }


}

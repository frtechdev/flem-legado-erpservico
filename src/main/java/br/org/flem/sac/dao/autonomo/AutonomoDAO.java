/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.dao.autonomo;

import br.org.flem.fwe.exception.AcessoDadosException;
import br.org.flem.fwe.hibernate.dao.base.BaseDAOAb;
import br.org.flem.sac.negocio.Autonomo;

/**
 * @author mjpereira
 */
public class AutonomoDAO extends BaseDAOAb<Autonomo>{

    public AutonomoDAO() throws AcessoDadosException {
    }


    @Override
    protected Class<Autonomo> getClasseDto() {
        return Autonomo.class;
    }

    public static void main(String[] args) throws AcessoDadosException {
        Autonomo aut = new Autonomo();
        aut.setAno(2013);
        aut.setMes(1);
        System.out.println(" "+new AutonomoDAO().obterPorFiltro(aut));
    }

}

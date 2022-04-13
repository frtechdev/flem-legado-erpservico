/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.bo;

import br.org.flem.fwe.bo.BaseBOAb;
import br.org.flem.fwe.exception.AcessoDadosException;
import br.org.flem.fwe.exception.AplicacaoException;
import br.org.flem.sac.dao.ArquivosPlanservDAO;
import br.org.flem.sac.negocio.ArquivosPlanserv;
import java.util.List;



/**
 *
 * @author mccosta
 */
public class ArquivosPlanservBO extends BaseBOAb<ArquivosPlanserv> {

    public ArquivosPlanservBO() throws AplicacaoException{
        super(new ArquivosPlanservDAO());
    }

    public List<ArquivosPlanserv> obterTodosOrdenado() throws AcessoDadosException{
        return ((ArquivosPlanservDAO) dao).obterTodosOrdenado();
    }

    public ArquivosPlanserv getUltimoArquivo(){
        return ((ArquivosPlanservDAO) dao).getUltimoArquivo();
    }
}

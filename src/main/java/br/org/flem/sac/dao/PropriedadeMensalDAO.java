package br.org.flem.sac.dao;

import br.org.flem.fwe.exception.AcessoDadosException;
import br.org.flem.fwe.hibernate.dao.base.BaseDAOAb;
import br.org.flem.sac.negocio.PropriedadeMensal;

/**
 *
 * @author mccosta
 */
public class PropriedadeMensalDAO extends BaseDAOAb<PropriedadeMensal>{

    public static final String SEQUENCIAL_ARQUIVO_MENSAL_PLANSERV = "SEQUENCIAL_ARQUIVO_MENSAL_PLANSERV";

    public PropriedadeMensalDAO() throws  AcessoDadosException{

    }

    protected Class<PropriedadeMensal> getClasseDto() {
        return PropriedadeMensal.class;
    }


}

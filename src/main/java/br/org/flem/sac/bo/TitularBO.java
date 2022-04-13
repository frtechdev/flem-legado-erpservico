package br.org.flem.sac.bo;

import br.org.flem.fwe.bo.BaseBOAb;
import br.org.flem.fwe.exception.AplicacaoException;
import br.org.flem.sac.dao.TitularDAO;
import br.org.flem.sac.dto.planserv.Beneficiario;
import br.org.flem.sac.negocio.Titular;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author mccosta
 */
public class TitularBO extends BaseBOAb<Titular>{

    public TitularBO() throws AplicacaoException{
        super(new TitularDAO());
    }

    public Collection<Titular> obterTitularAtivo(){
        return ((TitularDAO) dao).obterTitularAtivo();
    }

    public String obterIdsTitularAtivo(){ 
        String ids = "";
        Collection<Titular> titulares = ((TitularDAO) dao).obterTitularAtivo();

        for (Titular titular : titulares) {
            ids += titular.getCod_Mat_Titular()+",";
        }
        ids = ids.substring(0, ids.length()-1);

        return ids;
    }
    
    public Collection<Titular> obterTitularesAtivo(){ 
        return ((TitularDAO) dao).obterTitularAtivo();
    }

    public void inserir(Beneficiario b) throws AplicacaoException {
        Titular titular = new Titular();
        titular.setIdt_Titular(Integer.valueOf("10"+b.getMatricula()));
        titular.setCod_Mat_Titular(Integer.valueOf(b.getMatricula()));
        titular.setDat_operacao(new Date());
        titular.setDat_beneficio_ini(new Date());
        titular.setTip_ja_excluido(false);
        titular.setTip_exclusao_titular(false);
        this.inserir(titular);
    }
    
    public void excluir(Integer matricula) throws AplicacaoException {
        Titular titular = ((TitularDAO) dao).obterPorMatricula(matricula);
        if(titular != null){
            titular.setTip_ja_excluido(true);
            titular.setTip_exclusao_titular(true);
            titular.setDat_beneficio_fim(new Date());
            this.alterar(titular);
        }
    }
    
}

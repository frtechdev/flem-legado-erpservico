package br.org.flem.sac.bo;

import br.org.flem.fwe.exception.AcessoDadosException;
import br.org.flem.sac.dao.ContratoDAO;
import java.util.Collection;
import br.org.flem.sac.negocio.Contrato;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mccosta
 */
public class ContratoBO {

    ContratoDAO dao;

    public ContratoBO() throws AcessoDadosException {

        dao = new ContratoDAO();
    }

    public void inserirOuAlterar(Contrato objeto) throws AcessoDadosException {
        dao.inserirOuAlterar(objeto);
    }

    public void inserir(Contrato objeto) throws AcessoDadosException {
        if (obterPorNumero(objeto.getNumero()) != null) {
            throw new AcessoDadosException("Número de contrato já cadastrado");
        }
        dao.inserir(objeto);
    }

    public void alterar(Contrato objeto) throws AcessoDadosException {
        dao.alterar(objeto);
    }

    public void excluir(Contrato objeto) throws AcessoDadosException {
        dao.excluir(objeto);
    }

    public void excluir(Collection<Contrato> objetos) throws AcessoDadosException {
        dao.excluir(objetos);
    }

    public Contrato obterPorPk(Serializable objeto) throws AcessoDadosException {
        return dao.obterPorPk(objeto);
    }

    public Contrato obterPorPk(Integer id) throws AcessoDadosException {
        return dao.obterPorPk(id);
    }

    public Collection<Contrato> obterTodos() throws AcessoDadosException {
        return dao.obterTodos();
    }

    public Collection<Contrato> obterTodosOrdenadoPorCampo(String campo)
            throws AcessoDadosException {
        return dao.obterTodosOrdenadoPorCampo(campo);
    }

    public Collection<Contrato> obterPorFiltro(Contrato objeto) throws AcessoDadosException {
        return dao.obterPorFiltro(objeto);
    }

    public Contrato obterPorNumero(String numero) {
        return dao.obterPorNumero(numero);
    }

    public List<String> obterNumerosContratos() {
        return dao.obterNumerosContratos();
    }

    public Collection<Contrato> obterPorFiltro(String contrato, String nomeFornecedor, Date dataInicioVigencia, Date dataFimVigencia) throws AcessoDadosException {
        return dao.obterPorFiltro(contrato, nomeFornecedor, dataInicioVigencia, dataFimVigencia);
    }
}

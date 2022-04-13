package br.org.flem.sac.dao;

import br.org.flem.fwe.exception.AcessoDadosException;
import br.org.flem.fwe.hibernate.dao.base.BaseDAOAb;
import br.org.flem.sac.negocio.Aditivo;
import br.org.flem.sac.negocio.Contrato;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author mccosta
 */
public class ContratoDAO extends BaseDAOAb<Contrato> {

    public ContratoDAO() throws AcessoDadosException {
    }

    @Override
    protected Class getClasseDto() {
        return Contrato.class;
    }

    public Contrato obterPorNumero(String numero) {
        return (Contrato) session.createQuery("from Contrato c where c.numero = :numero").setString("numero", numero).uniqueResult();
    }

    public List<String> obterNumerosContratos() {
        return session.createQuery("select c.numero from Contrato c").list();
    }

    public Collection<Contrato> obterPorFiltro(String contrato, String nomeFornecedor, Date dataInicioVigencia, Date dataFimVigencia) {
        Criteria contratoCriteria = session.createCriteria(Contrato.class);
        if (contrato != null && !contrato.trim().equals("")) {
            contratoCriteria.add(Expression.eq("numero", contrato.trim()));
        }

        if (nomeFornecedor != null && !nomeFornecedor.trim().equals("")) {
            contratoCriteria.add(Expression.like("nomeFornecedor", "%" + nomeFornecedor.trim() + "%"));
        }
        if ((dataInicioVigencia != null && dataFimVigencia != null)) {
            Criteria aditivoCriteria = session.createCriteria(Aditivo.class);
            contratoCriteria = session.createCriteria(Contrato.class).createAlias("aditivos", "A", Criteria.LEFT_JOIN);
            Disjunction dis = Restrictions.disjunction();
            dis.add(Expression.between("fimVigencia", dataInicioVigencia, dataFimVigencia));
            dis.add(Expression.between("A.fimVigencia", dataInicioVigencia, dataFimVigencia));
            contratoCriteria.add(dis);

        }
        return contratoCriteria.list();
    }

    @Override
    protected void adicionarAgregacoesCriteria(Criteria c, Contrato objeto) {
    }
}

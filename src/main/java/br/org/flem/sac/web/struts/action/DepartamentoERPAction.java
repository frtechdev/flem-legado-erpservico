package br.org.flem.sac.web.struts.action;

import br.org.flem.fw.persistencia.dao.legado.gem.CentroCustoDAO;
import br.org.flem.fw.persistencia.dto.Departamento;
import br.org.flem.fw.service.impl.RHServico;
import br.org.flem.fwe.exception.AplicacaoException;
import br.org.flem.fwe.hibernate.util.HibernateUtil;
import br.org.flem.sac.bo.CentroResponsabilidadeERPBO;
import br.org.flem.sac.bo.DepartamentoERPBO;
import br.org.flem.sac.negocio.DepartamentoERP;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author mccosta
 */
public class DepartamentoERPAction extends DispatchAction{

    public DepartamentoERPAction(){}

    @Override
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("listaDepartamento", new DepartamentoERPBO().obterTodos());
        request.setAttribute("mapDepartamentoDominio", new RHServico().obterDepartamentosMapIndexCodigo());
        return mapping.findForward("lista");
    }

    public ActionForward novo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Map<String, Departamento> mapLista = new RHServico().obterDepartamentosMap();
            ArrayList<Departamento> departamentoList = new ArrayList<Departamento>();
            Collection<DepartamentoERP> depERP = new DepartamentoERPBO().obterTodos();
            boolean tem = false;

            for (Departamento d : mapLista.values()) {
                tem = false;
                for (DepartamentoERP dERP : depERP) {
                    if(d.getCodigoDominio().equals(dERP.getCod_lotacao_dominio())){
                        tem = true;
                    }
                }
                if(!tem){
                    departamentoList.add(d);
                }
            }

            Collections.sort(departamentoList, new Comparator<Departamento>() {
                public int compare(Departamento arg0, Departamento arg1) {
                    if (arg0.getNome().compareTo(arg1.getNome()) != 0) {
                        return arg0.getNome().compareTo(arg1.getNome());
                    } else {
                        return arg0.getNome().compareTo(arg1.getNome());
                    }
                }
            });
            request.setAttribute("listaDepartamento", departamentoList);
            request.setAttribute("listaCentRespHR", new CentroResponsabilidadeERPBO().obterTodos());
            request.setAttribute("listaCentCustoGEM", new CentroCustoDAO().obterCentroDeCusto());

        } catch (AplicacaoException ex) {
            Logger.getLogger(DepartamentoERPAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapping.findForward("novo");
    }

    public ActionForward selecionar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        String id = request.getParameter("id");

        try {
            if (GenericValidator.isInt(id)) {

                DepartamentoERP departamentoERP = new DepartamentoERPBO().obterPorPk(Integer.valueOf(id));

                dyna.set("departamento", departamentoERP);
                
                request.setAttribute("listaDepartamento",new RHServico().obterDepartamentos());
                request.setAttribute("dep", departamentoERP);
                request.setAttribute("listaCentRespHR", new CentroResponsabilidadeERPBO().obterTodos());
                request.setAttribute("listaCentCustoGEM", new CentroCustoDAO().obterCentroDeCusto());

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return mapping.findForward("editar");
    }

    public ActionForward adicionar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;

        try {
            DepartamentoERP departamentoERP = (DepartamentoERP) dyna.get("departamento");

            HibernateUtil.beginTransaction();
            new DepartamentoERPBO().inserir(departamentoERP);
            HibernateUtil.commitTransaction();
        }catch(Exception e){
            e.printStackTrace();
        }

        return mapping.findForward("redirect");
    }

    public ActionForward alterar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;

        try {
            DepartamentoERP departamentoERPform = (DepartamentoERP) dyna.get("departamento");
            DepartamentoERP departamentoERP = new DepartamentoERPBO().obterPorPk(departamentoERPform.getId());

            departamentoERP.setCod_ccusto(departamentoERPform.getCod_ccusto());
            departamentoERP.setCod_cresp(departamentoERPform.getCod_cresp());
            departamentoERP.setDes_competencia(departamentoERPform.getDes_competencia());
            departamentoERP.setNom_dept(departamentoERPform.getNom_dept());
            departamentoERP.setCod_lotacao_dominio(departamentoERPform.getCod_lotacao_dominio());
            departamentoERP.setCod_lotacao_hr(departamentoERPform.getCod_lotacao_hr());

            HibernateUtil.beginTransaction();
            new DepartamentoERPBO().alterar(departamentoERP);
            HibernateUtil.commitTransaction();
        }catch(Exception e){
            e.printStackTrace();
        }

        return mapping.findForward("redirect");
    }

}

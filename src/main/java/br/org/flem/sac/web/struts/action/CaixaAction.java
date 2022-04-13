/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.flem.sac.web.struts.action;

import br.org.flem.fw.persistencia.dto.Funcionario;
import br.org.flem.fw.service.impl.RHServico;
import br.org.flem.fwe.exception.AcessoDadosException;
import br.org.flem.fwe.exception.AplicacaoException;
import br.org.flem.fwe.hibernate.util.HibernateUtil;
import br.org.flem.fwe.web.util.MensagemTagUtil;
import br.org.flem.sac.arquivo.Arquivo;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.bo.CaixaBO;
import br.org.flem.sac.dao.CaixaDAO;
import br.org.flem.sac.layout.rpa.LayoutCaixa;
import br.org.flem.sac.negocio.Caixa;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
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
 * @author tscortes
 */
public class CaixaAction extends DispatchAction {

    public ActionForward unspecified(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws AplicacaoException {
        final String dataInicio = (String) request.getSession().getAttribute("filtro_dataInicio");
        final String dataFim = (String) request.getSession().getAttribute("filtro_dataFim");
        final Boolean gerarEmTela = request.getParameter("emTela") == null;
        final RHServico rh = new RHServico();
        final List<Funcionario> funcs = (List<Funcionario>) rh.obterFuncionariosAtivos();
        final List<Funcionario> retorno = new ArrayList<Funcionario>();
        try {
            final Date dtInicio = new SimpleDateFormat("dd/MM/yyyy").parse(dataInicio);
            final Date dtFim = new SimpleDateFormat("dd/MM/yyyy").parse(dataFim);
            if (gerarEmTela) {
                if (dataInicio != null || dataInicio != null || dataFim != null || dataFim != null) {
                    for (final Funcionario f : funcs) {
                        if ((f.getAdmissao().before(dtFim) || f.getAdmissao().equals(dtFim)) && (f.getAdmissao().after(dtInicio) || f.getAdmissao().equals(dtInicio))) {
                            retorno.add(f);
                        }
                    }
                }
            } else if (dataInicio != null || dataInicio != null || dataFim != null || dataFim != null) {
                for (final Funcionario f : funcs) {
                    final SimpleDateFormat formatoAnoMesDia = new SimpleDateFormat("ddMMyyyy");
                    final Caixa c = new Caixa();
                    if ((f.getAdmissao().before(dtFim) || f.getAdmissao().equals(dtFim)) && (f.getAdmissao().after(dtInicio) || f.getAdmissao().equals(dtInicio))) {
                        c.setNomeCompleto(f.getNome());
                        c.setNomeReduzido(f.getNome_curto());
                        c.setCpf(f.getCpf());
                        c.setPis(f.getPis());
                        c.setDataNascimento(formatoAnoMesDia.format(f.getNascimento()));
                        //c.setLocalNascimento(f.getEndereco().getCidade());
                        // Adicionado por Daniel Augusto Almeida em 11/03/2022
                    c.setLocalNascimento(f.getLocalNascimento());
                    System.out.println(f.getLocalNascimento());
                    // Adicionado por Daniel Augusto Almeida em 11/03/2022
                        c.setUf(f.getEndereco().getUf());
                        c.setEstadocivil("1");
                        c.setNomeConjugue("");
                        c.setCpfConjugue("00000000000");
                        c.setNomePai(f.getNomePai());
                        c.setNomeMae(f.getNomeMae());
                        c.setSexo((f.getSexo() == "F") ? "1" : "2");
                        c.setEspacamento2("00");
                        c.setTipoDocumento("1");
                        c.setDocumento(f.getRg());
                        c.setOrgaoEmissorDocumento(f.getEmissorRg());
                        c.setUfOrgaoEmissorDocumento(f.getEmissorUf());
                        c.setDataEmissaoDocumento("20012017");
                        c.setEspacamento10("0000000000000000");
                        c.setOcupacao("159");
                        c.setDataAdmissao(formatoAnoMesDia.format(f.getAdmissao()));
                        c.setTipoLogradouro("R");
                        c.setEndereco(f.getEndereco().getDescricao());
                        c.setNumeroEndereco(f.getEndereco().getNumero());
                        c.setBairro(f.getEndereco().getBairro());
                        c.setCidade(f.getEndereco().getCidade());
                        c.setUf(f.getEndereco().getUf());
                        c.setCep(f.getEndereco().getCep());
                        c.setDdd("071");
                        c.setTelefone("31037500");
                        c.setEmail(f.getEmail());
                        c.setGrauInstrucao("07");
                        c.setRendaBruta("0000000000000000000".substring(f.getNivel().getSalario().toString().length()) + f.getNivel().getSalario().toString().replace(".", "0"));
                        c.setCodBanco("000");
                        c.setAgencia("0000");
                        c.setOp("000");
                        c.setConta("00000");
                        c.setDigito("0");
                        new CaixaBO().inserir(c);
                    }
                }
            }
            request.setAttribute("listafuncionario", (Object) new CaixaDAO().obterTodos());
            request.setAttribute("lista", (Object) retorno);
        } catch (Exception ex) {
        }
        request.setAttribute("listafuncionario", (Object) new CaixaDAO().obterTodos());
        return mapping.findForward("lista");
    }

    public ActionForward filtrar(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final DynaActionForm dyna = (DynaActionForm) form;
        final String dataInicio = (String) dyna.get("data_Inicio");
        request.getSession().setAttribute("filtro_dataInicio", (Object) dataInicio);
        final Boolean gerarEmTela = (Boolean) dyna.get("emTela");
        request.getSession().setAttribute("filtro_emTela", (Object) gerarEmTela);
        final String dataFim = (String) dyna.get("data_Fim");
        request.getSession().setAttribute("filtro_dataFim", (Object) dataFim);
        return this.unspecified(mapping, form, request, response);
    }

    public List<Layout> geraArquivoMensal(final List<Funcionario> funcs, final Date dtInicio, final Date dtFim) {
        final SimpleDateFormat formatoAnoMesDia = new SimpleDateFormat("ddMMyyyy");
        final List<Layout> layoutList = new ArrayList<Layout>();
        for (final Funcionario f : funcs) {
            if (f.getAdmissao().before(dtFim) && f.getAdmissao().after(dtInicio)) {
                final LayoutCaixa layout = new LayoutCaixa();
                layout.setNomeCompleto(f.getNome());
                layout.setNomeReduzido(f.getNome_curto());
                layout.setCpf(f.getCpf());
                layout.setPis(f.getPis());
                layout.setDataNascimento(formatoAnoMesDia.format(f.getNascimento()));
                //layout.setLocalNascimento(f.getEndereco().getCidade());
                // Adicionado por Daniel Augusto Almeida em 11/03/2022
                    layout.setLocalNascimento(f.getLocalNascimento());
                    // Adicionado por Daniel Augusto Almeida em 11/03/2022
                layout.setUf(f.getEndereco().getUf());
                layout.setEstadocivil("1");
                layout.setNomeConjugue("");
                layout.setCpfConjugue("00000000000");
                layout.setNomePai(f.getNomePai());
                layout.setNomeMae(f.getNomeMae());
                layout.setSexo((f.getSexo().equalsIgnoreCase("F")) ? "1" : "2");
                layout.setEspacamento2("00");
                layout.setTipoDocumento("1");
                layout.setDocumento(f.getRg());
                layout.setOrgaoEmissorDocumento(f.getEmissorRg());
                layout.setUfOrgaoEmissorDocumento(f.getEmissorUf());
                layout.setDataEmissaoDocumento("20012017");
                layout.setEspacamento10("0000000000000000");
                layout.setOcupacao("159");
                layout.setDataAdmissao(formatoAnoMesDia.format(f.getAdmissao()));
                layout.setTipoLogradouro("R");
                layout.setEndereco(f.getEndereco().getDescricao());
                layout.setNumeroEndereco(f.getEndereco().getNumero());
                layout.setBairro(f.getEndereco().getBairro());
                layout.setCidade(f.getEndereco().getCidade());
                layout.setUf(f.getEndereco().getUf());
                layout.setCep(f.getEndereco().getCep());
                layout.setComplemento(f.getEndereco().getComplemento());
                layout.setDdd("071");
                layout.setTelefone("31037500");
                layout.setCelular(f.getCelular());
                layout.setEmail(f.getEmail());
                layout.setGrauInstrucao("07");
                layout.setRendaLiquida("0000000000000000000".substring(f.getNivel().getSalario().toString().length()) + f.getNivel().getSalario().toString().replace(".", "0"));
                layout.setRendaBruta("0000000000000000000".substring(f.getNivel().getSalario().toString().length()) + f.getNivel().getSalario().toString().replace(".", "0"));
                layout.setCodBanco("000".substring(0).length() + "000".toString().replace(".", "0"));
                layout.setAgencia("0000".substring(0).length() + "0000".toString().replace(".", "0"));
                layout.setOp("0000".substring(0).length() + "0000".toString().replace(".", "0"));
                layout.setConta("00000000".substring(0).length() + "00000000".toString().replace(".", "0"));
                layout.setDigito("00".substring(0).length() + "00".toString().replace(".", "0"));
                layoutList.add((Layout) layout);
            }
        }
        return layoutList;
    }

    public ActionForward listar(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        request.setAttribute("listafuncionario", (Object) new CaixaDAO().obterTodos());
        return mapping.findForward("area");
    }

    public ActionForward selecionar(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) {
        final DynaActionForm dyna = (DynaActionForm) form;
        final String id = request.getParameter("id");
        try {
            if (GenericValidator.isInt(id)) {
                final Caixa func = (Caixa) new CaixaBO().obterPorPk(Integer.valueOf(id));
                dyna.set("func", (Object) func);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("editar");
    }

    public ActionForward alterar(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) {
        final DynaActionForm dyna = (DynaActionForm) form;
        final Caixa funcBean = (Caixa) dyna.get("func");
        try {
            final Caixa f = (Caixa) new CaixaBO().obterPorPk(funcBean);
            f.setPis(funcBean.getPis());
            f.setNomeReduzido(funcBean.getNomeReduzido().toUpperCase());
            f.setOrgaoEmissorDocumento(funcBean.getOrgaoEmissorDocumento().toUpperCase());
            f.setCodBanco("000".substring(funcBean.getCodBanco().length()) + funcBean.getCodBanco());
            f.setConta("00000000".substring(funcBean.getConta().length()) + funcBean.getConta());
            f.setOp("0000".substring(funcBean.getOp().length()) + funcBean.getOp());
            f.setAgencia("0000".substring(funcBean.getAgencia().length()) + funcBean.getAgencia());
            f.setDigito(funcBean.getDigito());
            new CaixaBO().alterar(f);
            MensagemTagUtil.adicionarMensagem(request.getSession(), "Atualizado com Sucesso");
        } catch (Exception ex) {
            MensagemTagUtil.adicionarMensagem(request.getSession(), ex.getMessage(), "erro", this.getClass().getName(), (Throwable) ex);
            Logger.getLogger(ParcelaAction.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        request.setAttribute("acao", (Object) "Usuario.do");
        return mapping.findForward("redirect");
    }

    public ActionForward excluir(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws AplicacaoException {
        final ArrayList erros = new ArrayList();
        try {
            String[] id = new String[0];
            if (request.getParameterValues("ids_exclusao") != null) {
                id = request.getParameterValues("ids_exclusao");
            }
            HibernateUtil.beginTransaction();
            for (int i = 0; i < id.length; ++i) {
                final CaixaBO caixaBO = new CaixaBO();
                final Caixa caixa = (Caixa) caixaBO.obterPorPk(Integer.valueOf(id[i]));
                try {
                    caixaBO.excluir(caixa);
                } catch (Exception ex2) {
                    HibernateUtil.rollbackTransaction();
                    request.getSession().setAttribute("listaDeMensagens", (Object) erros);
                    break;
                }
            }
            HibernateUtil.commitTransaction();
        } catch (AcessoDadosException ex) {
            Logger.getLogger(ContratoAction.class.getName()).log(Level.SEVERE, null, (Throwable) ex);
        }
        if (erros.size() <= 0) {
            erros.add("Exclus\u00e3o realizada com sucesso!");
            request.getSession().setAttribute("listaDeMensagens", (Object) erros);
        }
        return mapping.findForward("lista");
    }

    public ActionForward gerarArquivo(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws AplicacaoException, IOException, Exception {
        
        final List<Layout> layoutList = new ArrayList<Layout>();
        final Collection<Caixa> funcs = (Collection<Caixa>) new CaixaBO().obterTodos();
        for (final Caixa f : funcs) {
            final LayoutCaixa layout = new LayoutCaixa();
            layout.setNomeCompleto(f.getNomeCompleto());
            layout.setNomeReduzido(f.getNomeReduzido());
            layout.setCpf(f.getCpf());
            layout.setPis(f.getPis());
            layout.setDataNascimento(f.getDataNascimento());
            //layout.setLocalNascimento(f.getCidade());
            // Adicionado por Daniel Augusto Almeida em 11/03/2022
            layout.setLocalNascimento(f.getLocalNascimento());
            System.out.println(f.getLocalNascimento());
            // Adicionado por Daniel Augusto Almeida em 11/03/2022
            layout.setUf(f.getUfNascimento());
            layout.setEstadocivil("1");
            layout.setNomeConjugue("");
            layout.setCpfConjugue("00000000000");
            layout.setNomePai(f.getNomePai());
            layout.setNomeMae(f.getNomeMae());
            layout.setSexo((f.getSexo() == "F") ? "1" : "2");
            layout.setEspacamento2("00");
            layout.setTipoDocumento("1");
            layout.setDocumento(f.getDocumento());
            layout.setOrgaoEmissorDocumento(f.getOrgaoEmissorDocumento());
            layout.setUfOrgaoEmissorDocumento(f.getUfOrgaoEmissorDocumento());
            layout.setDataEmissaoDocumento("20012017");
            layout.setEspacamento10("0000000000000000");
            layout.setOcupacao("159");
            layout.setDataAdmissao(f.getDataAdmissao());
            layout.setTipoLogradouro("R");
            layout.setEndereco(f.getEndereco());
            layout.setNumeroEndereco(f.getNumeroEndereco());
            layout.setBairro(f.getBairro());
            layout.setCidade(f.getCidade());
            layout.setUf(f.getUf());
            layout.setCep(f.getCep());
            layout.setComplemento(f.getComplemento());
            layout.setDdd("071");
            layout.setTelefone("031037500");
            layout.setEmail(f.getEmail());
            layout.setGrauInstrucao("07");
            layout.setRendaBruta(f.getRendaBruta());
            layout.setRendaLiquida(f.getRendaLiquida());
            layout.setCodBanco(f.getCodBanco());
            layout.setAgencia(f.getAgencia());
            layout.setOp(f.getOp());
            layout.setConta(f.getConta());
            layout.setDigito(f.getDigito());
            layoutList.add((Layout) layout);
        }
        response.setContentType("text/plain");
        final String nomeArquivo = "Arquivo";
        response.setHeader("Content-Disposition", "attachment; filename=" + nomeArquivo + ".txt");
        final ServletOutputStream outStream = response.getOutputStream();
        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter((OutputStream) outStream);
        final BufferedWriter bf = new BufferedWriter(outputStreamWriter);
        final Arquivo arquivo = new Arquivo();
        File file = arquivo.geraArquivo((List) layoutList);
        final byte[] arquivoCaixa = new Arquivo().lerArquivo(file);
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=Exportacao_Caixa_" + new SimpleDateFormat("ddMMyyyy").format(new Date()) + ".txt");
        outStream.write(arquivoCaixa, 0, arquivoCaixa.length);
        outStream.flush();
        outStream.close();
        return mapping.findForward("lista");
    }
}

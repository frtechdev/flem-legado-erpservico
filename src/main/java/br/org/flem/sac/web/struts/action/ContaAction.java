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
import br.org.flem.fwe.util.Abreviatura;
import br.org.flem.fwe.web.tag.MensagemTag;
import br.org.flem.fwe.web.util.MensagemTagUtil;
import br.org.flem.sac.arquivo.Arquivo;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.bo.CaixaBO;
import br.org.flem.sac.bo.ExcelCreator;
import br.org.flem.sac.dao.CaixaDAO;
import br.org.flem.sac.layout.rpa.GrauInstrucao;
import br.org.flem.sac.layout.rpa.LayoutCaixa;
import br.org.flem.sac.layout.rpa.OrgaoExp;
import br.org.flem.sac.negocio.Caixa;
import java.io.File;
import java.io.IOException;
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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author tscortes
 */
public class ContaAction extends DispatchAction {

    SimpleDateFormat sdf;

    public ContaAction() {
        this.sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public ActionForward unspecified(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final DynaActionForm dyna = (DynaActionForm) form;
        request.setAttribute("listafuncionario", (Object) new CaixaDAO().obterTodos());
        request.getSession().setAttribute("matriculas", (Object) new RHServico().obterFuncionariosAtivos());
        dyna.set("cod_banco", (Object) null);
        dyna.set("conta", (Object) null);
        dyna.set("agencia", (Object) null);
        dyna.set("digito", (Object) null);
        dyna.set("op", (Object) null);
        return mapping.findForward("inicio");
    }

    public ActionForward adicionar(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final DynaActionForm dyna = (DynaActionForm) form;
        final String banco = dyna.getString("cod_banco");
        final String conta = dyna.getString("conta");
        final String agencia = dyna.getString("agencia");
        final String digito = dyna.getString("digito");
        final String op = dyna.getString("op");
        try {
            final String matricula = dyna.getString("matricula").trim();
            if (matricula == null || matricula.isEmpty()) {
                MensagemTagUtil.adicionarMensagem(request.getSession(), "Selecione um Funcion\u00e1rio.");
                return (ActionForward) new ActionRedirect("Conta.do");
            }
            final RHServico rh = new RHServico();
            final Funcionario f = (Funcionario) rh.obterFuncionarioPorMatricula(Integer.valueOf(matricula));
            final Caixa c = new Caixa();
            final SimpleDateFormat formatoAnoMesDia = new SimpleDateFormat("ddMMyyyy");
            c.setNomeCompleto(f.getNome());
            c.setNomeReduzido(new Abreviatura().abreviarNome(f.getNome(), 30));
            c.setCpf(f.getCpf());
            c.setPis((f.getPis() == null) ? "" : f.getPis());
            c.setDataNascimento(formatoAnoMesDia.format(f.getNascimento()));
            //c.setLocalNascimento((f.getEndereco().getCidade() == null) ? "" : f.getEndereco().getCidade())
            // Adicionado por Daniel Augusto Almeida em 11/03/2022
                    c.setLocalNascimento(f.getLocalNascimento());
                    System.out.println(f.getLocalNascimento());
                    // Adicionado por Daniel Augusto Almeida em 11/03/2022
            c.setUf((f.getEndereco().getUf() == null) ? "" : f.getEndereco().getUf());
            c.setEstadocivil("1");
            c.setNomeConjugue("");
            c.setCpfConjugue("00000000000");
            c.setNomePai((f.getNomePai() == null) ? "" : f.getNomePai());
            c.setNomeMae((f.getNomeMae() == null) ? "" : f.getNomeMae());
            c.setSexo(("F".equals(f.getSexo())) ? "1" : "2");
            c.setEspacamento2("00");
            c.setTipoDocumento("1");
            c.setDocumento(f.getRg());
            c.setOrgaoEmissorDocumento(OrgaoExp.obterPorIdDominio(f.getIdEmissorRg().toString()).getOrgExpcaixa());
            c.setUfOrgaoEmissorDocumento((f.getEmissorUf() == null) ? "" : f.getEmissorUf());
            c.setDataEmissaoDocumento(formatoAnoMesDia.format(f.getDataExpIdentidade()));
            c.setUfNascimento(f.getEmissorUf());
            c.setEspacamento10("0000000000000000");
            c.setOcupacao("159");
            c.setDataAdmissao(formatoAnoMesDia.format(f.getAdmissao()));
            c.setTipoLogradouro("R");
            if (f.getEndereco().getDescricao().length() > 23) {
                c.setEndereco((f.getEndereco().getDescricao() == null) ? "" : f.getEndereco().getDescricao().substring(0, 23));
            } else {
                c.setEndereco((f.getEndereco().getDescricao() == null) ? "" : f.getEndereco().getDescricao());
            }
            c.setNumeroEndereco((f.getEndereco().getNumero() == null) ? "" : f.getEndereco().getNumero());
            c.setBairro((f.getEndereco().getBairro() == null) ? "" : f.getEndereco().getBairro());
            c.setCidade((f.getEndereco().getCidadeResidencia() == null) ? "" : f.getEndereco().getCidadeResidencia());
            c.setUf((f.getEndereco().getUf() == null) ? "" : f.getEndereco().getUf());
            c.setCep((f.getEndereco().getCep() == null) ? "" : f.getEndereco().getCep());
            c.setComplemento((f.getEndereco().getComplemento() == null) ? "" : f.getEndereco().getComplemento());
            c.setDdd("071");
            c.setTelefone("31037500");
            c.setCelular((f.getCelular() == null) ? "" : f.getCelular());
            c.setEmail((f.getEmail() == null) ? "" : f.getEmail());
            c.setGrauInstrucao(GrauInstrucao.obterPorIdDominio(f.getEscolaridade()).getIdCaixa());
            c.setRendaLiquida("0000000000000000000".substring(f.getNivel().getSalario().toString().length()) + f.getNivel().getSalario().toString().replace(".", "0"));
            c.setRendaBruta("0000000000000000000".substring(f.getNivel().getSalario().toString().length()) + f.getNivel().getSalario().toString().replace(".", "0"));
            c.setCodBanco("000".substring(banco.length()) + banco);
            c.setAgencia("0000".substring(agencia.length()) + agencia);
            c.setOp("0000".substring(op.length()) + op);
            c.setConta("0000000000000".substring(conta.length()) + conta);
            c.setDigito("0".substring(digito.length()) + digito);
            new CaixaBO().inserir(c);
        } catch (Exception e) {
            final List<String> mensagens = new ArrayList<String>();
            mensagens.add("Erro ao adicionar funcionario na lista");
            request.getSession().setAttribute("listaDeMensagens", (Object) mensagens);
            e.printStackTrace();
        }
        this.unspecified(mapping, form, request, response);
        return mapping.findForward("inicio");
    }

    public ActionForward alterar(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) {
        final DynaActionForm dyna = (DynaActionForm) form;
        final Caixa funcBean = (Caixa) dyna.get("func");
        try {
            final Caixa f = new CaixaBO().obterPorPk(funcBean);
            f.setPis(funcBean.getPis());
            f.setNomeReduzido(funcBean.getNomeReduzido().toUpperCase());
            f.setOrgaoEmissorDocumento(funcBean.getOrgaoEmissorDocumento().toUpperCase());
            f.setUf(funcBean.getUfNascimento().toUpperCase());
            f.setCodBanco("000".substring(funcBean.getCodBanco().length()) + funcBean.getCodBanco());
            f.setConta("0000000000000".substring(funcBean.getConta().length()) + funcBean.getConta());
            f.setOp("0000".substring(funcBean.getOp().length()) + funcBean.getOp());
            f.setAgencia("0000".substring(funcBean.getAgencia().length()) + funcBean.getAgencia());
            f.setDigito(funcBean.getDigito());
            new CaixaBO().alterar(f);
            MensagemTagUtil.adicionarMensagem(request.getSession(), "Atualizado com Sucesso");
        } catch (Exception ex) {
            MensagemTagUtil.adicionarMensagem(request.getSession(), ex.getMessage(), "erro", this.getClass().getName(), (Throwable) ex);
            Logger.getLogger(ParcelaAction.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        request.setAttribute("acao", (Object) "Conta.do");
        return mapping.findForward("redirect");
    }

    public ActionForward excluir(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws AplicacaoException, Exception {
        String[] ids = new String[1];
        ArrayList mensagens = new ArrayList();
        if (request.getParameter("id") != null) {
            ids[0] = request.getParameter("id");
        } else {
            ids = request.getParameterValues("ids_exclusao");
        }
        try {
            if (ids.length > 0) {
                for (String id : ids) {
                    Caixa caixa = new CaixaBO().obterPorPk(Integer.parseInt(id));
                    if( caixa != null ){
                        new CaixaBO().excluir(caixa);
                    }
                }
                if( ids.length > 1){
                    mensagens.add("Contas selecionadas foram excluídas com sucesso!");
                }else{
                    mensagens.add("Conta selecionada foi excluída com sucesso!");
                }
            }
            request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS, mensagens);
        } catch (AcessoDadosException ex) {
            MensagemTagUtil.adicionarMensagem(request.getSession(), ex.getMessage(), "erro ao excluir", this.getClass().getName(), (Throwable) ex);
            request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS, (Object) ex);
        }
        this.unspecified(mapping, form, request, response);
        return mapping.findForward("inicio");
    }

    public ActionForward gerarArquivo(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws AplicacaoException, IOException, Exception {
        final List<Layout> layoutList = new ArrayList<Layout>();
        final Collection<Caixa> funcs = (Collection<Caixa>) new CaixaBO().obterTodos();
        for (final Caixa f : funcs) {
            final LayoutCaixa layout = new LayoutCaixa();
            layout.setNomeCompleto(f.getNomeCompleto());
            layout.setNomeReduzido(new Abreviatura().abreviarNome(f.getNomeCompleto(), 30));
            layout.setCpf(f.getCpf());
            layout.setPis(f.getPis());
            layout.setDataNascimento(f.getDataNascimento());
            layout.setLocalNascimento(f.getLocalNascimento());
            layout.setUf(f.getUfNascimento());
            layout.setEstadocivil("1");
            layout.setNomeConjugue("");
            layout.setCpfConjugue("00000000000");
            layout.setNomePai(f.getNomePai());
            layout.setNomeMae(f.getNomeMae());
            layout.setSexo(("F".equals(f.getSexo())) ? "2" : "1");
            layout.setEspacamento2("00");
            layout.setTipoDocumento("1");
            layout.setDocumento(f.getDocumento());
            layout.setOrgaoEmissorDocumento(f.getOrgaoEmissorDocumento());
            layout.setUfOrgaoEmissorDocumento(f.getUfOrgaoEmissorDocumento());
            layout.setDataEmissaoDocumento(f.getDataEmissaoDocumento());
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
            layout.setCelular(f.getCelular());
            layout.setEmail(f.getEmail());
            layout.setGrauInstrucao(f.getGrauInstrucao());
            layout.setRendaLiquida(f.getRendaLiquida());
            layout.setRendaBruta(f.getRendaBruta());
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
        final Arquivo arquivo = new Arquivo();
        File file;
        file = arquivo.geraArquivo((List) layoutList);
        final byte[] arquivoCaixa = new Arquivo().lerArquivo(file);
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=Exportacao_Caixa_" + new SimpleDateFormat("ddMMyyyy").format(new Date()) + ".txt");
        outStream.write(arquivoCaixa, 0, arquivoCaixa.length);
        outStream.flush();
        outStream.close();
        return mapping.findForward("lista");
    }

    public ActionForward gerarArquivoXLSX(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws AplicacaoException, IOException, Exception {
        final Collection<Caixa> funcs = (Collection<Caixa>) new CaixaBO().obterTodos();
        List<String[]> contentList = new ArrayList<String[]>();
        String[] headers = new String[]{"Nome Completo", "Nome Reduzido", "CPF", "PIS", "Data de Nascimento",
            "Local de Nascimento", "UF", "Estado Civil", "Nome Conjugue", "CPF Conjugue", "Nome de Pai",
            "Nome da Mãe", "Sexo", "DOC - Tipo de Documento", "DOC - Número", "DOC - Orgão Emissor",
            "DOC - UF Orgão Emissor", "DOC - Data de Emissão", "Ocupação",
            "Data de Admissão", "Rua", "Endereço", "Número", "Bairro", "Cidade", "UF", "CEP", "Complemento", "DDD", "Telefone",
            "Telefone Celular", "E-mail", "Grau de Instrução", "Renda Bruta", "Renda Líquida",
            "Conta Destino - Banco", "Conta Destino - Agência", "Conta Destino - OP", "Conta Destino - Conta", "Conta Destino - DV"};
        ExcelCreator exporterExcel = new ExcelCreator();
        for (final Caixa f : funcs) {
            System.out.println("Local nascimento: " + f.getLocalNascimento());
            String[] content = new String[]{f.getNomeCompleto(), new Abreviatura().abreviarNome(f.getNomeCompleto(), 30),
                f.getCpf(),
                f.getPis(),
                f.getDataNascimento(),
                f.getLocalNascimento(),
                f.getUfNascimento(),
                "1",
                "",
                "00000000000",
                f.getNomePai(),
                f.getNomeMae(),
                f.getSexo(),
                "1",
                f.getDocumento(),
                f.getOrgaoEmissorDocumento(),
                f.getUfOrgaoEmissorDocumento(),
                f.getDataEmissaoDocumento(),
                "159",
                f.getDataAdmissao(),
                "R",
                f.getEndereco(),
                f.getNumeroEndereco(),
                f.getBairro(),
                f.getCidade(),
                f.getUf(),
                f.getCep(),
                f.getComplemento(),
                "071",
                "031037500",
                f.getCelular(),
                f.getEmail(),
                f.getGrauInstrucao(),
                f.getRendaLiquida(),
                f.getRendaBruta(),
                f.getCodBanco(),
                f.getAgencia(),
                f.getOp(),
                f.getConta(),
                f.getDigito()};
            contentList.add(content);
        }
        HSSFWorkbook workbook = exporterExcel.createWorkbook(contentList, headers);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=Exportacao_Caixa.xls");
        final ServletOutputStream outStream = response.getOutputStream();

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=Exportacao_Caixa_" + new SimpleDateFormat("ddMMyyyy").format(new Date()) + ".xls");
        workbook.write(outStream);
        outStream.flush();
        outStream.close();
        return mapping.findForward("lista");
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
}

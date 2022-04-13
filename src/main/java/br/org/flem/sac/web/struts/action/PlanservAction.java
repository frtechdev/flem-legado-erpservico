package br.org.flem.sac.web.struts.action;

import br.org.flem.fw.persistencia.dto.ConvenioMedicoEnum;
import br.org.flem.fw.persistencia.dto.ParentescoEnum;
import br.org.flem.fw.service.IDependente;
import br.org.flem.fw.service.IFuncionario;
import br.org.flem.fw.service.IUsuario;
import br.org.flem.fw.service.impl.RHServico;
import br.org.flem.fw.util.Constante;
import br.org.flem.fwe.web.tag.MensagemTag;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.bo.ArquivosPlanservBO;
import br.org.flem.sac.bo.PlanservBO;
import br.org.flem.sac.bo.TitularBO;
import br.org.flem.sac.dto.planserv.Beneficiario;
import br.org.flem.sac.dto.planserv.OperacaoEnum;
import br.org.flem.sac.negocio.ArquivosPlanserv;
import br.org.flem.sac.negocio.Titular;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author mjpereira
 */
public class PlanservAction extends DispatchAction {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {  
        request.getSession().setAttribute("set", new TreeSet());
        request.getSession().setAttribute("matriculas", new RHServico().obterTodosPorPlanservMatriculasFuncionario(null));
        return mapping.findForward("inicio");
    }

    @SuppressWarnings(value = "unchecked")
    public ActionForward adicionar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaActionForm dyna = (DynaActionForm) form;

        try {
            String matricula = dyna.getString("matricula").trim();
            if (matricula == null || matricula.isEmpty()) {
                return new ActionRedirect("Planserv.do");
            }
            String beneficiario = dyna.getString("beneficiario");
            String cdplano = dyna.getString("cdPlano");
            String operacao = dyna.getString("operacao");
            String dataOperacao = dyna.getString("dtoperacao");
            String dataBeneficio = dyna.getString("dtbeneficio");
            Beneficiario b = new Beneficiario();
            b.setMatricula(matricula);
            b.setDataOperacao(dataOperacao);
            if (dataBeneficio != null) {
                b.setDataBeneficio(dataBeneficio);
            }
            RHServico rh = new RHServico();
            IFuncionario func = rh.obterFuncionarioPorMatricula(Integer.valueOf(matricula));
            b.setFuncionario(func);
            int sequencial = Integer.parseInt(beneficiario) - Integer.parseInt(matricula);
            b.setRdp(sequencial);
            if (beneficiario.equals(matricula)) {
                b.setNome(func.getNome());
                b.setCdParentesco("00");
                b.setNomeParentesco("TITULAR");
                b.setTitular(true);
                b.setDataNascimento(sdf.format(func.getNascimento()));
                b.setSexo(func.getSexo());
                //b.setDataBeneficio(f.dataBeneficio);
            } else {
                Map<Integer, IDependente> map = rh.obterDependenteFuncionarioPorMatricula(Integer.parseInt(matricula));
                IDependente dependente = map.get(Integer.parseInt(beneficiario) - Integer.parseInt(matricula));
                b.setNome(dependente.getNome());
                ParentescoEnum parentescoEnum = ParentescoEnum.obterPorCodigoHR(dependente.getParentesco().getCodigoHR());
                b.setCdParentesco(parentescoEnum.getCodigoPlanserv());
                //b.setCdParentesco(ParentescoEnum.valueOf(dependente.getParentesco().getDescricao()).getDescricao());
                b.setNomeParentesco(dependente.getParentesco().getDescricao());
                b.setTitular(false);
                b.setDataNascimento(sdf.format(dependente.getNascimento()));
                b.setSexo(dependente.getSexo());
            }

            b.setCdOperacao((OperacaoEnum.values()[Integer.parseInt(operacao)]).getCodigo() + "");
            b.setNomeOperacao((OperacaoEnum.values()[Integer.parseInt(operacao)]).getDescricao());
            b.setCdPlano(cdplano);
            b.setNomePlano(ConvenioMedicoEnum.getDescricaoPorCodigoPlanServ(Integer.parseInt(cdplano)));
            Set set = (Set) request.getSession().getAttribute("set");
            if (set == null) {
                set = new TreeSet();
                request.getSession().setAttribute("set", set);
            }
            set.add(b);
            request.setAttribute("set", set);
            request.setAttribute("matricula", matricula);
        } catch (Exception e) {
            List<String> mensagens = new ArrayList<String>();
            mensagens.add("Erro ao adicionar funcionario na lista");
            request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS, mensagens);
            e.printStackTrace();
        }
        return mapping.findForward("inicio");
    }

    @SuppressWarnings("unchecked")
    public ActionForward arquivo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Set<Beneficiario> set = (Set) request.getSession().getAttribute("set");
        String caminho = this.getServlet().getServletContext().getRealPath("/pages/planserv/") + File.separator;
        FileInputStream in = null;
        try {
            TitularBO titularBO = new TitularBO();
            Titular titular = null;

            for (Beneficiario b : set) {
                if (titular == null) {
                    titular = titularBO.obterPorPkNull(Integer.valueOf("10" + b.getMatricula()));
                }
                if (b.isTitular()) {
                    if (b.getCdOperacao().equals(OperacaoEnum.INCUSAO.getCodigo() + "")) {
                        if (titular == null) {
                            new TitularBO().inserir(b);
                            titular = titularBO.obterPorPkNull(Integer.valueOf("10" + b.getMatricula()));
                        }
                    } else if (b.getCdOperacao().equals(OperacaoEnum.EXCLUSAO_PLANO.getCodigo() + "")) {
                        new TitularBO().excluir(Integer.valueOf(b.getMatricula()));
                    }
                }
            }
            String nome = new PlanservBO().gerarArquivoPlanserv(set, caminho);

            ArquivosPlanservBO arquivosPlanservBO = new ArquivosPlanservBO();
            ArquivosPlanserv arquivosPlanserv = new ArquivosPlanserv();
            arquivosPlanserv.setNome(nome);
            arquivosPlanserv.setTitular(titular);
            arquivosPlanserv.setUsuario(((IUsuario) request.getSession().getAttribute(Constante.USUARIO_LOGADO)).getUsername());

            arquivosPlanserv.setNumeroRegistro(arquivosPlanservBO.getUltimoArquivo().getNumeroRegistro() + 1);
            arquivosPlanserv.setData(new Date());

            File arquivo = new File(caminho + nome);
            in = new FileInputStream(arquivo);
            byte[] b = new byte[(int) arquivo.length()];
            in.read(b);
            in.close();
            arquivosPlanserv.setArquivo(b);
            arquivosPlanservBO.inserir(arquivosPlanserv);


            response.setContentType("application/save");
            response.setHeader("Content-disposition", "attachment; filename=" + nome);
            return new ActionForward("/pages/planserv/" + nome);

        } catch (Exception ex) {
            Logger.getLogger(PlanservAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(PlanservAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        List<String> mensagens = new ArrayList<String>();
        mensagens.add("Funcionário não encontrado na base de titulares do ERP.");
        request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS, mensagens);
        request.getSession().setAttribute("set", new TreeSet());

        return mapping.findForward("inicio");
    }
  
    public ActionForward zipArquivos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ByteArrayOutputStream file = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(file);
        try {
            List<ArquivosPlanserv> arquivos = new ArquivosPlanservBO().obterTodosOrdenado();
            for (ArquivosPlanserv arquivo : arquivos) {
                try {
                    ZipEntry ze = new ZipEntry(arquivo.getNome());
                    zip.putNextEntry(ze);
                    zip.write(arquivo.getArquivo());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            zip.closeEntry();
            zip.close();
        } catch (Exception ex) {
            Logger.getLogger(PlanservAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setHeader("Content-Disposition", "attachment; filename=arquivosPlanserv.zip");

        //escreve o arquivo no buffer de saida
        try {
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(file.toByteArray());
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward("inicio");
    }

    public ActionForward arquivoMensal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        List<Layout> layout = new ArrayList<Layout>();
        String mesGeracaoArquivo = request.getParameter("mes");
        String anoGeracaoArquivo = request.getParameter("ano");
        Boolean gerarEmTela = request.getParameter("emTela") != null ? true : false;

        try {
            Calendar caleAtual = Calendar.getInstance();
            int anoAtual = caleAtual.get(Calendar.YEAR);
            int mesAtual = caleAtual.get(Calendar.MONTH);

            Calendar caleGeracao = Calendar.getInstance();
            caleGeracao.get(Calendar.YEAR);
            caleGeracao.get(Calendar.MONTH);

            /**
            if (caleAtual.after(caleGeracao)) {   
                throw new Exception("Não foi possível obter os descontos do Planserv. Verifique se já foi gerada folha!");
            }
            **/
            layout = new PlanservBO().geraArquivoMensal(mesGeracaoArquivo, anoGeracaoArquivo, gerarEmTela);
            
            if(gerarEmTela){
                request.setAttribute("layout", new PlanservBO().somenteDetalheMensal(layout));
                request.setAttribute("mostrarTabela", gerarEmTela);
            }else{
                /**
                 * ********************************************************************
                 * Gerar arquivo mensal do Planserv
                 * *******************************************************************
                 */
                StringBuffer output = new StringBuffer();

                response.setContentType("text/plain");
                Calendar cal = new GregorianCalendar();
                Calendar calMesAnterior = new GregorianCalendar();
                calMesAnterior.add(calMesAnterior.MONTH, -1);

                int mes = 0;
                int ano = 0;
                if (caleGeracao.after(caleAtual)) {
                    mes = calMesAnterior.get(Calendar.MONTH) + 1;
                    ano = calMesAnterior.get(Calendar.YEAR);
                } else {
                    mes = cal.get(Calendar.MONTH) + 1;
                    ano = cal.get(Calendar.YEAR);
                }

                String nomeArquivo = "V-97-";
                if (mes < 10) {
                    nomeArquivo += "0" + mes;
                } else {
                    nomeArquivo += mes;
                }
                nomeArquivo += String.valueOf(ano).substring(2, 4) + "2703-PLA";

                response.setHeader("Content-Disposition", "attachment; filename=" + nomeArquivo + ".txt");

                //escreve o arquivo no buffer de saida
                ServletOutputStream outStream = response.getOutputStream();

                for (Layout l : layout) {
                    output.append(l.toLayout() + "\n");
                }

                outStream.print(output.toString());

                outStream.flush();
                outStream.close();
                return null;
             }
        } catch (Exception ex) {
            Logger.getLogger(PlanservAction.class.getName()).log(Level.SEVERE, null, ex);
            List<String> mensagens = new ArrayList<String>();
            mensagens.add(ex.getMessage());
            request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS, mensagens);
        }
        return mapping.findForward("inicio");
    }
    
    public ActionForward beneficiarios(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        PlanservBO planservBO = new PlanservBO();
        
        String codMatricula  = request.getParameter("matricula");

        request.setAttribute("matricula", codMatricula);
        request.setAttribute("funcionario", planservBO.obterFuncionario(codMatricula));

        request.setAttribute("beneficiarios", planservBO.obterDependentes(codMatricula));
        return mapping.findForward("beneficiarios");
    }
}


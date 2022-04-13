/*
 * RpaAction.java
 *
 * Created on 04/09/2007, 15:50:14
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.web.struts.action;

import br.org.flem.fw.service.IRpa;
import br.org.flem.fw.service.RpaServico;
import br.org.flem.fw.service.impl.RpaServicoImpl;
import br.org.flem.fwe.util.StringUtil;
import br.org.flem.fwe.web.tag.MensagemTag;
import br.org.flem.sac.arquivo.Arquivo;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.layout.rpa.Parcela;
import br.org.flem.sac.layout.rpa.ParcelasI;
import br.org.flem.sac.layout.rpa.ParcelasITAX;
import br.org.flem.sac.layout.rpa.RateioR;
import br.org.flem.sac.layout.rpa.RateioRACT;
import br.org.flem.sac.layout.rpa.RegistroHeader;
import br.org.flem.sac.layout.rpa.RegistroHeaderH;
import br.org.flem.sac.layout.rpa.RegistroHeaderHPAY;
import br.org.flem.sac.layout.rpa.RegistroHeaderHTAX;
import br.org.flem.sac.layout.rpa.RegistroLinha;
import br.org.flem.sac.layout.rpa.ServicosL1;
import br.org.flem.sac.layout.rpa.ServicosL1ACT;
import br.org.flem.sac.layout.rpa.ServicosL1TAX;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author lbrito
 */
public class RpaAction extends DispatchAction {

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        RpaServico rpaServico = new RpaServicoImpl();

        request.setAttribute("lista", rpaServico.obterTodosRpa());

        return mapping.findForward("lista");
    }
    
    @Deprecated
    public ActionForward exportarArquivo_gem211(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String[] rpaExportar = null;
        RpaServico rpaServico = new RpaServicoImpl();
        List<Layout> layoutRpa = new ArrayList<Layout>();
        String entidade = null;
        Date dataInclusao = new Date();
        Date dataVencimento = null;
        
        rpaExportar = request.getParameterValues("selecaoExportar");
        
        Calendar cal = new GregorianCalendar();

        // Adiciona dois dias (48 horas) a data atual para o vencimento da RPA
        cal.add(Calendar.DAY_OF_MONTH, 2);
        
        // Trata se a data de vencimento não cai em final de semana, nesse caso busca o próximo dia útil.
        while ( cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        dataVencimento = cal.getTime();
                      
            for (String rpaSelecao : rpaExportar) {

                IRpa rpa = rpaServico.obterPorId(Integer.valueOf(rpaSelecao));

                RegistroHeader header = new RegistroHeader();

                header.setCompanhia("FE01");
                header.setGrupoCP("GFOR");
                entidade = "P" + StringUtil.formatarCampoNumerico(rpa.getPessoa().getId(), 6);
                header.setEntidade(entidade);
                header.setLocal("0001");
                header.setTipoDocumentoReferencia("RPA");
                header.setDocumentoReferencia(String.valueOf(rpa.getId()));
                header.setTipoRegistro("01"); // Registro de Header
                header.setDataEmissao( dataInclusao );
                header.setDataRecebimento( dataInclusao );
                header.setDataContabil( dataInclusao );
                header.setCondicaoPagto("AVI");
                header.setMoeda("R$");
                header.setDescricao(rpa.getDescricaoServico());
                header.setDescricao1("");
                header.setDescricao2("");
                header.setCentroResponsabilidade("");
                if ( rpa.getFormaPagamento().getId() == 1 ) {
                // Se o tipo de pagamento for crédito em conta (ID = 1), define a conta bancária para depósito
                    header.setContaBancariaForn("001");
                } 
                else {
                // Se o tipo de pagamento não for crédito em conta (ID != 1), não define uma conta bancária para depósito
                    header.setContaBancariaForn("");
                }
                
                header.setCentroPagamento(""); // Campo definido pelo Adm. Financeiro
                header.setMetodoPagto(""); // Campo definido pelo Adm. Financeiro
                header.setIndicadorPagamento("0");
                header.setValorTotal(rpa.getValorLiquido());
                header.setCategoriaDespesa("");
                header.setCampoUsuario1("");
                header.setCampoUsuario2("");
                header.setCampoUsuario3("");
                header.setSituacaoParcela("");
                header.setMotivoBloqueio("");
                header.setSituacaoDocumento("0"); // Importa como um documento Salvo
                header.setTipoDocumentoReferencia2("");
                header.setDocumentoReferencia2("");
                header.setNomeFornEventual("");
                header.setEnderecoFornEventual("");
                header.setIdentificadorFornEventual("");
                header.setBancoFornEventual("");
                header.setAgenciaFornEventual("");
                header.setContaFornEventual("");

                layoutRpa.add(header);

                // O campo CC1 pode ser obtido a partir do primeiro caracter do campo CC2
                String centroContabil1 = String.valueOf(rpa.getCentroCusto());
                /*
                 * Linha de despesa: Registro do valor da Despesa BRUTA
                 */
                RegistroLinha linhaDespesa = new RegistroLinha();
                linhaDespesa.setCompanhia("FE01");
                linhaDespesa.setGrupoCP("GFOR");
                linhaDespesa.setEntidade(entidade);
                linhaDespesa.setLocal("0001");
                linhaDespesa.setTipoDocumentoReferencia("RPA");
                linhaDespesa.setDocumentoReferencia(String.valueOf(rpa.getId()));
                //linhaDespesa.setDocumentoReferencia( "07025" );
                linhaDespesa.setTipoRegistro("02"); // "02" = Registro de linha de Despesa
                linhaDespesa.setTipoLinha("1"); // "1" = Tipo de linha de Despesa
                linhaDespesa.setDescricao(rpa.getDescricaoServico());
                linhaDespesa.setDescricao1("");
                linhaDespesa.setDescricao2("");
                linhaDespesa.setValorLinha(rpa.getValorBruto());
                linhaDespesa.setEventoContabil("RFP001");
                linhaDespesa.setContaContabil(rpa.getElementoDespesa());
                linhaDespesa.setCentroContabil1(centroContabil1);
                linhaDespesa.setCentroContabil2(rpa.getCentroCusto());
                linhaDespesa.setCentroContabil3(rpa.getCentroResponsabilidade());
                linhaDespesa.setCentroContabil4("");
                linhaDespesa.setCodigoTributo("");
                linhaDespesa.setCodigoRetencao("");
                linhaDespesa.setCategoriaDespesa("");

                layoutRpa.add(linhaDespesa);

                if (rpa.getValorIRRF() != 0.0) {
                    /*
                     * Linha de despesa: Registro do valor do Imposto IRRF
                     */
                    RegistroLinha linhaImposto1 = new RegistroLinha();
                    linhaImposto1.setCompanhia("FE01");
                    linhaImposto1.setGrupoCP("GFOR");
                    linhaImposto1.setEntidade(entidade);
                    linhaImposto1.setLocal("0001");
                    linhaImposto1.setTipoDocumentoReferencia("RPA");
                    linhaImposto1.setDocumentoReferencia(String.valueOf(rpa.getId()));
                    linhaImposto1.setTipoRegistro("02"); // "02" = Registro de linha de Despesa
                    linhaImposto1.setTipoLinha("4"); // "4" = Tipo de linha de Imposto de Renda (IRRF)
                    linhaImposto1.setDescricao("IRRF");
                    linhaImposto1.setDescricao1("");
                    linhaImposto1.setDescricao2("");
                    linhaImposto1.setValorLinha(rpa.getValorIRRF());
                    linhaImposto1.setEventoContabil("IRRF01");
                    linhaImposto1.setContaContabil("");
                    linhaImposto1.setCentroContabil1("");
                    linhaImposto1.setCentroContabil2("");
                    linhaImposto1.setCentroContabil3("");
                    linhaImposto1.setCentroContabil4("");
                    linhaImposto1.setCodigoTributo("IR");
                    linhaImposto1.setCodigoRetencao("0588");
                    linhaImposto1.setCategoriaDespesa("");

                    layoutRpa.add(linhaImposto1);
                }
                
                if (rpa.getValorISS() != 0.0) {
                /*
                 * Linha de despesa: Registro do valor do ISS
                 */
                    RegistroLinha linhaImposto2 = new RegistroLinha();
                    linhaImposto2.setCompanhia("FE01");
                    linhaImposto2.setGrupoCP("GFOR");
                    linhaImposto2.setEntidade(entidade);
                    linhaImposto2.setLocal("0001");
                    linhaImposto2.setTipoDocumentoReferencia("RPA");
                    linhaImposto2.setDocumentoReferencia(String.valueOf(rpa.getId()));
                    linhaImposto2.setTipoRegistro("02"); // "02" = Registro de linha de Despesa
                    linhaImposto2.setTipoLinha("5"); // "5" = Tipo de linha de Outros Impostos
                    linhaImposto2.setDescricao("ISS");
                    linhaImposto2.setDescricao1("");
                    linhaImposto2.setDescricao2("");
                    linhaImposto2.setValorLinha(rpa.getValorISS());
                    linhaImposto2.setEventoContabil("ISS01");
                    linhaImposto2.setContaContabil("");
                    linhaImposto2.setCentroContabil1("");
                    linhaImposto2.setCentroContabil2("");
                    linhaImposto2.setCentroContabil3("");
                    linhaImposto2.setCentroContabil4("");
                    linhaImposto2.setCodigoTributo("ISS");
                    linhaImposto2.setCodigoRetencao("");
                    linhaImposto2.setCategoriaDespesa("");
                    layoutRpa.add(linhaImposto2);
                
                }
                
                if (rpa.getValorINSSRetido() != 0.0) {
                    /*
                     * Linha de despesa: Registro do valor do INSS (deduzivel)
                     */
                    RegistroLinha linhaImposto3 = new RegistroLinha();
                    linhaImposto3.setCompanhia("FE01");
                    linhaImposto3.setGrupoCP("GFOR");
                    linhaImposto3.setEntidade(entidade);
                    linhaImposto3.setLocal("0001");
                    linhaImposto3.setTipoDocumentoReferencia("RPA");
                    linhaImposto3.setDocumentoReferencia(String.valueOf(rpa.getId()));
                    linhaImposto3.setTipoRegistro("02"); // "02" = Registro de linha de Despesa
                    linhaImposto3.setTipoLinha("5"); // "5" = Tipo de linha de Outros Impostos
                    linhaImposto3.setDescricao("INSS Contratado");
                    linhaImposto3.setDescricao1("");
                    linhaImposto3.setDescricao2("");
                    linhaImposto3.setValorLinha(rpa.getValorINSSRetido());
                    linhaImposto3.setEventoContabil("INSSD01");
                    linhaImposto3.setContaContabil("");
                    linhaImposto3.setCentroContabil1("");
                    linhaImposto3.setCentroContabil2("");
                    linhaImposto3.setCentroContabil3("");
                    linhaImposto3.setCentroContabil4("");
                    linhaImposto3.setCodigoTributo("INSD");
                    linhaImposto3.setCodigoRetencao("2100");
                    linhaImposto3.setCategoriaDespesa("");

                    layoutRpa.add(linhaImposto3);
                }
                
                if (rpa.getValorINSS() != 0.0) {
                /*
                 * Linha de despesa: Registro do valor do INSS (nao deduzivel)
                 */
                    RegistroLinha linhaImposto4 = new RegistroLinha();
                    linhaImposto4.setCompanhia("FE01");
                    linhaImposto4.setGrupoCP("GFOR");
                    linhaImposto4.setEntidade(entidade);
                    linhaImposto4.setLocal("0001");
                    linhaImposto4.setTipoDocumentoReferencia("RPA");
                    linhaImposto4.setDocumentoReferencia(String.valueOf(rpa.getId()));
                    linhaImposto4.setTipoRegistro("02"); // "02" = Registro de linha de Despesa
                    linhaImposto4.setTipoLinha("5"); // "5" = Tipo de linha de Outros Impostos
                    linhaImposto4.setDescricao("INSS Contratante");
                    linhaImposto4.setDescricao1("");
                    linhaImposto4.setDescricao2("");
                    linhaImposto4.setValorLinha(rpa.getValorINSS());
                    linhaImposto4.setEventoContabil("INSSND");
                    linhaImposto4.setContaContabil("");
                    linhaImposto4.setCentroContabil1("");
                    linhaImposto4.setCentroContabil2("");
                    linhaImposto4.setCentroContabil3("");
                    linhaImposto4.setCentroContabil4("");
                    linhaImposto4.setCodigoTributo("INSN");
                    linhaImposto4.setCodigoRetencao("2100");
                    linhaImposto4.setCategoriaDespesa("");

                    layoutRpa.add(linhaImposto4);
                }
                
                Parcela p = new Parcela();
                p.setCompanhia("FE01");
                p.setGrupoCP("GFOR");
                p.setEntidade(entidade);
                p.setLocal("0001");
                p.setTipoDocumentoReferencia("RPA");
                p.setDocumentoReferencia(String.valueOf(rpa.getId()));
                p.setTipoRegistro("03"); // "03" = Registro Parcela
                p.setDataVencimento( dataVencimento );
                p.setValorParcela( rpa.getValorLiquido() );
                p.setTipoDocumentoCobranca("");
                p.setCodigoBarra("");
                p.setNomeFavorecido("");
                p.setDocumentoIdentFavorecido("");
                p.setBancoFavororecido("");
                p.setAgenciaFavorecido("");
                p.setContaFavorecido("");
                p.setComentario1("");
                p.setComentario2("");
                p.setComentario3("");

                layoutRpa.add( p );
            }       


        try {
        Arquivo arquivo = new Arquivo();
        File file = null;

        file = arquivo.geraArquivo(layoutRpa);

        byte[] arquivoRpa = new Arquivo().lerArquivo(file);

        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=" 
                + "Exportacao_Rpa_" + new SimpleDateFormat("ddMMyyyy").format(new Date()) 
                + ".txt");

        //escreve o arquivo no buffer de saida
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(arquivoRpa, 0, arquivoRpa.length);

        outStream.flush();
        outStream.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
            ArrayList erros = new ArrayList();
            erros.add("Erro na geração do arquivo.\nDescrição: "+ex.getMessage());
            request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS, erros);
        }
        return unspecified(mapping, form, request, response);
    }
    
    //_banco215
    public ActionForward exportarArquivo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String[] rpaExportar = null;
        RpaServico rpaServico = new RpaServicoImpl();
        List<Layout> layoutRpa = new ArrayList<Layout>();
        String entidade = null;
        Date dataInclusao = new Date();
        Date dataVencimento = null;
        
        rpaExportar = request.getParameterValues("selecaoExportar");
        
        Calendar cal = new GregorianCalendar();

        // Adiciona dois dias (48 horas) a data atual para o vencimento da RPA
        cal.add(Calendar.DAY_OF_MONTH, 2);
        
        // Trata se a data de vencimento não cai em final de semana, nesse caso busca o próximo dia útil.
        while ( cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        dataVencimento = cal.getTime();
                      
            for (String rpaSelecao : rpaExportar) {

                IRpa rpa = rpaServico.obterPorId(Integer.valueOf(rpaSelecao));
                
                RegistroHeaderH headerH = new RegistroHeaderH();
                RegistroHeaderHPAY headerHPAY = new RegistroHeaderHPAY();
                RegistroHeaderHTAX headerHTAX = new RegistroHeaderHTAX();
                ServicosL1 servicosL1 = new ServicosL1();
                ServicosL1TAX servicosL1TAX = new ServicosL1TAX();
                ServicosL1ACT servicosL1ACT = new ServicosL1ACT();
                RateioR rateioR = new RateioR();
                RateioRACT rateioRACT = new RateioRACT();
                ParcelasI parcelasI = new ParcelasI();
                ParcelasITAX parcelasITAX = new ParcelasITAX();

                entidade = rpa.getPessoa().getCpf(); // ok

                
                
                //H Header
                headerH.setTipoRegistroH("000");
                headerH.setTipoProcessoExternoH(""); 
                headerH.setIdentProcessoExternoH("");
                headerH.setCompanhiaH("FE01");           
                headerH.setTipoProcessoH("RCP30"); //????       
                headerH.setSituacaoDocH("0");         
                headerH.setTipoMovimentoH("5");       
                headerH.setEntradaSaidaH("0");        
                headerH.setTipoDadosH("0");           
                headerH.setTipoRecebimentoH("REC");     
                headerH.setLocalCompanhiaH("0001");      
                headerH.setIdentFornecedorClienteH(entidade);
                headerH.setLocalFornecedorClienteH("0001");
                headerH.setDescricaoFornecedorClienteH("");
                headerH.setCnpjFornecedorClienteH("");  
                headerH.setEnderecoFornecedorClienteH("");
                headerH.setUfFornecedorClienteH("");    
                headerH.setCidadeFornecedorClienteH("");
                headerH.setCepFornecedorClienteH("");   
                headerH.setTelefoneFornecedorClienteH("");
                headerH.setInscrEstadualFornecedorClienteH("");
                headerH.setTipoProcessoOrigemH("");  
                headerH.setIdentProcessoOrigemH(""); 
                headerH.setTipoDocOrigemH("");       
                headerH.setIdentDocOrigemH("");      
                headerH.setSerieDocOrigemH("");      
                headerH.setSubSerieDocOrigemH("");   
                headerH.setDtaEmissaoDocOrigemH(dataInclusao);   
                headerH.setTipoDocH("RPA");             
                headerH.setIdentDocH(String.valueOf(rpa.getId()));            
                headerH.setSerieDocH("");            
                headerH.setSubSerieDocH("");         
                headerH.setModeloDocH("");
                headerH.setTipoDocRefH("RPA");
                headerH.setIdentDocRefH(String.valueOf(rpa.getId()));         
                headerH.setDtaRecebimentoH(dataInclusao);        
                headerH.setDtaEmissaoH(dataInclusao);           
                headerH.setDtaContabilH(dataInclusao);          
                headerH.setRegraEscrituracaoH("8000005");// as regras de escrituracao estão na tabela obvius.btrcfa
                headerH.setDescontoH(0d);
                headerH.setPercentualH(0d);
                headerH.setOutrosValoresH(0d);
                headerH.setMaoObraH(0d);
                headerH.setSeguroH(0d);
                headerH.setFreteH(0d);
                headerH.setIeSubstTributarioH(0d);
                headerH.setObservacao1H("");
                headerH.setObservacao2H("");
                headerH.setObservacao3H("");
                headerH.setObservacao4H("");
                headerH.setCampoUsuario1H("");
                headerH.setCampoUsuario2H("");
                headerH.setCampoUsuario3H("");
                headerH.setCampoUsuario4H("");
                headerH.setCampoUsuario5H("");
                headerH.setCampoUsuario6H("");
                headerH.setChaveNotaFiscalEletronicaH("");
                
                layoutRpa.add(headerH);
  
                
                //HPAY Header - Contas a Pagar
                headerHPAY.setCompanhiaCP("040");
                headerHPAY.setGrupoFornecedorCP("GFE");
                headerHPAY.setCentroResponsabilidadeCP(rpa.getCentroResponsabilidade());
                headerHPAY.setCentroPagamentoCP("CGP");//tesouraria da conta geral de projetos
                headerHPAY.setMetodoPagtoCP("PEL");//pagamento eletrônico
                headerHPAY.setCondicaoPagtoCP("05D");//5 dias após recebimento NF
                headerHPAY.setDescricaoUsuario1CP(rpa.getDescricaoServico().substring(0, rpa.getDescricaoServico().length()));
                headerHPAY.setDescricaoUsuario2CP("");
                if(rpa.getDescricaoServico().length() > 60){
                    headerHPAY.setDescricaoUsuario2CP(rpa.getDescricaoServico().substring(60, rpa.getDescricaoServico().length()));
                }
                headerHPAY.setDescricaoUsuario3CP("");
                if(rpa.getDescricaoServico().length() > 120){
                    headerHPAY.setDescricaoUsuario3CP(rpa.getDescricaoServico().substring(120, rpa.getDescricaoServico().length()));
                }
                headerHPAY.setBancoDepositoCP(String.valueOf(rpa.getBanco().getId()));//4
                headerHPAY.setAgenciaDepositoCP(rpa.getNumeroAgencia());//5
                headerHPAY.setContaDepositoCP(rpa.getNumeroContaCorrente());//20
                
                System.out.println("001");
                
                layoutRpa.add(headerHPAY);
                
                
                //HTAX Header - Tributos
                headerHTAX.setTipoRegistroT("060");
                headerHTAX.setIndicadorTributosT("3");
                headerHTAX.setInssPatronalT(0d);
                headerHTAX.setValorPisT(0d);
                headerHTAX.setValorCofinT(0d);
                
                layoutRpa.add(headerHTAX);
                
                System.out.println("002");
                
                //S Serviços
                servicosL1.setTipoRegistroS("100");
                servicosL1.setSeqRegistroServicoS(1);
                servicosL1.setCodServicoFederalS("0002");
                servicosL1.setCodServicoS("0002");
                servicosL1.setSinteticaS("");
                servicosL1.setDetalhadaServico1S("");
                servicosL1.setDetalhadaServico2S("");
                servicosL1.setDetalhadaServico3S("");
                servicosL1.setDetalhadaServico4S("");
                servicosL1.setPaisS("BRA");
                servicosL1.setUfS("MG");
                servicosL1.setCidadeS("7010");
                servicosL1.setCodSvcS("0002");
                servicosL1.setUnidMedidaS("UN");
                servicosL1.setQuantidadeS(1);
                servicosL1.setValorUnitarioS(670644.0);
                servicosL1.setDescontoS(0d);
                servicosL1.setPercentualDescontoS(0d);
                servicosL1.setCodAjusteDescontoS("");
                servicosL1.setRegraEscrituracaoS("800002");
                servicosL1.setValorSubContratoS(0d);
                servicosL1.setCampoUsuario1S("");
                servicosL1.setCampoUsuario2S("");
                servicosL1.setCampoUsuario3S("");
                servicosL1.setCampoUsuario4S("");
                servicosL1.setCampoUsuario5S("");
    
                layoutRpa.add(servicosL1);
                
                System.out.println("003");
                
                
                
                RegistroLinha linhaImposto3 = new RegistroLinha();
                linhaImposto3.setCompanhia("FE01");
                linhaImposto3.setGrupoCP("GFOR");
                linhaImposto3.setEntidade(entidade);
                linhaImposto3.setLocal("0001");
                linhaImposto3.setTipoDocumentoReferencia("RPA");
                linhaImposto3.setDocumentoReferencia(String.valueOf(rpa.getId()));
                linhaImposto3.setTipoRegistro("02"); // "02" = Registro de linha de Despesa
                linhaImposto3.setTipoLinha("5"); // "5" = Tipo de linha de Outros Impostos
                linhaImposto3.setDescricao("INSS Contratado");
                linhaImposto3.setDescricao1("");
                linhaImposto3.setDescricao2("");
                linhaImposto3.setValorLinha(rpa.getValorINSSRetido());
                linhaImposto3.setEventoContabil("INSSD01");
                linhaImposto3.setContaContabil("");
                linhaImposto3.setCentroContabil1("");
                linhaImposto3.setCentroContabil2("");
                linhaImposto3.setCentroContabil3("");
                linhaImposto3.setCentroContabil4("");
                linhaImposto3.setCodigoTributo("INSD");
                linhaImposto3.setCodigoRetencao("2100");
                linhaImposto3.setCategoriaDespesa("");
                
                layoutRpa.add(linhaImposto3);
                
                System.out.println("004");
                
                
                
                // Linha 05 - (L1TAX ) Serviços - Tributos  
                servicosL1TAX.setTipoRegistroT("110");
                servicosL1TAX.setSeqRegistroServicoT(1);
                servicosL1TAX.setAliquotaIRRetencaoT(0d);
                servicosL1TAX.setBaseCalcIRRetencaoT(0d);
                servicosL1TAX.setReducaoBaseIRRetencaoT(0d);
                servicosL1TAX.setValorIRRetencaoT(0d);
                servicosL1TAX.setAliquotaINSSRetencaoT(0d);
                servicosL1TAX.setBaseCalcINSSRetencaoT(0d);
                servicosL1TAX.setReducaoBaseINSSRetencaoT(0d);
                servicosL1TAX.setValorINSSRetencaoT(rpa.getValorINSSRetido());
                servicosL1TAX.setAliquotaPISRetencaoT(0d);
                servicosL1TAX.setBaseCalcPISRetencaoT(0d);
                servicosL1TAX.setReducaoBasePISRetencaoT(0d);
                servicosL1TAX.setValorPISRetencaoT(0d);
                servicosL1TAX.setAliquotaCOFINSRetencaoT(0d);
                servicosL1TAX.setBaseCalcCOFINSRetencaoT(0d);
                servicosL1TAX.setReducaoBaseCOFINSRetencaoT(0d);
                servicosL1TAX.setValorCOFINSRetencaoT(0d);
                servicosL1TAX.setAliquotaCSLLRetencaoT(0d);
                servicosL1TAX.setBaseCalcCSLLRetencaoT(0d);
                servicosL1TAX.setReducaoBaseCSLLRetencaoT(0d);
                servicosL1TAX.setValorCSLLRetencaoT(0d);
                servicosL1TAX.setAliquotaISSRetencaoT(0d);
                servicosL1TAX.setBaseCalcISSRetencaoT(0d);
                servicosL1TAX.setReducaoBaseISSRetencaoT(0d);
                servicosL1TAX.setValorISSRetencaoT(0d);
                
                layoutRpa.add(servicosL1TAX);
                
                System.out.println("005");
                
                // Linha 06 - L1ACT
                servicosL1ACT.setTipoRegistroCF("120");  
                servicosL1ACT.setSeqRegistroServicoCF(1);
                servicosL1ACT.setContaFinanceiraCF("315104");
                servicosL1ACT.setCodEventoContabilCF("INTPF1");
                servicosL1ACT.setTipoRateioCF("1");
                
                layoutRpa.add(servicosL1ACT);
                
                System.out.println("006");
                
                
                // Linha 07 - (R) Rateio
                rateioR.setTipoRegistroR("600");
                rateioR.setSeqRegistroRateioR(1);
                rateioR.setTipoItemR("0");
                rateioR.setSeqItemDocR(1);
                rateioR.setSeqRateioR(1);
                rateioR.setValorR(rpa.getValorBruto());
                rateioR.setPercentualR(0d);
                rateioR.setValorAbsolutoR(rpa.getValorLiquido());
                rateioR.setCampoUsuario1R("");
                rateioR.setCampoUsuario2R("");
                rateioR.setCampoUsuario3R("");
                rateioR.setCampoUsuario4R("");
                rateioR.setCampoUsuario5R("");
                
                layoutRpa.add(rateioR);
                
                System.out.println("007");
                                                                                        
                // Linha 08 - (RACT) Rateio Contábil / Financeiro / Orçamentário
                rateioRACT.setTipoRegistroCFO("610");
                rateioRACT.setSeqRegistroRateioCFO(1);
                rateioRACT.setTipoItemCFO("0");
                rateioRACT.setSeqItemDocCFO(1);
                rateioRACT.setSeqRateioCFO(1);
                rateioRACT.setContaContabilCFO("");//315104
                rateioRACT.setContaContabil1CFO("");//A25301020200
                rateioRACT.setContaContabil2CFO("");//A1G120900000
                rateioRACT.setContaContabil3CFO("");
                rateioRACT.setContaContabil4CFO("");
                rateioRACT.setContaContabilOrcamentariaCFO("");
                rateioRACT.setCentroOrcamentaria1CFO("");
                rateioRACT.setCentroOrcamentaria2CFO("");
                rateioRACT.setCentroOrcamentaria3CFO("");
                rateioRACT.setCentroOrcamentaria4CFO("");
                
                layoutRpa.add(rateioRACT);

                
                System.out.println("008");
                
                // Linha 09 - (I) - Parcelas 
                parcelasI.setTipoRegistroI("700");
                parcelasI.setSeqRegistroParcelaI(1);
                parcelasI.setDtaVencimentoI(rpa.getDataPagamento());
                parcelasI.setDtaPagamentoI(rpa.getDataPagamento());
                parcelasI.setValorI(rpa.getValorBruto());
                parcelasI.setValorLiquidoParcelaI(rpa.getValorLiquido());
                parcelasI.setIdentPortadorI("");
                parcelasI.setNumDocPortadorI("");
                parcelasI.setTipoCodBarrasI("");
                parcelasI.setIdentCodBarrasI("");
                parcelasI.setCodBarrasI("");
                
                layoutRpa.add(parcelasI);
                
                System.out.println("009");
                
                // Linha 10 - (ITAX) Parcelas Tributos
                parcelasITAX.setTipoRegistroT("710");
                parcelasITAX.setSeqRegistroParcelaT(1);
                parcelasITAX.setValorTributadoT(0d);
                parcelasITAX.setBaseCalcPIST(0d);
                parcelasITAX.setValorPIST(0d);
                parcelasITAX.setBaseCalcCOFINST(0d);
                parcelasITAX.setValorCOFINST(0d);
                parcelasITAX.setBaseCalcCSLLT(0d);
                parcelasITAX.setValorCSLLT(0d);
                parcelasITAX.setBaseCalcIRT(0d);
                parcelasITAX.setValorIRT(0d);
                parcelasITAX.setBaseCalcINSST(0d);
                parcelasITAX.setValorINSST(0d);
                parcelasITAX.setBaseCalcISST(0d);
                parcelasITAX.setValorISST(0d);
                
                layoutRpa.add(parcelasITAX);
                
                System.out.println("010");
                
            }       


        try {
            Arquivo arquivo = new Arquivo();
            File file = null;

            System.out.println("011");
            
            file = arquivo.geraArquivo(layoutRpa);

            byte[] arquivoRpa = new Arquivo().lerArquivo(file);

            response.setContentType("text/plain");
            response.setHeader("Content-Disposition", "attachment; filename=" 
                    + "Exportacao_Rpa_" + new SimpleDateFormat("ddMMyyyy").format(new Date()) 
                    + ".txt");
            
            System.out.println("012");

            //escreve o arquivo no buffer de saida
            ServletOutputStream outStream = response.getOutputStream();
            
            System.out.println("013");
            
            outStream.write(arquivoRpa, 0, arquivoRpa.length);
            
            System.out.println("014");

            outStream.flush();
            outStream.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
            ArrayList erros = new ArrayList();
            erros.add("Erro na geração do arquivo.\nDescrição: "+ex.getMessage());
            request.getSession().setAttribute(MensagemTag.LISTA_MENSAGENS, erros);
        }
        return unspecified(mapping, form, request, response);
    }

    public RpaAction() {
    }
    
    public static void main(String[] args) {
        
    }
    
}

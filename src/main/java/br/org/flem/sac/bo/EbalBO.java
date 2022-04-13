/*
 * EbalBO.java
 *
 * Created on 27/08/2007, 15:14:27
 */

package br.org.flem.sac.bo;

import br.org.flem.fw.service.IFuncionario;
import br.org.flem.fw.service.RH;
import br.org.flem.fw.service.impl.RHServico;
import br.org.flem.fwe.exception.AcessoDadosException;
import br.org.flem.fwe.exception.AplicacaoException;
import br.org.flem.fwe.exception.RegistroJaExistenteException;
import br.org.flem.fwe.hibernate.util.HibernateUtil;
import br.org.flem.sac.arquivo.Arquivo;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.dao.ArquivoCredicestaDAO;
import br.org.flem.sac.layout.ebal.batch.RegistroTipo1;
import br.org.flem.sac.layout.ebal.batch.RegistroTipo2;
import br.org.flem.sac.layout.ebal.batch.RegistroTipo9;
import br.org.flem.sac.layout.ebal.cadastroslimites.CadastrosLimitesDetalhe;
import br.org.flem.sac.layout.ebal.cadastroslimites.CadastrosLimitesHeader;
import br.org.flem.sac.layout.ebal.credicesta.CredicestaDetalhe;
import br.org.flem.sac.layout.ebal.credicesta.CredicestaHeader;
import br.org.flem.sac.layout.ebal.credicesta.LayoutCredicestaVO;
import br.org.flem.sac.layout.ebal.retornoconciliacao.RetornoConciliacaoDetalhe;
import br.org.flem.sac.layout.ebal.retornoconciliacao.RetornoConciliacaoHeader;
import br.org.flem.sac.layout.ebal.retornoconciliacao.RetornoConciliacaoTrailer;
import br.org.flem.sac.negocio.ArquivoCredicesta;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author FCSilva
 */

public class EbalBO {

    public EbalBO() {
    }

    public void excluirArquivoCredicesta(ArquivoCredicesta arquivo) throws AcessoDadosException {
        HibernateUtil.beginTransaction();
        new ArquivoCredicestaDAO().excluir(arquivo);
        HibernateUtil.commitTransaction();
    }

    /**
     ** Retorna um DTO representando o layout completo(header + detalhe) do arquivo credicesta
     */
    public LayoutCredicestaVO montarLayoutCredicesta(ArquivoCredicesta arquivoCredicesta) 
             throws IOException {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(arquivoCredicesta.getArquivo());
        Scanner in = new Scanner(byteStream);

        try {

            LayoutCredicestaVO layoutCredicestaVO = new LayoutCredicestaVO();

            if (in.hasNextLine()) {

                StringBuffer linha = new StringBuffer(in.nextLine());
                CredicestaHeader header = new CredicestaHeader();
                header.setConstante(linha.substring(0, 28));
                header.setBrancos(20);
                Date date = new java.text.SimpleDateFormat("yyyyMMdd").parse(linha.substring(49, 57));
                header.setData(date);
                header.setConstante(linha.substring(57, 70));
                header.setBrancos(45);

                layoutCredicestaVO.setHeader(header);
            }
            List<CredicestaDetalhe> layoutDetalhe = new ArrayList<CredicestaDetalhe>();

            for (int i = 1; in.hasNextLine(); i++) {
                StringBuffer linha = new StringBuffer(in.nextLine());

                CredicestaDetalhe detalhe = new CredicestaDetalhe();
                detalhe.setIndicadorRegistro(Integer.valueOf(linha.substring(0, 1)));
                detalhe.setOrgao(linha.substring(1, 3));
                detalhe.setConstante(4);
                detalhe.setMatricula(Integer.valueOf(linha.substring(7, 16)));
                detalhe.setBranco(30);
                detalhe.setConstante2(linha.substring(46, 49));

                //converte o valor descontado no arquivo credicesta para o formato N(15,2)
                detalhe.setValorDescontado(Double.valueOf(linha.substring(50, 59) + "." + linha.substring(59, 61)));
                detalhe.setBeneficiario(Integer.valueOf(linha.substring(61, 65)));
                detalhe.setConstante3(linha.substring(65, 85));
                detalhe.setConstante4(linha.substring(85, 87));

                detalhe.setBranco2(37);
                detalhe.setFlagEnvio(linha.substring(124, 126));
                layoutDetalhe.add(detalhe);
            }
            layoutCredicestaVO.setDetalhe(layoutDetalhe);

            return layoutCredicestaVO;
        }

        catch (ParseException ex) {
           throw new IOException("Erro ao montar layout do arquivo: " 
                   + new SimpleDateFormat("MM/yyyy").format(arquivoCredicesta.getDataReferencia()));
        }        
    }


    private File gerarArquivoBatch(LayoutCredicestaVO layoutCredicestaVO) throws IOException {
        //layout do arquivo de lote
        SimpleDateFormat formatoMesAnoInverso = new SimpleDateFormat("yyyyMM");
        CredicestaHeader credicestaHeader = layoutCredicestaVO.getHeader();
        
        List<Layout> layoutBatch = new ArrayList<Layout>();
        
        Calendar dataReferencia = new GregorianCalendar();
        dataReferencia.setTime(credicestaHeader.getData());
        dataReferencia.add(Calendar.MONTH, 1);
        
        RegistroTipo1 registroTipo1 = new RegistroTipo1();
        registroTipo1.setNomeLote("EBAL" + formatoMesAnoInverso.format(dataReferencia.getTime()));
        registroTipo1.setEmpresa("FLE");
        registroTipo1.setFiller1(4);
        registroTipo1.setFiller2(25);
        registroTipo1.setFiller3(4);
        registroTipo1.setFiller4(7);

        //colecao auxiliar que contera os registros tipo 2
        List<RegistroTipo2> lDetalheRegistro2 = new ArrayList<RegistroTipo2>();

        //total de linhas requerido no layout geral
        int numeroRegistros = 0;
       
        for (CredicestaDetalhe credicestaDetalhe : layoutCredicestaVO.getDetalhe()) {
            RegistroTipo2 registroTipo2 = new RegistroTipo2();
            registroTipo2.setTipoRegistro(2);
            registroTipo2.setNomeLote("EBAL" + formatoMesAnoInverso.format(dataReferencia.getTime()));

            //fixo
            registroTipo2.setEstabelecimento(1);
            //fixo
            registroTipo2.setEmpresa("FLE");

            registroTipo2.setProntuario(credicestaDetalhe.getMatricula());
            
            registroTipo2.setDataCompetencia(dataReferencia.getTime());
            registroTipo2.setDataBase(dataReferencia.getTime());

            
            registroTipo2.setCodigoVerba(305);
            registroTipo2.setValor(credicestaDetalhe.getValorDescontado());
            
            registroTipo2.setReferencia(0);
            
            registroTipo2.setCalculo(1);
            
            registroTipo2.setNumeroParcelas(1);
            registroTipo2.setFiller(7);
            lDetalheRegistro2.add(registroTipo2);
            numeroRegistros++;

            //incrementa o valor global em registroTipo1
            registroTipo1.setValor(registroTipo1.getValor() + registroTipo2.getValor());
        }

        layoutBatch.add(registroTipo1);
        layoutBatch.addAll(lDetalheRegistro2);

        RegistroTipo9 registroTipo9 = new RegistroTipo9();
        registroTipo9.setTipoRegistro(9);
        registroTipo9.setFiller(74);
        registroTipo9.setNumeroRegistros(numeroRegistros + 2); //detalhe + header + fim
        layoutBatch.add(registroTipo9);
        
        return new Arquivo().geraArquivo(layoutBatch, "HREBALLOTE.txt");
    }

    private File gerarArquivoRetornoConciliacao(LayoutCredicestaVO layoutCredicestaVO) throws IOException {
        //layout do arquivo de lote
        List<Layout> layoutConciliacao = new ArrayList<Layout>();
        int contador = 1;
        CredicestaHeader credicestaHeader = layoutCredicestaVO.getHeader();
        RetornoConciliacaoHeader header = new RetornoConciliacaoHeader();
        header.setIndicadorRegistro(0);
        header.setDescritivoDestino("EBAL-EMPRESA BAIANA DE ALIMENTOS S.A.");
        header.setConstante("FOLHA");
        header.setTipoRetorno("O");
        
        Calendar folhaReferencia = new GregorianCalendar();
        folhaReferencia.setTime(credicestaHeader.getData());
        folhaReferencia.add(Calendar.MONTH, 1);
        
        header.setFolhaReferencia(folhaReferencia.getTime());

        Date dataAtual = new Date();

        header.setDataGeracaoArquivo(dataAtual);
        header.setContador(contador);

        layoutConciliacao.add(header);

        Double valorTotal = new Double(0);
        for (CredicestaDetalhe credicestaDetalhe : layoutCredicestaVO.getDetalhe()) {

            RetornoConciliacaoDetalhe detalhe = new RetornoConciliacaoDetalhe();
            detalhe.setIndicadorRegistro(1);
            detalhe.setGestor(3);
            detalhe.setOrgao(517);
            detalhe.setMatricula(credicestaDetalhe.getMatricula());
            detalhe.setBeneficiario(credicestaDetalhe.getBeneficiario());
            detalhe.setValorDescontado(credicestaDetalhe.getValorDescontado());

            valorTotal = new Double(valorTotal + detalhe.getValorDescontado());

            detalhe.setCodigoErro(0);
            detalhe.setCodigoSituacaoFuncional(0);
            detalhe.setBrancos(24);

            contador++;
            detalhe.setContador(contador);
            layoutConciliacao.add(detalhe);
        }

        contador++;
        
        //trailer
        RetornoConciliacaoTrailer trailer = new RetornoConciliacaoTrailer();
        trailer.setIndicadorRegistro(9);
        trailer.setBrancos(50);        
        trailer.setValorArquivo(valorTotal);
        trailer.setContador(contador);
        layoutConciliacao.add(trailer);
        
        return new Arquivo().geraArquivo(layoutConciliacao, 
                "FLEMCON" + new SimpleDateFormat("MMyyyy").format(new Date())+".txt");
    }
    
    /**
     * A partir do HRBI gera o arquivo de cadastros e limites
     */
    public File gerarArquivoCadastrosLimites() throws IllegalAccessException, InvocationTargetException, IOException {
        //layout do arquivo de cadastros e limites
        List<Layout> layoutCadastrosLimites = new ArrayList<Layout>();

        CadastrosLimitesHeader header = new CadastrosLimitesHeader();

        //data de competencia = data atual com o mes somado 1
        Calendar mesReferencia = new GregorianCalendar();
        mesReferencia.setTime(new Date());
        mesReferencia.add(Calendar.MONTH, 1);
        header.setData(mesReferencia.getTime());
        
        header.setLayout("C03");
        layoutCadastrosLimites.add(header);

        //busca os dados dos colaboradores com credicesta no HRBI
        RHServico rh = new RHServico();
        for (IFuncionario funcionario : rh.obterPorTodosEbal()) {

            CadastrosLimitesDetalhe detalhe = new CadastrosLimitesDetalhe();
            detalhe.setGestor(3);
            
            //@todo verificar mudanca de codigo do orgao
            detalhe.setOrgao(517);
            
            detalhe.setBeneficiario(0);

            detalhe.setLocalTrabalho("FLEM");
            BeanUtils.copyProperties(detalhe, funcionario);
            layoutCadastrosLimites.add(detalhe);
        }   
        
        Calendar calendario = new GregorianCalendar();
        calendario.setTime(new Date());
        
        //gera o nome do arquivo com o número do próximo mês
        calendario.add(Calendar.MONTH, 1);
        return new Arquivo().geraArquivo(layoutCadastrosLimites,  
                "FLEM" + new SimpleDateFormat("MMyyyy").format(calendario.getTime()) + ".txt");
    }    
    
    public ArquivoCredicesta obterArquivoCredicestaPorPk(Serializable id) throws AcessoDadosException {
        return new ArquivoCredicestaDAO().obterPorPk(id);
    }

    public List<ArquivoCredicesta> obterTodosArquivoCredicestaOrdenado() throws AcessoDadosException {
        return new ArquivoCredicestaDAO().obterTodosOrdenado();
    }
    
    /**
     * Persiste o arquivo(físico) credicesta
     */
    public void inserirArquivoCredicesta(ArquivoCredicesta arquivoCredicesta) throws AplicacaoException, ParseException, IOException {

        Date agora = new Date();
            
    
        //arquivoFiltro será passado como critério para o método obterPorFiltro
        ArquivoCredicesta arquivoFiltro = new ArquivoCredicesta();

        arquivoFiltro.setNome(arquivoCredicesta.getNome());
        arquivoFiltro.setDataGravacao(arquivoCredicesta.getDataGravacao());

        //recuperacao da data de referencia diretamente do arquivo
        LayoutCredicestaVO layoutCredicestaVO = montarLayoutCredicesta(arquivoCredicesta);
        Date dataReferencia = layoutCredicestaVO.getHeader().getData();
        arquivoFiltro.setDataReferencia(dataReferencia);

        ArquivoCredicestaDAO arquivoCredicestaDAO = new ArquivoCredicestaDAO();
        List<ArquivoCredicesta> listaArquivosEquivalentes = arquivoCredicestaDAO.obterPorFiltro(arquivoFiltro);

        if (!listaArquivosEquivalentes.isEmpty()) {
            throw new RegistroJaExistenteException("Registro já existe para a referência " 
                    + new SimpleDateFormat("MM/yyyy").format(dataReferencia));
        }

        arquivoCredicesta.setDataGravacao(agora);            
        arquivoCredicesta.setNome("FLEM" + new SimpleDateFormat("MMyyyy").format(dataReferencia) + "ELET"); 

        arquivoCredicesta.setDataReferencia(dataReferencia);
        HibernateUtil.beginTransaction();
        arquivoCredicestaDAO.inserir(arquivoCredicesta);
        HibernateUtil.commitTransaction();
    }

    public File gerarArquivoBatch(ArquivoCredicesta arquivoCredicesta) throws ParseException, IOException {
        LayoutCredicestaVO layoutCredicestaVO = montarLayoutCredicesta(arquivoCredicesta);
        return gerarArquivoBatch(layoutCredicestaVO);        
    }   
    
    public File gerarArquivoRetornoConciliacao(ArquivoCredicesta arquivoCredicesta) throws ParseException, IOException {
        LayoutCredicestaVO layoutCredicestaVO = montarLayoutCredicesta(arquivoCredicesta);
        return gerarArquivoRetornoConciliacao(layoutCredicestaVO);        
    } 
}

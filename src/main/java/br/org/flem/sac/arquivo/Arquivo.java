/*
 * Arquivo.java
 *
 *Classe responsavel por criar os arquivos.
 *
 * Created on 10 de Novembro de 2006, 09:12
 */
package br.org.flem.sac.arquivo;

import br.org.flem.fwe.util.Data;
import br.org.flem.fwe.util.StringUtil;
import br.org.flem.fwe.util.Valores;
import br.org.flem.sac.dto.autonomo.FolhaAutonomoDTO;
import br.org.flem.sac.util.IOUtil;
import br.org.flem.sac.util.Propriedades;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author mjpereira
 */
public class Arquivo {

    /**
     * Creates a new instance of Arquivo
     */
    public Arquivo() {
        System.setProperty("user.dir", Propriedades.getInstancia().get("tempdir"));
    }

    public List<String> getCabecalho(int pagina, String mes) {
        List lista = new ArrayList();
        StringBuffer sb = IOUtil.linhaVazia(133);
        sb.replace(0, 133, "FUND. LUIS EDUARDO MAGALHAES                RELAT. ANALITICO DE CALCULO (FOL.PAGAM.)                 DT.REF.: " + mes + "    PAG.:    " + StringUtil.formatarCampoCaracterAlinhadoDireita(pagina + "", 2));
        lista.add(sb);
        sb = IOUtil.linhaVazia(133);
        sb.replace(0, 133, "RH2100                                                                                                     " + Data.formataData(new Date()) + "          " + Data.formataHoraCurta(new Date()));
        lista.add(sb);
        sb = IOUtil.linhaVazia(132);
        sb.replace(0, 133, "BANCOS-BANCOS   RP ADM GERAL-RP ADM GER   SUPAF-SUPAF   NGA-NGA                                                                    ");
        lista.add(sb);
        sb = IOUtil.linhaVazia(132);
        sb.replace(0, 132, StringUtil.repetirTexto("-", 133));
        lista.add(sb);
        sb = IOUtil.linhaVazia(130);
        sb.replace(0, 128, StringUtil.formatarCampoCaracterAlinhadoDireita("DEPENDENTES", 129));
        lista.add(sb);
        sb = IOUtil.linhaVazia(133);
        sb.replace(0, 133, "CAT. PRONTUARIO NOME                           ADMISSAO    RESCISAO   CARGO   SIND. SALARIO BASE       TS     H/MES   IR    SAL.FAM.");
        lista.add(sb);
        sb = IOUtil.linhaVazia(132);
        sb.replace(0, 132, StringUtil.repetirTexto("-", 133));
        lista.add(sb);
        return lista;
    }

    /*
     *
     * TOTAL DE FUNCIONARIOS :     28 NA LOTACAO : OUTROS
     FUND. LUIS EDUARDO MAGALHAES                TOTAIS DE VERBAS (RESUMO DA FOLHA)                       DT.REF.: 10/2009    PAG.:    24
     RH2112                          CATEG.: CLT EST CALC.: 01 02 05                                            26/10/2009          09:56
     BANCOS-BANCOS
     ------------------------------------------------------------------------------------------------------------------------------------
     PROVENTOS                                                  DESCONTOS
     ------------------------------------------------------------------------------------------------------------------------------------
     *
     *
     */
    public List<String> getCabecalhoSintetico(int pagina, int aut, String mes) {
        List lista = new ArrayList();
        StringBuffer sb = IOUtil.linhaVazia(133);
        sb.replace(0, 133, "TOTAL DE FUNCIONARIOS :     " + StringUtil.formatarCampoCaracterAlinhadoDireita(aut + "", 2) + " NA LOTACAO : OUTROS      ");
        lista.add(sb);
        sb = IOUtil.linhaVazia(133);
        sb.replace(0, 133, "FUND. LUIS EDUARDO MAGALHAES                TOTAIS DE VERBAS (RESUMO DA FOLHA)                       DT.REF.: " + mes + "    PAG.:    " + StringUtil.formatarCampoCaracterAlinhadoDireita(pagina + "", 2));
        lista.add(sb);
        sb = IOUtil.linhaVazia(133);
        sb.replace(0, 133, "RH2112                          CATEG.: AUT     CALC.: 01                                                  " + Data.formataData(new Date()) + "          " + Data.formataHoraCurta(new Date()));
        lista.add(sb);
        sb = IOUtil.linhaVazia(132);
        sb.replace(0, 133, "BANCOS-BANCOS                                                                    ");
        lista.add(sb);
        sb = IOUtil.linhaVazia(132);
        sb.replace(0, 132, StringUtil.repetirTexto("-", 133));
        lista.add(sb);
        sb = IOUtil.linhaVazia(130);
        sb.replace(0, 133, "                 PROVENTOS                                                  DESCONTOS");
        lista.add(sb);
        sb = IOUtil.linhaVazia(132);
        sb.replace(0, 132, StringUtil.repetirTexto("-", 133));
        lista.add(sb);
        return lista;
    }

    public StringBuffer getlinha() {
        StringBuffer sb = IOUtil.linhaVazia(133);
        sb.replace(0, 133, StringUtil.repetirTexto("-", 133));
        return sb;
    }

    public List<String> getlinhaBases() {
        List lista = new ArrayList();
        StringBuffer sb = IOUtil.linhaVazia(133);
        sb = IOUtil.linhaVazia(132);
        sb.replace(0, 132, StringUtil.repetirTexto("-", 133));
        lista.add(sb);
        sb = IOUtil.linhaVazia(130);
        sb.replace(0, 133, "                 BASES                                                      BASES    ");
        lista.add(sb);
        sb = IOUtil.linhaVazia(132);
        sb.replace(0, 132, StringUtil.repetirTexto("-", 133));
        lista.add(sb);
        return lista;
    }

    public synchronized void geraArquivo(List<Layout> lista, File arquivo) throws IOException {
        List<String> linhas = new ArrayList<String>();
        for (Layout l : lista) {
            linhas.add(l.toLayout());
        }

        if (!arquivo.exists()) {
            arquivo.createNewFile();
        }
        FileUtils.writeLines(arquivo, null, linhas);
        FileUtils.writeLines(arquivo, "ISO-8859-1", linhas);
    }

    public synchronized void geraArquivo(List<Layout> lista, File arquivo, String mes) throws IOException {
        int pagina = 1;
        int linha = 7;
        List<String> linhas = new ArrayList<String>();
        linhas.addAll(getCabecalho(pagina, mes));

        for (Layout l : lista) {
            linha = linha + l.toLayoutCollection().size();
            if (linha > 56) {
                linhas.add((char) '\n' + (char) '\r' + "");
                linha = 7;
                pagina++;
                linhas.addAll(getCabecalho(pagina, mes));
            }
            linhas.addAll(l.toLayoutCollection());
        }

        if (!arquivo.exists()) {
            arquivo.createNewFile();
        }
        FileUtils.writeLines(arquivo, null, linhas);
        FileUtils.writeLines(arquivo, "ISO-8859-1", linhas);
    }

    public synchronized void geraArquivoSintetico(List<FolhaAutonomoDTO> lista, File arquivo, String mes) throws IOException {
        double base = 0d;
        double ir = 0d;
        double iss = 0d;
        double insd = 0d;
        double insn = 0d;
        int autonomos = 0;
        for (FolhaAutonomoDTO fa : lista) {
            base = base + fa.getEntidade().getBase();
            ir = ir + fa.getEntidade().getIr();
            iss = iss + fa.getEntidade().getIss();
            insn = insn + fa.getEntidade().getInsn();
            insd = insd + fa.getEntidade().getInsd();
            autonomos++;
        }

        double liquido = base - (iss + insd + ir);

        int pagina = 1;
        List<String> linhas = new ArrayList<String>();
        linhas.addAll(getCabecalhoSintetico(pagina, autonomos, mes));
        StringBuffer sb = IOUtil.linhaVazia(133);
        sb.replace(14, 30, "009 REND. BR AUT");
        sb.replace(54, 69, StringUtil.formatarCampoCaracterAlinhadoDireita(Valores.formataMoeda(base) + "", 15));
        sb.replace(73, 98, "394 INSS CT INDI  11,0000");
        sb.replace(113, 127, StringUtil.formatarCampoCaracterAlinhadoDireita(Valores.formataMoeda(insd) + "", 15));
        linhas.add(sb.toString());

        sb = IOUtil.linhaVazia(133);
        sb.replace(73, 98, "395 ISS RET AUTO   5,0000");
        sb.replace(113, 127, StringUtil.formatarCampoCaracterAlinhadoDireita(Valores.formataMoeda(iss) + "", 15));
        linhas.add(sb.toString());

        sb = IOUtil.linhaVazia(133);
        sb.replace(73, 98, "396 IRRF                 ");
        sb.replace(113, 127, StringUtil.formatarCampoCaracterAlinhadoDireita(Valores.formataMoeda(ir) + "", 15));
        linhas.add(sb.toString());

        linhas.addAll(getlinhaBases());

        sb = IOUtil.linhaVazia(133);
        sb.replace(14, 30, "900 INSS EMP TOT  20,0000");
        sb.replace(54, 69, StringUtil.formatarCampoCaracterAlinhadoDireita(Valores.formataMoeda(insn) + "", 15));
        sb.replace(73, 98, "901 REND. LQ. AU         ");
        sb.replace(113, 127, StringUtil.formatarCampoCaracterAlinhadoDireita(Valores.formataMoeda(liquido) + "", 15));
        linhas.add(sb.toString());


        if (!arquivo.exists()) {
            arquivo.createNewFile();
        }
        FileUtils.writeLines(arquivo, null, linhas);
        FileUtils.writeLines(arquivo, "ISO-8859-1", linhas);

    }

    /**
     * Cria um arquivo a partir do layout
     */
    public synchronized void preencheArquivo(List<Layout> lista, String nomeArquivo) throws IOException {
        File arquivo = new File(nomeArquivo);
        List<String> linhas = new ArrayList<String>();
        for (Layout l : lista) {
            linhas.add(l.toLayout());
        }

        FileUtils.writeLines(arquivo, "ISO-8859-1", linhas);

    }

    /**
     * Retorna um arquivo com o layout
     */
    public synchronized File geraArquivo(List<Layout> lista) throws IOException {
        //o retorno deve ser utilizado em algum buffer
        File arquivo = new File(Propriedades.getInstancia().get("tempdir") + "erpservico_arquivo");

        List<String> linhas = new ArrayList<String>();
        for (Layout l : lista) {
            linhas.add(l.toLayout());
        }
        FileUtils.writeLines(arquivo, "ISO-8859-1", linhas);
        return arquivo;

    }

    /**
     * Retorna um arquivo com o layout
     */
    public synchronized File geraArquivo(List<Layout> lista, String nomeArquivo) throws IOException {
        //o retorno deve ser utilizado em algum buffer
        File arquivo = new File(Propriedades.getInstancia().get("tempdir") + nomeArquivo);

        List<String> linhas = new ArrayList<String>();
        for (Layout l : lista) {
            linhas.add(l.toLayout());
        }

        FileUtils.writeLines(arquivo, "ISO-8859-1", linhas);
        return arquivo;

    }

    public synchronized byte[] lerArquivo(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();

        long len = fc.size();
        if (len > Integer.MAX_VALUE) {
            throw new Exception("Impossível ler: o arquivo é muito grande.");
        }
        byte[] data = new byte[(int) len];
        ByteBuffer bb = ByteBuffer.wrap(data);
        fc.read(bb);
        return data;
    }
}

/*
 * AutonomosBO.java
 *
 * Created on 21/08/2007, 18:31:55
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.bo;
 
import br.org.flem.fwe.exception.AcessoDadosException;
import br.org.flem.fw.persistencia.dto.Entidade;
import br.org.flem.fw.service.FlemNet;
import br.org.flem.fw.service.GEM;
import br.org.flem.fw.service.IEntidade;
import br.org.flem.fw.service.IPessoa;
import br.org.flem.fw.service.impl.FlemNetImpl;
import br.org.flem.fw.service.impl.GEMImpl;
import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.arquivo.Arquivo;
import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.dao.autonomo.AutonomoDAO;
import br.org.flem.sac.dto.autonomo.AutonomoDTO;
import br.org.flem.sac.dto.autonomo.FolhaAutonomoDTO;
import br.org.flem.sac.dto.autonomo.ValorAutonomoDTO;
import br.org.flem.sac.dto.autonomo.ValoresAnoDTO;
import br.org.flem.sac.negocio.Autonomo;
import br.org.flem.sac.util.Propriedades;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mjpereira
 */
public class AutonomosBO {

    public AutonomosBO() {
    }
    
    public List<IEntidade> obterAutonomosConsistentes() {
        GEM gem = new GEMImpl();
        List<IEntidade> lista = new ArrayList<IEntidade>();
        for (IEntidade entidade : gem.obterListaAutonomos()) {
            if (validarPIS(entidade.getCodigoContribuicao())){
                lista.add(entidade);
            }
        }
        return lista;
    }

    public List<IEntidade> obterAutonomosInconsistentes() {
        GEM gem = new GEMImpl();
        List<IEntidade> lista = new ArrayList<IEntidade>();
        for (IEntidade entidade : gem.obterListaAutonomos()) {
            if (!validarPIS(entidade.getCodigoContribuicao())){
                lista.add(entidade);
            }
        }
        return lista;
    }


    public static boolean validarPIS (String plPIS) {
        int liTamanho = 0;
        StringBuffer lsAux = null;
        StringBuffer lsMultiplicador = new StringBuffer("3298765432");
        int liTotalizador = 0;
        int liResto = 0;
        int liMultiplicando = 0;
        int liMultiplicador = 0;
        boolean lbRetorno = true;
        int liDigito = 99;

        lsAux = new StringBuffer().append(plPIS);
        liTamanho = lsAux.length();

        if (liTamanho != 11) {
            lbRetorno = false;
        }

        if (lbRetorno) {
            for (int i=0; i<10; i++) {
                liMultiplicando = Integer.parseInt(lsAux.substring(i, i+1));
                liMultiplicador = Integer.parseInt(lsMultiplicador.substring(i, i+1));
                liTotalizador += liMultiplicando * liMultiplicador;
            }

            liResto = 11 - liTotalizador % 11;
            liResto = liResto == 10 || liResto == 11 ? 0 : liResto;

            liDigito = Integer.parseInt("" + lsAux.charAt(10));
            lbRetorno = liResto == liDigito;
        }

        return lbRetorno;
    }

    public void gerarArquivoAutonomos() throws IOException {
        List<Layout> lista = new ArrayList<Layout>();
        for (IEntidade entidade : obterAutonomosConsistentes()) {
            AutonomoDTO autonomo = new AutonomoDTO();
            autonomo.setEntidade(entidade);
            lista.add(autonomo);
        }
        //                                 /mnt/workfile_hr_producao/ + HRAUTON.TXT
        String arquivo = Propriedades.getInstancia().get("workdirHR") + Propriedades.getInstancia().get("arquivo.autonomos");
        new Arquivo().geraArquivo(lista, new File(arquivo));
    }



    @SuppressWarnings(value = "unchecked")
    public void atulizarNumeroINSSAutonomosFlemNetParaGEM() throws SQLException {
        List lista = new ArrayList();
        FlemNet flemnet = new FlemNetImpl();
        for (IPessoa pessoa : flemnet.obterTodasPessoasComInssPreenchido()) {
            if (pessoa.getInss().trim().length()==11) {
                Entidade entidade = new Entidade();
                entidade.setCodigo(StringUtil.formatarCodigo(String.valueOf(pessoa.getId())));
                entidade.setCodigoContribuicao(pessoa.getInss());
                lista.add(entidade);
            }
        }
        GEM gem = new GEMImpl();
        gem.atualizarEntidadeCampoUsuario3(lista);
    }

    @SuppressWarnings(value = "unchecked")
    public List<ValorAutonomoDTO> obterAutonomosComImpostos(String mes, String ano) {
        List<ValorAutonomoDTO> lista = new ArrayList<ValorAutonomoDTO>();
        GEM gem = new GEMImpl();
        System.out.println(mes + " / " + ano);
        for (IEntidade entidade : gem.obterEntidadesAutonomosComImpostos(mes, ano)) {
            
            //Só add no List se o codigo da Entidade começar com 'P'
            
            //if(entidade.getCodigo().substring(0, 1).equals("P") || (entidade.getCodigoAntigo().length() > 0 && entidade.getCodigoAntigo().substring(0, 1).equals("P"))){
                
                //System.out.println(entidade.getCodigo() + " , " + entidade.getCodigoAntigo());
          
                ValorAutonomoDTO vadto = new ValorAutonomoDTO();
                vadto.setEntidade(entidade);
                vadto.setDataReferencia(mes + "" + ano);
                lista.add(vadto);
            //}
        }
        //System.out.println(lista.size());
       return lista;
    }
    
    @SuppressWarnings(value = "unchecked")
    public void geraArquivoValoresGEMParaHR(String mes, String ano) throws SQLException, IOException {
        List<Layout> lista = new ArrayList(this.obterAutonomosComImpostos(mes, ano));
        String arquivo = Propriedades.getInstancia().get("workdirHR") + Propriedades.getInstancia().get("arquivo.valores");
        new Arquivo().geraArquivo(lista, new File(arquivo));
    }


    @SuppressWarnings(value = "unchecked")
    public List<FolhaAutonomoDTO> obterAutonomosComImpostosParaFolha(String mes, String ano) {
        List<FolhaAutonomoDTO> lista = new ArrayList<FolhaAutonomoDTO>();
        GEM gem = new GEMImpl();
        for (IEntidade entidade : gem.obterEntidadesAutonomosComImpostos(mes, ano)) {
            FolhaAutonomoDTO vadto = new FolhaAutonomoDTO();
            vadto.setEntidade(entidade);
            vadto.setDataReferencia(mes+""+ano);
            lista.add(vadto);
        }
        return lista;
    }
    
    private List<FolhaAutonomoDTO> folhaAutonomosPagosViaFluxoCaixa(int mes, int ano){
        Autonomo aut = new Autonomo();
        aut.setAno(ano);
        aut.setMes(mes);
        List<Autonomo> lista = new ArrayList<Autonomo>();
        try {
            lista = new AutonomoDAO().obterPorFiltro(aut);
        } catch (AcessoDadosException ex) {
            Logger.getLogger(AutonomosBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<FolhaAutonomoDTO> listaRetorno = new ArrayList<FolhaAutonomoDTO>();
        for(Autonomo a : lista){
            FolhaAutonomoDTO fa = new FolhaAutonomoDTO();
            Entidade entidade =  new Entidade();
            entidade.setBase(a.getBase());
            entidade.setInsd(a.getInsd());
            entidade.setInsn(a.getInsn());
            entidade.setIr(a.getIr());
            entidade.setIss(a.getIss());
            entidade.setNomeExtenso(a.getNome());
            entidade.setNomeAbreviado(a.getNome());
            entidade.setCodigo(a.getCodigo());
            fa.setEntidade(entidade);
            listaRetorno.add(fa);
        }
        return listaRetorno;
    }

    private void folhaSintetico( List lista, String mes, String ano, String caminho) throws IOException{
         String arquivo = caminho + "temp/HR_"+mes+""+ano+"_sintetico.txt";
         new Arquivo().geraArquivoSintetico(lista, new File(arquivo),mes+"/"+ano);
    }

    public void folhaAnalitico( List<Layout> lista, String mes, String ano, String caminho) throws IOException{
        String arquivo = caminho + "temp/HR_"+mes+""+ano+"_analitico.txt";
        new Arquivo().geraArquivo(lista, new File(arquivo),mes+"/"+ano);
    }

    @SuppressWarnings(value = "unchecked")
    public void geraFolhaAutonomos(String mes, String ano,String caminho) throws SQLException, IOException {
        List<Layout> lista = new ArrayList(this.obterAutonomosComImpostosParaFolha(mes, ano));
        lista.addAll(this.folhaAutonomosPagosViaFluxoCaixa(Integer.parseInt(mes), Integer.parseInt(ano)));
        this.folhaSintetico(lista, mes, ano, caminho);
        this.folhaAnalitico(lista, mes, ano, caminho);
    }

    


    /**
     *
     *  Obter numero de dependentes do flemnet
     *
     **/
    public int obterNumeroDependentes(String codigo) throws SQLException {
        int idt = StringUtil.parseCodigo(codigo);
        FlemNet flemnet = new FlemNetImpl();
        IPessoa pessoa = flemnet.obterPessoaPorId(idt);
        return pessoa.getDependentes();
    }

    /**
     *
     *
     *
     *
     *
     **/
    public List<ValoresAnoDTO> obterAutonomosComImpostos(String ano) throws Exception {
        Map<String, ValoresAnoDTO> map = new HashMap<String, ValoresAnoDTO>();
        GEM gem = new GEMImpl();
        FlemNet flemnet = new FlemNetImpl();
        String codigo = "";
        for (int i = 1; i <= 12; i++) {
            String mes = i < 10 ? "0" + i : i + "";
            for (IEntidade entidade : gem.obterEntidadesAutonomosComImpostos(mes, ano)) {
                IPessoa pessoa = null;
                codigo = entidade.getCodigo();
                if(codigo.length() > 8){
                    pessoa = flemnet.obterPessoaPorCPF(codigo);
                }else{
                    pessoa = flemnet.obterPessoaPorId(StringUtil.parseCodigo(codigo));
                }
                
                if (pessoa != null) {
                    ValoresAnoDTO dtoAno = map.containsKey(entidade.getCodigo()) ? map.get(entidade.getCodigo()) : ValoresAnoDTO.class.newInstance();
                    dtoAno.setEntidade(entidade);
                    dtoAno.somatorioBase(entidade.getBase());
                    dtoAno.setDependentes(pessoa.getDependentes());
                    Class[] type = new Class[1];
                    type[0] = Double.TYPE;
                    Method m1 = dtoAno.getClass().getMethod("setIr" + i, type);
                    Object[] arg1 = new Object[1];
                    arg1[0] = new Double(entidade.getIr());
                    m1.invoke(dtoAno, arg1);
                    Method m2 = dtoAno.getClass().getMethod("setInsd" + i, type);
                    Object[] arg2 = new Object[1];
                    arg2[0] = new Double(entidade.getInsd());
                    m2.invoke(dtoAno, arg2);
                    map.put(dtoAno.getEntidade().getCodigo(), dtoAno);
                }
            }
        }
        return new ArrayList<ValoresAnoDTO>(map.values());
    }
    
    public static void main(String[] args) {

        List<ValoresAnoDTO> list = null;
        try {
            list = new AutonomosBO().obterAutonomosComImpostos("2014");
            System.out.println(list.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        //new AutonomosBO().obterAutonomosConsistentes();
        
        /*String pisIan = "13526603315";//135.26603.31-5
        String pisMurilo = "12824230055";//128.24230.05-5

        String pis = pisMurilo;
        pis = pisIan;
        pis = "12524400070";

        if(validarPIS(pis)){
            System.out.println("PIS Correto!");
        }else{
            System.out.println("PIS Incorreto!");
        }*/
    }
}

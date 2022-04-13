/*
 * DWRFuncoes.java
 * Created on 9 de Outubro de 2006, 11:45
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package br.org.flem.sac.web.dwr;

import br.org.flem.fw.persistencia.dto.Departamento;
import br.org.flem.fw.service.IDependente;
import br.org.flem.fw.service.IFuncionario;
import br.org.flem.fw.service.impl.RHServico;
import br.org.flem.sac.bo.AutonomosBO;
import br.org.flem.sac.dto.planserv.Beneficiario;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;

/**
 *
 * @author MJPEREIRA
 */
//
@RemoteProxy(name = "Funcoes")
public class DWRFuncoes {

    /**
     * Creates a new instance of DWRFuncoes
     */
    public DWRFuncoes() {
    }

    @RemoteMethod
    @SuppressWarnings(value = "unchecked")
    public Beneficiario obterDadosPlanserv(Integer matricula, Integer numero) throws IOException {
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setMatricula(String.valueOf(matricula));
        RHServico rh = new RHServico();
        if (numero.equals(matricula)) {
            IFuncionario func = rh.obterFuncionarioPorMatricula(matricula);
            if (func.getPlano() != null) {
                beneficiario.setCdPlano(String.valueOf(func.getPlano().getCodigoPlanserv()));
            }
        } else {
            Map<Integer, IDependente> map = rh.obterDependenteFuncionarioPorMatricula(matricula);
            IDependente dependente = map.get(numero - matricula);
            if (dependente.getPlano() != null) {
                beneficiario.setCdPlano(String.valueOf(dependente.getPlano().getCodigoPlanserv()));
            }
        }
        return beneficiario;
    }

    @RemoteMethod
    @SuppressWarnings(value = "unchecked")
    public String geraArquivoAutonomo() {
        try {
            new AutonomosBO().gerarArquivoAutonomos();
            return "ok";
        } catch (IOException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            return "error";
        }
    }

    @RemoteMethod
    @SuppressWarnings(value = "unchecked")
    public String atualizarInssAutonomo() {
        try {
            new AutonomosBO().atulizarNumeroINSSAutonomosFlemNetParaGEM();
            return "ok";
        } catch (SQLException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            return "erro";
        }
    }

    @RemoteMethod
    @SuppressWarnings(value = "unchecked")
    public String geraArquivoValores(String mes, String ano) {
        try {
            new AutonomosBO().geraArquivoValoresGEMParaHR(mes, ano);
            return "ok";
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            return "erro";
        }
    }

    @RemoteMethod
    @SuppressWarnings(value = "unchecked")
    public String geraFolhaAutonomos(String mes, String ano, String caminho) {
        try {
            new AutonomosBO().geraFolhaAutonomos(mes, ano, caminho);
            return "ok";
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            return "erro";
        }
    }

    @RemoteMethod
    public Map obterListaPlanserv(int matricula) {
        Map<Integer, String> map = new TreeMap<Integer, String>();
        RHServico rh = new RHServico();
        IFuncionario func = rh.obterFuncionarioPorMatricula(matricula);
        map.put(func.getMatricula(), func.getNome() + " - " + "TITULAR");
        Map<Integer, IDependente> dependentes = rh.obterDependenteFuncionarioPorMatricula(matricula);
        for (IDependente dependente : dependentes.values()) {
            map.put(matricula + dependente.getSequencia(), dependente.getNome() + " - " + dependente.getParentesco().getDescricao().toUpperCase());
        }
        return map;
    }

    @RemoteMethod
    public String obterDataAdmissao(int matricula) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        IFuncionario func = new RHServico().obterFuncionarioPorMatricula(matricula);
        return sdf.format(func.getAdmissao());
    }

    @RemoteMethod
    public String obterDepartamento(String codigo) {
        Departamento departamento = new RHServico().obterDepartamentoPorCodigo(codigo);
        return departamento.getCodigoDominio() + ";" + departamento.getNome();

    }

    @RemoteMethod
    public String obterNomeCompleto(final String codigo) {
        final IFuncionario funcionario = new RHServico().obterFuncionarioPorMatricula(Integer.parseInt(codigo));
        return funcionario.getNome();
    }

    @RemoteMethod
    public String obterDepartamentoDoFuncionario(String codigo) {
        IFuncionario f = new RHServico().obterFuncionarioPorMatricula(Integer.parseInt(codigo));
        return f.getDepartamento().getNome();
    }
}

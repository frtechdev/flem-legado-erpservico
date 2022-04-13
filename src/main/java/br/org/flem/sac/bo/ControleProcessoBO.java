/*
 * ControleProcessoBO.java
 * 
 * Created on 28/08/2007, 16:52:58
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.flem.sac.bo;

import br.org.flem.fw.service.Colaboradores;
import br.org.flem.fw.service.ControleProcesso;
import br.org.flem.fw.service.impl.ColaboradoresImpl;
import br.org.flem.fw.service.impl.ControleProcessoImpl;
import br.org.flem.sac.dto.controleprocessos.Processo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mjpereira
 */
public class ControleProcessoBO {

    public ControleProcessoBO() {
    }

    @SuppressWarnings("unchecked")
    public List<Processo> obterConsolidacaoProcessoMesAno(String mes, String ano) {
        ControleProcesso cp = new ControleProcessoImpl();
        Colaboradores colaboradores = new ColaboradoresImpl();
        Map<String, String> fonteMap = colaboradores.obterMapCentroDeCustoProjeto();
        Map<String, Integer> map = cp.obterQuantidadeProcessosFonteRecursoPorMesAno(mes, ano);
        List<Processo> lista = new ArrayList<Processo>();
        for (String key : map.keySet()) {
            Processo processo = new Processo();
            String rec = fonteMap.get(key.trim());
            if (rec != null) {
                processo.setNomeFonteRecurso(rec);
            }
            processo.setFonteRecurso(key);
            processo.setQuantidade(map.get(key));
            lista.add(processo);
        }
        return lista;

    }

}

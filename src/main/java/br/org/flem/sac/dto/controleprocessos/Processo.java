/*
 * Processo.java
 *
 * Created on 28/08/2007, 16:54:36
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.dto.controleprocessos;

import java.io.Serializable;

/**
 *
 * @author mjpereira
 */
public class Processo implements Serializable {

    private String fonteRecurso;
    private String nomeFonteRecurso;

    public String getNomeFonteRecurso() {
        return nomeFonteRecurso;
    }

    public void setNomeFonteRecurso(String nomeFonteRecurso) {
        this.nomeFonteRecurso = nomeFonteRecurso;
    }
    private int quantidade;


    public Processo() {
    }

    public String getFonteRecurso() {
        return fonteRecurso;
    }

    public void setFonteRecurso(String fonteRecurso) {
        this.fonteRecurso = fonteRecurso;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    
}

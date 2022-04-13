/*
 * LayoutSaebCompleto.java
 *
 * Classe que encapsula o header o o detalhe do Layout Saeb
 */

package br.org.flem.sac.layout.ebal.credicesta;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author fcsilva
 */
public class LayoutCredicestaVO {

    public LayoutCredicestaVO() {
    }

    private CredicestaHeader header;
    private List<CredicestaDetalhe> detalhe = new ArrayList<CredicestaDetalhe>();

    public CredicestaHeader getHeader() {
        return header;
    }

    public void setHeader(CredicestaHeader header) {
        this.header = header;
    }

    public List<CredicestaDetalhe> getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(List<CredicestaDetalhe> detalhe) {
        this.detalhe = detalhe;
    }
}

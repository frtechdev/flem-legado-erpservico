/*
 * CadastrosLimitesHeader.java
 *
 * Created on 22 de Agosto de 2007, 09:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.sac.layout.ebal.cadastroslimites;

import br.org.flem.sac.arquivo.Layout;
import br.org.flem.sac.util.IOUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CadastrosLimitesHeader implements Layout{
    
    private SimpleDateFormat formatoMes = new SimpleDateFormat("MM");
    private SimpleDateFormat formatoAno = new SimpleDateFormat("yyyy");    
    
    private String layout;
    private Date data;

    public Date getData(){
        return data;
    }

    public void setData(Date data){
        this.data = data;
    }
    
    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getMes() {
        return formatoMes.format(getData());
    }

    public String getAno() {
        return formatoAno.format(getData());
    }
    
    public String toLayout() {
        StringBuffer sb = IOUtil.linhaVazia(9);
        sb.replace(0,2,this.getLayout());        
        sb.replace(3,4,String.valueOf(getMes()));
        sb.replace(5,8,String.valueOf(getAno()));
        return sb.substring(0,9);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

/*
 * MoedaWrapper.java
 *
 * Created on 13 de Setembro de 2006, 11:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.sac.web.displaytag;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.servlet.jsp.PageContext;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

/**
 *
 * @author mjpereira
 */
public class MoedaWrapper implements DisplaytagColumnDecorator{
    
    private static final Locale LOCAL = new Locale("pt","BR");  
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(LOCAL));

  /**
   * Method decorate
   * @param pColumnValue Object
   * @return Object
   */
    public Object decorate(Object object, PageContext pageContext, MediaTypeEnum mediaTypeEnum) throws DecoratorException {
        
        Double ldouble = (Double) object;
	return decimalFormat.format(ldouble.doubleValue());
    }
    
}

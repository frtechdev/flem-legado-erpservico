/*
 * MoedaWrapper.java
 *
 * Created on 13 de Setembro de 2006, 11:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.sac.web.displaytag;

import br.org.flem.sac.dto.autonomo.ValoresAnoDTO;
import java.text.DecimalFormat;
import javax.servlet.jsp.PageContext;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

/**
 *
 * @author mjpereira
 */
public class TotalInsdWrapper implements DisplaytagColumnDecorator{
    
private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

  /**
   * Method decorate
   * @param pColumnValue Object
   * @return Object
   */
    public Object decorate(Object object, PageContext pageContext, MediaTypeEnum mediaTypeEnum) throws DecoratorException {
        ValoresAnoDTO vadto = (ValoresAnoDTO) object;
	return decimalFormat.format(vadto.totalInsd());
    }
    
}

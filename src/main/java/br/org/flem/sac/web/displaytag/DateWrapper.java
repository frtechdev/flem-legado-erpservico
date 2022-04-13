/*
 * DateWrapper.java
 * 
 * Created on 06/09/2007, 15:18:56
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.sac.web.displaytag;

import java.util.Date;
import javax.servlet.jsp.PageContext;
import org.apache.commons.lang.time.FastDateFormat;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

/**
 *
 * @author fcsilva
 */
public class DateWrapper implements DisplaytagColumnDecorator{

    public DateWrapper() {
    }
    
    private FastDateFormat dateFormat = FastDateFormat.getInstance("dd/MM/yyyy"); 

    public Object decorate(Object columnValue, PageContext pageContext, 
            MediaTypeEnum media) throws DecoratorException 
    { 
        Date date = (Date) columnValue; 
        return this.dateFormat.format(date); 
    }    
    

}

/*
 * Layout.java
 * 
 */

package br.org.flem.sac.arquivo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mjpereira
 */
public interface Layout extends Serializable{
    
    String toLayout();
    List toLayoutCollection();
}

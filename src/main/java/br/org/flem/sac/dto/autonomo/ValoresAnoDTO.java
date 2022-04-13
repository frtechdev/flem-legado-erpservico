/*
 * ValoresAnoDTO.java
 *
 * Created on 1 de Fevereiro de 2007, 16:47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.sac.dto.autonomo;

import br.org.flem.fw.service.IEntidade;
import java.io.Serializable;

/**
 *
 * @author mjpereira
 */
public class ValoresAnoDTO implements Serializable{
    
    
    private double ir1 = 0d;
    private double insd1 = 0d;
    private double ir2 = 0d;
    private double insd2 = 0d;
    private double ir3 = 0d;
    private double insd3 = 0d;
    private double ir4 = 0d;
    private double insd4 = 0d;
    private double ir5 = 0d;
    private double insd5 = 0d;
    private double ir6 = 0d;
    private double insd6 = 0d;
    private double ir7 = 0d;
    private double insd7 = 0d;
    private double ir8 = 0d;
    private double insd8 = 0d;
    private double ir9 = 0d;
    private double insd9 = 0d;
    private double ir10 = 0d;
    private double insd10 = 0d;
    private double ir11 = 0d;
    private double insd11 = 0d;
    private double ir12 = 0d;
    private double insd12 = 0d;
    private double base = 0d;
    
    private IEntidade entidade;
    
    private int dependentes = 0;

    public int getDependentes() {
        return dependentes;
    }

    public void setDependentes(int dependentes) {
        this.dependentes = dependentes;
    }

    public IEntidade getEntidade() {
        return entidade;
    }

    public void setEntidade(IEntidade entidade) {
        this.entidade = entidade;
    }
    
    
    /** Creates a new instance of ValoresAnoDTO */
    public ValoresAnoDTO() {
    }

    
    public Double getIr1() {
        return ir1;
    }
    
    public void setIr1(double ir1) {
        this.ir1 = ir1;
    }
    
    public Double getInsd1() {
        return insd1;
    }
    
    public void setInsd1(double insd1) {
        this.insd1 = insd1;
    }
    
    public Double getIr2() {
        return ir2;
    }
    
    public void setIr2(double ir2) {
        this.ir2 = ir2;
    }
    
    public Double getInsd2() {
        return insd2;
    }
    
    public void setInsd2(double insd2) {
        this.insd2 = insd2;
    }
    
    public Double getIr3() {
        return ir3;
    }
    
    public void setIr3(double ir3) {
        this.ir3 = ir3;
    }
    
    public Double getInsd3() {
        return insd3;
    }
    
    public void setInsd3(double insd3) {
        this.insd3 = insd3;
    }
    
    public Double getIr4() {
        return ir4;
    }
    
    public void setIr4(double ir4) {
        this.ir4 = ir4;
    }
    
    public Double getInsd4() {
        return insd4;
    }
    
    public void setInsd4(double insd4) {
        this.insd4 = insd4;
    }
    
    public Double getIr5() {
        return ir5;
    }
    
    public void setIr5(double ir5) {
        this.ir5 = ir5;
    }
    
    public Double getInsd5() {
        return insd5;
    }
    
    public void setInsd5(double insd5) {
        this.insd5 = insd5;
    }
    
    public Double getIr6() {
        return ir6;
    }
    
    public void setIr6(double ir6) {
        this.ir6 = ir6;
    }
    
    public Double getInsd6() {
        return insd6;
    }
    
    public void setInsd6(double insd6) {
        this.insd6 = insd6;
    }
    
    public Double getIr7() {
        return ir7;
    }
    
    public void setIr7(double ir7) {
        this.ir7 = ir7;
    }
    
    public Double getInsd7() {
        return insd7;
    }
    
    public void setInsd7(double insd7) {
        this.insd7 = insd7;
    }
    
    public Double getIr8() {
        return ir8;
    }
    
    public void setIr8(double ir8) {
        this.ir8 = ir8;
    }
    
    public Double getInsd8() {
        return insd8;
    }
    
    public void setInsd8(double insd8) {
        this.insd8 = insd8;
    }
    
    public Double getIr9() {
        return ir9;
    }
    
    public void setIr9(double ir9) {
        this.ir9 = ir9;
    }
    
    public Double getInsd9() {
        return insd9;
    }
    
    public void setInsd9(double insd9) {
        this.insd9 = insd9;
    }
    
    public Double getIr10() {
        return ir10;
    }
    
    public void setIr10(double ir10) {
        this.ir10 = ir10;
    }
    
    public Double getInsd10() {
        return insd10;
    }
    
    public void setInsd10(double insd10) {
        this.insd10 = insd10;
    }
    
    public Double getIr11() {
        return ir11;
    }
    
    public void setIr11(double ir11) {
        this.ir11 = ir11;
    }
    
    public Double getInsd11() {
        return insd11;
    }
    
    public void setInsd11(double insd11) {
        this.insd11 = insd11;
    }
    
    public Double getIr12() {
        return ir12;
    }
    
    public void setIr12(double ir12) {
        this.ir12 = ir12;
    }
    
    public Double getInsd12() {
        return insd12;
    }
    
    public void setInsd12(double insd12) {
        this.insd12 = insd12;
    }
    
    public Double totalInsd(){
        return  insd1+insd2+insd3+insd4+insd5+insd6+insd7+insd8+insd9+insd10+insd11+insd12;
    }
    public Double totalIr(){
        return   ir1+ir2+ir3+ir4+ir5+ir6+ir7+ir8+ir9+ir10+ir11+ir12;
    }
    
    public double getBase() {
        return base;
    }
    
    public void setBase(double base) {
        this.base = base;
    }
    
    public void somatorioBase(double base) {
        this.base = this.base+base;
    }

    
}

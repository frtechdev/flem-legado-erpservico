/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.flem.sac.dto.receitas;

/**
 *
 * @author mjpereira
 */
public class ProjetoReceitaAnoDTO {

    private String projeto;
    private String centroCusto;
    private Double janeiro =0.0 ;
    private Double fevereiro = 0.0;
    private Double marco = 0.0;
    private Double abril = 0.0;
    private Double maio = 0.0;
    private Double junho = 0.0;
    private Double julho = 0.0;
    private Double agosto = 0.0;
    private Double setembro = 0.0;
    private Double outubro = 0.0;
    private Double novembro = 0.0;
    private Double dezembro = 0.0;
    private Double total = 0.0;

    /**
     * @return the projeto
     */
    public String getProjeto() {
        return projeto;
    }

    /**
     * @param projeto the projeto to set
     */
    public void setProjeto(String projeto) {
        this.projeto = projeto;
    }

    /**
     * @return the centroCusto
     */
    public String getCentroCusto() {
        return centroCusto;
    }

    /**
     * @param centroCusto the centroCusto to set
     */
    public void setCentroCusto(String centroCusto) {
        this.centroCusto = centroCusto;
    }

    /**
     * @return the janeiro
     */
    public Double getJaneiro() {
        return janeiro;
    }

    /**
     * @param janeiro the janeiro to set
     */
    public void setJaneiro(Double janeiro) {
        this.janeiro = janeiro;
    }

    /**
     * @return the fevereiro
     */
    public Double getFevereiro() {
        return fevereiro;
    }

    /**
     * @param fevereiro the fevereiro to set
     */
    public void setFevereiro(Double fevereiro) {
        this.fevereiro = fevereiro;
    }

    /**
     * @return the marco
     */
    public Double getMarco() {
        return marco;
    }

    /**
     * @param marco the marco to set
     */
    public void setMarco(Double marco) {
        this.marco = marco;
    }

    /**
     * @return the abril
     */
    public Double getAbril() {
        return abril;
    }

    /**
     * @param abril the abril to set
     */
    public void setAbril(Double abril) {
        this.abril = abril;
    }

    /**
     * @return the maio
     */
    public Double getMaio() {
        return maio;
    }

    /**
     * @param maio the maio to set
     */
    public void setMaio(Double maio) {
        this.maio = maio;
    }

    /**
     * @return the junho
     */
    public Double getJunho() {
        return junho;
    }

    /**
     * @param junho the junho to set
     */
    public void setJunho(Double junho) {
        this.junho = junho;
    }

    /**
     * @return the julho
     */
    public Double getJulho() {
        return julho;
    }

    /**
     * @param julho the julho to set
     */
    public void setJulho(Double julho) {
        this.julho = julho;
    }

    /**
     * @return the agosto
     */
    public Double getAgosto() {
        return agosto;
    }

    /**
     * @param agosto the agosto to set
     */
    public void setAgosto(Double agosto) {
        this.agosto = agosto;
    }

    /**
     * @return the setembro
     */
    public Double getSetembro() {
        return setembro;
    }

    /**
     * @param setembro the setembro to set
     */
    public void setSetembro(Double setembro) {
        this.setembro = setembro;
    }

    /**
     * @return the outubro
     */
    public Double getOutubro() {
        return outubro;
    }

    /**
     * @param outubro the outubro to set
     */
    public void setOutubro(Double outubro) {
        this.outubro = outubro;
    }

    /**
     * @return the novembro
     */
    public Double getNovembro() {
        return novembro;
    }

    /**
     * @param novembro the novembro to set
     */
    public void setNovembro(Double novembro) {
        this.novembro = novembro;
    }

    /**
     * @return the dezembro
     */
    public Double getDezembro() {
        return dezembro;
    }

    /**
     * @param dezembro the dezembro to set
     */
    public void setDezembro(Double dezembro) {
        this.dezembro = dezembro;
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    public void setValorMes(int mes, Double valor) {
        switch (mes) {
            case 0:
                this.janeiro = valor;
                break;
            case 1:
                this.fevereiro = valor;
                break;
            case 2:
                this.marco = valor;
                break;
            case 3:
                this.abril = valor;
                break;
            case 4:
                this.maio = valor;
                break;
            case 5:
                this.junho = valor;
                break;
            case 6:
                this.julho = valor;
                break;
            case 7:
                this.agosto = valor;
                break;
            case 8:
                this.setembro = valor;
                break;
            case 9:
                this.outubro = valor;
                break;
            case 10:
                this.novembro = valor;
                break;
            case 11:
                this.dezembro = valor;
                break;
        }

    }

    public Double getValorMes(int mes) {
        Double valor = 0.0;
        switch (mes) {
            case 0:
                valor = this.janeiro;
                break;
            case 1:
                valor = this.fevereiro;
                break;
            case 2:
                valor = this.marco;
                break;
            case 3:
                valor = this.abril;
                break;
            case 4:
                valor = this.maio;
                break;
            case 5:
                valor = this.junho;
                break;
            case 6:
                valor = this.julho;
                break;
            case 7:
                valor = this.agosto;
                break;
            case 8:
                valor = this.setembro;
                break;
            case 9:
                valor = this.outubro;
                break;
            case 10:
                valor = this.novembro;
                break;
            case 11:
                valor = this.dezembro;
                break;
        }
        return valor;
    }
}


package br.org.flem.sac.layout.rpa;

/**
 *
 * @author tscortes
 */
public enum GrauInstrucao {
    NAO_ALFABETIZADO("1", "01", "NAO_ALFABETIZADO"),
    ATE_4_SERIE_INCOMPLETA_DO_ENSINO_FUNDAMENTAL("2", "02", "ATE_4_SERIE_INCOMPLETA_DO_ENSINO_FUNDAMENTAL"),
    COM_4_SERIE_COMPLETA_DO_ENSINO_FUNDAMENTAL("3", "03", "COM_4_SERIE_COMPLETA_DO_ENSINO_FUNDAMENTAL"),
    DE_5_A_8_SERIE_INCOMPLETA_ENSINO_FUNDAMENTAL("4", "04", "DE_5_A_8_SERIE_INCOMPLETA_ENSINO_FUNDAMENTAL"),
    ENSINO_FUNDAMENTAL_COMPLETO("5", "05", "ENSINO_FUNDAMENTAL_COMPLETO"),
    ENSINO_MEDIO_INCOMPLETO("6", "06", "ENSINO_MEDIO_INCOMPLETO"),
    ENSINO_MEDIO_COMPLETO("7", "07", "ENSINO_MEDIO_COMPLETO"),
    SUPERIOR_INCOMPLETO("8", "08", "SUPERIOR_INCOMPLETO"),
    SUPERIOR_COMPLETO("9", "09", "SUPERIOR_COMPLETO"),
    ESPECIALIZACAO_POSGRADUACAO("13", "10", "ESPECIALIZACAO_POSGRADUACAO"),
    MESTRADO("10", "11", "MESTRADO"),
    DOUTORADO("11", "12", "DOUTORADO"),
    PHD("12", "X", "PHD");

    private String idDominio;
    private String idCaixa;
    private String descricao;

    private GrauInstrucao(final String idDominio, final String idCaixa, final String descricao) {
        this.idDominio = idDominio;
        this.idCaixa = idCaixa;
        this.descricao = descricao;
    }

    public String getIdDominio() {
        return this.idDominio;
    }

    public void setIdDominio(final String idDominio) {
        this.idDominio = idDominio;
    }

    public String getIdCaixa() {
        return this.idCaixa;
    }

    public void setIdCaixa(final String idCaixa) {
        this.idCaixa = idCaixa;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public static GrauInstrucao obterPorIdDominio(final String idDominio) {
        for (final GrauInstrucao g : values()) {
            if (g.idDominio.equals(idDominio)) {
                return g;
            }
        }
        return null;
    }

}

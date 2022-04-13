/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.flem.sac.layout.rpa;

/**
 *
 * @author tscortes
 */
public enum OrgaoExp {
    SSP("1", "SSP"),
    TRE("2", "EXT"),
    EXT("3", "SSP"),
    DRT("4", "DRT"),
    M_MILITAR("5", "SSP"),
    MIN_AER("6", "MAER"),
    MIN_EXER("7", "MEX"),
    MIN_MAR("8", "MAR"),
    DPF("9", "DPF"),
    INSS("10", "INSS"),
    SRF("11", "SRF"),
    CLASSISSTAS("12", "OUTRO"),
    CRA("13", "CRA"),
    CRAS("14", "CRAS"),
    CRB("15", "CRB"),
    CRC("16", "CRC"),
    CRECI("17", "CRECI"),
    COREN("18", "COREN"),
    CREA("19", "CREA"),
    CONRE("20", "CONRE"),
    CRF("21", "CRF"),
    CREFITO("22", "OUTRO"),
    CRM("23", "CRM"),
    CRMV("24", "CRMV"),
    OMB("25", "OMB"),
    CRN("26", "CRN"),
    CRO("27", "CRO"),
    CONRERP("28", "OUTRO"),
    CRP("29", "CRP"),
    CRQ("30", "CRQ"),
    CORE("31", "CORE"),
    OAB("32", "OAB"),
    OE("33", "OE"),
    DOC_ESTR("34", "OUTRO"),
    CRE("35", "CRE"),
    REG_CIVIL("36", "OUTRO"),
    DETRAN("37", "OUTRO"),
    CBM("38", "CBM");

    private String idOrgExpDominio;
    private String orgExpcaixa;

    private OrgaoExp(final String orgExpDominio, final String orgExpcaixa) {
        this.idOrgExpDominio = orgExpDominio;
        this.orgExpcaixa = orgExpcaixa;
    }

    public String getIdOrgExpDominio() {
        return this.idOrgExpDominio;
    }

    public void setIdOrgExpDominio(final String idOrgExpDominio) {
        this.idOrgExpDominio = idOrgExpDominio;
    }

    public String getOrgExpcaixa() {
        return this.orgExpcaixa;
    }

    public void setOrgExpcaixa(final String orgExpcaixa) {
        this.orgExpcaixa = orgExpcaixa;
    }

    public static OrgaoExp obterPorIdDominio(final String orgExpDominio) {
        for (final OrgaoExp o : values()) {
            if (o.getIdOrgExpDominio().equals(orgExpDominio)) {
                return o;
            }
        }
        return null;
    }
}

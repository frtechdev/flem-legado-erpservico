package br.org.flem.sac.layout.rpa;

import br.org.flem.sac.arquivo.Layout;
import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.util.IOUtil;
import java.util.List;

/**
 *
 * @author MCCosta
 */
public class ServicosL1 implements Layout {
    String percent[];
    
    // Linha 04 - (S) Serviços  
    private String tipoRegistroS = "100";   //A 03 1 - 3 Fixo "100"
    private Integer seqRegistroServicoS;     //N 05 4 - 8 
    private String codServicoFederalS;      //A 16 9 - 24 
    private String codServicoS;             //A 16 25 - 40 
    private String sinteticaS;              //A 250 41 - 290
    private String detalhadaServico1S;      //A 250 291 - 540
    private String detalhadaServico2S;      //A 250 541 - 790
    private String detalhadaServico3S;      //A 250 791 - 1040
    private String detalhadaServico4S;      //A 250 1041 - 1290
    private String paisS;                   //A 03 1291 - 1293 Local da Exec. do Serviço
    private String ufS;                     //A 03 1294 - 1296 Local da Exec. do Serviço
    private String cidadeS;                 //A 04 1297 - 1300 Local da Exec. do Serviço
    private String codSvcS;                 //A 16 1301 - 1316 Local da Exec. do Serviço
    private String unidMedidaS;             //A 05 1317 - 1321
    private Integer quantidadeS;             //N 13 1322 - 1334
    private Double valorUnitarioS;          //N 18 1335 - 1352 Utilizar sempre 4 casas decimais
    private Double descontoS;               //N 18 1353 - 1370
    private Double percentualDescontoS;     //N 3,7 1371 - 1380
    private String codAjusteDescontoS;      //A 04 1381 - 1384
    private String regraEscrituracaoS;      //A 07 1385 - 1391
    private Double valorSubContratoS;       //N 18 1392 - 1409
    private String campoUsuario1S;          //A 60 1410 - 1469
    private String campoUsuario2S;          //A 60 1470 - 1529
    private String campoUsuario3S;          //A 60 1530 - 1589
    private String campoUsuario4S;          //A 60 1590 - 1649
    private String campoUsuario5S;          //A 60 1650 - 1709
    
    public ServicosL1() {
    
    }

    public String getCampoUsuario1S() {
        return campoUsuario1S;
    }

    public void setCampoUsuario1S(String campoUsuario1S) {
        this.campoUsuario1S = campoUsuario1S;
    }

    public String getCampoUsuario2S() {
        return campoUsuario2S;
    }

    public void setCampoUsuario2S(String campoUsuario2S) {
        this.campoUsuario2S = campoUsuario2S;
    }

    public String getCampoUsuario3S() {
        return campoUsuario3S;
    }

    public void setCampoUsuario3S(String campoUsuario3S) {
        this.campoUsuario3S = campoUsuario3S;
    }

    public String getCampoUsuario4S() {
        return campoUsuario4S;
    }

    public void setCampoUsuario4S(String campoUsuario4S) {
        this.campoUsuario4S = campoUsuario4S;
    }

    public String getCampoUsuario5S() {
        return campoUsuario5S;
    }

    public void setCampoUsuario5S(String campoUsuario5S) {
        this.campoUsuario5S = campoUsuario5S;
    }

    public String getCidadeS() {
        return cidadeS;
    }

    public void setCidadeS(String cidadeS) {
        this.cidadeS = cidadeS;
    }

    public String getCodAjusteDescontoS() {
        return codAjusteDescontoS;
    }

    public void setCodAjusteDescontoS(String codAjusteDescontoS) {
        this.codAjusteDescontoS = codAjusteDescontoS;
    }

    public String getCodServicoFederalS() {
        return codServicoFederalS;
    }

    public void setCodServicoFederalS(String codServicoFederalS) {
        this.codServicoFederalS = codServicoFederalS;
    }

    public String getCodServicoS() {
        return codServicoS;
    }

    public void setCodServicoS(String codServicoS) {
        this.codServicoS = codServicoS;
    }

    public String getCodSvcS() {
        return codSvcS;
    }

    public void setCodSvcS(String codSvcS) {
        this.codSvcS = codSvcS;
    }

    public Double getDescontoS() {
        return descontoS;
    }

    public void setDescontoS(Double descontoS) {
        this.descontoS = descontoS;
    }

    public String getDetalhadaServico1S() {
        return detalhadaServico1S;
    }

    public void setDetalhadaServico1S(String detalhadaServico1S) {
        this.detalhadaServico1S = detalhadaServico1S;
    }

    public String getDetalhadaServico2S() {
        return detalhadaServico2S;
    }

    public void setDetalhadaServico2S(String detalhadaServico2S) {
        this.detalhadaServico2S = detalhadaServico2S;
    }

    public String getDetalhadaServico3S() {
        return detalhadaServico3S;
    }

    public void setDetalhadaServico3S(String detalhadaServico3S) {
        this.detalhadaServico3S = detalhadaServico3S;
    }

    public String getDetalhadaServico4S() {
        return detalhadaServico4S;
    }

    public void setDetalhadaServico4S(String detalhadaServico4S) {
        this.detalhadaServico4S = detalhadaServico4S;
    }

    public String getPaisS() {
        return paisS;
    }

    public void setPaisS(String paisS) {
        this.paisS = paisS;
    }

    public String[] getPercent() {
        return percent;
    }

    public void setPercent(String[] percent) {
        this.percent = percent;
    }

    public Double getPercentualDescontoS() {
        return percentualDescontoS;
    }

    public void setPercentualDescontoS(Double percentualDescontoS) {
        this.percentualDescontoS = percentualDescontoS;
    }

    public Integer getQuantidadeS() {
        return quantidadeS;
    }

    public void setQuantidadeS(Integer quantidadeS) {
        this.quantidadeS = quantidadeS;
    }

    public String getRegraEscrituracaoS() {
        return regraEscrituracaoS;
    }

    public void setRegraEscrituracaoS(String regraEscrituracaoS) {
        this.regraEscrituracaoS = regraEscrituracaoS;
    }

    public Integer getSeqRegistroServicoS() {
        return seqRegistroServicoS;
    }

    public void setSeqRegistroServicoS(Integer seqRegistroServicoS) {
        this.seqRegistroServicoS = seqRegistroServicoS;
    }

    public String getSinteticaS() {
        return sinteticaS;
    }

    public void setSinteticaS(String sinteticaS) {
        this.sinteticaS = sinteticaS;
    }

    public String getTipoRegistroS() {
        return tipoRegistroS;
    }

    public void setTipoRegistroS(String tipoRegistroS) {
        this.tipoRegistroS = tipoRegistroS;
    }

    public String getUfS() {
        return ufS;
    }

    public void setUfS(String ufS) {
        this.ufS = ufS;
    }

    public String getUnidMedidaS() {
        return unidMedidaS;
    }

    public void setUnidMedidaS(String unidMedidaS) {
        this.unidMedidaS = unidMedidaS;
    }

    public Double getValorSubContratoS() {
        return valorSubContratoS;
    }

    public void setValorSubContratoS(Double valorSubContratoS) {
        this.valorSubContratoS = valorSubContratoS;
    }

    public Double getValorUnitarioS() {
        return valorUnitarioS;
    }

    public void setValorUnitarioS(Double valorUnitarioS) {
        this.valorUnitarioS = valorUnitarioS;
    }
    
    
    public String toLayout() {
        StringBuffer linha = IOUtil.linhaVazia(1709);
        
        linha.replace(0,2,String.valueOf(this.getTipoRegistroS()));
        linha.replace(3,7,StringUtil.formatarCampoNumerico(String.valueOf(this.getSeqRegistroServicoS()),5));
        linha.replace(8,23,StringUtil.formatarCampoCaracter(this.getCodServicoFederalS(),16));
        linha.replace(24,39,StringUtil.formatarCampoCaracter(this.getCodServicoS(),16));
        linha.replace(40,289,StringUtil.formatarCampoCaracter(this.getSinteticaS(),250));
        linha.replace(290,539,StringUtil.formatarCampoCaracter(this.getDetalhadaServico1S(),250));
        linha.replace(540,789,StringUtil.formatarCampoCaracter(this.getDetalhadaServico2S(),250));
        linha.replace(790,1039,StringUtil.formatarCampoCaracter(this.getDetalhadaServico3S(),250));
        linha.replace(1040,1289,StringUtil.formatarCampoCaracter(this.getDetalhadaServico4S(),250));
        linha.replace(1290,1292,StringUtil.formatarCampoCaracter(this.getPaisS(),3));
        linha.replace(1293,1295,StringUtil.formatarCampoCaracter(this.getUfS(),3));
        linha.replace(1296,1299,StringUtil.formatarCampoCaracter(this.getCidadeS(),4));
        linha.replace(1300,1315,StringUtil.formatarCampoCaracter(this.getCodSvcS(),16));
        linha.replace(1316,1320,StringUtil.formatarCampoCaracter(this.getUnidMedidaS(),5));
        linha.replace(1321,1333,StringUtil.formatarCampoNumerico(String.valueOf(this.getQuantidadeS()),13));
        linha.replace(1334,1351,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorUnitarioS()),18)); //Utilizar sempre 4 casas decimais
        linha.replace(1352,1369,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getDescontoS()),18)); 
        percent = String.valueOf(this.getPercentualDescontoS()).split("\\.");
        linha.replace(1370,1372,StringUtil.formatarCampoNumerico(percent[0],3));
        linha.replace(1373,1379,StringUtil.formatarCampoNumerico(percent[1],7));
        linha.replace(1380,1383,StringUtil.formatarCampoCaracter(this.getCodAjusteDescontoS(),4));
        linha.replace(1384,1390,StringUtil.formatarCampoCaracter(this.getRegraEscrituracaoS(),7));
        linha.replace(1391,1408,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getValorSubContratoS()),18)); 
        linha.replace(1409,1468,StringUtil.formatarCampoCaracter(this.getCampoUsuario1S(),60));
        linha.replace(1469,1528,StringUtil.formatarCampoCaracter(this.getCampoUsuario2S(),60));
        linha.replace(1529,1588,StringUtil.formatarCampoCaracter(this.getCampoUsuario3S(),60));
        linha.replace(1589,1648,StringUtil.formatarCampoCaracter(this.getCampoUsuario4S(),60));
        linha.replace(1649,1708,StringUtil.formatarCampoCaracter(this.getCampoUsuario5S(),60));

        return linha.substring(0,1708);
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

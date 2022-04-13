package br.org.flem.sac.layout.rpa;

import br.org.flem.sac.arquivo.Layout;
import java.text.SimpleDateFormat;
import java.util.Date;
import br.org.flem.fwe.util.StringUtil;
import br.org.flem.sac.util.IOUtil;
import java.util.List;

/**
 *
 * @author MCCosta
 */
public class RegistroHeaderH implements Layout{
    String percent[];
    
    // Linha 01 - (H) Header
    private String tipoRegistroH = "000";   //A 03 1 - 3 Fixo "000"
    private String tipoProcessoExternoH;    //A 06 4 - 9 Tipo de processo préviamente cadastrado no GEM
    private String identProcessoExternoH;   //A 16 10 - 25 Identificação do processo no sistema de origem
    private String companhiaH;              //A 04 26 - 29
    private String tipoProcessoH;           //A 06 30 - 35 
    private String situacaoDocH;            //A 01 36 - 36 (0 - Valido / 1 - Cancelado
    private String tipoMovimentoH;          //A 01 37 - 37 (0 ? Salvar, 1 - Registrar, 4 - Registrar/Verificar, 5 - Registrar/Verificar/Aprovar)
    private String entradaSaidaH;           //A 01 38 - 38 (0 ? Entrada / 1 - Saída)
    private String tipoDadosH;              //A 01 39 - 39 (0 - Normal, 1 - Complementar, 2 - Ativo Fixo, 3 - Devolução)
    private String tipoRecebimentoH;        //A 04 40 - 43 
    private String localCompanhiaH;         //A 04 44 - 47
    private String identFornecedorClienteH; //A 16 48 - 63 Código da Entidade
    private String localFornecedorClienteH; //A 04 64 - 67 Local da Entidade
    private String descricaoFornecedorClienteH; //A 60 68 - 127
    private String cnpjFornecedorClienteH;  //A 16 128 - 143
    private String enderecoFornecedorClienteH;  //A 100 144 - 243
    private String ufFornecedorClienteH;    //A 02 244 - 245
    private String cidadeFornecedorClienteH;    //A 04 246 - 249
    private String cepFornecedorClienteH;   //A 10 250 - 259
    private String telefoneFornecedorClienteH;  //A 12 260 - 271
    private String inscrEstadualFornecedorClienteH; //A 14 272 - 285
    private String tipoProcessoOrigemH;     //A 06 286 - 291
    private String identProcessoOrigemH;   //A 16 292 - 307
    private String tipoDocOrigemH;          //A 06 308 - 313
    private String identDocOrigemH;        //A 16 314 - 329
    private String serieDocOrigemH;         //A 03 330 - 332
    private String subSerieDocOrigemH;      //A 02 333 - 334
    private Date dtaEmissaoDocOrigemH;    //N 08 335 - 342
    private String tipoDocH;                //A 06 343 - 348
    private String identDocH;              //A 16 349 - 364
    private String serieDocH;               //A 03 365 - 367
    private String subSerieDocH;            //A 02 368 - 369
    private String modeloDocH;              //A 06 370 - 375
    private String tipoDocRefH;             //A 06 376 - 381 Cód.de documento préviamente cadastrado ?NF?, ?NFE?, ?NFS?, ?NFF?, etc...(É GRAVADO NO CTAS.RECEBER, CAMPO OPITR_REF_2_TP)
    private String identDocRefH;           //A 16 382 - 397 Número do documento...(É GRAVADO NO CTAS.RECEBER, CAMPO OPITR_REF_2_ID)
    private Date dtaRecebimentoH;         //N 08 398 - 405
    private Date dtaEmissaoH;             //N 08 406 - 413
    private Date dtaContabilH;            //N 08 414 - 421
    private String regraEscrituracaoH;      //A 07 422 - 428
    private Double descontoH;               //N 18 429 - 446
    private Double percentualH;             //N 3,7 447 - 456
    private Double outrosValoresH;          //N 18 457 - 474
    private Double maoObraH;                //N 18 475 - 492
    private Double seguroH;                 //N 18 493 - 510
    private Double freteH;                  //N 18 511 - 528
    private Double ieSubstTributarioH;      //N 18 529 - 546
    private String observacao1H;            //A 250 547 - 796 preencher apenas 248 caracteres
    private String observacao2H;            //A 250 797 - 1046 preencher apenas 248 caracteres
    private String observacao3H;            //A 250 1047 - 1296 preencher apenas 248 caracteres
    private String observacao4H;            //A 250 1297 - 1546 preencher apenas 248 caracteres
    private String campoUsuario1H;          //A 30 1547 - 1576
    private String campoUsuario2H;          //A 30 1577 - 1606
    private String campoUsuario3H;          //A 30 1607 - 1636
    private String campoUsuario4H;          //A 30 1637 - 1666 (Para Fat.UNIMED Odonto, será passado doc_tp do sistema de origem I.S. Odonto)
    private String campoUsuario5H;          //A 30 1667 - 1696 (Para Fat.UNIMED Odonto, será passado doc_id do sistema de origem I.S. Odonto)
    private String campoUsuario6H;          //A 30 1697 - 1726
    private String chaveNotaFiscalEletronicaH;  //A 44 1727 - 1770
    
    
    public RegistroHeaderH() {
    
    }

    public String getCampoUsuario1H() {
        return campoUsuario1H;
    }

    public void setCampoUsuario1H(String campoUsuario1H) {
        this.campoUsuario1H = campoUsuario1H;
    }

    public String getCampoUsuario2H() {
        return campoUsuario2H;
    }

    public void setCampoUsuario2H(String campoUsuario2H) {
        this.campoUsuario2H = campoUsuario2H;
    }

    public String getCampoUsuario3H() {
        return campoUsuario3H;
    }

    public void setCampoUsuario3H(String campoUsuario3H) {
        this.campoUsuario3H = campoUsuario3H;
    }

    public String getCampoUsuario4H() {
        return campoUsuario4H;
    }

    public void setCampoUsuario4H(String campoUsuario4H) {
        this.campoUsuario4H = campoUsuario4H;
    }

    public String getCampoUsuario5H() {
        return campoUsuario5H;
    }

    public void setCampoUsuario5H(String campoUsuario5H) {
        this.campoUsuario5H = campoUsuario5H;
    }

    public String getCampoUsuario6H() {
        return campoUsuario6H;
    }

    public void setCampoUsuario6H(String campoUsuario6H) {
        this.campoUsuario6H = campoUsuario6H;
    }

    public String getCepFornecedorClienteH() {
        return cepFornecedorClienteH;
    }

    public void setCepFornecedorClienteH(String cepFornecedorClienteH) {
        this.cepFornecedorClienteH = cepFornecedorClienteH;
    }

    public String getChaveNotaFiscalEletronicaH() {
        return chaveNotaFiscalEletronicaH;
    }

    public void setChaveNotaFiscalEletronicaH(String chaveNotaFiscalEletronicaH) {
        this.chaveNotaFiscalEletronicaH = chaveNotaFiscalEletronicaH;
    }

    public String getCidadeFornecedorClienteH() {
        return cidadeFornecedorClienteH;
    }

    public void setCidadeFornecedorClienteH(String cidadeFornecedorClienteH) {
        this.cidadeFornecedorClienteH = cidadeFornecedorClienteH;
    }

    public String getCnpjFornecedorClienteH() {
        return cnpjFornecedorClienteH;
    }

    public void setCnpjFornecedorClienteH(String cnpjFornecedorClienteH) {
        this.cnpjFornecedorClienteH = cnpjFornecedorClienteH;
    }

    public String getCompanhiaH() {
        return companhiaH;
    }

    public void setCompanhiaH(String companhiaH) {
        this.companhiaH = companhiaH;
    }

    public Double getDescontoH() {
        return descontoH;
    }

    public void setDescontoH(Double descontoH) {
        this.descontoH = descontoH;
    }

    public String getDescricaoFornecedorClienteH() {
        return descricaoFornecedorClienteH;
    }

    public void setDescricaoFornecedorClienteH(String descricaoFornecedorClienteH) {
        this.descricaoFornecedorClienteH = descricaoFornecedorClienteH;
    }

    public Date getDtaContabilH() {
        return dtaContabilH;
    }

    public void setDtaContabilH(Date dtaContabilH) {
        this.dtaContabilH = dtaContabilH;
    }

    public Date getDtaEmissaoDocOrigemH() {
        return dtaEmissaoDocOrigemH;
    }

    public void setDtaEmissaoDocOrigemH(Date dtaEmissaoDocOrigemH) {
        this.dtaEmissaoDocOrigemH = dtaEmissaoDocOrigemH;
    }

    public Date getDtaEmissaoH() {
        return dtaEmissaoH;
    }

    public void setDtaEmissaoH(Date dtaEmissaoH) {
        this.dtaEmissaoH = dtaEmissaoH;
    }

    public Date getDtaRecebimentoH() {
        return dtaRecebimentoH;
    }

    public void setDtaRecebimentoH(Date dtaRecebimentoH) {
        this.dtaRecebimentoH = dtaRecebimentoH;
    }

    public String getEnderecoFornecedorClienteH() {
        return enderecoFornecedorClienteH;
    }

    public void setEnderecoFornecedorClienteH(String enderecoFornecedorClienteH) {
        this.enderecoFornecedorClienteH = enderecoFornecedorClienteH;
    }

    public String getEntradaSaidaH() {
        return entradaSaidaH;
    }

    public void setEntradaSaidaH(String entradaSaidaH) {
        this.entradaSaidaH = entradaSaidaH;
    }

    public Double getFreteH() {
        return freteH;
    }

    public void setFreteH(Double freteH) {
        this.freteH = freteH;
    }

    public String getIdentDocH() {
        return identDocH;
    }

    public void setIdentDocH(String identDocH) {
        this.identDocH = identDocH;
    }

    public String getIdentDocOrigemH() {
        return identDocOrigemH;
    }

    public void setIdentDocOrigemH(String identDocOrigemH) {
        this.identDocOrigemH = identDocOrigemH;
    }

    public String getIdentDocRefH() {
        return identDocRefH;
    }

    public void setIdentDocRefH(String identDocRefH) {
        this.identDocRefH = identDocRefH;
    }

    public String getIdentFornecedorClienteH() {
        return identFornecedorClienteH;
    }

    public void setIdentFornecedorClienteH(String identFornecedorClienteH) {
        this.identFornecedorClienteH = identFornecedorClienteH;
    }

    public String getIdentProcessoExternoH() {
        return identProcessoExternoH;
    }

    public void setIdentProcessoExternoH(String identProcessoExternoH) {
        this.identProcessoExternoH = identProcessoExternoH;
    }

    public String getIdentProcessoOrigemH() {
        return identProcessoOrigemH;
    }

    public void setIdentProcessoOrigemH(String identProcessoOrigemH) {
        this.identProcessoOrigemH = identProcessoOrigemH;
    }

    public Double getIeSubstTributarioH() {
        return ieSubstTributarioH;
    }

    public void setIeSubstTributarioH(Double ieSubstTributarioH) {
        this.ieSubstTributarioH = ieSubstTributarioH;
    }

    public String getInscrEstadualFornecedorClienteH() {
        return inscrEstadualFornecedorClienteH;
    }

    public void setInscrEstadualFornecedorClienteH(String inscrEstadualFornecedorClienteH) {
        this.inscrEstadualFornecedorClienteH = inscrEstadualFornecedorClienteH;
    }

    public String getLocalCompanhiaH() {
        return localCompanhiaH;
    }

    public void setLocalCompanhiaH(String localCompanhiaH) {
        this.localCompanhiaH = localCompanhiaH;
    }

    public String getLocalFornecedorClienteH() {
        return localFornecedorClienteH;
    }

    public void setLocalFornecedorClienteH(String localFornecedorClienteH) {
        this.localFornecedorClienteH = localFornecedorClienteH;
    }

    public Double getMaoObraH() {
        return maoObraH;
    }

    public void setMaoObraH(Double maoObraH) {
        this.maoObraH = maoObraH;
    }

    public String getModeloDocH() {
        return modeloDocH;
    }

    public void setModeloDocH(String modeloDocH) {
        this.modeloDocH = modeloDocH;
    }

    public String getObservacao1H() {
        return observacao1H;
    }

    public void setObservacao1H(String observacao1H) {
        this.observacao1H = observacao1H;
    }

    public String getObservacao2H() {
        return observacao2H;
    }

    public void setObservacao2H(String observacao2H) {
        this.observacao2H = observacao2H;
    }

    public String getObservacao3H() {
        return observacao3H;
    }

    public void setObservacao3H(String observacao3H) {
        this.observacao3H = observacao3H;
    }

    public String getObservacao4H() {
        return observacao4H;
    }

    public void setObservacao4H(String observacao4H) {
        this.observacao4H = observacao4H;
    }

    public Double getOutrosValoresH() {
        return outrosValoresH;
    }

    public void setOutrosValoresH(Double outrosValoresH) {
        this.outrosValoresH = outrosValoresH;
    }

    public String[] getPercent() {
        return percent;
    }

    public void setPercent(String[] percent) {
        this.percent = percent;
    }

    public Double getPercentualH() {
        return percentualH;
    }

    public void setPercentualH(Double percentualH) {
        this.percentualH = percentualH;
    }

    public String getRegraEscrituracaoH() {
        return regraEscrituracaoH;
    }

    public void setRegraEscrituracaoH(String regraEscrituracaoH) {
        this.regraEscrituracaoH = regraEscrituracaoH;
    }

    public Double getSeguroH() {
        return seguroH;
    }

    public void setSeguroH(Double seguroH) {
        this.seguroH = seguroH;
    }

    public String getSerieDocH() {
        return serieDocH;
    }

    public void setSerieDocH(String serieDocH) {
        this.serieDocH = serieDocH;
    }

    public String getSerieDocOrigemH() {
        return serieDocOrigemH;
    }

    public void setSerieDocOrigemH(String serieDocOrigemH) {
        this.serieDocOrigemH = serieDocOrigemH;
    }

    public String getSituacaoDocH() {
        return situacaoDocH;
    }

    public void setSituacaoDocH(String situacaoDocH) {
        this.situacaoDocH = situacaoDocH;
    }

    public String getSubSerieDocH() {
        return subSerieDocH;
    }

    public void setSubSerieDocH(String subSerieDocH) {
        this.subSerieDocH = subSerieDocH;
    }

    public String getSubSerieDocOrigemH() {
        return subSerieDocOrigemH;
    }

    public void setSubSerieDocOrigemH(String subSerieDocOrigemH) {
        this.subSerieDocOrigemH = subSerieDocOrigemH;
    }

    public String getTelefoneFornecedorClienteH() {
        return telefoneFornecedorClienteH;
    }

    public void setTelefoneFornecedorClienteH(String telefoneFornecedorClienteH) {
        this.telefoneFornecedorClienteH = telefoneFornecedorClienteH;
    }

    public String getTipoDadosH() {
        return tipoDadosH;
    }

    public void setTipoDadosH(String tipoDadosH) {
        this.tipoDadosH = tipoDadosH;
    }

    public String getTipoDocH() {
        return tipoDocH;
    }

    public void setTipoDocH(String tipoDocH) {
        this.tipoDocH = tipoDocH;
    }

    public String getTipoDocOrigemH() {
        return tipoDocOrigemH;
    }

    public void setTipoDocOrigemH(String tipoDocOrigemH) {
        this.tipoDocOrigemH = tipoDocOrigemH;
    }

    public String getTipoDocRefH() {
        return tipoDocRefH;
    }

    public void setTipoDocRefH(String tipoDocRefH) {
        this.tipoDocRefH = tipoDocRefH;
    }

    public String getTipoMovimentoH() {
        return tipoMovimentoH;
    }

    public void setTipoMovimentoH(String tipoMovimentoH) {
        this.tipoMovimentoH = tipoMovimentoH;
    }

    public String getTipoProcessoExternoH() {
        return tipoProcessoExternoH;
    }

    public void setTipoProcessoExternoH(String tipoProcessoExternoH) {
        this.tipoProcessoExternoH = tipoProcessoExternoH;
    }

    public String getTipoProcessoH() {
        return tipoProcessoH;
    }

    public void setTipoProcessoH(String tipoProcessoH) {
        this.tipoProcessoH = tipoProcessoH;
    }

    public String getTipoProcessoOrigemH() {
        return tipoProcessoOrigemH;
    }

    public void setTipoProcessoOrigemH(String tipoProcessoOrigemH) {
        this.tipoProcessoOrigemH = tipoProcessoOrigemH;
    }

    public String getTipoRecebimentoH() {
        return tipoRecebimentoH;
    }

    public void setTipoRecebimentoH(String tipoRecebimentoH) {
        this.tipoRecebimentoH = tipoRecebimentoH;
    }

    public String getTipoRegistroH() {
        return tipoRegistroH;
    }

    public void setTipoRegistroH(String tipoRegistroH) {
        this.tipoRegistroH = tipoRegistroH;
    }

    public String getUfFornecedorClienteH() {
        return ufFornecedorClienteH;
    }

    public void setUfFornecedorClienteH(String ufFornecedorClienteH) {
        this.ufFornecedorClienteH = ufFornecedorClienteH;
    }

    
    public String toLayout() {
        SimpleDateFormat formatoAnoMesDia = new SimpleDateFormat("yyyyMMdd");
        StringBuffer linha = IOUtil.linhaVazia(1770);
        
        linha.replace(0,2,String.valueOf(this.getTipoRegistroH()));
        linha.replace(3,8,StringUtil.formatarCampoCaracter(this.getTipoProcessoExternoH(),6));
        linha.replace(9,24,StringUtil.formatarCampoCaracter(this.getIdentProcessoExternoH(),16));
        linha.replace(25,28,StringUtil.formatarCampoCaracter(this.getCompanhiaH(),4));
        linha.replace(29,34,StringUtil.formatarCampoCaracter(this.getTipoProcessoH(),6));
        linha.replace(35,35,this.getSituacaoDocH());
        linha.replace(36,36,this.getTipoMovimentoH());
        linha.replace(37,37,this.getEntradaSaidaH());
        linha.replace(38,38,this.getTipoDadosH());
        linha.replace(39,42,StringUtil.formatarCampoCaracter(this.getTipoRecebimentoH(),4));
        linha.replace(43,46,StringUtil.formatarCampoCaracter(this.getLocalCompanhiaH(),4));
        linha.replace(47,62,StringUtil.formatarCampoCaracter(this.getIdentFornecedorClienteH(),16));
        linha.replace(63,66,StringUtil.formatarCampoCaracter(this.getLocalFornecedorClienteH(),04));
        linha.replace(67,126,StringUtil.formatarCampoCaracter(this.getDescricaoFornecedorClienteH(),60));
        linha.replace(127,142,StringUtil.formatarCampoCaracter(this.getCnpjFornecedorClienteH(),16));
        linha.replace(143,242,StringUtil.formatarCampoCaracter(this.getEnderecoFornecedorClienteH(),100));
        linha.replace(243,244,StringUtil.formatarCampoCaracter(this.getUfFornecedorClienteH(),2));
        linha.replace(245,248,StringUtil.formatarCampoCaracter(this.getCidadeFornecedorClienteH(),4));
        linha.replace(249,258,StringUtil.formatarCampoCaracter(this.getCepFornecedorClienteH(),10));
        linha.replace(259,270,StringUtil.formatarCampoCaracter(this.getTelefoneFornecedorClienteH(),12));
        linha.replace(271,284,StringUtil.formatarCampoCaracter(this.getInscrEstadualFornecedorClienteH(),14));
        linha.replace(285,290,StringUtil.formatarCampoCaracter(this.getTipoProcessoOrigemH(),6));
        linha.replace(291,306,StringUtil.formatarCampoCaracter(this.getIdentProcessoOrigemH(),16));
        linha.replace(307,312,StringUtil.formatarCampoCaracter(this.getTipoDocOrigemH(),6));
        linha.replace(313,328,StringUtil.formatarCampoCaracter(this.getIdentDocOrigemH(),16));
        linha.replace(329,331,StringUtil.formatarCampoCaracter(this.getSerieDocOrigemH(),3));
        linha.replace(332,333,StringUtil.formatarCampoCaracter(this.getSubSerieDocOrigemH(),2));
        linha.replace(334,341,String.valueOf(formatoAnoMesDia.format(this.getDtaEmissaoDocOrigemH())));
        linha.replace(342,347,StringUtil.formatarCampoCaracter(this.getTipoDocH(),6));
        linha.replace(342,347,StringUtil.formatarCampoCaracter(this.getTipoDocH(),6));
        linha.replace(348,363,StringUtil.formatarCampoCaracter(this.getIdentDocH(),16));
        linha.replace(364,366,StringUtil.formatarCampoCaracter(this.getSerieDocH(),3));
        linha.replace(367,368,StringUtil.formatarCampoCaracter(this.getSubSerieDocH(),2));
        linha.replace(369,374,StringUtil.formatarCampoCaracter(this.getModeloDocH(),6));
        linha.replace(375,380,StringUtil.formatarCampoCaracter(this.getTipoDocRefH(),6));
        linha.replace(381,396,StringUtil.formatarCampoCaracter(this.getIdentDocRefH(),16));
        linha.replace(397,404,String.valueOf(formatoAnoMesDia.format(this.getDtaRecebimentoH())));
        linha.replace(405,412,String.valueOf(formatoAnoMesDia.format(this.getDtaEmissaoH())));
        linha.replace(413,420,String.valueOf(formatoAnoMesDia.format(this.getDtaContabilH())));
        linha.replace(421,427,StringUtil.formatarCampoCaracter(this.getRegraEscrituracaoH(),7));
        linha.replace(428,445,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getDescontoH()),18));
        percent = String.valueOf(this.getPercentualH()).split("\\.");
        linha.replace(446,448,StringUtil.formatarCampoNumerico(percent[0],3));
        linha.replace(449,455,StringUtil.formatarCampoNumerico(percent[1],7));
        linha.replace(456,473,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getOutrosValoresH()),18));
        linha.replace(474,491,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getMaoObraH()),18));
        linha.replace(492,509,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getSeguroH()),18));
        linha.replace(510,527,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getFreteH()),18));
        linha.replace(528,545,StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(this.getIeSubstTributarioH()),18));
        linha.replace(546,795,StringUtil.formatarCampoCaracter(this.getObservacao1H(),250));
        linha.replace(796,1045,StringUtil.formatarCampoCaracter(this.getObservacao2H(),250));
        linha.replace(1046,1295,StringUtil.formatarCampoCaracter(this.getObservacao3H(),250));
        linha.replace(1296,1545,StringUtil.formatarCampoCaracter(this.getObservacao4H(),250));
        linha.replace(1546,1575,StringUtil.formatarCampoCaracter(this.getCampoUsuario1H(),30));
        linha.replace(1576,1605,StringUtil.formatarCampoCaracter(this.getCampoUsuario2H(),30));
        linha.replace(1606,1635,StringUtil.formatarCampoCaracter(this.getCampoUsuario3H(),30));
        linha.replace(1636,1665,StringUtil.formatarCampoCaracter(this.getCampoUsuario4H(),30));
        linha.replace(1666,1695,StringUtil.formatarCampoCaracter(this.getCampoUsuario5H(),30));
        linha.replace(1696,1725,StringUtil.formatarCampoCaracter(this.getCampoUsuario6H(),30));
        linha.replace(1726,1769,StringUtil.formatarCampoCaracter(this.getChaveNotaFiscalEletronicaH(),44));
        
        return linha.substring(0,1769);
        
    }

    public List toLayoutCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
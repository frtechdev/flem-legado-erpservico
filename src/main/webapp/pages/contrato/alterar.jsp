<%@page contentType="text/html" errorPage="/erro.jsp"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://flem.org.br/acesso-tag" prefix="acesso"%>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
        <link rel="stylesheet" type="text/css" href="css/800px.css" />
        <link href="css/displaytag.css" rel="stylesheet" type="text/css" />
        <script src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.maskedinput.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.maskMoney.js"></script>
        
        
        
        <html:javascript formName="contratoForm" method="validar" />
        
        
        <title><fmt:message key="aplicacao.nome" /> versão: <fmt:message key="aplicacao.versao" /></title>
        
        <script>
            jQuery.noConflict();
            jQuery(document).ready(function(){
                jQuery("input[class*=real]").maskMoney({symbol: "", decimal: ",", thousands: "."});
            });
        </script>
        
        <script>
            function liberarData(){
                if(document.getElementById("aviso").value == "1"){
                    document.getElementById("linhaAviso").style.color = "#FF0000";
                    document.getElementById("dataLimiteAviso").style.color = "#FF0000";
                    document.getElementById("dataLimiteAviso").readOnly=false;
                }else{
                    document.getElementById("linhaAviso").style.color = "#DDDDDD";
                    document.getElementById("dataLimiteAviso").style.color = "#DDDDDD";
                    document.getElementById("dataLimiteAviso").readOnly=true;
                    document.getElementById("dataLimiteAviso").value = "";
                }
            }
        </script>
        
    </head>
    <body>
        <div id="wrap">
            <jsp:include flush="false" page="/inc/header.jsp" />
            <jsp:include flush="false" page="/inc/sidebar.jsp" />
            <div id="content">
                <h2>Alterar Contrato</h2>
                <html:form action="/Contrato.do?metodo=alterar" onsubmit="return validar(this);" >
                    <html:hidden property="id"/>
                    <table>
                        <tr>
                            <td>Início da Vigência: </td>
                            <td><html:text property="dataInicioVigencia" size="10" maxlength="100" styleClass="requerido" /> Ex.: 01/01/2008</td>
                        </tr>
                        <tr>
                            <td>Fim da Vigência: </td>
                            <td><html:text property="dataFimVigencia" size="10" maxlength="10" styleClass="requerido" /> Ex.: 01/01/2008</td>
                        </tr>
                        <tr>
                            <td>Avisar por email: </td>
                            <td>
                                <html:select property="aviso" styleId="aviso" onchange="liberarData()">
                                    <html:option value="0">Não</html:option>
                                    <html:option value="1">Sim</html:option>
                                </html:select>
                            </td>
                        </tr>
                        <tr id="linhaAviso" style="color: #DDDDDD;">
                            <td>Data de Aviso do Final da Vigência: </td>
                            <td>
                                <html:text property="dataLimiteAviso" styleId="dataLimiteAviso" size="10" maxlength="10" /> 
                                Ex.: 01/01/2008 (Data para o sistema começar a mandar email avisando do final da Contrato)
                                <script>liberarData()</script>
                            </td>
                        </tr>
                        <tr>
                            <td>Centro de Custos: </td>
                            <td>
                                <html:text property="centroCusto" styleId="centroCusto" size="50" maxlength="20" /> 
                            </td>
                        </tr>
                        <tr>
                            <td>Número da SD: </td>
                            <td>
                                <html:text property="numeroSD" styleId="numeroSD" size="50" maxlength="20" /> 
                            </td>
                        </tr>
                        <tr>
                            <td>Nº do Contrato: </td>
                            <td>
                                <html:text property="numeroContrato" styleId="numeroContrato" size="50" maxlength="20" /> 
                            </td>
                        </tr>
                        <tr>
                            <td>Fornecedor: </td>
                            <td align="left">
                                <html:select styleId="idFornecedor" styleClass="requerido" property="idFornecedor">
                                    <html:option value="">-- Selecione --</html:option>
                                    <c:forEach items="${fornecedores}" var="fornecedor">
                                        <html:option value="${fornecedor.codigo}">${fornecedor.nomeAbreviado} - ${fornecedor.codigo}</html:option>
                                    </c:forEach>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>Nº do Processo: </td>
                            <td><html:text property="numero" size="50" maxlength="100" styleClass="requerido" /></td>
                        </tr>
                        <tr>
                            <td>Valor: </td>
                            <td><html:text property="valor" size="15" maxlength="100" styleClass="real requerido" /></td>
                        </tr>
                        <tr>
                            <td>Objeto: </td>
                            <td><html:text property="objeto" size="100" maxlength="300" /> </td>
                        </tr>
                        <tr>
                            <td>Descrição:</td>
                            <td><html:textarea property="observacao"  cols="72" rows="4"/></td>
                        </tr>
                        <tr>
                            <td>Situaçao: </td>
                            <td>
                                <html:select property="situacao" styleClass="requerido">
                                    <html:option value="VIGENTE">Vigente</html:option>
                                    <html:option value="CANCELADO">Cancelado</html:option>
                                    <html:option value="FINALIZADO">Finalizado</html:option>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                                <html:submit value="salvar" styleClass="botao" /> &nbsp;
                                <html:button property="" value="voltar" onclick="javascript: history.go(-1);" styleClass="botao" />
                            </td>
                        </tr>
                    </table>
                </html:form>
            </div> 
            <jsp:include flush="false" page="/inc/footer.jsp" />
        </div>
    </body>
</html>

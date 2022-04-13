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
        <link href="<%=request.getContextPath()%>/css/calendar-blue.css" rel="stylesheet" type="text/css" />
        <script language="JavaScript" src="<%=request.getContextPath()%>/js/calendar.js"  type="text/javascript" ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/js/calendar-pt.js"  type="text/javascript" ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/js/calendar-setup.js"  type="text/javascript" ></script>
        <script src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.maskedinput.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.maskMoney.js"></script>
        
        <html:javascript formName="aditivoForm" method="validar" />
        <title><fmt:message key="aplicacao.nome" /> versão: <fmt:message key="aplicacao.versao" /></title>
        
        <script>
            jQuery.noConflict();
            jQuery(document).ready(function(){
                jQuery("input[class*=real]").maskMoney({symbol: "", decimal: ",", thousands: "."});
            });
        </script>
    </head>
    <body>
        <div id="wrap">
            <jsp:include flush="false" page="/inc/header.jsp" />
            <jsp:include flush="false" page="/inc/sidebar.jsp" />
            <div id="content">
                <h2>Novo Aditivo</h2>
                <html:form action="/Aditivo.do?metodo=alterar" onsubmit="return validar(this);" >
                    <html:hidden property="id"/>
                    <html:hidden property="idContrato"/>
                    <table>
                        <tr>
                            <td>Início da Vigência: </td>
                            <td><html:text property="dataInicioVigencia" styleId="dataInicioVigencia" size="10" maxlength="10" styleClass="requerido" /><img  alt="Selecione uma data" src="img/seletorData.png" onclick="return showCalendar('dataInicioVigencia', '%d/%m/%Y');"/></td>
                        </tr>
                        <tr>
                            <td>Fim da Vigência: </td>
                            <td><html:text property="dataFimVigencia" styleId="dataFimVigencia" size="10" maxlength="10" styleClass="requerido" /><img  alt="Selecione uma data" src="img/seletorData.png" onclick="return showCalendar('dataFimVigencia', '%d/%m/%Y');"/></td>
                        </tr>
                        <tr>
                            <td>Valor: </td>
                            <td>
                                <html:text property="valor" size="15" maxlength="100" styleClass="real requerido"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                                <html:submit value="adicionar" styleClass="botao" /> &nbsp;
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

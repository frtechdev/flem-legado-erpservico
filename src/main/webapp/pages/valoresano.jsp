<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://flem.org.br/autentica-tag" prefix="acesso"%>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="css/800px.css" />
        <link href="<%=request.getContextPath()%>/css/displaytag.css" rel="stylesheet" type="text/css" />
        <title><fmt:message key="aplicacao.nome" /></title>
        <script src='<%=request.getContextPath()%>/dwr/interface/Funcoes.js' ></script>
        <script src='<%=request.getContextPath()%>/dwr/engine.js' ></script>
        <script src='<%=request.getContextPath()%>/dwr/util.js' ></script>
        <script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/js/dwr.js"  type="text/javascript" > </script>
        <script language="JavaScript"  type="text/javascript" >
            function init() {
                useLoadingImage("img/carregar.gif");
            }
            
            function criarArquivo() {
                var mes = DWRUtil.getValue("mes");
                var ano = DWRUtil.getValue("ano");
                var resp = Funcoes.geraArquivoValores(mes,ano);
                $("help").innerHTML="Acesse o Consist HR. <br> Use o comando 862. <br> Selecione a opção Bath. <br> Execute! <br> Use o comando 74 para verificar a execução do serviço.";
            }
        </script>
    </head>
    <body onload="init();" >
        <acesso:autentica sistema="erpserv" />
        <div id="wrap">
            <jsp:include flush="false" page="../inc/header.jsp" />
            <jsp:include flush="false" page="../inc/sidebar.jsp" />
            <div id="content">
                <h2>Exportar Valores de Autônomos </h2>
                <html:form action="/ValorAutonomoAno.do?metodo=lista" >
                    <table>
                        <tr><td>Ano: </td><td>
                                <html:select styleId="ano" property="ano" >
                                    <html:option value="2005" />                                
                                    <html:option value="2006" />
                                    <html:option value="2007" />
                                    <html:option value="2008" />
                                    <html:option value="2009" />
                                    <html:option value="2010" />
                                    <html:option value="2011" />
                                    <html:option value="2012" />
                                    <html:option value="2013" />
                                    <html:option value="2014" />
                                    <html:option value="2015" />
                                </html:select>
                            </td>
                            <td><html:submit value="Visualizar" styleClass="botao"/></td>
                        </tr>
                    </table>
                </html:form>
                <br>
                <div id="help">  </div>
            </div>    
            <jsp:include flush="false" page="../inc/footer.jsp" />     
        </div>
    </body>
</html>

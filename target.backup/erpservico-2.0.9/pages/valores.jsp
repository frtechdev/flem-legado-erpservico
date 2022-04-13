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
        <script language="JavaScript" src="<%=request.getContextPath()%>/js/dwr.js"  type="text/javascript" > </script>
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

           function criarFolha() {
                var mes = DWRUtil.getValue("mes");
                var ano = DWRUtil.getValue("ano");
                var caminho = DWRUtil
                var resp = Funcoes.geraFolhaAutonomos(mes,ano,"<%=getServletConfig().getServletContext().getRealPath("/")%>");
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
                <html:form action="/ValorAutonomo.do?metodo=listar" >
                    <table>
                        <tr><td>Mês: </td><td>
                                <html:select styleId="mes" property="mes" >
                                    <html:option value="01" >Janeiro</html:option>
                                    <html:option value="02" >Fevereiro</html:option>
                                    <html:option value="03" >Março</html:option>
                                    <html:option value="04" >Abril</html:option>
                                    <html:option value="05" >Maio</html:option>
                                    <html:option value="06" >Junho</html:option>
                                    <html:option value="07" >Julho</html:option>
                                    <html:option value="08" >Agosto</html:option>
                                    <html:option value="09" >Setembro</html:option>
                                    <html:option value="10" >Outubro</html:option>
                                    <html:option value="11" >Novembro</html:option>
                                    <html:option value="12" >Dezembro</html:option>
                                </html:select>
                        </td>
                        <td></td>
                        </tr>
                        <tr><td>Ano: </td><td>
                                <html:select styleId="ano" property="ano" >
                                     <c:forEach items="${anos}" var="ano" >
                                            <html:option value="${ano}" />
                                     </c:forEach>
                                </html:select>
                        </td>
                        <td></td>
                        </tr>
                        <tr><td><html:submit value="Visualizar" styleClass="botao"/></td>
                            <td><input type="button" onclick="criarArquivo();" value="Gerar Arquivo" class="botao"/></td>
                            <td><input type="button" onclick="criarFolha();" value="Gerar Folha" class="botao"/></td>
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


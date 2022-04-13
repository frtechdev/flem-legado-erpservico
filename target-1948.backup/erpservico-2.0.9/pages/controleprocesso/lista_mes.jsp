<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://flem.org.br/autentica-tag" prefix="acesso"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<html> 
    <head>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="css/800px.css" />
        <link href="<%=request.getContextPath()%>/css/displaytag.css" rel="stylesheet" type="text/css" />
        <title><fmt:message key="aplicacao.nome" /></title>
    </head>
    <body>
        <acesso:autentica sistema="erpserv" />
        <div id="wrap">
            <jsp:include flush="false" page="/inc/header.jsp" />
            <jsp:include flush="false" page="/inc/sidebar.jsp" />
            <div id="content">
                <h2>Consulta</h2>
                <html:form action="/ControleProcesso.do?metodo=listar" >
                    <table>
                        <tr>
                            <td>Mes:</td>
                            <td><html:select styleId="mes" property="mes" >
                                    <html:option value="1" >Janeiro</html:option> 
                                    <html:option value="2" >Fevereiro</html:option> 
                                    <html:option value="3" >Março</html:option> 
                                    <html:option value="4" >Abril</html:option> 
                                    <html:option value="5" >Maio</html:option> 
                                    <html:option value="6" >Junho</html:option> 
                                    <html:option value="7" >Julho</html:option> 
                                    <html:option value="8" >Agosto</html:option> 
                                    <html:option value="9" >Setembro</html:option> 
                                    <html:option value="10" >Outubro</html:option> 
                                    <html:option value="11" >Novembro</html:option> 
                                    <html:option value="12" >Dezembro</html:option> 
                            </html:select></td>
                        </tr>
                        <tr>
                            <td>Ano:</td>
                            <td>
                               <html:select styleId="ano" property="ano" >
                                     <c:forEach items="${anos}" var="ano" >
                                            <html:option value="${ano}" />
                                     </c:forEach>
                                </html:select>
                            </td>
                        </tr>
                    </table>
                    <html:submit value="consultar" />
                </html:form>
                
                <display:table id="item" name="lista" sort="list" export="true" requestURI="/ControleProcesso.do" class="tabelaDisplay" >
                    <display:column property="fonteRecurso" title="Codigo" sortable="true" />
                    <display:column property="nomeFonteRecurso" title="Nome" sortable="true" />
                    <display:column property="quantidade" title="Quantidade " sortable="true" />
                </display:table>
            </div>
            <jsp:include flush="false" page="/inc/footer.jsp" />
        </div>
    </body>
</html>

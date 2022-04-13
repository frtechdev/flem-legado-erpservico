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
            <jsp:include flush="false" page="../inc/header.jsp" />
            <jsp:include flush="false" page="../inc/sidebar.jsp" />
            <div id="content">
                <h2>Lista Autônomos - GEM</h2>
                <display:table id="entidade" name="lista" defaultsort="1" sort="list" export="false" excludedParams="metodo" requestURI="/Autonomo.do" pagesize="50" class="tabelaDisplay" >
                    <display:column property="codigo" title="Codigo" sortable="true" />
                    <display:column property="nomeAbreviado"  title="Nome Abreviado" sortable="true" />
                    <display:column property="cpf" title="CPF" sortable="true" />
                    <display:column property="codigoContribuicao" title="INSS" sortable="true" />
                </display:table>
            </div>
            <jsp:include flush="false" page="../inc/footer.jsp" />
        </div>
    </body>
</html>
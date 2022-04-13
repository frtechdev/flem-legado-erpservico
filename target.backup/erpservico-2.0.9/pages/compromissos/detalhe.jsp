<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://flem.org.br/autentica-tag" prefix="acesso"%>
<%@taglib uri="http://flem.org.br/mensagem-tag" prefix="mensagem"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>


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
                <h3>Rateios</h3>

                <display:table id="detalhe" name="lista" defaultsort="5" sort="list" export="false" excludedParams="metodo" requestURI="./FonteRecurso.do" pagesize="10" class="tabelaDisplay">
                    <display:column property="centroCusto" title="Código Centro de Custo" sortable="true" group="1"   />
                    <display:column property="tipo" title="Tipo" sortable="true"  />
                    <display:column property="valor" title="Valor" format="{0, number, #.##}"  style="text-align: right" sortable="true"  />
                </display:table>
                
                <br>
            </div>

            <jsp:include flush="false" page="/inc/footer.jsp" />
            <mensagem:alert/>
            <mensagem:alert escopo="session" />
        </div>
    </body>
</html>

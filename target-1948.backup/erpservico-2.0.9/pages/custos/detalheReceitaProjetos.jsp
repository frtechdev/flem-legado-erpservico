<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://flem.org.br/autentica-tag" prefix="acesso"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://displaytag.sf.net/el" prefix="display-el"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://flem.org.br/mensagem-tag" prefix="msg"%>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="css/800px.css" />
        <link href="css/displaytag.css" rel="stylesheet" type="text/css" />
        <title><fmt:message key="aplicacao.nome" /></title>
    </head>
    <body id="body" >
        <acesso:autentica sistema="erpserv" />
        <div id="wrap" >
            <jsp:include flush="false" page="/inc/header.jsp" />
            <jsp:include flush="false" page="/inc/sidebar.jsp" />
            <div id="content">
                <h2>Receita de Projetos</h2>
                

                <display:table id="receitaProjeto" excludedParams="metodo" requestURI="/ReceitaProjetos.do?metodo=detalhe&centrocusto=${centrocusto}"  name="lista" defaultsort="1" sort="list"  export="true"  class="report" decorator="org.displaytag.decorator.TotalTableDecorator" >
                    <display:column property="data"  title="Mes" format="{0,date,MMMM/yyyy}" group="1" />
                    <display:column property="data"  title="Data" format="{0,date,dd/MM/yyyy}"  />
                    <display:column property="descricao"  title="Descrição" />
                    <display:column property="valor" title="Valor" format="{0, number, #,##0.00}"  style="text-align: right" total="true" />
                </display:table>
            </div>
            <jsp:include flush="false" page="/inc/footer.jsp" />
        </div>
        <msg:alert escopo="session"/>
    </body>
</html>
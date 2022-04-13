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
                <h2>Exportar Valores de Autônomos </h2>
                <display:table id="item" name="lista" defaultsort="1" sort="list" export="false" excludedParams="metodo" requestURI="/ValorAutonomo.do" pagesize="50" class="tabelaDisplay" >
                    <display:column property="entidade.codigo" title="Codigo" sortable="true" />
                    <display:column property="entidade.nomeExtenso" title="Nome " sortable="true" />
                    <display:column property="entidade.cpf" title="CPF" sortable="true" />
                    <display:column property="entidade.base" title="BASE" sortable="true" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                    <display:column property="entidade.insd" title="INSD" sortable="true" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                    <display:column property="entidade.insn" title="INSN" sortable="true" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                    <display:column property="entidade.ir" title="IR" sortable="true" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                    <display:column property="entidade.iss" title="ISS" sortable="true" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                </display:table>
            </div>
            <jsp:include flush="false" page="../inc/footer.jsp" />
        </div>
    </body>
</html>

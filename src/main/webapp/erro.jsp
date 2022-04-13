<%@page contentType="text/html" isErrorPage="true"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://flem.org.br/autentica-tag" prefix="acesso"%>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/800px.css" />
        <link href="css/displaytag.css" rel="stylesheet" type="text/css" />
        <title><fmt:message key="aplicacao.nome" /></title>
    </head>
    <body>
        <div id="wrap">
            <jsp:include flush="false" page="inc/header.jsp" />
            <jsp:include flush="false" page="inc/sidebar.jsp" />
            <div id="content">
                <h2><fmt:message key="aplicacao.nome" /></h2>
                <p class="box"><strong>Erro:</strong>Erro entre em contato com o suporte.</p>
            </div>
            <jsp:include flush="false" page="inc/footer.jsp" />
            
        </div>
    </body>
</html>

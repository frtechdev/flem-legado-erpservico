<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

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
            <jsp:include flush="false" page="inc/header.jsp" />
            <jsp:include flush="false" page="inc/sidebar.jsp" />
            <div id="content">
                <h2><fmt:message key="aplicacao.nome" /></h2>
                <p>
                    <img src="img/helpdesk.jpg" alt="Utilize a aplicação HelpDesk para relatar problemas encontrados." class="left photo" />
                    Esta aplicação tem por objetivo apoiar a comunicação do ERP com outros sistemas de forma mais ágil e flexível.
                </p>
                <p class="box">
                    <strong>Nota:</strong> O NTO pensando em você!
                </p>
            </div>
            
           
            <jsp:include flush="false" page="inc/footer.jsp" />

        </div>
    </body>
</html>

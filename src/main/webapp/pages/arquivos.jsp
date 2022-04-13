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
        <script language="JavaScript" src="<%=request.getContextPath()%>/js/dwr.js"  type="text/javascript" > </script>
    </head>
    <body>
        <acesso:autentica sistema="erpserv" />
        <div id="wrap">
            <jsp:include flush="false" page="../inc/header.jsp" />
            <jsp:include flush="false" page="../inc/sidebar.jsp" />
            <div id="content">
                <h2>Arquivos Gerados </h2>
                <!--html:form action="/ValorAutonomo.do?metodo=listar" >

                /html:form-->
                <br>

                <table border="0">
                    <c:forEach items="${listaArquivos}" var="arquivo" >
                    <tr>
                        <td>
                            <a href="./temp/${arquivo.name}" ><c:out value="${arquivo.name}"/> </a>
                        </td>
                    </tr>
                    </c:forEach>
                </table>

            </div>
        </div>
        <jsp:include flush="false" page="../inc/footer.jsp" />
    </body>
</html>


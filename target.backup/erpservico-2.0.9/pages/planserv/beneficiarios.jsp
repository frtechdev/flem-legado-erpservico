<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://flem.org.br/autentica-tag" prefix="acesso"%>
<html> 
    <head>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="css/800px.css" />
        <link href="<%=request.getContextPath()%>/css/displaytag.css" rel="stylesheet" type="text/css" />
        <title><fmt:message key="aplicacao.nome" /></title>
        <script src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
    </head>
    <body>
        <acesso:autentica sistema="erpserv" />
        <div id="wrap">
            <jsp:include flush="false" page="/inc/header.jsp" />
            <jsp:include flush="false" page="/inc/sidebar.jsp" />
            <div id="content">
                <h2>Beneficiarios</h2>
                <h4>${funcionario.nome}</h4>
                <html:form action="/Planserv.do?metodo=beneficiarios&matricula=${matricula}" >
                    <display:table name="beneficiarios" export="true" style="border:1px solid;" requestURI="/Planserv.do?metodo=beneficiarios&matricula=${matricula}">
                        <display:column property="nome"/>
                        <display:column property="parentesco.descricao"/>
                        <display:column property="nascimento" format="{0,date,dd/MM/yyyy}"/>
                    </display:table>
                </html:form>
            </div>
            <jsp:include flush="false" page="/inc/footer.jsp" />
        </div>
    </body>
</html>

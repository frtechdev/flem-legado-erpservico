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
                <h3>Departamentos</h3>

                <display:table id="departamento" name="listaDepartamento" defaultsort="5" sort="list" export="false" excludedParams="metodo" requestURI="./Departamento.do" pagesize="10" class="tabelaDisplay">
                    <display:column style="width:22px;">
                        <input type="checkbox" name="ids_exclusao" value="${departamento.id}"/>
                    </display:column>
                    <display:column property="cod_ccusto" title="Código Centro de Custo" sortable="true" group="1"   />
                    <display:column property="cod_lotacao_hr" title="Departamento" sortable="true"  />
                    <display:column title="Departamento Dominio" sortable="true">${mapDepartamentoDominio[departamento.cod_lotacao_dominio.toString()].nome}</display:column>
                    <display:column property="nom_dept" title="Nome Completo" sortable="true"  />
                    <display:column title="" href="./Departamento.do?metodo=selecionar&cc" paramId="id" paramProperty="id" ><img align="middle" src="img/edit.png" width="22" height="22" border="0" alt="editar"/></display:column>
                </display:table>
                
                <br>
            </div>

            <jsp:include flush="false" page="/inc/footer.jsp" />
            <mensagem:alert/>
            <mensagem:alert escopo="session" />
        </div>
    </body>
</html>

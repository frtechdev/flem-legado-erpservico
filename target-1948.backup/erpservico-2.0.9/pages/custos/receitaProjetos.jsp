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
                <html:form action="ReceitaProjetos.do?metodo=filtrar" >
                    <table>
                        <tr>
                            <td>
                            Ano:
                            <td>
                            <td>
                            <html:select property="ano" value="${ano}">
                                <html:option value="">Selecione</html:option>
                                <html:option value="2009">2009</html:option>
                                <html:option value="2008">2008</html:option>
                            </html:select>
                            <td>
                        </tr>
                    </table>
                    <html:submit value="filtrar" styleClass="botao"/>
                    &nbsp; &nbsp;
                    <!--html:button property="a" value="Imprimir" styleClass="botao" onclick="location.href='Relatorio.do?metodo=listagemAtivoFixo'"/-->
                </html:form>

                <display:table id="receitaProjetos" excludedParams="metodo" name="lista" defaultorder="descending" defaultsort="1" sort="list" export="true" requestURI="/ReceitaProjetos.do" class="report" decorator="org.displaytag.decorator.TotalTableDecorator" >
                    <display:column value="-" href="./ReceitaProjetos.do?metodo=detalhe" paramId="centrocusto" paramProperty="centroCusto"></display:column>
                    <display:column property="projeto" title="Projeto" style="white-space: nowrap" url="/ReceitaProjetos.do" />
                    <display:column property="janeiro"  title=" JAN " format="{0, number, #,##0.00}"  style="text-align: right" total="true"  />
                    <display:column property="fevereiro"  title=" FEV " format="{0, number, #,##0.00}"  style="text-align: right" total="true" />
                    <display:column property="marco"  title=" MAR " format="{0, number, #,##0.00}"  style="text-align: right" total="true" />
                    <display:column property="abril"  title=" ABR " format="{0, number, #,##0.00}"  style="text-align: right" total="true" />
                    <display:column property="maio"  title=" MAI " format="{0, number, #,##0.00}"  style="text-align: right" total="true" />
                    <display:column property="junho"  title=" JUN " format="{0, number, #,##0.00}"  style="text-align: right" total="true" />
                    <display:column property="julho"  title=" JUL " format="{0, number, #,##0.00}"  style="text-align: right" total="true" />
                    <display:column property="agosto"  title=" AGO " format="{0, number, #,##0.00}"  style="text-align: right" total="true" />
                    <display:column property="setembro"  title=" SET " format="{0, number, #,##0.00}"  style="text-align: right" total="true" />
                    <display:column property="outubro"  title=" OUT " format="{0, number, #,##0.00}"  style="text-align: right" total="true" />
                    <display:column property="novembro"  title=" NOV " format="{0, number, #,##0.00}"  style="text-align: right" total="true" />
                    <display:column property="dezembro"  title=" DEZ " format="{0, number, #,##0.00}"  style="text-align: right" total="true" />
                    <display:column property="total"  title=" Total " format="{0, number, #,##0.00}"  style="text-align: right" total="true" />

                    <!--display:column property="data" title="Data" sortable="true"  format="{0,date,dd/MM/yyyy}"    /-->
                    <!--display:column property="valor"  title="Valor " sortable="true" format="R$  {0, number, #,##0.00}"  style="text-align: right" /-->
                </display:table>
            </div>
            <jsp:include flush="false" page="/inc/footer.jsp" />
        </div>
        <msg:alert escopo="session"/>
    </body>
</html>
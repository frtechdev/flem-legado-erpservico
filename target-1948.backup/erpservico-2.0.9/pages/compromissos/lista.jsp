<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://flem.org.br/autentica-tag" prefix="acesso"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<html> 
    <head>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="css/800px.css" />
        <link href="<%=request.getContextPath()%>/css/displaytag.css" rel="stylesheet" type="text/css" />
        <title><fmt:message key="aplicacao.nome" /></title>
        <script src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.maskedinput.js"></script>

        <script type="text/javascript">
            function validaForm(){
                var dtInicio = document.getElementById("dtInicio").value.split("/");
                var dtFim = document.getElementById("dtFim").value.split("/");
                if(dtInicio.length !== 3 || dtFim.length !== 3){
                    alert("Informar uma data");
                    return false;
                }
                if(new Date(dtInicio[2], dtInicio[1] - 1, dtInicio[0]) > new Date(dtFim[2], dtFim[1] - 1, dtFim[0]) ){
                    alert("Informar uma data de Pagamento Final maior ou igual a Inicial");
                    return false;
                }
                return true;
            }
            jQuery.noConflict();
            jQuery(document).ready(function(){
                jQuery(".maskData").mask("99/99/9999");
            });
        </script>
    </head>
    <body>
        <acesso:autentica sistema="erpserv" />
        <div id="wrap">
            <jsp:include flush="false" page="/inc/header.jsp" />
            <jsp:include flush="false" page="/inc/sidebar.jsp" />
            <div id="content">
                <h2>Consulta</h2>
                <html:form action="/FonteRecurso.do?metodo=listar" onsubmit="return validaForm();">
                    <table>
                        <tr>
                            <td>Fonte de Recurso:</td>
                            <td><html:text property="codigo" /></td>
                        </tr>
                        <tr>
                            <td>Data Pgto Inicial:</td>
                            <td><html:text property="dataInicio" styleId="dtInicio" styleClass="maskData"/></td>
                        </tr>
                        <tr>
                            <td>Data Pgto Final:</td>
                            <td><html:text property="dataFim" styleId="dtFim" styleClass="maskData"/></td>
                        </tr>
                    </table>
                    <html:submit value="consultar" />
                </html:form>

                <display:table id="item" name="lista" sort="list" export="true" requestURI="/FonteRecurso.do" class="tabelaDisplay" pagesize="20" >
                    <display:column property="id" sortable="true" />
                    <display:column property="dataExibicao" title="Data Pagamento" format="{0,date,dd/MM/yyyy}" sortable="true"  />
                    <display:column property="nomeFornecedor" title="Favorecido" sortable="true" />
                    <display:column property="codigoFornecedor" title="CNPJ/CPF" sortable="true" />
                    <display:column property="tipo" title="Tipo Documento" sortable="true" />
                    <display:column property="numero" title="Nº Documento" sortable="true" />
                    <display:column property="dataAprovacao" title="Data Aprovação" format="{0,date,dd/MM/yyyy}" sortable="true"/>
                    <display:column title="Data Baixa" sortable="true" style="text-align:center">
                        <c:if test="${item.data == null}">---</c:if>
                        <c:if test="${item.data != null}"><fmt:formatDate value="${item.data}" pattern="dd/MM/yyyy" /></c:if>
                    </display:column>
                    <display:column property="valor" title="Valor" format="{0, number, #,##0.00}"  style="text-align: right" sortable="true" />
                    <display:column property="descricao" title="Descrição" sortable="true" />
                    <display:column property="centroCusto" title="Centro de Custo" sortable="true" />
                    <display:column property="rateio" title="Rateio" format="{0, number, #,##0.00}"  style="text-align: right" sortable="true" />
                  
                    
                </display:table>
                
            </div>
            <jsp:include flush="false" page="/inc/footer.jsp" />
        </div>
    </body>
</html>

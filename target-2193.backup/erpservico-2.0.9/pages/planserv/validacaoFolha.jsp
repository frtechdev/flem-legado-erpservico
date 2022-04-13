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
        <script src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript">
            var meses = ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"];
            $(document).ready(function(){
                var atual = new Date();

                $(meses).each(function(index,mes){
                    $('#mes').append($('<option>', {value: index,text : mes}));
                });

                if(atual.getMonth() == 0){
                    $('#ano').append($('<option>', {value: atual.getFullYear()-1,text : atual.getFullYear()-1}));    
                }
                $('#ano').append($('<option>', {value: atual.getFullYear(),text : atual.getFullYear()}));

                var mesAtual = <%=request.getParameter("mes")%>;
                if(mesAtual != null){
                    $('#mes').val(mesAtual);
                }else{
                    $('#mes').val(atual.getMonth() - 1);
                }
            });
        </script>
    </head>
    <body>
        <acesso:autentica sistema="erpserv" />
        <div id="wrap">
            <jsp:include flush="false" page="/inc/header.jsp" />
            <jsp:include flush="false" page="/inc/sidebar.jsp" />
            <div id="content">
                <h2>Validação Folha </h2>
                <html:form action="/ValidacaoFolhaPlanserv.do?" method="GET">
                    <input type="hidden" name="metodo" value="gerarXmlEnvioValidacao"/>
                    <table>
                        <tr>
                            <td>Mes:</td>
                            <td><select id="mes" name="mes"></select></td>
                        </tr>
                        <tr>
                            <td>Ano:</td>
                            <td>
                                <select id="ano" name="ano"></select>
                            </td>
                        </tr>
                    </table>
                    <html:submit value="Gerar Arquivo Mensal" />
                </html:form>
            </div>
            <jsp:include flush="false" page="/inc/footer.jsp" />
        </div>
    </body>
</html>

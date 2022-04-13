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
                var mesAnterior = new Date();
                if(atual.getMonth() === 0){
                    mesAnterior.setMonth(11);
                    mesAnterior.setYear(atual.getFullYear()-1);
                }else{
                    mesAnterior.setMonth(atual.getMonth()-1);
                }
                $('#mes').append($('<option>', {value: mesAnterior.getMonth(),text : meses[mesAnterior.getMonth()]}));
                $('#mes').append($('<option>', {value: atual.getMonth(),text : meses[atual.getMonth()]}));

                if(atual.getMonth() === 0){
                    $('#ano').append($('<option>', {value: mesAnterior.getFullYear(),text : mesAnterior.getFullYear()}));    
                }
                $('#ano').append($('<option>', {value: atual.getFullYear(),text : atual.getFullYear()}));
                var mesAtual = <%=request.getParameter("mes")%>;
                if(mesAtual !== null){
                    $('#mes').val(mesAtual);
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
                <h2>Arquivo Mensal</h2>
                <html:form action="/ArquivoMensal.do?metodo=arquivoMensal" >
                    <table>
                        <tr>
                            <td>Mes:</td>
                            <td><select id="mes" name="mes"></select></td>
                        </tr>
                        <tr>
                            <td>Ano:</td>
                            <td>
                                <select id="ano" name="ano">
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Em tela:</td>
                            <td><html:checkbox property="emTela" value="true"/></td>
                        </tr>
                    </table>
                    <html:submit value="Gerar Arquivo Mensal" />
                </html:form>
                <display:table name="layout" id="detalhe" export="true" style="border:1px solid; display: ${mostrarTabela ? 'visbile' : 'none'}" requestURI="ArquivoMensal.do?metodo=arquivoMensal">
                    <display:column media="html">
                        <html:link target="_blank" title="Titular" href="Planserv.do?metodo=beneficiarios&matricula=${detalhe.codigoDominio}">${detalhe.nomeTitular}</html:link>
                    </display:column>
                    <display:column media="excel">
                        ${detalhe.nomeTitular}
                    </display:column>
                    <display:column property="cpf" title="CPF"/>
                    <display:column property="baseCalculo" title="Salário" format="{0,number,##0.00}"/>
                    <display:column property="qtdDependente" title="Dependentes"/>
                    <display:column property="valorDependente" title="Valor" format="{0,number,##0.00}"/>
                    <display:column property="qtdAgregadoMenor" title="Agregado Menor 24"/>
                    <display:column property="valorDescontoAgregadoMenor" title="Valor" format="{0,number,##0.00}"/>
                    <display:column property="qtdAgregadoMaior" title="Agregado Maior 24"/>
                    <display:column property="valorDescontoAgregadoMaior" title="Valor" format="{0,number,##0.00}"/>
                    <display:column property="valorTitular" title="Valor Titular" format="{0,number,##0.00}"/>
                    <display:column property="valorEspecial" title="Valor Especial" format="{0,number,##0.00}"/>
                    <display:column property="valorConjuge" title="Valor Conjuge" format="{0,number,##0.00}"/>
                    <display:column property="valorCoParticipacao" title="Co Participação" format="{0,number,##0.00}"/>
                    <display:column property="totalDescontos" title="Folha" format="{0,number,##0.00}"/>
                    <display:column property="saldo" title="Saldo" format="{0,number,##0.00}"/>
                    <display:setProperty name="export.excel.filename" value="Faturamento.Planserv.xls"/>
                    <display:setProperty name="export.pdf.filename" value="Faturamento.Planserv.pdf"/>
                </display:table>
            </div>
            <jsp:include flush="false" page="/inc/footer.jsp" />
        </div>
    </body>
</html>

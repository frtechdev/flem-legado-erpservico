<%@page contentType="text/html" errorPage="/erro.jsp"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://flem.org.br/mensagem-tag" prefix="msg"%>
<%@taglib uri="http://flem.org.br/acesso-tag" prefix="acesso"%>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
        <link rel="stylesheet" type="text/css" href="css/800px.css" />
        <link href="css/displaytag.css" rel="stylesheet" type="text/css" />
        <title><fmt:message key="aplicacao.nome" /> versão: <fmt:message key="aplicacao.versao" /></title>
        <script language="JavaScript">
            function confirmarExclusao() {

                var selecao = document.getElementsByName("ids_exclusao");               
                var marcado = false;

                for (i=0; i<selecao.length; i++){
                    if (selecao[i].checked == true) { marcado = true; break; }
                }

                if ( marcado ) {
                    var confirmado = confirm('Tem certeza que deseja excluir os registros selecionados?');
                    return confirmado;
                }
                
                alert("Nenhum registro selecionado para exclusão!");
                return false;
            }            
            
        </script>
    </head>
    <body>
        <div id="wrap">
            <jsp:include flush="false" page="/inc/header.jsp" />
            <jsp:include flush="false" page="/inc/sidebar.jsp" />
            <div id="content">
                <h2>Lista de Parcelas</h2> 
                <fieldset>
                    <legend>Dados do contrato</legend>
                    <table>
                        <tr>
                            <td>Nº do Processo:</td>
                            <td>${contrato.numero}</td>
                        </tr>
                        <tr>
                            <td>Inicio Vigência:</td>
                            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${contrato.inicioVigencia}"/></td>
                        </tr>
                        <tr>
                            <td>Fim Vigência:</td>
                            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${contrato.fimVigencia}"/></td>
                        </tr>
                        <tr>
                            <td>Fornecedor: </td>
                            <td>${contrato.nomeFornecedor}</td>
                        </tr>
                        <tr>
                            <td>Valor:</td>
                            <td><fmt:formatNumber type="NUMBER" value="${contrato.valor}" minFractionDigits="2" maxFractionDigits="2"/></td>
                        </tr>
                        <tr>
                            <td>Saldo a Pagar:</td>
                            <td><fmt:formatNumber type="NUMBER" value="${contrato.saldoAPagar}" minFractionDigits="2" maxFractionDigits="2"/></td>
                        </tr>
                    </table>
                </fieldset>
                <html:form action="/Parcela.do" method="post">
                    <div style="width:100%; text-align:right;">
                        <html:button property="" value="Voltar" onclick="location.href='Contrato.do'" styleClass="botao" />
                        &nbsp;
                        <html:button property="" value="Novo" onclick="location.href='Parcela.do?metodo=novo&idContrato=${contrato.id}'" styleClass="botao" /> 
                        &nbsp;
                        <html:submit property="metodo" value="excluir" styleClass="botao" onclick="return confirmarExclusao();"/>
                    </div>

                    <display:table id="parcela" name="parcelas" defaultsort="6"  sort="list" export="false" excludedParams="metodo" requestURI="./Parcela.do" pagesize="30" class="tabelaDisplay">
                        <display:column style="width: 50;"> <input type="checkbox" name="ids_exclusao" value="${parcela.id}"/></display:column>
                        <display:column property="notaFiscal" title="Nota Fiscal" format="{0,number,#,##0.00}" style="width: 500;"/>
                        <display:column property="dataPagamento"  title="Data do Vencimento" format="{0,date,dd/MM/yyyy}" style="width: 200;"/>
                        <display:column property="valor" title="Valor" format="{0,number,#,##0.00}" style="width: 150;"/>
                        <acesso:verificaPermissao funcionalidade="mtb_fin">
                            <display:column title="Editar" href="./Parcela.do?metodo=selecionar" paramId="id" paramProperty="id" ><img align="middle" src="img/edit.png" width="22" height="22" border="0" alt="Alterar"/></display:column>
                        </acesso:verificaPermissao>
                    </display:table>
                    <br />
                    Total Pago:<fmt:formatNumber type="NUMBER" value="${totalPago}" minFractionDigits="2" maxFractionDigits="2"/>
                </html:form>
            </div>
            <jsp:include flush="false" page="/inc/footer.jsp" />
        </div>
        <msg:alert escopo="session"/>
    </body>
</html>

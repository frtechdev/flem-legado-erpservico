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
        <link href="css/displaytag.css" rel="stylesheet" type="text/css" />
        <title><fmt:message key="aplicacao.nome" /></title>

     <script language="javascript">
         function validar(){
            
            var selecao = document.getElementsByName("selecaoExportar");
           
            for (i=0; i<selecao.length; i++){                
                if (selecao[i].checked == true){                    
                    return true;
                }
            }
            
            alert("Nenhuma RPA foi selecionada.");
            return false;                  
         }        
        
     </script>   
        
    </head>
    <body>
        <html:form action="/Rpa.do?metodo=exportarArquivo" onsubmit="return validar();">
        
            <acesso:autentica sistema="erpserv" />
        <div id="wrap">
            <jsp:include flush="false" page="/inc/header.jsp" />
            <jsp:include flush="false" page="/inc/sidebar.jsp" />
            <div id="content">
                <h3>RPA</h3>
                <html:submit value="Exportar" styleClass="botao"/><br><br><br>
                <div id="help">Selecione os RPAs e depois clique em Exportar para gerar arquivo de carga para o GEM.<br>
                  Para carregar o arquivo vá em: <b>Atividade -> 4-Contas a Pagar -> 9-Interfaces -> 6-Carregar Doc. pagar</b>
                </div>
                <br>
                <display:table id="rpa" name="lista" sort="list" export="false" excludedParams="metodo" requestURI="./Rpa.do" pagesize="30" class="tabelaDisplay">                    
                    <display:column value="${rpa.id}" title="Controle" sortable="true" style="height=25px" />
                    <display:column value="${rpa.pessoa.nome}" title="Nome" sortable="true" />
                    <display:column value="${rpa.dataPagamento}" title="Dt. Pagto" decorator="br.org.flem.sac.web.displaytag.DateWrapper" sortable="true" />
                    <display:column value="${rpa.valorLiquido}" title="Vl. Líq. (R$)" style="text-align: right;" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" sortable="true" />
                    <display:column value="${rpa.situacaoDocumento}" title="Situação" style="text-align: left;" sortable="true" />
                    
                    <display:column title="Exp." style="text-align: center;">
                        <c:if test="${rpa.codSituacaoDocumento == 0}"> <input type="checkbox" name="selecaoExportar" value="${rpa.id}" alt="Selecione para exportar" /> </c:if> 
                    </display:column>
                        
                </display:table>
                
                <html:submit value="Exportar" styleClass="botao"/>
                <br>
            </div>
            
            <jsp:include flush="false" page="/inc/footer.jsp" />
            <mensagem:alert escopo="session"/>
        </div>
        </html:form>
    </body>
</html>

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

        <script language="JavaScript">
            function submitFiltro(){
                document.forms[0].action = "<%=request.getContextPath()%>/Caixa.do?metodo=filtrar";
                document.forms[0].submit();
            }
        </script>  

        
    </head>
    <body>
        <html:form action="/Caixa.do" method="post">
            <acesso:autentica sistema="erpserv" />
        <div id="wrap">
            <jsp:include flush="false" page="/inc/header.jsp" />
            <jsp:include flush="false" page="/inc/sidebar.jsp" />
            <div id="content">
                <h3>Caixa</h3>
                
                <table>
                            <tr>
                                <td>Data Inicio:
                                    <html:text property="data_Inicio"  styleId="data_Inicio" value="${filtro_dataInicio}" size="10" maxlength="10" styleClass="requerido"  />
                                </td>   
                                <td>Data Fim:
                                    <html:text property="data_Fim" styleId="data_Fim" value="${filtro_dataFim}" size="10" maxlength="10" styleClass="requerido" /> *(Preencher data de inicio e fim)
                                    &nbsp;
                                </td>
                                <tr>
                                    <td>Gerar para área de Transferência:
                                    <html:checkbox property="emTela" value="true"/></td>
                                </tr>
                            </tr>
                        </table>
                        <html:submit property="metodo" value="filtrar" onclick="submitFiltro()" styleClass="botao"/>
                        &nbsp;
                        
                        <display:table id="func" name="lista" sort="list" export="false" excludedParams="metodo" requestURI="./Caixa.do" pagesize="30" class="tabelaDisplay">                    
                            <display:column value="${func.id}" title="Id" sortable="true" style="height=25px" />
                            <display:column value="${func.nome}" title="Nome" sortable="true" />
                            <display:column value="${func.nome_curto}" title="NomeCurto" sortable="true" />
                            <display:column value="${func.cpf}" title="CPF" sortable="true" />
                            <display:column value="${func.email}" title="Email" sortable="true" />
                            <display:column value="${func.nomePai}" title="Pai" sortable="true" />
                            <display:column value="${func.nomeMae}" title="Mãe" sortable="true" />  
                        </display:table>
                
                        <h3>Área de Transferência</h3>
                <br>
                        <display:table id="func" name="listafuncionario" defaultsort="5" sort="list" export="false" excludedParams="metodo"  pagesize="30" class="tabelaDisplay">
                            <display:column style="width:22px;">
                                <input type="checkbox" name="ids_exclusao" title="Id" value="${func.id}"/>
                            </display:column>
                            <display:column property="nomeCompleto" title="Nome Completo" sortable="true" />
                            <display:column property="nomeReduzido" title="Nome reduzido" />
                            <display:column property="cpf" title="CPF" sortable="true" />
                            <display:column property="orgaoEmissorDocumento" title="Orgão Emissor Doc" />
                            <display:column property="codBanco" title="Codigo do banco" />
                            <display:column property="agencia" title="Agencia" />
                            <display:column property="op" title="Operação"  />
                            <display:column property="conta" title="Conta"  />
                            <display:column property="digito" title="Digito" />
                            <display:column title="" href="./Caixa.do?metodo=selecionar&id" paramId="id" paramProperty="id" ><img align="middle" src="img/edit.png" width="22" height="22" border="0" alt="editar"/></display:column>
                        </display:table>
                            
                            <html:submit property="metodo" value="excluir" styleClass="botao" onclick="return confirmarExclusao();"/>
                             <html:submit property="metodo" value="Arquivo" styleClass="botao"/>
            </div>
            <jsp:include flush="false" page="/inc/footer.jsp" />
            <mensagem:alert escopo="session"/>
        </div>
        </html:form>
    </body>
</html>

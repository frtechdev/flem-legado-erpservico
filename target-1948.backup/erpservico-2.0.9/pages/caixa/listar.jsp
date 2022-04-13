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
        <script language="JavaScript">
            
            function confirmarExclusao() {

                var selecao = document.getElementsByName("ids_exclusao");               
                var marcado = false;

                for (i=0; i<selecao.length; i++){
                    if (selecao[i].checked == true) { marcado = true; break; }
                }

                if ( marcado ) {
                    var confirmado = confirm('A exclusão de um contrato pode implicar diretamente na geração dos relatórios financeiros.\nCertifique-se que de os contratos que estão sendo excluídos não comprometerão a integridade das informações.\n\nDeseja realmente realizar esta exclusão?');
                    return confirmado;
                }
                
                alert("Nenhum registro selecionado para exclusão!");
                return false;
            }
            
        </script>
    </head>
    <body>
        <acesso:autentica sistema="erpserv" />
        <div id="wrap">
            <jsp:include flush="false" page="/inc/header.jsp" />
            <jsp:include flush="false" page="/inc/sidebar.jsp" />
            <div id="content">
            
                <h3>Área de Transferência</h3>
                <html:form action="/Caixa.do"  onsubmit="return validar(this);">
                        <div style="width:100%; text-align:right;">

                            <html:submit property="metodo" value="excluir" styleClass="botao" onclick="return confirmarExclusao();"/>
                            <html:submit property="metodo" value="gerarArquivo" styleClass="botao"/>
                        </div>
                        <display:table id="func" name="listafuncionario" defaultsort="5" sort="list" export="false" excludedParams="metodo" requestURI="./Caixa.do?metodo=listar" pagesize="30" class="tabelaDisplay">
                            <display:column style="width: 50;"> 
                                <input type="checkbox" name="ids_exclusao" value="${contrato.id}"/>
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


                
                <br>
                </html:form>
            </div>

            <jsp:include flush="false" page="/inc/footer.jsp" />
            <mensagem:alert/>
            <mensagem:alert escopo="session" />
        </div>
    </body>
</html>

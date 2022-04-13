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
        <link href="css/displaytag.css" rel="stylesheet" type="text/css" />
        <title><fmt:message key="aplicacao.nome" /></title>

        <script src='<%=request.getContextPath()%>/dwr/interface/Funcoes.js'></script>
        <script src='<%=request.getContextPath()%>/dwr/engine.js'></script>
        <script src='<%=request.getContextPath()%>/dwr/util.js'></script>
        <script language="JavaScript" src='<%=request.getContextPath()%>/js/dwr.js'  type="text/javascript" ></script>
        <script type="text/javascript" >

            function init() {
                useLoadingImage("img/carregar.gif");
            <c:if test="${matricula != null}">
                Funcoes.obterListaPlanserv($('matricula').value, beneficiarios);
            </c:if>
                var date = new Date();
                date.setDate(1);
                $('dtbeneficio').value = formatDate(date);
            }

            function formatDate(date) {
                var dd = date.getDate();
                var mm = date.getMonth() + 1;
                var yyyy = date.getFullYear();
                if (dd < 10) {
                    dd = '0' + dd;
                }
                if (mm < 10) {
                    mm = '0' + mm;
                }

                return dd + "/" + mm + "/" + yyyy;
            }

            function preencher(dado) {
                DWRUtil.setValue("cdPlano", dado.cdPlano);
            }

            function beneficiarios(dado) {
                DWRUtil.removeAllOptions("beneficiario");
                DWRUtil.addOptions("beneficiario", [{key: "", value: "Selecione:"}], "key", "value");
                DWRUtil.addOptions("beneficiario", dado);
            }

            function preencherAdmissao(dado) {
                $('dtAdmissao').innerHTML = dado;
            }
            function preencherDepartamento(dado) {
                $('departamento').innerHTML = dado;
            }
            function preencherNome(dado) {
                $('nomeCompleto').innerHTML = dado;
            }

            function confirmarExclusao() {

                var selecao = document.getElementsByName("ids_exclusao");
                var marcado = false;

                for (i = 0; i < selecao.length; i++) {
                    if (selecao[i].checked == true) {
                        marcado = true;
                        break;
                    }
                }

                if (marcado) {
                    var confirmado = confirm('Deseja realmente realizar esta exclusão?');
                    return confirmado;
                }

                alert("Nenhum registro selecionado para exclusão!");
                return false;
            }


            function limpa() {
                if (document.getElementById('cod_banco').value !== "") {
                    document.getElementById('agencia').value = "";
                    document.getElementById('op').value = "";
                    document.getElementById('conta').value = "";
                    document.getElementById('digito').value = "";
                }
            }

        </script>


    </head>
    <body onload="init();" >
        <acesso:autentica sistema="erpserv" />
        <div id="wrap">
            <jsp:include flush="false" page="/inc/header.jsp" />
            <jsp:include flush="false" page="/inc/sidebar.jsp" />
            <div id="content">
                <h2>Cadastro de Funcionário para Abertura de Conta </h2>
                <html:form action="/Conta.do" >
                    <table>
                        <tr><td>Funcionarios: </td><td>
                                <html:select styleId="funcionarios" property="funcionario" onchange="$('matricula').value = this.value;$('btPesquisar').click();limpa();">
                                    <html:option value="">Selecione</html:option>
                                    <html:optionsCollection label="nome" value="id" name="matriculas"/>
                                </html:select> </td></tr>
                        <tr><td>Matricula : </td><td><html:text styleId="matricula" property="matricula" /> <input type="button" id="btPesquisar" onclick="Funcoes.obterListaPlanserv($('matricula').value, beneficiarios);Funcoes.obterNomeCompleto($('matricula').value, preencherNome);Funcoes.obterDataAdmissao($('matricula').value, preencherAdmissao);Funcoes.obterDepartamentoDoFuncionario($('matricula').value, preencherDepartamento);" value="Pesquisar" class="botao"/></td></tr>
                        <tr><td>Funcionário : </td><td><span id="nomeCompleto"  > </span></td></tr>
                        <tr><td>Data de Admissão : </td><td><span id="dtAdmissao"  > </span></td></tr>
                        <tr><td>Departamento : </td><td><span id="departamento"  > </span></td></tr>


                        <tr><td>Codigo banco: </td><td>
                                <html:text property="cod_banco" styleId="cod_banco" maxlength="3" size="3" />
                            </td></tr>
                        <tr><td>Agência: </td><td>
                                <html:text property="agencia" styleId="agencia" maxlength="4" size="4"/>
                            </td></tr>
                        <tr><td>Op: </td><td>
                                <html:text property="op" styleId="op" maxlength="4" size="4"/>
                            </td></tr>
                        <tr><td>Conta: </td><td>
                                <html:text property="conta" styleId="conta" maxlength="8" size="8"/>
                            </td></tr>
                        <tr><td>Dígito: </td><td>
                                <html:text property="digito" styleId="digito" maxlength="2" size="2"/>
                            </td></tr>
                        <tr><td></td><td>
                                <html:submit property="metodo" value="adicionar" styleClass="botao"/>
                            </td></tr>
                    </table>

                    <br>
                    <display:table id="func" name="listafuncionario" defaultsort="5" sort="list" export="false" excludedParams="metodo" requestURI="./Conta.do" pagesize="30" class="tabelaDisplay">
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
                        <display:column title="" href="./Conta.do?metodo=selecionar&id" paramId="id" paramProperty="id" ><img align="middle" src="img/edit.png" width="22" height="22" border="0" alt="editar"/></display:column>
                        <display:column title="" href="./Conta.do?metodo=excluir&id" paramId="id" paramProperty="id" ><img align="middle" src="img/delete.png" width="22" height="22" border="0" alt="editar"/></display:column>
                    </display:table>
                    <br>

                    <html:submit property="metodo" value="gerarArquivo" styleClass="botao" />
                    <html:submit property="metodo" value="gerarArquivoXLSX" styleClass="botao" />
                    <html:submit property="metodo" value="excluir" styleClass="botao" onclick="return confirmarExclusao();"/>
                    <br>
                </div>
            </html:form>
            <jsp:include flush="false" page="/inc/footer.jsp" />

        </div>
    </body>
</html>
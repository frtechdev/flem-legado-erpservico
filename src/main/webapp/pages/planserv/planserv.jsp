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

            function init(){
                useLoadingImage("img/carregar.gif");
                <c:if test="${matricula != null}">
                    Funcoes.obterListaPlanserv($('matricula').value, beneficiarios );
                </c:if>
                var date = new Date();
                date.setDate(1);
                $('dtbeneficio').value = formatDate(date);
            }

            function formatDate(date){
                var dd = date.getDate();
                var mm = date.getMonth()+1;
                var yyyy = date.getFullYear();
                if(dd<10){
                    dd='0'+dd;
                } 
                if(mm<10){
                    mm='0'+mm;
                }

                return dd+"/"+mm+"/"+yyyy;
            }

            function preencher(dado) {
                DWRUtil.setValue("cdPlano",dado.cdPlano);
            }

            function beneficiarios(dado){
                DWRUtil.removeAllOptions("beneficiario");
                DWRUtil.addOptions("beneficiario", [{key:"", value:"Selecione:"}], "key", "value");                        
                DWRUtil.addOptions("beneficiario", dado);
            }
            
            function preencherAdmissao(dado){
                $('dtAdmissao').innerHTML = dado;
            }
            function preencherDepartamento(dado){
                $('departamento').innerHTML = dado;
            }
        </script>
        <html:javascript formName="planservForm" method="validar" />
    </head>
    <body onload="init();" >
        <acesso:autentica sistema="erpserv" />
        <div id="wrap">
            <jsp:include flush="false" page="/inc/header.jsp" />
            <jsp:include flush="false" page="/inc/sidebar.jsp" />
            <div id="content">
                <h2>Movimentação Cadastral do Planserv </h2>
                <html:form action="/Planserv.do?metodo=adicionar" onsubmit="return validar(this);" >
                    <table>
                        <tr><td>Funcionarios: </td><td>
                                <html:select styleId="funcionarios" property="funcionario" onchange="$('matricula').value = this.value;$('btPesquisar').click();">
                                    <html:option value="">Selecione</html:option>
                                    <html:optionsCollection label="nome" value="id" name="matriculas"/>
                                </html:select> </td></tr>
                        <tr><td>Matricula : </td><td><html:text styleId="matricula" property="matricula" /> <input type="button" id="btPesquisar" onclick="Funcoes.obterListaPlanserv($('matricula').value, beneficiarios );Funcoes.obterDataAdmissao($('matricula').value, preencherAdmissao);Funcoes.obterDepartamentoDoFuncionario($('matricula').value, preencherDepartamento);" value="Pesquisar" class="botao"/></td></tr>
                        <tr><td>Data de Admissão : </td><td><span id="dtAdmissao"  > </span></td></tr>
                        <tr><td>Departamento : </td><td><span id="departamento"  > </span></td></tr>
                        <tr><td>Funcionário / Dependente : </td><td>
                                <html:select styleId="beneficiario" property="beneficiario" onchange="javascript:Funcoes.obterDadosPlanserv($('matricula').value, $('beneficiario').value, preencher);" >
                                </html:select>
                        </td></tr>
                        <tr><td>Operação : </td><td>
                                <html:radio property="operacao" styleId="operacao" value="0" />Inclusão de Plano<br>
                                <html:radio property="operacao" styleId="operacao" value="1" />Alteração de Dados Cadastrais<br>
                                <html:radio property="operacao" styleId="operacao" value="2" />Alteração de Plano<br>
                                <html:radio property="operacao" styleId="operacao" value="3" />Exclusão do Plano<br>
                                <html:radio property="operacao" styleId="operacao" value="4" />Reativação do Plano<br>
                        </td></tr>
                        <tr><td>Data da operação (solicitação do plano): </td><td>
                                <html:text property="dtoperacao" styleId="dtoperacao" />
                        </td></tr>
                        <tr><td>Data da inicio do beneficio : </td><td>
                                <html:text property="dtbeneficio" styleId="dtbeneficio" />
                        </td></tr>
                        <tr><td>Plano : </td><td>
                                <html:radio property="cdPlano" styleId="plano" value="2530" />B&aacute;sico<br>
                                <html:radio property="cdPlano" styleId="plano" value="4530" />Especial<br>
                        </td></tr>
                        <tr><td></td><td>
                                <html:submit value="Adicionar" styleClass="botao"/>
                        </td></tr>
                    </table>
                </html:form>
                <br>
                <display:table id="beneficiario" name="set" defaultsort="0" sort="list" export="false" excludedParams="metodo" requestURI="/Planserve.do" class="tabelaDisplay" >
                    <display:column property="matricula" title="Matricula" />
                    <display:column property="nome" title="Nome" />
                    <display:column property="nomePlano" title="Plano" />
                    <display:column property="nomeParentesco" title="Parentesco" />
                    <display:column property="nomeOperacao" title="Operacao" />
                </display:table>
                <br>
                <input type="button" onclick="location.href='Planserv.do?metodo=arquivo';" value="Gerar Arquivo" class="botao"/>
                <br>
            </div>
            
            <jsp:include flush="false" page="/inc/footer.jsp" />
            
        </div>
    </body>
</html>

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
        <script language="javascript">
            ;
            function moveDualList(srcList, destList, moveAll) {
                if (srcList == null) {
                    return;
                }
                if ((srcList.selectedIndex == -1) && (moveAll == false)) {
                    return;
                }
                newDestList = new Array(destList.options.length);
                for (var len = 0; len < destList.options.length; len++) {
                    if(destList.options[len] != null) {
                        newDestList[len]= new Option(destList.options[len].text, destList.options[len].value, destList.options[len].defaultSelected, destList.options[len].selected);
                    }
                }
                for (var i = 0; i < srcList.options.length; i++) {
                    if(srcList.options[i] != null && srcList.options[i].value != "" &&(srcList.options[i].selected == true || moveAll)) {
                        newDestList[len]= new Option(srcList.options[i].text, srcList.options[i].value, srcList.options[i].defaultSelected, srcList.options[i].selected);
                        len++;
                    }
                }
                newDestList.sort(compareOptionValues);
                for (var j = 0; j < newDestList.length; j++) {
                    if(newDestList[j] != null) {
                        destList.options[j] = newDestList[j];
                    }
                }
                for (var k = srcList.options.length - 1; k >= 0; k--) {
                    if(srcList.options[k] != null && srcList.options[k].value != "" &&(srcList.options[k].selected == true || moveAll)) {
                        srcList.options[k]= null;
                    }
                }   
            }
            function compareOptionValues(a, b) {
                var sA = a.text;
                var sB = b.text;
                if (sA == sB) {
                    return 0;
                }else if (sA < sB) {
                    return -1;
                }else {
                    return 1;
                }
            }
            
            function selecionarDepartamentos(){
                for (var i = document.getElementById('departamentosEscolhidos').options.length - 1; i >= 0; i-- ){
                    document.getElementById('departamentosEscolhidos').options[i].selected = true;
                }
                return true;
            }
        </script>
        <html:javascript formName="relatorioForm" method="validar" />
    </head>
    <body>
        <acesso:autentica sistema="erpserv" />
        <div id="wrap">
            <jsp:include flush="false" page="/inc/header.jsp" />
            <jsp:include flush="false" page="/inc/sidebar.jsp" />
            <div id="content">
                <h2>Relação de funcionários desligados</h2>
                <html:form action="Relatorio.do?metodo=filtrarDemitidos" onsubmit="return (selecionarDepartamentos() && validar(this));" >
                    <table cellpadding="0" cellspacing="0">
                        <tr>
                            <td>Data inicial: </td>
                            <td><html:text property="dataInicial" size="10" maxlength="10" styleClass="requerido" /> </td>
                        </tr>
                        <tr>
                            <td>Data final: </td>
                            <td><html:text property="dataFinal" size="10" maxlength="10" styleClass="requerido" /> </td>
                        </tr>
                    </table>
                    <table cellpadding="0" cellspacing="0">
                        <tr>
                            <td>
                                <table>
                                    <tr><td>Selecionar lotação:</td></tr>
                                    <tr><td>
                                            <html:select property="departamentos" styleId="departamentosLivres" style="width: 320px; height: 100px;" multiple="multiple">
                                                <html:optionsCollection label="nome" name="listaDepartamentos" value="codigoDominio" />
                                            </html:select>
                                        </td></tr>
                                </table>
                            </td>
                            <td style="vertical-align: bottom;">
                                <table>
                                    <tr><td>
                                            <div style="text-align: center"><html:button property="metodo" value="add todos" styleClass="bt_ir_todos" onclick="moveDualList(document.getElementById('departamentosLivres'), document.getElementById('departamentosEscolhidos'), true);" /></div>
                                            <div style="text-align: center"><html:button property="metodo" value="add" styleClass="bt_ir_um" onclick="moveDualList(document.getElementById('departamentosLivres'), document.getElementById('departamentosEscolhidos'), false);" /></div>
                                            <div style="text-align: center"><html:button property="metodo" value="remover" styleClass="bt_vir_um" onclick="moveDualList(document.getElementById('departamentosEscolhidos'), document.getElementById('departamentosLivres'), false);" /></div>
                                            <div style="text-align: center"><html:button property="metodo" value="remover todos" styleClass="bt_vir_todos" onclick="moveDualList(document.getElementById('departamentosEscolhidos'), document.getElementById('departamentosLivres'), true);" /></div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td>
                                <table>
                                    <tr><td>Lotações selecionadas:</td></tr>
                                    <tr>
                                        <td>
                                            <html:select property="departamentosEscolhidos" styleId="departamentosEscolhidos" styleClass="requerido" style="width: 320px; height: 100px;" multiple="multiple" >
                                                <html:optionsCollection label="nome" name="departamentosEscolhidos" value="codigoDominio" />
                                            </html:select>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="submit" value="filtrar" class="botao"/>
                            </td>
                        </tr>
                    </table>
                </html:form>
                <br>
                <table>
                    <tr>
                        <td>Departamentos:</td>
                    </tr> 
                    <tr> 
                        <c:forEach items="${departamentosEscolhidos}" var="departamento" varStatus="count">
                            <td>
                                ${departamento.nome}
                            </td>
                        </c:forEach>
                    </tr>
                </table>
                <display:table id="funcionario" name="demitidos" defaultsort="0" sort="list" export="true"  requestURI="/Relatorio.do" class="tabelaDisplay" >
                    <display:column property="admissao" title="Admissão" />
                    <display:column property="recisao" title="Recisão" />
                    <display:column property="matricula" title="Matricula" />
                    <display:column property="nome" title="Nome" />
                    <display:column property="lotacao" title="Lotação" />
                </display:table>
            </div>

            <jsp:include flush="false" page="/inc/footer.jsp" />

        </div>
    </body>
</html>

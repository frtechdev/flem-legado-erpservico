<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://flem.org.br/autentica-tag" prefix="acesso"%>
<%@taglib uri="http://flem.org.br/mensagem-tag" prefix="mensagem"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>


<html:html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="css/800px.css" />
        <link href="<%=request.getContextPath()%>/css/displaytag.css" rel="stylesheet" type="text/css" />
        <script src="<%=request.getContextPath()%>/dwr/interface/Funcoes.js"></script>
        <script src="<%=request.getContextPath()%>/dwr/engine.js"></script>
        <script src="<%=request.getContextPath()%>/dwr/util.js"></script>
        
        <title><fmt:message key="aplicacao.nome" /></title>

        <html:javascript formName="departamentoForm" method="validar"/>

        <script>
            function obterDepartamento() {
                Funcoes.obterDepartamento(DWRUtil.getValue("codigoId"), setCamposDepartamento);
            }

            function setCamposDepartamento(dadosDepartamento){
                dados = dadosDepartamento.split(";");
                $("nom_dept").value = dados[1];
                //$("cod_lotacao_hr").value = dados[0];
            }
        </script>

    </head>
    <body>
        <acesso:autentica sistema="erpserv" />
        <div id="wrap">
            <jsp:include flush="false" page="/inc/header.jsp" />
            <jsp:include flush="false" page="/inc/sidebar.jsp" />
            <div id="content">
                <h2>Novo Departamento</h2>
                <html:form action="/Departamento.do?metodo=adicionar" onsubmit="return validar(this);" method="post" enctype="multipart/form-data" >

                    <table border="0" width="100%">
                        <tr bgcolor="#EEEEEE">
                            <td colspan="2"><strong>Departamentos Domínio</strong></td>
                        </tr>

                        <tr><td height="10"></td></tr>

                        <tr>
                            <td colspan="2">
                                Lotação Domínio:<br />
                                <html:select property="departamento.cod_lotacao_dominio" styleId="codigoId" style="width: 400px;" onchange="obterDepartamento()">
                                    <html:option value="">-- Selecione --</html:option>
                                    <html:optionsCollection name="listaDepartamento" value="codigoDominio" label="nome"/>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                SIGLA (Lotação HR)<br />
                                <html:text property="departamento.cod_lotacao_hr" styleId="codigoId" style="width: 400px;"/>
                            </td>
                        </tr>
                        

                        <tr><td height="10"></td></tr>

                        <tr bgcolor="#EEEEEE">
                            <td colspan="2"><strong>Informar dados para o Departamento Selecionado</strong></td>
                        </tr>

                        <tr><td height="10"></td></tr>

                        <tr>
                            <td colspan="2">
                                Nome:<br />
                                <html:text property="departamento.nom_dept" styleId="nom_dept" style="background-color:#DDDDDD; width: 400px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Código do Centro de Custo<br />
                                <html:select property="departamento.cod_ccusto" styleId="cod_ccusto" style="width: 400px;">
                                    <html:option value="">-- Selecione --</html:option>
                                    <html:optionsCollection name="listaCentCustoGEM" value="id" label="descricaoCompleta"/>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                Competências<br />
                                <html:textarea property="departamento.des_competencia" cols="70" rows="5" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                Código do Centro de Responsabilidade<br />
                                <html:select property="departamento.cod_cresp" styleId="cod_crespId" style="width: 400px;">
                                    <html:option value="">-- Selecione --</html:option>
                                    <html:optionsCollection name="listaCentRespHR" value="cod_cresp" label="nom_cresp"/>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <html:submit value="salvar" styleClass=""/>
                            </td>
                        </tr>
                </table>
                </html:form>
                <jsp:include flush="false" page="/inc/footer.jsp" />
            </div>

        </div>
    </body>
</html:html>
<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://flem.org.br/autentica-tag" prefix="acesso"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://flem.org.br/mensagem-tag" prefix="msg"%>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="css/800px.css" />
        <link href="css/displaytag.css" rel="stylesheet" type="text/css" />
        <title><fmt:message key="aplicacao.nome" /></title>
        <script>
            function mostraDetalheEquipamento(codigo, serial, tombo) {
                alert(
                    "Código: "+codigo+"\n\n"+
                    "Serial: "+serial+"\n\n"+
                    "Tombo: "+tombo+"\n\n"
                );
            }
        </script>
    </head>
    <body id="body" >
        <acesso:autentica sistema="erpserv" />
        <div id="wrap" >
            <jsp:include flush="false" page="/inc/header.jsp" />
            <jsp:include flush="false" page="/inc/sidebar.jsp" />
            <div id="content">
                <h2>Ativo Fixo</h2>
                <html:form action="AtivoFixo.do?metodo=filtrar" >
                    <table>
                        <tr>
                            <td>
                                Código:
                            <td>
                            <td>
                                <html:text property="codigo" size="30" value="${AtivoFixoAction_codigo}"/>
                            <td>
                        </tr>
                        <tr>
                            <td>
                                Descrição:
                            <td>
                            <td>
                                <html:text property="descricao" size="30" value="${AtivoFixoAction_descricao}"/>
                            <td>
                        </tr>
                        <tr>
                            <td>
                                Responsável:
                            <td>
                            <td>
                                <html:text property="responsavel" size="30" value="${AtivoFixoAction_responsavel}"/>
                            <td>
                        </tr>
                        <tr>
                            <td>
                                Detentor:
                            <td>
                            <td>
                                <html:text property="detentor" size="30" value="${AtivoFixoAction_detentor}"/>
                            <td>    
                        </tr>
                        <tr>
                            <td>
                                Tombo:
                            <td>
                            <td>
                                <html:text property="tombo" size="30" value="${AtivoFixoAction_tombo}"/>
                            <td>
                        </tr>
                        <tr>
                            <td>
                                Situação:
                            <td>
                            <td>
                                <html:select property="situacao" value="${AtivoFixoAction_situacao}">
                                    <html:option value="">Todas</html:option>
                                    <html:option value="0">Ativo em uso</html:option>
                                    <html:option value="1">Ativo fora uso</html:option>
                                    <html:option value="2">Inativo</html:option>
                                </html:select>
                            <td>
                        </tr>
                        <tr>
                            <td>
                                Localização:
                            <td>
                            <td>
                                <html:text property="localizacao" size="30" value="${AtivoFixoAction_localizacao}"/>
                            <td>
                        </tr>
                        <tr>
                            <td>
                                Serial:
                            <td>
                            <td>
                                <html:text property="serial" size="30" value="${AtivoFixoAction_serial}"/>
                            <td>
                        </tr>
                    </table>
                    <html:submit value="filtrar" styleClass="botao"/>
                    &nbsp; &nbsp;
                    <html:button property="a" value="Imprimir" styleClass="botao" onclick="location.href='Relatorio.do?metodo=listagemAtivoFixo'"/>
                </html:form>
                
                <display:table id="ativofixo" excludedParams="metodo" name="lista" defaultorder="descending" defaultsort="1" sort="list" export="false" requestURI="/AtivoFixo.do" class="tabelaDisplay"  pagesize="50">
                    <display:column property="tombo" title="Tombo" sortable="true"   />
                    <display:column property="centroCusto" title="Centro Custo" sortable="true"   />
                    <display:column property="descricao"  title="Descrição" sortable="true"   />
                    <display:column property="responsavel"  title="Responsavel" sortable="true"  />
                    <display:column property="detentor"  title="Detentor" sortable="true"  />
                    <display:column property="localizacao"  title="Localização" sortable="true"   />
                    <display:column property="situacao" title="Situação" sortable="true"  decorator="br.org.flem.sac.web.displaytag.Situacao" />
                    <display:column title="Detalhe" sortable="true" ><div style="cursor: pointer;" onclick=" mostraDetalheEquipamento('${ativofixo.codigo}', '${ativofixo.serial}', '${ativofixo.tombo}');">Detalhe</div></display:column>
                </display:table>
            </div>
             <jsp:include flush="false" page="/inc/footer.jsp" />
        </div>
        <msg:alert escopo="session"/>  
    </body>
</html>
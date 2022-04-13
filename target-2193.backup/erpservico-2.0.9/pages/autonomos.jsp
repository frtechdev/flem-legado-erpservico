<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://flem.org.br/autentica-tag" prefix="acesso"%>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="css/800px.css" />
        <link href="css/displaytag.css" rel="stylesheet" type="text/css" />
        <script src='<%=request.getContextPath()%>/dwr/interface/Funcoes.js' ></script>
        <script src='<%=request.getContextPath()%>/dwr/engine.js' ></script>
        <script src='<%=request.getContextPath()%>/dwr/util.js' ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/js/dwr.js"  type="text/javascript" > </script>
        <script language="JavaScript"  type="text/javascript" >
            function init() {
                useLoadingImage("img/carregar.gif");
            }
            
            function criarArquivo() {
            var resp = Funcoes.geraArquivoAutonomo();
                $("help").innerHTML="Acesse o Consist HR.<br> Use o comando 5z.<br>Clique a função SAUTON. <br> Execute! <br> Use o comando 74 para verificar a execução do serviço.";
            }
            
            function atualizarInss() {
            var resp = Funcoes.atualizarInssAutonomo();
            DWRUtil.setValue("help","");
            }
        </script>
        <title><fmt:message key="aplicacao.nome" /></title>
    </head>
    <body onload="init();" >
        <acesso:autentica sistema="erpserv" />
        <div id="wrap">
            <jsp:include flush="false" page="../inc/header.jsp" />
            <jsp:include flush="false" page="../inc/sidebar.jsp" />
            <div id="content">
                <h2>Controle de Autônomos</h2>
                <li><a href="./Autonomo.do">Lista Autônomos - GEM</a></li>
                <li><a href="./Autonomo.do?metodo=inconsistentes">Lista Autônomos inconsistentes - GEM</a></li>
                <li><a href="#" onclick="atualizarInss();">Atualizar INSS</a></li>
                <li><a href="#" onclick="criarArquivo();">Criar Arquivo</a></li>
                <br>
                <div id="help"></div>
            </div>
            <jsp:include flush="false" page="../inc/footer.jsp" />
        </div>
    </body>
</html>

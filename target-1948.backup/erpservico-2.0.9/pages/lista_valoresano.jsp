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
        <link href="<%=request.getContextPath()%>/css/displaytag.css" rel="stylesheet" type="text/css" />
        <title><fmt:message key="aplicacao.nome" /></title>
    </head>
    <body>
        <acesso:autentica sistema="erpserv" />
        <div id="wrap">
            <display:table id="item" name="lista" defaultsort="1" sort="list" export="true" excludedParams="metodo" requestURI="/ValorAutonomoAno.do" class="tabelaDisplay" >
                <display:column property="entidade.nomeExtenso" title="Nome " sortable="true" />
                <display:column property="base" title="Base" sortable="true"  decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column property="dependentes" title="nº Dep." sortable="true"   />
                <display:column value="${item.insd1}" title="INSD/JAN" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.ir1}" title="IR/JAN" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.insd2}" title="INSD/FEV" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.ir2}" title="IR/FEV" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.insd3}" title="INSD/MAR" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.ir3}" title="IR/MAR" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.insd4}" title="INSD/ABR" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.ir4}" title="IR/ABR" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.insd5}" title="INSD/MAI" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.ir5}" title="IR/MAI" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.insd6}" title="INSD/JUN" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.ir6}" title="IR/JUN" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.insd7}" title="INSD/JUL" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.ir7}" title="IR/JUL" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.insd8}" title="INSD/AGO" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.ir8}" title="IR/AGO" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.insd9}" title="INSD/SET" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.ir9}" title="IR/SET" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.insd10}" title="INSD/OUT" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.ir10}" title="IR/OUT" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.insd11}" title="INSD/NOV" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.ir11}" title="IR/NOV" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.insd12}" title="INSD/DEZ" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item.ir12}" title="IR/DEZ" decorator="br.org.flem.sac.web.displaytag.MoedaWrapper" />
                <display:column value="${item}" title="INSD/TOTAL" decorator="br.org.flem.sac.web.displaytag.TotalInsdWrapper" /> 
                <display:column value="${item}" title="IR/TOTAL" decorator="br.org.flem.sac.web.displaytag.TotalIrWrapper" />
            </display:table>
        </div>
    </body>
</html>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>

<div id="header">
    <a href="Home.do">
        <img align="left" width="200px" height="60px" src="img/logoerp.gif" alt="ERP - Página Inicial "/>
    </a>
    <img align="right" src="img/flemlogo.gif" alt="Flem Web"/>
</div>
 
<div id="subHeader" style="text-align:right; font-size:7pt; color:#6B94CD; font-weight:bold;">     
    <jsp:useBean id="hoje" class="java.util.Date"/>
    <c-rt:set var="usuario" value="<%=((br.org.flem.fw.service.IUsuario) 
    session.getAttribute(br.org.flem.fwe.util.Constante.USUARIO_LOGADO))%>" />
    <fmt-rt:formatDate value="${hoje}" type="DATE" pattern="dd/MM/yyyy"/>, Olá <c-rt:out value="${usuario.nome}"/>
</div>

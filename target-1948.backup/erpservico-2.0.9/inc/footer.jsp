<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://flem.org.br/mensagem-tag" prefix="msg" %>

<div id="footer">
    <p>
        &copy; 2011 Fundação Luis Eduardo Magalhães |
        versão: <fmt:message key="aplicacao.versao" /> | 
        ultima atualização: <fmt:message key="aplicacao.atualizacao" /> &nbsp;&nbsp;
    </p>
    <msg:alert escopo="session"/>
</div>
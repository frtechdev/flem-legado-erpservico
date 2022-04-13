<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<FOLHA> 
    <COD_ORGAO>${validacaoFolha.codOrgao}</COD_ORGAO>
    <VALOR_TOTAL_ORGAO><fmt:formatNumber type="number" value="${validacaoFolha.valorTotalOrgao}" pattern="0.00"/></VALOR_TOTAL_ORGAO>
    <MES_REF>${validacaoFolha.mesReferencia}</MES_REF>
    <c:forEach items="${validacaoFolha.gruposFamiliares}" var="grupoFamiliar">
    <GRUPO_FAMILIAR>
        <VAL_BASE_CALCULO><fmt:formatNumber type="number" value="${grupoFamiliar.baseCalculo}" pattern="0.00"/></VAL_BASE_CALCULO>
        <NUM_ASSOCIADO_RESP>${grupoFamiliar.numAssociadoResponsavel}</NUM_ASSOCIADO_RESP>
        <NUM_CPF>${grupoFamiliar.cpf}</NUM_CPF>
        <IND_APOSENTADO>N</IND_APOSENTADO>
        <c:forEach items="${grupoFamiliar.associados}" var="associado">
        <ASSOCIADO>
            <NUM_ASSOCIADO>${associado.numAssociado}</NUM_ASSOCIADO>
            <GRAU_DEPENDENCIA>${associado.grauDependencia}</GRAU_DEPENDENCIA>
            <c:forEach items="${associado.pagamentos}" var="pagamento">
            <PAGAMENTO>
                <TIPO>${pagamento.tipoPagamento.codigo}</TIPO>
                <VALOR><fmt:formatNumber type="number" value="${pagamento.valor}" pattern="0.00"/></VALOR>
            </PAGAMENTO>
                </c:forEach>
        </ASSOCIADO>
        </c:forEach>
    </GRUPO_FAMILIAR>
    </c:forEach>
</FOLHA>
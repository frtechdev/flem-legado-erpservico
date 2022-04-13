<%@page contentType="text/html" errorPage="/erro.jsp"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://flem.org.br/acesso-tag" prefix="acesso"%>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
        <link rel="stylesheet" type="text/css" href="css/800px.css" />
        <link href="css/displaytag.css" rel="stylesheet" type="text/css" />
   
        <title><fmt:message key="aplicacao.nome" /> versão: <fmt:message key="aplicacao.versao" /></title>
    </head>
    <body>
        <div id="wrap">
            <jsp:include flush="false" page="/inc/header.jsp" />
            <jsp:include flush="false" page="/inc/sidebar.jsp" />
            <div id="content">
                <h2>Funcionario: </h2>
                <html:form action="/Conta.do" onsubmit="return validar(this);" >
                    <html:hidden property="func.id"/>
                    <table>
                        <tr>
                            <td>Nome Completo</td>
                            <td><html:text property="func.nomeCompleto"  size="100" maxlength="100" disabled="true" /> </td>
                        </tr>
                        <tr>
                            <td>Nome reduzido</td>
                            <td><html:text property="func.nomeReduzido"   size="100" maxlength="100" disabled="true"/> </td>
                        </tr>
                        <tr>
                            <td>CPF</td>
                            <td><html:text property="func.cpf"   size="15" maxlength="100" disabled="true"/> </td>
                        </tr>
                        <tr>
                            <td>PIS</td>
                            <td><html:text property="func.pis"  size="100" maxlength="100" disabled="true"/>  </td>
                        </tr>
                        <tr>
                            <td>Orgão Emissor Documento</td>
                            <td><html:text property="func.orgaoEmissorDocumento" size="6" maxlength="2" disabled="true"/> </td>
                        </tr>
                        
                        <tr>
                            <td>UF</td>
                            <td><html:text property="func.ufNascimento" size="4" maxlength="6" disabled="true"/> </td>
                        </tr>
                        
                        <tr>
                            <td>Estado Civil</td>
                            <td><html:text property="func.estadocivil" size="3" maxlength="1" disabled="true"/> </td>
                        </tr>
                        
                        
                        
                        <tr>
                            <td>Data de Nascimento</td>
                            <td><html:text property="func.dataNascimento"   size="30" maxlength="10" disabled="true"/> </td>
                        </tr>
                        <tr>
                            <td>Local de nascimento</td>
                            <td><html:text property="func.localNascimento"   size="60" maxlength="60" disabled="true"/> </td>
                        </tr>
                        <tr>
                            <td>Nome do Pai</td>
                            <td><html:text property="func.nomePai"   size="60" maxlength="60" disabled="true"/> </td>
                        </tr>
                        <tr>
                            <td>Nome da Mãe</td>
                            <td><html:text property="func.nomeMae"   size="60" maxlength="60" disabled="true"/> </td>
                        </tr>
                        <tr>
                            <td>Documento </td>
                            <td><html:text property="func.documento"   size="10" maxlength="10" disabled="true"/> </td>
                        </tr>
                        
                        <tr>
                            <td>Data Emissão do Documento</td>
                            <td><html:text property="func.dataEmissaoDocumento"   size="30" maxlength="10" disabled="true"/> </td>
                        </tr>
                        <tr>
                            <td>Endereço</td>
                            <td><html:text property="func.endereco"   size="60" maxlength="60" disabled="true"/> </td>
                        </tr>
                        <tr>
                            <td>Numero</td>
                            <td><html:text property="func.numeroEndereco"   size="20" maxlength="20" disabled="true"/> </td>
                        </tr>
                        <tr>
                            <td>Bairro</td>
                            <td><html:text property="func.bairro"   size="60" maxlength="60" disabled="true"/> </td>
                        </tr>
                        <tr>
                            <td>Cidade</td>
                            <td><html:text property="func.cidade"   size="60" maxlength="60" disabled="true"/> </td>
                        </tr>
                        <tr>
                            <td>Cep</td>
                            <td><html:text property="func.cep"   size="8" maxlength="8" disabled="true"/> </td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td><html:text property="func.email"   size="50" maxlength="50" disabled="true"/> </td>
                        </tr>
                        
                        
                        
                        <tr>
                            <td>Código do banco</td>
                            <td><html:text property="func.codBanco"  styleId="func.nomeCompleto" size="3" maxlength="3" /> </td>
                        </tr>
                        <tr>
                            <td>Agência</td>
                            <td><html:text property="func.agencia"  styleId="func.nomeCompleto" size="8" maxlength="5" /> </td>
                        </tr>
                        <tr>
                            <td>Operação</td>
                            <td><html:text property="func.op"  styleId="func.nomeCompleto" size="3" maxlength="3" /> </td>
                        </tr>
                        <tr>
                            <td>Conta</td>
                            <td><html:text property="func.conta"  styleId="func.nomeCompleto" size="8" maxlength="8"  /> </td>
                        </tr>
                        <tr>
                            <td>Dígito</td>
                            <td><html:text property="func.digito"  styleId="func.nomeCompleto" size="8" maxlength="3"  /> </td>
                        </tr>

                        <tr>
                            <td colspan="2" align="center">
                                <acesso:verificaPermissao funcionalidade="mtb_proj">
                                    <html:submit   property="metodo" value="alterar" styleClass="botao" /> &nbsp;
                                </acesso:verificaPermissao>
                                <html:button property="" value="voltar" onclick="javascript: history.go(-1);" styleClass="botao" />
                            </td>
                        </tr>
                    </table>
                </html:form>
            </div> 
            <jsp:include flush="false" page="/inc/footer.jsp" />
        </div>
    </body>
</html>

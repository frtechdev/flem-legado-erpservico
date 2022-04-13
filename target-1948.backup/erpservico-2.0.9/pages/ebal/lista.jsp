<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://flem.org.br/autentica-tag" prefix="acesso"%>
<%@taglib uri="http://flem.org.br/mensagem-tag" prefix="mensagem"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>


<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="css/800px.css" />
        <link href="<%=request.getContextPath()%>/css/displaytag.css" rel="stylesheet" type="text/css" />
        <title><fmt:message key="aplicacao.nome" /></title>

<script>                
  function confirmaExclusao(aURL){     
       if(confirm("Deseja realmente excluir o registro?")){
          location.href = aURL;
       }          
    }        
</script>        
        
    </head>
    <body>
        <acesso:autentica sistema="erpserv" />
        <div id="wrap">
            <jsp:include flush="false" page="/inc/header.jsp" />
            <jsp:include flush="false" page="/inc/sidebar.jsp" />
            <div id="content">
                <h3>Ebal </h3>
                <html:form action="/Ebal.do?metodo=gravarArquivoCredicesta" enctype="multipart/form-data">
                    <ul>
                        <table>
                            <tr>
                                <td>
                                    <li>Arquivo Credicesta:                                
                                        <html:file styleId="arquivoCredicesta" property="arquivoCredicesta" />
                                &nbsp;</li>
                            </td>    
                                
                                <td><html:submit value="Importar" /></td>
                                
                            </tr>
                            
                            <tr>
                                <td>&nbsp;</td>      
                                <td>&nbsp;</td> 
                            </tr>                        
                            
                            <tr>
                                <td>
                                    <li>Arquivo de Cadastros e Limites:&nbsp;</li>
                            </td>      
                                <td>
                                    <input type="button" value="Exportar" onclick="location=href='./Ebal.do?metodo=gerarArquivoCadastrosLimites'">
                                </td>
                                
                            </tr>
                            
                        </table>               
                    </ul>
                    
                </html:form>
              
                
                <!--display:table id="arquivo" name="lista" defaultsort="1" sort="list" export="false" excludedParams="metodo" requestURI="./Ebal.do" pagesize="30" class="tabelaDisplay"-->
                <display:table id="arquivo" name="lista" sort="list" export="false" excludedParams="metodo" requestURI="./Ebal.do" pagesize="30" class="tabelaDisplay">                                        
                    <display:column value="${arquivo.nome}" title="Arquivo credicesta" sortable="true"/>
                    <display:column value="${arquivo.dataReferencia}" title="Data de Referência" decorator="br.org.flem.sac.web.displaytag.DateWrapper" sortable="true"/>
                    <display:column value="${arquivo.dataGravacao}" title="Data de Gravação" decorator="br.org.flem.sac.web.displaytag.DateWrapper" sortable="true"/>
                    <display:column title="" href="./Ebal.do?metodo=gerarArquivoBatch" paramId="idArquivoCredicesta" paramProperty="id" ><img align="middle" src="img/exec.png" width="22" height="22" border="0" alt="Gerar arquivo batch"/></display:column>                                        
                    <display:column title="" href="./Ebal.do?metodo=gerarArquivoRetornoConciliacao" paramId="idArquivoCredicesta" paramProperty="id" ><img align="middle" src="img/exec.png" width="22" height="22" border="0" alt="Gerar arquivo de retorno e conciliação"/></display:column>                    
                    <display:column>
                        
                   <a href="#" onclick="javascript:
                   confirmaExclusao('./Ebal.do?metodo=excluirArquivoCredicesta&idArquivoCredicesta=${arquivo.id}')">                  
                        <img align="middle" src="img/delete.png" width="22" height="22" border="0" alt="Excluir Arquivo"/>
                   </a>                        

                    </display:column>                    
                </display:table>                
                
                <br>
            </div>
            
            <jsp:include flush="false" page="/inc/footer.jsp" />
            <mensagem:alert/> 
            <mensagem:alert escopo="session" /> 
        </div>
    </body>
</html>

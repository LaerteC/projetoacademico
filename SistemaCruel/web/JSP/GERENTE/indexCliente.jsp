<%-- 
    Document   : indexCliente
    Created on : 30/11/2021, 13:44:17
    Author     : Gustavo
--%>
<%@page isELIgnored="false"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <title>Clientes</title>
    </head>
    <body class="navbar container">
        
        <c:if test="${empty sessionScope.usuario}">
            <c:set var="msg" value="Usuário deve se autenticar para acessar o sistema." />          
            <jsp:forward page="/jsp/index.jsp" />
        </c:if>
        
          <nav class="navbar container" style="background-color: #054F77;">
              <a 
               style="background-color: #054F77; width:150px;"
               class="btn btn-primary btn-xs"
               href="${pageContext.request.contextPath}/GerenteServlet?action=show" 
               role="button"
                <h1>Sitema Cruel</h1>
              </a>
                <ul class="nav">
                    <li class="nav-item">
                      <a style="background-color: #054F77;" class="btn btn-primary btn-xs" href="${pageContext.request.contextPath}/LogoutServlet">
                          Sair
                      </a>
                    </li>
                </ul>
          </nav>
            <br>
            <br>
            <br>
        <h1>Lista de Clientes</h1>
            <br>   
            <table class="table">
                    <thead class="thead-dark">
                      <tr>
                        <th scope="col">Nome</th>
                        <th scope="col">CPF</th>
                        <th scope="col">Função</th>
                        <th scope="col">Ações</th>
                      </tr>
                    </thead>    
                <c:forEach var="cliente" items="${clientes}">
                    <tr>                        
                        <td>${cliente.nome}</td>
                        <td>${cliente.cpf}</td>
                        <td>${cliente.tipo}</td>
                        <td>
                            <a 
                                style="background-color:#027700;"
                                class="btn btn-primary btn-custom" 
                                href="${pageContext.request.contextPath}/GerenteServlet?action=formUpdateCliente&cpf=${cliente.cpf}&tipo=${cliente.tipo}" 
                                role="button"
                            >
                            Edit
                            </a>
                            <button
                                style="background-color:red;"
                                class="btn btn-primary btn-custom " 
                                value="Delete"
                                onclick="confirmacao(${cliente.cpf},${cliente.tipo})"
                            >
                             Desativar
                            </button>
                            <a 
                                style="background-color:#027700;"
                                class="btn btn-primary btn-custom" 
                                href="${pageContext.request.contextPath}/GerenteServlet?action=showAtendente&cpf=${cliente.cpf}&tipo=${cliente.tipo}" 
                                role="button"
                            >
                            Ver
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
                        
          <br>
          <br>
          
        <div class="row">    
          <a 
                role="button" 
                class="btn btn-primary" 
                style="background-color:#027700;" 
                href="${pageContext.request.contextPath}/GerenteServlet?action=formNewCliente&tipo=Servidor"
            >
                Adicionar Servidor
            </a>
                        
            <br>
            <br>
            
            <a 
                role="button" 
                class="btn btn-primary" 
                style="background-color:#027700;" 
                href="${pageContext.request.contextPath}/GerenteServlet?action=formNewCliente&tipo=Externo"
            >
                Adicionar Externo
            </a>
                        
            <br>
            <br>
            
            <a 
                role="button" 
                class="btn btn-primary" 
                style="background-color:#027700;" 
                href="${pageContext.request.contextPath}/GerenteServlet?action=formNewCliente&tipo=Professor"
            >
                Adicionar Professor
            </a>
                        
            <br>
            <br>
            
            <a 
                role="button" 
                class="btn btn-primary"
                style="background-color:#027700;" 
                href="${pageContext.request.contextPath}/GerenteServlet?action=formNewCliente&tipo=Aluno"
            >
                Adicionar Aluno
            </a>                
        </div> 
            <br>
          
        <%-- JAVA SCRIPT --%>
        <script type="text/javascript">
            
            var ajax = new XMLHttpRequest();
            
            function confirmacao(cpfExcluir,tipo){
                var resposta = confirm("Deseja desativar o cadastro desse usuario?");
                if (resposta === true){                      
                ajax.open(
                    "GET",
                    "${pageContext.request.contextPath}/GerenteServlet?action=desativarCliente&cpf="+cpfExcluir+"&tipo="+tipo);
                    ajax.send();                                 
              }
            }         

        </script>
        <%-- END JAVA SCRIPT --%>            
          
    </body>
</html>

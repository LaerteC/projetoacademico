<%-- 
    Document   : indexCardapio
    Created on : 10/12/2021, 10:19:40
    Author     : laert
--%>
<%@page isELIgnored="false"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cardapio Cadastro</title>
    </head>
    <body class="container">
                 <nav class="navbar container" style="background-color: #054F77;">
              <a 
               style="background-color: #054F77; "
               class="btn btn-primary btn-xs"
               href="${pageContext.request.contextPath}/NutricionistaServlet?action=show" 
               role="button"
              <span class="navbar-text">Nutricionista: Cadastro de Cardápios </span>
              </a>
                <ul class="nav">
                    <li class="nav-item">
                      <a style="background-color: #054F77;" class="btn btn-primary btn-xs" href="${pageContext.request.contextPath}/LogoutServlet">
                          Sair
                      </a>
                    </li>
                </ul>
          </nav>
        </nav>
        

   
        
        <c:if test="${empty sessionScope.usuario}">
            <c:set var="msg" value="Usuário deve se autenticar para acessar o sistema." />          
            <jsp:forward page="/jsp/index.jsp" />
        </c:if>
        
        
         <br>
         <h2> Cardápios </h2>
            <br>   
            <table class="table">
                    <thead class="thead-dark">
                      <tr>
                        <th scope="col">Almoço</th>
                        <th scope="col">Janta</th>
                        <th scope="col">Ações</th>
                      </tr>
                    </thead>    
                <c:forEach var="tipo" items="">
                    <tr>                        
                        <td></td>
                        
                        <td>
                            <a 
                                style="background-color:#027700;"
                                class="btn btn-primary btn-custom" 
                                href="${pageContext.request.contextPath}/NutricionistaServlet?action=formUpdateCardapio&id=" 
                                role="button"
                            >
                            Editar
                            </a>
                            <button
                                style="background-color:red;"
                                class="btn btn-primary btn-custom " 
                                value="Delete"
                                onclick="confirmacao()"
                            >
                             Deletar
                            </button>
                            <a 
                                style="background-color:#027700;"
                                class="btn btn-primary btn-custom" 
                                href="${pageContext.request.contextPath}/NutricionistaServlet?action=showCardapio&id=" 
                                role="button"
                            >
                            Ver
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            
          <br>
      <div>
          
           <a 
          role="button" 
          class="btn btn-primary"
          style="background-color:#027700;" 
          href="${pageContext.request.contextPath}/NutricionistaServlet?action=formNewCardapioAlmoco"
        >
            Almoço
        </a>
        
          <a 
          role="button" 
          class="btn btn-primary"
          style="background-color:#027700;" 
          href="${pageContext.request.contextPath}/NutricionistaServlet?action=formNewCardapioJanta"
        >
            Jantar
        </a>
        <a 
            style="background-color:red;" 
            class="btn btn-primary" 
            href="${pageContext.request.contextPath}/NutricionistaServlet?action=show"
            role="button"
          >
              Voltar
        </a>
       </div>
            
     <%-- JAVA SCRIPT --%>
        <script type="text/javascript">
            
            var ajax = new XMLHttpRequest();
            
            function confirmacao(idExcluir){
                var resposta = confirm("Deletar o tipo de Ingrediente?");
                if (resposta === true){                      
                ajax.open(
                    "GET",
                    "${pageContext.request.contextPath}/NutricionistaServlet?action=deletarTipoIngrediente&id="+idExcluir);
                    ajax.send(); 
                    
                    
              }
             
              window.location.href="${pageContext.request.contextPath}/NutricionistaServlet?action=indexTipoIngrediente";
            }         
             
        </script>
        <%-- END JAVA SCRIPT --%>            
    </body>
</html>


        
</body>
</html>

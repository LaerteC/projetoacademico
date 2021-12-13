<%-- 
    Document   : atendenteForm
    Created on : 26/11/2021, 12:30:53
    Author     : Gustavo
--%>

<%@page isELIgnored="false"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <title>Atendente</title>
    </head>
    <body class="container" style="background-color:#F5F5DC;">
        
        <c:if test="${empty sessionScope.usuario}">
            <c:set var="msg" value="Usuário deve se autenticar para acessar o sistema." />          
            <jsp:forward page="/jsp/index.jsp" />
        </c:if>
        
         <nav class="navbar container" style="background-color: #054F77;">
              <a 
               style="background-color: #054F77; width:150px;"
               class="btn btn-primary btn-xs"
               href="${pageContext.request.contextPath}/${paginaLogo}" 
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
            <form action="${pageContext.request.contextPath}/AtendenteServlet?action=updateRegistro" method="Post">
                <div class="form-group">
                   <label for="exampleInputEmail1">CPF</label>
                  <input 
                      type="text"
                      style="width:600px;"
                      maxlength="14"
                      class="form-control" 
                      placeholder="CPF" 
                      name="cpf"
                      id="cpf"
                      autocomplete="off"
                      onkeyup="mascaraCPF()"
                      value="<c:out value="${registro.cpf}"/>" required="true">
                </div>
                <div class="form-group">
                   <label for="password">Valor Cobrado</label>
                  <input 
                      type="text"
                      style="width:600px;"
                      maxlength="100"
                      class="form-control" 
                      placeholder="R$0,00" 
                      name="valor"
                      id="valor"
                      autocomplete="off"
                      value="<c:out value="${registro.valorCobrado}"/>" required="true">
                </div>
                <div class="form-group">
                   <label for="exampleInputEmail1">Justificativa</label>
                   <input 
                       type="text"
                       style="width:600px;"
                       maxlength="100"
                       class="form-control" 
                       placeholder="Justificativa" 
                       name="justificativa"
                       id="justificativa"
                       autocomplete="off"
                       value="<c:out value="${registro.justificativa}"/>" required="true">
                </div>
                <div class="form-group">
                   <label for="exampleInputEmail1">Data do Registro</label>
                  <input 
                      type="date"
                      style="width:180px;"
                      class="form-control" 
                      name="data"
                      id="data"
                      value="${registro.dataHora}" required="true">
                </div>
                <button 
                  style="background-color:#027700;"
                  type="submit" 
                  class="btn btn-primary" 
                  id="cadastrar"
                >
                   Alterar
                </button>
                <a
                  style="background-color:#FF6961;" 
                  class="btn btn-primary" 
                  href="${pageContext.request.contextPath}/AtendenteServlet?data=${registro.dataHora}"
                  role="button"
                >
                    Cancelar
                </a>
              </div>
           </form>                   
        
            <br>
            <br>
            
            <script type="text/javascript">
                               
            //Gatilhos            
            $('#data').attr("disabled", true);
            
            //Funcoes
            function mascaraCPF(){
                var cpf = document.getElementById('cpf');
                if(cpf.value.length == 3 || cpf.value.length == 7){
                    cpf.value += ".";
                }else if(cpf.value.length == 11){
                    cpf.value += "-";
                }
            }

            function mascaraCEP(){
                var cep = document.getElementById('cep');
                if(cep.value.length == 5){
                    cep.value += "-";
                }
            }
            
        </script>
        
    </body>
</html>

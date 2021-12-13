<%-- 
    Document   : atendenteListar
    Created on : 26/11/2021, 12:31:41
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

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Add icon library -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" type="text/css" href="CSS/atendente.css" media="screen" />

    <title>CRUEL</title>
</head>

<body class="container">
    
        <c:if test="${empty sessionScope.usuario}">
            <c:set var="msg" value="Usuário deve se autenticar para acessar o sistema." />          
            <jsp:forward page="/jsp/index.jsp" />
        </c:if>
    
    <nav class="navbar container navbar-dark bg-dark ">
        <div class="container-fluid">
            <a 
               style="background-color: #054F77; width:150px;"
               class="btn btn-primary btn-xs"
               href="${pageContext.request.contextPath}/AtendenteServlet?action=show" 
               role="button">
                <h5>Sitema Cruel</h5>
            </a>
            <a style="background-color: #054F77;" class="btn btn-primary btn-xs" href="${pageContext.request.contextPath}/LogoutServlet">
               Sair
            </a>
        </div>
    </nav>

    <nav class="navbarMenu">
        <div class="card" style="width:100%">
            <img class="card-img-top" src="IMAGE/funcionarios.png" alt="Card image" style="width:50%" id="imgcard">
            <div class="card-body">
                <h4 class="card-title">John Doe</h4>
                <p class="card-text">Seja bem vindo(a)! Bora trabalhar</p>
            </div>
        </div>
    <form action="${pageContext.request.contextPath}/AtendenteServlet?action=formRegistro" method="Post">   
        <ul>
            <br>
            <li><div class="form-group">
                    <label for="exampleInputEmail1"><h4>Data Registro</h4></label>
                <br>
                <input
                      type="date"
                      style="width:160px;"
                      class="form-control" 
                      name="data"
                      id="data"
                      value="${dataDia}" required="true">
            </div>
            </li>
                <br>
            <li>
                <button 
                  style="background-color: green; width:160px;"
                  type="submit" 
                  class="btn btn-primary" 
                  id="cadastrar"
                >
                   Buscar Registro
                </button>
            </li>
        </ul>
    </form>
    </nav>

    <div class="container-form container-fluid">
        <div class="container mt-4">
            <h4>Inserir novo Registro</h4>
            <form>
                <label>CPF</label>
                    <input name="cpf" id="cpf" type="text" class="form-control form-control-sm mt-1" placeholder="000.000.000-00">
                <label>Valor a pagar</label>
                    <input name="valor" id="valor" style="width:100px;" type="text" class="form-control form-control-sm mt-1" placeholder="R$0,00">
                <br>
                <button style="background-color: green;" class="btn btn-primary" type="submit">Registrar</button>
            </form>
        </div>
    </div>

    <table class="table table-striped mb-4" border="1"">
        <thead>
            <tr style="text-align: center; background-color: chocolate;">
                <th scope="col">Cardápio</th>
                <th scope="col">Arroz</th>
                <th scope="col">Feijao</th>
                <th scope="col">Acompanhamento</th>
                <th scope="col">Salada</th>
                <th scope="col">Sobremesa</th>
            </tr>
        </thead>
        <tbody>
                <tr style="text-align: center;">                        
                    <td>${cardapioDia.cardapio}</td>
                    <td>${cardapioDia.arroz}</td>
                    <td>${cardapioDia.feijao}</td>
                    <td>${cardapioDia.acompanhamento}</td>
                    <td>${cardapioDia.salada}</td>
                    <td>${cardapioDia.sobremesa}</td>
                </tr>
        </tbody>
    </table>

</body>
    <script type="text/javascript">
        
        function mascaraCPF(){
            var cpf = document.getElementById('cpf');
                if(cpf.value.length == 3 || cpf.value.length == 7){
                    cpf.value += ".";
                }else if(cpf.value.length == 11){
                    cpf.value += "-";
                }
            }
        
        function mascaraValor(){
            var cep = document.getElementById('valor');
               if(cep.value.length == 0){
                    cep.value += "R$";
            }
        }
    </script>
</html>

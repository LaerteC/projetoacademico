<%-- 
    Document   : gerenteListar
    Created on : 26/11/2021, 12:29:15
    Author     : Gustavo
--%>
<%@page isELIgnored="false"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <title>index</title>
    </head>
    <body class="container">       
     
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
        <div class="row">    
          <a 
                role="button" 
                class="btn btn-primary" 
                style="background-color:#027700;" 
                href="${pageContext.request.contextPath}/GerenteServlet?action=indexCliente"
            >
                Gerenciar Clientes
            </a>
                        
            <br>
            <br>
            
            <a 
                role="button" 
                class="btn btn-primary" 
                style="background-color:#027700;" 
                href="${pageContext.request.contextPath}/GerenteServlet?action=indexAtendente"
            >
                Gerenciar Atendentes
            </a>
                        
            <br>
            <br>
            
            <a 
                role="button" 
                class="btn btn-primary" 
                style="background-color:#027700;" 
                href="${pageContext.request.contextPath}/GerenteServlet?action=indexNutricionista"
            >
                Gerenciar Nutricionista
            </a>
                        
            <br>
            <br>
            
            <a 
                role="button" 
                class="btn btn-primary"
                style="background-color:#027700;" 
                href="${pageContext.request.contextPath}/GerenteServlet?action=indexGerente"
            >
                Gerenciar Gerentes
            </a>                
        </div>                              
          <br>
        
        <h5>Dashboard Almoço</h5>
          <br>
            <table class="table">
                    <thead class="thead-dark">
                      <tr>
                        <th scope="col">Mes</th>
                        <th scope="col">Categoria</th>
                        <th scope="col">Quantidade</th>
                        <th scope="col">Valor Arrecado</th>
                      </tr>
                    </thead>    
                <c:forEach var="Almoco" items="${Almocos}">
                    <tr>
                        <td>${Almoco.nomeMes}</td>
                        <td>${Almoco.nomeCategoria}</td>                        
                        <td>${Almoco.quantidade}</td>
                        <td>${Almoco.valor}</td>                        
                    </tr>
                </c:forEach>
            </table>
          
          <br> 
          
        <h5>Dashboard Jantar</h5>
          <br>
            <table class="table">
                    <thead class="thead-dark">
                      <tr>
                        <th scope="col">Mes</th>
                        <th scope="col">Categoria</th>
                        <th scope="col">Quantidade</th>
                        <th scope="col">Valor Arrecado</th>
                      </tr>
                    </thead>    
                <c:forEach var="Jantar" items="${Jantares}">
                    <tr>
                        <td>${Jantar.nomeMes}</td>
                        <td>${Jantar.nomeCategoria}</td>                        
                        <td>${Jantar.quantidade}</td>
                        <td>${Jantar.valor}</td>                        
                    </tr>
                </c:forEach>
            </table>
          
          <br>
          
        <h5>Relatórios</h5>
           <br>
          
        <div class="row" style="text-align:left">  
          
            <form action="${pageContext.request.contextPath}/RelatorioServlet?tipoRelatorio=RelatorioUm" method="Post">
              
                <div class="form-group">
                  <label>Selecione o mês para o relatório</label>
                   <select 
                       name="mes" 
                       id="inputProduto" 
                       class="form-control" 
                       value="${mesAtual}"
                       >
                       <c:forEach var="mes" items="${meses}">
                           <option value="${mes}">
                               <c:out value="${mes}" />
                           </option>
                       </c:forEach>    
                   </select>
                </div>
                
              <button 
                style="background-color:#027700;"
                type="submit" 
                class="btn btn-primary" 
                id="cadastrar"
              >
               Gerar relatório mensal
              </button>
            </form>
          
            <form action="${pageContext.request.contextPath}/RelatorioServlet?tipoRelatorio=RelatorioDois" method="Post">
                <div class="container">
                  <label>Selecione o ano</label>
                   <select 
                       name="ano" 
                       id="inputAno" 
                       class="form-control" 
                       value="${anoAtual }"
                       >
                       <c:forEach var="ano" items="${anos}">
                           <option value="${ano}">
                               <c:out value="${ano}" />
                           </option>
                       </c:forEach>    
                   </select>
                       <br>
              <button 
                style="background-color:#027700;"
                type="submit" 
                class="btn btn-primary" 
                id="cadastrar"
              >
               Gerar relatório anual
              </button>
             </div>          
            </form>
                       
            <form action="${pageContext.request.contextPath}/RelatorioServlet?tipoRelatorio=RelatorioTres" method="Post">
                  <div class="container">   
                  <label for="exampleInputEmail1">Data Nascimento</label>
                  <br>
                  <input 
                      type="date"
                      style="width:160px;"
                      class="form-control" 
                      name="data1"
                      id="data1"
                      required="true">

                   <label for="exampleInputEmail1">Data Nascimento</label>
                   <br>
                  <input 
                      type="date"
                      style="width:160px;"
                      class="form-control" 
                      name="data2"
                      id="data2"
                      required="true">                
                <label>Selecione o Usuario</label>
                   <select 
                       name="usuario" 
                       id="usuario" 
                       class="form-control"
                       style="width:180px;"
                       >
                       <c:forEach var="usuario" items="${usuarios}">
                           <option value="${usuario.cpf}">
                               ${usuario.nome}
                           </option>
                       </c:forEach>    
                   </select>
                <br>
              <button 
                style="background-color:#027700;"
                type="submit" 
                class="btn btn-primary" 
                id="cadastrar"
              >
               Gerar relatório Usuário
              </button>
              </div>
            </form>           
            
        </div> 
         
            <br>
            <br>
           
          <br>           
    </body>
</html>

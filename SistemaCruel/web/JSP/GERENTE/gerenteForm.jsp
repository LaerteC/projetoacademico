<%-- 
    Document   : gerenteForm
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
        <title>Gerente</title>
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
            <form action="${pageContext.request.contextPath}/${empty gerente.nome ?"GerenteServlet?action=saveGerente":"ClientesServlet?action=updateGerente"}" method="Post">
                <div class="form-group">
                   <label for="exampleInputEmail1">Nome</label>
                  <input 
                      type="text"
                      style="width:600px;"
                      maxlength="100"
                      class="form-control" 
                      placeholder="Name" 
                      name="nome"
                      id="nome"
                      autocomplete="off"
                      value="<c:out value="${gerente.nome}"/>" required="true">
                </div>
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
                      value="<c:out value="${gerente.cpf}"/>" required="true">
                </div>
                <div class="form-group">
                   <label for="exampleInputEmail1">Email</label>
                  <input 
                      type="text"
                      style="width:600px;"
                      maxlength="100"
                      class="form-control"
                      placeholder="Email"
                      name="email"
                      id="email"
                      autocomplete="off"
                      value="<c:out value="${gerente.email}"/>" required="true">
                </div>
                <div class="form-group">
                   <label for="password">Senha</label>
                  <input 
                      type="text"
                      style="width:600px;"
                      maxlength="100"
                      class="form-control" 
                      placeholder="Senha" 
                      name="senha"
                      id="senha"
                      autocomplete="off"
                      value="<c:out value="${gerente.senha}"/>" required="true">
                </div>
                <div class="form-group">
                   <label for="exampleInputEmail1">Rua</label>
                   <input 
                       type="text"
                       style="width:600px;"
                       maxlength="100"
                       class="form-control" 
                       placeholder="Rua" 
                       name="rua"
                       id="rua"
                       autocomplete="off"
                       value="<c:out value="${gerente.rua}"/>" required="true">
                </div>
                <div class="form-group">
                   <label for="exampleInputEmail1">Cep</label>
                  <input 
                      type="text"
                      style="width:300px;"
                      maxlength="9"
                      class="form-control" 
                      placeholder="Cep" 
                      name="cep"
                      id="cep"
                      onkeyup="mascaraCEP()"
                      autocomplete="off"
                      value="<c:out value="${gerente.cep}"/>" required="true">
                </div>
                <div class="form-group">
                  <label for="exampleFormControlSelect1">Estado</label>
                  <select
                      name="estado"
                      class="form-control" 
                      id="estado"
                      style="width:300px;"
                      >
                    <c:forEach items="${estados}" var="estado" >                       
                        <option value="${estado.idEstado}" ${(estado.idEstado == cidade.idEstado) ? 'selected' : ''}>
                            ${estado.nomeEstado}
                        </option>
                    </c:forEach>
                    <option ${empty gerente.nome ? 'selected' : ''}>Selecione um Estado</option>
                  </select>
                </div>
                <div class="form-group">
                   <label for="exampleInputEmail1">Cidade</label>
                    <select
                      name="cidade"
                      class="form-control" 
                      id="cidade"
                      style="width:300px;"
                      >
                    <option ${empty gerente.idCidade ? 'selected' : ''}>Selecione uma Cidade</option>
                  </select>
                </div>
                <div class="form-group">
                   <label for="exampleInputEmail1">Data Nascimento</label>
                  <input 
                      type="date"
                      style="width:180px;"
                      class="form-control" 
                      name="data"
                      id="data"
                      value="${gerente.dataNascimento}" required="true">
                </div>
                <button 
                  style="background-color:#027700;"
                  type="submit" 
                  class="btn btn-primary" 
                  id="cadastrar"
                >
                   ${(empty gerente) ? "Salvar" : "Alterar" }
                </button>
                <a
                  style="background-color:#FF6961;" 
                  class="btn btn-primary" 
                  href="${pageContext.request.contextPath}/GerenteServlet?action=indexGerente"
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
            $("#estado").change(function(){
                getCidades();
                });

            if(!(${gerente.id} === null)){
               getCidades(); 
            }

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

            function getCidades(){
                var estadoId = $("#estado").val();
                var selecionado = ${gerente.idCidade};
                var url = "AJAXCidadeServlet";
                $.ajax({
                        url : url, // URL da sua Servlet
                        data : {
                            estadoId : estadoId
                        }, // Parâmetro passado para a Servlet
                        dataType : 'json',
                        success : function(data){
                            $("#cidade").empty();
                            $.each(data, function(i, obj) {
                                $("#cidade").append('<option value=' + obj.idCidade + ' >' + obj.nomeCidade + '</option>');
                            });
                        },
                        error : function(request, textStatus, errorThrown) {
                            alert(request.status + ', Error: ' + request.statusText);
                             // Erro
                        }
                    });
                    
            if(${bloqueado.equals("verdadeiro")}){
              $('#nome').attr("disabled", true);
              $('#cpf').attr("disabled", true);
              $('#email').attr("disabled", true);
              $('#rua').attr("disabled", true);
              $('#senha').attr("disabled", true);
              $('#cep').attr("disabled", true);
              $('#cidade').attr("disabled", true);
              $('#estado').attr("disabled", true);
              $('#data').attr("disabled", true);
            }
                    
            }
            
            if(${bloqueado.equals("editar")}){
              $('#cpf').attr("disabled", true);             
              $('#cadastrar').attr("disabled",true);
            }
            
        </script>
        
    </body>
</html>

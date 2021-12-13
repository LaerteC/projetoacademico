<%-- 
    Document   : TipoingredienteForm
    Created on : 06/12/2021, 01:52:43
    Author     : laert
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
        <title> Cardápio </title>
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
                <h1>UFPR Cruel</h1>
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
        <c:if test="${!(empty msg)}">
            <font color=red>${msg}</font>
        </c:if>
        <br>
        <br>
        <form action="${pageContext.request.contextPath}/${redirecionarFormulario}" method="Post">
            <div class="form-group">
                <label for="exampleFormControlSelect1">Arroz </label>
                <select
                    name="arroz"
                    class="form-control" 
                    id="arroz"
                    style="width:300px;"
                    >
                    <c:forEach items="${ingredientes}" var="tipo" >                       
                        <option value="${tipo.idIngrediente}" ${(tipo.idTipoIngrediente == 1) ? 'selected' : 'hidden'}>
                            ${tipo.nomeIngrediente}
                        </option>
                    </c:forEach>
                    <option ${empty ingrediente.idTipoIngrediente ? 'selected' : ''}>Selecione o tipo de Ingrediente</option>
                </select>
            </div>

            <div class="form-group">
                <label for="exampleInputEmail1"> Quantidade de Arroz</label>
                <input 
                    type="text"
                    style="width:600px;"
                    maxlength="100"
                    class="form-control" 
                    placeholder="" 
                    name="qtdArroz"
                    id="qtdArroz"
                    autocomplete="off"
                    value="<c:out value="${almocar.qtdeArroz}"/>" required="true">
            </div>

            <div class="form-group">
                <label for="exampleFormControlSelect1">Feijão </label>
                <select
                    name="feijao"
                    class="form-control" 
                    id="feijao"
                    style="width:300px;"
                    >
                    <c:forEach items="${ingredientes}" var="tipo" >                       
                        <option value="${tipo.idIngrediente}" ${(tipo.idTipoIngrediente == 2) ? 'selected' : 'hidden'}>
                            ${tipo.nomeIngrediente}
                        </option>
                    </c:forEach>
                    <option ${empty ingrediente.idTipoIngrediente ? 'selected' : ''}>Selecione o tipo de Ingrediente</option>
                </select>
            </div>

            <div class="form-group">
                <label for="exampleInputEmail1"> Quantidade de Feijão</label>
                <input 
                    type="text"
                    style="width:600px;"
                    maxlength="100"
                    class="form-control" 
                    placeholder="" 
                    name="qtdfeijao"
                    id="qtdfeijao"
                    autocomplete="off"
                    value="<c:out value="${almocar.qtdeFeijao}"/>" required="true">
            </div>



            <div class="form-group">
                <label for="exampleFormControlSelect1">Acompanhamento </label>
                <select
                    name="acompanhamento"
                    class="form-control" 
                    id="acompanhamento"
                    style="width:300px;"
                    >
                    <c:forEach items="${ingredientes}" var="tipo" >                       
                        <option value="${tipo.idIngrediente}" ${(tipo.idTipoIngrediente == 3) ? 'selected' : 'hidden'}>
                            ${tipo.nomeIngrediente}
                        </option>
                    </c:forEach>
                    <option ${empty ingrediente.idTipoIngrediente ? 'selected' : ''}>Selecione o tipo de Ingrediente</option>
                </select>
            </div>


            <div class="form-group">
                <label for="exampleInputEmail1"> Quantidade de Acompanhamento</label>
                <input 
                    type="text"
                    style="width:600px;"
                    maxlength="100"
                    class="form-control" 
                    placeholder="" 
                    name="qtdacompanhamento"
                    id="qtdacompanhamento"
                    autocomplete="off"
                    value="<c:out value="${almocar.qtdeAcompanhamento}"/>" required="true">
            </div>


            <div class="form-group">
                <label for="exampleFormControlSelect1">Salada </label>
                <select
                    name="salada"
                    class="form-control" 
                    id="salada"
                    style="width:300px;"
                    >
                    <c:forEach items="${ingredientes}" var="tipo" >                       
                        <option value="${tipo.idIngrediente}" ${(tipo.idTipoIngrediente == 4) ? 'selected' : 'hidden'}>
                            ${tipo.nomeIngrediente}
                        </option>
                    </c:forEach>
                    <option ${empty ingrediente.idTipoIngrediente ? 'selected' : ''}>Selecione o tipo de Ingrediente</option>
                </select>
            </div>


            <div class="form-group">
                <label for="exampleInputEmail1"> Quantidade de Salada</label>
                <input 
                    type="text"
                    style="width:600px;"
                    maxlength="100"
                    class="form-control" 
                    placeholder="" 
                    name="qtdSalada"
                    id="qtdSalada"
                    autocomplete="off"
                    value="<c:out value="${almocar.qtdeSalada}"/>" required="true">
            </div>


            <div class="form-group">
                <label for="exampleFormControlSelect1">Sobremesa </label>
                <select
                    name="sobremesa"
                    class="form-control" 
                    id="idTipoIngrediente"
                    style="width:300px;"
                    >
                    <c:forEach items="${ingredientes}" var="tipo" >                       
                        <option value="${tipo.idIngrediente}" ${(tipo.idTipoIngrediente == 8) ? 'selected' : 'hidden'}>
                            ${tipo.nomeIngrediente}
                        </option>
                    </c:forEach>
                    <option ${empty ingrediente.idTipoIngrediente ? 'selected' : ''}>Selecione o tipo de Ingrediente</option>
                </select>
            </div>

            <div class="form-group">
                <label for="exampleInputEmail1"> Quantidade de Sobremesa</label>
                <input 
                    type="text"
                    style="width:600px;"
                    maxlength="100"
                    class="form-control" 
                    placeholder="" 
                    name="qtdSobremesa"
                    id="qtdSobremesa"
                    autocomplete="off"
                    value="<c:out value="${almocar.qtdeSalada}"/>" required="true">
            </div>



            <button 
                style=" ${(bloqueado.equals("verdadeiro")) ?  "background-color:#d3d3d3;" : " background-color:#027700;" }"
                type="submit" 
                class="btn btn-primary" 
                id="cadastrar"

                >
                ${(empty almocar.idArroz) ? "Salvar" : "Alterar" }
            </button>
            <a 
            style="background-color:red;" 
            class="btn btn-primary" 
            href="${pageContext.request.contextPath}/NutricionistaServlet?action=show"
            role="button"
          >
              Cancelar
        </a>
        </div>
    </form>   
    <br>
    <br>

    <script type="text/javascript">


        if (${bloqueado.equals("verdadeiro")}) {
            $('#nome').attr("disabled", true);
            $('#idTipoIngrediente').attr("disabled", true);
            $('#cadastrar').attr("disabled", true);

        }


    </script>

</body>
</html>

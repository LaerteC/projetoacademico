<%-- 
    Document   : indexCard
    Created on : 02/12/2021, 12:29:15
    Author     : laert
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
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>

        <!-- Add icon library -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="JSP/css/" media="screen" />
        <link rel="stylesheet" type="text/css" href="JSP/css/atendente.css" media="screen" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cardapio Cadastro</title>
    </head>
    <body class="container">       

        
        
        <c:if test="${empty sessionScope.usuario}">
            <c:set var="msg" value="Usuário deve se autenticar para acessar o sistema." />          
            <jsp:forward page="/jsp/index.jsp" />
        </c:if>

        <nav class="navbar container " style="background-color: #054F77;">
            <a 
                style="background-color: #054F77; width:150px;"
                class="btn btn-primary btn-xs"
                href="${pageContext.request.contextPath}/NutricionistaServlet?action=show" 
                role="button"
                <h1>UFPR Cruel</h1>
            </a>


            <a style="background-color: #054F77;" class="btn btn-primary btn-xs" href="${pageContext.request.contextPath}/LogoutServlet">
                Sair
            </a>


        </nav>

        <br>
        
        <div>
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark ">
            <div class="container">
                <h4 class="navbar-text">Nutricionista: Cadastro de Cardápios </h4>
            </div>
        </nav>

        <nav class="navbarMenu">
            <div class="card" style="width:100%">
                <img class="card-img-top" src="JSP/img/funcionarios.png" alt="Card image" style="width:50%" id="imgcard">
                <div class="card-body">
                    <h4 class="card-title">Nome :<c:out value="${login.login}" /></h4>
                    <p class="card-text" style="color:blueviolet"><strong> Nutricionista </strong> </p>
                </div>
            </div>
            <ul>
                <li><a href="#home">Home</a></li>
                <li><a 
                        href="${pageContext.request.contextPath}/NutricionistaServlet?action=indexIngrediente"
                        >
                        Gerenciar Ingredientes
                    </a></li>
                <li><a 

                        href="${pageContext.request.contextPath}/NutricionistaServlet?action=indexTipoIngrediente"
                        >
                        Gerenciar Tipo de Ingredientes
                    </a></li>
                <li><a 
                        href="${pageContext.request.contextPath}/NutricionistaServlet?action=indexCardapio"
                        >
                        Gerenciar Cardápios
                    </a></li>
            </ul>  

        </nav>
    </div>

        <div class="row">
           
            <div align="center">

                <table class="table table-striped" border="0">
                    <thead style="text-align:center;">
                        <tr>
                            <th scope="col">Data </th>
                            <th scope="col">Id </th>
                            <th scope="col">Almoço </th>
                            <th scope="col">Editar Almoço</th>
                            <th scope="col">Visualizar Almoço</th>
                            <th scope="col">Janta</th>
                            <th scope="col">Editar Janta</th>
                            <th scope="col">Visualizar Janta</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cardapio" items="${listaCardapio}">
                            <tr style="text-align:center;">
                                <td style="text-align"> ${cardapio.dataHora}</td>
                                <td style="text-align"> ${cardapio.idCardapio}</td>
                                <td style="text-align:center;background-color: ${empty cardapio.idAlmoco ? "red;":"green;"}"> ${empty cardapio.idAlmoco ? "null":"ok"}</td>

                                <td style="text-align:center;"><a class="btn" href="${pageContext.request.contextPath}/NutricionistaServlet?action=formNewCardapioAlmoco"><i class="fa fa-pencil-square-o"></i></a></td>
                                <td style="text-align:center;"><a class="btn" data-bs-toggle="modal" data-bs-target="#myModal"><i class="fa fa-eye"></i></a></td>
                                <td style="text-align:center;background-color: ${empty cardapio.idJanta ? "red;":"green;"}"> ${empty cardapio.idJanta ? "null":"ok"}</td>



                                <td style="text-align:center;"><a class="btn" href="${pageContext.request.contextPath}/NutricionistaServlet?action=formNewCardapioJanta"><i class="fa fa-pencil-square-o"></i></a></td>
                                <td style="text-align:center;"><button class="btn" data-bs-toggle="modal" data-bs-target="#myModal"><i class="fa fa-eye"></i></button></td>

                            </tr>
                        </c:forEach>

                    </tbody>


                </table>

                 <!-- BEGIN The Modal -->
            <div class="modal" id="myModal">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Almoço</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <div class="container mt-3">
                                <!--<h2>Striped Rows</h2>
                              <p>The .table-striped class adds zebra-stripes to a table:</p>-->
                                <!-- Table Price -->
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Domingo</th>
                                            <th>Segunda-Feira</th>
                                            <th>Terça-Feira</th>
                                            <th>Quarta-Feira</th>
                                            <th>Quinta-Feira</th>
                                            <th>Sexta-Feira</th>
                                            <th>Sabado</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <th>Costela</th>
                                            <th>Strogonoff</th>
                                            <th>Macarrão</th>
                                            <th>Feijoada</th>
                                            <th>Bife</th>
                                            <th>Frango Grelhado</th>
                                            <th>Sopa</th>
                                        </tr>
                                    </tbody>
                                </table>
                                <br><br><br>


                                <!-- Table Price -->
                                <h2>Tabela de Preço</h2>
                                <p>Acompanhe nossos preços por categorias de cliente. Sujeito a mudança sem aviso prévio.</p>
                                <table class="table">
                                    <thead>
                                        <tr class="table-active">
                                            <th>Categoria Cliente</th>
                                            <th>Preço</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <th>Aluno</th>
                                            <th>1,50</th>
                                        </tr>
                                    </tbody>
                                    <tbody>
                                        <tr>
                                            <th>Professor</th>
                                            <th>3,50</th>
                                        </tr>
                                    </tbody>
                                    <tbody>
                                        <tr>
                                            <th>Servidor</th>
                                            <th>3,30</th>
                                        </tr>
                                    </tbody>
                                    <tbody>
                                        <tr>
                                            <th>Externo</th>
                                            <th>16,90</th>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Fechar</button>
                </div>

            </div>
        </div>

        <!-- END The Modal -->

            </div>

            
        </div>

    </body>
</html>

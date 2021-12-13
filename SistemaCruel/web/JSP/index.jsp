
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina Inicial</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
    <body class="container">  




        <nav class="navbar navbar-dark bg-dark navbar-expand-lg">
            <div class="container-fluid">
                <a class="btn btn-warning" href="#" role="button">UFPR CRUEL</a>
                <!--<a class="navbar-brand">CRUEL</a>-->

                <form  class="d-flex" action="${pageContext.request.contextPath}/LoginServlet" method="Post">


                    <input type="text" class="form-control me-2" placeholder="Login" name="login">

                    <input type="password" class="form-control me-2" placeholder="Senha" name="senha">

                    <button class="btn btn-outline-success" type="submit">Entrar</button>
                    <br>
                </form>

            </div>
        </nav>
        <div class="container text-center">


            <c:if test="${!(empty msg)}">
                <font color=red>${msg}</font>
            </c:if>
            <br>

            <div class="jumbotron jumbotron-fluid">
                <div class="container">
                    <h1 class="display-4">CRUEL RESTAURANTE UNIVERSITARIO</h1>
                    <p class="lead">Cardápios do RU para Estudantes Legais da Universidade Federal do Parana.</p>
                </div>
            </div>

            <div class="row">
                <div align="center">
                    <table border="0">
                        <tr>
                            <td>
                                <div class="card" style="width: 18rem;">
                                    <img class="card-img-top" src="JSP/img/almoco.jpg" alt="Card image cap">
                                    <div class="card-body">
                                        <h5 class="card-title">Almoço</h5>
                                        <p class="card-text">Acompanhe nosso cardapio de almoço preparado para a semana.</p>
                                        <!-- Button to Open the Modal -->
                                        <button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#myModal">
                                            Ir para Cardápio
                                        </button>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="card" style="width: 18rem;">
                                    <img class="card-img-top" src="JSP/img/janta.jpg" alt="Card image cap">
                                    <div class="card-body">
                                        <h5 class="card-title">Janta</h5>
                                        <p class="card-text">Acompanhe nosso cardapio da Janta preparado para a semana.</p>
                                        <!-- Button to Open the Modal -->
                                        <button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#myModal">
                                            Ir para Cardápio
                                        </button>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

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

        <br>
        <div>            
            <h6>Em caso de problemas contactar os administradores: 
                <jsp:getProperty name="configuracao" property="emailAdmin" />
            </h6>
        </div>

    </body>
</html>

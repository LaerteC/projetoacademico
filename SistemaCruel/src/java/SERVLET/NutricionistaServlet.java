package SERVLET;

import BEANS.AlmocoBean;
import BEANS.IngredienteBean;
import BEANS.JantaBean;
import BEANS.NutricionistaBean;
import BEANS.TipoIngredienteBean;
import FACADE.NutricionistaFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gustavo
 */
@WebServlet(name = "NutricionistaServlet", urlPatterns = {"/NutricionistaServlet"})
public class NutricionistaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InstantiationException, IllegalAccessException {
        response.setContentType("text/html;charset=UTF-8");

        //Fazendo validação se o usuário está logado ou não
        if (request.getSession(false) == null) {
            String mensagem = "Usuário deve se autenticar para acessar o sistema!";
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
            request.setAttribute("msg", mensagem);
            rd.forward(request, response);
        }
        // Rotas de telas com/ sem paramentros
        RequestDispatcher rd = null;

        String urlErro = "/JSP/index.jsp";

        NutricionistaBean nutricionista = new NutricionistaBean();
        TipoIngredienteBean tipoIngrediente = new TipoIngredienteBean();
        IngredienteBean ingredienteBean = new IngredienteBean();

        java.sql.Date dataParametro = null;
        java.util.Date dt = null;
        AlmocoBean almoco = new AlmocoBean();
        JantaBean janta = new JantaBean();

        NutricionistaFacade nutricionistaFacade = new NutricionistaFacade();
        //Recebendo a action do parametro
        String action = (String) request.getParameter("action");
        String paginaParaEncaminharAExc = "";
        int id = 0;
        try {

            switch (action) {

                case "show":
                    
                     String data1 = "2021-12-01";
                     String data2 = "2022-02-25";

                    //DayOfWeek currentDia = currentDate.getDayOfWeek();
                    //int currentDay = currentDate.getMonthValue();
                    //System.out.println( nutricionistaFacade.formarData(testeData,1) +" AQUI VAI SER A PORRA DA DATAAAAAAAA !!!!!!!" );
                    // request.setAttribute("nutricionista",nutricionistaFacade.buscarNutricionista());
                     request.setAttribute("listaCardapio", nutricionistaFacade.listarCardapio());
                    RequestDispatcher d = request.getRequestDispatcher("/JSP/NUTRICIONISTA/index.jsp");
                    d.forward(request, response);
                    break;

                /*  CARDAPIO CRUD*/
                case "deletarCardaio":
//                    id = Integer.parseInt(request.getParameter("id"));
//                    nutricionistaFacade.deletarTipoIngrediente(id);
//                    rd = request.getRequestDispatcher("NutricionistaServlet?action=indexTipoIngrediente");
//                    rd.forward(request, response);
                    break;

                case "saveCardapioAlmoco":
                    paginaParaEncaminharAExc = "/JSP/CARDAPIO/cardapioForm.jsp";
                    urlErro = "/JSP/CARDAPIO/cardapioForm.jsp";
                    almoco.setIdArroz(Integer.parseInt(request.getParameter("arroz")));
                    almoco.setIdFeijao(Integer.parseInt(request.getParameter("feijao")));
                    almoco.setIdAcompanhamento(Integer.parseInt(request.getParameter("acompanhamento")));
                    almoco.setIdSalada(Integer.parseInt(request.getParameter("salada")));
                    almoco.setIdSobremesa(Integer.parseInt(request.getParameter("sobremesa")));

                    almoco.setQtdeArroz(Double.parseDouble(request.getParameter("qtdArroz")));
                    almoco.setQtdeFeijao(Double.parseDouble(request.getParameter("qtdfeijao")));
                    almoco.setQtdeAcompanhamento(Double.parseDouble(request.getParameter("qtdacompanhamento")));
                    almoco.setQtdeSalada(Double.parseDouble(request.getParameter("qtdSalada")));
                    almoco.setQtdeSobremesa(Double.parseDouble(request.getParameter("qtdSobremesa")));

                    //dataParametro ="2021-12-11";
                    dataParametro = java.sql.Date.valueOf("2021-12-11");
                    dt = new java.util.Date(dataParametro.getTime());
                    //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    //dtf.format(LocalDateTime.now());

                    nutricionistaFacade.inserirAlmoco(almoco, dt);
                    paginaParaEncaminharAExc = "NutricionistaServlet?action=indexCardapio";
                    rd = request.getRequestDispatcher(paginaParaEncaminharAExc);
                    rd.forward(request, response);
                    break;

                case "saveCardapioJanta":
            
                    paginaParaEncaminharAExc = "/JSP/CARDAPIO/cardapioForm.jsp";

                    urlErro = "/JSP/CARDAPIO/cardapioFormJanta.jsp";
                    janta.setIdArroz(Integer.parseInt(request.getParameter("arroz")));
                    janta.setIdFeijao(Integer.parseInt(request.getParameter("feijao")));
                    janta.setIdAcompanhamento(Integer.parseInt(request.getParameter("acompanhamento")));
                    janta.setIdSalada(Integer.parseInt(request.getParameter("salada")));
                    janta.setIdSobremesa(Integer.parseInt(request.getParameter("sobremesa")));

                    janta.setQtdeArroz(Double.parseDouble(request.getParameter("qtdArroz")));
                    janta.setQtdeFeijao(Double.parseDouble(request.getParameter("qtdfeijao")));
                    janta.setQtdeAcompanhamento(Double.parseDouble(request.getParameter("qtdacompanhamento")));
                    janta.setQtdeSalada(Double.parseDouble(request.getParameter("qtdSalada")));
                    janta.setQtdeSobremesa(Double.parseDouble(request.getParameter("qtdSobremesa")));
                    // dataParametro = java.sql.Date.valueOf(request.getParameter("data"));
                    // dt = new java.util.Date(dataParametro.getTime());
                    //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    //dtf.format(LocalDateTime.now());
                    dataParametro = java.sql.Date.valueOf("2021-12-11");
                    dt = new java.util.Date(dataParametro.getTime());
                    nutricionistaFacade.inserirJanta(janta, dt);
                    paginaParaEncaminharAExc = "NutricionistaServlet?action=indexCardapio";
                    rd = request.getRequestDispatcher(paginaParaEncaminharAExc);
                    rd.forward(request, response);
                    break;

                case "formNewCardapioAlmoco":
                    rd = request.getRequestDispatcher("/JSP/CARDAPIO/cardapioForm.jsp");

                    request.setAttribute("ingredientes", nutricionistaFacade.lista());

                    request.setAttribute("redirecionarFormulario", "NutricionistaServlet?action=saveCardapioAlmoco");

                    request.setAttribute("paginaIndex", "NutricionistaServlet?action=show");
                    request.setAttribute("paginaRetorno", "NutricionistaServlet?action=indexRefeicao");
                    request.setAttribute("bloqueado", "falso");
                    rd.forward(request, response);
                    break;

                case "formNewCardapioJanta":
                    rd = request.getRequestDispatcher("/JSP/CARDAPIO/cardapioForm.jsp");

                    request.setAttribute("ingredientes", nutricionistaFacade.lista());

                    request.setAttribute("redirecionarFormulario", "NutricionistaServlet?action=saveCardapioJanta");

                    request.setAttribute("paginaIndex", "NutricionistaServlet?action=show");
                    request.setAttribute("paginaRetorno", "NutricionistaServlet?action=indexRefeicao");
                    request.setAttribute("bloqueado", "falso");
                    rd.forward(request, response);
                    break;
                case "updateCardapio":

//                    paginaParaEncaminharAExc = "/JSP/INGREDIENTE/tipoIngredienteForm.jsp";
//                    tipoIngrediente.setIdTipoIngrediente(Integer.parseInt(request.getParameter("id")));
//                    tipoIngrediente.setNomeTipoIngrediente(request.getParameter("nome"));
//                    request.setAttribute("Tipoingredientes", nutricionistaFacade.listaTipoIngrediente());
//                    nutricionistaFacade.atualizarDadosTipoIngrediente(tipoIngrediente);
//                    paginaParaEncaminharAExc = "NutricionistaServlet?action=indexTipoIngrediente";
//                    rd = request.getRequestDispatcher(paginaParaEncaminharAExc);
//                    rd.forward(request, response);
                    break;

                case "formUpdateCardapio":

//                    id = Integer.parseInt(request.getParameter("id"));
//
//                    tipoIngrediente = nutricionistaFacade.buscarTipoIngredientePorId(id);
//
//                    rd = request.getRequestDispatcher("JSP/INGREDIENTE/tipoIngredienteForm.jsp");
//                    request.setAttribute("id", tipoIngrediente.getIdTipoIngrediente());
//                    request.setAttribute("redirecionarFormulario", "NutricionistaServlet?action=updateTipoIngrediente");
//                    request.setAttribute("Tipoingredientes", tipoIngrediente);
//                    request.setAttribute("paginaIndex", "NutricionistaServlet?action=show");
//                    request.setAttribute("paginaRetorno", "NutricionistaServlet?action=indexTipoIngrediente");
//                    request.setAttribute("bloqueado", "editar");
//                    rd.forward(request, response);
                    break;

                case "indexCardapio":
                    rd = request.getRequestDispatcher("/JSP/NUTRICIONISTA/indexCardapio.jsp");
                    request.setAttribute("paginaIndex", "NutricionistaServlet?action=show");
                    request.setAttribute("ingredientes", nutricionistaFacade.lista());
                    request.setAttribute("tipoIngrediente", nutricionistaFacade.listaTipoIngrediente());
                    // request.setAttribute("almoco",nutricionistaFacade.listarAlmoco());
                    //request.setAttribute("janta",nutricionistaFacade.listarJanta());
                    rd.forward(request, response);
                    ;
                    break;

                case "showCardapio":

//                    id = Integer.parseInt(request.getParameter("id"));
//
//                    tipoIngrediente = nutricionistaFacade.buscarTipoIngredientePorId(id);
//
//                    rd = request.getRequestDispatcher("JSP/INGREDIENTE/tipoIngredienteForm.jsp");
//                    request.setAttribute("Tipoingredientes", nutricionistaFacade.listaTipoIngrediente());
//                    request.setAttribute("id", tipoIngrediente.getIdTipoIngrediente());
//                    request.setAttribute("Tipoingredientes", tipoIngrediente);
//                    request.setAttribute("redirecionarFormulario", "NutricionistaServlet?action=updateTipoIngrediente");
//                    request.setAttribute("paginaIndex", "NutricionistaServlet?action=show");
//                    request.setAttribute("paginaRetorno", "NutricionistaServlet?action=indexTipoIngrediente");
//                    request.setAttribute("bloqueado", "verdadeiro");
//                    rd.forward(request, response);
                    break;

                case "deletarTipoIngrediente":
                    id = Integer.parseInt(request.getParameter("id"));
                    nutricionistaFacade.deletarTipoIngrediente(id);
                    rd = request.getRequestDispatcher("NutricionistaServlet?action=indexTipoIngrediente");
                    rd.forward(request, response);
                    break;
                case "saveTipoIngrediente":
                    paginaParaEncaminharAExc = "/JSP/INGREDIENTE/tipoIngredienteForm.jsp";

                    tipoIngrediente.setNomeTipoIngrediente(request.getParameter("nome"));
                    // tipoIngrediente.setIdTipoIngrediente(Integer.parseInt(request.getParameter("idTipoIngrediente")));
                    nutricionistaFacade.inserirTipoIngrediente(tipoIngrediente);
                    paginaParaEncaminharAExc = "NutricionistaServlet?action=indexTipoIngrediente";
                    rd = request.getRequestDispatcher(paginaParaEncaminharAExc);
                    rd.forward(request, response);
                    break;

                case "formNewTipoIngrediente":
                    rd = request.getRequestDispatcher("/JSP/INGREDIENTE/tipoIngredienteForm.jsp");
                    request.setAttribute("Tipoingredientes", tipoIngrediente);
                    request.setAttribute("Tipo", nutricionistaFacade.listaTipoIngrediente());
                    request.setAttribute("redirecionarFormulario", "NutricionistaServlet?action=saveTipoIngrediente");
                    request.setAttribute("paginaIndex", "NutricionistaServlet?action=show");
                    request.setAttribute("paginaRetorno", "NutricionistaServlet?action=indexTipoIngrediente");
                    request.setAttribute("bloqueado", "falso");
                    rd.forward(request, response);
                    break;

                case "updateTipoIngrediente":

                    paginaParaEncaminharAExc = "/JSP/INGREDIENTE/tipoIngredienteForm.jsp";
                    tipoIngrediente.setIdTipoIngrediente(Integer.parseInt(request.getParameter("id")));
                    tipoIngrediente.setNomeTipoIngrediente(request.getParameter("nome"));
                    request.setAttribute("Tipoingredientes", nutricionistaFacade.listaTipoIngrediente());
                    nutricionistaFacade.atualizarDadosTipoIngrediente(tipoIngrediente);
                    paginaParaEncaminharAExc = "NutricionistaServlet?action=indexTipoIngrediente";
                    rd = request.getRequestDispatcher(paginaParaEncaminharAExc);
                    rd.forward(request, response);
                    break;
                case "formUpdateTipoIngrediente":

                    id = Integer.parseInt(request.getParameter("id"));

                    tipoIngrediente = nutricionistaFacade.buscarTipoIngredientePorId(id);

                    rd = request.getRequestDispatcher("JSP/INGREDIENTE/tipoIngredienteForm.jsp");
                    request.setAttribute("id", tipoIngrediente.getIdTipoIngrediente());
                    request.setAttribute("redirecionarFormulario", "NutricionistaServlet?action=updateTipoIngrediente");
                    request.setAttribute("Tipoingredientes", tipoIngrediente);
                    request.setAttribute("paginaIndex", "NutricionistaServlet?action=show");
                    request.setAttribute("paginaRetorno", "NutricionistaServlet?action=indexTipoIngrediente");
                    request.setAttribute("bloqueado", "editar");
                    rd.forward(request, response);

                    break;
                case "indexTipoIngrediente":
                    rd = request.getRequestDispatcher("/JSP/NUTRICIONISTA/indexTipoIngrediente.jsp");
                    request.setAttribute("paginaIndex", "NutricionistaServlet?action=show");
                    request.setAttribute("Tipoingredientes", nutricionistaFacade.listaTipoIngrediente());
                    rd.forward(request, response);
                    break;

                case "deletarIngrediente":
                    id = Integer.parseInt(request.getParameter("id"));
                    nutricionistaFacade.deletarIngrediente(id);
                    rd = request.getRequestDispatcher("NutricionistaServlet?action=indexIngrediente");
                    rd.forward(request, response);
                    break;

                case "showTipoIngrediente":

                    id = Integer.parseInt(request.getParameter("id"));

                    tipoIngrediente = nutricionistaFacade.buscarTipoIngredientePorId(id);

                    rd = request.getRequestDispatcher("JSP/INGREDIENTE/tipoIngredienteForm.jsp");
                    request.setAttribute("Tipoingredientes", nutricionistaFacade.listaTipoIngrediente());
                    request.setAttribute("id", tipoIngrediente.getIdTipoIngrediente());
                    request.setAttribute("Tipoingredientes", tipoIngrediente);
                    request.setAttribute("redirecionarFormulario", "NutricionistaServlet?action=updateTipoIngrediente");
                    request.setAttribute("paginaIndex", "NutricionistaServlet?action=show");
                    request.setAttribute("paginaRetorno", "NutricionistaServlet?action=indexTipoIngrediente");
                    request.setAttribute("bloqueado", "verdadeiro");
                    rd.forward(request, response);
                    break;

                case "showIngrediente":

                  

                    id = Integer.parseInt(request.getParameter("id"));

                    ingredienteBean = nutricionistaFacade.buscarIngredientePorId(id);

                    rd = request.getRequestDispatcher("JSP/INGREDIENTE/ingredienteForm.jsp");
                    request.setAttribute("tipoIngrediente", nutricionistaFacade.listaTipoIngrediente());
                    request.setAttribute("id", ingredienteBean.getIdIngrediente());
                    request.setAttribute("ingrediente", ingredienteBean);
                    request.setAttribute("redirecionarFormulario", "NutricionistaServlet?action=updateIngrediente");
                    request.setAttribute("paginaIndex", "NutricionistaServlet?action=show");
                    request.setAttribute("paginaRetorno", "NutricionistaServlet?action=indexIngrediente");
                    request.setAttribute("bloqueado", "verdadeiro");
                    rd.forward(request, response);
                    break;

                case "formUpdateIngrediente":
                   id = Integer.parseInt(request.getParameter("id"));

                    ingredienteBean = nutricionistaFacade.buscarIngredientePorId(id);

                    rd = request.getRequestDispatcher("JSP/INGREDIENTE/ingredienteForm.jsp");
                    request.setAttribute("id", ingredienteBean.getIdIngrediente());
                    request.setAttribute("tipoIngrediente", nutricionistaFacade.listaTipoIngrediente());
                    request.setAttribute("ingrediente", ingredienteBean);
                    request.setAttribute("redirecionarFormulario", "NutricionistaServlet?action=updateIngrediente");
                    request.setAttribute("paginaIndex", "NutricionistaServlet?action=show");
                    request.setAttribute("paginaRetorno", "NutricionistaServlet?action=indexIngrediente");
                    request.setAttribute("bloqueado", "editar");
                    rd.forward(request, response);
                    break;
                    
                    
                case "saveIngrediente":
                    paginaParaEncaminharAExc = "/JSP/INGREDIENTE/ingredienteForm.jsp";

                    ingredienteBean.setNomeIngrediente(request.getParameter("nome"));
                    ingredienteBean.setIdTipoIngrediente(Integer.parseInt(request.getParameter("idTipoIngrediente")));
                    nutricionistaFacade.inserirIngrediente(ingredienteBean);

                    paginaParaEncaminharAExc = "NutricionistaServlet?action=indexIngrediente";

                    rd = request.getRequestDispatcher(paginaParaEncaminharAExc);
                    rd.forward(request, response);

                    break;
                case "indexIngrediente":
                    rd = request.getRequestDispatcher("/JSP/NUTRICIONISTA/indexIngrediente.jsp");
                    request.setAttribute("paginaIndex", "NutricionistaServlet?action=show");

                    request.setAttribute("ingredientes", nutricionistaFacade.lista());
                    rd.forward(request, response);
                    break;

                case "formNewIngrediente":
                    rd = request.getRequestDispatcher("/JSP/INGREDIENTE/ingredienteForm.jsp");
                    request.setAttribute("ingrediente", ingredienteBean);
                    request.setAttribute("tipoIngrediente", nutricionistaFacade.listaTipoIngrediente());
                    request.setAttribute("redirecionarFormulario", "NutricionistaServlet?action=saveIngrediente");
                    request.setAttribute("paginaIndex", "NutricionistaServlet?action=show");
                    request.setAttribute("paginaRetorno", "NutricionistaServlet?action=indexIngrediente");
                    request.setAttribute("bloqueado", "falso");
                    rd.forward(request, response);
                    break;
                case "updateIngrediente":
                    paginaParaEncaminharAExc = "/JSP/INGREDIENTE/ingredienteForm.jsp";
                    ingredienteBean.setIdIngrediente(Integer.parseInt(request.getParameter("id")));
                    ingredienteBean.setNomeIngrediente(request.getParameter("nome"));
                    request.setAttribute("tipoIngrediente", nutricionistaFacade.listaTipoIngrediente());
                    ingredienteBean.setIdTipoIngrediente(Integer.parseInt(request.getParameter("idTipoIngrediente")));
                    nutricionistaFacade.atualizarDadosIngrediente(ingredienteBean);
                    paginaParaEncaminharAExc = "NutricionistaServlet?action=indexIngrediente";
                    rd = request.getRequestDispatcher(paginaParaEncaminharAExc);
                    rd.forward(request, response);
                    break;
            }

        } catch (Exception ex) {
            //Tratando as exceções SQL
            String mensagem = "Ocorreu um erro: " + ex.getMessage() + " ";
            rd = request.getRequestDispatcher(urlErro);
            request.setAttribute("msg", mensagem);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (InstantiationException ex) {
            Logger.getLogger(NutricionistaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(NutricionistaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (InstantiationException ex) {
            Logger.getLogger(NutricionistaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(NutricionistaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

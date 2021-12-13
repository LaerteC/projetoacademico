/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVLET;

import BEANS.AtendenteBean;
import BEANS.RegistroBean;
import FACADE.AtendenteFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
@WebServlet(name = "AtendenteServlet", urlPatterns = {"/AtendenteServlet"})
public class AtendenteServlet extends HttpServlet {

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
        if(request.getSession(false) == null)
         {
              String mensagem = "Usuário deve se autenticar para acessar o sistema!";                
              RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
              request.setAttribute("msg", mensagem);
              rd.forward(request, response);
         }
        
        //Variaveis auxiliares
        int id = 0;
        
        //Bean
        RegistroBean registro = new RegistroBean();
        
        //Variaveis de data para fazer a conversão que vem do formulário
        java.sql.Date dataParametro;
        java.util.Date dta;
        
        //Objeto atendente
        AtendenteBean atendente = new AtendenteBean();
        
        //Facade
        AtendenteFacade atendenteFacade = new AtendenteFacade();
        
        //Recebendo a action do parametro
        String action = (String)request.getParameter("action");
        String paginaParaEncaminharAExc = "";
        
        //Dispatcher
        RequestDispatcher rd = null;
        
        //Variaveis locais
        DateTimeFormatter dtf = null;
        String dataString = null;
        SimpleDateFormat format = null;
        java.util.Date data = null;
        java.sql.Date dataSQL = null;
        int idRegistro = 0;
        
        try{
           
                switch(action){                    
                    case "show":
                    dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    dataString = dtf.format(LocalDateTime.now());
                    format = new SimpleDateFormat("dd/MM/yyyy");
                    data = null;
                    data = format.parse(dataString);
                    dataSQL = new java.sql.Date(data.getTime());
                    rd = request.getRequestDispatcher("/JSP/ATENDENTE/index.jsp");
                    request.setAttribute("cardapioDia", atendenteFacade.buscarAlmocoDoDia());
                    request.setAttribute("dataDia",dataSQL);
                    rd.forward(request, response); 
                    break;
                case "formRegistro":                    
                    dataParametro = java.sql.Date.valueOf(request.getParameter("data"));
                    java.util.Date dt = new java.util.Date(dataParametro.getTime());
                    List<RegistroBean> lista = atendenteFacade.buscarRegistrosPorData(dt);
                    java.sql.Date dataFormatada = atendenteFacade.converterDataSQL(request.getParameter("data"));
                    rd = request.getRequestDispatcher("/JSP/ATENDENTE/indexRegistro.jsp");
                    request.setAttribute("registros", lista);
                    request.setAttribute("data", dataFormatada);
                    rd.forward(request, response);
                    break;                 
                case "excluirRegistro":
                    idRegistro = Integer.parseInt(request.getParameter("id"));
                    atendenteFacade.removerRegistro(idRegistro);                    
                    break;
                case "formUpdateRegistro":
                    idRegistro = Integer.parseInt(request.getParameter("id"));
                    rd = request.getRequestDispatcher("/JSP/ATENDENTE/indexRegistro.jsp");
                    request.setAttribute("registro", atendenteFacade.buscarRegistrosPorId(idRegistro));
                    rd.forward(request, response);
                    break;
                case "updateRegistro":
                    registro.setCpf(request.getParameter("cpf"));
                    registro.setJustificativa(request.getParameter("justificativa"));
                    registro.setValorCobrado(Double.parseDouble(request.getParameter("valor")));
                    
                    //Data
                    dataParametro = java.sql.Date.valueOf(request.getParameter("data"));                    
                    dataFormatada = atendenteFacade.converterDataSQL(request.getParameter("data"));
                    
                    
                    
                    break;
                case "saveRegistro":
                    //Formatando as datas
                    dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    dataString = dtf.format(LocalDateTime.now());
                    format = new SimpleDateFormat("dd/MM/yyyy");                    
                    data = format.parse(dataString);                    
                    dataSQL = new java.sql.Date(data.getTime());
                    
                    String cpf = request.getParameter("cpf");
                    String valor = request.getParameter("valor");
                    
                    
                    
                    break;
                }
            
        }catch(Exception ex){                    
            //Tratando as exceções SQL
            String mensagem = "Ocorreu um erro: "+ ex.getMessage() +" ";
            rd = request.getRequestDispatcher("/JSP/index.jsp");
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
            Logger.getLogger(AtendenteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AtendenteServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AtendenteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AtendenteServlet.class.getName()).log(Level.SEVERE, null, ex);
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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVLET;

import BEANS.CidadeBean;
import FACADE.GerenteFacade;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AJAXCidadeServlet", urlPatterns = {"/AJAXCidadeServlet"})
public class CidadeServlet extends HttpServlet {

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
        //Pegando dados da Facade
        GerenteFacade gerenteFacade = new GerenteFacade();
        
        //Recebendo a action do parametro
        String action = (String)request.getParameter("action");
        String paginaParaEncaminharAExc = "";
        
        try{
           
                    int id = Integer.parseInt(request.getParameter("estadoId"));                    
                    //Lista
                    List<CidadeBean> lista = new ArrayList<CidadeBean>();       
        
                    // Vai no BD buscar todas as cidades deste estado, em uma lista
                    lista = gerenteFacade.retornarCidades(id);

                    // transforma o MAP em JSON
                    String json = new Gson().toJson(lista);   

                    // retorna o JSON
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);                 
                       
            
        }catch(Exception ex){                    
            //Tratando as exceções SQL
            String mensagem = "Ocorreu um erro: "+ ex.getMessage() +" ";
            RequestDispatcher rd = request.getRequestDispatcher("/JSP/index.jsp");
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
            Logger.getLogger(CidadeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CidadeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CidadeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CidadeServlet.class.getName()).log(Level.SEVERE, null, ex);
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

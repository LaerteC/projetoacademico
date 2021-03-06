/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVLET;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AlunoServlet", urlPatterns = {"/AlunoServlet"})
public class AlunoServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Fazendo validação se o usuário está logado ou não
        if(request.getSession(false) == null)
         {
              String mensagem = "Usuário deve se autenticar para acessar o sistema!";                
              RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
              request.setAttribute("msg", mensagem);
              rd.forward(request, response);
         }
        
        //Recebendo a action do parametro
        String action = (String)request.getParameter("action");
        String paginaParaEncaminharAExc = "";
        
        try{
           
            switch(action){                    
                    case "show":
                        
                        break;
                    case "formNewAluno":

                        break;
                    case "formUpdate":                       

                        break;
                    case "desativarAluno":

                        break;
                    case "update":

                        break;                   
                }
            
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
        processRequest(request, response);
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
        processRequest(request, response);
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

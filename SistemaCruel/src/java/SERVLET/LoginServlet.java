/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVLET;

import BEANS.AtendenteBean;
import BEANS.GerenteBean;
import BEANS.LoginBean;
import BEANS.NutricionistaBean;
import FACADE.AtendenteFacade;
import FACADE.GerenteFacade;
import FACADE.LoginFacade;
import FACADE.NutricionistaFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gustavo
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        
        //LoginBean
        LoginBean login = new LoginBean();
        
        //Verificar se o usuário foi validado para abrir uma sessao
        int verificaValidacaoUsuario = 0;
        
        //Variaveis responsaveis por encaminhar as mensagens e encaminhar o usuario para a sua pagina
        String mensagem = "";
        String pagina = "/JSP/index.jsp";
        
        //Variaveis para validar o usuario    
        String email = (String)request.getParameter("login");
        String senha = (String)request.getParameter("senha");
        
        try{
        //FacadesParaValidacao
        LoginFacade facade = new LoginFacade();
        
        //Fazendo as verificações
        
        //Verificando se os dados digitados pertence ao Atendente
        login = facade.validarLoginAtendente(email, senha);        
        //Verificando qual dos métodos retorna um objeto
        if(login.getLogin() != null)
        {         
            verificaValidacaoUsuario = 1;
            //Definindo o link da página para o usuário ir
            pagina = "/AtendenteServlet?action=show";
            
        }else
        {
        //Verificando se os dados digitados pertence ao Gerente
        login = facade.validarLoginGerente(email, senha);
            //Verificando se o objeto retornado é diferente de nulo
            if(login.getLogin() != null)
            {
                verificaValidacaoUsuario = 1;
                //Definindo o link da página para o usuário ir
                pagina = "/GerenteServlet?action=show";
            }else
            {
                //Verificando se os dados digitados pertence ao Nutricionista
                login = facade.validarLoginNutricionista(email, senha);
                    //Verificando se o objeto retornado é diferente de nulo
                    if(login.getLogin() != null)
                    {
                        verificaValidacaoUsuario = 1;
                        //Definindo o link da página para o usuário ir
                        pagina = "/NutricionistaServlet?action=show";
                        request.getSession().setAttribute("login", login);
                    }
            }
        
        }
        //Se ele passou por alguma das validações acima ele retorna 1 e inicia uma sessão
        if(verificaValidacaoUsuario == 1){
            //Iniciando sessão
            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", login);
        }else{
            mensagem = "Não foi possível realizar o login, por gentileza verifique os dados inseridos.";
        }

            //Encaminhando a tentativa de login para o destino de acordo com as validações
            RequestDispatcher rd = request.getRequestDispatcher(pagina);
            request.setAttribute("msg", mensagem);
            rd.forward(request, response);
        
        }catch(Exception ex){
            RequestDispatcher rd = request.getRequestDispatcher(pagina);
            request.setAttribute("msg", ex.getMessage());
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
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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

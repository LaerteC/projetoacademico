/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVLET;

import DAO.ConnectionFactory;
import FACADE.GerenteFacade;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Gustavo
 */
@WebServlet(name = "RelatorioServlet", urlPatterns = {"/RelatorioServlet"})
public class RelatorioServlet extends HttpServlet {

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
            throws ServletException, IOException, ParseException, InstantiationException, IllegalAccessException {
    String tipoRelatorio = request.getParameter("tipoRelatorio");
        
    try{
            //Facade
            GerenteFacade facade = new GerenteFacade();
        
            ConnectionFactory con = new ConnectionFactory();
            
            String jasper = "";
            
            String host = "http://" + request.getServerName() + ":" + request.getServerPort();
            
            HashMap params = new HashMap();
            
            if(tipoRelatorio.equalsIgnoreCase("RelatorioUm")){
                
                int numeroDoMes = 0;
                
                String mes = request.getParameter("mes");
                
                numeroDoMes = facade.retornaMes(mes);
                
                jasper = request.getContextPath() + "/RELATORIO/RelatorioUm.jasper";
                
                params.put("Inteiro1", numeroDoMes);                
                
            }else if(tipoRelatorio.equalsIgnoreCase("RelatorioDois")){
                int ano = 0;
                
                ano = Integer.parseInt(request.getParameter("ano"));
                
                jasper = request.getContextPath() + "/RELATORIO/RelatorioDois.jasper";
                
                params.put("Ano", ano);
                
            }else if(tipoRelatorio.equalsIgnoreCase("RelatorioTres")){
                
                //Trabalhando com as datas
                java.sql.Date dataParam1 = java.sql.Date.valueOf(request.getParameter("data1"));
                java.sql.Date dataParam2 = java.sql.Date.valueOf(request.getParameter("data2"));
                java.util.Date data1 = new java.util.Date(dataParam1.getTime());
                java.util.Date data2 = new java.util.Date(dataParam2.getTime());

                //
                String cpf = request.getParameter("usuario");
                
                //Convertendo de String para util.date
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                
                //Pegando as datas e trabalhando com elas
                /*
                java.util.Date data1 = (java.util.Date)format.parse(request.getParameter("data1"));
                java.util.Date data2 = (java.util.Date)format.parse(request.getParameter("data2"));
                */
                //Formatando as datas
                java.sql.Date dataFormatada1 = new java.sql.Date(data1.getTime());                
                java.sql.Date dataFormatada2 = new java.sql.Date(data2.getTime());        
                
                params.put("DataInicio", dataFormatada1);
                params.put("DataFinal", dataFormatada2);
                params.put("CPF", cpf);
                
                jasper = request.getContextPath() + "/RELATORIO/RelatorioTres.jasper";
                
            }
            
            URL jasperURL = new URL(host + jasper);
                        
            byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, ConnectionFactory.getConnection());
            
            if(bytes != null){
            response.setContentType("application/pdf");
            OutputStream ops = response.getOutputStream();
            
            ops.write(bytes); 
            }        
        }catch(JRException ex){
            System.out.println("Ocorreu um erro ao tentar imprimir o relatório " + ex.getMessage());
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
        } catch (ParseException ex) {
            Logger.getLogger(RelatorioServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RelatorioServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RelatorioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(RelatorioServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RelatorioServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RelatorioServlet.class.getName()).log(Level.SEVERE, null, ex);
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

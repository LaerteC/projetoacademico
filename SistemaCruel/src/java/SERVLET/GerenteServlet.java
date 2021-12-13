/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVLET;

import BEANS.AlunoBean;
import BEANS.AtendenteBean;
import BEANS.CidadeBean;
import BEANS.EstadoBean;
import BEANS.ExternoBean;
import BEANS.GerenteBean;
import BEANS.NutricionistaBean;
import BEANS.ProfessorBean;
import BEANS.ServidorBean;
import FACADE.GerenteFacade;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GerenteServlet", urlPatterns = {"/GerenteServlet"})
public class GerenteServlet extends HttpServlet {

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
            throws ServletException, IOException, InstantiationException, IllegalAccessException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        
        //Data
        java.sql.Date dataParametro = null;
        
        //Instanciando as Facade
        GerenteFacade gerenteFacade = new GerenteFacade();
        
        //Fazendo validação se o usuário está logado ou não
        if(request.getSession(false) == null)
         {
              String mensagem = "Usuário deve se autenticar para acessar o sistema!";                
              RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
              request.setAttribute("msg", mensagem);
              rd.forward(request, response);
         }
        //Variaveis auxiliares
        RequestDispatcher rd = null;
        
        //Listas
        List<GerenteBean> listaGerente = new ArrayList();
        
        //Objetos Bean
        GerenteBean gerente = new GerenteBean();
        AtendenteBean atendente = new AtendenteBean();
        NutricionistaBean nutricionista = new NutricionistaBean();
        AlunoBean aluno = new AlunoBean();
        ExternoBean externo = new ExternoBean();
        ProfessorBean professor = new ProfessorBean();
        ServidorBean servidor = new ServidorBean();
        
        //Recebendo a action do parametro
        String action = (String)request.getParameter("action");
        String paginaParaEncaminharAExc = "/JSP/index.jsp";
        String paginaLogo = "";
        int id = 0;
        
        try{           
           
            switch(action){                    
                case "show":                       
                    int mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
                    String ano = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
                    
                    RequestDispatcher c = request.getRequestDispatcher("/JSP/GERENTE/index.jsp");
                    request.setAttribute("Jantares", gerenteFacade.dashBoardJantar());
                    request.setAttribute("Almocos", gerenteFacade.dashBoardAlmoco());
                    request.setAttribute("anos", gerenteFacade.listaAnos());
                    request.setAttribute("meses", gerenteFacade.listaMeses());
                    request.setAttribute("mesAtual", gerenteFacade.retornaMes(mes));
                    request.setAttribute("anoAtual", String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                    request.setAttribute("usuarios", gerenteFacade.listarTodosUsuarios());
                    request.setAttribute("mensagem", "Usuário deve se autenticar para acessar o sistema." );
                    request.setAttribute("pagina", "/JSP/index.jsp");
                    c.forward(request, response);                    
                    break;
                case "indexGerente":                    
                    RequestDispatcher d = request.getRequestDispatcher("/JSP/GERENTE/indexGerente.jsp");
                    request.setAttribute("gerentes", gerenteFacade.listarGerentesOrdenadoPorNome());
                    d.forward(request, response); 
                    break;
                case "showGerente":
                    id = Integer.parseInt(request.getParameter("id"));
                    gerente = gerenteFacade.buscarGerentePorId(id);
                    
                    RequestDispatcher j = request.getRequestDispatcher("/JSP/GERENTE/gerenteForm.jsp");
                    request.setAttribute("estados", gerenteFacade.retornaListaEstados());
                    request.setAttribute("gerente", gerente);
                    request.setAttribute("bloqueado", "verdadeiro");
                    request.setAttribute("cidade", gerenteFacade.buscarCidadePorId(gerente.getIdCidade()));
                    j.forward(request, response);
                    break;
                case "formNewGerente":
                    RequestDispatcher e = request.getRequestDispatcher("/JSP/GERENTE/gerenteForm.jsp");
                    request.setAttribute("estados", gerenteFacade.retornaListaEstados());
                    request.setAttribute("gerente", gerente);
                    request.setAttribute("bloqueado", "falso");
                    request.setAttribute("cidade", null);
                    e.forward(request, response);
                    break;
                case "formUpdateGerente":                       
                    id = Integer.parseInt(request.getParameter("id"));
                    
                    gerente = gerenteFacade.buscarGerentePorId(id);
                    
                    RequestDispatcher f = request.getRequestDispatcher("/JSP/GERENTE/gerenteForm.jsp");
                    request.setAttribute("estados", gerenteFacade.retornaListaEstados());
                    request.setAttribute("gerente", gerente);
                    request.setAttribute("bloqueado", "editar");
                    request.setAttribute("cidade", gerenteFacade.buscarCidadePorId(gerente.getIdCidade()));
                    f.forward(request, response);
                    break;
                case "desativarGerente":
                    id = Integer.parseInt(request.getParameter("id"));
                    
                    gerenteFacade.desativarCadastroGerente(id);
                    RequestDispatcher g = request.getRequestDispatcher("/JSP/GERENTE/indexGerente.jsp");
                    request.setAttribute("mensagem", "Cadastro Desativado com Sucesso!");
                    g.forward(request, response);
                    break;
                case "updateGerente":
                    paginaParaEncaminharAExc = "/JSP/GERENTE/gerenteForm.jsp";
                    dataParametro = java.sql.Date.valueOf(request.getParameter("data"));
                    java.util.Date dt = new java.util.Date(dataParametro.getTime());
                    
                    gerente.setCep(request.getParameter("cep"));
                    gerente.setCpf(request.getParameter("cpf"));
                    gerente.setDataNascimento(dt);
                    gerente.setEmail(request.getParameter("email"));
                    gerente.setEstadoCadastro((byte)1);
                    gerente.setIdCidade(Integer.parseInt(request.getParameter("cidade")));
                    gerente.setNome(request.getParameter("nome"));
                    gerente.setRua(request.getParameter("rua"));
                    gerente.setSenha(request.getParameter("senha"));
                    
                    gerenteFacade.atualizarDadosGerente(gerente);
                    RequestDispatcher h = request.getRequestDispatcher("/JSP/GERENTE/indexGerente.jsp");
                    h.forward(request, response);
                    break;
                case "saveGerente":
                    paginaParaEncaminharAExc = "/JSP/GERENTE/gerenteForm.jsp";
                                        
                    dataParametro = java.sql.Date.valueOf(request.getParameter("data"));
                    java.util.Date data = new java.util.Date(dataParametro.getTime());
                    
                    gerente.setCep(request.getParameter("cep"));
                    gerente.setCpf(request.getParameter("cpf"));
                    gerente.setDataNascimento(data);
                    gerente.setEmail(request.getParameter("email"));
                    gerente.setEstadoCadastro((byte)1);
                    gerente.setIdCidade(Integer.parseInt(request.getParameter("cidade")));
                    gerente.setNome(request.getParameter("nome"));
                    gerente.setRua(request.getParameter("rua"));
                    gerente.setSenha(request.getParameter("senha"));
                    
                    gerenteFacade.inserirGerente(gerente);                    
                    RequestDispatcher i = request.getRequestDispatcher("/JSP/GERENTE/indexGerente.jsp");
                    request.setAttribute("gerentes", gerenteFacade.listarGerentesOrdenadoPorNome());
                    request.setAttribute("mensagem", "Gerente Adicionado com Sucesso!");
                    i.forward(request, response);
                    break;
                case "indexAtendente":                    
                    rd = request.getRequestDispatcher("/JSP/GERENTE/indexAtendente.jsp");
                    request.setAttribute("paginaIndex", "GerenteServlet?action=show");
                    request.setAttribute("atendentes", gerenteFacade.listarAtendentesOrdenadoPorNome());
                    rd.forward(request, response); 
                    break;
                case "showAtendente":
                    id = Integer.parseInt(request.getParameter("id"));
                    atendente = gerenteFacade.buscarAtendentePorId(id);
                    
                    rd = request.getRequestDispatcher("/JSP/ATENDENTE/atendenteForm.jsp");
                    request.setAttribute("estados", gerenteFacade.retornaListaEstados());
                    request.setAttribute("atendente", atendente);
                    request.setAttribute("bloqueado", "verdadeiro");
                    request.setAttribute("paginaIndex", "GerenteServlet?action=show");
                    request.setAttribute("paginaRetorno","GerenteServlet?action=indexAtendente");
                    request.setAttribute("cidade", gerenteFacade.buscarCidadePorId(gerente.getIdCidade()));
                    rd.forward(request, response);
                    break;
                case "formNewAtendente":
                    rd = request.getRequestDispatcher("/JSP/ATENDENTE/atendenteForm.jsp");
                    request.setAttribute("estados", gerenteFacade.retornaListaEstados());
                    request.setAttribute("atendente", atendente);
                    request.setAttribute("redirecionarFormulario", "GerenteServlet?action=saveAtendente");
                    request.setAttribute("paginaIndex", "GerenteServlet?action=show");
                    request.setAttribute("paginaRetorno","GerenteServlet?action=indexAtendente");
                    request.setAttribute("bloqueado", "falso");
                    request.setAttribute("cidade", null);
                    rd.forward(request, response);
                    break;
                case "formUpdateAtendente":                       
                    id = Integer.parseInt(request.getParameter("id"));
                    
                    atendente = gerenteFacade.buscarAtendentePorId(id);
                    
                    rd = request.getRequestDispatcher("/JSP/ATENDENTE/atendenteForm.jsp");
                    request.setAttribute("estados", gerenteFacade.retornaListaEstados());
                    request.setAttribute("atendente", atendente);
                    request.setAttribute("redirecionarFormulario", "GerenteServlet?action=updateAtendente");
                    request.setAttribute("paginaIndex", "GerenteServlet?action=show");
                    request.setAttribute("paginaRetorno","GerenteServlet?action=indexAtendente");
                    request.setAttribute("bloqueado", "editar");
                    request.setAttribute("cidade", gerenteFacade.buscarCidadePorId(gerente.getIdCidade()));
                    rd.forward(request, response);
                    break;
                case "desativarAtendente":
                    id = Integer.parseInt(request.getParameter("id"));
                    
                    gerenteFacade.desativarCadastroAtendente(id);
                    rd = request.getRequestDispatcher("GerenteServlet?action=indexGerente");
                    rd.forward(request, response);
                    break;
                case "updateAtendente":
                    paginaParaEncaminharAExc = "/JSP/ATENDENTE/atendenteForm.jsp";
                    dataParametro = java.sql.Date.valueOf(request.getParameter("data"));
                    java.util.Date dta = new java.util.Date(dataParametro.getTime());
                    
                    atendente.setCep(request.getParameter("cep"));
                    atendente.setCpf(request.getParameter("cpf"));
                    atendente.setDataNascimento(dta);
                    atendente.setEmail(request.getParameter("email"));
                    atendente.setEstadoCadastro((byte)1);
                    atendente.setIdCidade(Integer.parseInt(request.getParameter("cidade")));
                    atendente.setNome(request.getParameter("nome"));
                    atendente.setRua(request.getParameter("rua"));
                    atendente.setSenha(request.getParameter("senha"));
                    
                    gerenteFacade.atualizarDadosAtendente(atendente);
                    paginaParaEncaminharAExc = "GerenteServlet?action=indexAtendente";
                    rd = request.getRequestDispatcher(paginaParaEncaminharAExc);
                    rd.forward(request, response);
                    break;
                case "saveAtendente":
                    paginaParaEncaminharAExc = "/JSP/ATENDENTE/atendenteForm.jsp";
                                        
                    dataParametro = java.sql.Date.valueOf(request.getParameter("data"));
                    java.util.Date dataAux = new java.util.Date(dataParametro.getTime());
                    
                    atendente.setCep(request.getParameter("cep"));
                    atendente.setCpf(request.getParameter("cpf"));
                    atendente.setDataNascimento(dataAux);
                    atendente.setEmail(request.getParameter("email"));
                    atendente.setEstadoCadastro((byte)1);
                    atendente.setIdCidade(Integer.parseInt(request.getParameter("cidade")));
                    atendente.setNome(request.getParameter("nome"));
                    atendente.setRua(request.getParameter("rua"));
                    atendente.setSenha(request.getParameter("senha"));
                    
                    gerenteFacade.inserirAtendente(atendente);
                    
                    paginaParaEncaminharAExc = "GerenteServlet?action=indexAtendente";
                    
                    rd = request.getRequestDispatcher(paginaParaEncaminharAExc);
                    rd.forward(request, response);
                    break;
                case "indexNutricionista":                    
                    rd = request.getRequestDispatcher("/JSP/GERENTE/indexNutricionista.jsp");
                    request.setAttribute("paginaIndex", "GerenteServlet?action=show");
                    request.setAttribute("nutricionistas", gerenteFacade.listarNutricionistasOrdenadoPorNome());
                    rd.forward(request, response); 
                    break;
                case "showNutricionista":
                    id = Integer.parseInt(request.getParameter("id"));
                    nutricionista = gerenteFacade.buscarNutricionistaPorId(id);
                    
                    rd = request.getRequestDispatcher("/JSP/NUTRICIONISTA/nutricionistaForm.jsp");
                    request.setAttribute("estados", gerenteFacade.retornaListaEstados());
                    request.setAttribute("nutricionista", nutricionista);
                    request.setAttribute("bloqueado", "verdadeiro");
                    request.setAttribute("paginaIndex", "GerenteServlet?action=show");
                    request.setAttribute("paginaRetorno","GerenteServlet?action=indexNutricionista");
                    request.setAttribute("cidade", gerenteFacade.buscarCidadePorId(gerente.getIdCidade()));
                    rd.forward(request, response);
                    break;
                case "formNewNutricionista":
                    rd = request.getRequestDispatcher("/JSP/NUTRICIONISTA/nutricionistaForm.jsp");
                    request.setAttribute("estados", gerenteFacade.retornaListaEstados());
                    request.setAttribute("nutricionista", nutricionista);
                    request.setAttribute("redirecionarFormulario", "GerenteServlet?action=saveNutricionista");
                    request.setAttribute("paginaIndex", "GerenteServlet?action=show");
                    request.setAttribute("paginaRetorno","GerenteServlet?action=indexNutricionista");
                    request.setAttribute("bloqueado", "falso");
                    request.setAttribute("cidade", null);
                    rd.forward(request, response);
                    break;
                case "formUpdateNutricionista":                       
                    id = Integer.parseInt(request.getParameter("id"));
                    
                    nutricionista = gerenteFacade.buscarNutricionistaPorId(id);
                    
                    rd = request.getRequestDispatcher("/JSP/NUTRICIONISTA/nutricionistaForm.jsp");
                    request.setAttribute("estados", gerenteFacade.retornaListaEstados());
                    request.setAttribute("nutricionista", nutricionista);
                    request.setAttribute("redirecionarFormulario", "GerenteServlet?action=updateNutricionista");
                    request.setAttribute("paginaIndex", "GerenteServlet?action=show");
                    request.setAttribute("paginaRetorno","GerenteServlet?action=indexNutricionista");
                    request.setAttribute("bloqueado", "editar");
                    request.setAttribute("cidade", gerenteFacade.buscarCidadePorId(gerente.getIdCidade()));
                    rd.forward(request, response);
                    break;
                case "desativarNutricionista":
                    id = Integer.parseInt(request.getParameter("id"));
                    
                    gerenteFacade.desativarCadastroNutricionista(id);
                    rd = request.getRequestDispatcher("GerenteServlet?action=indexNutricionista");
                    rd.forward(request, response);
                    break;
                case "updateNutricionista":
                    paginaParaEncaminharAExc = "/JSP/NUTRICIONISTA/nutricionistaForm.jsp";
                    dataParametro = java.sql.Date.valueOf(request.getParameter("data"));
                    java.util.Date utilData = new java.util.Date(dataParametro.getTime());
                    
                    nutricionista.setCep(request.getParameter("cep"));
                    nutricionista.setCpf(request.getParameter("cpf"));
                    nutricionista.setDataNascimento(utilData);
                    nutricionista.setEmail(request.getParameter("email"));
                    nutricionista.setEstadoCadastro((byte)1);
                    nutricionista.setIdCidade(Integer.parseInt(request.getParameter("cidade")));
                    nutricionista.setNome(request.getParameter("nome"));
                    nutricionista.setRua(request.getParameter("rua"));
                    nutricionista.setSenha(request.getParameter("senha"));
                    nutricionista.setCrm(request.getParameter("crn"));
                    
                    gerenteFacade.atualizarDadosNutricionista(nutricionista);
                    paginaParaEncaminharAExc = "GerenteServlet?action=indexNutricionista";
                    rd = request.getRequestDispatcher(paginaParaEncaminharAExc);
                    rd.forward(request, response);
                    break;
                case "saveNutricionista":
                    paginaParaEncaminharAExc = "/JSP/NUTRICIONISTA/nutricionistaForm.jsp";
                                        
                    dataParametro = java.sql.Date.valueOf(request.getParameter("data"));
                    java.util.Date dataAu = new java.util.Date(dataParametro.getTime());
                    
                    nutricionista.setCep(request.getParameter("cep"));
                    nutricionista.setCpf(request.getParameter("cpf"));
                    nutricionista.setDataNascimento(dataAu);
                    nutricionista.setEmail(request.getParameter("email"));
                    nutricionista.setEstadoCadastro((byte)1);
                    nutricionista.setIdCidade(Integer.parseInt(request.getParameter("cidade")));
                    nutricionista.setNome(request.getParameter("nome"));
                    nutricionista.setRua(request.getParameter("rua"));
                    nutricionista.setSenha(request.getParameter("senha"));
                    nutricionista.setCrm(request.getParameter("crn"));
                    
                    gerenteFacade.inserirNutricionista(nutricionista);
                    
                    paginaParaEncaminharAExc = "GerenteServlet?action=indexNutricionista";
                    
                    rd = request.getRequestDispatcher(paginaParaEncaminharAExc);
                    rd.forward(request, response);
                    break;
                case "indexCliente":
                    //Passando uma lista com todos os dados                    
                    
                    listaGerente = gerenteFacade.listarTodosUsuarios();
                    
                    rd = request.getRequestDispatcher("JSP/GERENTE/indexCliente.jsp");
                    request.setAttribute("clientes", listaGerente);
                    rd.forward(request, response);                    
                    break;
                case "formNewCliente":                    
                    String tipoForm = request.getParameter("tipo");
                    List<EstadoBean> estados = gerenteFacade.retornaListaEstados();
                    switch(tipoForm)
                    {                    
                        case "Aluno":
                        rd = request.getRequestDispatcher("JSP/ALUNO/alunoForm.jsp");
                        request.setAttribute("estados", estados);
                        request.setAttribute("redirecionarFormulario", "GerenteServlet?action=saveAluno");
                        request.setAttribute("aluno", aluno);
                        request.setAttribute("bloqueado", "falso");
                        rd.forward(request, response); 
                            break;
                        case "Professor":
                        rd = request.getRequestDispatcher("JSP/PROFESSOR/professorForm.jsp");
                        request.setAttribute("estados", estados);
                        request.setAttribute("redirecionarFormulario", "GerenteServlet?action=saveProfessor");
                        request.setAttribute("professor", professor);
                        request.setAttribute("bloqueado", "falso");
                        rd.forward(request, response); 
                            break;
                        case "Servidor":
                        rd = request.getRequestDispatcher("JSP/SERVIDOR/servidorForm.jsp");
                        request.setAttribute("redirecionarFormulario", "GerenteServlet?action=saveServidor");
                        request.setAttribute("estados", estados);
                        request.setAttribute("servidor", servidor);
                        request.setAttribute("bloqueado", "falso");
                        rd.forward(request, response);                             
                            break;
                        case "Externo":
                        rd = request.getRequestDispatcher("JSP/EXTERNO/externoForm.jsp");
                        request.setAttribute("redirecionarFormulario", "GerenteServlet?action=saveExterno");
                        request.setAttribute("estados", estados);
                        request.setAttribute("externo", externo);
                        request.setAttribute("bloqueado", "falso");
                        rd.forward(request, response);                             
                            break;                                                    
                    }
                    
                    break;
                case "formUpdateCliente":
                    //Pegando a action para renovar o cliente
                    String actionForExclude = request.getParameter("tipo");
                    //Recebendo o CPF passado quando clicado no botão editar
                    String cpfUpdate = request.getParameter("cpf");

                    //Chamando Facade para atualizar os dados                    
                    if(actionForExclude.equalsIgnoreCase("Aluno")){
                        //Aluno
                        aluno = gerenteFacade.buscarAlunoPorCPF(cpfUpdate);
                        //Encaminhando para a página indicada
                        rd = request.getRequestDispatcher("JSP/ALUNO/alunoForm.jsp");
                        request.setAttribute("estados", gerenteFacade.retornaListaEstados());
                        request.setAttribute("aluno", aluno);
                        request.setAttribute("redirecionarFormulario", "GerenteServlet?action=updateAluno");
                        request.setAttribute("paginaIndex", "GerenteServlet?action=show");
                        request.setAttribute("paginaRetorno","GerenteServlet?action=indexCliente");
                        request.setAttribute("bloqueado", "editar");
                        request.setAttribute("cidade", gerenteFacade.buscarCidadePorId(aluno.getIdCidade()));
                        rd.forward(request, response); 
                    }else if(actionForExclude.equalsIgnoreCase("Professor")){
                        //Professor
                        professor = gerenteFacade.buscarProfessorPorCPF(cpfUpdate);
                        //Encaminhando para a página indicada
                        rd = request.getRequestDispatcher("JSP/PROFESSOR/professorForm.jsp");
                        request.setAttribute("estados", gerenteFacade.retornaListaEstados());
                        request.setAttribute("professor", professor);
                        request.setAttribute("redirecionarFormulario", "GerenteServlet?action=updateProfessor");
                        request.setAttribute("paginaIndex", "GerenteServlet?action=show");
                        request.setAttribute("paginaRetorno","GerenteServlet?action=indexCliente");
                        request.setAttribute("bloqueado", "editar");
                        request.setAttribute("cidade", gerenteFacade.buscarCidadePorId(professor.getIdCidade()));
                        rd.forward(request, response); 
                    }else if(actionForExclude.equalsIgnoreCase("Externo")){
                        //Externo
                        externo = gerenteFacade.buscarExternoPorCPF(cpfUpdate);
                        //Encaminhando para a página indicada
                        rd = request.getRequestDispatcher("JSP/EXTERNO/externoForm.jsp");
                        request.setAttribute("estados", gerenteFacade.retornaListaEstados());
                        request.setAttribute("externo", externo);
                        request.setAttribute("redirecionarFormulario", "GerenteServlet?action=updateExterno");
                        request.setAttribute("paginaIndex", "GerenteServlet?action=show");
                        request.setAttribute("paginaRetorno","GerenteServlet?action=indexCliente");
                        request.setAttribute("bloqueado", "editar");
                        request.setAttribute("cidade", gerenteFacade.buscarCidadePorId(externo.getIdCidade()));
                        rd.forward(request, response); 
                    }else if(actionForExclude.equalsIgnoreCase("Servidor")){
                        //Servidor
                        servidor = gerenteFacade.buscarServidorPorCPF(cpfUpdate);
                        //Encaminhando para a página indicada
                        rd = request.getRequestDispatcher("JSP/SERVIDOR/servidorForm.jsp");
                        request.setAttribute("estados", gerenteFacade.retornaListaEstados());
                        request.setAttribute("servidor", servidor);
                        request.setAttribute("redirecionarFormulario", "GerenteServlet?action=updateServidor");
                        request.setAttribute("paginaIndex", "GerenteServlet?action=show");
                        request.setAttribute("paginaRetorno","GerenteServlet?action=indexCliente");
                        request.setAttribute("bloqueado", "editar");
                        request.setAttribute("cidade", gerenteFacade.buscarCidadePorId(servidor.getIdCidade()));
                        rd.forward(request, response); 
                    }                       
                    break;
                case "showCliente":
                    //Pegando a action para renovar o cliente
                    String acao = request.getParameter("tipo");
                    //Recebendo o CPF passado quando clicado no botão editar
                    String cpfShow = request.getParameter("cpf");

                    //Chamando Facade para atualizar os dados                    
                    if(acao.equalsIgnoreCase("Aluno")){
                        //Aluno
                        aluno = gerenteFacade.buscarAlunoPorCPF(cpfShow);
                        //Encaminhando para a página indicada
                        rd = request.getRequestDispatcher("JSP/ALUNO/alunoForm.jsp");
                        request.setAttribute("estados", gerenteFacade.retornaListaEstados());
                        request.setAttribute("aluno", aluno);
                        request.setAttribute("redirecionarFormulario", "GerenteServlet?action=updateAluno");
                        request.setAttribute("paginaIndex", "GerenteServlet?action=show");
                        request.setAttribute("paginaRetorno","GerenteServlet?action=indexCliente");
                        request.setAttribute("bloqueado", "verdadeiro");
                        request.setAttribute("cidade", gerenteFacade.buscarCidadePorId(aluno.getIdCidade()));
                        rd.forward(request, response); 
                    }else if(acao.equalsIgnoreCase("Professor")){
                        //Professor
                        professor = gerenteFacade.buscarProfessorPorCPF(cpfShow);
                        //Encaminhando para a página indicada
                        rd = request.getRequestDispatcher("JSP/PROFESSOR/professorForm.jsp");
                        request.setAttribute("estados", gerenteFacade.retornaListaEstados());
                        request.setAttribute("professor", professor);
                        request.setAttribute("redirecionarFormulario", "GerenteServlet?action=updateProfessor");
                        request.setAttribute("paginaIndex", "GerenteServlet?action=show");
                        request.setAttribute("paginaRetorno","GerenteServlet?action=indexCliente");
                        request.setAttribute("bloqueado", "verdadeiro");
                        request.setAttribute("cidade", gerenteFacade.buscarCidadePorId(professor.getIdCidade()));
                        rd.forward(request, response); 
                    }else if(acao.equalsIgnoreCase("Externo")){
                        //Externo
                        externo = gerenteFacade.buscarExternoPorCPF(cpfShow);
                        //Encaminhando para a página indicada
                        rd = request.getRequestDispatcher("JSP/EXTERNO/externoForm.jsp");
                        request.setAttribute("estados", gerenteFacade.retornaListaEstados());
                        request.setAttribute("externo", externo);
                        request.setAttribute("redirecionarFormulario", "GerenteServlet?action=updateExterno");
                        request.setAttribute("paginaIndex", "GerenteServlet?action=show");
                        request.setAttribute("paginaRetorno","GerenteServlet?action=indexCliente");
                        request.setAttribute("bloqueado", "verdadeiro");
                        request.setAttribute("cidade", gerenteFacade.buscarCidadePorId(externo.getIdCidade()));
                        rd.forward(request, response); 
                    }else if(acao.equalsIgnoreCase("Servidor")){
                        //Servidor
                        servidor = gerenteFacade.buscarServidorPorCPF(cpfShow);
                        //Encaminhando para a página indicada
                        rd = request.getRequestDispatcher("JSP/SERVIDOR/formServidor.jsp");
                        request.setAttribute("estados", gerenteFacade.retornaListaEstados());
                        request.setAttribute("servidor", servidor);
                        request.setAttribute("redirecionarFormulario", "GerenteServlet?action=updateServidor");
                        request.setAttribute("paginaIndex", "GerenteServlet?action=show");
                        request.setAttribute("paginaRetorno","GerenteServlet?action=indexCliente");
                        request.setAttribute("bloqueado", "verdadeiro");
                        request.setAttribute("cidade", gerenteFacade.buscarCidadePorId(servidor.getIdCidade()));
                        rd.forward(request, response); 
                    }                       
                    break;
                case "updateAluno":
                    //Acertando a data ingresso
                    dataParametro = java.sql.Date.valueOf(request.getParameter("anoIngresso"));
                    java.util.Date dataIngresso = new java.util.Date(dataParametro.getTime());                                      
                                       
                    //Recebendo os parametros
                    aluno.setAnoIngresso(dataIngresso);
                    aluno.setCep(request.getParameter("cep"));
                    aluno.setCpf(request.getParameter("cpf"));
                    aluno.setCurso(request.getParameter("curso"));
                    //Trabalhando com as datas
                    dataParametro = java.sql.Date.valueOf(request.getParameter("data"));
                    java.util.Date dataAluno = new java.util.Date(dataParametro.getTime()); 
                    //Continua inserindo dados
                    aluno.setDataNascimento(dataAluno);
                    aluno.setEmail(request.getParameter("email"));
                    aluno.setEstadoCadastro((byte)1);
                    aluno.setGrr(request.getParameter("grr"));
                    aluno.setIdCidade(Integer.parseInt(request.getParameter("idCidade")));
                    aluno.setNome(request.getParameter("nome"));
                    aluno.setRua(request.getParameter("rua"));
                    //Chamando Facade para atualizar os dados
                    gerenteFacade.atualizarDadosAluno(aluno);
                    //Depois de atualizado retornando para a index Cliente
                    rd = request.getRequestDispatcher("GerenteServlet?action=indexCliente");
                    rd.forward(request, response);
                    break;
                case "updateExterno":
                    //Trabalhando com as datas
                    dataParametro = java.sql.Date.valueOf(request.getParameter("data"));
                    java.util.Date dataExterno = new java.util.Date(dataParametro.getTime()); 
                    //Recebendo os parametros
                    externo.setCep(request.getParameter("cep"));
                    externo.setCpf(request.getParameter("cpf"));
                    externo.setDataNascimento(dataExterno);
                    externo.setEmail(request.getParameter("email"));
                    externo.setEstadoCadastro((byte)1);
                    externo.setIdCidade(Integer.parseInt(request.getParameter("cidade")));
                    externo.setNome(request.getParameter("nome"));
                    externo.setObservacao(request.getParameter("observacao"));
                    externo.setRua(request.getParameter("rua"));
                    //Chamando Facade para atualizar os dados                    
                    gerenteFacade.atualizarDadosExterno(externo);
                    //Redirecionando
                    rd = request.getRequestDispatcher("GerenteServlet?action=indexCliente");
                    rd.forward(request, response);                    
                    break;
                case "updateProfessor":
                    //Trabalhando com as datas
                    dataParametro = java.sql.Date.valueOf(request.getParameter("data"));
                    java.util.Date dataProfessor = new java.util.Date(dataParametro.getTime());                     
                    //Recebendo os parametros
                    professor.setAreaEstudo(request.getParameter("cep"));
                    professor.setCep(request.getParameter("cep"));
                    professor.setCpf(request.getParameter("cep"));
                    professor.setDataNascimento(dataProfessor);
                    professor.setDepartamento(request.getParameter("departamento"));
                    professor.setEmail(request.getParameter("email"));
                    professor.setEstadoCadastro((byte)1);
                    professor.setIdCidade(Integer.parseInt(request.getParameter("cidade")));
                    professor.setNome(request.getParameter("nome"));
                    professor.setRua(request.getParameter("rua"));
                    //Chamando Facade para atualizar os dados
                    gerenteFacade.atualizarDadosProfessor(professor);
                    //Redirecionando
                    rd = request.getRequestDispatcher("GerenteServlet?action=indexCliente");
                    rd.forward(request, response);                    
                    break;
                case "upadteServidor":
                    //Trabalhando com as datas
                    dataParametro = java.sql.Date.valueOf(request.getParameter("dataIngresso"));
                    java.util.Date dataIngressoServidor = new java.util.Date(dataParametro.getTime());   
                    //Recebendo os parametros
                    servidor.setCep(request.getParameter("cep"));
                    servidor.setCpf(request.getParameter("cpf"));
                    servidor.setDataIngresso(dataIngressoServidor);
                    //Trabalhando com as datas
                    dataParametro = java.sql.Date.valueOf(request.getParameter("data"));
                    java.util.Date dataServidor = new java.util.Date(dataParametro.getTime());   
                    servidor.setDataNascimento(dataServidor);
                    servidor.setEmail(request.getParameter("email"));
                    servidor.setEstadoCadastro((byte)1);
                    servidor.setIdCidade(Integer.parseInt(request.getParameter("cidade")));
                    servidor.setNome(request.getParameter("nome"));
                    servidor.setRua(request.getParameter("rua"));
                    servidor.setUnidade(request.getParameter("unidade"));
                    //Chamando Facade para atualizar os dados
                    gerenteFacade.atualizarDadosServidor(servidor);
                    //Redirecionando
                    rd = request.getRequestDispatcher("GerenteServlet?action=indexCliente");
                    rd.forward(request, response);                    
                    break;
            case "saveAluno":
                    //Acertando a data ingresso
                    dataParametro = java.sql.Date.valueOf(request.getParameter("anoIngresso"));
                    java.util.Date dataIngressoAluno = new java.util.Date(dataParametro.getTime());                                      
                                       
                    //Recebendo os parametros
                    aluno.setAnoIngresso(dataIngressoAluno);
                    aluno.setCep(request.getParameter("cep"));
                    aluno.setCpf(request.getParameter("cpf"));
                    aluno.setCurso(request.getParameter("curso"));
                    //Trabalhando com as datas
                    dataParametro = java.sql.Date.valueOf(request.getParameter("data"));
                    java.util.Date dataNovoAluno = new java.util.Date(dataParametro.getTime()); 
                    //Continua inserindo dados
                    aluno.setDataNascimento(dataNovoAluno);
                    aluno.setEmail(request.getParameter("email"));
                    aluno.setEstadoCadastro((byte)1);
                    aluno.setGrr(request.getParameter("grr"));
                    aluno.setIdCidade(Integer.parseInt(request.getParameter("idCidade")));
                    aluno.setNome(request.getParameter("nome"));
                    aluno.setRua(request.getParameter("rua"));
                    //Chamando Facade para atualizar os dados
                    gerenteFacade.inserirAluno(aluno);
                    //Depois de atualizado retornando para a index Cliente
                    rd = request.getRequestDispatcher("GerenteServlet?action=indexCliente");
                    rd.forward(request, response);
                    break;
                case "saveeExterno":
                    //Trabalhando com as datas
                    dataParametro = java.sql.Date.valueOf(request.getParameter("data"));
                    java.util.Date dataIngressoExterno = new java.util.Date(dataParametro.getTime()); 
                    //Recebendo os parametros
                    externo.setCep(request.getParameter("cep"));
                    externo.setCpf(request.getParameter("cpf"));
                    externo.setDataNascimento(dataIngressoExterno);
                    externo.setEmail(request.getParameter("email"));
                    externo.setEstadoCadastro((byte)1);
                    externo.setIdCidade(Integer.parseInt(request.getParameter("cidade")));
                    externo.setNome(request.getParameter("nome"));
                    externo.setObservacao(request.getParameter("observacao"));
                    externo.setRua(request.getParameter("rua"));
                    //Chamando Facade para atualizar os dados                    
                    gerenteFacade.inserirExterno(externo);
                    //Redirecionando
                    rd = request.getRequestDispatcher("GerenteServlet?action=indexCliente");
                    rd.forward(request, response);                    
                    break;
                case "saveProfessor":
                    //Trabalhando com as datas
                    dataParametro = java.sql.Date.valueOf(request.getParameter("data"));
                    java.util.Date dataIngressoProfessor = new java.util.Date(dataParametro.getTime());                     
                    //Recebendo os parametros
                    professor.setAreaEstudo(request.getParameter("cep"));
                    professor.setCep(request.getParameter("cep"));
                    professor.setCpf(request.getParameter("cep"));
                    professor.setDataNascimento(dataIngressoProfessor);
                    professor.setDepartamento(request.getParameter("departamento"));
                    professor.setEmail(request.getParameter("email"));
                    professor.setEstadoCadastro((byte)1);
                    professor.setIdCidade(Integer.parseInt(request.getParameter("cidade")));
                    professor.setNome(request.getParameter("nome"));
                    professor.setRua(request.getParameter("rua"));
                    //Chamando Facade para atualizar os dados
                    gerenteFacade.inserirProfessor(professor);
                    //Redirecionando
                    rd = request.getRequestDispatcher("GerenteServlet?action=indexCliente");
                    rd.forward(request, response);                    
                    break;
                case "saveServidor":
                    //Trabalhando com as datas
                    dataParametro = java.sql.Date.valueOf(request.getParameter("dataIngresso"));
                    java.util.Date dataIngressoServido = new java.util.Date(dataParametro.getTime());   
                    //Recebendo os parametros
                    servidor.setCep(request.getParameter("cep"));
                    servidor.setCpf(request.getParameter("cpf"));
                    servidor.setDataIngresso(dataIngressoServido);
                    //Trabalhando com as datas
                    dataParametro = java.sql.Date.valueOf(request.getParameter("data"));
                    java.util.Date dataServido = new java.util.Date(dataParametro.getTime());   
                    servidor.setDataNascimento(dataServido);
                    servidor.setEmail(request.getParameter("email"));
                    servidor.setEstadoCadastro((byte)1);
                    servidor.setIdCidade(Integer.parseInt(request.getParameter("cidade")));
                    servidor.setNome(request.getParameter("nome"));
                    servidor.setRua(request.getParameter("rua"));
                    servidor.setUnidade(request.getParameter("unidade"));
                    //Chamando Facade para atualizar os dados
                    gerenteFacade.inserirServidor(servidor);
                    //Redirecionando
                    rd = request.getRequestDispatcher("GerenteServlet?action=indexCliente");
                    rd.forward(request, response);                    
                    break;
                case "desativarCliente":
                    //Recebendo o CPF passado quando clicado no botão excluir
                    String cpfDelete = request.getParameter("cpf");
                    String actionExcluir = request.getParameter("tipo");
                    
                    switch(actionExcluir){
                        case "Aluno":
                        //Aluno
                        aluno = gerenteFacade.buscarAlunoPorCPF(cpfDelete);
                        gerenteFacade.desativarCadastroAluno(aluno.getId());
                        break;
                        case "Professor":
                        //Professor
                        professor = gerenteFacade.buscarProfessorPorCPF(cpfDelete);
                        gerenteFacade.desativarCadastroProfessor(professor.getId());
                        break;
                        case "Externo":
                        //Externo
                        externo = gerenteFacade.buscarExternoPorCPF(cpfDelete);
                        gerenteFacade.desativarCadastroExterno(externo.getId());
                        break;
                        case "Servidor":
                        servidor = gerenteFacade.buscarServidorPorCPF(cpfDelete);
                        //Fazendo as validações para saber qual facade chamador
                        gerenteFacade.desativarCadastroServidor(servidor.getId());
                        break;
                    }
                    
                    rd = request.getRequestDispatcher("GerenteServlet?action=indexCliente");
                    rd.forward(request, response);   
                    break;               

            } 
            
        }catch(Exception ex){
            //Tratando as exceções SQL
            String mensagem = "Ocorreu um erro: "+ ex.getMessage() +" ";
            rd = request.getRequestDispatcher(paginaParaEncaminharAExc);
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
            Logger.getLogger(GerenteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GerenteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GerenteServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GerenteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GerenteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GerenteServlet.class.getName()).log(Level.SEVERE, null, ex);
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

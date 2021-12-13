
package FACADE;

import BEANS.AlunoBean;
import BEANS.AtendenteBean;
import BEANS.CidadeBean;
import BEANS.EstadoBean;
import BEANS.ExternoBean;
import BEANS.GerenteBean;
import BEANS.NutricionistaBean;
import BEANS.ProfessorBean;
import BEANS.RegistroBean;
import BEANS.ServidorBean;
import DAO.AlunoDAO;
import DAO.AtendenteDAO;
import DAO.CidadeDAO;
import DAO.EstadoDAO;
import DAO.ExternoDAO;
import DAO.GerenteDAO;
import DAO.NutricionistaDAO;
import DAO.ProfessorDAO;
import DAO.RegistroDAO;
import DAO.ServidorDAO;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.List;
import java.util.stream.Collectors;

public class GerenteFacade {
 
    //Instanciando as DAOS
    ProfessorDAO professorDAO = new ProfessorDAO();
    AtendenteDAO atendenteDAO = new AtendenteDAO();
    ExternoDAO externoDAO = new ExternoDAO();
    GerenteDAO gerenteDAO = new GerenteDAO();
    NutricionistaDAO nutricionistaDAO = new NutricionistaDAO();
    AlunoDAO alunoDAO = new AlunoDAO();
    ServidorDAO servidorDAO = new ServidorDAO();
    RegistroDAO registroDAO = new RegistroDAO();
    CidadeDAO cidadeDAO = new CidadeDAO();
    EstadoDAO estadoDAO = new EstadoDAO();
    
    //Iniciando as DAO
    public GerenteFacade() throws InstantiationException, IllegalAccessException
    {
    }

    //***********************************
    //************* CLIENTES **************
    //***********************************
       
    public int retornaMes(String mes){
        int numeroMes = 0;
        if(mes.equalsIgnoreCase("Janeiro")){
            numeroMes = 1;
        }else if(mes.equalsIgnoreCase("Fevereiro")){
            numeroMes = 2;
        }else if(mes.equalsIgnoreCase("Março")){
            numeroMes = 3;
        }else if(mes.equalsIgnoreCase("Abril")){
            numeroMes = 4;
        }else if(mes.equalsIgnoreCase("Maio")){
            numeroMes = 5;
        }else if(mes.equalsIgnoreCase("Junho")){
            numeroMes = 6;
        }else if(mes.equalsIgnoreCase("Julho")){
            numeroMes = 7;
        }else if(mes.equalsIgnoreCase("Agosto")){
            numeroMes = 8;
        }else if(mes.equalsIgnoreCase("Setembro")){
            numeroMes = 9;
        }else if(mes.equalsIgnoreCase("Outubro")){
            numeroMes = 10;
        }else if(mes.equalsIgnoreCase("Novembro")){
            numeroMes = 11;
        }else if(mes.equalsIgnoreCase("Dezembro")){
            numeroMes = 12;
        }
      return numeroMes;  
    }
    
    public AlunoBean buscarAlunoPorCPF(String cpf) throws Exception{
        AlunoBean aluno = new AlunoBean();
        try{
            aluno = alunoDAO.buscarAlunoPorCPF(cpf);
        }catch(Exception ex){
            throw ex;
        }
      return aluno;
    }
    
    public ExternoBean buscarExternoPorCPF(String cpf) throws Exception{
        ExternoBean externo = new ExternoBean();
        try{
            externo = externoDAO.buscarExternoPorCPF(cpf);
        }catch(Exception ex){
            throw ex;
        }
      return externo;
    }
    
    public ProfessorBean buscarProfessorPorCPF(String cpf) throws Exception{
        ProfessorBean professor = new ProfessorBean();
        try{
            professor = professorDAO.buscarProfessorPorCPF(cpf);
        }catch(Exception ex){
            throw ex;
        }
      return professor;
    }
    
    public ServidorBean buscarServidorPorCPF(String cpf) throws Exception{
        ServidorBean servidor = new ServidorBean();
        try{
            servidor = servidorDAO.buscarServidorPorCPF(cpf);
        }catch(Exception ex){
            throw ex;
        }
      return servidor;
    }
    
    //***********************************
    //************* CIDADE **************
    //***********************************
    public CidadeBean buscarCidadePorId(int id) throws Exception{
        
        CidadeBean cidade = new CidadeBean();        
        try{
            cidade = cidadeDAO.buscarCidadePorId(id);
        }catch(Exception ex){
            throw ex;
        }        
        return cidade;
    }
    
    public List<EstadoBean> retornaListaEstados() throws Exception{
    
        List<EstadoBean> estados = new ArrayList();
        try{
            estados = estadoDAO.listarTodosEstados();
        }catch(Exception ex){
            throw ex;
        }
       return estados;
    }
    
    public List<CidadeBean> retornarCidades(int id) throws Exception{
        List<CidadeBean> lista = cidadeDAO.listarTodasCidadesPorEstado(id);
        try{
            lista = cidadeDAO.listarTodasCidadesPorEstado(id);
        }catch(Exception ex){
            throw ex;
        }
        return lista;
        
    }
    
    //***********************************
    //************* LISTAS **************
    //***********************************
    
    public String retornaMes(int mes){
        String retorna = "";
        if(mes == 1){
            retorna = "Janeiro";
        }else if(mes == 2){
            retorna = "Fevereiro";
        }else if(mes == 3){
            retorna = "Março";
        }else if(mes == 4){
            retorna = "Abril";
        }else if(mes == 5){
            retorna = "Maio";
        }else if(mes == 6){
            retorna = "Junho";
        }else if(mes == 7){
            retorna = "Julho";
        }else if(mes == 8){
            retorna = "Agosto";
        }else if(mes == 9){
            retorna = "Setembro";        
        }else if(mes == 10){
            retorna = "Outubro";
        }else if(mes == 11){
            retorna = "Novembro";
        }else if(mes == 12){
            retorna = "Dezembro";
        }
      return retorna;
    }
    
    //***********************************
    //************* LISTAS **************
    //***********************************
    public List<String> listaMeses() throws InstantiationException, IllegalAccessException{
        List<String> lista = registroDAO.listarMeses();
        try{
            return lista;
        }catch(Exception ex){
            throw ex;
        }        
    }
    
    public List<String> listaAnos() throws InstantiationException, IllegalAccessException{
        List<String> lista = registroDAO.listarAnos();
        try{
            return lista;
        }catch(Exception ex){
            throw ex;
        }        
    }
    
    //***********************************
    //********** Dados da INDEX *********
    //***********************************
    
    public List<RegistroBean> dashBoardAlmoco() throws Exception{
        List<RegistroBean> lista = new ArrayList();
        try{
            lista = registroDAO.dashboardGerenteAlmoco();
        }catch(Exception ex){
            throw ex;
        }        
        return lista;
    }
    
    public List<RegistroBean> dashBoardJantar() throws Exception{
        List<RegistroBean> lista = new ArrayList();
        try{
            lista = registroDAO.dashboardGerenteJanta();
        }catch(Exception ex){
            throw ex;
        }        
        return lista;
    }
    
    //***********************************
    //********** Professor DAO **********
    //***********************************
    public void inserirProfessor(ProfessorBean professor) throws Exception{        
        try{
            //Verificando se existe email e o cpf já existe em dados do Professor
            professorDAO.verificaCPFExistente(professor.getCpf());
            professorDAO.verificaEmailExistente(professor.getEmail());
                        
            professorDAO.adicionarProfessor(professor);
            
        }catch(Exception ex){
            throw ex;
        }        
    } 
    public void atualizarDadosProfessor(ProfessorBean professor) throws Exception{
        try{
            professorDAO.AtualizarDadosProfessor(professor);           
        }catch(Exception ex){
            throw ex;
        }
    }    
    public void buscarProfessorPorIniciais(String nomes) throws Exception{
        try{
            professorDAO.buscarProfessorPorCpfEmailNome(nomes);
        }catch(Exception ex){
            throw ex;
        }
    }    
    public List<ProfessorBean> listarProfessoresOrdenadoPorNome() throws Exception{
        try{
            return professorDAO.listarTodosProfessoresAtivos();
        }catch(Exception ex){
            throw ex;
        }
    }    
    public void desativarCadastroProfessor(int id) throws Exception{
        try{
            professorDAO.desativarCadastroProfessor(id);
        }catch(Exception ex){
            throw ex;
        }
    }
    public ProfessorBean buscarProfessorPorId(int id) throws Exception{
        try{
            return professorDAO.buscarProfessorPorId(id);
        }catch(Exception ex){
            throw ex;
        }
    }
    //***********************************
    //*********** Aluno DAO *************
    //***********************************
    public void inserirAluno(AlunoBean aluno) throws Exception{        
        try{
            //Verificando se existe email e o cpf já existe em dados do Aluno
            alunoDAO.verificaCPFExistente(aluno.getCpf());
            alunoDAO.verificaEmailExistente(aluno.getEmail());
                        
            alunoDAO.adicionarAluno(aluno);
            
        }catch(Exception ex){
            throw ex;
        }        
    }    
    public void atualizarDadosAluno(AlunoBean aluno) throws Exception{
        try{
            alunoDAO.AtualizarDadosAluno(aluno);           
        }catch(Exception ex){
            throw ex;
        }
    }    
    public void buscarAlunoPorIniciais(String nomes) throws Exception{
        try{
            alunoDAO.buscarAlunoPorCpfEmailNome(nomes);
        }catch(Exception ex){
            throw ex;
        }
    }    
    public List<AlunoBean> listarAlunosOrdenadoPorNome() throws Exception{
        try{
            return alunoDAO.listarTodosAlunossAtivos();
        }catch(Exception ex){
            throw ex;
        }
    }    
    public void desativarCadastroAluno(int id) throws Exception{
        try{
            alunoDAO.desativarCadastroAluno(id);
        }catch(Exception ex){
            throw ex;
        }
    }
    public AlunoBean buscarAlunoPorId(int id) throws Exception{
        try{
            return alunoDAO.buscarAlunoPorId(id);
        }catch(Exception ex){
            throw ex;
        }
    }
    //***********************************
    //********** Externo DAO ************
    //*********************************** 
    public void inserirExterno(ExternoBean externo) throws Exception{        
        try{
        //Verificando se existe email e o cpf já existe em dados do Externo
            externoDAO.verificaCPFExistente(externo.getCpf());
            externoDAO.verificaEmailExistente(externo.getEmail());
                        
            externoDAO.adicionarExterno(externo);            
        }catch(Exception ex){
            throw ex;
        }        
    }    
    public void atualizarDadosExterno(ExternoBean externo) throws Exception{
        try{
            externoDAO.AtualizarDadosExterno(externo);           
        }catch(Exception ex){
            throw ex;
        }
    }    
    public void buscarExternoPorIniciais(String nomes) throws Exception{
        try{
            externoDAO.buscarExternoPorCpfEmailNome(nomes);
        }catch(Exception ex){
            throw ex;
        }
    }    
    public List<ExternoBean> listarExternosOrdenadoPorNome() throws Exception{
        try{
            return externoDAO.listarTodosExternosAtivos();
        }catch(Exception ex){
            throw ex;
        }
    }    
    public void desativarCadastroExterno(int id) throws Exception{
        try{
            externoDAO.desativarCadastroExterno(id);
        }catch(Exception ex){
            throw ex;
        }
    }
    public ExternoBean buscarExternoPorId(int id) throws Exception{
        try{
            return externoDAO.buscarExternoPorId(id);
        }catch(Exception ex){
            throw ex;
        }
    }
    
    //***********************************
    //********** Servidor DAO ***********
    //***********************************    
    public void inserirServidor(ServidorBean servidor) throws Exception{        
        try{
        //Verificando se existe email e o cpf já existe em dados do Servidor
            servidorDAO.verificaCPFExistente(servidor.getCpf());
            servidorDAO.verificaEmailExistente(servidor.getEmail());
                        
            servidorDAO.adicionarServidor(servidor);            
        }catch(Exception ex){
            throw ex;
        }        
    }    
    public void atualizarDadosServidor(ServidorBean servidor) throws Exception{
        try{
            servidorDAO.AtualizarDadosServidor(servidor);           
        }catch(Exception ex){
            throw ex;
        }
    }    
    public void buscarServidorPorIniciais(String nomes) throws Exception{
        try{
            servidorDAO.buscarServidorPorCpfEmailNome(nomes);
        }catch(Exception ex){
            throw ex;
        }
    }    
    public List<ServidorBean> listarServidoresOrdenadoPorNome() throws Exception{
        try{
            return servidorDAO.listarTodosServidoresAtivos();
        }catch(Exception ex){
            throw ex;
        }
    }    
    public void desativarCadastroServidor(int id) throws Exception{
        try{
            servidorDAO.desativarCadastroServidor(id);
        }catch(Exception ex){
            throw ex;
        }
    }
   public ServidorBean buscarServidorPorId(int id) throws Exception{
        try{
            return servidorDAO.buscarServidorPorId(id);
        }catch(Exception ex){
            throw ex;
        }
    }
    //***********************************
    //********* Atendente DAO ***********
    //*********************************** 
    public void inserirAtendente(AtendenteBean atendente) throws Exception{        
        try{
        //Verificando se existe email e o cpf já existe em dados do Atendente
            gerenteDAO.verificaCPFExistente(atendente.getCpf());
            gerenteDAO.verificaEmailExistente(atendente.getEmail());
                        
            atendenteDAO.adicionarAtendente(atendente);            
        }catch(Exception ex){
            throw ex;
        }        
    }    
    public void atualizarDadosAtendente(AtendenteBean atendente) throws Exception{
        try{
            atendenteDAO.AtualizarDadosAtendente(atendente);           
        }catch(Exception ex){
            throw ex;
        }
    }    
    public void buscarAtendentePorIniciais(String nomes) throws Exception{
        try{
            atendenteDAO.buscarAtendentePorCpfEmailNome(nomes);
        }catch(Exception ex){
            throw ex;
        }
    }    
    public List<AtendenteBean> listarAtendentesOrdenadoPorNome() throws Exception{
        try{
            return atendenteDAO.listarTodosAtendentesAtivos();
        }catch(Exception ex){
            throw ex;
        }
    }    
    public void desativarCadastroAtendente(int id) throws Exception{
        try{
            atendenteDAO.desativarCadastroAtendente(id);
        }catch(Exception ex){
            throw ex;
        }
    }
    public AtendenteBean buscarAtendentePorId(int id) throws Exception{
        try{
            return atendenteDAO.buscarAtendentePorId(id);
        }catch(Exception ex){
            throw ex;
        }
    }
    //***********************************
    //******* Nutricionista DAO *********
    //*********************************** 
    public void inserirNutricionista(NutricionistaBean nutricionista) throws Exception{        
        try{
        //Verificando se existe email e o cpf já existe em dados do Atendente
            nutricionistaDAO.verificaCPFExistente(nutricionista.getCpf());
            nutricionistaDAO.verificaEmailExistente(nutricionista.getEmail());
                        
            nutricionistaDAO.adicionarNutricionista(nutricionista);            
        }catch(Exception ex){
            throw ex;
        }        
    }    
    public void atualizarDadosNutricionista(NutricionistaBean nutricionista) throws Exception{
        try{
             nutricionistaDAO.AtualizarDadosNutricionista(nutricionista);           
        }catch(Exception ex){
            throw ex;
        }
    }    
    public void buscarNutricionistaPorIniciais(String nomes) throws Exception{
        try{
            nutricionistaDAO.buscarNutricionistaPorCpfEmailNome(nomes);
        }catch(Exception ex){
            throw ex;
        }
    }    
    public List<NutricionistaBean> listarNutricionistasOrdenadoPorNome() throws Exception{
        try{
            return nutricionistaDAO.listarTodosNutricionistasAtivos();
        }catch(Exception ex){
            throw ex;
        }
    }    
    public void desativarCadastroNutricionista(int id) throws Exception{
        try{
            nutricionistaDAO.desativarCadastroNutricionista(id);
        }catch(Exception ex){
            throw ex;
        }
    }
    public NutricionistaBean buscarNutricionistaPorId(int id) throws Exception{
        try{
            return nutricionistaDAO.buscarNutricionistaPorId(id);
        }catch(Exception ex){
            throw ex;
        }
    }
    //***********************************
    //******* Nutricionista DAO *********
    //***********************************
    public void inserirGerente(GerenteBean gerente) throws Exception{        
        try{
        //Verificando se existe email e o cpf já existe em dados do Atendente
            gerenteDAO.verificaCPFExistente(gerente.getCpf());
            gerenteDAO.verificaEmailExistente(gerente.getEmail());
                        
            gerenteDAO.adicionarGerente(gerente);            
        }catch(Exception ex){
            throw ex;
        }        
    }    
    public void atualizarDadosGerente(GerenteBean gerente) throws Exception{
        try{
             gerenteDAO.AtualizarDadosGerente(gerente);           
        }catch(Exception ex){
            throw ex;
        }
    }    
    public void buscarGerentePorIniciais(String nomes) throws Exception{
        try{
            gerenteDAO.buscarGerentePorCpfEmailNome(nomes);
        }catch(Exception ex){
            throw ex;
        }
    }    
    public List<GerenteBean> listarGerentesOrdenadoPorNome() throws Exception{
        try{
            return gerenteDAO.listarTodosGerentesAtivos();
        }catch(Exception ex){
            throw ex;
        }
    }    
    public void desativarCadastroGerente(int id) throws Exception{
        try{
            gerenteDAO.desativarCadastroGerente(id);
        }catch(Exception ex){
            throw ex;
        }
    }
    public GerenteBean buscarGerentePorId(int id) throws Exception{
        try{
            return gerenteDAO.buscarGerentePorId(id);
        }catch(Exception ex){
            throw ex;
        }
    }
    
    public List<GerenteBean> listarTodosUsuarios() throws Exception{
        List<GerenteBean> lista = new ArrayList();
        try{
            lista = gerenteDAO.listarTodosUsuarios();
        }catch(Exception ex){
            throw ex;
        }
      return lista;
    }
    
    
    
}

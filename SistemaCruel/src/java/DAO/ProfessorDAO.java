
package DAO;

import BEANS.ProfessorBean;
import EXCEPTION.AdicionarException;
import EXCEPTION.EditarException;
import EXCEPTION.ErroCpfExistenteException;
import EXCEPTION.ErroDesativarCadastroException;
import EXCEPTION.ErroEmailExistenteException;
import EXCEPTION.ErroUsuarioNaoEncontradoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {
    
    private Connection con = null;
    
    public ProfessorDAO() throws InstantiationException, IllegalAccessException{
        con = ConnectionFactory.getConnection();
    }
    
    public void adicionarProfessor(ProfessorBean aluno) throws InstantiationException, IllegalAccessException, AdicionarException{               
        String sql = "INSERT INTO Professor(nome,cpf,dataNascimento,idCidade,Rua,cep,email,estadoCadastro,departamento,AreaEstudo) values"
                + "(?,?,?,?,?,?,?,?,?,?)";
        con = ConnectionFactory.getConnection();        
        java.sql.Date dataNascimento = new java.sql.Date(aluno.getDataNascimento().getTime());
        
        PreparedStatement stmt = null;
        byte estadoCadastro = 1;
        try{
            //Abrindo uma conexão para inserir os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1,aluno.getNome());
            stmt.setString(2,aluno.getCpf());
            stmt.setDate(3,dataNascimento);
            stmt.setInt(4,aluno.getIdCidade());
            stmt.setString(5,aluno.getRua());
            stmt.setString(6,aluno.getCep());
            stmt.setString(7,aluno.getEmail());
            stmt.setByte(8, estadoCadastro);
            stmt.setString(9,aluno.getDepartamento());
            stmt.setString(10,aluno.getAreaEstudo());
            stmt.executeUpdate();
            
        }catch(Exception ex){
            throw new AdicionarException("Aluno");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }              
    }

    public ProfessorBean buscarProfessorPorCPF(String cpf) throws InstantiationException, IllegalAccessException, ErroUsuarioNaoEncontradoException{
    ProfessorBean professor = new ProfessorBean();
    PreparedStatement stmt = null;
    String sql = "SELECT * FROM Professor WHERE cpf=?";
    con = ConnectionFactory.getConnection();
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1,cpf);
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            if(rs.next()){ 
                professor.setId(rs.getInt("id"));
                professor.setCep(rs.getString("cep"));
                professor.setCpf(rs.getString("cpf"));
                professor.setDataNascimento(rs.getDate("dataNascimento"));
                professor.setEmail(rs.getString("email"));
                professor.setEstadoCadastro(rs.getByte("estadoCadastro"));
                professor.setIdCidade(rs.getInt("idCidade"));
                professor.setNome(rs.getString("nome"));
                professor.setRua(rs.getString("Rua"));
                professor.setDepartamento(rs.getString("departamento"));
                professor.setAreaEstudo(rs.getString("AreaEstudo"));
            }
         return professor;
        }catch(Exception ex){
            throw new ErroUsuarioNaoEncontradoException("Professor");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }    
    }
    
    public ProfessorBean buscarProfessorPorId(int id) throws InstantiationException, IllegalAccessException, ErroUsuarioNaoEncontradoException{
    ProfessorBean professor = new ProfessorBean();
    PreparedStatement stmt = null;
    String sql = "SELECT * FROM Professor WHERE id=?";
    con = ConnectionFactory.getConnection();
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            if(rs.next()){ 
                professor.setId(rs.getInt("id"));
                professor.setCep(rs.getString("cep"));
                professor.setCpf(rs.getString("cpf"));
                professor.setDataNascimento(rs.getDate("dataNascimento"));
                professor.setEmail(rs.getString("email"));
                professor.setEstadoCadastro(rs.getByte("estadoCadastro"));
                professor.setIdCidade(rs.getInt("idCidade"));
                professor.setNome(rs.getString("nome"));
                professor.setRua(rs.getString("Rua"));
                professor.setDepartamento(rs.getString("departamento"));
                professor.setAreaEstudo(rs.getString("AreaEstudo"));
            }else{
                throw new Exception();
            }
         return professor;
        }catch(Exception ex){
            throw new ErroUsuarioNaoEncontradoException("Professor");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }    
    }
        
    public List<ProfessorBean> buscarProfessorPorCpfEmailNome(String preview) throws InstantiationException, IllegalAccessException, ErroUsuarioNaoEncontradoException{    
        PreparedStatement stmt = null;
        List<ProfessorBean> listaProfessor = new ArrayList();
        String sql = "SELECT * FROM Professor WHERE nome LIKE %?% OR cpf LIKE %?% OR email LIKE %?%";
        con = ConnectionFactory.getConnection();
        try{
            ProfessorBean professor = new ProfessorBean();
            stmt = con.prepareStatement(sql);
            stmt.setString(1,preview);
            stmt.setString(2,preview);
            stmt.setString(3,preview);
            
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            while(rs.next()){ 
                professor.setId(rs.getInt("id"));
                professor.setCep(rs.getString("cep"));
                professor.setCpf(rs.getString("cpf"));
                professor.setDataNascimento(rs.getDate("dataNascimento"));
                professor.setEmail(rs.getString("email"));
                professor.setEstadoCadastro(rs.getByte("estadoCadastro"));
                professor.setIdCidade(rs.getInt("idCidade"));
                professor.setNome(rs.getString("nome"));
                professor.setRua(rs.getString("Rua"));
                professor.setAreaEstudo(rs.getString("AreaEstudo"));
                professor.setDepartamento(rs.getString("departamento"));                
                listaProfessor.add(professor);
            }
         return listaProfessor;
        }catch(Exception ex){
            System.out.println("Erro ao tentar uasr o sql like");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaProfessor;
    }
    
        public List<ProfessorBean> listarTodosProfessoresAtivos() throws InstantiationException, IllegalAccessException{
        List<ProfessorBean> listaProfessores = new ArrayList();
        
        String sql = "SELECT * FROM Professor WHERE estadoCadastro=1 ORDER BY nome ASC";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                ProfessorBean professor = new ProfessorBean();
                professor.setId(rs.getInt("id"));
                professor.setCep(rs.getString("cep"));
                professor.setCpf(rs.getString("cpf"));
                professor.setDataNascimento(rs.getDate("dataNascimento"));
                professor.setEmail(rs.getString("email"));
                professor.setEstadoCadastro(rs.getByte("estadoCadastro"));
                professor.setIdCidade(rs.getInt("idCidade"));
                professor.setNome(rs.getString("nome"));
                professor.setRua(rs.getString("Rua"));
                professor.setDepartamento(rs.getString("departamento"));
                professor.setAreaEstudo(rs.getString("AreaEstudo"));
                listaProfessores.add(professor);
            }            
            return listaProfessores;                       
        }catch(Exception ex){
            System.out.println("Ocorreu algum erro ao listar os professores ativos " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaProfessores; 
    }
    
    public List<ProfessorBean> listarTodosProfessoresDesativados() throws InstantiationException, IllegalAccessException{
        List<ProfessorBean> listaProfessores = new ArrayList();
        
        String sql = "SELECT * FROM Professor WHERE estadoCadastro=0";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                ProfessorBean professor = new ProfessorBean();
                professor.setId(rs.getInt("id"));
                professor.setCep(rs.getString("cep"));
                professor.setCpf(rs.getString("cpf"));
                professor.setDataNascimento(rs.getDate("dataNascimento"));
                professor.setEmail(rs.getString("email"));
                professor.setEstadoCadastro(rs.getByte("estadoCadastro"));
                professor.setIdCidade(rs.getInt("idCidade"));
                professor.setNome(rs.getString("nome"));
                professor.setRua(rs.getString("Rua"));
                professor.setDepartamento(rs.getString("departamento"));
                professor.setAreaEstudo(rs.getString("AreaEstudo"));               
                listaProfessores.add(professor);
            }            
            return listaProfessores;                       
        }catch(Exception ex){
            System.out.println("Ocorreu algum erro ao listar os professores desativados " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaProfessores; 
    }
    
    public void AtualizarDadosProfessor(ProfessorBean professor) throws IllegalAccessException, InstantiationException, EditarException{        
        String sql = "UPDATE Professor SET nome=?, cpf=?, dataNascimento=?, idCidade=?, Rua=?, cep=?, email=?, estadoCadastro=?,"
                        + " AreaEstudo=?, departamento=? WHERE id=?";        
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        java.sql.Date data = new java.sql.Date(professor.getDataNascimento().getTime());
        try{        
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1,professor.getNome());
            stmt.setString(2,professor.getCpf());
            stmt.setDate(3,data);
            stmt.setInt(4,professor.getIdCidade());
            stmt.setString(5, professor.getRua());
            stmt.setString(6, professor.getCep());
            stmt.setString(7, professor.getEmail());
            stmt.setByte(8,professor.getEstadoCadastro());
            stmt.setString(9,professor.getAreaEstudo());
            stmt.setString(10,professor.getDepartamento());
            stmt.setInt(11, professor.getId());
            stmt.execute();
                        
        }catch(Exception ex){
            throw new EditarException("o cadastro do(a) professor " + professor.getNome());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }        
    }
    
    public void desativarCadastroProfessor(int id) throws InstantiationException, IllegalAccessException, ErroDesativarCadastroException{
        byte desativar = 0;
        String sql = "UPDATE Professor SET estadoCadastro=? WHERE id=?";        
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            stmt = con.prepareStatement(sql);
            stmt.setByte(1,desativar);
            stmt.setInt(2, id);
        }catch(Exception ex){
            throw new ErroDesativarCadastroException();
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }        
    }
    
        public void verificaCPFExistente(String cpf) throws InstantiationException, IllegalAccessException, ErroCpfExistenteException{
        List<String> listaCPF = new ArrayList();
        String sql = "select cpf from Atendente " +
        "union " +
        "select cpf from Professor " +
        "union " +
        "select cpf from Gerente " +
        "union " +
        "select cpf from Externo " +
        "union " +
        "select cpf from Aluno " +
        "union " +
        "select cpf from Servidor " +
        "union " +
        "select cpf from Nutricionista";
        PreparedStatement stmt = null;
        int verificado = 0;
        con = ConnectionFactory.getConnection();
        
        try{
            //Pesquisando os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();            
            //Se existir um CPF cadastrado como o passado ele vai passar pelo IF
            if(rs.next()){
                listaCPF.add(rs.getString("cpf"));
            }
            for(String var : listaCPF){
                if(var.equals(cpf)){
                   throw new Exception();
                }
            }
        }catch(Exception ex){
            throw new ErroCpfExistenteException();
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }        
    }
    
    public void verificaEmailExistente(String email) throws InstantiationException, IllegalAccessException, ErroEmailExistenteException{
        List<String> listaEmail = new ArrayList();
        String sql = "select email from Atendente " +
        "union " +
        "select email from Professor " +
        "union " +
        "select email from Gerente " +
        "union " +
        "select email from Externo " +
        "union " +
        "select email from Aluno " +
        "union " +
        "select email from Servidor " +
        "union " +
        "select email from Nutricionista";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();
        
        try{
            //Pesquisando os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();            
            //Se existir um EMAIL cadastrado como o passado. Ele vai passar pelo IF e retornar a exceção
            if(rs.next()){
                listaEmail.add(rs.getString("email"));
            }
            for(String var : listaEmail){
                if(var.equals(email)){
                   throw new Exception();
                }
            }    
        }catch(Exception ex){
            throw new ErroEmailExistenteException();
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }        
    }
    
}

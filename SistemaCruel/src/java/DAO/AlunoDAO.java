
package DAO;

import BEANS.AlunoBean;
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

public class AlunoDAO {
    
    private Connection con = null;
    
    public AlunoDAO() throws InstantiationException, IllegalAccessException{
        con = ConnectionFactory.getConnection();
    }
    
    public void adicionarAluno(AlunoBean aluno) throws InstantiationException, IllegalAccessException, AdicionarException{               
        String sql = "INSERT INTO Aluno(nome,cpf,dataNascimento,idCidade,Rua,cep,email,estadoCadastro,AnoIngresso,grr,curso) values"
                + "(?,?,?,?,?,?,?,?,?,?)";
        con = ConnectionFactory.getConnection();        
        java.sql.Date dataNascimento = new java.sql.Date(aluno.getDataNascimento().getTime());
        java.sql.Date dataIngresso = new java.sql.Date(aluno.getAnoIngresso().getTime());
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
            stmt.setDate(9,dataIngresso);
            stmt.setString(10,aluno.getGrr());
            stmt.setString(11,aluno.getCurso());
            stmt.executeUpdate();
            
        }catch(Exception ex){
            throw new AdicionarException("Aluno");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }              
    }
        
    public AlunoBean buscarAlunoPorId(int id) throws InstantiationException, IllegalAccessException, ErroUsuarioNaoEncontradoException{
    AlunoBean aluno = new AlunoBean();
    PreparedStatement stmt = null;
    String sql = "SELECT * FROM Aluno WHERE id=?";
    con = ConnectionFactory.getConnection();
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            if(rs.next()){ 
                aluno.setId(rs.getInt("id"));
                aluno.setCep(rs.getString("cep"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setDataNascimento(rs.getDate("dataNascimento"));
                aluno.setEmail(rs.getString("email"));
                aluno.setEstadoCadastro(rs.getByte("estadoCadastro"));
                aluno.setIdCidade(rs.getInt("idCidade"));
                aluno.setNome(rs.getString("nome"));
                aluno.setRua(rs.getString("Rua"));
                aluno.setAnoIngresso(rs.getDate("AnoIngresso"));
                aluno.setGrr(rs.getString("grr"));
                aluno.setCurso(rs.getString("curso"));
            }else{
                throw new Exception();
            }
         return aluno;
        }catch(Exception ex){
            throw new ErroUsuarioNaoEncontradoException("Aluno");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }    
    }
    
    public AlunoBean buscarAlunoPorCPF(String cpf) throws InstantiationException, IllegalAccessException, ErroUsuarioNaoEncontradoException{
    AlunoBean aluno = new AlunoBean();
    PreparedStatement stmt = null;
    String sql = "SELECT * FROM Aluno WHERE cpf=?";
    con = ConnectionFactory.getConnection();
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1,cpf);
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            if(rs.next()){ 
                aluno.setId(rs.getInt("id"));
                aluno.setCep(rs.getString("cep"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setDataNascimento(rs.getDate("dataNascimento"));
                aluno.setEmail(rs.getString("email"));
                aluno.setEstadoCadastro(rs.getByte("estadoCadastro"));
                aluno.setIdCidade(rs.getInt("idCidade"));
                aluno.setNome(rs.getString("nome"));
                aluno.setRua(rs.getString("Rua"));
                aluno.setAnoIngresso(rs.getDate("AnoIngresso"));
                aluno.setGrr(rs.getString("grr"));
                aluno.setCurso(rs.getString("curso"));
            }
         return aluno;
        }catch(Exception ex){
            throw new ErroUsuarioNaoEncontradoException("Aluno");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }    
    }
    
    public List<AlunoBean> buscarAlunoPorCpfEmailNome(String preview) throws InstantiationException, IllegalAccessException, ErroUsuarioNaoEncontradoException{    
    PreparedStatement stmt = null;
    List<AlunoBean> listaAlunos = new ArrayList();
    String sql = "SELECT * FROM Aluno WHERE nome LIKE %?% OR cpf LIKE %?% OR email LIKE %?%";
    con = ConnectionFactory.getConnection();
        try{
            AlunoBean aluno = new AlunoBean();
            stmt = con.prepareStatement(sql);
            stmt.setString(1,preview);
            stmt.setString(2,preview);
            stmt.setString(3,preview);
            
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            while(rs.next()){ 
                aluno.setId(rs.getInt("id"));
                aluno.setCep(rs.getString("cep"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setDataNascimento(rs.getDate("dataNascimento"));
                aluno.setEmail(rs.getString("email"));
                aluno.setEstadoCadastro(rs.getByte("estadoCadastro"));
                aluno.setIdCidade(rs.getInt("idCidade"));
                aluno.setNome(rs.getString("nome"));
                aluno.setRua(rs.getString("Rua"));
                aluno.setAnoIngresso(rs.getDate("AnoIngresso"));
                aluno.setGrr(rs.getString("grr"));
                aluno.setCurso(rs.getString("curso"));
                listaAlunos.add(aluno);
            }
         return listaAlunos;
        }catch(Exception ex){
            System.out.println("Erro ao tentar uasr o sql like");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaAlunos;
    }
    
    public List<AlunoBean> listarTodosAlunossAtivos() throws InstantiationException, IllegalAccessException{
        List<AlunoBean> listaAlunos = new ArrayList();
        
        String sql = "SELECT * FROM Aluno WHERE estadoCadastro=1 ORDER BY nome ASC";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                AlunoBean aluno = new AlunoBean();
                aluno.setId(rs.getInt("id"));
                aluno.setCep(rs.getString("cep"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setDataNascimento(rs.getDate("dataNascimento"));
                aluno.setEmail(rs.getString("email"));
                aluno.setEstadoCadastro(rs.getByte("estadoCadastro"));
                aluno.setIdCidade(rs.getInt("idCidade"));
                aluno.setNome(rs.getString("nome"));
                aluno.setRua(rs.getString("Rua"));
                aluno.setAnoIngresso(rs.getDate("AnoIngresso"));
                aluno.setGrr(rs.getString("grr")); 
                aluno.setCurso(rs.getString("curso"));
                listaAlunos.add(aluno);
            }            
            return listaAlunos;                       
        }catch(Exception ex){
            System.out.println("Ocorreu algum erro ao listar os alunos ativos " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaAlunos; 
    }
    
    public List<AlunoBean> listarTodosAlunosDesativados() throws InstantiationException, IllegalAccessException{
        List<AlunoBean> listaAlunos = new ArrayList();
        
        String sql = "SELECT * FROM Aluno WHERE estadoCadastro=0";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                AlunoBean aluno = new AlunoBean();
                aluno.setId(rs.getInt("id"));
                aluno.setCep(rs.getString("cep"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setDataNascimento(rs.getDate("dataNascimento"));
                aluno.setEmail(rs.getString("email"));
                aluno.setEstadoCadastro(rs.getByte("estadoCadastro"));
                aluno.setIdCidade(rs.getInt("idCidade"));
                aluno.setNome(rs.getString("nome"));
                aluno.setRua(rs.getString("Rua"));
                aluno.setAnoIngresso(rs.getDate("AnoIngresso"));
                aluno.setGrr(rs.getString("grr"));      
                aluno.setCurso(rs.getString("curso"));
                listaAlunos.add(aluno);
            }            
            return listaAlunos;                       
        }catch(Exception ex){
            System.out.println("Ocorreu algum erro ao listar os alunos desativados " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaAlunos; 
    }
    
    public void AtualizarDadosAluno(AlunoBean aluno) throws IllegalAccessException, InstantiationException, EditarException{        
        String sql = "UPDATE Aluno SET nome=?, cpf=?, dataNascimento=?, idCidade=?, Rua=?, cep=?, email=?, estadoCadastro=?,"
                        + " AnoIngresso=?, grr=?, curso=? WHERE id=?";        
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        java.sql.Date data = new java.sql.Date(aluno.getDataNascimento().getTime());
        java.sql.Date dataIngresso = new java.sql.Date(aluno.getAnoIngresso().getTime());
        try{        
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1,aluno.getNome());
            stmt.setString(2,aluno.getCpf());
            stmt.setDate(3,data);
            stmt.setInt(4,aluno.getIdCidade());
            stmt.setString(5, aluno.getRua());
            stmt.setString(6, aluno.getCep());
            stmt.setString(7, aluno.getEmail());
            stmt.setByte(8,aluno.getEstadoCadastro());
            stmt.setDate(9,dataIngresso);
            stmt.setString(10,aluno.getGrr());
            stmt.setString(11,aluno.getCurso());
            stmt.setInt(12, aluno.getId());
            stmt.execute();
                        
        }catch(Exception ex){
            throw new EditarException("o cadastro do(a) aluno " + aluno.getNome());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }        
    }
    
    public void desativarCadastroAluno(int id) throws InstantiationException, IllegalAccessException, ErroDesativarCadastroException{
        byte desativar = 0;
        String sql = "UPDATE Aluno SET estadoCadastro=? WHERE id=?";     
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

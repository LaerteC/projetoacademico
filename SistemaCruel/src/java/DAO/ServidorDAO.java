
package DAO;

import BEANS.ServidorBean;
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

public class ServidorDAO {
    
    private Connection con = null;
    
    public ServidorDAO() throws InstantiationException, IllegalAccessException{
        con = ConnectionFactory.getConnection();
        }
    
    public void adicionarServidor(ServidorBean servidor) throws InstantiationException, IllegalAccessException, AdicionarException{               
        String sql = "INSERT INTO Servidor(nome,cpf,dataNascimento,idCidade,Rua,cep,email,estadoCadastro,dataIngresso,unidade) values"
                + "(?,?,?,?,?,?,?,?,?,?)";
        con = ConnectionFactory.getConnection();        
        java.sql.Date dataNascimento = new java.sql.Date(servidor.getDataNascimento().getTime());
        java.sql.Date dataIngresso = new java.sql.Date(servidor.getDataIngresso().getTime());
        PreparedStatement stmt = null;
        byte estadoCadastro = 1;
        try{
            //Abrindo uma conexão para inserir os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1,servidor.getNome());
            stmt.setString(2,servidor.getCpf());
            stmt.setDate(3,dataNascimento);
            stmt.setInt(4,servidor.getIdCidade());
            stmt.setString(5,servidor.getRua());
            stmt.setString(6,servidor.getCep());
            stmt.setString(7,servidor.getEmail());
            stmt.setByte(8, estadoCadastro);
            stmt.setDate(9,dataIngresso);
            stmt.setString(10,servidor.getUnidade());
            stmt.executeUpdate();
            
        }catch(Exception ex){
            throw new AdicionarException("Servidor");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }              
    }
    
    public ServidorBean buscarServidorPorCPF(String cpf) throws InstantiationException, IllegalAccessException, ErroUsuarioNaoEncontradoException{
    ServidorBean servidor = new ServidorBean();
    PreparedStatement stmt = null;
    String sql = "SELECT * FROM Servidor WHERE cpf=?";
    con = ConnectionFactory.getConnection();
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1,cpf);
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            if(rs.next()){ 
                servidor.setId(rs.getInt("id"));
                servidor.setCep(rs.getString("cep"));
                servidor.setCpf(rs.getString("cpf"));
                servidor.setDataNascimento(rs.getDate("dataNascimento"));
                servidor.setEmail(rs.getString("email"));
                servidor.setEstadoCadastro(rs.getByte("estadoCadastro"));
                servidor.setIdCidade(rs.getInt("idCidade"));
                servidor.setNome(rs.getString("nome"));
                servidor.setRua(rs.getString("Rua"));
                servidor.setDataIngresso(rs.getDate("dataIngresso"));
                servidor.setUnidade(rs.getString("unidade"));
            }
         return servidor;
        }catch(Exception ex){
            throw new ErroUsuarioNaoEncontradoException("Servidor");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }    
    }
        
    public ServidorBean buscarServidorPorId(int id) throws InstantiationException, IllegalAccessException, ErroUsuarioNaoEncontradoException{
    ServidorBean servidor = new ServidorBean();
    PreparedStatement stmt = null;
    String sql = "SELECT * FROM Servidor WHERE id=?";
    con = ConnectionFactory.getConnection();
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            if(rs.next()){ 
                servidor.setId(rs.getInt("id"));
                servidor.setCep(rs.getString("cep"));
                servidor.setCpf(rs.getString("cpf"));
                servidor.setDataNascimento(rs.getDate("dataNascimento"));
                servidor.setEmail(rs.getString("email"));
                servidor.setEstadoCadastro(rs.getByte("estadoCadastro"));
                servidor.setIdCidade(rs.getInt("idCidade"));
                servidor.setNome(rs.getString("nome"));
                servidor.setRua(rs.getString("Rua"));
                servidor.setDataIngresso(rs.getDate("dataIngresso"));
                servidor.setUnidade(rs.getString("unidade"));
            }else{
                throw new Exception();
            }
         return servidor;
        }catch(Exception ex){
            throw new ErroUsuarioNaoEncontradoException("Servidor");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }    
    }
    
    public List<ServidorBean> buscarServidorPorCpfEmailNome(String preview) throws InstantiationException, IllegalAccessException, ErroUsuarioNaoEncontradoException{    
    PreparedStatement stmt = null;
    List<ServidorBean> listaServidores = new ArrayList();
    String sql = "SELECT * FROM Servidor WHERE nome LIKE %?% OR cpf LIKE %?% OR email LIKE %?%";
    con = ConnectionFactory.getConnection();
        try{
            ServidorBean servidor = new ServidorBean();
            stmt = con.prepareStatement(sql);
            stmt.setString(1,preview);
            stmt.setString(2,preview);
            stmt.setString(3,preview);
            
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            while(rs.next()){ 
                servidor.setId(rs.getInt("id"));
                servidor.setCep(rs.getString("cep"));
                servidor.setCpf(rs.getString("cpf"));
                servidor.setDataNascimento(rs.getDate("dataNascimento"));
                servidor.setEmail(rs.getString("email"));
                servidor.setEstadoCadastro(rs.getByte("estadoCadastro"));
                servidor.setIdCidade(rs.getInt("idCidade"));
                servidor.setNome(rs.getString("nome"));
                servidor.setRua(rs.getString("Rua"));
                servidor.setDataIngresso(rs.getDate("dataIngresso"));
                servidor.setUnidade(rs.getString("unidade"));            
                listaServidores.add(servidor);
            }
         return listaServidores;
        }catch(Exception ex){
            System.out.println("Erro ao tentar usar o sql like");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaServidores;
    }
    
    public List<ServidorBean> listarTodosServidoresAtivos() throws InstantiationException, IllegalAccessException{
        List<ServidorBean> listaExternos = new ArrayList();
        
        String sql = "SELECT * FROM Servidor WHERE estadoCadastro=1 ORDER BY nome ASC";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                ServidorBean servidor = new ServidorBean();
                servidor.setId(rs.getInt("id"));
                servidor.setCep(rs.getString("cep"));
                servidor.setCpf(rs.getString("cpf"));
                servidor.setDataNascimento(rs.getDate("dataNascimento"));
                servidor.setEmail(rs.getString("email"));
                servidor.setEstadoCadastro(rs.getByte("estadoCadastro"));
                servidor.setIdCidade(rs.getInt("idCidade"));
                servidor.setNome(rs.getString("nome"));
                servidor.setRua(rs.getString("Rua"));
                servidor.setDataIngresso(rs.getDate("dataIngresso"));
                servidor.setUnidade(rs.getString("unidade"));                 
                listaExternos.add(servidor);
            }            
            return listaExternos;                       
        }catch(Exception ex){
            System.out.println("Ocorreu algum erro ao listar os servidores ativos " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaExternos; 
    }
    
    public List<ServidorBean> listarTodosAlunosDesativados() throws InstantiationException, IllegalAccessException{
        List<ServidorBean> listaAlunos = new ArrayList();
        
        String sql = "SELECT * FROM Servidor WHERE estadoCadastro=0";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                ServidorBean servidor = new ServidorBean();
                servidor.setId(rs.getInt("id"));
                servidor.setCep(rs.getString("cep"));
                servidor.setCpf(rs.getString("cpf"));
                servidor.setDataNascimento(rs.getDate("dataNascimento"));
                servidor.setEmail(rs.getString("email"));
                servidor.setEstadoCadastro(rs.getByte("estadoCadastro"));
                servidor.setIdCidade(rs.getInt("idCidade"));
                servidor.setNome(rs.getString("nome"));
                servidor.setRua(rs.getString("Rua"));
                servidor.setDataIngresso(rs.getDate("dataIngresso"));
                servidor.setUnidade(rs.getString("unidade"));                  
                listaAlunos.add(servidor);
            }            
            return listaAlunos;                       
        }catch(Exception ex){
            System.out.println("Ocorreu algum erro ao listar os servidores desativados " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaAlunos; 
    }
    
    public void AtualizarDadosServidor(ServidorBean servidor) throws IllegalAccessException, InstantiationException, EditarException{        
        String sql = "UPDATE Servidor SET nome=?, cpf=?, dataNascimento=?, idCidade=?, Rua=?, cep=?, email=?, estadoCadastro=?,"
                        + " dataIngresso=?, unidade=? WHERE id=?";        
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        java.sql.Date data = new java.sql.Date(servidor.getDataNascimento().getTime());
        java.sql.Date dataIngresso = new java.sql.Date(servidor.getDataIngresso().getTime());
        try{        
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1,servidor.getNome());
            stmt.setString(2,servidor.getCpf());
            stmt.setDate(3,data);
            stmt.setInt(4,servidor.getIdCidade());
            stmt.setString(5, servidor.getRua());
            stmt.setString(6, servidor.getCep());
            stmt.setString(7, servidor.getEmail());
            stmt.setByte(8,servidor.getEstadoCadastro());
            stmt.setDate(9,dataIngresso);
            stmt.setString(10,servidor.getUnidade());
            stmt.setInt(11, servidor.getId());
            stmt.execute();
                        
        }catch(Exception ex){
            throw new EditarException("o cadastro do(a) servidor " + servidor.getNome());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }        
    }
    
    public void desativarCadastroServidor(int id) throws InstantiationException, IllegalAccessException, ErroDesativarCadastroException{
        byte desativar = 0;
        String sql = "UPDATE Servidor SET estadoCadastro=? WHERE id=?";        
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

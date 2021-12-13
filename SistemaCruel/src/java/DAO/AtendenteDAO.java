
package DAO;

import BEANS.AtendenteBean;
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

public class AtendenteDAO {
    
    private Connection con = null;
    
    public AtendenteDAO() throws InstantiationException, IllegalAccessException{
        con = ConnectionFactory.getConnection();
    }
    
    public void adicionarAtendente(AtendenteBean atendente) throws InstantiationException, IllegalAccessException, AdicionarException{        
        byte estadoCadastro = 1;        
        String sql = "INSERT INTO Atendente(nome,cpf,dataNascimento,idCidade,Rua,cep,email,estadoCadastro,senha) values"
                + "(?,?,?,?,?,?,?,?,?)";
        con = ConnectionFactory.getConnection();        
        java.sql.Date data = new java.sql.Date(atendente.getDataNascimento().getTime());
        PreparedStatement stmt = null;
        try{
            //Abrindo uma conexão para inserir os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1,atendente.getNome());
            stmt.setString(2,atendente.getCpf());
            stmt.setDate(3,data);
            stmt.setInt(4,atendente.getIdCidade());
            stmt.setString(5,atendente.getRua());
            stmt.setString(6,atendente.getCep());
            stmt.setString(7,atendente.getEmail());
            stmt.setByte(8, estadoCadastro);
            stmt.setString(9, atendente.getSenha());            
            stmt.executeUpdate();
            
        }catch(Exception ex){
            throw new AdicionarException("Atendente");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }              
    }
    
    public AtendenteBean buscarAtendentePorId(int id) throws InstantiationException, IllegalAccessException, ErroUsuarioNaoEncontradoException{
    AtendenteBean atendente = new AtendenteBean();
    PreparedStatement stmt = null;
    String sql = "SELECT * FROM Atendente WHERE id=?";
    con = ConnectionFactory.getConnection();
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            if(rs.next()){ 
                atendente.setId(rs.getInt("id"));
                atendente.setCep(rs.getString("cep"));
                atendente.setCpf(rs.getString("cpf"));
                atendente.setDataNascimento(rs.getDate("dataNascimento"));
                atendente.setEmail(rs.getString("email"));
                atendente.setEstadoCadastro(rs.getByte("estadoCadastro"));
                atendente.setIdCidade(rs.getInt("idCidade"));
                atendente.setNome(rs.getString("nome"));
                atendente.setRua(rs.getString("Rua"));
                atendente.setSenha(rs.getString("senha"));                
            }else{
                throw new Exception();
            }
         return atendente;
        }catch(Exception ex){
            throw new ErroUsuarioNaoEncontradoException("Atendente");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }    
    }
    
    public List<AtendenteBean> buscarAtendentePorCpfEmailNome(String preview) throws InstantiationException, IllegalAccessException, ErroUsuarioNaoEncontradoException{    
    PreparedStatement stmt = null;
    List<AtendenteBean> listaAtendentes = new ArrayList();
    String sql = "SELECT * FROM Atendente WHERE nome LIKE %?% OR cpf LIKE %?% OR email LIKE %?%";
    con = ConnectionFactory.getConnection();
        try{
            AtendenteBean atendente = new AtendenteBean();
            stmt = con.prepareStatement(sql);
            stmt.setString(1,preview);
            stmt.setString(2,preview);
            stmt.setString(3,preview);
            
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            while(rs.next()){ 
                atendente.setId(rs.getInt("id"));
                atendente.setCep(rs.getString("cep"));
                atendente.setCpf(rs.getString("cpf"));
                atendente.setDataNascimento(rs.getDate("dataNascimento"));
                atendente.setEmail(rs.getString("email"));
                atendente.setEstadoCadastro(rs.getByte("estadoCadastro"));
                atendente.setIdCidade(rs.getInt("idCidade"));
                atendente.setNome(rs.getString("nome"));
                atendente.setRua(rs.getString("Rua"));
                atendente.setSenha(rs.getString("senha")); 
                
                listaAtendentes.add(atendente);
            }
         return listaAtendentes;
        }catch(Exception ex){
            System.out.println("Erro ao tentar uasr o sql like");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaAtendentes;
    }
    
    public List<AtendenteBean> listarTodosAtendentesAtivos() throws InstantiationException, IllegalAccessException{
        List<AtendenteBean> listaAtendentes = new ArrayList();
        
        String sql = "SELECT * FROM Atendente WHERE estadoCadastro=1 ORDER BY nome ASC";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                AtendenteBean atendente = new AtendenteBean();
                atendente.setId(rs.getInt("id"));
                atendente.setCep(rs.getString("cep"));
                atendente.setCpf(rs.getString("cpf"));
                atendente.setDataNascimento(rs.getDate("dataNascimento"));
                atendente.setEmail(rs.getString("email"));
                atendente.setEstadoCadastro(rs.getByte("estadoCadastro"));
                atendente.setIdCidade(rs.getInt("idCidade"));
                atendente.setNome(rs.getString("nome"));
                atendente.setRua(rs.getString("Rua"));
                atendente.setSenha(rs.getString("senha"));
                
                listaAtendentes.add(atendente);
            }            
            return listaAtendentes;                       
        }catch(Exception ex){
            System.out.println("Ocorreu algum erro ao listar os atendentes " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaAtendentes; 
    }
    
    public List<AtendenteBean> listarTodosAtendentesDesativados() throws InstantiationException, IllegalAccessException{
        List<AtendenteBean> listaAtendentes = new ArrayList();
        
        String sql = "SELECT * FROM Atendente WHERE estadoCadastro=0";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                AtendenteBean atendente = new AtendenteBean();
                atendente.setId(rs.getInt("id"));
                atendente.setCep(rs.getString("cep"));
                atendente.setCpf(rs.getString("cpf"));
                atendente.setDataNascimento(rs.getDate("dataNascimento"));
                atendente.setEmail(rs.getString("email"));
                atendente.setEstadoCadastro(rs.getByte("estadoCadastro"));
                atendente.setIdCidade(rs.getInt("idCidade"));
                atendente.setNome(rs.getString("nome"));
                atendente.setRua(rs.getString("Rua"));
                atendente.setSenha(rs.getString("senha"));
                
                listaAtendentes.add(atendente);
            }
            
            return listaAtendentes;
            
        }catch(Exception ex){
            System.out.println("Ocorreu algum erro ao listar os atendentes desativados " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaAtendentes; 
    }
    
    public void AtualizarDadosAtendente(AtendenteBean atendente) throws IllegalAccessException, InstantiationException, EditarException{        
        String sql = "UPDATE Atendente SET nome=?, dataNascimento=?, idCidade=?, Rua=?, cep=?, email=?, estadoCadastro=?, "
                        + "senha=? WHERE cpf=?";        
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();       
        java.sql.Date data = new java.sql.Date(atendente.getDataNascimento().getTime());
        try{
        
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1,atendente.getNome());
            stmt.setDate(2,data);
            stmt.setInt(3,atendente.getIdCidade());
            stmt.setString(4, atendente.getRua());
            stmt.setString(5, atendente.getCep());
            stmt.setString(6, atendente.getEmail());
            stmt.setByte(7,atendente.getEstadoCadastro());
            stmt.setString(8,atendente.getSenha());
            stmt.setString(9,atendente.getCpf());
            stmt.execute();
                        
        }catch(Exception ex){
            throw new EditarException("o cadastro do(a) atendente ativados " + atendente.getNome());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }        
    }
    
    public void desativarCadastroAtendente(int id) throws InstantiationException, IllegalAccessException, ErroDesativarCadastroException{
        byte desativar = 0;
        String sql = "UPDATE Atendente SET estadoCadastro=0 WHERE id=?";        
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
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

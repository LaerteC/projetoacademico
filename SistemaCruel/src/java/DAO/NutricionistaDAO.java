
package DAO;

import BEANS.NutricionistaBean;
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

public class NutricionistaDAO {
    
    private Connection con = null;
    
    public NutricionistaDAO() throws InstantiationException, IllegalAccessException{
        con = ConnectionFactory.getConnection();
    }
    
    public void adicionarNutricionista(NutricionistaBean nutricionista) throws InstantiationException, IllegalAccessException, AdicionarException{               
        String sql = "INSERT INTO Nutricionista(nome,cpf,dataNascimento,idCidade,Rua,cep,email,estadoCadastro,senha,crn) values"
                + "(?,?,?,?,?,?,?,?,?,?)";
        con = ConnectionFactory.getConnection();        
        java.sql.Date data = new java.sql.Date(nutricionista.getDataNascimento().getTime());
        PreparedStatement stmt = null;
        try{
            //Abrindo uma conexão para inserir os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1,nutricionista.getNome());
            stmt.setString(2,nutricionista.getCpf());
            stmt.setDate(3,data);
            stmt.setInt(4,nutricionista.getIdCidade());
            stmt.setString(5,nutricionista.getRua());
            stmt.setString(6,nutricionista.getCep());
            stmt.setString(7,nutricionista.getEmail());
            stmt.setByte(8, nutricionista.getEstadoCadastro());
            stmt.setString(9, nutricionista.getSenha());
            stmt.setString(10, nutricionista.getCrm());
            stmt.executeUpdate();
            
        }catch(Exception ex){
            throw new AdicionarException("Nutricionista");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }              
    }
    
    public List<NutricionistaBean> listarTodosNutricionistasDesativos() throws InstantiationException, IllegalAccessException{
        List<NutricionistaBean> listaNutricionistas = new ArrayList();
        
        String sql = "SELECT * FROM Nutricionista WHERE estadoCadastro=0";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                NutricionistaBean nutricionista = new NutricionistaBean();
                nutricionista.setId(rs.getInt("id"));
                nutricionista.setCep(rs.getString("cep"));
                nutricionista.setCpf(rs.getString("cpf"));
                nutricionista.setDataNascimento(rs.getDate("dataNascimento"));
                nutricionista.setEmail(rs.getString("email"));
                nutricionista.setEstadoCadastro(rs.getByte("estadoCadastro"));
                nutricionista.setIdCidade(rs.getInt("idCidade"));
                nutricionista.setNome(rs.getString("nome"));
                nutricionista.setRua(rs.getString("Rua"));
                nutricionista.setSenha(rs.getString("senha"));
                nutricionista.setCrm(rs.getString("crn"));
                
                listaNutricionistas.add(nutricionista);
            }            
            return listaNutricionistas;                       
        }catch(Exception ex){
            System.out.println("Ocorreu algum erro ao listar os nutricionistas ativos " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaNutricionistas; 
    }
    
    public List<NutricionistaBean> listarTodosNutricionistasAtivos() throws InstantiationException, IllegalAccessException{
        List<NutricionistaBean> listaNutricionistas = new ArrayList();
        
        String sql = "SELECT * FROM Nutricionista WHERE estadoCadastro=1 ORDER BY nome ASC";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                NutricionistaBean nutricionista = new NutricionistaBean();
                nutricionista.setId(rs.getInt("id"));
                nutricionista.setCep(rs.getString("cep"));
                nutricionista.setCpf(rs.getString("cpf"));
                nutricionista.setDataNascimento(rs.getDate("dataNascimento"));
                nutricionista.setEmail(rs.getString("email"));
                nutricionista.setEstadoCadastro(rs.getByte("estadoCadastro"));
                nutricionista.setIdCidade(rs.getInt("idCidade"));
                nutricionista.setNome(rs.getString("nome"));
                nutricionista.setRua(rs.getString("Rua"));
                nutricionista.setSenha(rs.getString("senha"));
                nutricionista.setCrm(rs.getString("crn"));
                
                listaNutricionistas.add(nutricionista);
            }            
            return listaNutricionistas;                       
        }catch(Exception ex){
            System.out.println("Ocorreu algum erro ao listar os nutricionistas ativos " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaNutricionistas; 
    }
    
    public NutricionistaBean buscarNutricionistaPorId(int id) throws InstantiationException, IllegalAccessException, ErroUsuarioNaoEncontradoException{
    NutricionistaBean nutricionista = new NutricionistaBean();
    PreparedStatement stmt = null;
    String sql = "SELECT * FROM Nutricionista WHERE id=?";
    con = ConnectionFactory.getConnection();
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            if(rs.next()){ 
                nutricionista.setId(rs.getInt("id"));
                nutricionista.setCep(rs.getString("cep"));
                nutricionista.setCpf(rs.getString("cpf"));
                nutricionista.setDataNascimento(rs.getDate("dataNascimento"));
                nutricionista.setEmail(rs.getString("email"));
                nutricionista.setEstadoCadastro(rs.getByte("estadoCadastro"));
                nutricionista.setIdCidade(rs.getInt("idCidade"));
                nutricionista.setNome(rs.getString("nome"));
                nutricionista.setRua(rs.getString("Rua"));
                nutricionista.setSenha(rs.getString("senha"));
                nutricionista.setCrm(rs.getString("crn"));               
            }else{
                throw new Exception();
            }
         return nutricionista;
        }catch(Exception ex){
            throw new ErroUsuarioNaoEncontradoException("Nutricionista");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }    
    }
    
    public List<NutricionistaBean> buscarNutricionistaPorCpfEmailNome(String preview) throws InstantiationException, IllegalAccessException, ErroUsuarioNaoEncontradoException{    
    PreparedStatement stmt = null;
    List<NutricionistaBean> listaAtendentes = new ArrayList();
    String sql = "SELECT * FROM Nutricionista WHERE nome LIKE %?% OR cpf LIKE %?% OR email LIKE %?%";
    con = ConnectionFactory.getConnection();
        try{
            NutricionistaBean nutricionista = new NutricionistaBean();
            stmt = con.prepareStatement(sql);
            stmt.setString(1,preview);
            stmt.setString(2,preview);
            stmt.setString(3,preview);
            
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            while(rs.next()){ 
                nutricionista.setId(rs.getInt("id"));
                nutricionista.setCep(rs.getString("cep"));
                nutricionista.setCpf(rs.getString("cpf"));
                nutricionista.setDataNascimento(rs.getDate("dataNascimento"));
                nutricionista.setEmail(rs.getString("email"));
                nutricionista.setEstadoCadastro(rs.getByte("estadoCadastro"));
                nutricionista.setIdCidade(rs.getInt("idCidade"));
                nutricionista.setNome(rs.getString("nome"));
                nutricionista.setRua(rs.getString("Rua"));
                nutricionista.setSenha(rs.getString("senha"));
                nutricionista.setCrm(rs.getString("crn"));
                
                listaAtendentes.add(nutricionista);
            }
         return listaAtendentes;
        }catch(Exception ex){
            System.out.println("Erro ao tentar uasr o sql like");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaAtendentes;
    }
    
    public void AtualizarDadosNutricionista(NutricionistaBean nutricionista) throws IllegalAccessException, InstantiationException, EditarException{        
        String sql = "UPDATE Nutricionista SET nome=?, cpf=?, dataNascimento=?, idCidade=?, Rua=?, cep=?, email=?, estadoCadastro=?, "
                        + "senha=?, crn=? WHERE cpf=?";        
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        java.sql.Date data = new java.sql.Date(nutricionista.getDataNascimento().getTime());
        try{        
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1,nutricionista.getNome());
            stmt.setString(2,nutricionista.getCpf());
            stmt.setDate(3,data);
            stmt.setInt(4,nutricionista.getIdCidade());
            stmt.setString(5, nutricionista.getRua());
            stmt.setString(6, nutricionista.getCep());
            stmt.setString(7, nutricionista.getEmail());
            stmt.setByte(8,nutricionista.getEstadoCadastro());
            stmt.setString(9,nutricionista.getSenha());
            stmt.setString(10,nutricionista.getCrm());
            stmt.setString(11,nutricionista.getCpf());
            stmt.execute();
                        
        }catch(Exception ex){
            throw new EditarException("o cadastro do(a) nutricionista " + nutricionista.getNome());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }        
    }
    
    public void desativarCadastroNutricionista(int id) throws InstantiationException, IllegalAccessException, ErroDesativarCadastroException{
        byte desativar = 0;
        String sql = "UPDATE Nutricionista SET estadoCadastro=0 WHERE id=?";      
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
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

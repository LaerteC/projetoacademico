
package DAO;

import BEANS.ExternoBean;
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

public class ExternoDAO {

    private Connection con = null;
    
    public ExternoDAO() throws InstantiationException, IllegalAccessException{
        con = ConnectionFactory.getConnection();
    }
    
    public void adicionarExterno(ExternoBean externo) throws InstantiationException, IllegalAccessException, AdicionarException{               
        String sql = "INSERT INTO Externo(nome,cpf,dataNascimento,idCidade,Rua,cep,email,estadoCadastro,Observacao) values"
                + "(?,?,?,?,?,?,?,?,?)";
        con = ConnectionFactory.getConnection();        
        java.sql.Date dataNascimento = new java.sql.Date(externo.getDataNascimento().getTime());        
        PreparedStatement stmt = null;
        byte estadoCadastro = 1;
        try{
            //Abrindo uma conexão para inserir os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1,externo.getNome());
            stmt.setString(2,externo.getCpf());
            stmt.setDate(3,dataNascimento);
            stmt.setInt(4,externo.getIdCidade());
            stmt.setString(5,externo.getRua());
            stmt.setString(6,externo.getCep());
            stmt.setString(7,externo.getEmail());
            stmt.setByte(8, estadoCadastro);
            stmt.setString(9,externo.getObservacao());
            stmt.executeUpdate();
            
        }catch(Exception ex){
            throw new AdicionarException("Externo");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }              
    }

    public ExternoBean buscarExternoPorCPF(String cpf) throws InstantiationException, IllegalAccessException, ErroUsuarioNaoEncontradoException{
    ExternoBean externo = new ExternoBean();
    PreparedStatement stmt = null;
    String sql = "SELECT * FROM Externo WHERE cpf=?";
    con = ConnectionFactory.getConnection();
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1,cpf);
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            if(rs.next()){ 
                externo.setId(rs.getInt("idAtendente"));
                externo.setCep(rs.getString("cep"));
                externo.setCpf(rs.getString("cpf"));
                externo.setDataNascimento(rs.getDate("dataNascimento"));
                externo.setEmail(rs.getString("email"));
                externo.setEstadoCadastro(rs.getByte("estadoCadastro"));
                externo.setIdCidade(rs.getInt("idCidade"));
                externo.setNome(rs.getString("nome"));
                externo.setRua(rs.getString("Rua"));
                externo.setObservacao(rs.getString("Observacao"));
            }
         return externo;
        }catch(Exception ex){
            throw new ErroUsuarioNaoEncontradoException("Externo");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }    
    }
    
    public ExternoBean buscarExternoPorId(int id) throws InstantiationException, IllegalAccessException, ErroUsuarioNaoEncontradoException{
    ExternoBean servidor = new ExternoBean();
    PreparedStatement stmt = null;
    String sql = "SELECT * FROM Externo WHERE id=?";
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
                servidor.setObservacao(rs.getString("Observacao"));
            }else{
                throw new Exception();
            }
         return servidor;
        }catch(Exception ex){
            throw new ErroUsuarioNaoEncontradoException("Externo");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }    
    }
    
    public List<ExternoBean> buscarExternoPorCpfEmailNome(String preview) throws InstantiationException, IllegalAccessException, ErroUsuarioNaoEncontradoException{    
    PreparedStatement stmt = null;
    List<ExternoBean> listaExternos = new ArrayList();
    String sql = "SELECT * FROM Externo WHERE nome LIKE %?% OR cpf LIKE %?% OR email LIKE %?%";
    con = ConnectionFactory.getConnection();
        try{
            ExternoBean externo = new ExternoBean();
            stmt = con.prepareStatement(sql);
            stmt.setString(1,preview);
            stmt.setString(2,preview);
            stmt.setString(3,preview);
            
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            while(rs.next()){ 
                externo.setId(rs.getInt("id"));
                externo.setCep(rs.getString("cep"));
                externo.setCpf(rs.getString("cpf"));
                externo.setDataNascimento(rs.getDate("dataNascimento"));
                externo.setEmail(rs.getString("email"));
                externo.setEstadoCadastro(rs.getByte("estadoCadastro"));
                externo.setIdCidade(rs.getInt("idCidade"));
                externo.setNome(rs.getString("nome"));
                externo.setRua(rs.getString("Rua"));
                externo.setObservacao(rs.getString("Observacao"));            
                listaExternos.add(externo);
            }
         return listaExternos;
        }catch(Exception ex){
            System.out.println("Erro ao tentar usar o sql like");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaExternos;
    }
    
    public List<ExternoBean> listarTodosExternosAtivos() throws InstantiationException, IllegalAccessException{
        List<ExternoBean> listaExternos = new ArrayList();
        
        String sql = "SELECT * FROM Externo WHERE estadoCadastro=1 ORDER BY nome ASC";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                ExternoBean externo = new ExternoBean();
                externo.setId(rs.getInt("id"));
                externo.setCep(rs.getString("cep"));
                externo.setCpf(rs.getString("cpf"));
                externo.setDataNascimento(rs.getDate("dataNascimento"));
                externo.setEmail(rs.getString("email"));
                externo.setEstadoCadastro(rs.getByte("estadoCadastro"));
                externo.setIdCidade(rs.getInt("idCidade"));
                externo.setNome(rs.getString("nome"));
                externo.setRua(rs.getString("Rua"));
                externo.setObservacao(rs.getString("Observacao"));                 
                listaExternos.add(externo);
            }            
            return listaExternos;                       
        }catch(Exception ex){
            System.out.println("Ocorreu algum erro ao listar os externos ativos " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaExternos; 
    }
    
        public List<ExternoBean> listarTodosExternosInativos() throws InstantiationException, IllegalAccessException{
        List<ExternoBean> listaExternos = new ArrayList();
        
        String sql = "SELECT * FROM Externo WHERE estadoCadastro=0";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                ExternoBean externo = new ExternoBean();
                externo.setId(rs.getInt("id"));
                externo.setCep(rs.getString("cep"));
                externo.setCpf(rs.getString("cpf"));
                externo.setDataNascimento(rs.getDate("dataNascimento"));
                externo.setEmail(rs.getString("email"));
                externo.setEstadoCadastro(rs.getByte("estadoCadastro"));
                externo.setIdCidade(rs.getInt("idCidade"));
                externo.setNome(rs.getString("nome"));
                externo.setRua(rs.getString("Rua"));
                externo.setObservacao(rs.getString("Observacao"));                 
                listaExternos.add(externo);
            }            
            return listaExternos;                       
        }catch(Exception ex){
            System.out.println("Ocorreu algum erro ao listar os externos inativos " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaExternos; 
    }
        
    public void AtualizarDadosExterno(ExternoBean externo) throws IllegalAccessException, InstantiationException, EditarException{        
        String sql = "UPDATE Externo SET nome=?, cpf=?, dataNascimento=?, idCidade=?, Rua=?, cep=?, email=?, estadoCadastro=?,"
                        + " Observacao=? WHERE id=?";        
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        java.sql.Date data = new java.sql.Date(externo.getDataNascimento().getTime());
        try{        
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1,externo.getNome());
            stmt.setString(2,externo.getCpf());
            stmt.setDate(3,data);
            stmt.setInt(4,externo.getIdCidade());
            stmt.setString(5, externo.getRua());
            stmt.setString(6, externo.getCep());
            stmt.setString(7, externo.getEmail());
            stmt.setByte(8,externo.getEstadoCadastro());
            stmt.setString(9,externo.getObservacao());
            stmt.setInt(10, externo.getId());
            stmt.execute();
                        
        }catch(Exception ex){
            throw new EditarException("o cadastro do(a) externo " + externo.getNome());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }        
    }
    
    public void desativarCadastroExterno(int id) throws InstantiationException, IllegalAccessException, ErroDesativarCadastroException{
        byte desativar = 0;
        String sql = "UPDATE Externo SET estadoCadastro=? WHERE id=?";        
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


package DAO;

import BEANS.GerenteBean;
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

public class GerenteDAO {
    
    //DAO
    CidadeDAO cidadeDAO = new CidadeDAO();
    
    private Connection con = null;
    
    public GerenteDAO() throws InstantiationException, IllegalAccessException{
      con = ConnectionFactory.getConnection();
    }
    
    public void adicionarGerente(GerenteBean gerente) throws InstantiationException, IllegalAccessException, AdicionarException{               
        String sql = "INSERT INTO Gerente(nome,cpf,dataNascimento,idCidade,Rua,cep,email,estadoCadastro,senha) values"
                + "(?,?,?,?,?,?,?,?,?)";
        con = ConnectionFactory.getConnection();        
        java.sql.Date data = new java.sql.Date(gerente.getDataNascimento().getTime());
        PreparedStatement stmt = null;
        try{
            //Abrindo uma conexão para inserir os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1,gerente.getNome());
            stmt.setString(2,gerente.getCpf());
            stmt.setDate(3,data);
            stmt.setInt(4,gerente.getIdCidade());
            stmt.setString(5,gerente.getRua());
            stmt.setString(6,gerente.getCep());
            stmt.setString(7,gerente.getEmail());
            stmt.setByte(8, gerente.getEstadoCadastro());
            stmt.setString(9, gerente.getSenha());
            stmt.executeUpdate();
            
        }catch(Exception ex){
            throw new AdicionarException("Gerente");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }              
    }
    
    public GerenteBean buscarGerentePorId(int id) throws InstantiationException, IllegalAccessException, ErroUsuarioNaoEncontradoException{
    GerenteBean gerente = new GerenteBean();
    PreparedStatement stmt = null;
    String sql = "SELECT * FROM Gerente WHERE id=?";
    con = ConnectionFactory.getConnection();
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            if(rs.next()){ 
                gerente.setId(rs.getInt("id"));
                gerente.setCep(rs.getString("cep"));
                gerente.setCpf(rs.getString("cpf"));
                gerente.setDataNascimento(rs.getDate("dataNascimento"));
                gerente.setEmail(rs.getString("email"));
                gerente.setEstadoCadastro(rs.getByte("estadoCadastro"));
                gerente.setIdCidade(rs.getInt("idCidade"));
                gerente.setNome(rs.getString("nome"));
                gerente.setRua(rs.getString("Rua"));
                gerente.setSenha(rs.getString("senha"));
                gerente.setCidade(cidadeDAO.buscarCidadePorId(gerente.getIdCidade()));
            }else{
                throw new Exception();
            }
         return gerente;
        }catch(Exception ex){
            throw new ErroUsuarioNaoEncontradoException("Gerente");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }    
    }
    
    public List<GerenteBean> buscarGerentePorCpfEmailNome(String preview) throws InstantiationException, IllegalAccessException, ErroUsuarioNaoEncontradoException{    
    PreparedStatement stmt = null;
    List<GerenteBean> listaGerentes = new ArrayList();
    String sql = "SELECT * FROM Gerente WHERE nome LIKE %?% OR cpf LIKE %?% OR email LIKE %?%";
    con = ConnectionFactory.getConnection();
        try{
            GerenteBean gerente = new GerenteBean();
            stmt = con.prepareStatement(sql);
            stmt.setString(1,preview);
            stmt.setString(2,preview);
            stmt.setString(3,preview);
            
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            while(rs.next()){ 
                gerente.setId(rs.getInt("id"));
                gerente.setCep(rs.getString("cep"));
                gerente.setCpf(rs.getString("cpf"));
                gerente.setDataNascimento(rs.getDate("dataNascimento"));
                gerente.setEmail(rs.getString("email"));
                gerente.setEstadoCadastro(rs.getByte("estadoCadastro"));
                gerente.setIdCidade(rs.getInt("idCidade"));
                gerente.setNome(rs.getString("nome"));
                gerente.setRua(rs.getString("Rua"));
                gerente.setSenha(rs.getString("senha"));
                gerente.setCidade(cidadeDAO.buscarCidadePorId(gerente.getIdCidade()));
                
                listaGerentes.add(gerente);
            }
         return listaGerentes;
        }catch(Exception ex){
            System.out.println("Erro ao tentar uasr o sql like");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaGerentes;
    }
    
    public List<GerenteBean> listarTodosGerentesAtivos() throws InstantiationException, IllegalAccessException{
        List<GerenteBean> listaGerentes = new ArrayList();
        
        String sql = "SELECT * FROM Gerente WHERE estadoCadastro=1 ORDER BY nome ASC";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                GerenteBean gerente = new GerenteBean();
                gerente.setId(rs.getInt("id"));
                gerente.setCep(rs.getString("cep"));
                gerente.setCpf(rs.getString("cpf"));
                gerente.setDataNascimento(rs.getDate("dataNascimento"));
                gerente.setEmail(rs.getString("email"));
                gerente.setEstadoCadastro(rs.getByte("estadoCadastro"));
                gerente.setIdCidade(rs.getInt("idCidade"));
                gerente.setNome(rs.getString("nome"));
                gerente.setRua(rs.getString("Rua"));
                gerente.setSenha(rs.getString("senha"));
                gerente.setCidade(cidadeDAO.buscarCidadePorId(gerente.getIdCidade()));
                
                listaGerentes.add(gerente);
            }            
            return listaGerentes;                       
        }catch(Exception ex){
            System.out.println("Ocorreu algum erro ao listar os gerentes ativos " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaGerentes; 
    }
    
        public List<GerenteBean> listarTodosUsuarios() throws InstantiationException, IllegalAccessException{
        
        //List<String> listaCPFRegistros = listarTodosUsuariosRegistrados();    
            
        List<GerenteBean> listaGerentes = new ArrayList();
        
        //
        
        
        List<GerenteBean> listaParaEnviar = new ArrayList();
        
        String sql = "select nome,cpf, Servidor as 'Tipo' from( " +
            "select nome,cpf,'Servidor' from Servidor " +
            "union " +
            "select nome,cpf,'Professor' from Professor " +
            "union " +
            "select nome,cpf,'Externo' from Externo " +
            "union " +
            "select nome,cpf,'Aluno' from Aluno ) as T order by nome asc";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                GerenteBean gerente = new GerenteBean();
                gerente.setCpf(rs.getString("cpf"));
                gerente.setNome(rs.getString("nome"));
                gerente.setTipo(rs.getString("Tipo"));
                listaGerentes.add(gerente);
            }
            
            /*//For para percorrer percorridamente as vareaveis
            for(String cpf : listaCPFRegistros){
                for(GerenteBean validando : listaGerentes){
                    if(cpf.equalsIgnoreCase(validando.getCpf())){
                        listaParaEnviar.add(validando);
                    }
                }
            }*/
            
            return listaGerentes;                       
        }catch(Exception ex){
            System.out.println("Ocorreu algum erro ao listar os gerentes ativos " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaParaEnviar; 
    }
    
        private List<String> listarTodosUsuariosRegistrados() throws InstantiationException, IllegalAccessException{
        List<String> listaCPFRegistros = new ArrayList();
        
        String sql = "select distinct cpf from Registro";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                String cpf = "";
                cpf = rs.getString("cpf");
                
                listaCPFRegistros.add(cpf);
            }            
            return listaCPFRegistros;                       
        }catch(Exception ex){
            System.out.println("Ocorreu algum erro ao listar os gerentes ativos " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaCPFRegistros; 
    }
        
    public List<GerenteBean> listarTodosGerentesDesativados() throws InstantiationException, IllegalAccessException{
        List<GerenteBean> listaGerentes = new ArrayList();
        
        String sql = "SELECT * FROM Gerente WHERE estadoCadastro=0";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                GerenteBean gerente = new GerenteBean();
                gerente.setId(rs.getInt("id"));
                gerente.setCep(rs.getString("cep"));
                gerente.setCpf(rs.getString("cpf"));
                gerente.setDataNascimento(rs.getDate("dataNascimento"));
                gerente.setEmail(rs.getString("email"));
                gerente.setEstadoCadastro(rs.getByte("estadoCadastro"));
                gerente.setIdCidade(rs.getInt("idCidade"));
                gerente.setNome(rs.getString("nome"));
                gerente.setRua(rs.getString("Rua"));
                gerente.setSenha(rs.getString("senha"));
                gerente.setCidade(cidadeDAO.buscarCidadePorId(gerente.getIdCidade()));
                
                listaGerentes.add(gerente);
            }            
            return listaGerentes;                       
        }catch(Exception ex){
            System.out.println("Ocorreu algum erro ao listar os gerentes ativos " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaGerentes; 
    }
    
    public void AtualizarDadosGerente(GerenteBean gerente) throws IllegalAccessException, InstantiationException, EditarException{        
        String sql = "UPDATE Gerente SET nome=?, cpf=?, dataNascimento=?, idCidade=?, Rua=?, cep=?, email=?, estadoCadastro=?, "
                        + "senha=? WHERE cpf=?";        
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        java.sql.Date data = new java.sql.Date(gerente.getDataNascimento().getTime());
        try{        
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1,gerente.getNome());
            stmt.setString(2,gerente.getCpf());
            stmt.setDate(3,data);
            stmt.setInt(4,gerente.getIdCidade());
            stmt.setString(5, gerente.getRua());
            stmt.setString(6, gerente.getCep());
            stmt.setString(7, gerente.getEmail());
            stmt.setByte(8,gerente.getEstadoCadastro());
            stmt.setString(9,gerente.getSenha());
            stmt.setString(10,gerente.getCpf());
            stmt.execute();
                        
        }catch(Exception ex){
            throw new EditarException("o cadastro do(a) gerente " + gerente.getNome());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }        
    }
    
    public void desativarCadastroGerente(int id) throws InstantiationException, IllegalAccessException, ErroDesativarCadastroException{
        String sql = "UPDATE Gerente SET estadoCadastro=0 WHERE id=?";        
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
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
            ResultSet rs = stmt.executeQuery();            
            //Se existir um CPF cadastrado como o passado ele vai passar pelo IF
            while(rs.next()){
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
            ResultSet rs = stmt.executeQuery();            
            //Se existir um EMAIL cadastrado como o passado. Ele vai passar pelo IF e retornar a exceção
            while(rs.next()){
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

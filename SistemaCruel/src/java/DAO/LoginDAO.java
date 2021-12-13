
package DAO;

import BEANS.LoginBean;
import DAO.ConnectionFactory;
import EXCEPTION.LoginException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
    
    private Connection con = null;
    
    public LoginDAO() throws InstantiationException, IllegalAccessException{
        con = ConnectionFactory.getConnection();
    }
    
    public LoginBean validarLoginAtendente(String email, String senha) throws InstantiationException, IllegalAccessException{
        LoginBean login = new LoginBean();
        String sql = "SELECT * FROM Atendente WHERE email=? and senha=?";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1,email);
            stmt.setString(2,senha);
            //stmt.setString(2,this.encriptografar(senha));
            ResultSet rs = stmt.executeQuery();
            //Percorrendo os dados no banco para verificar se encontra o usuario
            if(rs.next()){                            
                login.setId(rs.getInt("id"));
                login.setLogin(rs.getString("nome"));
                login.setSenha(rs.getString("senha"));
                return login;
            }          
        }catch(Exception ex){
            System.out.println("Ocorreu um erro LOGINDAO -> Validar Atendente -> " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
     return login;        
    }
    
    public LoginBean validarLoginNutricionista(String email, String senha) throws InstantiationException, IllegalAccessException{
        LoginBean login = new LoginBean();
        String sql = "SELECT * FROM Nutricionista WHERE email=? and senha=?";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1,email);
            stmt.setString(2,senha);
            ResultSet rs = stmt.executeQuery();
            //Percorrendo os dados no banco para verificar se encontra o usuario
            if(rs.next()){                            
                login.setId(rs.getInt("id"));
                login.setLogin(rs.getString("nome"));
                login.setSenha(rs.getString("senha"));
                return login;
            }            
        }catch(Exception ex){
            System.out.println("Ocorreu um erro LOGINDAO -> Validar Nutri -> " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
     return login;
    }
    
    public LoginBean validarLoginGerente(String email, String senha) throws InstantiationException, IllegalAccessException{
        LoginBean login = new LoginBean();
        String sql = "SELECT * FROM Gerente WHERE email=? and senha=?";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1,email);
            stmt.setString(2,senha);
            ResultSet rs = stmt.executeQuery();
            //Percorrendo os dados no banco para verificar se encontra o usuario
            if(rs.next()){                            
                login.setId(rs.getInt("id"));
                login.setLogin(rs.getString("nome"));
                login.setSenha(rs.getString("senha"));
                return login;
            }          
        }catch(Exception ex){
            System.out.println("Ocorreu um erro LOGINDAO -> Validar Gerente -> " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
     return login;
    }
    
    private String encriptografar(String senha) throws Exception{
        String retorno = "";
        MessageDigest md;
        try{
            md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1,md.digest(senha.getBytes()));
            retorno = hash.toString(16);
        }catch(Exception ex){
            throw ex;
        }
        
        return retorno;
    }
    
}

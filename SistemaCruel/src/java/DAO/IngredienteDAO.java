package DAO;

import BEANS.IngredienteBean;
import EXCEPTION.AdicionarException;
import EXCEPTION.EditarException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IngredienteDAO {
 
    private Connection con = null;
    
    public IngredienteDAO() throws InstantiationException, IllegalAccessException{
        con = ConnectionFactory.getConnection();
    }
    
        public List<IngredienteBean> listarIngredientesPorTipoIngrediente(int id) throws InstantiationException, IllegalAccessException{
        List<IngredienteBean> listaIngredientes = new ArrayList();
        
        String sql = "SELECT * FROM Ingrediente WHERE id=?";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                IngredienteBean ingrediente = new IngredienteBean();
                ingrediente.setIdIngrediente(rs.getInt("id"));
                ingrediente.setNomeIngrediente(rs.getString("nome"));
                ingrediente.setIdTipoIngrediente(rs.getInt("idTipoIngrediente")); 
                
                listaIngredientes.add(ingrediente);
            }            
            return listaIngredientes;                       
        }catch(Exception ex){
            System.out.println("Ocorreu algum erro ao listar as cidades " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaIngredientes; 
    }
    
    public String retornarNomeIngrediente(int id) throws InstantiationException, IllegalAccessException{
        String nome = null;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM Ingrediente where id=?";
        con = ConnectionFactory.getConnection();
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            if(rs.next()){ 
                nome = rs.getString("nome");
            }
         return nome;
        }catch(Exception ex){
            System.out.println("Não foi possível buscar o ingrediente " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        } 
        return nome;        
    }
        
    public IngredienteBean buscarIngredientePorId(int id) throws InstantiationException, IllegalAccessException{
    IngredienteBean ingrediente = new IngredienteBean();
    PreparedStatement stmt = null;
    String sql = "SELECT * FROM Ingrediente";
    con = ConnectionFactory.getConnection();
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            if(rs.next()){ 
                ingrediente.setIdIngrediente(rs.getInt("id"));
                ingrediente.setNomeIngrediente(rs.getString("nome"));
                ingrediente.setIdTipoIngrediente(rs.getInt("idTipoIngrediente"));                
            }
         return ingrediente;
        }catch(Exception ex){
            System.out.println("Não foi possível buscar o ingrediente " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }    
        return ingrediente;
    }
    
    public List<IngredienteBean> listarIngredientes() throws InstantiationException, IllegalAccessException {
       
        List<IngredienteBean> listaIngredientes = new ArrayList();

        String sql = "SELECT * FROM Ingrediente";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();
        try {
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                IngredienteBean ingrediente = new IngredienteBean();
                ingrediente.setIdIngrediente(rs.getInt("id"));
                ingrediente.setNomeIngrediente(rs.getString("nome"));
                ingrediente.setIdTipoIngrediente(rs.getInt("idTipoIngrediente"));

                listaIngredientes.add(ingrediente);
            }
            return listaIngredientes;
        } catch (Exception ex) {
            System.out.println("Ocorreu algum erro ao listar Ingredientes " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaIngredientes;
    }
    
    public void adicionarIngrediente(IngredienteBean ingrediente) throws InstantiationException, IllegalAccessException, AdicionarException {
       
        String sql = "INSERT INTO ingrediente(nome,idTipoIngrediente) values"
                + "(?,?)";
        con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            //Abrindo uma conexão para inserir os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1, ingrediente.getNomeIngrediente());
            stmt.setInt(2, ingrediente.getIdTipoIngrediente());

            stmt.executeUpdate();

        } catch (Exception ex) {
            throw new AdicionarException("Ingrediente error !!!");
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void atualizarDadosIngrediente(IngredienteBean ingrediente) throws IllegalAccessException, InstantiationException, EditarException {
        
        String sql = "UPDATE ingrediente SET nome=?,idTipoIngrediente=?"
                + " WHERE id=?";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();

        try {

            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1, ingrediente.getNomeIngrediente());
            stmt.setInt(2, ingrediente.getIdTipoIngrediente());
            stmt.setInt(3, ingrediente.getIdIngrediente());

            stmt.execute();

        } catch (Exception ex) {
            throw new EditarException("Ocorreu um erro ao adicionar o Ingrediente " + ingrediente.getNomeIngrediente());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void deletarIngrediente(int id) throws InstantiationException, IllegalAccessException {
       
        String sql = "delete from ingrediente WHERE id=?";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception ex) {
             System.out.println("Não foi possível deletar o ingrediente " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
}

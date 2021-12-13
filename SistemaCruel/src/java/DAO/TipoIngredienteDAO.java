
package DAO;

import BEANS.TipoIngredienteBean;
import EXCEPTION.AdicionarException;
import EXCEPTION.EditarException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TipoIngredienteDAO {
    
    private Connection con = null;
    
    public TipoIngredienteDAO() throws InstantiationException, IllegalAccessException{
        con = ConnectionFactory.getConnection();
    }
    
        public List<TipoIngredienteBean> listarTodosTiposIngredientes() throws InstantiationException, IllegalAccessException{
        List<TipoIngredienteBean> listaTiposIngredientes = new ArrayList();
        
        String sql = "SELECT * FROM TipoIngrediente";
        
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                TipoIngredienteBean tipoIngrediente = new TipoIngredienteBean();
                tipoIngrediente.setIdTipoIngrediente(rs.getInt("id"));
                tipoIngrediente.setNomeTipoIngrediente(rs.getString("nome"));
                
                listaTiposIngredientes.add(tipoIngrediente);
            }            
            return listaTiposIngredientes;                       
        }catch(Exception ex){
            System.out.println("Ocorreu algum erro ao listar os tipos de ingredientes " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaTiposIngredientes; 
    }
    
    public TipoIngredienteBean buscarCidadePorId(int id) throws InstantiationException, IllegalAccessException{
    TipoIngredienteBean tipoIngrediente = new TipoIngredienteBean();
    PreparedStatement stmt = null;
    String sql = "SELECT * FROM TipoIngrediente WHERE id=?";
    con = ConnectionFactory.getConnection();
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            if(rs.next()){ 
                tipoIngrediente.setIdTipoIngrediente(rs.getInt("id"));
                tipoIngrediente.setNomeTipoIngrediente(rs.getString("nome"));
            }
         return tipoIngrediente;
        }catch(Exception ex){
            System.out.println("Não foi possível buscar o tipo do ingrediente " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }    
        return tipoIngrediente;
    }
    
    public TipoIngredienteBean buscarTipoIngredientePorId(int id) throws InstantiationException, IllegalAccessException {
        TipoIngredienteBean tipo = new TipoIngredienteBean();
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM TipoIngrediente WHERE id=?";
        con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            //Percorrendo os dados no banco e preenchendo a lista.
            if (rs.next()) {
                tipo.setIdTipoIngrediente(rs.getInt("id"));
                tipo.setNomeTipoIngrediente(rs.getString("nome"));

            }
            return tipo;
        } catch (Exception ex) {
            System.out.println("Não foi possível buscar o ingrediente " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return tipo;
    }

    public void atualizarDadosTipoIngrediente(TipoIngredienteBean tipo) throws IllegalAccessException, InstantiationException, EditarException {

        String sql = "UPDATE TipoIngrediente SET nome=?"
                + " WHERE id=?";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();

        try {

            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1, tipo.getNomeTipoIngrediente());
            stmt.setInt(2, tipo.getIdTipoIngrediente());

            stmt.execute();

        } catch (Exception ex) {
            throw new EditarException("Ocorreu um erro ao adicionar o Tipo de Ingrediente " + tipo.getNomeTipoIngrediente());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public void adicionarTipoIngrediente(TipoIngredienteBean tipo) throws InstantiationException, IllegalAccessException, AdicionarException {

        String sql = "INSERT INTO TipoIngrediente(nome) values"
                + " (?)";
        con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            //Abrindo uma conexão para inserir os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1, tipo.getNomeTipoIngrediente());

            stmt.executeUpdate();

        } catch (Exception ex) {
             System.out.println("Não foi possível buscar o ingrediente " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public void deletarTipoIngrediente(int id) throws InstantiationException, IllegalAccessException {

        String sql = "delete from TipoIngrediente WHERE id=?";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception ex) {
             System.out.println("Não foi possível buscar o ingrediente " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
}

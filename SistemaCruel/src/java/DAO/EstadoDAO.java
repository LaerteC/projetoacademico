
package DAO;

import BEANS.EstadoBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO {
    
    private Connection con = null;
    
    public EstadoDAO() throws InstantiationException, IllegalAccessException{
        con = ConnectionFactory.getConnection();
    }
    
     public List<EstadoBean> listarTodosEstados() throws InstantiationException, IllegalAccessException{
        List<EstadoBean> listaEstados = new ArrayList();
        
        String sql = "SELECT * FROM Estado";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                EstadoBean estado = new EstadoBean();
                estado.setIdEstado(rs.getInt("id"));
                estado.setNomeEstado(rs.getString("nome"));
                
                listaEstados.add(estado);
            }            
            return listaEstados;                       
        }catch(Exception ex){
            System.out.println("Ocorreu algum erro ao listar os estados " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaEstados; 
    }
    
    public EstadoBean buscarCidadePorId(int id) throws InstantiationException, IllegalAccessException{
    EstadoBean estado = new EstadoBean();
    PreparedStatement stmt = null;
    String sql = "SELECT * FROM Estado ";
    con = ConnectionFactory.getConnection();
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            if(rs.next()){ 
                estado.setIdEstado(rs.getInt("id"));
                estado.setNomeEstado(rs.getString("nome"));               
            }
         return estado;
        }catch(Exception ex){
            System.out.println("Não foi possível buscar o estado " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }    
        return estado;
    }
    
}

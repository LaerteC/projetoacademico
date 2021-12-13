
package DAO;

import BEANS.CidadeBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAO {

    private Connection con = null;
    
    public CidadeDAO() throws InstantiationException, IllegalAccessException{
        con = ConnectionFactory.getConnection();
    }
    
    public List<CidadeBean> listarTodasCidadesPorEstado(int id) throws InstantiationException, IllegalAccessException{
        List<CidadeBean> listaCidades = new ArrayList();
        
        String sql = "SELECT * FROM Cidade where idEstado=?";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                CidadeBean cidade = new CidadeBean();
                cidade.setIdCidade(rs.getInt("id"));
                cidade.setNomeCidade(rs.getString("nome"));
                cidade.setIdEstado(rs.getInt("idEstado"));
                
                listaCidades.add(cidade);
            }            
            return listaCidades;                       
        }catch(Exception ex){
            System.out.println("Ocorreu algum erro ao listar as cidades " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaCidades; 
    }
    
    public CidadeBean buscarCidadePorId(int id) throws InstantiationException, IllegalAccessException{
    CidadeBean cidade = new CidadeBean();
    PreparedStatement stmt = null;
    String sql = "SELECT * FROM Cidade WHERE id=?";
    con = ConnectionFactory.getConnection();
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();            
            //Percorrendo os dados no banco e preenchendo a lista.
            if(rs.next()){ 
                cidade.setIdCidade(rs.getInt("id"));
                cidade.setNomeCidade(rs.getString("nome"));
                cidade.setIdEstado(rs.getInt("idEstado"));                
            }
         return cidade;
        }catch(Exception ex){
            System.out.println("Não foi possível buscar a cidade " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }    
        return cidade;
    }
    
}

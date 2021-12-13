
package DAO;

import BEANS.JantaBean;
import EXCEPTION.AdicionarException;
import EXCEPTION.ErroCarneExistenteException;
import EXCEPTION.ErroSobremesaExistenteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class JantaDAO {

    private Connection con = null;
    
    public JantaDAO() throws InstantiationException, IllegalAccessException{
        con = ConnectionFactory.getConnection();
    }
    
    public void adicionarJanta(JantaBean janta) throws InstantiationException, IllegalAccessException, AdicionarException{               
        String sql = "INSERT INTO Janta(idArroz,idFeijao,idAcompanhamento,idSalada,idSobremesa,qtdeArroz,qtdeAcompanhamento,qtdeSalada,qtdeSobremesa) values"
                + "(?,?,?,?,?,?,?,?,?)";
        con = ConnectionFactory.getConnection();        
        PreparedStatement stmt = null;
        try{
            //Abrindo uma conexão para inserir os dados
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,janta.getIdArroz());
            stmt.setInt(2,janta.getIdFeijao());
            stmt.setInt(3,janta.getIdAcompanhamento());
            stmt.setInt(4,janta.getIdSalada());
            stmt.setInt(5,janta.getIdSobremesa());
            stmt.setDouble(6, janta.getQtdeArroz());
            stmt.setDouble(7,janta.getQtdeAcompanhamento());
            stmt.setDouble(8,janta.getQtdeSalada());
            stmt.setDouble(9, janta.getQtdeSobremesa());
            stmt.executeUpdate();
            
        }catch(Exception ex){
            throw new AdicionarException("Janta");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }              
    }
    
    public JantaBean buscarAlmocoPorId(int id) throws InstantiationException, IllegalAccessException{        
        String sql = "SELECT * FROM Janta WHERE id=?";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();  
        JantaBean janta = new JantaBean();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){ 
                janta.setIdAcompanhamento(rs.getInt("idAcompanhamento"));
                janta.setIdJanta(rs.getInt("id"));
                janta.setIdArroz(rs.getInt("idArroz"));
                janta.setIdFeijao(rs.getInt("idFeijao"));
                janta.setIdSalada(rs.getInt("idSalada"));
                janta.setIdSobremesa(rs.getInt("idSobremesa"));
                janta.setQtdeAcompanhamento(rs.getDouble("qtdeAcompanhamento"));
                janta.setQtdeArroz(rs.getDouble("qtdeArroz"));
                janta.setQtdeFeijao(rs.getDouble("qtdeFeijao"));
                janta.setQtdeSalada(rs.getDouble("qtdeSalada"));
                janta.setQtdeSobremesa(rs.getDouble("qtdeSobremesa"));
            }
            return janta;
        }catch(Exception ex){
            System.out.println("Ocorreu um erro durante a busca do objeto janta" + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }        
        return janta;
    }
    
    public void validarJantar(int carne, int sobremesa, Date data) throws InstantiationException, IllegalAccessException, Exception {

        int verificaCarne = 0;
        int verificaSobremesa = 0;

        String sql = "SELECT J.idAcompanhamento as 'acompanhamento', J.idSobremesa as 'sobremesa' FROM Cardapio C"
                + " INNER JOIN Almoco A ON C.idAlmoco = A.id"
                + " INNER JOIN Janta J ON C.idJanta = J.id"
                + " WHERE Day(dataHora) = Day(?);";

        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();
        java.sql.Date dataFormatada = new java.sql.Date(data.getTime());
        try {
            stmt = con.prepareStatement(sql);
            stmt.setDate(1, dataFormatada);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                verificaCarne = rs.getInt("acompanhamento");
                verificaSobremesa = rs.getInt("sobremesa");

                if (verificaCarne == carne) {

                    throw new ErroCarneExistenteException("Jantar");
                }

                if (verificaSobremesa == sobremesa) {

                    throw new ErroSobremesaExistenteException("Jantar");
                }
            }

        } catch (Exception ex) {
            throw ex;
        }

    }
    
}

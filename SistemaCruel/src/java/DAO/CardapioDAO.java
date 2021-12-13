
package DAO;

import BEANS.CardapioBean;
import EXCEPTION.AdicionarException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CardapioDAO {
    
    private Connection con = null;
    
    public CardapioDAO() throws InstantiationException, IllegalAccessException{
        con = ConnectionFactory.getConnection();
    }
    
    public void adicionarCardapio(CardapioBean cardapio) throws InstantiationException, IllegalAccessException, AdicionarException{               
        String sql = "INSERT INTO Cardapio(id,idAlmoco,idJanta,idNutricionista) values (?,?,?,?)";
        con = ConnectionFactory.getConnection();
        int id = verificaUltimoId();
        PreparedStatement stmt = null;
        try{
            //Abrindo uma conexão para inserir os dados
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.setInt(2,cardapio.getIdAlmoco());
            stmt.setInt(3,cardapio.getIdJanta());
            stmt.setInt(4,cardapio.getIdNutricionista());
            stmt.executeUpdate();
            
        }catch(Exception ex){
            throw new AdicionarException("Cardapio");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }              
    }
    
    public CardapioBean buscarCardapioDoDia(java.util.Date date) throws InstantiationException, IllegalAccessException{
    CardapioBean cardapio = new CardapioBean();
    java.sql.Date data = new java.sql.Date(date.getTime());
    PreparedStatement stmt = null;
    con = ConnectionFactory.getConnection();        
    String sql = "SELECT * FROM Cardapio WHERE CAST(diaDoCardapio AS date) =?";
        
        try{                   
            stmt = con.prepareStatement(sql);
            stmt.setDate(1,data);
            ResultSet rs = stmt.executeQuery(); 
            
            if(rs.next()){
                cardapio.setIdAlmoco(rs.getInt("idAlmoco"));
                cardapio.setIdCardapio(rs.getInt("id"));
                cardapio.setIdJanta(rs.getInt("idJanta"));
                cardapio.setIdNutricionista(rs.getInt("idNutricionista"));
            }
            
            return cardapio;
            
        }catch(Exception ex){                
            System.out.println("Ocorreu um erro " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return cardapio;
    }
    
    private int verificaUltimoId() throws InstantiationException, IllegalAccessException{
    int contador = 1;
    String sql = "SELECT * FROM Cardapio";
    PreparedStatement stmt = null;
    con = ConnectionFactory.getConnection();
    try{
       stmt = con.prepareStatement(sql);
       ResultSet rs = stmt.executeQuery();      
    while(rs.next()){
        contador++;
    }
    return contador;
    }catch(Exception ex){
        System.out.println("Erro ao tentar pegar o ID");
    }finally{
        ConnectionFactory.closeConnection(con, stmt);
    }
    return contador;
    }
        
    public String formarData(String data, int dias) {
        String DataAlterada = "";
        String FormatoDaData = "dd-MM-yyyy";
        try {
            SimpleDateFormat format = new SimpleDateFormat(FormatoDaData);
            java.sql.Date Data = new java.sql.Date(format.parse(data).getTime());
            Calendar ob = Calendar.getInstance();
            ob.setTime(Data);
            ob.add(Calendar.DATE, +dias);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FormatoDaData);
            DataAlterada = simpleDateFormat.format(ob.getTime());

            return DataAlterada;
        } catch (Exception e) {
            return "Data Inválida";//caso passe a data fora do formato } return DataAlterada; }
        }

    }

    public List<CardapioBean> listarCardapio() throws InstantiationException, IllegalAccessException {

        List<CardapioBean> listaCardapio = new ArrayList();

        String sql = "SELECT * FROM Cardapio";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();
        try {
            //Inserindo os dados
            stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CardapioBean cardapio = new CardapioBean();
                cardapio.setIdCardapio(rs.getInt("id"));
                cardapio.setIdJanta(rs.getInt("idJanta"));
                cardapio.setIdAlmoco(rs.getInt("idAlmoco"));
                cardapio.setIdAlmoco(rs.getInt("idNutricionista"));
                cardapio.setDataHora(rs.getDate("dataHora"));

                listaCardapio.add(cardapio);
            }
            return listaCardapio;
        } catch (Exception ex) {
            System.out.println("Ocorreu algum erro ao listar os cardápios " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listaCardapio;
    }

    public void setarDatas(String data1, String data2) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        Date dt1 = df.parse(data1);
        Date dt2 = df.parse(data2);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt1);

        for (Date dt = dt1; dt.compareTo(dt2) <= 0;) {
            //System.out.println (df.format (dt));
            cal.add(Calendar.DATE, +1);
            dt = cal.getTime();
             
        }
    }

    public int verificarUltimoId() throws InstantiationException, IllegalAccessException {
        int id=0;
                           
        String sql = "SELECT COUNT(*) FROM Cardapio ";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();
        try {
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
               
                id++;
                
            }
            return id+1;
        } catch (Exception ex) {
            System.out.println("Ocorreu algum erro nos IDs " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return id+1;
    }
}


package DAO;

import BEANS.RegistroBean;
import EXCEPTION.AdicionarException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RegistroDAO {
    
    private Connection con = null;
    
    public RegistroDAO() throws InstantiationException, IllegalAccessException{
        con = ConnectionFactory.getConnection();
    }
    
    public void adicionarRegistro(RegistroBean registro) throws InstantiationException, IllegalAccessException, AdicionarException{               
        String sql = "INSERT INTO Registro(cpf,dataHora,valorCobrado,justificativa,idAtendente,idCardapio) values"
                + "(?,?,?,?,?,?)";
        con = ConnectionFactory.getConnection();        
        //java.sql.Date dataNascimento = new java.sql.Date(aluno.getDataHora()getTime());
        //java.sql.Date data = new java.sql.Timestamp( aluno.getDataHora().getTime()); 
        PreparedStatement stmt = null;
        byte estadoCadastro = 1;
        try{
            //Abrindo uma conexão para inserir os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1,registro.getCpf());
            stmt.setTimestamp(2, new java.sql.Timestamp(registro.getDataHora().getTime()));
            stmt.setDouble(3,registro.getValorCobrado());
            stmt.setString(4,registro.getJustificativa());
            stmt.setInt(5,registro.getIdAtendente());
            stmt.setInt(6,registro.getIdCardapio());
            stmt.executeUpdate();
            
        }catch(Exception ex){
            throw new AdicionarException("Registro");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }                    
    }
    
    public RegistroBean buscarRegistrosPorId(int id) throws InstantiationException, IllegalAccessException, Exception{
        String sql = "select * from Registro where date(dataHora) = ?";
        con = ConnectionFactory.getConnection();                
        PreparedStatement stmt = null;
        RegistroBean registro = new RegistroBean();
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                registro.setCpf(rs.getString("cpf"));
                registro.setDataHora(rs.getDate("dataHora"));
                registro.setIdAtendente(rs.getInt("idAtendente"));
                registro.setIdCardapio(rs.getInt("idCardapio"));
                registro.setIdRegistro(rs.getInt("idRegistro"));
                registro.setJustificativa(rs.getString("justificativa"));
                registro.setValorCobrado(rs.getDouble("valorCobrado"));
                registro.setId(rs.getInt("id"));
            }
        }catch(Exception ex){
            throw ex;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
       return registro; 
    }
    
    public List<RegistroBean> buscarRegistrosPorData(java.util.Date data) throws InstantiationException, IllegalAccessException, Exception{
        List<RegistroBean> lista = new ArrayList();
        String sql = "select * from Registro where date(dataHora) = ?";
        con = ConnectionFactory.getConnection();                
        PreparedStatement stmt = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dataSQL = new java.sql.Date(data.getTime());
        try{
            stmt = con.prepareStatement(sql);
            stmt.setDate(1, dataSQL);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                RegistroBean registro = new RegistroBean();
                registro.setCpf(rs.getString("cpf"));
                registro.setDataHora(rs.getDate("dataHora"));
                registro.setIdAtendente(rs.getInt("idAtendente"));
                registro.setIdCardapio(rs.getInt("idCardapio"));
                registro.setIdRegistro(rs.getInt("idRegistro"));
                registro.setJustificativa(rs.getString("justificativa"));
                registro.setValorCobrado(rs.getDouble("valorCobrado"));
                registro.setId(rs.getInt("id"));
                lista.add(registro);
            }
        }catch(Exception ex){
            throw ex;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
       return lista; 
    }
    
    public List<String> listarMeses() throws InstantiationException, IllegalAccessException{
    
        String sql = "select month(dataHora) as 'mes' from Registro group by month(dataHora)";        
        con = ConnectionFactory.getConnection();                
        List<String> lista = new ArrayList();        
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                lista.add(verificaMes(rs.getInt("mes")));
            }
            return lista;
        }catch(Exception ex){
        
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
        return lista;
    }
    
    public List<String> listarAnos() throws InstantiationException, IllegalAccessException{
    
        String sql = "select year(dataHora) as 'ano' from Registro group by year(dataHora)";
        con = ConnectionFactory.getConnection();                
        List<String> lista = new ArrayList();        
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                lista.add(rs.getString("ano"));
            }
            return lista;
        }catch(Exception ex){
        
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
        return lista;        
    }
    
    public List<RegistroBean> dashboardGerenteAlmoco() throws InstantiationException, IllegalAccessException{
        
        con = ConnectionFactory.getConnection();
                
        List<RegistroBean> dashBoard = new ArrayList();
        
        PreparedStatement stmt = null;
        
        String sql = "select t.nome,month(dataHora) as 'mes',count(*) as 'Quantidade',concat('R$ ',replace(format(SUM(valorCobrado),2),'.',',')) as 'Valor' from Registro r " +
        "inner join tipoPessoa t on t.id = r.idRegistro where TIME(dataHora)<= '17:00:00' group by r.idRegistro;";
        
        try{
        stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                RegistroBean registro = new RegistroBean();
                registro.setNomeCategoria(rs.getString("nome"));
                registro.setValor(rs.getString("Valor"));
                registro.setQuantidade(rs.getInt("quantidade"));
                registro.setNomeMes(verificaMes(rs.getInt("mes")));
                dashBoard.add(registro);
            }
            
        }catch(Exception ex){
        
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }               
        return dashBoard;
    }
    
    public List<RegistroBean> dashboardGerenteJanta() throws InstantiationException, IllegalAccessException{
        
        con = ConnectionFactory.getConnection();
                
        List<RegistroBean> dashBoard = new ArrayList();
        
        PreparedStatement stmt = null;
        
        String sql = "select t.nome,month(dataHora) as 'mes',count(*) as 'Quantidade',concat('R$ ',replace(format(SUM(valorCobrado),2),'.',',')) as 'Valor' from Registro r " +
        "inner join tipoPessoa t on t.id = r.idRegistro where TIME(dataHora)>= '17:00:00' group by r.idRegistro;";
        
        try{
        stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                RegistroBean registro = new RegistroBean();
                registro.setNomeCategoria(rs.getString("nome"));
                registro.setValor(rs.getString("Valor"));
                registro.setQuantidade(rs.getInt("Quantidade"));
                registro.setNomeMes(verificaMes(rs.getInt("mes")));
                dashBoard.add(registro);
            }
            
        }catch(Exception ex){
        
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }               
        return dashBoard;
    }
    
    public void removerRegistro(int id) throws Exception{
    
        con = ConnectionFactory.getConnection();
        
        String sql = "DELETE FROM Registro WHERE id=?";
        
        PreparedStatement stmt = null;
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.executeUpdate();
           
        }catch(SQLException ex){
            throw new Exception("Erro ao tentar remover Registro: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
 private String verificaMes(int id){
    String mes = "";
    switch(id){
        case 1: 
            mes = "Janeiro";    
        break;
        case 2: 
            mes = "Fevereiro";
        break;
        case 3: 
            mes = "Março";
        break;
        case 4: 
            mes = "Abril";
        break;
        case 5: 
            mes = "Maio";
        break;
        case 6: 
            mes = "Junho";
        break;
        case 7: 
            mes = "Julho";
        break;
        case 8: 
            mes = "Agosto";
        break;
        case 9: 
            mes = "Setembro";
        break;
        case 10: 
            mes = "Outubro";
        break;
        case 11: 
           mes = "Novembro";
        break;
        case 12: 
            mes = "Dezembro";
        break;    
    }
    return mes;
 }   
    
}


package DAO;

import BEANS.AlmocoBean;
import EXCEPTION.AdicionarException;
import EXCEPTION.ErroCarneExistenteException;
import EXCEPTION.ErroSobremesaExistenteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AlmocoDAO {
    
    private Connection con = null;
    
    public AlmocoDAO() throws InstantiationException, IllegalAccessException{
        con = ConnectionFactory.getConnection();
    }
    
    public void adicionarAlmoco(AlmocoBean almoco) throws InstantiationException, IllegalAccessException, AdicionarException{               
        String sql = "INSERT INTO Almoco(idArroz,idFeijao,idAcompanhamento,idSalada,idSobremesa,qtdeArroz,qtdeAcompanhamento,qtdeSalada,qtdeSobremesa) values"
                + "(?,?,?,?,?,?,?,?,?)";
        con = ConnectionFactory.getConnection();        
        PreparedStatement stmt = null;
        try{
            //Abrindo uma conexão para inserir os dados
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,almoco.getIdArroz());
            stmt.setInt(2,almoco.getIdFeijao());
            stmt.setInt(3,almoco.getIdAcompanhamento());
            stmt.setInt(4,almoco.getIdSalada());
            stmt.setInt(5,almoco.getIdSobremesa());
            stmt.setDouble(6, almoco.getQtdeArroz());
            stmt.setDouble(7,almoco.getQtdeAcompanhamento());
            stmt.setDouble(8,almoco.getQtdeSalada());
            stmt.setDouble(9, almoco.getQtdeSobremesa());
            stmt.executeUpdate();
            
        }catch(Exception ex){
            throw new AdicionarException("Almoco");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }              
    }
    
//    public List<AlmocoBean> listarAlmoco() throws InstantiationException, IllegalAccessException {
//        
//        List<AlmocoBean> listarAlmoco = new ArrayList();
//
//        String sql = "SELECT * FROM almoco";
//        PreparedStatement stmt = null;
//        con = ConnectionFactory.getConnection();
//        try {
//            //Inserindo os dados
//            stmt = con.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                AlmocoBean almocoBean = new AlmocoBean();
//                almocoBean.setIdAlmoco(rs.getInt("id"));
//                almocoBean.setIdArroz(rs.getInt(""));
//                almocoBean.setIdArroz(rs.getInt(""));
//                professor.setDataNascimento(rs.getDate("dataNascimento"));
//                professor.setEmail(rs.getString("email"));
//                professor.setEstadoCadastro(rs.getByte("estadoCadastro"));
//                professor.setIdCidade(rs.getInt("idCidade"));
//                professor.setNome(rs.getString("nome"));
//                professor.setRua(rs.getString("Rua"));
//                professor.setDepartamento(rs.getString("departamento"));
//                professor.setAreaEstudo(rs.getString("AreaEstudo"));
//                listaProfessores.add(professor);
//            }
//            return listaProfessores;
//        } catch (Exception ex) {
//            System.out.println("Ocorreu algum erro ao listar os professores desativados " + ex.getMessage());
//        } finally {
//            ConnectionFactory.closeConnection(con, stmt);
//        }
//        return listaProfessores;
//    }
        
    public AlmocoBean buscarCardapioAlmocoDia() throws InstantiationException, IllegalAccessException, Exception{
        String sql = "select C.id as 'idCardapio',A.id,A.idArroz,A.idFeijao,A.idAcompanhamento,A.idSalada,A.idSobremesa,A.qtdeArroz,A.qtdeAcompanhamento,A.qtdeSalada,A.qtdeSobremesa,A.qtdeFeijao  from Cardapio C " +
        "inner join Almoco A on C.idAlmoco = A.id " +
        "where date(dataHora) = date(?)";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();  
        AlmocoBean almoco = new AlmocoBean();
        //Pegando data e horas atuais
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String data = dtf.format(LocalDateTime.now());               
        
        java.sql.Date dataBanco = retornandoData(data);
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setDate(1, dataBanco);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){ 
            almoco.setIdCardapio(rs.getInt("idCardapio"));
            almoco.setIdAcompanhamento(rs.getInt("idAcompanhamento"));
            almoco.setIdAlmoco(rs.getInt("id"));
            almoco.setIdArroz(rs.getInt("idArroz"));
            almoco.setIdFeijao(rs.getInt("idFeijao"));
            almoco.setIdSalada(rs.getInt("idSalada"));
            almoco.setIdSobremesa(rs.getInt("idSobremesa"));
            almoco.setQtdeAcompanhamento(rs.getDouble("qtdeAcompanhamento"));
            almoco.setQtdeArroz(rs.getDouble("qtdeArroz"));
            almoco.setQtdeFeijao(rs.getDouble("qtdeFeijao"));
            almoco.setQtdeSalada(rs.getDouble("qtdeSalada"));
            almoco.setQtdeSobremesa(rs.getDouble("qtdeSobremesa"));
            }
            return almoco;
        }catch(Exception ex){
            throw ex;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }        
        }
    
    
    public AlmocoBean buscarAlmocoPorId(int id) throws InstantiationException, IllegalAccessException{        
        String sql = "SELECT * FROM Almoco WHERE id=?";
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();  
        AlmocoBean almoco = new AlmocoBean();        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){ 
                almoco.setIdAcompanhamento(rs.getInt("idAcompanhamento"));
                almoco.setIdAlmoco(rs.getInt("id"));
                almoco.setIdArroz(rs.getInt("idArroz"));
                almoco.setIdFeijao(rs.getInt("idFeijao"));
                almoco.setIdSalada(rs.getInt("idSalada"));
                almoco.setIdSobremesa(rs.getInt("idSobremesa"));
                almoco.setQtdeAcompanhamento(rs.getDouble("qtdeAcompanhamento"));
                almoco.setQtdeArroz(rs.getDouble("qtdeArroz"));
                almoco.setQtdeFeijao(rs.getDouble("qtdeFeijao"));
                almoco.setQtdeSalada(rs.getDouble("qtdeSalada"));
                almoco.setQtdeSobremesa(rs.getDouble("qtdeSobremesa"));
            }
            return almoco;
        }catch(Exception ex){
            System.out.println("Ocorreu um erro durante a busca do objeto almoco" + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }        
        return almoco;
    }
    
    private java.sql.Date retornandoData(String dataString){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date data = null;
        java.sql.Date dataSQL = null;
        try{
            data = format.parse(dataString);
            dataSQL = new java.sql.Date(data.getTime());
        }catch(Exception ex){
            System.out.println("Erro ao exibir data");
        }
        return dataSQL;
    }
    
        public void validarAlmoco(int carne, int sobremesa, Date data) throws InstantiationException, IllegalAccessException, Exception{
      
        int verificaCarne =0;
        int verificaSobremesa =0;
        
        String sql = "SELECT A.idAcompanhamento as 'acompanhamento', A.idSobremesa as 'sobremesa' FROM Cardapio C"
                +" INNER JOIN Almoco A ON C.idAlmoco = A.id" 
                +" INNER JOIN Janta J ON C.idJanta = J.id"
                +" WHERE Day(dataHora) = Day(?)";
        
                
        PreparedStatement stmt = null;
        con = ConnectionFactory.getConnection();
         java.sql.Date dataFormatada = new java.sql.Date(data.getTime());
        try{
            stmt = con.prepareStatement(sql);
            stmt.setDate(1,dataFormatada);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                verificaCarne = rs.getInt("acompanhamento");
                verificaSobremesa = rs.getInt("sobremesa");
                
                if(verificaCarne == carne){
                    
                    throw new ErroCarneExistenteException("Almoço");
                }
                
                if(verificaSobremesa == sobremesa){
                    
                    throw new ErroSobremesaExistenteException("Almoço");
                }
            }
            
        }catch(Exception ex){
            throw ex;
        }
        
    }
    
}

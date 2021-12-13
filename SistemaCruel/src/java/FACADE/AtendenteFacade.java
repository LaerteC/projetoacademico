
package FACADE;

import BEANS.AlmocoBean;
import BEANS.RegistroBean;
import DAO.AlmocoDAO;
import DAO.AlunoDAO;
import DAO.AtendenteDAO;
import DAO.ExternoDAO;
import DAO.IngredienteDAO;
import DAO.NutricionistaDAO;
import DAO.ProfessorDAO;
import DAO.RegistroDAO;
import DAO.ServidorDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AtendenteFacade {
    
        //Instanciando as DAOS
    ProfessorDAO professorDAO = new ProfessorDAO();
    AtendenteDAO atendenteDAO = new AtendenteDAO();
    ExternoDAO externoDAO = new ExternoDAO();
    NutricionistaDAO nutricionistaDAO = new NutricionistaDAO();
    AlunoDAO alunoDAO = new AlunoDAO();
    IngredienteDAO ingredienteDAO = new IngredienteDAO();
    AlmocoDAO almocoDAO = new AlmocoDAO();
    RegistroDAO registroDAO = new RegistroDAO();
    ServidorDAO servidorDAO = new ServidorDAO();
    
    public AtendenteFacade() throws InstantiationException, IllegalAccessException{
    }
    
    
    public AlmocoBean buscarAlmocoDoDia() throws Exception{
        AlmocoBean almoco = new AlmocoBean();
        try{
            almoco = almocoDAO.buscarCardapioAlmocoDia();
            almoco.setAcompanhamento(ingredienteDAO.retornarNomeIngrediente(almoco.getIdAcompanhamento()));
            almoco.setArroz(ingredienteDAO.retornarNomeIngrediente(almoco.getIdArroz()));
            almoco.setFeijao(ingredienteDAO.retornarNomeIngrediente(almoco.getIdFeijao()));
            almoco.setSalada(ingredienteDAO.retornarNomeIngrediente(almoco.getIdSalada()));
            almoco.setSobremesa(ingredienteDAO.retornarNomeIngrediente(almoco.getIdSobremesa()));
            almoco.setCardapio("Almoço");
           return almoco;
        }catch(Exception ex){
            throw ex;
        }
    }
    
    public RegistroBean buscarRegistrosPorId(int id) throws Exception{
        RegistroBean registro = new RegistroBean();
        try{
            registroDAO.buscarRegistrosPorId(id);
        }catch(Exception ex){
            throw ex;
        }
        return registro;
    }
    
    public void removerRegistro(int id) throws Exception{
        try{
            registroDAO.removerRegistro(id);
        }catch(Exception ex){
            throw ex;
        }
    }
    
    public List<RegistroBean> buscarRegistrosPorData(java.util.Date data) throws Exception{        
        List<RegistroBean> lista = new ArrayList();
        try{
           lista = registroDAO.buscarRegistrosPorData(data);
        }catch(Exception ex){
            throw ex;
        }
        return lista;
    }
    
    public java.sql.Date converterDataSQL(String dataString){
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
    
}

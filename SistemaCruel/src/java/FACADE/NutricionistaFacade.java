package FACADE;

import BEANS.AlmocoBean;
import BEANS.CardapioBean;
import BEANS.IngredienteBean;
import BEANS.JantaBean;
import BEANS.TipoIngredienteBean;
import DAO.AlmocoDAO;
import DAO.CardapioDAO;
import DAO.IngredienteDAO;
import DAO.JantaDAO;
import DAO.NutricionistaDAO;
import DAO.TipoIngredienteDAO;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NutricionistaFacade {

    NutricionistaDAO nutricionistaDAO = new NutricionistaDAO();
    IngredienteDAO ingredienteDAO = new IngredienteDAO();
    TipoIngredienteDAO tipoIngredienteDAO = new TipoIngredienteDAO();
    AlmocoDAO almoco = new AlmocoDAO();
    JantaDAO janta = new JantaDAO();
    CardapioDAO cardapioDao = new CardapioDAO();

    public NutricionistaFacade() throws InstantiationException, IllegalAccessException {

    }

    //***********************************
    //************* LISTAS **************  
    //***********************************
    public List<IngredienteBean> lista() throws InstantiationException, IllegalAccessException {
        List<IngredienteBean> lista = ingredienteDAO.listarIngredientes();
        try {
            return lista;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void inserirIngrediente(IngredienteBean ingrediente) throws Exception {
        try {
            //Verificando se existe email e o cpf já existe em dados do Aluno
            ingredienteDAO.adicionarIngrediente(ingrediente);

        } catch (Exception ex) {
            throw ex;
        }
    }

    public IngredienteBean buscarIngredientePorId(int id) throws Exception {

        try {

            return ingredienteDAO.buscarIngredientePorId(id);
        } catch (Exception ex) {

            throw ex;
        }
    }

    public void atualizarDadosIngrediente(IngredienteBean ingrediente) throws Exception {

        try {
            ingredienteDAO.atualizarDadosIngrediente(ingrediente);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void deletarIngrediente(int id) throws Exception {

        try {
            ingredienteDAO.deletarIngrediente(id);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<TipoIngredienteBean> listaTipoIngrediente() throws InstantiationException, IllegalAccessException {

        List<TipoIngredienteBean> lista = tipoIngredienteDAO.listarTodosTiposIngredientes();
        try {
            return lista;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public TipoIngredienteBean buscarTipoIngredientePorId(int id) throws Exception {

        try {

            return tipoIngredienteDAO.buscarTipoIngredientePorId(id);
        } catch (Exception ex) {

            throw ex;
        }
    }

    public void atualizarDadosTipoIngrediente(TipoIngredienteBean tipo) throws Exception {

        try {
            tipoIngredienteDAO.atualizarDadosTipoIngrediente(tipo);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void inserirTipoIngrediente(TipoIngredienteBean tipo) throws Exception {

        try {

            tipoIngredienteDAO.adicionarTipoIngrediente(tipo);

        } catch (Exception ex) {
            throw ex;
        }
    }

    public void deletarTipoIngrediente(int id) throws Exception {

        try {
            tipoIngredienteDAO.deletarTipoIngrediente(id);
        } catch (Exception ex) {
            throw ex;
        }
    }

//       public List<AlmocoBean> listarAlmoco() throws InstantiationException, IllegalAccessException {
//        List<AlmocoBean> lista = almoco.listarAlmoco();
//        try {
//            return lista;
//        } catch (Exception ex) {
//            throw ex;
//        }
//    }
    public void inserirAlmoco(AlmocoBean almoco, Date data) throws Exception {

        try {
            this.validarJanta(almoco.getIdAcompanhamento(), almoco.getIdSobremesa(), data);
            this.almoco.adicionarAlmoco(almoco);

        } catch (Exception ex) {
            throw ex;
        }
    }

    public void inserirJanta(JantaBean janta, Date data) throws Exception {

        try {
            this.validarAlmoco(janta.getIdAcompanhamento(), janta.getIdSobremesa(), data);
            this.janta.adicionarJanta(janta);

        } catch (Exception ex) {
            throw ex;
        }
    }

    public void validarAlmoco(int carne, int sobremesa, Date data) throws Exception {

        try {
            almoco.validarAlmoco(carne, sobremesa, data);

        } catch (Exception ex) {
            throw ex;
        }
    }

    public void validarJanta(int carne, int sobremesa, Date data) throws Exception {

        try {
            janta.validarJantar(carne, sobremesa, data);

        } catch (Exception ex) {
            throw ex;
        }
    }

   
    public List<CardapioBean> listarCardapio() throws InstantiationException, IllegalAccessException{
         List<CardapioBean> lista = cardapioDao.listarCardapio();
        try {
            return lista;
        } catch (Exception ex) {
            throw ex;
        }
        
    }

}

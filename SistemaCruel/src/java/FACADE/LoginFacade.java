
package FACADE;

import BEANS.LoginBean;
import DAO.LoginDAO;

public class LoginFacade {
    
    //Chamando a DAO
    LoginDAO daoLogin = new LoginDAO();
    
    public LoginFacade() throws InstantiationException, IllegalAccessException{
        daoLogin = new LoginDAO();
    }
    
    public LoginBean validarLoginAtendente(String email, String senha) throws Exception{
        try{
           return daoLogin.validarLoginAtendente(email, senha);
        }catch(Exception ex){
            throw ex;
        }
    }    
    
    public LoginBean validarLoginNutricionista(String email, String senha) throws Exception{
        try{
            return daoLogin.validarLoginNutricionista(email, senha);
        }catch(Exception ex){
            throw ex;
        }
    }
    
    public LoginBean validarLoginGerente(String email, String senha) throws Exception{
        try{
            return daoLogin.validarLoginGerente(email, senha);
        }catch(Exception ex){
            throw ex;
        }
    }
    
}

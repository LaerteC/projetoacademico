
package EXCEPTION;

public class AdicionarException extends AppException{
    
    public AdicionarException(String tipoUsuario) {
        super("Ocorreu um erro ao tentar adicionar um(a) novo(a) "+ tipoUsuario + "!");
    }
    
}

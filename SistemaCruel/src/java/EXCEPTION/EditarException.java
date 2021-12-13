
package EXCEPTION;

public class EditarException extends AppException{
    
    public EditarException(String mensagem) {
        super("Ocorreu um erro ao editar " + mensagem + "!");
    }
    
}

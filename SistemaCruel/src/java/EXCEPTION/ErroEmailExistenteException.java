
package EXCEPTION;

public class ErroEmailExistenteException extends AppException{
    
    public ErroEmailExistenteException() {
        super("O email informado não está disponível!");
    }
    
}

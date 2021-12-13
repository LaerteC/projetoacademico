
package EXCEPTION;

public class ErroCpfExistenteException extends AppException{
    
    public ErroCpfExistenteException() {
        super("CPF Informado já está cadastrado em nosso sistema!");
    }
    
}


package EXCEPTION;

public class ErroUsuarioNaoEncontradoException extends AppException{
    
    public ErroUsuarioNaoEncontradoException(String mensagem) {
        super(mensagem + " não encontrado!");
    }
    
}

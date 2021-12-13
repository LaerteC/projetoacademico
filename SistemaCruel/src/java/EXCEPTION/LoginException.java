
package EXCEPTION;

public class LoginException extends AppException{
    public LoginException(){
        super("Não foi possível realizar o login, verifique o email ou a senha!");
    }
}

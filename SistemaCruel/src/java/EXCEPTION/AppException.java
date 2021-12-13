package EXCEPTION;

public class AppException extends Exception{    
    //Declarando a classe que vai herdar de exceptin, é esse atributo mensagem que vai ser mostrado
    public AppException(String mensagem){
        super(mensagem);
        }    
}

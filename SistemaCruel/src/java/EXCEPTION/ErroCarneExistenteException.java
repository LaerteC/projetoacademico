/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EXCEPTION;

/**
 *
 * @author laert
 */
public class ErroCarneExistenteException extends AppException {

    public ErroCarneExistenteException(String mensagem) {

        super("Essa carne já está cadastrada no " + mensagem+ " !");
    }

}

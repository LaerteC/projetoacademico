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
public class ErroSobremesaExistenteException extends AppException {

    /**
     * Constructs an instance of <code>ErroSobremesaExistenteException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ErroSobremesaExistenteException(String msg) {
        super("Essa carne já está cadastrada no " + msg+ " !");
    }
}

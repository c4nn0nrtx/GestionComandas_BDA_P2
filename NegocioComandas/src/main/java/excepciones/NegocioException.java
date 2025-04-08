/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Beto_
 */
public class NegocioException extends Exception{

    /**
     *
     * @param message
     */
    public NegocioException(String message) {
        super(message);
    }

    /**
     *
     * @param cause
     */
    public NegocioException(Throwable cause) {
        super(cause);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }
}

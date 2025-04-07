/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Maximiliano
 */
public class NegocioException extends Exception
{
    public NegocioException(String message) {
        super(message);
    }

    public NegocioException(Throwable cause) {
        super(cause);
    }

    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }

}

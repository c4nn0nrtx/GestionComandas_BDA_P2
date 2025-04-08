/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlGUI;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

/**
 * La clase {@code ControlGUI} gestiona la visualización y el intercambio de
 * datos entre diferentes ventanas ({@link JFrame}) de la aplicación. Utiliza
 * un {@link Map} para almacenar y acceder a los frames por su nombre,
 * permitiendo mostrar, ocultar y pasar información entre ellos.
 * @author Beto_
 */
public class ControlGUI {
    private Map<String, JFrame> frames = new HashMap<>();
    private JFrame frameActual = null;

    public ControlGUI() {
    }
    
    /**
     * Añade el frame al Map de frames para su gestión
     * @param nombre el nombre del frame, que es la llave del mapa
     * @param frame el frame, el objeto guardado
     */
    public void registrarFrame(String nombre, JFrame frame){
        frames.put(nombre, frame);
    }
    
    /**
     * Obtiene el frame por su nombre
     * @param nombre el nombre del frame
     * @return el frame
     */
    public JFrame obtenerFrame(String nombre){
        return frames.get(nombre);
    }
    
    /**
     * Muestra el frame indicado
     * @param nombre como se llama el frame
     */
    public void mostrarFrame(String nombre){
        //Buscamos el frame de nuestros frames
        //Usando de llave su nombre
        JFrame frame = frames.get(nombre);
        
        //Si el frame obtenido no es nulo, si existe pues
        if(frame != null){
            //Si el frame actual se está mostrando se cierra
            if(frameActual != null){
                frameActual.setVisible(false); 
            }
            frame.setVisible(true);
            frameActual = frame;
        }else{
            System.err.println("Frame:" + nombre + " no registrado.");
        }
    }
    
    /**
     * Oculta el frame actual
     */
    public void ocultarFrameActual() {
        if(frameActual != null){
            frameActual.setVisible(false);
        }
    }
    
    /**
     * Pasa datos entre frames
     * Solo los frames receptores puedes obtener datos
     * @param nombreFrameDestino el frame destinatario
     * @param clave la clave
     * @param dato El dato a pasar entre frames
     */
    public void pasarDato(String nombreFrameDestino, String clave, Object dato){
        //Obtiene el frame de destino por su nombre
        JFrame frameDestino = frames.get(nombreFrameDestino);
        
        //Si el frame destinatario es un receptor
        if(frameDestino instanceof ReceptorDatos){
            //Conversión explicita al saber que si recibe datos
            ((ReceptorDatos) frameDestino).recibirDato(clave, dato);
        }else{
            System.err.println("Error: El frame destinatario: " + nombreFrameDestino + " no recibe datos");
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlGUI;

/**
 * Representa los frames que pueden recibir datos
 * 
 * La interfaz {@code ReceptorDatos} define el contrato para los objetos que tienen la capacidad de
 * recibir datos desde otras partes de la aplicación, típicamente a través de un
 * mecanismo de comunicación gestionado por una clase controladora (como {@code ControlGUI}).
 * @author Beto_
 */
public interface ReceptorDatos {
    public void recibirDato(String clave, Object dato);
}

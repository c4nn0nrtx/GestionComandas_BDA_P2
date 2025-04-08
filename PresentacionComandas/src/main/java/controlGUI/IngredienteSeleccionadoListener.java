/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlGUI;

import DTOs.viejos.IngredienteViejoDTO;

/**
 * Representa los frames que poseen listener del ingrediente
 * 
 * La interfaz {@code IngredienteSeleccionadoListener} define el contrato para
 * los objetos que desean ser notificados cuando un ingrediente es seleccionado
 * en la interfaz de usuario. Cualquier clase que implemente esta interfaz
 * debe proporcionar una implementación para el método
 * {@link #ingredienteSeleccionado(IngredienteViejoDTO)}, que se invocará
 * cuando un ingrediente sea seleccionado, pasando como argumento el
 * {@link IngredienteViejoDTO} del ingrediente seleccionado.
 * @author Beto_
 */
public interface IngredienteSeleccionadoListener {
    public void ingredienteSeleccionado(IngredienteViejoDTO ingrediente);
}

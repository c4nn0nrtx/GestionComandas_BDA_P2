/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import controlGUI.ControlGUI;
import modulo.ingredientes.AgregarIngredienteGUI;
import modulo.ingredientes.BuscadorIngredienteGUI;
import modulo.ingredientes.GestionarIngredienteGUI;

/**
 *
 * @author brand
 */
public class Main {
    //Control de GUIs, el mediador de la capa presentación
    public static ControlGUI controlGUI = new ControlGUI();
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        //Añadimos las pantallas al controlador para que puedan ser gestionadas
    
    //0. Menú
        
    //1.Modulo de ingredientes
        //1.1 Instanciar los frames
        AgregarIngredienteGUI agregarIngredienteGUI = new AgregarIngredienteGUI();
        BuscadorIngredienteGUI buscadorIngredienteGUI = new BuscadorIngredienteGUI();
        GestionarIngredienteGUI gestionarIngredienteGUI = new GestionarIngredienteGUI();

        //1.2 Añadirlos al controlador
        controlGUI.registrarFrame("AgregarIngrediente", agregarIngredienteGUI);
        controlGUI.registrarFrame("BuscadorIngrediente", buscadorIngredienteGUI);
        controlGUI.registrarFrame("GestionarIngrediente", gestionarIngredienteGUI);

    //2. Modulo de productos
    
    //3. Modulo de clientes
    
    //4. Modulo de Comandas
    
    //5. Modulo de Reportes
    
        //inicio. Mostrar el primer frame
        controlGUI.mostrarFrame("GestionarIngrediente");
    }
}

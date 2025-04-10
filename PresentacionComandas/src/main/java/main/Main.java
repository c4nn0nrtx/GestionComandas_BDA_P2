/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import controlGUI.ControlGUI;
import miscelanea.MenuComandasGUI;
import miscelanea.MenuIngredientesGUI;
import miscelanea.MenuPrincipalGUI;
import miscelanea.MenuReportesGUI;
import modulo.comandas.AgregarComandaGUI;
import modulo.ingredientes.AgregarIngredienteGUI;
import modulo.ingredientes.BuscadorIngredienteGUI;
import modulo.ingredientes.GestionarIngredienteGUI;
import modulo.reportes.ReporteClientesGUI;
import modulo.reportes.ReporteComandasGUI;

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
        controlGUI.registrarFrame("AgregarIngredienteGUI", agregarIngredienteGUI);
        controlGUI.registrarFrame("BuscadorIngredienteGUI", buscadorIngredienteGUI);
        controlGUI.registrarFrame("GestionarIngredienteGUI", gestionarIngredienteGUI);

    //2. Modulo de productos
    
    //3. Modulo de clientes
    
    //4. Modulo de Comandas
        //4.1 Instanciar los frames
        AgregarComandaGUI agregarComandaGUI = new AgregarComandaGUI();
        
        //4.2 Añadirlos al controlador
        controlGUI.registrarFrame("AgregarComandaGUI", agregarComandaGUI);
    
    //5. Modulo de Reportes
        //5.1 Instanciar los frames
        ReporteClientesGUI reporteClientesGUI = new ReporteClientesGUI();
        ReporteComandasGUI reporteComandasGUI = new ReporteComandasGUI();
        
        //5.2 Añadirlos al controlador
        
        controlGUI.registrarFrame("ReporteClientesGUI", reporteClientesGUI);
        controlGUI.registrarFrame("ReporteComandasGUI", reporteComandasGUI);
        
    //6. Miscelánea
        //1.1 Instanciar los frames
        MenuPrincipalGUI menuPrincipalGUI = new MenuPrincipalGUI();
        MenuIngredientesGUI menuIngredientesGUI = new MenuIngredientesGUI();
        MenuComandasGUI menuComandasGUI = new MenuComandasGUI();
        MenuReportesGUI menuReportesGUI = new MenuReportesGUI();
        
        //1.2 Añadirlos al controlador
        controlGUI.registrarFrame("MenuPrincipalGUI", menuPrincipalGUI);
        controlGUI.registrarFrame("MenuIngredientesGUI", menuIngredientesGUI);
        controlGUI.registrarFrame("MenuComandasGUI", menuComandasGUI);
        controlGUI.registrarFrame("MenuReportesGUI", menuReportesGUI);
        
        ////////////////////////////////////////////////////////////////////////////////
    
        //inicio. Mostrar el primer frame
        controlGUI.mostrarFrame("MenuPrincipalGUI");
    }
}

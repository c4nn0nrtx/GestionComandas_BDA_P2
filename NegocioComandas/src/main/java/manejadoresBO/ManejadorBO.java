/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadoresBO;

import modulo.ingredientes.IIngredienteBO;
import modulo.ingredientes.IIngredienteDAO;
import modulo.ingredientes.IngredienteBO;
import modulo.ingredientes.IngredienteDAO;

/**
 *
 * @author Beto_
 */
public class ManejadorBO {
    public static IIngredienteBO crearIngredienteBO() {
        // 1. Creamos la instancia del DAO a utilizar.
        // Se obtiene la única instancia disponible debido al uso del patrón Singleton.
        IIngredienteDAO ingredienteDAO = IngredienteDAO.getInstanceDAO();

        // 2. Creamos la instancia del BO, inyectando el DAO como dependencia.
        IIngredienteBO ingredienteBO = new IngredienteBO(ingredienteDAO);

        // 3. Retornamos la instancia del BO lista para ser utilizada.
        return ingredienteBO;
    }
}

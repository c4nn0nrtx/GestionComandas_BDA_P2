/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.persistenciacomandas;

import ENUMs.UnidadMedida;
import entidades.Ingrediente;
import excepciones.PersistenciaException;
import java.util.List;
import modulo.ingredientes.IIngredienteDAO;
import modulo.ingredientes.IngredienteDAO;

/**
 *
 * @author brand
 */
public class PersistenciaComandas {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        IIngredienteDAO ingredienteDAO = IngredienteDAO.getInstanceDAO();
        
        Ingrediente ingrediente = new Ingrediente("Queso", UnidadMedida.GRAMOS, 25.5);
        try{
//            ingredienteDAO.agregar(ingrediente);
//            System.out.println("Ingrediente creado!");
//            
//            System.out.println("Ingrediente encontrado: " + ingredienteDAO.obtenerPorId(1l).getNombre());
            
            List<Ingrediente> ingNombre = ingredienteDAO.obtenerPorNombre("danonino");
            for (Ingrediente ingrediente1 : ingNombre) {
                System.out.println("Ingrediente: " + ingrediente1.getNombre());
            }
        }catch(PersistenciaException pe){
            System.out.println(pe.getMessage());
        }
    }
}

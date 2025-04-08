/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import DTOs.nuevos.MesaNuevoDTO;
import excepciones.NegocioException;
import javax.swing.JOptionPane;
import manejadoresBO.ManejadorBO;
import modulo.comandas.IMesaBO;

/**
 *
 * @author Beto_
 */
public class MesaInserts {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        IMesaBO mesaBO = ManejadorBO.crearMesaBO();
        
        //Insertar 20 mesas yess
        try{
            mesaBO.agregar(new MesaNuevoDTO(1));
            mesaBO.agregar(new MesaNuevoDTO(2));
            mesaBO.agregar(new MesaNuevoDTO(3));
            mesaBO.agregar(new MesaNuevoDTO(4));
            mesaBO.agregar(new MesaNuevoDTO(5));
            mesaBO.agregar(new MesaNuevoDTO(6));
            mesaBO.agregar(new MesaNuevoDTO(7));
            mesaBO.agregar(new MesaNuevoDTO(8));
            mesaBO.agregar(new MesaNuevoDTO(9));
            mesaBO.agregar(new MesaNuevoDTO(10));
            mesaBO.agregar(new MesaNuevoDTO(11));
            mesaBO.agregar(new MesaNuevoDTO(12));
            mesaBO.agregar(new MesaNuevoDTO(13));
            mesaBO.agregar(new MesaNuevoDTO(14));
            mesaBO.agregar(new MesaNuevoDTO(15));
            mesaBO.agregar(new MesaNuevoDTO(16));
            mesaBO.agregar(new MesaNuevoDTO(17));
            mesaBO.agregar(new MesaNuevoDTO(18));
            mesaBO.agregar(new MesaNuevoDTO(19));
            mesaBO.agregar(new MesaNuevoDTO(20));
        }catch(NegocioException ne){
            System.err.println("Ups" + ne.getMessage());
            ne.printStackTrace();
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modulo.comandas;

import entidades.Mesa;
import excepciones.PersistenciaException;

/**
 *
 * @author Beto_
 */
public interface IMesaDAO {
    public Mesa agregar(Mesa mesa) throws PersistenciaException;
    
    public Mesa ObtenerPorNumeroMesa(Integer numeroMesa) throws PersistenciaException;
}

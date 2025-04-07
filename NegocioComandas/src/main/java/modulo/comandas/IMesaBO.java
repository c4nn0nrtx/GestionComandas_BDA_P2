/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modulo.comandas;

import DTOs.nuevos.MesaNuevoDTO;
import DTOs.viejos.MesaViejoDTO;
import excepciones.NegocioException;

/**
 *
 * @author Beto_
 */
public interface IMesaBO {
    public MesaViejoDTO agregar(MesaNuevoDTO mesaNueva) throws NegocioException;
    
    public MesaViejoDTO obtenerPorNumeroMesa(Integer numeroMesa) throws NegocioException;
}

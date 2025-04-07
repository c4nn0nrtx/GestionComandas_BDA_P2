/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo.comandas;

import DTOs.nuevos.MesaNuevoDTO;
import DTOs.viejos.MesaViejoDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.logging.Logger;
import mappers.MesaMapper;

/**
 *
 * @author Beto_
 */
public class MesaBO implements IMesaBO{
    private static final Logger LOGGER = Logger.getLogger(MesaBO.class.getName());
    /**
     * Instancia del DAO que maneja la persistencia de ingredientes.
     */
    private IMesaDAO mesaDAO;

    /**
     * Constructor que recibe el DAO como dependencia. Se sigue el principio de
     * inversión de dependencias para facilitar la inyección de dependencias.
     *
     * @param mesaDAO DAO que se usará para acceder a la base de datos.
     */
    public MesaBO(IMesaDAO mesaDAO) {
        this.mesaDAO = mesaDAO;
    }

    @Override
    public MesaViejoDTO agregar(MesaNuevoDTO mesaNueva) throws NegocioException {
        try{
            MesaViejoDTO mesaObtenida = obtenerPorNumeroMesa(mesaNueva.getNumeroMesa());
            if(mesaObtenida != null){
                throw new NegocioException("No se admiten repetidos en el numero de la mesa");
            }
            return MesaMapper.toViejoDTO(mesaDAO.agregar(MesaMapper.toEntity(mesaNueva)));
        }catch(PersistenciaException pe){
            throw new NegocioException("Error al agregar la mesa", pe);
        }
    }

    @Override
    public MesaViejoDTO obtenerPorNumeroMesa(Integer numeroMesa) throws NegocioException {
        try{
            if(numeroMesa <= 0){
                throw new NegocioException("Error: número de mesa negativo o cero");
            }
            
            return MesaMapper.toViejoDTO(mesaDAO.ObtenerPorNumeroMesa(numeroMesa));
        }catch(PersistenciaException pe){
            throw new NegocioException("Error al obtener la mesa", pe);
        }
    }
    
    
    
}

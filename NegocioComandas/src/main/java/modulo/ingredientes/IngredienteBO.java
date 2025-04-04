/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo.ingredientes;

import DTOs.nuevos.IngredienteNuevoDTO;
import DTOs.viejos.IngredienteViejoDTO;
import ENUMs.UnidadMedida;
import entidades.Ingrediente;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mappers.IngredienteMapper;

/**
 *
 * @author Beto_
 */
public class IngredienteBO implements IIngredienteBO{
    IngredienteMapper ingredienteMapper = new IngredienteMapper();
    private static final Logger LOGGER = Logger.getLogger(IngredienteBO.class.getName());
    /**
     * Instancia del DAO que maneja la persistencia de ingredientes.
     */
    private IIngredienteDAO ingredienteDAO;

    /**
     * Constructor que recibe el DAO como dependencia. Se sigue el principio de
     * inversión de dependencias para facilitar la inyección de dependencias.
     *
     * @param ingredienteDAO DAO que se usará para acceder a la base de datos.
     */
    public IngredienteBO(IIngredienteDAO ingredienteDAO) {
        this.ingredienteDAO = ingredienteDAO;
    }
    
    @Override
    public Ingrediente agregarIngrediente(IngredienteNuevoDTO ingredienteNuevo) throws NegocioException {
        // Validar campos vacíos
        validarIngredienteDTO(ingredienteNuevo);
        
        // Validar que un ingrediente no tenga nombre y unidad repetidos
        validarNombreConUnidadMedida(ingredienteNuevo.getNombre(), 
                ingredienteNuevo.getUnidadMedida());

        // Convertir DTO a entidad
        Ingrediente ingrediente = IngredienteMapper.toEntity(ingredienteNuevo);

        try {
            return ingredienteDAO.agregar(ingrediente);
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al agregar ingrediente", ex);
            throw new NegocioException("Error al agregar ingrediente", ex);
        }
    }

    @Override
    public boolean eliminarIngrediente(Long id) throws NegocioException {
        try {
            return ingredienteDAO.eliminar(id);
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al eliminar ingrediente", ex);
            throw new NegocioException("Error al eliminar ingrediente", ex);
        }
    }

    @Override
    public Ingrediente actualizarIngrediente(IngredienteNuevoDTO ingredienteNuevo) throws NegocioException {
        // Validaciones de campos vacíos
        validarIngredienteDTO(ingredienteNuevo);

        // Convertir DTO a entidad
        Ingrediente ingrediente = IngredienteMapper.toEntity(ingredienteNuevo);

        try {
            return ingredienteDAO.actualizar(ingrediente);
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al actualizar ingrediente", ex);
            throw new NegocioException("Error al actualizar ingrediente", ex);
        }
    }

    @Override
    public Ingrediente obtenerIngredientePorId(Long id) throws NegocioException {
        try {
            return ingredienteDAO.obtenerPorId(id);
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al obtener ingrediente por ID", ex);
            throw new NegocioException("Error al obtener ingrediente por ID", ex);
        }
    }

    @Override
    public List<IngredienteViejoDTO> obtenerTodos() throws NegocioException {
        try {
            return IngredienteMapper.toViejoDTOList(ingredienteDAO.obtenerTodos());
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al obtener todos los ingredientes", ex);
            throw new NegocioException("Error al obtener todos los ingredientes", ex);
        }
    }

    @Override
    public List<IngredienteViejoDTO> obtenerPorNombre(String nombre) throws NegocioException {
        try {
            return IngredienteMapper.toViejoDTOList(ingredienteDAO.obtenerPorNombre(nombre));
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al obtener ingredientes por nombre", ex);
            throw new NegocioException("Error al obtener ingredientes por nombre", ex);
        }
    }

    @Override
    public List<IngredienteViejoDTO> obtenerPorUnidadMedida(UnidadMedida unidadMedida) throws NegocioException {
        try {
            return IngredienteMapper.toViejoDTOList(ingredienteDAO.obtenerPorUnidadMedida(unidadMedida));
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al obtener ingredientes por unidad de medida", ex);
            throw new NegocioException("Error al obtener ingredientes por unidad de medida", ex);
        }
    }

    /**
     * Validación estándar (campos vacios)
     * @param ingredienteNuevo el ingrediente que se esta agregando
     * @throws NegocioException Si no cumple con los caractéres suficientes, etc.
     */
    private void validarIngredienteDTO(IngredienteNuevoDTO ingredienteNuevo) throws NegocioException {
        if (ingredienteNuevo == null) {
            throw new NegocioException("El ingrediente no puede ser nulo.");
        }
        if (ingredienteNuevo.getNombre() == null || ingredienteNuevo.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del ingrediente no puede estar vacío.");
        }
        if (ingredienteNuevo.getUnidadMedida() == null) {
            throw new NegocioException("La unidad de medida del ingrediente no puede ser nula.");
        }
    }
    
    private void validarNombreConUnidadMedida(String nombre, UnidadMedida unidadMedida) throws NegocioException{
        try{
            List<Ingrediente> ingredientes = ingredienteDAO.obtenerTodos();
            
            //recorrer los ingredientes
            for (Ingrediente ingrediente : ingredientes) {
                if(ingrediente.getNombre().equalsIgnoreCase(nombre) 
                        && ingrediente.getUnidadMedida() == unidadMedida){
                    throw new NegocioException("Ya existe un ingrediente con el mismo nombre y unidad de medida.");
                }
            }
        } catch(PersistenciaException pe){
            LOGGER.log(Level.SEVERE, "Error al validar el nombre y unidad existente", pe);
            throw new NegocioException("Error al validar el nombre y unidad existente");
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo;

import DTOs.nuevos.DetalleComandaNuevoDTO;
import DTOs.viejos.DetalleComandaViejoDTO;
import entidades.DetalleComanda;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mappers.DetalleComandaMapper;
import modulo.productos.DetalleComandaDAO;
import modulo.productos.IDetalleComandaDAO;

/**
 *
 * @author Maximiliano
 */
public class DetalleComandaBO implements IDetalleComandaBO
{
    private static final Logger LOGGER = Logger.getLogger(DetalleComandaBO.class.getName());
    private final IDetalleComandaDAO detalleDAO;

    /*public DetalleComandaBO() {
        this.detalleDAO = DetalleComandaDAO.getInstance();
    }*/
    
    public DetalleComandaBO() {
        this.detalleDAO = DetalleComandaDAO.getInstance();
    }

    public DetalleComandaBO(IDetalleComandaDAO detalleDAO) {
        this.detalleDAO = detalleDAO;
    }
    
    @Override
    public DetalleComanda agregarDetalleComanda(DetalleComandaNuevoDTO detalleNuevo) throws NegocioException {
        validarDetalleNuevo(detalleNuevo);

        DetalleComanda detalle = DetalleComandaMapper.toEntity(detalleNuevo);

        try {
            return detalleDAO.agregar(detalle);
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al agregar detalle de comanda", ex);
            throw new NegocioException("Error al agregar detalle de comanda", ex);
        }
    }

    @Override
    public DetalleComanda modificarDetalleComanda(DetalleComandaViejoDTO detalleViejo) throws NegocioException {
        validarDetalleViejo(detalleViejo);

        DetalleComanda detalle = DetalleComandaMapper.toEntity(detalleViejo);

        try {
            return detalleDAO.actualizar(detalle);
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al modificar detalle de comanda", ex);
            throw new NegocioException("Error al modificar detalle de comanda", ex);
        }
    }

    @Override
    public boolean eliminarDetalleComanda(Long id) throws NegocioException {
        try {
            return detalleDAO.eliminar(id);
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al eliminar detalle de comanda", ex);
            throw new NegocioException("Error al eliminar detalle de comanda", ex);
        }
    }

    @Override
    public List<DetalleComandaViejoDTO> obtenerPorIdComanda(Long idComanda) throws NegocioException {
        try {
            List<DetalleComanda> detalles = detalleDAO.obtenerPorIdComanda(idComanda);
            return DetalleComandaMapper.toViejoDTOList(detalles);
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al obtener detalles por ID de comanda", ex);
            throw new NegocioException("Error al obtener detalles por ID de comanda", ex);
        }
    }

    @Override
    public List<DetalleComandaViejoDTO> obtenerPorIdProducto(Long idProducto) throws NegocioException {
        try {
            List<DetalleComanda> detalles = detalleDAO.obtenerPorIdProducto(idProducto);
            return DetalleComandaMapper.toViejoDTOList(detalles);
        } catch (PersistenciaException ex) {
            LOGGER.log(Level.SEVERE, "Error al obtener detalles por ID de producto", ex);
            throw new NegocioException("Error al obtener detalles por ID de producto", ex);
        }
    }

    private void validarDetalleNuevo(DetalleComandaNuevoDTO dto) throws NegocioException {
        if (dto == null) {
            throw new NegocioException("El detalle no puede ser nulo.");
        }
        if (dto.getCantidad() == null || dto.getCantidad() <= 0) {
            throw new NegocioException("La cantidad debe ser mayor a cero.");
        }
        if (dto.getPrecioUnitario() == null || dto.getPrecioUnitario() <= 0) {
            throw new NegocioException("El precio unitario debe ser mayor a cero.");
        }
        if (dto.getImporteTotal() == null || dto.getImporteTotal() <= 0) {
            throw new NegocioException("El importe total debe ser mayor a cero.");
        }
        if (dto.getIdComanda() == null || dto.getIdProducto() == null) {
            throw new NegocioException("Debe especificar ID de comanda y producto.");
        }
    }

    private void validarDetalleViejo(DetalleComandaViejoDTO dto) throws NegocioException {
        if (dto == null || dto.getId() == null) {
            throw new NegocioException("El detalle o su ID no pueden ser nulos.");
        }
        validarDetalleNuevo(new DetalleComandaNuevoDTO(
            dto.getCantidad(),
            dto.getImporteTotal(),
            dto.getPrecioUnitario(),
            dto.getNotas(),
            dto.getIdComanda(),
            dto.getIdProducto()
        ));
    }
}

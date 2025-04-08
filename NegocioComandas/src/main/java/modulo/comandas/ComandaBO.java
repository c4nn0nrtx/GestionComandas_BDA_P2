/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo.comandas;

import DTOs.nuevos.ComandaNuevoDTO;
import DTOs.viejos.ComandaViejoDTO;
import ENUMs.EstadoComanda;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mappers.ComandaMapper;

/**
 * La clase {@code ComandaBO} implementa la interfaz {@link IComandaBO} y
 * contiene la lógica de negocio para la gestión de las comandas. Actúa como
 * una capa intermedia entre la capa de presentación (UI) y la capa de acceso
 * a datos (DAO). Realiza validaciones de negocio, generación de folios y
 * conversiones entre DTOs y entidades.
 * @author Beto_
 */
public class ComandaBO implements IComandaBO{
    private static final Logger LOGGER = Logger.getLogger(MesaBO.class.getName());
    private static final String PREFIJO = "OB";
    private static final DateTimeFormatter FORMATEO_FECHA = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final AtomicInteger CONSECUTIVO = new AtomicInteger(0);
    
    /**
     * Instancia del DAO que maneja la persistencia de las comandas.
     */
    private IComandaDAO comandaDAO;

    /**
     * Constructor que recibe el DAO como dependencia. Se sigue el principio de
     * inversión de dependencias para facilitar la inyección de dependencias
     * y mejorar la testabilidad.
     *
     * @param comandaDAO DAO que se usará para acceder a la base de datos.
     * No debe ser {@code null}.
     */
    public ComandaBO(IComandaDAO comandaDAO) {
        this.comandaDAO = comandaDAO;
    }

    /**
     * Agrega una nueva comanda al sistema. Realiza validaciones de negocio
     * sobre el DTO de entrada, genera el folio, establece el estado y la fecha/hora
     * antes de persistirla utilizando el DAO. Finalmente, convierte la entidad
     * persistida a un DTO de salida.
     *
     * @param comandaNueva DTO que contiene la información de la nueva comanda
     * a agregar. No debe ser {@code null}.
     * @return Un {@link ComandaViejoDTO} que representa la comanda agregada.
     * @throws NegocioException Si se produce alguna violación de las reglas de
     * negocio durante la validación o si ocurre un
     * error en la capa de persistencia.
     */
    @Override
    public ComandaViejoDTO agregar(ComandaNuevoDTO comandaNueva) throws NegocioException {
        if (comandaNueva == null) {
            throw new NegocioException("La comanda no puede ser nula.");
        }

        if (comandaNueva.getIdMesa() == null) {
            throw new NegocioException("La comanda debe tener una mesa.");
        }

        if (comandaNueva.getTotalVenta() == null) {
            throw new NegocioException("La comanda debe tener el total de la venta.");
        }

        // Llenar los campos que no llena el usuario
        comandaNueva.setEstado(EstadoComanda.ABIERTA);
        comandaNueva.setFolio(generarFolio());
        comandaNueva.setFechaHora(LocalDateTime.now());

        try {
            return ComandaMapper.toViejoDTO(comandaDAO.agregar(ComandaMapper.toEntity(comandaNueva)));
        } catch (PersistenciaException pe) {
            LOGGER.log(Level.SEVERE, "Error al agregar la comanda", pe);
            throw new NegocioException("Error al agregar la comanda", pe);
        }
    }

    /**
     * Elimina una comanda del sistema basado en su ID.
     *
     * @param id El ID de la comanda a eliminar. Debe ser un valor positivo.
     * @return {@code true} si la comanda fue eliminada exitosamente;
     * {@code false} en caso contrario.
     * @throws NegocioException Si ocurre un error en la capa de persistencia.
     */
    @Override
    public boolean eliminar(Long id) throws NegocioException {
        try {
            comandaDAO.eliminar(id);
            return true;
        } catch (PersistenciaException pe) {
            LOGGER.log(Level.SEVERE, "Error al eliminar la comanda", pe);
            throw new NegocioException("Error al eliminar la comanda", pe);
        }
    }

    /**
     * Actualiza la información general de una comanda existente. Realiza
     * validaciones sobre el DTO de entrada antes de actualizarla utilizando el DAO.
     *
     * @param comandaVieja DTO que contiene la información actualizada de la
     * comanda. Debe incluir el ID de la comanda a actualizar.
     * @return Un {@link ComandaViejoDTO} que representa la comanda actualizada.
     * @throws NegocioException Si se produce alguna violación de las reglas de
     * negocio durante la validación o si ocurre un
     * error en la capa de persistencia.
     */
    @Override
    public ComandaViejoDTO actualizar(ComandaViejoDTO comandaVieja) throws NegocioException {
        if (comandaVieja == null) {
            throw new NegocioException("La comanda no puede ser nula.");
        }

        if (comandaVieja.getId() == null || comandaVieja.getId() <= 0) {
            throw new NegocioException("No se identificó la comanda.");
        }

        if (comandaVieja.getEstado() == null) {
            throw new NegocioException("No se identificó el estado.");
        }

        if (comandaVieja.getTotalVenta() == null) {
            throw new NegocioException("La comanda debe tener el total de la venta.");
        }

        if (comandaVieja.getIdMesa() == null) {
            throw new NegocioException("La comanda debe tener una mesa.");
        }

        if (obtenerPorId(comandaVieja.getId()) == null) {
            throw new NegocioException("La comanda no existe");
        }

        try {
            return ComandaMapper.toViejoDTO(comandaDAO.actualizar(ComandaMapper.toEntity(comandaVieja)));
        } catch (PersistenciaException pe) {
            LOGGER.log(Level.SEVERE, "Error al actualizar la comanda", pe);
            throw new NegocioException("Error al actualizar la comanda", pe);
        }
    }

    /**
     * Actualiza el estado de una comanda existente.
     *
     * @param comandaVieja DTO que contiene el ID de la comanda y el nuevo estado.
     * @return Un {@link ComandaViejoDTO} que representa la comanda con el
     * estado actualizado.
     * @throws NegocioException Si la comanda es nula o si el estado a actualizar
     * es nulo, o si ocurre un error en la capa de persistencia.
     */
    @Override
    public ComandaViejoDTO actualizarEstado(ComandaViejoDTO comandaVieja) throws NegocioException {
        if (comandaVieja == null) {
            throw new NegocioException("La comanda no puede ser nula.");
        }

        if (comandaVieja.getEstado() == null) {
            throw new NegocioException("No se puede actualizar a un estado nulo.");
        }

        try {
            return ComandaMapper.toViejoDTO(comandaDAO.actualizar(ComandaMapper.toEntity(comandaVieja)));
        } catch (PersistenciaException pe) {
            LOGGER.log(Level.SEVERE, "Error al actualizar el estado de la comanda", pe);
            throw new NegocioException("Error al actualizar el estado de la comanda", pe);
        }
    }

    /**
     * Obtiene una comanda del sistema basado en su ID.
     *
     * @param id El ID de la comanda a buscar. Debe ser un valor positivo.
     * @return Un {@link ComandaViejoDTO} que representa la comanda encontrada,
     * o {@code null} si no existe.
     * @throws NegocioException Si ocurre un error en la capa de persistencia.
     */
    @Override
    public ComandaViejoDTO obtenerPorId(Long id) throws NegocioException {
        try {
            return ComandaMapper.toViejoDTO(comandaDAO.obtenerPorId(id));
        } catch (PersistenciaException pe) {
            LOGGER.log(Level.SEVERE, "Error al obtener comanda por id", pe);
            throw new NegocioException("Error al obtener comanda por id", pe);
        }
    }

    /**
     * Obtiene una comanda del sistema basado en su folio único.
     *
     * @param folio El folio de la comanda a buscar.
     * @return Un {@link ComandaViejoDTO} que representa la comanda encontrada,
     * o {@code null} si no existe o si el folio no es válido.
     * @throws NegocioException Si el folio no es válido (nulo, vacío o no cumple
     * el patrón) o si ocurre un error en la capa de persistencia.
     */
    @Override
    public ComandaViejoDTO obtenerPorFolio(String folio) throws NegocioException {
        if (folio == null || folio.isEmpty()) {
            throw new NegocioException("El folio no es válido");
        }

        // Verificar si cumple con el patrón del folio
        if (!itMatchesFolio(folio)) {
            throw new NegocioException("Patrón no válido. Ej: 'OB-20191026-001'");
        }

        try {
            return ComandaMapper.toViejoDTO(comandaDAO.obtenerPorFolio(folio));
        } catch (PersistenciaException pe) {
            LOGGER.log(Level.SEVERE, "Error al obtener comanda por folio", pe);
            throw new NegocioException("Error al obtener comanda por folio", pe);
        }
    }

    /**
     * Obtiene una lista de comandas dentro de un rango de fechas especificado.
     *
     * @param fechaInicio La fecha de inicio del rango (inclusive).
     * @param fechaFin La fecha de fin del rango (inclusive).
     * @return Una {@link List} de {@link ComandaViejoDTO} que representan las
     * comandas encontradas dentro del rango de fechas. Puede ser una lista
     * vacía si no hay comandas en ese rango.
     * @throws NegocioException Si las fechas de inicio o fin son nulas, si el
     * periodo de fechas es inválido o si ocurre un error
     * en la capa de persistencia.
     */
    @Override
    public List<ComandaViejoDTO> obtenerPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws NegocioException {
        if (fechaInicio == null || fechaFin == null) {
            throw new NegocioException("Las fechas están vacías");
        }
        if (fechaInicio.isAfter(fechaFin) || fechaFin.isBefore(fechaInicio)) {
            throw new NegocioException("Periodo de fechas inválido");
        }
        // hora y minutos 0 para que no afecte la búsqueda por día completo
        fechaInicio = LocalDateTime.of(fechaInicio.getYear(), fechaInicio.getMonthValue(), fechaInicio.getDayOfMonth(), 0, 0);
        fechaFin = LocalDateTime.of(fechaFin.getYear(), fechaFin.getMonthValue(), fechaFin.getDayOfMonth(), 23, 59, 59);

        try {
            List<ComandaViejoDTO> comandasFecha = ComandaMapper.toViejoDTOList(comandaDAO.obtenerPorFechas(fechaInicio, fechaFin));
            if (comandasFecha == null) {
                throw new NegocioException("No se obtuvieron comandas con estas fechas");
            }
            // Regresamos las comandas por fecha
            return comandasFecha;
        } catch (PersistenciaException pe) {
            LOGGER.log(Level.SEVERE, "Error al obtener comanda por fechas", pe);
            throw new NegocioException("Error al obtener comanda por fechas", pe);
        }
    }

    /**
     * Obtiene una lista de todas las comandas almacenadas en el sistema.
     *
     * @return Una {@link List} de {@link ComandaViejoDTO} que representan todas
     * las comandas encontradas. Puede ser una lista vacía si no hay comandas.
     * @throws NegocioException Si ocurre un error en la capa de persistencia.
     */
    @Override
    public List<ComandaViejoDTO> obtenerTodos() throws NegocioException {
        try {
            return ComandaMapper.toViejoDTOList(comandaDAO.obtenerTodos());
        } catch (PersistenciaException pe) {
            LOGGER.log(Level.SEVERE, "Error al obtener todas las comandas", pe);
            throw new NegocioException("Error al obtener todos las comandas", pe);
        }
    }

    /**
     * Asigna un cliente a una comanda específica. Realiza validaciones de negocio
     * antes de delegar la operación al DAO.
     *
     * @param idComanda El ID de la comanda a la que se asignará el cliente.
     * @param idCliente El ID del cliente que se asignará a la comanda.
     * @return {@code true} si la asignación se realizó correctamente.
     * @throws NegocioException Si los IDs de la comanda o del cliente no son
     * válidos, si la comanda no existe o si ocurre un error
     * en la capa de persistencia.
     */
    @Override
    public boolean asignarCliente(Long idComanda, Long idCliente) throws NegocioException {
        try {
            if (idComanda == null || idComanda <= 0) {
                throw new NegocioException("No se identificó la comanda");
            }

            if (idCliente == null || idCliente <= 0) {
                throw new NegocioException("No se identificó el cliente");
            }

            // TODO: Validar si el cliente existe en la capa de persistencia (ClienteDAO)

            if (obtenerPorId(idComanda) == null) {
                throw new NegocioException("La comanda no existe");
            }

            comandaDAO.asignarCliente(idComanda, idCliente);
            return true;
        } catch (PersistenciaException pe) {
            LOGGER.log(Level.SEVERE, "Error al asignar al cliente", pe);
            throw new NegocioException("Error al asignar al cliente", pe);
        }
    }

    /**
     * Desasigna el cliente asociado a una comanda específica. Realiza
     * validaciones de negocio antes de delegar la operación al DAO.
     *
     * @param idComanda El ID de la comanda de la cual se desasignará el cliente.
     * @return {@code true} si la desasignación se realizó correctamente.
     * @throws NegocioException Si el ID de la comanda no es válido, si la
     * comanda no existe o si ocurre un error en la capa de persistencia.
     */
    @Override
    public boolean desasignarCliente(Long idComanda) throws NegocioException {
        try {
            if (idComanda == null || idComanda <= 0) {
                throw new NegocioException("No se identificó la comanda");
            }

            if (obtenerPorId(idComanda) == null) {
                throw new NegocioException("La comanda no existe");
            }

            comandaDAO.desasignarCliente(idComanda);
            return true;
        } catch (PersistenciaException pe) {
            LOGGER.log(Level.SEVERE, "Error al desasignar al cliente", pe);
            throw new NegocioException("Error al desasignar al cliente", pe);
        }
    }
    
    /**
     * Genera un folio único para la comanda. El folio tiene el formato
     * "OB-YYYYMMDD-XXX", donde YYYYMMDD es la fecha actual y XXX es un
     * consecutivo de tres dígitos que se incrementa por cada comanda generada
     * en el día. Intenta generar un folio único hasta 15 veces para evitar
     * colisiones.
     *
     * @return El folio único generado para la comanda.
     * @throws NegocioException Si no se puede generar un folio único después de
     * varios intentos.
     */
    private String generarFolio() throws NegocioException{
        String folioGenerado = "Folio no generado";
        int intentos=0; //le pondremos intentos para q no haya loop infinito
        while(intentos < 15){ //Unos 15, peror depende de las reglas del negocio
            
            //General el folio
            LocalDate fechaActual = LocalDate.now();
            String fechaFormateada = fechaActual.format(FORMATEO_FECHA);
            int consecutivoActual = CONSECUTIVO.incrementAndGet();
            folioGenerado = String.format("%s-%s-%03d", PREFIJO, fechaFormateada, consecutivoActual);
            
            //Busca por folio, si encuentra registros, no devuelve nada y repite
            ComandaViejoDTO comandaExistente = obtenerPorFolio(folioGenerado);
            if (comandaExistente == null) {
                return folioGenerado;
            }
        }
        return folioGenerado;
    }
    
    /**
     * Método auxiliar para verificar si un string coincide
     * con el formato del folio.
     * 
     * @param folio El folio único a verificar.
     * @return true si coincide con el patrón, false si no.
     */
    private boolean itMatchesFolio(String folio){
        String patronFolio = "^OB-\\d{8}-\\d{3}$";
        Pattern pattern = Pattern.compile(patronFolio);
        
        //Regresa si matchea o no, si entra en el patrón pues
        Matcher matcher = pattern.matcher(folio);
        return matcher.matches();
    }
}

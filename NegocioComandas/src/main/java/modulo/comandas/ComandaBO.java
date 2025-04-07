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
 *
 * @author Beto_
 */
public class ComandaBO implements IComandaBO{
    private static final Logger LOGGER = Logger.getLogger(MesaBO.class.getName());
    private static final String PREFIJO = "OB";
    private static final DateTimeFormatter FORMATEO_FECHA = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final AtomicInteger CONSECUTIVO = new AtomicInteger(0);
    /**
     * Instancia del DAO que maneja la persistencia de ingredientes.
     */
    private IComandaDAO comandaDAO;

    /**
     * Constructor que recibe el DAO como dependencia. Se sigue el principio de
     * inversión de dependencias para facilitar la inyección de dependencias.
     *
     * @param comandaDAO DAO que se usará para acceder a la base de datos.
     */
    public ComandaBO(IComandaDAO comandaDAO) {
        this.comandaDAO = comandaDAO;
    }

    @Override
    public ComandaViejoDTO agregar(ComandaNuevoDTO comandaNueva) throws NegocioException {
        if(comandaNueva == null){
            throw new NegocioException("La comanda no puede ser nula.");
        }
        
        if(comandaNueva.getIdMesa() == null){
            throw new NegocioException("La comanda debe tener una mesa.");
        }
        
        if(comandaNueva.getTotalVenta() == null){
            throw new NegocioException("La comanda debe tener el total de la venta.");
        }
        
        //Llenar los campos que no llena el usuario
        comandaNueva.setEstado(EstadoComanda.ABIERTA);
        comandaNueva.setFolio(generarFolio());
        comandaNueva.setFechaHora(LocalDateTime.now());

        try{
            return ComandaMapper.toViejoDTO(comandaDAO.agregar(ComandaMapper.toEntity(comandaNueva)));
        }catch(PersistenciaException pe){
            LOGGER.log(Level.SEVERE, "Error al agregar la comanda", pe);
            throw new NegocioException("Error al agregar la comanda", pe);
        }
    }

    @Override
    public boolean eliminar(Long id) throws NegocioException {
        try{
            comandaDAO.eliminar(id);
            return true;
        }catch(PersistenciaException pe){
            LOGGER.log(Level.SEVERE, "Error al actualizar la comanda", pe);
            throw new NegocioException("Error al actualizar la comanda", pe);
        }
    }

    @Override
    public ComandaViejoDTO actualizar(ComandaViejoDTO comandaVieja) throws NegocioException {
        if(comandaVieja == null){
            throw new NegocioException("La comanda no puede ser nula.");
        }
        
        if(comandaVieja.getId() == null || comandaVieja.getId() <= 0){
            throw new NegocioException("No se identificó la comanda.");
        }
        
        if(comandaVieja.getEstado() == null){
            throw new NegocioException("No se identificó el estado.");
        }
                
        if(comandaVieja.getTotalVenta() == null){
            throw new NegocioException("La comanda debe tener el total de la venta.");
        }
        
        if(comandaVieja.getIdMesa() == null){
            throw new NegocioException("La comanda debe tener una mesa.");
        }
        
        if(obtenerPorId(comandaVieja.getId()) != null){
            throw new NegocioException("La comanda no existe");
        }
        
        try{
            return ComandaMapper.toViejoDTO(comandaDAO.actualizar(ComandaMapper.toEntity(comandaVieja)));
        }catch(PersistenciaException pe){
            LOGGER.log(Level.SEVERE, "Error al actualizar la comanda", pe);
            throw new NegocioException("Error al actualizar la comanda", pe);
        }
    }
    
    @Override
    public ComandaViejoDTO actualizarEstado(ComandaViejoDTO comandaVieja) throws NegocioException {
        if(comandaVieja == null){
            throw new NegocioException("La comanda no puede ser nula.");
        }
        
        if(comandaVieja.getEstado() == null){
            throw new NegocioException("No se puede actualizar a un estado nulo.");
        }
        
        try{
            return ComandaMapper.toViejoDTO(comandaDAO.actualizar(ComandaMapper.toEntity(comandaVieja)));
        }catch(PersistenciaException pe){
            LOGGER.log(Level.SEVERE, "Error al actualizar la comanda", pe);
            throw new NegocioException("Error al actualizar la comanda", pe);
        }
    }

    @Override
    public ComandaViejoDTO obtenerPorId(Long id) throws NegocioException {
        try{
            return ComandaMapper.toViejoDTO(comandaDAO.obtenerPorId(id));
        }catch(PersistenciaException pe){
            LOGGER.log(Level.SEVERE, "Error al obtener comanda por id", pe);
            throw new NegocioException("Error al obtener comanda por id", pe);
        }    
    }
    
    @Override
    public ComandaViejoDTO obtenerPorFolio(String folio) throws NegocioException{
        if(folio == null || folio.isEmpty()){
            throw new NegocioException("El folio no es válido");
        }
        
        //Verificar si cumple con el patrón del folio
        if(!itMatchesFolio(folio)){
           throw new NegocioException("Patrón no válido. Ej: 'OB-20191026-001'");
        }
        
        try{
            return ComandaMapper.toViejoDTO(comandaDAO.obtenerPorFolio(folio));
        }catch(PersistenciaException pe){
            LOGGER.log(Level.SEVERE, "Error al obtener comanda por id", pe);
            throw new NegocioException("Error al obtener comanda por id", pe);
        } 
    }

    @Override
    public List<ComandaViejoDTO> obtenerTodos() throws NegocioException {
        try{
            return ComandaMapper.toViejoDTOList(comandaDAO.obtenerTodos());
        }catch(PersistenciaException pe){
            LOGGER.log(Level.SEVERE, "Error al obtener todas las comandas", pe);
            throw new NegocioException("Error al obtener todos las comandas", pe);
        }
    }

    @Override
    public boolean asignarCliente(Long idComanda, Long idCliente) throws NegocioException {
        try{
            if(idComanda == null || idComanda <= 0){
                throw new NegocioException("No se identificó la comanda");
            }
            
            if(idCliente == null || idComanda <= 0){
                throw new NegocioException("No se identificó el cliente");
            }
            
            if(obtenerPorId(idComanda) != null){
                throw new NegocioException("La comanda no existe");
            } // validacion de cliente en persistencia
            
            comandaDAO.asignarCliente(idComanda, idCliente);
            return true;
        }catch(PersistenciaException pe){
            LOGGER.log(Level.SEVERE, "Error al asignar al cliente", pe);
            throw new NegocioException("Error al asignar al cliente", pe);
        }
    }

    @Override
    public boolean desasignarCliente(Long idComanda) throws NegocioException {
        try{
            if(idComanda == null || idComanda <= 0){
                throw new NegocioException("No se identificó la comanda");
            }
            
            if(obtenerPorId(idComanda) != null){
                throw new NegocioException("La comanda no existe");
            }
            
            comandaDAO.desasignarCliente(idComanda);
            return true;
        }catch(PersistenciaException pe){
            LOGGER.log(Level.SEVERE, "Error al desasignar al cliente", pe);
            throw new NegocioException("Error al desasignar al cliente", pe);
        }
    }
    
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
    
    private boolean itMatchesFolio(String folio){
        String patronFolio = "^OB-\\d{8}-\\d{3}$";
        Pattern pattern = Pattern.compile(patronFolio);
        
        //Regresa si matchea o no, si entra en el patrón pues
        Matcher matcher = pattern.matcher(folio);
        return matcher.matches();
    }
}

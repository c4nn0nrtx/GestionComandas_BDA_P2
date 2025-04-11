package mappers;

import DTOs.nuevos.ClienteFrecuenteNuevoDTO;
import DTOs.viejos.ClienteFrecuenteViejoDTO;
import entidades.ClienteFrecuente;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilitaria para mapear entre entidades ClienteFrecuente y DTOs
 * ClientesFrecuentesDTO.
 *
 * Proporciona métodos para convertir entre las representaciones de datos.
 *
 * @author brand
 */
public class ClienteFrecuenteMapper {

    /**
     * Convierte un objeto ClienteFrecuenteNuevoDTO en una entidad
     * ClienteFrecuente.
     *
     * @param dto El objeto DTO a convertir.
     * @return Una entidad ClienteFrecuente o null si el DTO es null.
     */
    public static ClienteFrecuente nuevodtoToEntity(ClienteFrecuenteNuevoDTO dto) {
        if (dto == null) {
            return null;
        }
        ClienteFrecuente frecuente = new ClienteFrecuente();
        frecuente.setNombres(dto.getNombres());
        frecuente.setApellidoPaterno(dto.getaPaterno());
        frecuente.setApellidoMaterno(dto.getaMaterno());
        frecuente.setFechaRegistro(dto.getFechaRegistro());
        frecuente.setTotalGastado(dto.getTotalGastado());
        frecuente.setVisitas(dto.getVisitas());
        frecuente.setPuntosFidelidad(dto.getPuntosFidelidad());
        return frecuente;
    }

    /**
     * Convierte un objeto ClienteFrecuenteViejoDTO en una entidad
     * ClienteFrecuente.
     *
     * @param dto El objeto DTO a convertir.
     * @return Una entidad ClienteFrecuente o null si el DTO es null.
     */
    public static ClienteFrecuente viejodtoToEntity(ClienteFrecuenteViejoDTO dto) {
        if (dto == null) {
            return null;
        }
        ClienteFrecuente frecuente = new ClienteFrecuente();
        frecuente.setId(dto.getId());
        frecuente.setNombres(dto.getNombres());
        frecuente.setApellidoPaterno(dto.getaPaterno());
        frecuente.setApellidoMaterno(dto.getaMaterno());
        frecuente.setFechaRegistro(dto.getFechaRegistro());
        frecuente.setTotalGastado(dto.getTotalGastado());
        frecuente.setVisitas(dto.getVisitas());
        frecuente.setPuntosFidelidad(dto.getPuntosFidelidad());
        return frecuente;
    }

    /**
     * Convierte una entidad ClienteFrecuente en un objeto
     * ClienteFrecuenteNuevoDTO.
     *
     * @param frecuente La entidad ClienteFrecuente a convertir.
     * @return Un objeto DTO o null si la entidad es null.
     */
    public static ClienteFrecuenteNuevoDTO entityToNuevodto(ClienteFrecuente frecuente) {
        if (frecuente == null) {
            return null;
        }
        return new ClienteFrecuenteNuevoDTO(
                frecuente.getNombres(),
                frecuente.getApellidoPaterno(),
                frecuente.getApellidoMaterno(),
                frecuente.getCorreo(),
                frecuente.getTelefono(),
                frecuente.getFechaRegistro(),
                frecuente.getTotalGastado(),
                frecuente.getVisitas(),
                frecuente.getPuntosFidelidad()
        );
    }

    /**
     * Convierte una entidad ClienteFrecuente en un objeto
     * ClienteFrecuenteViejoDTO.
     *
     * @param frecuente La entidad ClienteFrecuente a convertir.
     * @return Un objeto DTO o null si la entidad es null.
     */
    public static ClienteFrecuenteViejoDTO entityToViejodto(ClienteFrecuente frecuente) {
        if (frecuente == null) {
            return null;
        }
        return new ClienteFrecuenteViejoDTO(
                frecuente.getId(),
                frecuente.getNombres(),
                frecuente.getApellidoPaterno(),
                frecuente.getApellidoMaterno(),
                frecuente.getCorreo(),
                frecuente.getTelefono(),
                frecuente.getFechaRegistro(),
                frecuente.getTotalGastado(),
                frecuente.getVisitas(),
                frecuente.getPuntosFidelidad()
        );
    }

    /**
     * Convierte una lista de entidades ClienteFrecuente en una lista de objetos
     * ClienteFrecuenteViejoDTO.
     *
     * @param frecuentes Lista de entidades ClienteFrecuente.
     * @return Lista de DTOs o null si la lista de entrada es null.
     */
    public static List<ClienteFrecuenteViejoDTO> entityListToViejodtoList(List<ClienteFrecuente> frecuentes) {
        if (frecuentes == null) {
            return null;
        }
        List<ClienteFrecuenteViejoDTO> dtos = new ArrayList<>();
        for (ClienteFrecuente frecuente : frecuentes) {
            dtos.add(entityToViejodto(frecuente));
        }
        return dtos;
    }
    
    /**
     * Convierte una lista de entidades ClienteFrecuente en una lista de objetos
     * ClienteFrecuenteViejoDTO.
     *
     * @param frecuentes Lista de entidades ClienteFrecuente.
     * @return Lista de DTOs o null si la lista de entrada es null.
     */
    public static List<ClienteFrecuenteNuevoDTO> entityListToNuevodtoList(List<ClienteFrecuente> frecuentes) {
        if (frecuentes == null) {
            return null;
        }
        List<ClienteFrecuenteNuevoDTO> dtos = new ArrayList<>();
        for (ClienteFrecuente frecuente : frecuentes) {
            dtos.add(entityToNuevodto(frecuente));
        }
        return dtos;
    }

    /**
     * Convierte una lista de objetos ClienteFrecuenteNuevoDTO en una lista de
     * entidades ClienteFrecuente.
     *
     * @param dtos Lista de objetos DTO.
     * @return Lista de entidades ClienteFrecuente o null si la lista de entrada
     * es null.
     */
    public static List<ClienteFrecuente> nuevodtoListToEntityList(List<ClienteFrecuenteNuevoDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<ClienteFrecuente> frecuentes = new ArrayList<>();
        for (ClienteFrecuenteNuevoDTO dto : dtos) {
            frecuentes.add(nuevodtoToEntity(dto));
        }
        return frecuentes;
    }
    
    /**
     * Convierte una lista de objetos ClienteFrecuenteViejoDTO en una lista de
     * entidades ClienteFrecuente.
     *
     * @param dtos Lista de objetos DTO.
     * @return Lista de entidades ClienteFrecuente o null si la lista de entrada
     * es null.
     */
    public static List<ClienteFrecuente> viejodtoListToEntityList(List<ClienteFrecuenteViejoDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<ClienteFrecuente> frecuentes = new ArrayList<>();
        for (ClienteFrecuenteViejoDTO dto : dtos) {
            frecuentes.add(viejodtoToEntity(dto));
        }
        return frecuentes;
    }
}

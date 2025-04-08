package mappers;

import DTOs.nuevos.ClientesFrecuentesDTO;
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
     * Convierte un objeto ClientesFrecuentesDTO en una entidad
     * ClienteFrecuente.
     *
     * @param dto El objeto DTO a convertir.
     * @return Una entidad ClienteFrecuente o null si el DTO es null.
     */
    public static ClienteFrecuente dtoToEntity(ClientesFrecuentesDTO dto) {
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
     * Convierte una entidad ClienteFrecuente en un objeto
     * ClientesFrecuentesDTO.
     *
     * @param frecuente La entidad ClienteFrecuente a convertir.
     * @return Un objeto DTO o null si la entidad es null.
     */
    public static ClientesFrecuentesDTO entityToDTO(ClienteFrecuente frecuente) {
        if (frecuente == null) {
            return null;
        }
        return new ClientesFrecuentesDTO(
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
     * ClientesFrecuentesDTO.
     *
     * @param frecuentes Lista de entidades ClienteFrecuente.
     * @return Lista de DTOs o null si la lista de entrada es null.
     */
    public static List<ClientesFrecuentesDTO> entityListToDTOList(List<ClienteFrecuente> frecuentes) {
        if (frecuentes == null) {
            return null;
        }
        List<ClientesFrecuentesDTO> dtos = new ArrayList<>();
        for (ClienteFrecuente frecuente : frecuentes) {
            dtos.add(entityToDTO(frecuente));
        }
        return dtos;
    }

    /**
     * Convierte una lista de objetos ClientesFrecuentesDTO en una lista de
     * entidades ClienteFrecuente.
     *
     * @param dtos Lista de objetos DTO.
     * @return Lista de entidades ClienteFrecuente o null si la lista de entrada
     * es null.
     */
    public static List<ClienteFrecuente> dtoListToEntityList(List<ClientesFrecuentesDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<ClienteFrecuente> frecuentes = new ArrayList<>();
        for (ClientesFrecuentesDTO dto : dtos) {
            frecuentes.add(dtoToEntity(dto));
        }
        return frecuentes;
    }
}

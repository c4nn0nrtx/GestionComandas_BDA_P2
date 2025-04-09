/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.nuevos.IngredienteNuevoDTO;
import DTOs.viejos.IngredienteViejoDTO;
import entidades.Ingrediente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Beto_
 */
public class IngredienteMapper {

    /**
     *
     * @param dto
     * @return
     */
    public static Ingrediente toEntity(IngredienteNuevoDTO dto) {
        if (dto == null) {
            return null;
        }
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre(dto.getNombre());
        ingrediente.setUnidadMedida(dto.getUnidadMedida());
        ingrediente.setStock(dto.getStock());
        return ingrediente;
    }

    /**
     *
     * @param ingrediente
     * @return
     */
    public static IngredienteViejoDTO toViejoDTO(Ingrediente ingrediente) {
        if (ingrediente == null) {
            return null;
        }
        return new IngredienteViejoDTO(
                ingrediente.getId(),
                ingrediente.getNombre(),
                ingrediente.getUnidadMedida(),
                ingrediente.getStock()
        );
    }

    /**
     *
     * @param ingredienteNuevoDTO
     * @return
     */
    public static IngredienteViejoDTO toViejoDTO(IngredienteNuevoDTO ingredienteNuevoDTO){
        if(ingredienteNuevoDTO == null){
            return null;
        }
        return new IngredienteViejoDTO(
                null,
                ingredienteNuevoDTO.getNombre(),
                ingredienteNuevoDTO.getUnidadMedida(),
                ingredienteNuevoDTO.getStock()
        );
    }

    /**
     *
     * @param dto
     * @return
     */
    public static Ingrediente toEntity(IngredienteViejoDTO dto) {
        if (dto == null) {
            return null;
        }
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setId(dto.getId()); // Asumiendo Long o Integer
        ingrediente.setNombre(dto.getNombre());
        ingrediente.setUnidadMedida(dto.getUnidadMedida());
        ingrediente.setStock(dto.getStock()); // Asumiendo Double
        return ingrediente;
    }

    /**
     *
     * @param ingredientes
     * @return
     */
    public static List<IngredienteViejoDTO> toViejoDTOList(List<Ingrediente> ingredientes) {
        if (ingredientes == null) {
            return null;
        }
        List<IngredienteViejoDTO> dtos = new ArrayList<>();
        for (Ingrediente ingrediente : ingredientes) {
            dtos.add(toViejoDTO(ingrediente));
        }
        return dtos;
    }

    /**
     *
     * @param dtos
     * @return
     */
    public static List<Ingrediente> toEntityList(List<IngredienteNuevoDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<Ingrediente> ingredientes = new ArrayList<>();
        for (IngredienteNuevoDTO dto : dtos) {
            ingredientes.add(toEntity(dto));
        }
        return ingredientes;
    }

    /**
     *
     * @param dtos
     * @return
     */
    public static List<Ingrediente> toEntityViejoDTOList(List<IngredienteViejoDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<Ingrediente> ingredientes = new ArrayList<>();
        for (IngredienteViejoDTO dto : dtos) {
            ingredientes.add(toEntity(dto));
        }
        return ingredientes;
    }
}

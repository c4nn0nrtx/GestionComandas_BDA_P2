/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.nuevos.MesaNuevoDTO;
import DTOs.viejos.MesaViejoDTO;
import entidades.Mesa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Beto_
 */
public class MesaMapper {
    public static Mesa toEntity(MesaNuevoDTO dto) {
        if (dto == null) {
            return null;
        }
        Mesa mesa = new Mesa();
        mesa.setNumeroMesa(dto.getNumeroMesa());
        return mesa;
    }

    public static MesaViejoDTO toViejoDTO(Mesa mesa) {
        if (mesa == null) {
            return null;
        }
        return new MesaViejoDTO(
                mesa.getId(),
                mesa.getNumeroMesa()
        );
    }

    public static MesaViejoDTO toViejoDTO(MesaNuevoDTO mesaNuevoDTO) {
        if (mesaNuevoDTO == null) {
            return null;
        }
        return new MesaViejoDTO(
                null, // ID puede ser nulo al crear desde NuevoDTO
                mesaNuevoDTO.getNumeroMesa()
        );
    }

    // Convierte MesaViejoDTO a Mesa
    public static Mesa toEntity(MesaViejoDTO dto) {
        if (dto == null) {
            return null;
        }
        Mesa mesa = new Mesa();
        mesa.setId(dto.getId());
        mesa.setNumeroMesa(dto.getNumeroMesa());
        return mesa;
    }

    public static List<MesaViejoDTO> toViejoDTOList(List<Mesa> mesas) {
        if (mesas == null) {
            return null;
        }
        List<MesaViejoDTO> dtos = new ArrayList<>();
        for (Mesa mesa : mesas) {
            dtos.add(toViejoDTO(mesa));
        }
        return dtos;
    }

    public static List<Mesa> toEntityList(List<MesaNuevoDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<Mesa> mesas = new ArrayList<>();
        for (MesaNuevoDTO dto : dtos) {
            mesas.add(toEntity(dto));
        }
        return mesas;
    }

    public static List<Mesa> toEntityViejoDTOList(List<MesaViejoDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<Mesa> mesas = new ArrayList<>();
        for (MesaViejoDTO dto : dtos) {
            mesas.add(toEntity(dto));
        }
        return mesas;
    }
}

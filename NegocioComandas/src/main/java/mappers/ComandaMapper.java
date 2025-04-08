/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.nuevos.ComandaNuevoDTO;
import DTOs.viejos.ComandaViejoDTO;
import entidades.Cliente;
import entidades.Comanda;
import entidades.Mesa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Beto_
 */
public class ComandaMapper {

    /**
     *
     * @param dto
     * @return
     */
    public static Comanda toEntity(ComandaNuevoDTO dto) {
        if (dto == null) {
            return null;
        }
        Comanda comanda = new Comanda();
        comanda.setFolio(dto.getFolio());
        comanda.setEstado(dto.getEstado());
        comanda.setFechaHora(dto.getFechaHora());
        comanda.setTotalVenta(dto.getTotalVenta());

        
        if (dto.getIdMesa() != null) {
            Mesa mesa = new Mesa();
            mesa.setId(dto.getIdMesa());
            comanda.setMesa(mesa);
        }
        if (dto.getIdCliente() != null) {
            Cliente cliente = new Cliente();
            cliente.setId(dto.getIdCliente());
            comanda.setCliente(cliente);
        }
        return comanda;
    }

    /**
     *
     * @param comanda
     * @return
     */
    public static ComandaViejoDTO toViejoDTO(Comanda comanda) {
        if (comanda == null) {
            return null;
        }
        Long idMesa = (comanda.getMesa() != null) ? comanda.getMesa().getId() : null;
        Long idCliente = (comanda.getCliente() != null) ? comanda.getCliente().getId() : null;

        return new ComandaViejoDTO(
                comanda.getId(),
                comanda.getFolio(),
                comanda.getEstado(),
                comanda.getFechaHora(),
                comanda.getTotalVenta(),
                idMesa,
                idCliente
        );
    }

    /**
     *
     * @param comandaNuevoDTO
     * @return
     */
    public static ComandaViejoDTO toViejoDTO(ComandaNuevoDTO comandaNuevoDTO) {
        if (comandaNuevoDTO == null) {
            return null;
        }
        return new ComandaViejoDTO(
                null, // ID puede ser nulo al crear desde NuevoDTO
                comandaNuevoDTO.getFolio(),
                comandaNuevoDTO.getEstado(),
                comandaNuevoDTO.getFechaHora(),
                comandaNuevoDTO.getTotalVenta(),
                comandaNuevoDTO.getIdMesa(),
                comandaNuevoDTO.getIdCliente()
        );
    }

    /**
     *
     * @param dto
     * @return
     */
    public static Comanda toEntity(ComandaViejoDTO dto) {
        if (dto == null) {
            return null;
        }
        Comanda comanda = new Comanda();
        comanda.setId(dto.getId());
        comanda.setFolio(dto.getFolio());
        comanda.setEstado(dto.getEstado());
        comanda.setFechaHora(dto.getFechaHora());
        comanda.setTotalVenta(dto.getTotalVenta());

        if (dto.getIdMesa() != null) {
            Mesa mesa = new Mesa();
            mesa.setId(dto.getIdMesa());
            comanda.setMesa(mesa);
        }
        if (dto.getIdCliente() != null) {
            Cliente cliente = new Cliente();
            cliente.setId(dto.getIdCliente());
            comanda.setCliente(cliente);
        }
        return comanda;
    }

    /**
     *
     * @param comandas
     * @return
     */
    public static List<ComandaViejoDTO> toViejoDTOList(List<Comanda> comandas) {
        if (comandas == null) {
            return null;
        }
        List<ComandaViejoDTO> dtos = new ArrayList<>();
        for (Comanda comanda : comandas) {
            dtos.add(toViejoDTO(comanda));
        }
        return dtos;
    }

    /**
     *
     * @param dtos
     * @return
     */
    public static List<Comanda> toEntityList(List<ComandaNuevoDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<Comanda> comandas = new ArrayList<>();
        for (ComandaNuevoDTO dto : dtos) {
            comandas.add(toEntity(dto));
        }
        return comandas;
    }

    /**
     *
     * @param dtos
     * @return
     */
    public static List<Comanda> toEntityViejoDTOList(List<ComandaViejoDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<Comanda> comandas = new ArrayList<>();
        for (ComandaViejoDTO dto : dtos) {
            comandas.add(toEntity(dto));
        }
        return comandas;
    }
}

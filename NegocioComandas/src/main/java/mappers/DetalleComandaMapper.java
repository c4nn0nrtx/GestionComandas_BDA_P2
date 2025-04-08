/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.nuevos.DetalleComandaNuevoDTO;
import DTOs.viejos.DetalleComandaViejoDTO;
import entidades.Comanda;
import entidades.DetalleComanda;
import entidades.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maximiliano
 */
public class DetalleComandaMapper 
{
    public static DetalleComanda toEntity(DetalleComandaNuevoDTO dto) {
        if (dto == null) {
            return null;
        }

        DetalleComanda detalle = new DetalleComanda();
        detalle.setCantidad(dto.getCantidad());
        detalle.setImporteTotal(dto.getImporteTotal());
        detalle.setPrecioUnitario(dto.getPrecioUnitario());
        detalle.setNotas(dto.getNotas());

        if (dto.getIdProducto() != null) {
            Producto producto = new Producto();
            producto.setId(dto.getIdProducto());
            detalle.setProducto(producto);
        }

        if (dto.getIdComanda() != null) {
            Comanda comanda = new Comanda();
            comanda.setId(dto.getIdComanda());
            detalle.setComanda(comanda);
        }

        return detalle;
    }

    public static DetalleComandaViejoDTO toViejoDTO(DetalleComanda detalle) {
        if (detalle == null) {
            return null;
        }

        return new DetalleComandaViejoDTO(
                detalle.getId(),
                detalle.getCantidad(),
                detalle.getImporteTotal(),
                detalle.getPrecioUnitario(),
                detalle.getNotas(),
                detalle.getComanda() != null ? detalle.getComanda().getId() : null,
                detalle.getProducto() != null ? detalle.getProducto().getId() : null
        );
    }

    public static DetalleComandaViejoDTO toViejoDTO(DetalleComandaNuevoDTO dto) {
        if (dto == null) {
            return null;
        }

        return new DetalleComandaViejoDTO(
                null,
                dto.getCantidad(),
                dto.getImporteTotal(),
                dto.getPrecioUnitario(),
                dto.getNotas(),
                dto.getIdComanda(),
                dto.getIdProducto()
        );
    }

    public static DetalleComanda toEntity(DetalleComandaViejoDTO dto) {
        if (dto == null) {
            return null;
        }

        DetalleComanda detalle = new DetalleComanda();
        detalle.setId(dto.getId());
        detalle.setCantidad(dto.getCantidad());
        detalle.setImporteTotal(dto.getImporteTotal());
        detalle.setPrecioUnitario(dto.getPrecioUnitario());
        detalle.setNotas(dto.getNotas());

        if (dto.getIdProducto() != null) {
            Producto producto = new Producto();
            producto.setId(dto.getIdProducto());
            detalle.setProducto(producto);
        }

        if (dto.getIdComanda() != null) {
            Comanda comanda = new Comanda();
            comanda.setId(dto.getIdComanda());
            detalle.setComanda(comanda);
        }

        return detalle;
    }

    public static List<DetalleComandaViejoDTO> toViejoDTOList(List<DetalleComanda> detalles) {
        if (detalles == null) {
            return null;
        }

        List<DetalleComandaViejoDTO> dtos = new ArrayList<>();
        for (DetalleComanda detalle : detalles) {
            dtos.add(toViejoDTO(detalle));
        }
        return dtos;
    }

    public static List<DetalleComanda> toEntityList(List<DetalleComandaNuevoDTO> dtos) {
        if (dtos == null) {
            return null;
        }

        List<DetalleComanda> detalles = new ArrayList<>();
        for (DetalleComandaNuevoDTO dto : dtos) {
            detalles.add(toEntity(dto));
        }
        return detalles;
    }

    public static List<DetalleComanda> toEntityViejoDTOList(List<DetalleComandaViejoDTO> dtos) {
        if (dtos == null) {
            return null;
        }

        List<DetalleComanda> detalles = new ArrayList<>();
        for (DetalleComandaViejoDTO dto : dtos) {
            detalles.add(toEntity(dto));
        }
        return detalles;
    }

}

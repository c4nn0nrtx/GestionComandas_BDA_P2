/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.nuevos.ProductoNuevoDTO;
import DTOs.viejos.ProductoViejoDTO;
import entidades.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maximiliano
 */
public class ProductoMapper 
{
    public static Producto toEntity(ProductoNuevoDTO dto) {
        if (dto == null) {
            return null;
        }
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setTipo(dto.getTipo());
        producto.setEstado(true); // Se habilita por defecto al crearlo
        return producto;
    }

    public static ProductoViejoDTO toViejoDTO(Producto producto) {
        if (producto == null) {
            return null;
        }
        return new ProductoViejoDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getTipo(),
                producto.getPrecio(),
                producto.isEstado()
        );
    }

    public static ProductoViejoDTO toViejoDTO(ProductoNuevoDTO productoNuevoDTO) {
        if (productoNuevoDTO == null) {
            return null;
        }
        return new ProductoViejoDTO(
                null,
                productoNuevoDTO.getNombre(),
                productoNuevoDTO.getTipo(),
                productoNuevoDTO.getPrecio(),
                true
        );
    }

    public static Producto toEntity(ProductoViejoDTO dto) {
        if (dto == null) {
            return null;
        }
        Producto producto = new Producto();
        producto.setId(dto.getId());
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setTipo(dto.getTipo());
        producto.setEstado(dto.getEstado());
        return producto;
    }

    public static List<ProductoViejoDTO> toViejoDTOList(List<Producto> productos) {
        if (productos == null) {
            return null;
        }
        List<ProductoViejoDTO> dtos = new ArrayList<>();
        for (Producto producto : productos) {
            dtos.add(toViejoDTO(producto));
        }
        return dtos;
    }

    public static List<Producto> toEntityList(List<ProductoNuevoDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<Producto> productos = new ArrayList<>();
        for (ProductoNuevoDTO dto : dtos) {
            productos.add(toEntity(dto));
        }
        return productos;
    }

    public static List<Producto> toEntityViejoDTOList(List<ProductoViejoDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<Producto> productos = new ArrayList<>();
        for (ProductoViejoDTO dto : dtos) {
            productos.add(toEntity(dto));
        }
        return productos;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import DTOs.nuevos.ProductoIngredienteNuevoDTO;
import DTOs.viejos.ProductoIngredienteViejoDTO;
import entidades.Ingrediente;
import entidades.Producto;
import entidades.ProductoIngrediente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maximiliano
 */
public class ProductoIngredienteMapper 
{
    public static ProductoIngrediente toEntity(ProductoIngredienteNuevoDTO dto) {
        if (dto == null) {
            return null;
        }

        ProductoIngrediente pi = new ProductoIngrediente();
        pi.setCantidadRequerida(dto.getCantidadRequerida());

        if (dto.getIdProducto() != null) {
            Producto producto = new Producto();
            producto.setId(dto.getIdProducto());
            pi.setProducto(producto);
        }

        if (dto.getIdIngrediente() != null) {
            Ingrediente ingrediente = new Ingrediente();
            ingrediente.setId(dto.getIdIngrediente());
            pi.setIngrediente(ingrediente);
        }

        return pi;
    }

    public static ProductoIngrediente toEntity(ProductoIngredienteViejoDTO dto) {
        if (dto == null) {
            return null;
        }

        ProductoIngrediente pi = new ProductoIngrediente();
        pi.setId(dto.getId());
        pi.setCantidadRequerida(dto.getCantidadRequerida());

        if (dto.getIdProducto() != null) {
            Producto producto = new Producto();
            producto.setId(dto.getIdProducto());
            pi.setProducto(producto);
        }

        if (dto.getIdIngrediente() != null) {
            Ingrediente ingrediente = new Ingrediente();
            ingrediente.setId(dto.getIdIngrediente());
            pi.setIngrediente(ingrediente);
        }

        return pi;
    }

    public static ProductoIngredienteViejoDTO toViejoDTO(ProductoIngrediente pi) {
        if (pi == null) {
            return null;
        }

        return new ProductoIngredienteViejoDTO(
                pi.getId(),
                pi.getCantidadRequerida(),
                pi.getProducto() != null ? pi.getProducto().getId() : null,
                pi.getIngrediente() != null ? pi.getIngrediente().getId() : null
        );
    }

    public static ProductoIngredienteViejoDTO toViejoDTO(ProductoIngredienteNuevoDTO dto) {
        if (dto == null) {
            return null;
        }

        return new ProductoIngredienteViejoDTO(
                null,
                dto.getCantidadRequerida(),
                dto.getIdProducto(),
                dto.getIdIngrediente()
        );
    }

    public static List<ProductoIngredienteViejoDTO> toViejoDTOList(List<ProductoIngrediente> lista) {
        if (lista == null) {
            return null;
        }

        List<ProductoIngredienteViejoDTO> dtos = new ArrayList<>();
        for (ProductoIngrediente pi : lista) {
            dtos.add(toViejoDTO(pi));
        }
        return dtos;
    }

    public static List<ProductoIngrediente> toEntityList(List<ProductoIngredienteNuevoDTO> dtos) {
        if (dtos == null) {
            return null;
        }

        List<ProductoIngrediente> lista = new ArrayList<>();
        for (ProductoIngredienteNuevoDTO dto : dtos) {
            lista.add(toEntity(dto));
        }
        return lista;
    }

    public static List<ProductoIngrediente> toEntityViejoDTOList(List<ProductoIngredienteViejoDTO> dtos) {
        if (dtos == null) {
            return null;
        }

        List<ProductoIngrediente> lista = new ArrayList<>();
        for (ProductoIngredienteViejoDTO dto : dtos) {
            lista.add(toEntity(dto));
        }
        return lista;
    }

}

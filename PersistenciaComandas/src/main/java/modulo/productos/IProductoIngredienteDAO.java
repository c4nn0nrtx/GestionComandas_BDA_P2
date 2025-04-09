/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modulo.productos;

import entidades.ProductoIngrediente;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Maximiliano
 */
public interface IProductoIngredienteDAO 
{
    public ProductoIngrediente agregar(ProductoIngrediente productoIngrediente) throws PersistenciaException;

    public boolean eliminar(Long id) throws PersistenciaException;

    public List<ProductoIngrediente> obtenerPorProducto(Long idProducto) throws PersistenciaException;

}

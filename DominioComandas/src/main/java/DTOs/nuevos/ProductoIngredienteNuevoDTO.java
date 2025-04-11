/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.nuevos;

/**
 * La clase ProductoNuevoDTO es un DTO (Data Transfer Object) que
 * encapsula la información necesaria para registrar o modificar un producto
 * dentro del sistema. Esta clase representa los datos de entrada (creación o
 * actualización) desde la capa de presentación o lógica de negocio, antes de
 * ser transformados en una entidad persistente.
 * 
 * Este DTO es especialmente útil para separar las capas de la aplicación
 * (presentación, negocio y persistencia), evitando así el acoplamiento directo
 * con la entidad Producto.
 * 
 * Se utiliza principalmente en las operaciones de alta y modificación
 * de productos.
 * 
 * Atributos incluidos:
 *   nombre: nombre único que identifica al producto.
 *   tipo: tipo o categoría del producto (PLATILLO, BEBIDA, POSTRE).
 *   precio: precio unitario del producto.
 *   estado: indica si el producto está habilitado (true) o deshabilitado (false).
 * 
 * @author Maximiliano Reyna Aguilar
 */
public class ProductoIngredienteNuevoDTO 
{
    private double cantidadRequerida;
    private Long idProducto;
    private Long idIngrediente;

    public ProductoIngredienteNuevoDTO() {
    }

    public ProductoIngredienteNuevoDTO(double cantidadRequerida, Long idProducto, Long idIngrediente) {
        this.cantidadRequerida = cantidadRequerida;
        this.idProducto = idProducto;
        this.idIngrediente = idIngrediente;
    }

    public double getCantidadRequerida() {
        return cantidadRequerida;
    }

    public void setCantidadRequerida(double cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    @Override
    public String toString() {
        return "ProductoIngredienteNuevoDTO{" +
                "cantidadRequerida=" + cantidadRequerida +
                ", idProducto=" + idProducto +
                ", idIngrediente=" + idIngrediente +
                '}';
    }

}

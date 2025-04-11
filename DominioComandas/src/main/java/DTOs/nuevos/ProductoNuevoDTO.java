/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.nuevos;

import ENUMs.TipoProducto;

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
public class ProductoNuevoDTO {
    
    /**
     * El nombre del producto. Debe ser único, no nulo y no estar vacío.
     */
    private String nombre;
    
    /**
     * La categoría del producto, representada por el enumerador {@link TipoProducto}.
     * Puede tomar valores como PLATILLO, BEBIDA o POSTRE.
     */
    private TipoProducto tipo;
    
    /**
     * El precio del producto. Debe ser mayor a cero.
     */
    private double precio;
    
    /**
     * El estado del producto. Si es true, el producto está habilitado;
     * si es {@code false}, está deshabilitado.
     */
    private boolean estado;

    /**
     * Constructor por defecto.
     */
    public ProductoNuevoDTO() {
    }

    /**
     * Constructor que permite inicializar todos los campos del DTO.
     * 
     * @param nombre El nombre del producto
     * @param tipo El tipo o categoría del producto
     * @param precio El precio unitario
     * @param estado El estado habilitado o deshabilitado
     */
    public ProductoNuevoDTO(String nombre, TipoProducto tipo, double precio, boolean estado) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.estado = estado;
    }

     /**
     * Devuelve el nombre del producto.
     * 
     * @return nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     * 
     * @param nombre nuevo nombre del producto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el tipo (categoría) del producto.
     * 
     * @return tipo del producto
     */
    public TipoProducto getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo (categoría) del producto.
     * 
     * @param tipo nuevo tipo del producto
     */
    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    /**
     * Devuelve el precio del producto.
     * 
     * @return precio del producto
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     * 
     * @param precio nuevo precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Indica si el producto está habilitado o no.
     * 
     * @return {@code true} si está habilitado; {@code false} si está deshabilitado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Establece el estado del producto.
     * 
     * @param estado nuevo estado (true = habilitado, false = deshabilitado)
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Devuelve una representación en cadena del objeto.
     * 
     * @return Regresa cadena con todos los atributos del objeto
     */
    @Override
    public String toString() {
        return "ProductoNuevoDTO{" + "nombre=" + nombre + ", tipo=" + tipo + ", precio=" + precio + ", estado=" + estado + '}';
    }
}

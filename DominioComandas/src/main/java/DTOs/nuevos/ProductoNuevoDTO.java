/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.nuevos;

import ENUMs.TipoProducto;

/**
 *
 * @author Beto_
 */
public class ProductoNuevoDTO {
    private String nombre;
    private TipoProducto tipo;
    private double precio;
    private boolean estado;

    /**
     *
     */
    public ProductoNuevoDTO() {
    }

    /**
     *
     * @param nombre
     * @param tipo
     * @param precio
     * @param estado
     */
    public ProductoNuevoDTO(String nombre, TipoProducto tipo, double precio, boolean estado) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.estado = estado;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public TipoProducto getTipo() {
        return tipo;
    }

    /**
     *
     * @param tipo
     */
    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    /**
     *
     * @return
     */
    public double getPrecio() {
        return precio;
    }

    /**
     *
     * @param precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     *
     * @return
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     *
     * @param estado
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "ProductoNuevoDTO{" + "nombre=" + nombre + ", tipo=" + tipo + ", precio=" + precio + ", estado=" + estado + '}';
    }
}

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

    public ProductoNuevoDTO() {
    }

    public ProductoNuevoDTO(String nombre, TipoProducto tipo, double precio, boolean estado) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "ProductoNuevoDTO{" + "nombre=" + nombre + ", tipo=" + tipo + ", precio=" + precio + ", estado=" + estado + '}';
    }
}

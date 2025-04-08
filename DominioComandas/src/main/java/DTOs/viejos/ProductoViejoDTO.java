/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.viejos;

import ENUMs.TipoProducto;

/**
 *
 * @author Beto_
 */
public class ProductoViejoDTO {
    private Long id;
    private String nombre;
    private TipoProducto tipo;
    private Double precio;
    private Boolean estado;

    /**
     *
     */
    public ProductoViejoDTO() {
    }

    /**
     *
     * @param id
     * @param nombre
     * @param tipo
     * @param precio
     * @param estado
     */
    public ProductoViejoDTO(Long id, String nombre, TipoProducto tipo, Double precio, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.estado = estado;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
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
    public Double getPrecio() {
        return precio;
    }

    /**
     *
     * @param precio
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     *
     * @return
     */
    public Boolean getEstado() {
        return estado;
    }

    /**
     *
     * @param estado
     */
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "ProductoViejoDTO{" + "id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", precio=" + precio + ", estado=" + estado + '}';
    }
}

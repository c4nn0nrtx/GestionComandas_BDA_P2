/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.nuevos;

import ENUMs.UnidadMedida;

/**
 *
 * @author Beto_
 */
public class IngredienteNuevoDTO {
    private String nombre;
    private UnidadMedida unidadMedida;
    private double stock;

    /**
     *
     */
    public IngredienteNuevoDTO() {
    }

    /**
     *
     * @param nombre
     * @param unidadMedida
     * @param stock
     */
    public IngredienteNuevoDTO(String nombre, UnidadMedida unidadMedida, double stock) {
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.stock = stock;
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
    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    /**
     *
     * @param unidadMedida
     */
    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    /**
     *
     * @return
     */
    public double getStock() {
        return stock;
    }

    /**
     *
     * @param stock
     */
    public void setStock(double stock) {
        this.stock = stock;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "IngredienteNuevoDTO{" + "nombre=" + nombre + ", unidadMedida=" + unidadMedida + ", stock=" + stock + '}';
    }
    
}

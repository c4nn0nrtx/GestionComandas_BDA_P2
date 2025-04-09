/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import ENUMs.UnidadMedida;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Beto_
 */
@Entity
@Table(name = "ingredientes")
@NamedQuery(name = "Ingrediente.buscarPorNombre", query = "SELECT i FROM Ingrediente i WHERE i.nombre LIKE :nombre")
@NamedQuery(name = "Ingrediente.buscarPorUnidadMedida", query = "SELECT i FROM Ingrediente i WHERE i.unidadMedida = :unidadMedida")
public class Ingrediente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "unidadMedida", nullable = false)
    @Enumerated(EnumType.STRING)
    private UnidadMedida unidadMedida;
    
    @Column(name = "stock", nullable = false)
    private Double stock;
   
    /**
     *
     */
    public Ingrediente() {
    }

    /**
     *
     * @param nombre
     * @param unidadMedida
     * @param stock
     */
    public Ingrediente(String nombre, UnidadMedida unidadMedida, Double stock) {
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.stock = stock;
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
    public Double getStock() {
        return stock;
    }

    /**
     *
     * @param stock
     */
    public void setStock(Double stock) {
        this.stock = stock;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Ingrediente{" + "id=" + id + ", nombre=" + nombre + ", unidadMedida=" + unidadMedida + ", stock=" + stock + '}';
    }
}

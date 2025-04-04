/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import ENUMs.UnidadMedida;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Beto_
 */
@Entity
@Table(name = "ingredientes")
@NamedQuery(name = "Ingrediente.buscarPorNombre", query = "SELECT i FROM Ingrediente i WHERE i.nombre = :nombre")
@NamedQuery(name = "Ingrediente.buscarPorUnidadMedida", query = "SELECT i FROM Ingrediente i WHERE i.unidadMedida = :unidadMedida")
public class Ingrediente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "unidadMedida", nullable = false)
    private UnidadMedida unidadMedida;
    
    @Column(name = "stock", nullable = false)
    private double stock;
    
    @OneToMany(mappedBy = "ingrediente", fetch = FetchType.LAZY) //lista de detalles
    private List<ProductoIngrediente> productosIngredientes = new ArrayList<>();

    public Ingrediente() {
    }

    public Ingrediente(String nombre, UnidadMedida unidadMedida, double stock) {
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public List<ProductoIngrediente> getProductosIngredientes() {
        return productosIngredientes;
    }

    public void setProductosIngredientes(List<ProductoIngrediente> productosIngredientes) {
        this.productosIngredientes = productosIngredientes;
    }

    @Override
    public String toString() {
        return "Ingrediente{" + "id=" + id + ", nombre=" + nombre + ", unidadMedida=" + unidadMedida + ", stock=" + stock + ", productosIngredientes=" + productosIngredientes + '}';
    }
}

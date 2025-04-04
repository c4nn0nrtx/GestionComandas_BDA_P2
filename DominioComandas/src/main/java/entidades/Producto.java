/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import ENUMs.TipoProducto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Beto_
 */
@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
    
    @Column(name = "tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoProducto tipo;
    
    @Column(name = "precio", nullable = false)
    private Double precio;
    
    @Column(name = "estado", nullable = false)
    private boolean estado;
    
    //lista de ingredientes con cantidad
    @OneToMany(mappedBy = "producto", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE}, 
            orphanRemoval = true, fetch = FetchType.LAZY)
            //muchas cascadas al ser dependiente del producto
            //Igual utilizamos orphanRemoval al no tener sentido que exista
            //Si el producto no existe, sin embargo vamos a inhabilitar así que x
            //nada más para que cuadre con el dominio
    private List<ProductoIngrediente> productosIngredientes = new ArrayList<>();

    public Producto() {
    }

    public Producto(String nombre, TipoProducto tipo, double precio, boolean estado) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.estado = estado;
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

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<ProductoIngrediente> getProductosIngredientes() {
        return productosIngredientes;
    }

    public void setProductosIngredientes(List<ProductoIngrediente> productosIngredientes) {
        this.productosIngredientes = productosIngredientes;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", precio=" + precio + ", estado=" + estado + ", productosIngredientes=" + productosIngredientes + '}';
    }
    
}

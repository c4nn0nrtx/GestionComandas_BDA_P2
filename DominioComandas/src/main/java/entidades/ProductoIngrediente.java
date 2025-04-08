/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Beto_
 */
@Entity
@Table(name = "productosIngredientes")
public class ProductoIngrediente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "cantidadRequerida", nullable = false)
    private double cantidadRequerida;
    
    @ManyToOne(fetch = FetchType.LAZY) //lazy para mejor carga de datos aunque venga x defecto
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY) //lazy para mejor carga de datos
    @JoinColumn(name = "id_ingrediente")
    private Ingrediente ingrediente;

    /**
     *
     */
    public ProductoIngrediente() {
    }
    
    /**
     *
     * @param cantidadRequerida
     * @param producto
     * @param ingrediente
     */
    public ProductoIngrediente(double cantidadRequerida, Producto producto, Ingrediente ingrediente) {
        this.cantidadRequerida = cantidadRequerida;
        this.producto = producto;
        this.ingrediente = ingrediente;
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
    public double getCantidadRequerida() {
        return cantidadRequerida;
    }

    /**
     *
     * @param cantidadRequerida
     */
    public void setCantidadRequerida(double cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    /**
     *
     * @return
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     *
     * @param producto
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     *
     * @return
     */
    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    /**
     *
     * @param ingrediente
     */
    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "ProductoIngrediente{" + "id=" + id + ", cantidadRequerida=" + cantidadRequerida + ", producto=" + producto + ", ingrediente=" + ingrediente + '}';
    }
    
}

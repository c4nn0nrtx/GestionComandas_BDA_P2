/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
 * Esta clase representa la relación entre un producto y sus ingredientes.
 * Cada instancia de esta clase indica cuánta cantidad de un ingrediente específico
 * se necesita para preparar un producto determinado.
 * 
 * Esta clase actúa como una entidad de unión en una relación muchos a muchos
 * entre `Producto` e `Ingrediente`, pero con un atributo adicional (`cantidadRequerida`),
 * por lo que se modela como una entidad independiente.
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entidad JPA que representa la tabla `productosIngredientes` en la base de datos.
 * Su propósito es registrar qué ingredientes se necesitan para preparar un producto,
 * junto con la cantidad exacta requerida de cada uno.
 * 
 * También contiene NamedQueries para facilitar búsquedas comunes en la base de datos.
 * @author Maximiliano Reyna Aguilar.
 */
@Entity
@Table(name = "productosIngredientes")
@NamedQueries({
    
    /**
     * Este named query consulta y obtiene todas las relaciones producto-ingrediente
     * para un producto específico a través de su ID.
     */
    @NamedQuery(name = "ProductoIngrediente.porProducto",
                query = "SELECT pi FROM ProductoIngrediente pi WHERE pi.producto.id = :idProducto"),
    
    /**
     * Este named query consulta y obtiene todas las relaciones producto-ingrediente
     * para un ingrediente específico a través de su ID.
     */
    @NamedQuery(name = "ProductoIngrediente.porIngrediente",
                query = "SELECT pi FROM ProductoIngrediente pi WHERE pi.ingrediente.id = :idIngrediente")
})
public class ProductoIngrediente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * El identificador único de la relación producto-ingrediente.
     * Se genera automáticamente en la base de datos usando la estrategia de identidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * La cantidad requerida del ingrediente para preparar una unidad del producto.
     * Este valor no puede ser nulo en la base de datos.
     */
    @Column(name = "cantidadRequerida", nullable = false)
    private double cantidadRequerida;
    
    /**
     * Es la referencia al producto asociado.
     * Mapeado con `@ManyToOne` porque múltiples relaciones pueden apuntar al mismo producto.
     * Se usa `FetchType.LAZY` para que el producto se cargue solo si se accede explícitamente.
     */
    @ManyToOne(fetch = FetchType.LAZY) //lazy para mejor carga de datos aunque venga x defecto
    @JoinColumn(name = "id_producto")
    private Producto producto;

    /**
     * Esta es la referencia al ingrediente asociado.
     * Múltiples relaciones pueden compartir un mismo ingrediente.
     * Se usa `FetchType.LAZY` para mejorar el rendimiento.
     */
    @ManyToOne(fetch = FetchType.LAZY) //lazy para mejor carga de datos
    @JoinColumn(name = "id_ingrediente")
    private Ingrediente ingrediente;

    /**
     * Constructor vacío requerido por JPA.
     */
    public ProductoIngrediente() {
    }
    
    /**
     * El constructor que permite crear una relación producto-ingrediente completa.
     *
     * @param cantidadRequerida La cantidad del ingrediente necesaria para el producto.
     * @param producto Esta es la referencia al producto.
     * @param ingrediente Esta es la referencia al ingrediente.
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

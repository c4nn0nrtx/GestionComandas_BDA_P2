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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entidad JPA que representa un producto del sistema en la base de datos.
 * 
 * Esta clase modela los productos que pueden ser vendidos en el restaurante,
 * tales como platillos, postres y bebidas. Cada producto tiene un nombre único,
 * un tipo (categoría), un precio, un estado (activo/inactivo) y puede estar asociado
 * a una lista de ingredientes requeridos para su preparación (a través de la clase ProductoIngrediente).
 * 
 * La tabla correspondiente en la base de datos se llama "productos".
 * 
 * Además, se definen múltiples NamedQueries para facilitar búsquedas frecuentes como:
 * - búsqueda por nombre
 * - búsqueda por tipo
 * - verificación de existencia por nombre
 * - obtención de productos activos
 * - búsqueda por nombre o tipo con un filtro combinado
 * 
 * Esta clase se integra con otras entidades como ProductoIngrediente en una relación uno a muchos.
 * 
 * @author Maximiliano Reyna Aguilar
 */
@Entity
@Table(name = "productos")
@NamedQueries({
    
    /**
     * Este NamedQuery permite buscar productos cuyo nombre contenga una subcadena específica,
     * sin importar mayúsculas o minúsculas.
     */
    @NamedQuery(name = "Producto.buscarPorNombre",
                query = "SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))"),
    
    /**
     * Este NamedQuery permite filtrar productos por su tipo (PLATILLO, POSTRE, BEBIDA).
     */
    @NamedQuery(name = "Producto.buscarPorTipo",
                query = "SELECT p FROM Producto p WHERE p.tipo = :tipo"),
    
    /**
     * Este NamedQuery verifica si ya existe un producto con un nombre específico,
     * sin importar diferencias de mayúsculas/minúsculas. Devuelve un COUNT.
     */
    @NamedQuery(name = "Producto.existeNombre",
                query = "SELECT COUNT(p) FROM Producto p WHERE LOWER(p.nombre) = LOWER(:nombre)"),
    
    /**
     * Este NamedQuery recupera únicamente los productos activos (estado = true).
     */
    @NamedQuery(name = "Producto.todosActivos",
                query = "SELECT p FROM Producto p WHERE p.estado = true"),

    /**
     * Este NamedQuery permite buscar productos por nombre o tipo combinando filtros.
     * Útil para buscadores flexibles.
     */
    @NamedQuery(name = "Producto.buscarPorNombreOCategoria",
    query = "SELECT p FROM Producto p WHERE p.estado = true AND (LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombreFiltro, '%')) OR p.tipo = :tipoFiltro)")
})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * El identificador único del producto. Generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * El nombre del producto. No puede ser nulo ni repetido en la base de datos.
     */
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
    
    /**
     * El tipo del producto (Platillo, Postre o Bebida). Mapeado desde el Enum TipoProducto.
     * Se almacena como cadena de texto.
     */
    @Column(name = "tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoProducto tipo;
    
    /**
     * El precio del producto. No puede ser nulo y debe ser mayor a 0.
     */
    @Column(name = "precio", nullable = false)
    private Double precio;
    
    /**
     * El estado del producto. true = habilitado / false = deshabilitado.
     */
    @Column(name = "estado", nullable = false)
    private Boolean estado;
    
    /**
     * La relación uno a muchos con la entidad ProductoIngrediente.
     * Representa los ingredientes requeridos para preparar el producto.
     * 
     * - `mappedBy`: indica que la relación está mapeada desde el campo "producto" en ProductoIngrediente.
     * - `cascade`: permite propagar operaciones (persistencia, eliminación, actualización).
     * - `orphanRemoval`: permite eliminar relaciones huérfanas cuando se elimina un producto.
     * - `fetch = LAZY`: los ingredientes se cargan bajo demanda.
     */
    //lista de ingredientes con cantidad
    @OneToMany(mappedBy = "producto", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE}, 
            orphanRemoval = true, fetch = FetchType.LAZY)
            //muchas cascadas al ser dependiente del producto
            //Igual utilizamos orphanRemoval al no tener sentido que exista
            //Si el producto no existe, sin embargo vamos a inhabilitar así que x
            //nada más para que cuadre con el dominio
    private List<ProductoIngrediente> productosIngredientes = new ArrayList<>();

    /**
     * Constructor vacío de la entidad producto.
     */
    public Producto() {
    }

    /**
     * Constructor principal utilizado para crear un producto desde DTOs o formularios.
     * 
     * @param nombre El nombre del producto
     * @param tipo El tipo (categoría) del producto
     * @param precio El precio del producto
     * @param estado El estado (activo/inactivo)
     */
    public Producto(String nombre, TipoProducto tipo, double precio, boolean estado) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.estado = estado;
    }

    /**
     * Este método obtiene el ID único del producto.
     * 
     * @return Regresa el identificador del producto (Long). Es autogenerado por la base de datos.
     */
    public Long getId() {
        return id;
    }

    /**
     * Este método establece el ID único del producto.
     * Este valor usualmente no se asigna manualmente, ya que es generado automáticamente.
     * 
     * @param id El identificador del producto a asignar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Este método obtiene el nombre del producto.
     * 
     * @return Regresa el nombre asignado al producto (String). Este valor es único y no puede estar vacío.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Este método establece el nombre del producto.
     * Este nombre debe ser único y no debe contener solo espacios.
     * 
     * @param nombre El nombre del producto a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Este método obtiene el tipo o categoría del producto.
     * 
     * @return Regresa el tipo del producto como valor del enum TipoProducto (PLATILLO, POSTRE, BEBIDA).
     */
    public TipoProducto getTipo() {
        return tipo;
    }

    /**
     * Este método establece el tipo o categoría del producto.
     * 
     * @param tipo El tipo del producto (debe ser uno de los valores definidos en TipoProducto).
     */
    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    /**
     * Este método obtiene el precio actual del producto.
     * 
     * @return Regresa el precio como valor decimal. No debe ser nulo ni negativo.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Este método establece el precio del producto.
     * Debe ser un número mayor a cero para reflejar el valor real del producto.
     * 
     * @param precio El precio a asignar al producto.
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * Este método obtiene el estado del producto.
     * 
     * @return Regresa true si el producto está activo o habilitado; false si está deshabilitado.
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Este método establece el estado del producto.
     * 
     * @param estado El valor booleano que determina si el producto está activo.
     * true para habilitar el producto, false para deshabilitarlo.
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Este método obtiene la lista de ingredientes asociados a este producto junto con sus cantidades requeridas.
     * 
     * @return Regresa la lista de objetos ProductoIngrediente que representan la relación entre este producto
     * y los ingredientes necesarios para su elaboración.
     */
    public List<ProductoIngrediente> getProductosIngredientes() {
        return productosIngredientes;
    }

    /**
     * Este método establece la lista de ingredientes requeridos para este producto.
     * 
     * @param productosIngredientes La lista de relaciones ProductoIngrediente que definen 
     * qué ingredientes y en qué cantidad se necesitan para preparar el producto.
     */
    public void setProductosIngredientes(List<ProductoIngrediente> productosIngredientes) {
        this.productosIngredientes = productosIngredientes;
    }

    /**
     * Este método devuelve una representación en cadena del objeto `Producto`.
     * @return Regresa la cadena que representa los valores clave del objeto.
     */
    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", precio=" + precio + ", estado=" + estado + ", productosIngredientes=" + productosIngredientes + '}';
    }
    
}

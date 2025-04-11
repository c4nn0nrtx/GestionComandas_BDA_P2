/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.viejos;

import ENUMs.TipoProducto;

/*
 * Esta es la clase DTO (Data Transfer Object) de solo lectura para representar información de un producto ya registrado.
 * 
 * Esta clase forma parte del patrón DTO que se usa para transferir datos entre capas (como la capa de negocio
 * y la capa de presentación) sin exponer directamente las entidades JPA.
 * 
 * `ProductoViejoDTO` encapsula todos los datos que representan un producto existente, es decir, con un `id` ya
 * asignado y generalmente persistido en la base de datos. Se utiliza principalmente para consultas y visualizaciones,
 * no para inserciones.
 * 
 * Se complementa con `ProductoNuevoDTO`, el cual es usado para registrar nuevos productos (sin ID).
 * 
 * @author Maximiliano Reyna Aguilar.
 */
public class ProductoViejoDTO {
    
   /**
    * El identificador único del producto. Este campo es asignado por la base de datos al momento de persistir la entidad.
    * Es necesario para realizar operaciones de actualización, eliminación o búsquedas específicas.
    */
    private Long id;
    
    /**
     * El nombre del producto. Este campo debe ser único entre los productos activos.
     * Se utiliza para mostrar, buscar y validar la existencia de productos.
     */
    private String nombre;
    
    /**
     * La categoría o tipo del producto. Este campo es un enumerador definido en {@link ENUMs.TipoProducto},
     * y puede ser: PLATILLO, POSTRE o BEBIDA. Se usa en filtros, búsquedas y organización.
     */
    private TipoProducto tipo;
    
    /**
     * El precio de venta del producto. Debe ser mayor a cero. 
     * Se utiliza tanto para mostrar precios en pantalla como para cálculos financieros.
     */
    private Double precio;
    
   /**
    * El estado lógico del producto. Si es `true`, el producto está habilitado; si es `false`, se considera deshabilitado.
    * Esto permite la eliminación lógica sin borrar físicamente los datos.
    */
    private Boolean estado;

    /**
     * Constructor vacío del dto viejo de producto.
     */
    public ProductoViejoDTO() {
    }

    /**
     * Constructor completo que permite construir rápidamente un objeto DTO con todos sus atributos.
     * 
     * @param id El identificador único del producto.
     * @param nombre El nombre del producto.
     * @param tipo La categoría del producto (Platillo, Postre, Bebida).
     * @param precio El precio de venta.
     * @param estado El estado lógico (activo o inactivo).
     */
    public ProductoViejoDTO(Long id, String nombre, TipoProducto tipo, Double precio, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.estado = estado;
    }

    /**
     * Este método devuelve el ID del producto.
     * @return Regresa el ID único del producto.
     */
    public Long getId() {
        return id;
    }

    /**
     * Este método establece el ID del producto.
     * @param id El Identificador único asignado por la base de datos.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Este método devuelve el nombre del producto.
     * @return Regresa el nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Este método establece el nombre del producto.
     * @param nombre El nombre nuevo del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Este método devuelve el tipo del producto.
     * @return Regresa el Tipo (categoría) del producto.
     */
    public TipoProducto getTipo() {
        return tipo;
    }

    /**
     * Este método establece el tipo del producto.
     * @param tipo La categoría (Enum) del producto.
     */
    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    /**
     * Este método devuelve el precio del producto.
     * @return Regresa el precio actual del producto.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Este método establece el precio del producto.
     * @param precio El nuevo precio del producto (debe ser positivo).
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * Este método devuelve el estado del producto.
     * @return Regresa true si está habilitado; false si está deshabilitado.
     */
    public Boolean getEstado() {
        return estado;
    }

    /**
     * Este método establece el estado del producto.
     * @param estado El nuevo estado (true = activo, false = inactivo).
     */
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    /**
     * Esta es la representación en forma de cadena del objeto.
     * Útil para propósitos de debugging, logging o visualización rápida en consola.
     * 
     * @return Regresa la cadena con los valores principales del producto.
     */
    @Override
    public String toString() {
        return "ProductoViejoDTO{" + "id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", precio=" + precio + ", estado=" + estado + '}';
    }
}

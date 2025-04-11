/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.viejos;

/**
 * La clase ProductoIngredienteViejoDTO representa un DTO de lectura
 * (salida) que encapsula la información de una relación ya existente entre un
 * producto y un ingrediente específico en el sistema. Este DTO refleja los
 * datos almacenados en la base de datos y es utilizado en respuestas o
 * consultas de tipo `GET`.
 *
 * Su contraparte de entrada es ProductoIngredienteNuevoDTO,
 * que se usa para registrar nuevas relaciones.
 *
 * Este DTO tiene como propósito simplificar la exposición de la información,
 * evitando mostrar entidades completas y permitiendo el manejo de datos
 * esenciales y planos (solo IDs y atributos primitivos).
 *
 * Atributos incluidos:
 *   id: Identificador único de la relación producto-ingrediente.
 *   cantidadRequerida: Cantidad del ingrediente que se requiere para preparar el producto.
 *   idProducto: Identificador del producto asociado.
 *   idIngrediente: Identificador del ingrediente relacionado.
 * 
 * Este DTO se usa, por ejemplo, al consultar los ingredientes que conforman un platillo.
 * 
 * @author Maximiliano Reyna Aguilar
 */
public class ProductoIngredienteViejoDTO 
{
    /**
     * Identificador único de la relación {@code ProductoIngrediente} en la base de datos.
     */
    private Long id;

    /**
     * Cantidad de ingrediente necesaria para elaborar una unidad del producto.
     * Este valor debe ser mayor a cero.
     */
    private double cantidadRequerida;

    /**
     * Identificador del producto al que pertenece esta relación.
     * Representa la clave foránea hacia la entidad {@code Producto}.
     */
    private Long idProducto;

    /**
     * Identificador del ingrediente relacionado con el producto.
     * Representa la clave foránea hacia la entidad {@code Ingrediente}.
     */
    private Long idIngrediente;

    /**
     * Constructor vacío requerido.
     */
    public ProductoIngredienteViejoDTO() {
    }

    /**
     * Constructor completo que inicializa todos los campos del DTO.
     * 
     * @param id ID único de la relación
     * @param cantidadRequerida Cantidad del ingrediente necesaria
     * @param idProducto ID del producto
     * @param idIngrediente ID del ingrediente
     */
    public ProductoIngredienteViejoDTO(Long id, double cantidadRequerida, Long idProducto, Long idIngrediente) {
        this.id = id;
        this.cantidadRequerida = cantidadRequerida;
        this.idProducto = idProducto;
        this.idIngrediente = idIngrediente;
    }

    /**
     * Devuelve el ID único de esta relación.
     * 
     * @return ID de la relación producto-ingrediente
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID único de esta relación.
     * 
     * @param id nuevo ID a asignar
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve la cantidad requerida del ingrediente para el producto.
     * 
     * @return cantidad requerida
     */
    public double getCantidadRequerida() {
        return cantidadRequerida;
    }

    /**
     * Establece la cantidad requerida del ingrediente para el producto.
     * 
     * @param cantidadRequerida nueva cantidad a asignar
     */
    public void setCantidadRequerida(double cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    /**
     * Devuelve el ID del producto asociado a esta relación.
     * 
     * @return ID del producto
     */
    public Long getIdProducto() {
        return idProducto;
    }

    /**
     * Establece el ID del producto para esta relación.
     * 
     * @param idProducto nuevo ID de producto
     */
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Devuelve el ID del ingrediente asociado.
     * 
     * @return ID del ingrediente
     */
    public Long getIdIngrediente() {
        return idIngrediente;
    }

    /**
     * Establece el ID del ingrediente asociado.
     * 
     * @param idIngrediente nuevo ID del ingrediente
     */
    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    /**
     * Representación textual del objeto. Útil para mostrar en logs o debugging.
     * 
     * @return String con todos los campos del objeto
     */
    @Override
    public String toString() {
        return "ProductoIngredienteViejoDTO{" +
                "id=" + id +
                ", cantidadRequerida=" + cantidadRequerida +
                ", idProducto=" + idProducto +
                ", idIngrediente=" + idIngrediente +
                '}';
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package ENUMs;

/*
 * Enum que representa las diferentes categorías posibles de productos en el sistema.
 * 
 * Este enum es utilizado por la entidad {@link Producto} para clasificar los productos
 * disponibles en el restaurante. Las categorías ayudan a filtrar, organizar y mostrar los
 * productos en interfaces o reportes.
 * 
 * Las opciones disponibles son:
 * - PLATILLO: Comidas principales como hamburguesas, ensaladas, tacos, etc.
 * - POSTRE: Productos dulces servidos al final, como pasteles, helados, etc.
 * - BEBIDA: Refrescos, jugos, agua, cafés, etc.
 * 
 * Este enum se almacena en la base de datos como cadena (gracias a la anotación @Enumerated(EnumType.STRING))
 * dentro de la tabla productos.
 * 
 * @author Maximiliano Reyna Aguilar
 */
public enum TipoProducto {

    /**
     * Categoría para productos que representan comidas principales o platos fuertes.
     */
    PLATILLO,

    /**
     * Categoría para productos servidos como postres o alimentos dulces.
     */
    POSTRE,

    /**
     * Categoría que agrupa todas las bebidas (con o sin alcohol).
     */
    BEBIDA
}

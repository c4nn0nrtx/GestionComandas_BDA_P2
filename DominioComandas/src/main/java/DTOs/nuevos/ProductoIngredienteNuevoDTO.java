/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.nuevos;

/**
 *
 * @author Maximiliano
 */
public class ProductoIngredienteNuevoDTO 
{
    private double cantidadRequerida;
    private Long idProducto;
    private Long idIngrediente;

    public ProductoIngredienteNuevoDTO() {
    }

    public ProductoIngredienteNuevoDTO(double cantidadRequerida, Long idProducto, Long idIngrediente) {
        this.cantidadRequerida = cantidadRequerida;
        this.idProducto = idProducto;
        this.idIngrediente = idIngrediente;
    }

    public double getCantidadRequerida() {
        return cantidadRequerida;
    }

    public void setCantidadRequerida(double cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    @Override
    public String toString() {
        return "ProductoIngredienteNuevoDTO{" +
                "cantidadRequerida=" + cantidadRequerida +
                ", idProducto=" + idProducto +
                ", idIngrediente=" + idIngrediente +
                '}';
    }

}

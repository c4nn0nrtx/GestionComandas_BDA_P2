/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.nuevos;

/**
 *
 * @author Maximiliano
 */
public class DetalleComandaNuevoDTO 
{
    private Integer cantidad;
    private Double importeTotal;
    private Double precioUnitario;
    private String notas;
    private Long idComanda;
    private Long idProducto;

    public DetalleComandaNuevoDTO() {
    }

    public DetalleComandaNuevoDTO(Integer cantidad, Double importeTotal, Double precioUnitario, String notas, Long idComanda, Long idProducto) {
        this.cantidad = cantidad;
        this.importeTotal = importeTotal;
        this.precioUnitario = precioUnitario;
        this.notas = notas;
        this.idComanda = idComanda;
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(Double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Long getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(Long idComanda) {
        this.idComanda = idComanda;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public String toString() {
        return "DetalleComandaNuevoDTO{" +
                "cantidad=" + cantidad +
                ", importeTotal=" + importeTotal +
                ", precioUnitario=" + precioUnitario +
                ", notas='" + notas + '\'' +
                ", idComanda=" + idComanda +
                ", idProducto=" + idProducto +
                '}';
    }

}

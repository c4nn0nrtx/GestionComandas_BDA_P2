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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Beto_
 */
@Entity
@Table(name = "detalles_comanda")
@NamedQueries({
    @NamedQuery(name = "DetalleComanda.porProducto",
                query = "SELECT d FROM DetalleComanda d WHERE d.producto.id = :idProducto"),

    @NamedQuery(name = "DetalleComanda.porComanda",
                query = "SELECT d FROM DetalleComanda d WHERE d.comanda.id = :idComanda")
})
public class DetalleComanda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
    
    @Column(name = "importeTotal", nullable = false)
    private Double importeTotal;
    
    @Column(name = "precioUnitario", nullable = false)
    private Double precioUnitario;
    
    @Column(name = "notas", nullable = false)
    private String notas;
    
    @ManyToOne(fetch = FetchType.LAZY) //lazy para mejor carga de datos aunque venga x defecto
    @JoinColumn(name = "id_comanda")
    private Comanda comanda;

    @ManyToOne(fetch = FetchType.LAZY) //lazy para mejor carga de datos
    @JoinColumn(name = "id_producto")
    private Producto producto;

    public DetalleComanda() {
    }

    public DetalleComanda(Integer cantidad, Double importeTotal, Double precioUnitario, String notas, Comanda comanda, Producto producto) {
        this.cantidad = cantidad;
        this.importeTotal = importeTotal;
        this.precioUnitario = precioUnitario;
        this.notas = notas;
        this.comanda = comanda;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "DetalleComanda{" + "id=" + id + ", cantidad=" + cantidad + ", importeTotal=" + importeTotal + ", precioUnitario=" + precioUnitario + ", notas=" + notas + ", comanda=" + comanda + ", producto=" + producto + '}';
    }
}

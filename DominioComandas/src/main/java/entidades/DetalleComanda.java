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
    
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    
    @Column(name = "importeTotal", nullable = false)
    private double importeTotal;
    
    @Column(name = "precioUnitario", nullable = false)
    private double precioUnitario;
    
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

    public DetalleComanda(int cantidad, double importeTotal, double precioUnitario, String notas, Comanda comanda, Producto producto) {
        this.cantidad = cantidad;
        this.importeTotal = importeTotal;
        this.precioUnitario = precioUnitario;
        this.notas = notas;
        this.comanda = comanda;
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
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
        return "DetalleComanda{" + "cantidad=" + cantidad + ", importeTotal=" + importeTotal + ", precioUnitario=" + precioUnitario + ", notas=" + notas + ", comanda=" + comanda + ", producto=" + producto + '}';
    }
}

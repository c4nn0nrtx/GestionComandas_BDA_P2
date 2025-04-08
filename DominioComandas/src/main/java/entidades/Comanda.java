/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import ENUMs.EstadoComanda;
import java.io.Serializable;
import java.time.LocalDateTime;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Beto_
 */
@Entity
@Table(name = "comandas")
@NamedQuery(
        name = "Comanda.buscarPorFolio", 
        query = "SELECT c FROM Comanda c WHERE c.folio = :folio"
)
@NamedQuery(
    name = "Comanda.buscarPorRangoFechas",
    query = "SELECT c FROM Comanda c WHERE c.fechaHora > :fechaInicio AND c.fechaHora < :fechaFin"
)
public class Comanda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "folio", nullable = false, unique = true)
    private String folio;
    
    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoComanda estado;
    
    @Column(name = "fechaHora", nullable = false)
    private LocalDateTime fechaHora;
    
    @Column(name = "totalVenta", nullable = false)
    private Double totalVenta;
    
    @ManyToOne
    @JoinColumn(name = "id_mesa", nullable = false) //false pq no hay comandas sin mesa
    private Mesa mesa;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = true) //true pq puede haber comanda sin clienteFrecuente
    private Cliente cliente; //puede cambiar si cambiamos a Joined!
    
    @OneToMany(mappedBy = "comanda", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE}, 
            orphanRemoval = true, fetch = FetchType.LAZY) //lista de detalles
            //muchas cascadas al ser dependiente de la comanda
            //Igual utilizamos orphanRemoval al no tener sentido que 
            //existan detalles si la comanda no existe
    private List<DetalleComanda> detallesComanda = new ArrayList<>();

    public Comanda() {
    }

    public Comanda(String folio, EstadoComanda estado, LocalDateTime fechaHora, Double totalVenta, Mesa mesa, Cliente cliente) {
        this.folio = folio;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.totalVenta = totalVenta;
        this.mesa = mesa;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public EstadoComanda getEstado() {
        return estado;
    }

    public void setEstado(EstadoComanda estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleComanda> getDetallesComanda() {
        return detallesComanda;
    }

    public void setDetallesComanda(List<DetalleComanda> detallesComanda) {
        this.detallesComanda = detallesComanda;
    }

    @Override
    public String toString() {
        return "Comanda{" + "id=" + id + ", folio=" + folio + ", estado=" + estado + ", fechaHora=" + fechaHora + ", totalVenta=" + totalVenta + ", mesa=" + mesa + ", cliente=" + cliente + ", detallesComanda=" + detallesComanda + '}';
    }
}

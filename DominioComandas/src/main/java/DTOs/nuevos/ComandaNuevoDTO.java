/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.nuevos;

import ENUMs.EstadoComanda;
import java.time.LocalDateTime;

/**
 *
 * @author Beto_
 */
public class ComandaNuevoDTO {
    private String folio;
    private EstadoComanda estado;
    private LocalDateTime fechaHora;
    private Double totalVenta;
    private Long idMesa;
    private Long idCliente;

    public ComandaNuevoDTO() {
    }

    public ComandaNuevoDTO(Double totalVenta, Long idMesa) {
        this.totalVenta = totalVenta;
        this.idMesa = idMesa;
    }

    public ComandaNuevoDTO(String folio, EstadoComanda estado, LocalDateTime fechaHora, Double totalVenta, Long idMesa, Long idCliente) {
        this.folio = folio;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.totalVenta = totalVenta;
        this.idMesa = idMesa;
        this.idCliente = idCliente;
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

    public Long getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Long idMesa) {
        this.idMesa = idMesa;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "ComandaNuevoDTO{" + "folio=" + folio + ", estado=" + estado + ", fechaHora=" + fechaHora + ", totalVenta=" + totalVenta + ", idMesa=" + idMesa + ", idCliente=" + idCliente + '}';
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.viejos;

import ENUMs.EstadoComanda;
import java.time.LocalDateTime;

/**
 *
 * @author Beto_
 */
public class ComandaViejoDTO {
    private Long id;
    private String folio;
    private EstadoComanda estado;
    private LocalDateTime fechaHora;
    private Double totalVenta;
    private Long idMesa;
    private Long idCliente;

    /**
     *
     */
    public ComandaViejoDTO() {
    }

    /**
     *
     * @param id
     * @param folio
     * @param estado
     * @param fechaHora
     * @param totalVenta
     * @param idMesa
     * @param idCliente
     */
    public ComandaViejoDTO(Long id, String folio, EstadoComanda estado, LocalDateTime fechaHora, Double totalVenta, Long idMesa, Long idCliente) {
        this.id = id;
        this.folio = folio;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.totalVenta = totalVenta;
        this.idMesa = idMesa;
        this.idCliente = idCliente;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getFolio() {
        return folio;
    }

    /**
     *
     * @param folio
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     *
     * @return
     */
    public EstadoComanda getEstado() {
        return estado;
    }

    /**
     *
     * @param estado
     */
    public void setEstado(EstadoComanda estado) {
        this.estado = estado;
    }

    /**
     *
     * @return
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     *
     * @param fechaHora
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     *
     * @return
     */
    public Double getTotalVenta() {
        return totalVenta;
    }

    /**
     *
     * @param totalVenta
     */
    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    /**
     *
     * @return
     */
    public Long getIdMesa() {
        return idMesa;
    }

    /**
     *
     * @param idMesa
     */
    public void setIdMesa(Long idMesa) {
        this.idMesa = idMesa;
    }

    /**
     *
     * @return
     */
    public Long getIdCliente() {
        return idCliente;
    }

    /**
     *
     * @param idCliente
     */
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "ComandaViejoDTO{" + "id=" + id + ", folio=" + folio + ", estado=" + estado + ", fechaHora=" + fechaHora + ", totalVenta=" + totalVenta + ", idMesa=" + idMesa + ", idCliente=" + idCliente + '}';
    }
}
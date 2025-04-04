/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Beto_
 */
@Entity
@Table(name = "clientesFrecuentes")
public class ClienteFrecuente extends Cliente implements Serializable {
    @Transient
    private Double totalGastado;
    
    @Transient
    private Integer visitas;
    
    @Transient
    private Integer puntosFidelidad;

    public ClienteFrecuente() {
    }

    public ClienteFrecuente(Double totalGastado, Integer visitas, Integer puntosFidelidad, String nombres, String apellidoPaterno, String apellidoMaterno, String correo, String telefono, LocalDate fechaRegistro) {
        super(nombres, apellidoPaterno, apellidoMaterno, correo, telefono, fechaRegistro);
        this.totalGastado = totalGastado;
        this.visitas = visitas;
        this.puntosFidelidad = puntosFidelidad;
    }
    
    public Double getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(Double totalGastado) {
        this.totalGastado = totalGastado;
    }

    public Integer getVisitas() {
        return visitas;
    }

    public void setVisitas(Integer visitas) {
        this.visitas = visitas;
    }

    public Integer getPuntosFidelidad() {
        return puntosFidelidad;
    }

    public void setPuntosFidelidad(Integer puntosFidelidad) {
        this.puntosFidelidad = puntosFidelidad;
    }

    @Override
    public String toString() {
        return "ClienteFrecuente{" + "totalGastado=" + totalGastado + ", visitas=" + visitas + ", puntosFidelidad=" + puntosFidelidad + '}';
    }
}

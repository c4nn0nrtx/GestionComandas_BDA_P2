package DTOs.viejos;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) para representar a un cliente frecuente.
 *
 * Esta clase encapsula la información de los clientes frecuentes, incluyendo
 * sus datos personales, historial de visitas y fidelización.
 *
 * @author brand
 */
public class ClienteFrecuenteViejoDTO {

    private Long id;
    private String nombres;
    private String aPaterno;
    private String aMaterno;
    private String correo;
    private String telefono;
    private LocalDate fechaRegistro;
    private Double totalGastado;
    private Integer visitas;
    private Integer puntosFidelidad;

    /**
     * Constructor por ausencia.
     */
    public ClienteFrecuenteViejoDTO() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param id Identificador del cliente.
     * @param nombres Nombre del cliente.
     * @param aPaterno Apellido paterno del cliente.
     * @param aMaterno Apellido materno del cliente.
     * @param correo Correo electrónico del cliente.
     * @param telefono Número de teléfono del cliente.
     * @param fechaRegistro Fecha de registro del cliente.
     * @param totalGastado Monto total gastado por el cliente.
     * @param visitas Número total de visitas del cliente.
     * @param puntosFidelidad Puntos de fidelidad acumulados.
     */
    public ClienteFrecuenteViejoDTO(Long id, String nombres, String aPaterno, String aMaterno, String correo, String telefono, LocalDate fechaRegistro, Double totalGastado, Integer visitas, Integer puntosFidelidad) {
        this.id = id;
        this.nombres = nombres;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.totalGastado = totalGastado;
        this.visitas = visitas;
        this.puntosFidelidad = puntosFidelidad;
    }

    /**
     * Método para obtener el identificador del cliente.
     *
     * @return Identificador del cliente.
     */
    public Long getId() {
        return id;
    }

    /**
     * Método para establecer el identificador del cliente.
     *
     * @param id Identificador del cliente.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Método para obtener los nombres del cliente.
     *
     * @return Nombre del cliente.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Método para establecer los nombres del cliente.
     *
     * @param nombres Establece el nombre del cliente.
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Método para obtener el apellido paterno del cliente.
     *
     * @return Apellido paterno del cliente.
     */
    public String getaPaterno() {
        return aPaterno;
    }

    /**
     * Método para establecer el apellido paterno del cliente.
     *
     * @param aPaterno Establece el apellido paterno del cliente.
     */
    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    /**
     * Método para obtener el apellido materno del cliente.
     *
     * @return Apellido materno del cliente.
     */
    public String getaMaterno() {
        return aMaterno;
    }

    /**
     * Método para establecer el apellido materno del cliente.
     *
     * @param aMaterno Establece el apellido materno del cliente.
     */
    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    /**
     * Método para obtener el correo electrónico del cliente.
     *
     * @return Correo electrónico del cliente.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Método para establecer el correo electrónico del cliente.
     *
     * @param correo Establece el correo electrónico del cliente.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Método para obtener el teléfono del cliente.
     *
     * @return Número de teléfono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Método para establecer el número de télefono de un cliente.
     *
     * @param telefono Establece el número de teléfono del cliente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Método para obtener la fecha de registro de un cliente.
     *
     * @return Fecha de registro del cliente.
     */
    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Método para establecer la fecha de registro de un cliente.
     *
     * @param fechaRegistro Establece la fecha de registro del cliente.
     */
    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Método para obtener el total gastado de un cliente.
     *
     * @return Monto total gastado por el cliente.
     */
    public Double getTotalGastado() {
        return totalGastado;
    }

    /**
     * Método para establecer el total gastado de un cliente.
     *
     * @param totalGastado Establece el monto total gastado por el cliente.
     */
    public void setTotalGastado(Double totalGastado) {
        this.totalGastado = totalGastado;
    }

    /**
     * Método para obtener el total de visitas de un cliente.
     *
     * @return Número total de visitas del cliente.
     */
    public Integer getVisitas() {
        return visitas;
    }

    /**
     * Método para establecer el número total de visitas de un cliente.
     *
     * @param visitas Establece el número total de visitas del cliente.
     */
    public void setVisitas(Integer visitas) {
        this.visitas = visitas;
    }

    /**
     * Método para obtener los puntos de fidelidad acumulados de un cliente.
     *
     * @return Puntos de fidelidad acumulados por el cliente.
     */
    public Integer getPuntosFidelidad() {
        return puntosFidelidad;
    }

    /**
     * Método para establecer los puntos de fidelidad acumulados de un cliente.
     *
     * @param puntosFidelidad Establece los puntos de fidelidad acumulados.
     */
    public void setPuntosFidelidad(Integer puntosFidelidad) {
        this.puntosFidelidad = puntosFidelidad;
    }

    /**
     * Devuelve una representación en cadena del objeto.
     *
     * @return Cadena con los atributos del cliente frecuente.
     */
    @Override
    public String toString() {
        return "ClienteFrecuenteViejoDTO{" + "id=" + id + ", nombres=" + nombres + ", aPaterno=" + aPaterno + ", aMaterno=" + aMaterno + ", correo=" + correo + ", telefono=" + telefono + ", fechaRegistro=" + fechaRegistro + ", totalGastado=" + totalGastado + ", visitas=" + visitas + ", puntosFidelidad=" + puntosFidelidad + '}';
    }

}

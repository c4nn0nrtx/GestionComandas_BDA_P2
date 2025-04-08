package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Representa un cliente en el sistema. Esta entidad almacena información
 * personal y de contacto de los clientes, así como la relación con sus
 * comandas.
 *
 * Se utiliza la estrategia de herencia SINGLE_TABLE para mantener una
 * estructura simple.
 *
 * @author Beto_
 */
@Entity
@Table(name = "clientes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//Aunque Joined permite que el sistema pueda tener distintivos entre clientes
//permitiendo que le sistema crezca, eelegimos singleTable ya que su estructura 
//se ve simple al tener la clienteFrecuente solo 3 atributos, 
//pero se puede cambiar a su consideración
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellidoPaterno", nullable = false)
    private String apellidoPaterno;

    @Column(name = "apellidoMaterno", nullable = false)
    private String apellidoMaterno;

    @Column(name = "correo", nullable = true)
    private String correo;

    @Column(name = "telefono", nullable = false, unique = true)
    private String telefono;

    @Column(name = "fechaRegistro", nullable = false)
    private LocalDate fechaRegistro;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Comanda> comandas = new ArrayList<>();
    //pocas cascadas, no nos interesa que se eliminen las comandas si se elimina un cliente

    /**
     * Constructor por ausencia.
     */
    public Cliente() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param nombres Nombre del cliente.
     * @param apellidoPaterno Apellido paterno del cliente.
     * @param apellidoMaterno Apellido materno del cliente.
     * @param correo Correo electrónico del cliente.
     * @param telefono Número de teléfono del cliente.
     * @param fechaRegistro Fecha de registro del cliente.
     */
    public Cliente(String nombres, String apellidoPaterno, String apellidoMaterno, String correo, String telefono, LocalDate fechaRegistro) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Obtiene el ID del cliente.
     *
     * @return ID del cliente.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del cliente.
     *
     * @param id ID del cliente.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return Nombre del cliente.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece el nombre del cliente.
     *
     * @param nombres Nombre del cliente.
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Obtiene el apellido paterno del cliente.
     *
     * @return Apellido paterno del cliente.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Establece el apellido paterno del cliente.
     *
     * @param apellidoPaterno Apellido paterno del cliente.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Obtiene el apellido materno del cliente.
     *
     * @return Apellido materno del cliente.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellido materno del cliente.
     *
     * @param apellidoMaterno Apellido materno del cliente.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Obtiene el correo electrónico del cliente.
     *
     * @return Correo electrónico del cliente.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del cliente.
     *
     * @param correo Correo electrónico del cliente.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene el teléfono del cliente.
     *
     * @return Teléfono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del cliente.
     *
     * @param telefono Teléfono del cliente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene la fecha de registro del cliente.
     *
     * @return Fecha de registro del cliente.
     */
    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Establece la fecha de registro del cliente.
     *
     * @param fechaRegistro Fecha de registro del cliente.
     */
    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Obtiene la lista de comandas asociadas al cliente.
     *
     * @return Lista de comandas del cliente.
     */
    public List<Comanda> getComandas() {
        return comandas;
    }

    /**
     * Establece la lista de comandas asociadas al cliente.
     *
     * @param comandas Lista de comandas del cliente.
     */
    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }

    /**
     * Representación en cadena de la entidad Cliente.
     *
     * @return Cadena con la información del cliente.
     */
    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombres=" + nombres + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", correo=" + correo + ", telefono=" + telefono + ", fechaRegistro=" + fechaRegistro + '}';
    }
}

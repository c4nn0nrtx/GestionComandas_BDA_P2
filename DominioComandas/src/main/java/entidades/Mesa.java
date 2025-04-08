/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Beto_
 */
@Entity
@Table(name = "mesas")
@NamedQuery(name = "Mesa.buscarPorNumeroMesa", query = "SELECT m FROM Mesa m WHERE m.numeroMesa = :numeroMesa")
public class Mesa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "numeroMesa", nullable = false, unique = true)
    private Integer numeroMesa;
    
    /**
     *
     */
    public Mesa() {
    }
    
    /**
     *
     * @param numeroMesa
     */
    public Mesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
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
    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    /**
     *
     * @param numeroMesa
     */
    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Mesa{" + "id=" + id + ", numeroMesa=" + numeroMesa + ", comandas=" + '}';
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Beto_
 */
@Entity
@Table(name = "mesas")
public class Mesa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "numeroMesa", nullable = false)
    private int numeroMesa;
    
    //En caso de que SI se quieran mantener las comandas de la mesa
//    @OneToMany(mappedBy = "mesa", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
//    private List<Comanda> comandas = new ArrayList<>();
    
    @OneToMany(mappedBy = "mesa", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE}, 
               orphanRemoval = true, //Para que se borren las comandas de esta mesa
               fetch = FetchType.LAZY) //Aunque segun yo Lazy es por defecto lazy en OneToMany
    private List<Comanda> comandas = new ArrayList<>();
    
    public Mesa() {
    }
    
    public Mesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }

    @Override
    public String toString() {
        return "Mesa{" + "id=" + id + ", numeroMesa=" + numeroMesa + ", comandas=" + comandas + '}';
    }
}

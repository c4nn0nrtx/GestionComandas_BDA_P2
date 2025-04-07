/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.viejos;

/**
 *
 * @author Beto_
 */
public class MesaViejoDTO {
    private Long id;
    private Integer numeroMesa;

    public MesaViejoDTO() {
    }

    public MesaViejoDTO(Long id, Integer numeroMesa) {
        this.id = id;
        this.numeroMesa = numeroMesa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    @Override
    public String toString() {
        return "MesaViejoDTO{" + "id=" + id + ", numeroMesa=" + numeroMesa + '}';
    }
}

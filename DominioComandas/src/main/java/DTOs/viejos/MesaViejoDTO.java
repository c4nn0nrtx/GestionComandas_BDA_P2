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

    /**
     *
     */
    public MesaViejoDTO() {
    }

    /**
     *
     * @param id
     * @param numeroMesa
     */
    public MesaViejoDTO(Long id, Integer numeroMesa) {
        this.id = id;
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
        return "MesaViejoDTO{" + "id=" + id + ", numeroMesa=" + numeroMesa + '}';
    }
}

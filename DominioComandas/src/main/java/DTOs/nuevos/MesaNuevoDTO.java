/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.nuevos;

/**
 *
 * @author Beto_
 */
public class MesaNuevoDTO {
    private Integer numeroMesa;

    public MesaNuevoDTO() {
    }

    public MesaNuevoDTO(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    @Override
    public String toString() {
        return "MesaNuevoDTO{" + "numeroMesa=" + numeroMesa + '}';
    }
}

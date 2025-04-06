/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modulo.ingredientes;

import DTOs.nuevos.IngredienteNuevoDTO;
import DTOs.viejos.IngredienteViejoDTO;
import ENUMs.UnidadMedida;
import entidades.Ingrediente;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Beto_
 */
public interface IIngredienteBO {
    Ingrediente agregarIngrediente(IngredienteNuevoDTO ingredienteNuevo) throws NegocioException;

    boolean eliminarIngrediente(Long id) throws NegocioException;

    Ingrediente actualizarIngrediente(IngredienteViejoDTO ingredienteViejo) throws NegocioException;
    
    public Ingrediente actualizarStockIngrediente(IngredienteViejoDTO ingredienteViejo) throws NegocioException;

    Ingrediente obtenerIngredientePorId(Long id) throws NegocioException;

    List<IngredienteViejoDTO> obtenerTodos() throws NegocioException;

    List<IngredienteViejoDTO> obtenerPorNombre(String nombre) throws NegocioException;

    List<IngredienteViejoDTO> obtenerPorUnidadMedida(UnidadMedida unidadMedida) throws NegocioException;
}

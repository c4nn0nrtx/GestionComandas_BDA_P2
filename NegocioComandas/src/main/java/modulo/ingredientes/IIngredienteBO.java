/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modulo.ingredientes;

import DTOs.nuevos.IngredienteNuevoDTO;
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

    Ingrediente actualizarIngrediente(IngredienteNuevoDTO ingredienteNuevo) throws NegocioException;

    Ingrediente obtenerIngredientePorId(Long id) throws NegocioException;

    List<Ingrediente> obtenerTodosIngredientes() throws NegocioException;

    List<Ingrediente> obtenerIngredientesPorNombre(String nombre) throws NegocioException;

    List<Ingrediente> obtenerIngredientesPorUnidadMedida(UnidadMedida unidadMedida) throws NegocioException;
}

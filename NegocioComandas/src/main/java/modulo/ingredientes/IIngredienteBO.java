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
    public IngredienteViejoDTO agregarIngrediente(IngredienteNuevoDTO ingredienteNuevo) throws NegocioException;

    public boolean eliminarIngrediente(Long id) throws NegocioException;

    public IngredienteViejoDTO actualizarIngrediente(IngredienteViejoDTO ingredienteViejo) throws NegocioException;
    
    public IngredienteViejoDTO actualizarStockIngrediente(IngredienteViejoDTO ingredienteViejo) throws NegocioException;

    public IngredienteViejoDTO obtenerPorId(Long id) throws NegocioException;

    public List<IngredienteViejoDTO> obtenerTodos() throws NegocioException;

    public List<IngredienteViejoDTO> obtenerPorNombre(String nombre) throws NegocioException;

    public List<IngredienteViejoDTO> obtenerPorUnidadMedida(UnidadMedida unidadMedida) throws NegocioException;
}

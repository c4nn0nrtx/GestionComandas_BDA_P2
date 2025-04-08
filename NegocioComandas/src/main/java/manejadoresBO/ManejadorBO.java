package manejadoresBO;

import modulo.clientes.ClienteFrecuenteBO;
import modulo.clientes.ClienteFrecuenteDAO;
import modulo.clientes.IClienteFrecuenteBO;
import modulo.clientes.IClienteFrecuenteDAO;
import modulo.comandas.ComandaBO;
import modulo.comandas.ComandaDAO;
import modulo.comandas.IComandaBO;
import modulo.comandas.IComandaDAO;
import modulo.comandas.IMesaBO;
import modulo.comandas.IMesaDAO;
import modulo.comandas.MesaBO;
import modulo.comandas.MesaDAO;
import modulo.ingredientes.IIngredienteBO;
import modulo.ingredientes.IIngredienteDAO;
import modulo.ingredientes.IngredienteBO;
import modulo.ingredientes.IngredienteDAO;

/**
 * La clase {@code ManejadorBO} actúa como un punto centralizado para la
 * creación y obtención de las instancias de los Business Objects (BOs) en la
 * aplicación. Sigue un patrón de fábrica simple, encapsulando la lógica de
 * instanciación de cada BO y sus dependencias (DAOs). Esto promueve la
 * separación de responsabilidades y facilita la gestión de las dependencias en
 * la capa de negocio.
 *
 * @author Beto_
 */
public class ManejadorBO {

    /**
     * Crea y devuelve una instancia del Business Object para la gestión de
     * ingredientes ({@link IngredienteBO}). Este método se encarga de obtener
     * la instancia del Data Access Object ({@link IngredienteDAO}) utilizando
     * el patrón Singleton y luego inyectarla en el constructor del BO.
     *
     * @return Una instancia de {@link IIngredienteBO} lista para ser utilizada.
     */
    public static IIngredienteBO crearIngredienteBO() {
        // 1. Creamos la instancia del DAO a utilizar.
        // Se obtiene la única instancia disponible debido al uso del patrón Singleton.
        IIngredienteDAO ingredienteDAO = IngredienteDAO.getInstanceDAO();

        // 2. Creamos la instancia del BO, inyectando el DAO como dependencia.
        IIngredienteBO ingredienteBO = new IngredienteBO(ingredienteDAO);

        // 3. Retornamos la instancia del BO lista para ser utilizada.
        return ingredienteBO;
    }

    /**
     * Crea y devuelve una instancia del Business Object para la gestión de
     * clientes frecuentes ({@link ClientesFrecuentesBO}). Este método se
     * encarga de obtener la instancia del Data Access Object
     * ({@link ClientesFrecuentesDAO}) utilizando el patrón Singleton y luego
     * inyectarla en el constructor del BO.
     *
     * @return Una instancia de {@link IIngredienteBO} lista para ser utilizada.
     */
    public static IClienteFrecuenteBO crearClienteFrecuenteBO() {
        // 1. Creamos la instancia del DAO a utilizar.
        // Se obtiene la única instancia disponible debido al uso del patrón Singleton.
        IClienteFrecuenteDAO frecuenteDAO = ClienteFrecuenteDAO.getInstanceDAO();

        // 2. Creamos la instancia del BO, inyectando el DAO como dependencia.
        IClienteFrecuenteBO frecuenteBO = new ClienteFrecuenteBO(frecuenteDAO);

        // 3. Retornamos la instancia del BO lista para ser utilizada.
        return frecuenteBO;
    }

    /**
     * Crea y devuelve una instancia del Business Object para la gestión de
     * comandas ({@link ComandaBO}). Similar a {@link #crearIngredienteBO()},
     * este método obtiene la instancia del DAO correspondiente
     * ({@link ComandaDAO}) y la inyecta en el constructor del BO.
     *
     * @return Una instancia de {@link IComandaBO} lista para ser utilizada.
     */
    public static IComandaBO crearComandaBO() {
        // 1. Creamos la instancia del DAO a utilizar.
        // Se obtiene la única instancia disponible debido al uso del patrón Singleton.
        IComandaDAO comandaDAO = ComandaDAO.getInstanceDAO();

        // 2. Creamos la instancia del BO, inyectando el DAO como dependencia.
        IComandaBO comandaBO = new ComandaBO(comandaDAO);

        // 3. Retornamos la instancia del BO lista para ser utilizada.
        return comandaBO;
    }

    /**
     * Crea y devuelve una instancia del Business Object para la gestión de
     * mesas ({@link MesaBO}). Este método sigue el mismo patrón que los
     * anteriores, obteniendo la instancia del DAO de mesas ({@link MesaDAO}) y
     * proporcionándola al constructor del BO.
     *
     * @return Una instancia de {@link IMesaBO} lista para ser utilizada.
     */
    public static IMesaBO crearMesaBO() {
        // 1. Creamos la instancia del DAO a utilizar.
        // Se obtiene la única instancia disponible debido al uso del patrón Singleton.
        IMesaDAO mesaDAO = MesaDAO.getInstanceDAO();

        // 2. Creamos la instancia del BO, inyectando el DAO como dependencia.
        IMesaBO mesaBO = new MesaBO(mesaDAO);

        // 3. Retornamos la instancia del BO lista para ser utilizada.
        return mesaBO;
    }
}

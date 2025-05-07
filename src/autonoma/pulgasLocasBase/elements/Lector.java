
package autonoma.pulgasLocasBase.elements;

/**
 *
 * @author 
 */
import java.io.IOException;
import java.util.ArrayList;

/**
 * Interfaz que define el contrato para leer datos desde una fuente específica.
 * 
 * @author Usuario
 * @version 1.0
 */
public interface Lector {
    /**
     * Lee datos desde la ruta especificada.
     * 
     * @param ruta Ruta de la fuente de datos.
     * @return Lista de líneas leídas.
     * @throws IOException Si ocurre un error durante la lectura.
     */
    public abstract ArrayList<String> leer(String ruta) throws IOException;
}
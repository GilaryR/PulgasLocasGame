package autonoma.pulgasLocasBase.elements;
import java.io.IOException;
import java.util.ArrayList;

/**
 * La interfaz {@code Lector} define el contrato para clases encargadas de leer datos
 * desde una fuente específica, como archivos de texto.
 * 
 * <p>Este diseño permite abstraer la fuente de datos y reutilizar lógica común.</p>
 * 
 * @author Luisa Fernanda Henao Posada
 * @since 2025-05-05
 * @version 1.0.0
 */
public interface Lector {

    /**
     * Lee datos desde la ruta especificada.
     * 
     * @param ruta Ruta de la fuente de datos.
     * @return Lista de líneas leídas como cadenas de texto.
     * @throws IOException Si ocurre un error durante la lectura.
     */
    ArrayList<String> leer(String ruta) throws IOException;
}

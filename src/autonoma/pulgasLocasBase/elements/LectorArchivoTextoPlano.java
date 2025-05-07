
package autonoma.pulgasLocasBase.elements;

/**
 *
 * @author jgiugtiñut
 */
import autonoma.pulgasLocasBase.elements.Lector;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa la interfaz {@link Lector} para leer datos desde archivos de texto plano.
 * Esta clase permite leer archivos línea por línea.
 * 
 * @author Usuario
 * @version 1.0
 */
public class LectorArchivoTextoPlano implements Lector {
    
    /**
     * Lee todas las líneas de un archivo de texto plano.
     * 
     * @param ruta Ruta del archivo que se desea leer.
     * @return Lista de líneas de texto contenidas en el archivo.
     * @throws IOException Si ocurre un error al acceder o leer el archivo.
     */
    @Override
    public ArrayList<String> leer(String ruta) throws IOException {
        List<String> lineas = Files.readAllLines(Paths.get(ruta));
        return new ArrayList<>(lineas);
    }
    
    /**
     * Lee el puntaje más alto almacenado en un archivo.
     * 
     * @param ruta Ruta del archivo que contiene el puntaje.
     * @return El puntaje más alto como un entero.
     * @throws IOException Si ocurre un error al acceder o leer el archivo.
     */
    public int leerPuntajeAlto(String ruta) throws IOException {
        try {
            List<String> lineas = this.leer(ruta);
            if (lineas.isEmpty()) {
                return 0;
            }
            int max = 0;
            for(int i = 1; i < lineas.size(); i++){
                String[] text = lineas.get(i).split(",");
                int puntuacion = Integer.parseInt(text[1]);
                if(max < puntuacion){
                    max = puntuacion;
                }
            }
            return max;
        } catch (Exception e) {
            return 0;
        }
    }
}
package autonoma.pulgasLocasBase.elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz {@link Lector} para leer datos desde archivos de texto plano.
 * 
 * <p>Lee archivos línea por línea y puede calcular el puntaje más alto en archivos con formato CSV.</p>
 * 
 * @author Luisa Fernanda Henao Posada
 * @since 2025-05-05
 * @version 1.0.0
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
     * Lee el puntaje más alto almacenado en un archivo CSV con estructura "nombre,puntaje".
     * 
     * @param ruta Ruta del archivo que contiene los datos.
     * @return El puntaje más alto encontrado en el archivo. Retorna 0 si hay errores o está vacío.
     * @throws IOException Si ocurre un error de lectura del archivo.
     */
    public int leerPuntajeAlto(String ruta) throws IOException {
        try {
            List<String> lineas = this.leer(ruta);
            if (lineas.isEmpty()) {
                return 0;
            }

            int max = 0;
            for (int i = 1; i < lineas.size(); i++) {
                String[] text = lineas.get(i).split(",");
                int puntuacion = Integer.parseInt(text[1]);
                if (max < puntuacion) {
                    max = puntuacion;
                }
            }
            return max;
        } catch (Exception e) {
            return 0;
        }
    }
}

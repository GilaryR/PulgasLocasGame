package autonoma.pulgasLocasBase.elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 * La clase {@code EscritorTextoPlano} implementa la interfaz {@link Escritor} 
 * para permitir la escritura de texto en archivos planos, utilizando codificación UTF-8.
 * 
 * <p>Esta clase crea un archivo si no existe, y permite escribir líneas de texto 
 * en formato CSV con un encabezado predefinido.</p>
 * 
 * @author Luisa Fernanda Henao Posada
 * @since 2025-05-05
 * @version 1.0.0
 */
public class EscritorTextoPlano implements Escritor {

    /**
     * Codificación usada para la escritura de archivos.
     */
    private final Charset charset = Charset.forName("UTF-8");

    /**
     * Encabezado predeterminado que se escribirá al crear el archivo.
     */
    private static final String HEADER = "nombre,puntaje";

    /**
     * Ruta del archivo donde se escribirá el contenido.
     */
    private String filePath;

    /**
     * Crea una nueva instancia de {@code EscritorTextoPlano} con la ruta especificada.
     * 
     * @param filePath la ruta del archivo donde se almacenará el contenido.
     */
    public EscritorTextoPlano(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Crea el archivo y escribe el encabezado si no existe.
     * 
     * @throws IOException si ocurre un error al crear o escribir en el archivo.
     */
    public void crearArchivo() throws IOException {
        File fichero = new File(this.filePath);
        FileWriter writer = new FileWriter(fichero, true);
        PrintWriter out = new PrintWriter(writer);
        out.println(this.HEADER);
        out.close();
    }

    /**
     * Escribe una línea de texto en el archivo especificado. 
     * Si el archivo no existe, lo crea con el encabezado correspondiente.
     * 
     * @param texto la línea de texto que se desea escribir.
     * @throws IOException si ocurre un error durante la escritura.
     */
    @Override
    public void escribir(String texto) throws IOException {
        File fichero = new File(this.filePath);
        if (!fichero.exists()) {
            crearArchivo();
        }
        FileWriter writer = new FileWriter(fichero, true);
        PrintWriter pw = new PrintWriter(writer);
        pw.println(texto);
        writer.close();
    }
}

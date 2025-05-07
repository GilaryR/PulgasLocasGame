package autonoma.pulgasLocasBase.elements;

import java.io.IOException;

/**
 * La interfaz {@code Escritor} define el comportamiento requerido para clases 
 * que deben implementar funcionalidades de escritura de texto en archivos.
 * 
 * <p>Esta interfaz puede ser utilizada para abstraer la escritura en diferentes 
 * formatos o destinos, como archivos de texto en el sistema de archivos, memoria 
 * interna, etc.</p>
 * 
 * @author Luisa Fernanda Henao Posada
 * @since 2025-05-05
 * @version 1.0.0
 */
public interface Escritor {

    /**
     * Escribe una cadena de texto en un archivo línea por línea.
     * 
     * <p>Este método debe ser implementado por las clases que definan 
     * cómo se escribirá el texto en el archivo correspondiente. 
     * Puede lanzar una excepción si el archivo no se encuentra 
     * o si ocurre un error de escritura.</p>
     * 
     * @param text la cadena de texto que se desea escribir en el archivo.
     * @throws IOException si ocurre un error durante la escritura o si 
     *         el archivo no existe o no puede accederse.
     */
    void escribir(String text) throws IOException;
}

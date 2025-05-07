
package autonoma.pulgasLocasBase.elements;

import java.io.IOException;

/**
 *
 * @author 
 */
public interface Escritor {
    /**
     *
     * Escribe el archivo de memoria interna por lineas
     *
     * @param archivo
     * @throws java.io.IOException si el archivo no existe
     */
    public abstract void escribir(String text) throws IOException;

}

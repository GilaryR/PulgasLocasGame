/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package autonoma.pulgasLocasBase.elements;

import java.io.IOException;

/**
 *
 * @author juanp
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

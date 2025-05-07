package autonoma.pulgasLocas.elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Representa una pulga normal en el juego. 
 * Hereda de {@link Pulga} y define su comportamiento específico.
 * 
 * Esta pulga se caracteriza por morir con un solo impacto y por tener una imagen
 * predeterminada que se intenta cargar desde los recursos del proyecto.
 * Si la imagen no se encuentra, se utiliza un color por defecto para dibujarla.
 * 
 * @author Gilary Rugeles
 * @since 2025-05-07
 * @version 1.0.0
 */

public class PulgaNormal extends Pulga {

    /**
     * Imagen de la pulga normal que será mostrada en pantalla.
     */
    private BufferedImage imagenPulgaNormal;

    /**
     * Crea una nueva instancia de {@code PulgaNormal}.
     * Intenta cargar una imagen desde el recurso especificado. 
     * Si no se encuentra, se asigna un color por defecto.
     *
     * @param x      Posición horizontal inicial de la pulga.
     * @param y      Posición vertical inicial de la pulga.
     * @param height Altura de la pulga.
     * @param width  Ancho de la pulga.
     */
    public PulgaNormal(int x, int y, int height, int width) {
        super(x, y, height, width);

        try {
            // Cargar imagen desde recursos (útil dentro de un .jar)
            imagenPulgaNormal = ImageIO.read(getClass().getResourceAsStream(
                "/autonoma/pulgasLocas/images/PulgaNormal.png"));
        } catch (IOException | NullPointerException e) {
            System.out.println("Imagen de pulga normal no encontrada. Usando color predeterminado.");
            this.color = java.awt.Color.RED;
        }
    }

    /**
     * Maneja el impacto recibido por la pulga.
     * Esta implementación hace que la pulga muera con un solo impacto.
     * 
     * @return {@code true}, ya que la pulga siempre muere con un impacto.
     */
    @Override
    public boolean recibirImpacto() {
        isAlive = false;
        return true;
    }

    /**
     * Dibuja la pulga en pantalla.
     * Si la imagen fue cargada correctamente, la dibuja. 
     * De lo contrario, dibuja una figura ovalada de color rojo.
     *
     * @param g Objeto {@code Graphics} utilizado para renderizar la pulga.
     */
    @Override
    public void paint(Graphics g) {
        if (imagenPulgaNormal != null) {
            g.drawImage(imagenPulgaNormal, x, y, width, height, null);
        } else {
            g.setColor(color);
            g.fillOval(x, y, width, height);
        }
    }
}

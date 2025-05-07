
package autonoma.pulgasLocas.elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 * Clase que representa una pulga mutante en el juego.
 * Las pulgas mutantes requieren dos impactos para ser eliminadas: 
 * el primero las convierte en una pulga normal, y el segundo las elimina.
 * También pueden tener una imagen personalizada si se encuentra en los recursos del proyecto.
 * 
 * @author Gilary Rugeles
 * @version 1.0.0
 * @since 2025-05-07
 */
public class PulgaMutante extends Pulga {

    /**
     * Imagen personalizada de la pulga mutante.
     */
    private BufferedImage imagenPulgaMutante;

    /**
     * Resistencia de la pulga mutante. Debe recibir dos impactos para ser eliminada.
     */
    private int resistencia = 2;

    /**
     * Crea una nueva instancia de PulgaMutante en una posición específica con un tamaño dado.
     * 
     * @param x coordenada X de la pulga.
     * @param y coordenada Y de la pulga.
     * @param height alto del sprite.
     * @param width ancho del sprite.
     */
    public PulgaMutante(int x, int y, int height, int width) {
        super(x, y, height, width);

        try {
            // Intenta cargar la imagen de la pulga mutante desde los recursos del proyecto
            imagenPulgaMutante = ImageIO.read(getClass().getResourceAsStream("/autonoma/pulgasLocas/images/PulgaMutante.jpg"));
        } catch (IOException | NullPointerException e) {
            System.out.println("Imagen de pulga mutante no encontrada. Usando color predeterminado.");
            this.color = java.awt.Color.RED;
        }
    }

    /**
     * Maneja el impacto recibido por la pulga.
     * Reduce su resistencia. Si es el primer impacto, la pulga no muere aún.
     * Si es el segundo impacto, la pulga muere.
     * 
     * @return {@code true} si la pulga debe ser eliminada, {@code false} si solo se transforma.
     */
    @Override
    public boolean recibirImpacto() {
        resistencia--;
        if (resistencia <= 0) {
            isAlive = false;
            return true; // La pulga muere
        }
        return false; // Se convierte en una pulga normal
    }

    /**
     * Dibuja la pulga mutante en pantalla.
     * Si hay una imagen cargada, se utiliza; de lo contrario, se representa con formas básicas.
     * 
     * @param g el contexto gráfico donde se dibuja la pulga.
     */
    @Override
    public void paint(Graphics g) {
        if (imagenPulgaMutante != null) {
            g.drawImage(imagenPulgaMutante, x, y, width, height, null);
        } else {
            g.setColor(color);
            g.fillOval(x, y, width, height);
            // Indicador visual adicional para identificar que es mutante
            g.setColor(java.awt.Color.YELLOW);
            g.drawOval(x + 3, y + 3, width - 6, height - 6);
        }
    }

    /**
     * Convierte esta pulga mutante en una pulga normal, manteniendo su posición y tamaño.
     * Esto ocurre tras el primer impacto recibido.
     * 
     * @return una nueva instancia de {@link PulgaNormal} con los mismos atributos de posición y tamaño.
     */
    public PulgaNormal convertirANormal() {
        return new PulgaNormal(this.x, this.y, this.height, this.width);
    }
}
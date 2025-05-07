
package autonoma.pulgasLocas.elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import static javax.swing.Spring.height;

/**
 * Clase que representa una pulga mutante en el juego.
 * @author Estudiante
 */
public class PulgaMutante extends Pulga {
    private BufferedImage imagenPulgaMutante;
    private int resistencia = 2; // Resiste el doble de impactos
    
    public PulgaMutante(int x, int y, int height, int width) {
        super(x, y, height, width);
        
        try {
            // Cargar imagen desde recursos (funciona dentro del .jar)
            imagenPulgaMutante = ImageIO.read(getClass().getResourceAsStream("/autonoma/pulgasLocas/images/PulgaMutante.jpg"));
        } catch (IOException | NullPointerException e) {
            System.out.println("Imagen de pulga normal no encontrada. Usando color predeterminado.");
            this.color = java.awt.Color.RED;
        }
    }
    
    @Override
    public boolean recibirImpacto() {
        resistencia--;
        if (resistencia <= 0) {
            isAlive = false;
            return true; // La pulga mutante muere con el segundo impacto
        }
        return false; // La pulga mutante se convierte en normal con el primer impacto
    }
    
    @Override
    public void paint(Graphics g) {
        if (imagenPulgaMutante != null) {
            g.drawImage(imagenPulgaMutante, x, y, width, height, null);
        } else {
            g.setColor(color);
            g.fillOval(x, y, width, height);
            // Dibujar algo que indique que es mutante
            g.setColor(java.awt.Color.YELLOW);
            g.drawOval(x + 3, y + 3, width - 6, height - 6);
        }
    }
    
    /**
     * Convierte esta pulga mutante en una pulga normal
     * @return una nueva pulga normal en la misma posición
     */
    public PulgaNormal convertirANormal() {
    PulgaNormal nueva = new PulgaNormal(this.x, this.y, this.height, this.width);
    return nueva;
    }
}
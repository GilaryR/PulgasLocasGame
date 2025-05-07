package autonoma.pulgasLocas.elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author jgiugtiñut
 */
public class PulgaNormal extends Pulga {
    private BufferedImage imagenPulgaNormal;
    
    public PulgaNormal(int x, int y, int height, int width) {
        super(x, y, height, width);
        
        try {
            // Cargar imagen desde recursos (funciona dentro del .jar)
            imagenPulgaNormal = ImageIO.read(getClass().getResourceAsStream("/autonoma/pulgasLocas/images/PulgaNormal.png"));
        } catch (IOException | NullPointerException e) {
            System.out.println("Imagen de pulga normal no encontrada. Usando color predeterminado.");
            this.color = java.awt.Color.RED;
        }
    }
    
    @Override
    public boolean recibirImpacto() {
        isAlive = false;
        return true; // La pulga normal muere con un solo impacto
    }
    
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

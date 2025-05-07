import autonoma.pulgasLocasBase.elements.Sprite;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * Clase abstracta que representa a una pulga dentro del juego.
 * Hereda de {@link Sprite} y proporciona una estructura básica para definir
 * el comportamiento común de las pulgas, como moverse aleatoriamente y 
 * reaccionar a los impactos.
 * 
 * Las clases concretas deben implementar el método {@code recibirImpacto} y {@code paint}.
 * 
 * @author Gilary Rugeles
 * @since 2025-05-07
 * @version 1.0.0
 */

public abstract class Pulga extends Sprite {

    /**
     * Imagen de la pulga que será dibujada en pantalla.
     */
    protected ImageIcon imagenPulga;

    /**
     * Indica si la pulga está viva o ha sido eliminada.
     */
    protected boolean isAlive = true;

    /**
     * Generador de números aleatorios para controlar el movimiento aleatorio de las pulgas.
     */
    protected static Random random = new Random();

    /**
     * Crea una nueva instancia de {@code Pulga}.
     * 
     * @param x      Posición horizontal inicial.
     * @param y      Posición vertical inicial.
     * @param height Altura de la pulga.
     * @param width  Ancho de la pulga.
     */
    public Pulga(int x, int y, int height, int width) {
        super(x, y, height, width);
    }

    /**
     * Método abstracto que define la reacción de la pulga al recibir un impacto.
     * Debe ser implementado por las subclases.
     *
     * @return {@code true} si la pulga muere tras el impacto, {@code false} en caso contrario.
     */
    public abstract boolean recibirImpacto();

    /**
     * Hace que la pulga salte a una nueva posición aleatoria dentro de los límites dados.
     *
     * @param boundaries Rectángulo que define los límites del área de movimiento.
     */
    public void saltar(Rectangle boundaries) {
        int newX = random.nextInt((int)boundaries.getWidth() - width);
        int newY = random.nextInt((int)boundaries.getHeight() - height);
        this.x = newX;
        this.y = newY;
    }

    /**
     * Verifica si la pulga está viva.
     * 
     * @return {@code true} si la pulga está viva, {@code false} si ha sido eliminada.
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Dibuja la pulga en la pantalla.
     * Este método debe ser implementado por las subclases.
     * 
     * @param g Objeto {@code Graphics} utilizado para dibujar la pulga.
     */
    @Override
    public abstract void paint(Graphics g);
}
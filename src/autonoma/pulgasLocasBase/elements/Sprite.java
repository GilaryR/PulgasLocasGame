package autonoma.pulgasLocasBase.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 * Clase abstracta que representa una entidad gráfica (sprite) dentro de un contenedor gráfico.
 * 
 * <p>Un sprite tiene posición, tamaño, color y puede contener una imagen. También puede 
 * detectar colisiones y saber si se encuentra fuera de los límites de su contenedor.</p>
 * 
 * @author Luisa Fernanda Henao
 * @since 2025-05-05
 * @version 1.0.0
 */
public abstract class Sprite {

    protected int x;
    protected int y;
    protected int height;
    protected int width;
    protected Color color;
    protected ImageIcon image;
    protected GraphicContainer gameContainer;

    /**
     * Constructor que inicializa la posición y dimensiones del sprite.
     * 
     * @param x Coordenada horizontal.
     * @param y Coordenada vertical.
     * @param height Altura del sprite.
     * @param width Anchura del sprite.
     */
    public Sprite(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    /**
     * Verifica si el sprite está fuera del contenedor gráfico.
     * 
     * @return {@code true} si está fuera, de lo contrario {@code false}.
     */
    public boolean isOutOfGraphicContainer() {
        return isOutOfGraphicContainer(x, y, width, height);
    }

    /**
     * Verifica si las coordenadas especificadas están fuera del contenedor gráfico.
     * 
     * @param x Posición horizontal.
     * @param y Posición vertical.
     * @param width Ancho.
     * @param height Alto.
     * @return {@code true} si está fuera del área, de lo contrario {@code false}.
     */
    public boolean isOutOfGraphicContainer(int x, int y, int width, int height) {
        if (gameContainer == null)
            return false;

        Rectangle bounds = gameContainer.getBoundaries();

        return !(x >= bounds.getX() &
                 y >= bounds.getY() &
                 x + width <= bounds.getX() + bounds.getWidth() &
                 y + height <= bounds.getY() + bounds.getHeight());
    }

    /**
     * Verifica si hay colisión con otro sprite.
     * 
     * @param other El otro sprite a comparar.
     * @return {@code true} si hay colisión, de lo contrario {@code false}.
     */
    public boolean checkCollision(Sprite other) {
        boolean collisionX = this.getX() + this.getWidth() >= other.getX() &&
                             this.getX() < other.getX() + other.getWidth();

        boolean collisionY = this.getY() + this.getHeight() >= other.getY() &&
                             this.getY() < other.getY() + other.getHeight();

        return collisionX && collisionY;
    }

    /**
     * Dibuja el sprite sobre un componente gráfico.
     * 
     * @param g El contexto gráfico donde se pintará.
     */
    public abstract void paint(Graphics g);

    // Métodos getters y setters

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public void setGraphicContainer(GraphicContainer gContainer) {
        this.gameContainer = gContainer;
    }
}

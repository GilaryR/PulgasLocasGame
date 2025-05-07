package autonoma.pulgasLocasBase.elements;



/**
 *
 * @author educacion
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author educacion
 */
public abstract class Sprite 
{
    protected int x;
    protected int y;
    protected int height;
    protected int width;
    protected Color color;
    protected ImageIcon image;
    protected GraphicContainer gameContainer;

    public Sprite(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }
    
    public boolean isOutOfGraphicContainer()
    {
        return isOutOfGraphicContainer(x, y, width, height);
    }

    public boolean isOutOfGraphicContainer(int x, int y, int width, int height)
    {
        if(gameContainer == null)
            return false;
        
        Rectangle bounds = gameContainer.getBoundaries();
        
        return !(x >= bounds.getX() &
                 y >= bounds.getY() &
                 x + width  <= bounds.getX() + bounds.getWidth() &
                 y + height <= bounds.getY() + bounds.getHeight());
    }
    
    public boolean checkCollision(Sprite other)
    {
        // Collision x-axis?
        boolean collisionX = this.getX() + this.getWidth() >= other.getX() &&
            this.getX() < other.getX() + other.getWidth();

        // Collision y-axis?
        boolean collisionY = this.getY() + this.getHeight() >= other.getY() &&
            this.getY() < other.getY() + other.getHeight();

        // Collision only if on both axes
        return collisionX && collisionY;        
    }

    public abstract void paint(Graphics g);
    
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

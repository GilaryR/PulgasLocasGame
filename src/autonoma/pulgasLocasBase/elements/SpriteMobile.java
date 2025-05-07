
package autonoma.pulgasLocasBase.elements;
import autonoma.pulgasLocasBase.elements.Sprite;

/**
 *
 * @author educacion
 */
public abstract class SpriteMobile extends Sprite
{
    protected int step;
    
    public SpriteMobile(int x, int y, int height, int width) {
        super(x, y, height, width);
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}

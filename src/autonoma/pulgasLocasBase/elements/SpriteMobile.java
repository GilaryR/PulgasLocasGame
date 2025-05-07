package autonoma.pulgasLocasBase.elements;

/**
 * Clase abstracta que representa un sprite con capacidad de movimiento.
 * Extiende la clase {@link Sprite} añadiendo un atributo de desplazamiento.
 * 
 * <p>Esta clase base es útil para definir sprites animados o móviles como jugadores,
 * enemigos o elementos que cambian de posición.</p>
 * 
 * @author Luisa Fernanda Henao Posada
 * @since 2025-05-05
 * @version 1.0.0
 * 
 */
public abstract class SpriteMobile extends Sprite {

    /**
     * Cantidad de píxeles que el sprite se moverá por paso.
     */
    protected int step;

    /**
     * Constructor que inicializa la posición y tamaño del sprite móvil.
     *
     * @param x Coordenada horizontal inicial.
     * @param y Coordenada vertical inicial.
     * @param height Altura del sprite.
     * @param width Anchura del sprite.
     */
    public SpriteMobile(int x, int y, int height, int width) {
        super(x, y, height, width);
    }

    /**
     * Obtiene el valor de desplazamiento por paso.
     * 
     * @return Cantidad de píxeles por paso.
     */
    public int getStep() {
        return step;
    }

    /**
     * Establece el valor de desplazamiento por paso.
     * 
     * @param step Cantidad de píxeles por paso.
     */
    public void setStep(int step) {
        this.step = step;
    }
}

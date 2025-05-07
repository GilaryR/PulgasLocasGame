package autonoma.pulgasLocasBase.elements;
import java.util.ArrayList;

/**
 * Clase abstracta que representa un contenedor de sprites. Extiende {@link Sprite}
 * e implementa la interfaz {@link GraphicContainer}.
 * 
 * <p>Esta clase permite contener y gestionar múltiples sprites, facilitando operaciones
 * como agregar, eliminar y contar objetos gráficos.</p>
 * 
 * @author Luisa Fernanda Henao Posada
 * @since 2025-05-05
 * @version 1.0.0
 */
public abstract class SpriteContainer extends Sprite implements GraphicContainer {

    /**
     * Lista que contiene todos los sprites dentro del contenedor.
     */
    protected ArrayList<Sprite> sprites;

    /**
     * Constructor que inicializa la posición y tamaño del contenedor, además de la lista de sprites.
     *
     * @param x Coordenada horizontal del contenedor.
     * @param y Coordenada vertical del contenedor.
     * @param height Altura del contenedor.
     * @param width Anchura del contenedor.
     */
    public SpriteContainer(int x, int y, int height, int width) {
        super(x, y, height, width);
        sprites = new ArrayList<>();
    }

    /**
     * Agrega un sprite a la lista del contenedor.
     * 
     * @param sprite Objeto {@link Sprite} a agregar.
     * @return {@code true} si fue agregado exitosamente.
     */
    public boolean add(Sprite sprite) {
        return sprites.add(sprite);
    }

    /**
     * Elimina el sprite ubicado en la posición indicada.
     * 
     * @param index Índice del sprite en la lista.
     */
    public void remove(int index) {
        sprites.remove(index);
    }

    /**
     * Elimina un sprite específico de la lista.
     * 
     * @param sprite Objeto {@link Sprite} que se desea eliminar.
     */
    public void remove(Sprite sprite) {
        sprites.remove(sprite);
    }

    /**
     * Retorna la cantidad de sprites almacenados en el contenedor.
     * 
     * @return Número total de sprites.
     */
    public int size() {
        return sprites.size();
    }
}

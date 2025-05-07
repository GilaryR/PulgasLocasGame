package autonoma.pulgasLocasBase.elements;
import java.awt.Rectangle;

/**
 * La interfaz {@code GraphicContainer} define el comportamiento que deben 
 * implementar los contenedores gráficos capaces de actualizar su contenido visual 
 * y proporcionar sus límites espaciales.
 * 
 * <p>Se utiliza comúnmente en aplicaciones gráficas donde se requiere redibujar 
 * componentes o conocer su área ocupada.</p>
 * 
 * @author Luisa Fernanda Henao Posada
 * @since 2025-05-05
 * @version 1.0.0
 */
public interface GraphicContainer {

    /**
     * Refresca o actualiza el contenido gráfico del contenedor.
     */
    void refresh();

    /**
     * Obtiene los límites (dimensiones y posición) del contenedor gráfico.
     * 
     * @return un objeto {@code Rectangle} que representa los límites del contenedor.
     */
    Rectangle getBoundaries();
}

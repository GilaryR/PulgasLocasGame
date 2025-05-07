
package autonoma.pulgasLocas.elements;


/**
 * Clase encargada de generar (spawnear) pulgas normales y mutantes 
 * periódicamente en el campo de batalla. 
 * Se ejecuta en un hilo separado para no bloquear la interfaz gráfica.
 * 
 * <p>Genera una pulga normal cada 5 segundos y una pulga mutante cada 10 segundos.</p>
 * 
 * @author Gilary Rugeles 
 * @version 1.0.0
 * @since 2025-05-07
 */
public class SpawnPulga implements Runnable {

    /**
     * Referencia al campo de batalla donde se agregan las pulgas.
     */
    private CampoBatalla campo;

    /**
     * Indica si el hilo debe seguir ejecutándose.
     */
    private boolean running = true;

    /**
     * Hilo que ejecuta el ciclo de generación de pulgas.
     */
    protected Thread thread;

    /**
     * Crea un nuevo generador de pulgas asociado a un campo de batalla y 
     * comienza su ejecución automática.
     * 
     * @param campo instancia de {@link CampoBatalla} donde se agregarán las pulgas.
     */
    public SpawnPulga(CampoBatalla campo) {
        this.campo = campo;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Método que ejecuta el ciclo de generación de pulgas.
     * Agrega una pulga normal cada 5 segundos y una pulga mutante cada 10 segundos.
     * El ciclo continúa mientras {@code running} sea {@code true}.
     */
    @Override
    public void run() {
        while (running) {
            try {
                this.thread.sleep(5000); // Espera 5 segundos
                this.campo.agregarPulgaNormal();
                this.thread.sleep(10000); // Espera 10 segundos más
                this.campo.agregarPulgaMutante();
            } catch (InterruptedException ex) {
                break; // Si el hilo es interrumpido, se detiene el ciclo
            }
        }
    }

    /**
     * Inicia nuevamente el hilo del generador de pulgas.
     * Si ya se estaba ejecutando, se reinicia.
     */
    public void start() {
        this.running = true;
        this.thread = new Thread(this);
        this.thread.start();
    }

    /**
     * Retorna si el generador de pulgas está activo.
     * 
     * @return {@code true} si está activo, {@code false} si se ha detenido.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Detiene la generación de pulgas interrumpiendo el hilo.
     */
    public void stop() {
        this.running = false;
        this.thread.interrupt();
    }
}
package autonoma.pulgasLocas.elements;


import autonoma.pulgasLocas.main.PulgaGame;
import autonoma.pulgasLocasBase.elements.EscritorTextoPlano;
import autonoma.pulgasLocasBase.elements.LectorArchivoTextoPlano;
import autonoma.pulgasLocasBase.elements.Sprite;
import autonoma.pulgasLocasBase.elements.SpriteContainer;
import java.awt.Color;
import java.awt.Font;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.JFrame;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Clase que representa el campo de batalla del juego Pulgas Locas.
 * Controla la lógica de juego como agregar pulgas, disparos, puntuación,
 * y reinicio del juego.
 *  * 
 * @author Gilary Rugeles
 * @since 2025-05-07
 * @version 1.0.0
 */
 
public class CampoBatalla extends SpriteContainer {

    private Random random;
    private int puntaje = 0;
    private int maxPuntaje = 0;
    private PulgaGame game;
    private LectorArchivoTextoPlano lector;
    private EscritorTextoPlano escritor;

    /**
     * Constructor de la clase CampoBatalla.
     *
     * @param frame JFrame asociado al juego.
     * @param game  Instancia principal del juego.
     */
    public CampoBatalla(JFrame frame, PulgaGame game) {
        super(0, 0, frame.getHeight(), frame.getWidth());
        random = new Random();
        lector = new LectorArchivoTextoPlano();
        this.game = game;
        escritor = new EscritorTextoPlano("src/autonoma/pulgasLocas/util/puntajes.txt");
        this.cargarMaximoPuntaje();
    }

    /**
     * Carga el máximo puntaje desde un archivo CSV.
     */
    private void cargarMaximoPuntaje() {
        try {
            maxPuntaje = lector.leerPuntajeAlto("src/autonoma/pulgasLocas/util/puntajes.txt");
        } catch (Exception e) {
            System.out.println("Error al cargar el máximo puntaje: " + e.getMessage());
            maxPuntaje = 0;
        }
    }

    /**
     * Guarda el puntaje actual en el archivo.
     */
    public void guardarPuntaje() {
        try {
            escritor.escribir("Pepito" + "," + Integer.toString(puntaje));
        } catch (Exception e) {
            System.out.println("Error al guardar el máximo puntaje: " + e.getMessage());
        }
    }

    /**
     * Agrega una nueva pulga normal al campo en una posición aleatoria no colisionante.
     */
    public void agregarPulgaNormal() {
        int tamanio = 30;
        int x, y;
        boolean colision;
        do {
            x = random.nextInt(width - tamanio);
            y = random.nextInt(height - tamanio);
            colision = false;
            for (Object obj : sprites) {
                Pulga p = (Pulga) obj;
                if (Math.abs(p.getX() - x) < tamanio && Math.abs(p.getY() - y) < tamanio) {
                    colision = true;
                    break;
                }
            }
        } while (colision);
        PulgaNormal nuevaPulga = new PulgaNormal(x, y, tamanio, tamanio);
        this.add(nuevaPulga);
    }

    /**
     * Agrega una nueva pulga mutante al campo en una posición aleatoria no colisionante.
     */
    public void agregarPulgaMutante() {
        int tamanio = 30;
        int x, y;
        boolean colision;
        do {
            x = random.nextInt(width - tamanio);
            y = random.nextInt(height - tamanio);
            colision = false;
            for (Object obj : sprites) {
                Pulga p = (Pulga) obj;
                if (Math.abs(p.getX() - x) < tamanio && Math.abs(p.getY() - y) < tamanio) {
                    colision = true;
                    break;
                }
            }
        } while (colision);
        PulgaMutante nuevaPulga = new PulgaMutante(x, y, tamanio, tamanio);
        this.add(nuevaPulga);
    }

    /**
     * Hace que todas las pulgas salten.
     */
   public void pulgasSaltan() {
    for (Object obj : sprites) {
        Pulga pulga = (Pulga) obj;
        pulga.saltar(this.getBoundaries());
    }
    reproducirSonido("src/autonoma/pulgasLocas/sounds/Saltos.wav");
}


    /**
     * Procesa un disparo con pistola, eliminando una pulga si es impactada.
     * Si se elimina la última pulga, se pregunta si se quiere reiniciar el juego.
     *
     * @param x Coordenada X del disparo.
     * @param y Coordenada Y del disparo.
     */
public void disparoPistola(int x, int y) {
    for (int i = 0; i < sprites.size(); i++) {
        Pulga pulga = (Pulga) sprites.get(i);
        if (x >= pulga.getX() && x <= pulga.getX() + pulga.getWidth()
                && y >= pulga.getY() && y <= pulga.getY() + pulga.getHeight()) {

            if (pulga instanceof PulgaMutante && !pulga.recibirImpacto()) {
                PulgaNormal pulgaNormal = ((PulgaMutante) pulga).convertirANormal();
                sprites.set(i, pulgaNormal);
                reproducirSonido("src/autonoma/pulgasLocas/sounds/Convertir.wav");
            } else {
                sprites.remove(i);
                puntaje++;
                reproducirSonido("src/autonoma/pulgasLocas/sounds/disparoPistola.wav");
            }
            break;
        }
    }

    if (this.sprites.size() == 0) {
        this.game.getSpawner().stop();
        String[] options = {"REINICIAR", "SALIR"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "¿Quieres continuar?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        boolean continuar = (choice == JOptionPane.YES_OPTION);
        if (continuar) {
            this.reiniciarJuego();
        } else {
            handleExitGame();
        }
    }
}

    /**
     * Solicita al usuario su nombre y guarda el puntaje antes de salir.
     */
    private void handleExitGame() {
        try {
            JTextField campoTexto = new JTextField();
            Object[] mensaje = {"Ingresa tu nombre:", campoTexto};
            int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Nombre del jugador", JOptionPane.OK_CANCEL_OPTION);
            if (opcion == JOptionPane.OK_OPTION) {
                String nombre = campoTexto.getText();
                this.escritor.escribir(nombre + "," + Integer.toString(puntaje));
            } else {
                JOptionPane.showMessageDialog(null, "PUNTAJE NO GUARDADO");
            }
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(CampoBatalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Dispara un misil que elimina la mitad de las pulgas aleatoriamente.
     */
    public void disparoMisil() {
        int cantidadEliminar = sprites.size() / 2;
        Collections.shuffle(sprites);
        for (int i = 0; i < cantidadEliminar; i++) {
            sprites.remove(0);
            puntaje++;
        }
         reproducirSonido("src/autonoma/pulgasLocas/sounds/Misil.wav");
    }

    /**
     * Verifica si todas las pulgas han sido eliminadas.
     *
     * @return true si no hay más pulgas, false en caso contrario.
     */
    public boolean todasPulgasEliminadas() {
        return sprites.isEmpty();
} 

    /**
     * Reinicia el juego, guardando el puntaje y restableciendo el estado inicial.
     */
    public void reiniciarJuego() {
        guardarPuntaje();
        if (maxPuntaje < puntaje) {
            maxPuntaje = puntaje;
        }
        puntaje = 0;
        sprites.clear();
        this.game.getSpawner().start();
    }

    /**
     * Obtiene el puntaje actual.
     *
     * @return puntaje actual del jugador.
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     * Obtiene el máximo puntaje alcanzado.
     *
     * @return puntaje máximo.
     */
    public int getMaxPuntaje() {
        return maxPuntaje;
    }

    /**
     * Retorna la cantidad actual de pulgas en el campo.
     *
     * @return número de pulgas restantes.
     */
    public int getCantidadPulgas() {
        return sprites.size();
    }

    /**
     * Maneja los eventos del teclado para acciones del juego.
     *
     * @param code código de la tecla presionada.
     */
    public void keyPressed(int code) {
        if (code == KeyEvent.VK_S) {
            pulgasSaltan();
        }
        if (code == KeyEvent.VK_R) {
            reiniciarJuego();
        }
        if (code == KeyEvent.VK_SPACE) {
            disparoMisil();
        }
        if (code == KeyEvent.VK_P) {
            agregarPulgaNormal();
        }
        if (code == KeyEvent.VK_M) {
            agregarPulgaMutante();
        }
    }

    /**
     * Método de actualización del contenedor (actualmente vacío).
     */
    public void update() {}

    /**
     * Pinta el campo de batalla, las pulgas y la información del juego.
     *
     * @param g contexto gráfico.
     */
    @Override
public void paint(Graphics g) {
    g.setColor(new Color(177, 224, 226));
    g.fillRect(x, y, width, height);

    for (Object sprite : sprites) {
        ((Sprite) sprite).paint(g);
    }
    Font fuente = new Font("Arial", Font.BOLD, 18);
    g.setFont(fuente);
    
    g.setColor(new Color(0, 153, 0)); 
    g.drawString("Puntaje: " + puntaje, 20, 90);

    g.setColor(new Color(0, 102, 204)); 
    g.drawString("Pulgas restantes: " + getCantidadPulgas(), 20, 70);

    g.setColor(new Color(204, 0, 0)); 
    g.drawString("Máximo puntaje: " + maxPuntaje, 20, 50);
}

    /**
     * Método de refresco del contenedor (actualmente vacío).
     */
    @Override
    public void refresh() {}

    /**
     * Obtiene los límites del campo de batalla.
     *
     * @return rectángulo que representa los límites del campo.
     */
    @Override
    public Rectangle getBoundaries() {
        return new Rectangle(x, y, width, height);
    }

    private void reproducirSonido(String ruta) {
    try {
        File archivoSonido = new File(ruta);
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(archivoSonido);
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
        System.out.println("Error al reproducir sonido: " + e.getMessage());
    }
}
}
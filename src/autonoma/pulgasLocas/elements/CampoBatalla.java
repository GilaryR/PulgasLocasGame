package autonoma.pulgasLocas.elements;



import autonoma.pulgasLocasBase.elements.EscritorTextoPlano;
import autonoma.pulgasLocasBase.elements.LectorArchivoTextoPlano;
import autonoma.pulgasLocasBase.elements.Sprite;
import autonoma.pulgasLocasBase.elements.SpriteContainer;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JFrame;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CampoBatalla extends SpriteContainer {

    private Random random;

    private int puntaje = 0;
    private int maxPuntaje = 0;
    private PulgaGame game;
    private LectorArchivoTextoPlano lector;
    private EscritorTextoPlano escritor;

    public CampoBatalla(JFrame frame, PulgaGame game) {
        super(0, 0, frame.getHeight(), frame.getWidth());
        random = new Random();
        lector = new LectorArchivoTextoPlano();
        this.game = game;
        escritor = new EscritorTextoPlano("maxpuntaje.csv");
        this.cargarMaximoPuntaje();
    }

    private void cargarMaximoPuntaje() {
        try {
            maxPuntaje = lector.leerPuntajeAlto("maxpuntaje.csv");
        } catch (Exception e) {
            System.out.println("Error al cargar el máximo puntaje: " + e.getMessage());
            maxPuntaje = 0;
        }
    }

    public void guardarPuntaje() {
        try {
            escritor.escribir("Pepito" + "," + Integer.toString(puntaje));
        } catch (Exception e) {
            System.out.println("Error al guardar el máximo puntaje: " + e.getMessage());
        }

    }

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

    public void pulgasSaltan() {
        for (Object obj : sprites) {
            Pulga pulga = (Pulga) obj;
            pulga.saltar(this.getBoundaries());
        }
    }

    public void disparoPistola(int x, int y) {
        for (int i = 0; i < sprites.size(); i++) {
            Pulga pulga = (Pulga) sprites.get(i);
            if (x >= pulga.getX() && x <= pulga.getX() + pulga.getWidth()
                    && y >= pulga.getY() && y <= pulga.getY() + pulga.getHeight()) {

                if (pulga instanceof PulgaMutante && !pulga.recibirImpacto()) {
                    PulgaNormal pulgaNormal = ((PulgaMutante) pulga).convertirANormal();
                    sprites.set(i, pulgaNormal);
                } else {
                    sprites.remove(i);
                    puntaje++;
                }
                break;
            }
        }
        if (this.sprites.size() == 0) {
            this.game.getSpawner().stop();
            String[] options = new String[2];
            options[0] = "REINICIAR";
            options[1] = "SALIR";
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "¿Quieres continuar?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, // icono
                    options, // texto de los botones
                    options[0] // botón por defecto
            );
            boolean continuar = (choice == JOptionPane.YES_OPTION);
            if (continuar) {
                this.reiniciarJuego();
            } else {
                handleExitGame();
            }
        }
    }

    private void handleExitGame() {
        try {
            JTextField campoTexto = new JTextField();
            Object[] mensaje = {
                "Ingresa tu nombre:", campoTexto
            };
            int opcion = JOptionPane.showConfirmDialog(
                    null,
                    mensaje,
                    "Nombre del jugador",
                    JOptionPane.OK_CANCEL_OPTION
            );
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

    public void disparoMisil() {
        int cantidadEliminar = sprites.size() / 2;
        Collections.shuffle(sprites);

        for (int i = 0; i < cantidadEliminar; i++) {
            sprites.remove(0);
            puntaje++;
        }
    }

    public boolean todasPulgasEliminadas() {
        return sprites.isEmpty();
    }

    public void reiniciarJuego() {
        guardarPuntaje();
        if(maxPuntaje < puntaje){
            maxPuntaje = puntaje;
        }
        puntaje = 0;
        sprites.clear();
        this.game.getSpawner().start();
    }

    public int getPuntaje() {
        return puntaje;
    }

    public int getMaxPuntaje() {
        return maxPuntaje;
    }

    public int getCantidadPulgas() {
        return sprites.size();
    }

    public void keyPressed(int code) {
        if (code == KeyEvent.VK_S) {
            // Hacer saltar todas las pulgas
            pulgasSaltan();
        }
        if (code == KeyEvent.VK_R) {
            // Reiniciar el juego
            reiniciarJuego();
        }
        if (code == KeyEvent.VK_SPACE) {
            // Disparar misil (eliminar la mitad de las pulgas)
            disparoMisil();
        }
        if (code == KeyEvent.VK_P) {
            // Agregar una nueva pulga normal
            agregarPulgaNormal();
        }
        if (code == KeyEvent.VK_M) {
            // Agregar una nueva pulga mutante
            agregarPulgaMutante();
        }
    }

    public void update() {

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(java.awt.Color.LIGHT_GRAY);
        g.fillRect(x, y, width, height);

        for (Object sprite : sprites) {
            ((Sprite) sprite).paint(g);
        }
        g.setColor(java.awt.Color.BLACK);
        g.drawString("Puntaje: " + puntaje, 20, 40);
        g.drawString("Pulgas restantes: " + getCantidadPulgas(), 20, 60);
        g.drawString("Máximo puntaje: " + maxPuntaje, 20, 80);
    }

    @Override
    public void refresh() {
    }

    @Override
    public Rectangle getBoundaries() {
        return new Rectangle(x, y, width, height);
    }

}

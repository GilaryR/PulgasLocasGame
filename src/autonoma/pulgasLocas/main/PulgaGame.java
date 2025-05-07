package autonoma.pulgasLocas.main;

import autonoma.pulgasLocas.elements.CampoBatalla;
import autonoma.pulgasLocas.elements.SpawnPulga;
import autonoma.pulgasLocas.ui.PulgasLocasWindow;
import java.awt.Graphics;

/**
 *
 * @author Alejandra
 */
public class PulgaGame implements Runnable {

    private Thread gameLoopThread;
    private PulgasLocasWindow ventana;
    private CampoBatalla campo;
    private SpawnPulga spawner;
    private final int FPS_SET = 5;
    private final int UPS_SET = 20;
    public PulgaGame() {
        this.ventana = new PulgasLocasWindow();
        this.campo = new CampoBatalla(ventana, this);
        ventana.setCampoBatalla(campo);
        initClasses();
        this.ventana.requestFocus();
        this.ventana.setVisible(true);
        this.startGameLoop();
    }
    public void update() {
        this.campo.update();
    }
    public void render(Graphics g) {
        this.campo.paint(g);
    }

    private void startGameLoop() {
        gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }

    private void initClasses() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                campo.agregarPulgaNormal();
            } else {
                campo.agregarPulgaMutante();
            }
        }
        this.spawner = new SpawnPulga(campo);
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        double deltaU = 0;
        double deltaF = 0;
        long lastCheck = System.currentTimeMillis();
       
        while (true) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                this.ventana.repaint();
                deltaF--;
                frames++;
            }
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
            
            }
        }
    }

    public SpawnPulga getSpawner() {
        return spawner;
    }

   
}

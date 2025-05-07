/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.pulgasLocas.elements;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juanp
 */
public class SpawnPulga implements Runnable {

    private CampoBatalla campo;
    private boolean running = true;
    protected Thread thread;

    public SpawnPulga(CampoBatalla campo) {
        this.campo = campo;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (running) {
            try {
                this.thread.sleep(5000);
                this.campo.agregarPulgaNormal();
                this.thread.sleep(10000);
                this.campo.agregarPulgaMutante();
            } catch (InterruptedException ex) {
                break;
            }
            
        }
    }
    public void start(){
        this.running = true;
        this.thread = new Thread(this);
        this.thread.start();
    }
    public boolean isRunning() {
        return running;
    }

    public void stop() {
        this.running = false;
        this.thread.interrupt();
    }
    
}

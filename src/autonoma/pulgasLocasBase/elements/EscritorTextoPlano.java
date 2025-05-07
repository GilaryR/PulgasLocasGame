
package autonoma.pulgasLocasBase.elements;

import autonoma.pulgasLocasBase.elements.Escritor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 *
 * @author juanp
 */
public class EscritorTextoPlano implements Escritor {

    private final Charset charset = Charset.forName("UTF-8");
    private static final String HEADER = "nombre,puntaje";
    private String filePath;

    public EscritorTextoPlano(String filePath) {
        this.filePath = filePath;
    }
    public void crearArchivo() throws IOException{
        File fichero = new File(this.filePath);
        FileWriter writer = new FileWriter(fichero, true);
        PrintWriter out = new PrintWriter(writer);
        out.println(this.HEADER);
        out.close();
        
    }
    @Override
    public void escribir(String texto) throws IOException {
        File fichero = new File(this.filePath);
        if(!fichero.exists()){
            crearArchivo();
        }
        FileWriter writer = new FileWriter(fichero, true);
        PrintWriter pw = new PrintWriter(writer);
        pw.println(texto);
        writer.close();
    }
}


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Apuestas
 */
public class Extraccion {
    
    
    public Extraccion(String link, String archivo) throws FileNotFoundException, IOException
    {
        URL url = new URL(link);
            InputStream input = url.openStream();
            OutputStream output = new FileOutputStream(archivo);
            byte[] b = new byte[2048];
            int longitud;

            while ((longitud = input.read(b)) != -1) {
               output.write(b, 0, longitud);
            }
    }
}

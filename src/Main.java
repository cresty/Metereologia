import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;

public class Main {

    /**
     * @param args the command line arguments
     * @throws FileNotFoundException 
     */
    
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
//    	Carga carga = new Carga("C:/aberporth.txt");
        
        
        String url = "http://www.metoffice.gov.uk/pub/data/weather/uk/climate/stationdata/leucharsdata.txt";
        String archivo = "C:\\Users\\cresty\\Documents\\archivo.txt";
        Extraccion ext = new Extraccion(url,archivo);
        Carga carga = new Carga(archivo);
        Seleccion a = new Seleccion (carga);
        
        JFrame frame = new JFrame("Informacion Meteorologica");
        frame.setSize(new Dimension(1500, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(a);
        frame.setVisible(true);
        
    }
}
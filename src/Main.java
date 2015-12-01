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
        Observador o = new Observador(0,0);
        Seleccion a = new Seleccion (carga,o);
        o.addObserver(a);
//    	carga.printHeader();
//    	System.out.println("asd");
//        carga.printData();
//    	System.out.println("asd");
//        Seleccion ventana = new Seleccion();
        
        JFrame frame = new JFrame("Informacion Meteorologica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(a);
        
        frame.setSize(250, 350);
        frame.setVisible(true);
////        carga.calcularAno();
//        System.out.println(carga.buscarAno(2015));
//        System.out.println(carga.data.size());   
    }
}
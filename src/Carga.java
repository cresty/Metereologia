import java.io.*;
import static java.lang.Math.pow;
import java.util.*;

public class Carga{
	
	ArrayList <String> header = new ArrayList<>();
	ArrayList <Linea> data = new ArrayList<>();
        public Carga (String f) throws FileNotFoundException {
            
            File archivo = new File(f);
            try (Scanner s = new Scanner(archivo)) {
                s.useDelimiter("\\s+|\\n");
                s.useLocale(Locale.ENGLISH);
                String dump;
                ArrayList <Float> list = new ArrayList();
                while (!s.hasNext("yyyy"))
                    dump = s.next();
                while (!s.hasNextFloat())
                    header.add(s.next());
                //printHeader();
                while (s.hasNext())
                {
                    list.clear();
                    for (int i=0;i<header.size()-5;i++) {
                        if (s.hasNextFloat())
                            list.add(s.nextFloat());
                        else if (s.hasNext("---"))
                        {
                            list.add((float)0);
                            dump = s.next();
                        }
                        else
                        {
                            dump = s.next();
                            float n=0;
                            float aux=(float) pow(10, dump.length()-4);
                            //System.out.println(aux);
                            for (int k = 0; k < dump.length(); k++)
                            {
                                char caracter = dump.charAt(k);
                                if(isNumeric(caracter))
                                {
                                    n = ((float)Character.getNumericValue(caracter)*aux)+n;
                                    aux = aux/10;
                                }
                            }
                            list.add(n);
                        }
                    }
                    if (s.hasNext("Provisional"))
                        dump = s.next();
                    Linea l = new Linea(list);
                    addData(l);
                }
            }
	}
	public void printHeader()
        {
            for (int i=0;i<header.size()-5;i++)
                System.out.println(header.get(i));
        }
        public void printData()
        {
            for (Linea data1 : data)
                System.out.println(data1.getData());
        }
        public final void addData(Linea l)
        {
            ArrayList <Float> linea = new ArrayList();
            for (int i=0;i<l.getData().size();i++)
                linea.add(l.getData(i));
            Linea l2 = new Linea (linea);
            data.add(l2);
        }
        public int calcularAno()
        {
            return (int) (data.get(data.size()-1).getAno()-data.get(1).getAno());
        }
        public int buscarAno(float a)
        {
            for (int i = 0;i<data.size();i++)
                if (a == data.get(i).getAno())
                {
                    return i;
                }
            return 0;
        }
        private static boolean isNumeric(char caracter)
        {
            try {Integer.parseInt(String.valueOf(caracter));
            return true;
            } catch (NumberFormatException ex){
            return false;
            }
        }
}
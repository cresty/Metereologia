/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cresty
 */
import java.util.ArrayList;

public final class Linea  {
	
    ArrayList <Float> data = new ArrayList();
    ArrayList <String> mes = new ArrayList();

    public Linea(float l)
    {
        data.add(l);
    }
    
    public Linea(ArrayList <Float> l)
    {
        data = l;
        crearMes();
    }
    
    public void printLine()
    {
        for (int i=0;i<data.size();i++)
        {
            System.out.println(data.get(i));
            System.out.println(mes.get(0));
        }
    }
    
    public Float getData(int n)
    {
        return data.get(n);
    }
    
     public void addData(float n)
    {
        data.add(n);
    }
     
     public int getSize()
    {
        return data.size();
    }
     
     public ArrayList getData()
    {
        return data;
    }
     
    public float getAno()
    {
        return data.get(0);
    }
    
    public String getMes()
    {
        return mes.get(0);
    }
    
    public void crearMes()
    {
        int a;
        float f;
        f = data.get(1);
        a = (int)f;
        switch (a) 
        {
            case 1:  mes.add("Enero");
                break;
            case 2:  mes.add("Febrero");
                break;
            case 3:  mes.add("Marzo");
                break;
            case 4:  mes.add("Abril");
                break;
            case 5:  mes.add("Mayo");
                break;
            case 6:  mes.add("Junio");
                break;
            case 7:  mes.add("Julio");
                break;
            case 8:  mes.add("Agosto");
                break;
            case 9:  mes.add("Septiembre");
                break;
            case 10: mes.add("Octubre");
                break;
            case 11: mes.add("Noviembre");
                break;
            case 12: mes.add("Diciembre");
                break;
        }
    }
}

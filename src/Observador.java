
import java.util.Observable;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cresty
 */
public class Observador extends Observable{
    
    public int obsAno;
    public int obsCat=1;
    
    public Observador( int ano, int cat)
    {
       this.obsAno = ano;
       this.obsCat = cat;
    }
    public void setValor(int ano, int cat) 
    {
        this.obsAno = ano;
        this.obsCat = cat;
        setChanged();
        notifyObservers();
    }
    public int getObsAno() 
    {
        return obsAno;
    }
    public int getObsCat()
    {
        return obsCat;
    }
    
}

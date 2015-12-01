/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
/**
 *
 * @author cresty
 */
public class Seleccion extends JPanel implements Observer{

  JList list, list2;

  DefaultListModel model, model1;

  int counter = 15;
  Observador o = null;
  Carga a = null;
  
  
  public Seleccion(final Carga a, final Observador o) {
    setLayout(new BorderLayout());
    
    model = new DefaultListModel();
    model1 = new DefaultListModel();
    this.a = a;
    this.o = o;
    list = new JList(model);
    list2 = new JList(model1);
    JScrollPane ano = new JScrollPane(list);
    JScrollPane mes = new JScrollPane(list2);
    JButton pieButton = new JButton("Mostrar Pie Chart");
    JButton lineButton = new JButton("Mostrar Line Chart");
    for (int i = 0; i <= a.calcularAno(); i++)
        model.addElement((int)(a.data.get(0).getAno()+i));
    for (int i = 2; i<a.header.size()-5;i++)
        model1.addElement(a.header.get(i));
    
    ListSelectionListener listenList1 = new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent listSelectionEvent) {
        boolean adjust = listSelectionEvent.getValueIsAdjusting();
        if (!adjust)
            if (!list.isSelectionEmpty() && !list2.isSelectionEmpty())
                o.setValor((int)list.getSelectedValue(), list2.getSelectedIndex());
      }
    };
    
    list.addListSelectionListener(listenList1);
    list2.addListSelectionListener(listenList1);
//    pieButton.addActionListener(new ActionListener() 
//    {
//        @Override
//        public void actionPerformed(ActionEvent e) 
//        {
//            int ano = (int) list.getSelectedValue();
//            float ano2 = (float)ano;
//            int select = list2.getSelectedIndex()+2;
//            ano = a.buscarAno(ano2);
//            DefaultPieDataset data = new DefaultPieDataset();
//            for (int i=ano;i<ano+12;i++)
//            {
//                if  (i < a.data.size())
//                    if ((a.data.get(i).data.get(0) == ano2) )
//                        data.setValue(a.data.get(i).getMes(), (a.data.get(i).data.get(select)));
//            }
//            JFreeChart chart = ChartFactory.createPieChart(a.header.get(select),data,true,true,false);
//            ChartFrame frame = new ChartFrame("Grafico de Sectores", chart);
//            frame.pack();
//            frame.setVisible(true);
//        }
//    });
//    
//    lineButton.addActionListener(new ActionListener() 
//    {
//        @Override
//        public void actionPerformed(ActionEvent e) 
//        {
//            int ano = (int) list.getSelectedValue();
//            float ano2 = (float)ano;
//            int select = list2.getSelectedIndex()+2;
//            ano = a.buscarAno(ano2);
//            DefaultCategoryDataset data = new DefaultCategoryDataset();
//            for (int i=ano;i<ano+12;i++)
//                if  (i < a.data.size())
//                    if (a.data.get(i).data.get(0) == ano2)
//                        data.addValue((a.data.get(i).data.get(select)), a.header.get(select+5) ,a.data.get(i).getMes());
//            JFreeChart chart = ChartFactory.createLineChart(a.header.get(select),"Mes",a.header.get(select+5),data,PlotOrientation.VERTICAL,true,true,false);
//            ChartFrame frame = new ChartFrame("Grafico Lineal", chart);
//            frame.pack();
//            frame.setVisible(true);
//        }
//    });
    add(ano, BorderLayout.NORTH);
    add(mes, BorderLayout.CENTER);
  }

    @Override
    public void update(Observable obs, Object obj) 
    {
        if (obs == o)
        {
            int ano = this.o.getObsAno();
            float ano2 = (float)ano;
            int select = this.o.getObsCat()+2;
            ano = a.buscarAno(ano2);
            DefaultPieDataset data = new DefaultPieDataset();
            for (int i=ano;i<ano+12;i++)
                if  (i < a.data.size())
                    if ((a.data.get(i).data.get(0) == ano2) )
                        data.setValue(a.data.get(i).getMes(), (a.data.get(i).data.get(select)));
            JFreeChart chart = ChartFactory.createPieChart(a.header.get(select),data,true,true,false);
            ChartFrame frame = new ChartFrame("Grafico de Sectores", chart);
            frame.pack();
            frame.setVisible(true);
        }
    }

}

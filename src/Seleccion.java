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
import org.jfree.chart.plot.dial.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;
/**
 *
 * @author cresty
 */
public class Seleccion extends JPanel implements Observer{

    JList list, list2;
    JPanel tmax = new JPanel();
    int ano1, select;
    float ano2, dato = 0;
    boolean t=false;
    
    

    DefaultListModel model, model1;

    int counter = 15;
    int c = 0;
    Observador o = null;
    Carga a = null;
  
    public Seleccion(final Carga a) {
        setLayout(new BorderLayout());
        model = new DefaultListModel();
        model1 = new DefaultListModel();
    //    model2 = new DefaultFrameModel();

        this.a = a;
        list = new JList(model);
        list2 = new JList(model1);
        JScrollPane ano = new JScrollPane(list);
        JScrollPane mes = new JScrollPane(list2);

    
//    JButton pieButton = new JButton("Mostrar Pie Chart");
//    JButton lineButton = new JButton("Mostrar Line Chart");
        for (int i = 0; i <= a.calcularAno(); i++)
            model.addElement((int)(a.data.get(0).getAno()+i));
        for (int i = 2; i<a.header.size()-5;i++)
            model1.addElement(a.header.get(i));
    
    ListSelectionListener listenList1 = new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent listSelectionEvent) {
        boolean adjust = listSelectionEvent.getValueIsAdjusting();
        if (!adjust)
            if (!list.isSelectionEmpty() && !list2.isSelectionEmpty())
            {
                dato = 0;
                c = 0;
                tmax.removeAll();
                ano1 = (int) list.getSelectedValue();
                ano2 = (float)ano1;
                int select = list2.getSelectedIndex()+2;
                ano1 = a.buscarAno(ano2);
//                DefaultPieDataset data = new DefaultPieDataset();
                for (int i=ano1;i<ano1+12;i++)
                    if  (i < a.data.size())
                        if ((a.data.get(i).data.get(0) == ano2) )
                        {
                            dato = a.data.get(i).data.get(select) + dato;
                            c++;
                        }
                dato = dato/c;
                            //data.setValue(a.data.get(i).getMes(), (a.data.get(i).data.get(select)));
//                JFreeChart chart = ChartFactory.createPieChart(a.header.get(select),data,true,true,false);
//                //ChartFrame frame = new ChartFrame("Grafico de Sectores", chart);
//                ChartPanel cp = new ChartPanel(chart);
//                cp.setPreferredSize(new Dimension(300, 300)); //size according to my window
//                cp.setMouseWheelEnabled(true);
//                add(cp);
//                revalidate();
//                t = true;
                
			DefaultValueDataset dataset = new DefaultValueDataset(dato);
			JFreeChart jfreechart = createStandardDialChart(a.header.get(select), "Value", dataset, a.getMin(select), a.getMax(select), (a.getMax(select)-a.getMin(select))/10, 4);
			DialPlot dialplot = (DialPlot)jfreechart.getPlot();
			StandardDialRange standarddialrange = new StandardDialRange(((a.getMax(select)-a.getMin(select))*(2/3))+a.getMin(select), a.getMax(select), Color.red);
			standarddialrange.setInnerRadius(0.52000000000000002D);
			standarddialrange.setOuterRadius(0.55000000000000004D);
			dialplot.addLayer(standarddialrange);
			StandardDialRange standarddialrange1 = new StandardDialRange(((a.getMax(select)-a.getMin(select))*(1/3))+a.getMin(select), ((a.getMax(select)-a.getMin(select))*(2/3))+a.getMin(select), Color.orange);
			standarddialrange1.setInnerRadius(0.52000000000000002D);
			standarddialrange1.setOuterRadius(0.55000000000000004D);
			dialplot.addLayer(standarddialrange1);
			StandardDialRange standarddialrange2 = new StandardDialRange(a.getMin(select), ((a.getMax(select)-a.getMin(select))*(1/3))+a.getMin(select), Color.green);
			standarddialrange2.setInnerRadius(0.52000000000000002D);
			standarddialrange2.setOuterRadius(0.55000000000000004D);
			dialplot.addLayer(standarddialrange2);
			GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
			DialBackground dialbackground = new DialBackground(gradientpaint);
			dialbackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
			dialplot.setBackground(dialbackground);
			dialplot.removePointer(0);
			org.jfree.chart.plot.dial.DialPointer.Pointer pointer = new org.jfree.chart.plot.dial.DialPointer.Pointer();
			pointer.setFillPaint(Color.yellow);
			dialplot.addPointer(pointer);
			ChartPanel cp = new ChartPanel(jfreechart);
                        cp.setMouseWheelEnabled(true);
                        add(cp);
                        revalidate();
            }
      };
    };
    
    list.addListSelectionListener(listenList1);
    list2.addListSelectionListener(listenList1);
    add(ano, BorderLayout.NORTH);
    add(mes, BorderLayout.SOUTH);
//    add(tmax, BorderLayout.CENTER);
//    add(tmin, BorderLayout.CENTER);
//    add(af, BorderLayout.CENTER);
//    add(rain, BorderLayout.CENTER);
//    add(sun, BorderLayout.CENTER);
    
    
  }

    @Override
    public void update(Observable obs, Object obj) 
    {
    }
    
        public static JFreeChart createStandardDialChart(String s, String s1, ValueDataset valuedataset, double d, double d1, double d2, int i)
		{
			DialPlot dialplot = new DialPlot();
			dialplot.setDataset(valuedataset);
			dialplot.setDialFrame(new StandardDialFrame());
			dialplot.setBackground(new DialBackground());
			DialTextAnnotation dialtextannotation = new DialTextAnnotation(s1);
			dialtextannotation.setFont(new Font("Dialog", 1, 14));
			dialtextannotation.setRadius(0.69999999999999996D);
			dialplot.addLayer(dialtextannotation);
			DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
			dialplot.addLayer(dialvalueindicator);
			StandardDialScale standarddialscale = new StandardDialScale(d, d1, -120D, -300D, 10D, 4);
			standarddialscale.setMajorTickIncrement(d2);
			standarddialscale.setMinorTickCount(i);
			standarddialscale.setTickRadius(0.88D);
			standarddialscale.setTickLabelOffset(0.14999999999999999D);
			standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
			dialplot.addScale(0, standarddialscale);
			dialplot.addPointer(new org.jfree.chart.plot.dial.DialPointer.Pin());
			DialCap dialcap = new DialCap();
			dialplot.setCap(dialcap);
			return new JFreeChart(s, dialplot);
		}

}

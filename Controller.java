package Lab_4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Controller extends JPanel implements ChangeListener, ActionListener{
     
    private Model myM;
    private View myW;
    private JSlider LChanger;/*man ändra på numeriska värdena på L* när programmet körs*/
    private JSlider deltaChanger; /*ändrar numeriska värden på delta t */
    private Timer myT;
    private int delta;
    private int i = 0;
    private double newTime = 0;
    private int nTimes = 100;
    private double[] Time = new double[nTimes];
    private StringBuilder infoList = new StringBuilder();
    
    /*tar objektens koordinater*/
    public Controller(Model m, View w){//konstruktorn tar ett model och view obj
        myM = m;
        myW = w;
        myM.setL(0.34);//start värde på L
        delta = 100; //millisek
        myT = new Timer(delta, this);/*läger 100 millisekund per förändring*/
        myT.start();
        myT.addActionListener(this);
        
        LChanger = new JSlider(0, 15, 1);/*efter att ha använt sliden körs det med L = 1*/
        LChanger.setMajorTickSpacing(5);/*stegen i lidern*/
        LChanger.setMinorTickSpacing(1);
        LChanger.setPaintTicks(true);
        LChanger.setPaintLabels(true);
        LChanger.addChangeListener(this);
        add(LChanger);
        
        deltaChanger = new JSlider(0, 1000, 100);
        deltaChanger.setMajorTickSpacing(500);
        deltaChanger.setMinorTickSpacing(100);
        deltaChanger.setPaintTicks(true);
        deltaChanger.setPaintLabels(true);
        deltaChanger.addChangeListener(this);
        add(deltaChanger);
    }
    
    public void stateChanged(ChangeEvent e){
        if(e.getSource()==LChanger){
            myM.setL(LChanger.getValue());/*getValue finns i JSLider*/           
        }
        if(e.getSource()==deltaChanger){
            myT.setDelay(deltaChanger.getValue());/*setDelay hämtar tidsintervallen mellan eventen från deltaChanger*/
        }        
    }
    public void actionPerformed(ActionEvent e){
        myM.setPos();
        double[] p = myM.getPos();/*måla om för nya positionerna för partiklarna*/
        myW.repaint();
        
        Time[i] = newTime*0.001;/* göra om till sekunder*/
        newTime += (double) myT.getDelay();
        infoList.append(Time[i]+", ");/* för att spara alla värden i CSV fil som sträng med siffror. kommatecken separerar variabelvärden.*/
        
        for (int j = 0; j< p.length; j++){/*sparar alla värden på koordinater per tidssteg*/
            infoList.append(p[j]+", ");
        }
        
        infoList.append("\n");
        i+=1; /*nytt tidssteg*/
        if (i==nTimes){ /* fortsätt loopa tills den gått igenom ett visst antal tidssteg*/
            myT.stop(); /*stanna när i==ntimes*/
            PrintWriter creatFile; /* skapa filen t_pos_data som en csv fil. För att MatLab ska kunna läsa*/
            try{
                creatFile = new PrintWriter("t_pos_data.csv");/* Comma Separated Values (CSV) skapas med append ovan*/ 
                creatFile.println(infoList);
                creatFile.close();
            }
            catch(FileNotFoundException e1){
                e1.printStackTrace(); /* error om filen inte går att skapa */
            }
        }      
    }    
}

package Lab_4;

import java.awt.*;
import javax.swing.JPanel;
import java.awt.geom.*;

public class View extends JPanel {

private int Xstor = 1000;/*storlekarna av fönstret*/
private int Ystor = 1000;
private Model m;    
private double[] myPos; /*vill ha en lista av partiklarnas pos*/    
    
    /*tar Model-objekt som argument för att veta vad  den ska rita*/
    public View(Model m) { 
        setPreferredSize(new Dimension(Xstor, Ystor));//storleken på fönstret.
        this.m = m;       
    }
    /*metoden paint ritar upp partiklarna i fönstret.*/
    public void paint(Graphics g){ 
        Graphics2D g2D = (Graphics2D)g;
        myPos = m.getPos();/*tar objektens koordinater*/
       
        /* Sudda ut hela ritytan, så att gamla bollar försvinner*/
        g2D.clearRect(0,0,Xstor, Ystor);
        g2D.setPaint(Color.BLACK); //färg på partiklarna
        
        /*tagen från övningen*/ 
    for ( int i =0;i<myPos.length; i = i + 2){ /* hoppar två steg i listan myPos för att ta x, respektive y värden.*/
        g2D.fill(new Ellipse2D.Double((myPos[i]*Xstor),(myPos[i+1]*Ystor),10,10));
    }  /* ritar ut partiklarna som små ellipser [ovan]*/
    }
}


package Lab_4;
import java.awt.*;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
    
    public MyFrame(){
        Model model = new Model(100000);/*skapar model objekt och antalet partiklar som skall medföljas programmet klarar av 100.000 partiklar.*/
        View view = new View(model);/*skapar view objekt*/
        Controller c = new Controller(model, view);/*tar med ett ocjekt c av typen kontroller*/
        
        this.setBackground(Color.YELLOW);
        this.add(BorderLayout.NORTH, view);
        add(c);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }
    
    public static void main(String[] args){
        new MyFrame();
    }
}

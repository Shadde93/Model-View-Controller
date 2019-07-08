package Lab_4;

import java.util.*;


public class Model {
    
    private double L, angle;
    private Particle[] particles; /*för att skapa en array med partiklarna*/
    
    /*konsturktor anger hur många partiklar som ska ingår*/
    public Model(int n){
        //setL(0.34);/*avstånd mellan partiklarna*/
        particles = new Particle[n];
        for(int i=0; i< n; i++){
            particles[i]= new Particle();/*skapar n objekt av typen particel och stoppar in en array*/
        }       
    }
    /*uppdaterar posi för partiklar*/
    public void setPos(){
        for (int i = 0; i< particles.length ;i++ ){
            particles[i].upPos();/*uppdaterar partiklarnas positioner i arrayn*/ 
        }
    } 
    public void setL(double Linput){
        L = Linput;
    }
    
    public double getL(){
        return L;
    }
    /*får pos från arryn*/
    public double[] getPos(){
        double[] myPos = new double[particles.length*2];/* gånger 2 för x och y pos*/
        this.setPos();
        int j = 0;
        for (int i= 0; i< particles.length; i = i +1){/*vill få respektive kordinat för alla partiklar, initierar j varrannan dvs*/
            myPos[j] = particles[i].getpX();
            myPos[j+1] = particles[i].getpY();
            j = j+2;
        }
        return myPos;
    }
    
    public class Particle{
        private double pX, pY;
    /*E1.3 fördelen är att skapa unika partiklar med specifika postioner*/
        public Particle(double x, double y ){/*kontruktor med 2 koordi*/
            x = pX;
            y = pY;
        }
        /*konstrukto som slumpar ut positionerna*/
        public Particle(){
            this.pX = Math.abs(new Random().nextDouble());/*slumpar positioer*/
            this.pY = Math.abs(new Random().nextDouble());
            pX = 0.5;
            pY = 0.5;
        }
        /*ger postionerna*/
        public void upPos(){
        angle = (Math.random()*2*Math.PI);
        pX = pX + L*Math.cos(angle);
        pY = pY + L*Math.sin(angle);
        }     
        
        /*returnera positionerna på partiklarna*/
        public double getpX(){
            return pX;
        }
         public double getpY(){
            return pY;
        }    
    }    
}



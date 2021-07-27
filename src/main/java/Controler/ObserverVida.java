package Controler;

import Modelo.Hero;
import java.util.Observable;
import java.util.Observer;

public class ObserverVida implements Observer {
    private int iVida;
    
    @Override
    public void update(Observable o, Object arg) { 
        Hero temp = (Hero)o;
        this.iVida = temp.getVidas();
        
        if(this.iVida > 0){
            System.out.println("\nObserverVida: Você perdeu uma vida!");
            System.out.println("ObserverVida: Vidas restantes: " + this.iVida + "\n");
        }else{
            System.out.println("\nObserverVida: Suas vidas acabaram!");
        }
    }
    
}

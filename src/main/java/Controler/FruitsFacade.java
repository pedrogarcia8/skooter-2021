package Controler;

import Modelo.BlackDiamondSquare;
import Modelo.BunchOfGrapes;
import Modelo.Cherry;
import Modelo.Elemento;
import Modelo.Hero;
import Modelo.OrangeSquare;
import Modelo.Papaya;
import Modelo.Strawberry;
import java.util.ArrayList;

public class FruitsFacade {
    private BunchOfGrapes bunchOfGrapes;
    private Papaya papaya;
    private Strawberry strawberry;
    private Cherry cherry;
    
    public FruitsFacade(){
        this.bunchOfGrapes = new BunchOfGrapes("bunchOfGrapes.png");
        this.papaya = new Papaya("papaya.png");
        this.strawberry = new Strawberry("strawberry.png");
        this.cherry = new Cherry("cherry.png");
    }
    
    public ArrayList<Elemento> montar(ArrayList<Elemento> elementos, int fase){
        switch(fase){
            case 1: 
                //Desenhando o cacho de uva
                bunchOfGrapes.setPosicao(0, 0);
                elementos.add(bunchOfGrapes); 

                //Desenhando o mamão
                papaya.setPosicao(0, 10);
                elementos.add(papaya);

                //Desenhando o morango
                strawberry.setPosicao(10, 0);
                elementos.add(strawberry);

                //Desenhando a cereja
                cherry.setPosicao(10, 10);
                elementos.add(cherry);
                break;
            case 2:
                //Desenhando o cacho de uva
                bunchOfGrapes.setPosicao(1, 5);
                elementos.add(bunchOfGrapes); 

                //Desenhando o mamão
                papaya.setPosicao(5, 1);
                elementos.add(papaya);

                //Desenhando o morango
                strawberry.setPosicao(5, 9);
                elementos.add(strawberry);

                //Desenhando a cereja
                cherry.setPosicao(9, 5);
                elementos.add(cherry);
                break;
            case 3:
                //Desenhando o cacho de uva
                bunchOfGrapes.setPosicao(5, 0);
                elementos.add(bunchOfGrapes); 

                //Desenhando o mamão
                papaya.setPosicao(5, 2);
                elementos.add(papaya);

                //Desenhando o morango
                strawberry.setPosicao(5, 8);
                elementos.add(strawberry);

                //Desenhando a cereja
                cherry.setPosicao(5, 10);
                elementos.add(cherry);
                break;
            case 4:
                //Desenhando o cacho de uva
                bunchOfGrapes.setPosicao(0, 0);
                elementos.add(bunchOfGrapes); 

                //Desenhando o mamão
                papaya.setPosicao(0, 10);
                elementos.add(papaya);

                //Desenhando o morango
                strawberry.setPosicao(10,0);
                elementos.add(strawberry);

                //Desenhando a cereja
                cherry.setPosicao(10, 10);
                elementos.add(cherry);
                break;
        }
        
        return elementos;
    }
   
}

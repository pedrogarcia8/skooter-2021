package Controler;

import Modelo.*;
import Auxiliar.*;
import java.util.ArrayList;

public class Fase {
    private Hero hHero;
    private Monster blueRobot;
    private Monster yellowRobot;
    private Monster pinkRobot;
    private Monster greenRobot;
    private ArrayList<Elemento> eElementos;
    private int iFaseAtual;
    
    public Fase(){
        this.iFaseAtual = 1;
        eElementos = new ArrayList<Elemento>(121);
    }
    
    public ArrayList<Elemento> getElementosFase(int numeroFase){
        switch(numeroFase) {
            case 1:
                this.cenarioPrimeiraFase();
              break;
            case 2:
                this.cenarioSegundaFase();
              break;
            case 3:
                this.cenarioTerceiraFase();
              break;
            case 4:
                this.cenarioQuartaFase();
              break;
            case 5:
              // code block
              break;
          }
        return eElementos;
    }
    
    public Hero getHero(){
        return hHero;
    }
    
    public Monster getBlueRobot(){
        return blueRobot;
    }
    
    public Monster getYellowRobot(){
        return yellowRobot;
    }
    
    public Monster getPinkRobot(){
        return pinkRobot;
    }
    
    public Monster getGreenRobot(){
        return greenRobot;
    }
    
    private void cenarioPrimeiraFase(){
        this.eElementos.clear();
        this.iFaseAtual = 1;
        this.hHero = new Hero("hero.png");  
        this.hHero.setPosicao(4, 4);
        this.addElemento(this.hHero);

        //Desenhando diamantes pretos
        GreenSquare greenSquare;
        for(int i = 3; i <= 9; i+=6){
            greenSquare = new GreenSquare("blackDiamondSquare.png");
            greenSquare.setPosicao(i, 0);
            this.addElemento(greenSquare);
        }
        
        for(int i = 0; i <= 5; i+=2){
            greenSquare = new GreenSquare("blackDiamondSquare.png");
            greenSquare.setPosicao(i, 1);
            this.addElemento(greenSquare);
        }
        
        for(int i = 1; i <= 11; i+=4){
            greenSquare = new GreenSquare("blackDiamondSquare.png");
            greenSquare.setPosicao(i, 2);
            this.addElemento(greenSquare);
        }
        
        greenSquare = new GreenSquare("blackDiamondSquare.png");
        greenSquare.setPosicao(8, 3);
        this.addElemento(greenSquare);
            
        for(int i = 0; i <= 6; i+=2){
            if(i == 4)
                i += 2;
            
            greenSquare = new GreenSquare("blackDiamondSquare.png");
            greenSquare.setPosicao(i, 5);
            this.addElemento(greenSquare);
        }
        
        for(int i = 5; i <= 11; i+=4){
            greenSquare = new GreenSquare("blackDiamondSquare.png");
            greenSquare.setPosicao(i, 6);
            this.addElemento(greenSquare);
        }
        
        for(int i = 6; i <= 11; i+=4){
            greenSquare = new GreenSquare("blackDiamondSquare.png");
            greenSquare.setPosicao(i, 7);
            this.addElemento(greenSquare);
        }
        
        for(int i = 1; i <= 11; i+=2){
            if(i == 5)
                i += 2;
            
            greenSquare = new GreenSquare("blackDiamondSquare.png");
            greenSquare.setPosicao(i, 8);
            this.addElemento(greenSquare);
        }
        
        for(int i = 4; i <= 11; i+=4){
            greenSquare = new GreenSquare("blackDiamondSquare.png");
            greenSquare.setPosicao(i, 9);
            this.addElemento(greenSquare);
        }
        
        for(int i = 1; i <= 11; i+=6){
            greenSquare = new GreenSquare("blackDiamondSquare.png");
            greenSquare.setPosicao(i, 10);
            this.addElemento(greenSquare);
        }
        
        OrangeSquare orangeSquare; //Desenhando quadrados laranjas
        for(int i = 1; i <= 9; i+=2){
            for(int j = 1; j <= 9; j+=2){
                orangeSquare = new OrangeSquare("orangeSquare.png");
                orangeSquare.setPosicao(i, j);
                this.addElemento(orangeSquare);
            }
        }
        
        //Desenhando o cacho de uva
        BunchOfGrapes bunchOfGrapes = new BunchOfGrapes("bunchOfGrapes.png");
        bunchOfGrapes.setPosicao(0, 0);
        this.addElemento(bunchOfGrapes); 
        
        //Desenhando o mamão
        Papaya papaya = new Papaya("papaya.png");
        papaya.setPosicao(0, 10);
        this.addElemento(papaya);
        
        //Desenhando o morango
        Strawberry strawberry = new Strawberry("strawberry.png");
        strawberry.setPosicao(10, 0);
        this.addElemento(strawberry);
        
        //Desenhando a cereja
        Cherry cherry = new Cherry("cherry.png");
        cherry.setPosicao(10, 10);
        this.addElemento(cherry);
        
        this.blueRobot = new Monster("blueRobot.png");
        this.blueRobot.setPosicao(10, 1);
        this.addElemento(this.blueRobot);
        
        this.yellowRobot = new Monster("yellowRobot.png");
        this.yellowRobot.setPosicao(2, 0);
        this.addElemento(this.yellowRobot);
        
        this.pinkRobot = new Monster("pinkRobot.png");
        this.pinkRobot.setPosicao(0, 9);
        this.addElemento(this.pinkRobot);
        
        this.greenRobot = new Monster("greenRobot.png");
        this.greenRobot.setPosicao(10, 9);
        this.addElemento(this.greenRobot);
    }
    
    private void cenarioSegundaFase(){ 
        this.eElementos.clear();
        this.iFaseAtual = 2;
        this.hHero = new Hero("hero.png");  
        this.hHero.setPosicao(5, 5);
        this.addElemento(this.hHero);
        
        //Desenhando a seta virada para cima
        Arrow upArrow;
        for(int i = 3; i <= 7; i+=2){
            upArrow = new Arrow("upArrow.png", "UP");
            upArrow.setPosicao(i, 0);
            this.addElemento(upArrow);
        }
        
        for(int i = 3; i <= 7; i+=4){
            for(int j = 6; j <= 10; j+=2){
                upArrow = new Arrow("upArrow.png", "UP");
                upArrow.setPosicao(i, j);
                this.addElemento(upArrow);
            }
        }
        
        //Desenhando a seta virada para baixo
        Arrow downArrow; 
        for(int i = 3; i <= 7; i+=4){
            for(int j = 2; j <= 4; j+=2){
                downArrow = new Arrow("downArrow.png", "DOWN");
                downArrow.setPosicao(i, j);
                this.addElemento(downArrow);
            }
        }
        
        downArrow = new Arrow("downArrow.png", "DOWN");
        downArrow.setPosicao(3, 10);
        this.addElemento(downArrow);
        
        //Desenhando a seta virada para a esquerda
        Arrow leftArrow; 
        for(int i = 5; i <= 7; i+=2){
            leftArrow = new Arrow("leftArrow.png", "LEFT");
            leftArrow.setPosicao(2, i);
            this.addElemento(leftArrow);  
        }
        
        
        leftArrow = new Arrow("leftArrow.png", "LEFT");
        leftArrow.setPosicao(6, 7);
        this.addElemento(leftArrow);
        
        leftArrow = new Arrow("leftArrow.png", "LEFT");
        leftArrow.setPosicao(10, 3);
        this.addElemento(leftArrow);
        
        
        //Desenhando a seta virada para a direita
        Arrow rightArrow; 
        for(int i = 0; i <= 8; i+=2){
            rightArrow = new Arrow("rightArrow.png", "RIGHT");
            rightArrow.setPosicao(i, 3);
            this.addElemento(rightArrow);  
        }
        
        for(int i = 0; i <= 8; i+=4){
            rightArrow = new Arrow("rightArrow.png", "RIGHT");
            rightArrow.setPosicao(i, 7);
            this.addElemento(rightArrow);  
        }
        
        rightArrow = new Arrow("rightArrow.png", "RIGHT");
        rightArrow.setPosicao(10, 7);
        this.addElemento(rightArrow);
        
        
        //Desenhando quadrados laranjas
        OrangeSquare orangeSquare; 
        for(int i = 3; i <= 7; i+=4){
            for(int j = 1; j <= 9; j+=2){
                orangeSquare = new OrangeSquare("orangeSquare.png");
                orangeSquare.setPosicao(i, j);
                this.addElemento(orangeSquare);
            }
        }
        
        for(int i = 1; i <= 9; i+=4){
            for(int j = 3; j <= 7; j+=4){
                orangeSquare = new OrangeSquare("orangeSquare.png");
                orangeSquare.setPosicao(i, j);
                this.addElemento(orangeSquare);
            }
        }
        
        //Desenhando o cacho de uva
        BunchOfGrapes bunchOfGrapes = new BunchOfGrapes("bunchOfGrapes.png");
        bunchOfGrapes.setPosicao(1, 5);
        this.addElemento(bunchOfGrapes); 
        
        //Desenhando o mamão
        Papaya papaya = new Papaya("papaya.png");
        papaya.setPosicao(5, 1);
        this.addElemento(papaya);
        
        //Desenhando o morango
        Strawberry strawberry = new Strawberry("strawberry.png");
        strawberry.setPosicao(5, 9);
        this.addElemento(strawberry);
        
        //Desenhando a cereja
        Cherry cherry = new Cherry("cherry.png");
        cherry.setPosicao(9, 5);
        this.addElemento(cherry);
        
        this.blueRobot = new Monster("blueRobot.png");
        this.blueRobot.setPosicao(9, 9);
        this.addElemento(this.blueRobot);
        
        this.yellowRobot = new Monster("yellowRobot.png");
        this.yellowRobot.setPosicao(1, 1);
        this.addElemento(this.yellowRobot);
        
        this.pinkRobot = new Monster("pinkRobot.png");
        this.pinkRobot.setPosicao(9, 1);
        this.addElemento(this.pinkRobot);
        
        this.greenRobot = new Monster("greenRobot.png");
        this.greenRobot.setPosicao(1, 9);
        this.addElemento(this.greenRobot);
    }
    
    private void cenarioTerceiraFase(){ 
        this.eElementos.clear();
        this.iFaseAtual = 3;
        this.hHero = new Hero("hero.png");  
        this.hHero.setPosicao(5, 5);
        this.addElemento(this.hHero);
        
        //Desenhando diamantes pretos
        GreenSquare greenSquare;
        for(int i = 1; i <= 9; i+=1){
            greenSquare = new GreenSquare("blackDiamondSquare.png");
            greenSquare.setPosicao(1, i);
            this.addElemento(greenSquare);
        }
       
        for(int i = 1; i <= 9; i+=1){
            greenSquare = new GreenSquare("blackDiamondSquare.png");
            greenSquare.setPosicao(9, i);
            this.addElemento(greenSquare);
        }
        
        for(int i = 3; i <= 7; i+=1){
            greenSquare = new GreenSquare("blackDiamondSquare.png");
            greenSquare.setPosicao(3, i);
            this.addElemento(greenSquare);
        }
        
        for(int i = 3; i <= 7; i+=1){
            greenSquare = new GreenSquare("blackDiamondSquare.png");
            greenSquare.setPosicao(7, i);
            this.addElemento(greenSquare);
        }
        
        for(int i = 2; i <= 8; i+=1){
            greenSquare = new GreenSquare("blackDiamondSquare.png");
            greenSquare.setPosicao(i, 1);
            this.addElemento(greenSquare);
        }
        
        for(int i = 2; i <= 8; i+=1){
            greenSquare = new GreenSquare("blackDiamondSquare.png");
            greenSquare.setPosicao(i, 9);
            this.addElemento(greenSquare);
        }
        
        for(int i = 4; i <= 6; i+=1){
            greenSquare = new GreenSquare("blackDiamondSquare.png");
            greenSquare.setPosicao(i, 3);
            this.addElemento(greenSquare);
        }
        
        for(int i = 4; i <= 6; i+=1){
            greenSquare = new GreenSquare("blackDiamondSquare.png");
            greenSquare.setPosicao(i, 7);
            this.addElemento(greenSquare);
        }
        
        //Desenhando o cacho de uva
        BunchOfGrapes bunchOfGrapes = new BunchOfGrapes("bunchOfGrapes.png");
        bunchOfGrapes.setPosicao(5, 0);
        this.addElemento(bunchOfGrapes); 
        
        //Desenhando o mamão
        Papaya papaya = new Papaya("papaya.png");
        papaya.setPosicao(5, 2);
        this.addElemento(papaya);
        
        //Desenhando o morango
        Strawberry strawberry = new Strawberry("strawberry.png");
        strawberry.setPosicao(5, 8);
        this.addElemento(strawberry);
        
        //Desenhando a cereja
        Cherry cherry = new Cherry("cherry.png");
        cherry.setPosicao(5, 10);
        this.addElemento(cherry);
        
        this.blueRobot = new Monster("blueRobot.png");
        this.blueRobot.setPosicao(0, 5);
        this.addElemento(this.blueRobot);
        
        this.yellowRobot = new Monster("yellowRobot.png");
        this.yellowRobot.setPosicao(2, 5);
        this.addElemento(this.yellowRobot);
        
        this.pinkRobot = new Monster("pinkRobot.png");
        this.pinkRobot.setPosicao(8, 5);
        this.addElemento(this.pinkRobot);
        
        this.greenRobot = new Monster("greenRobot.png");
        this.greenRobot.setPosicao(10, 5);
        this.addElemento(this.greenRobot);
    }
    
    private void cenarioQuartaFase() {
        
    }
    
    private void addElemento(Elemento umElemento) {
        eElementos.add(umElemento);
    }
    
    public int getFaseAtual(){
        return this.iFaseAtual;
    }
}

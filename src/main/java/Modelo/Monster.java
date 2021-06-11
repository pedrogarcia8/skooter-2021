package Modelo;

import java.util.ArrayList;


public class Monster extends Elemento{
    public Monster(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bMortal = true;
        this.bTransponivel = false;
    }
    
    public void autoDesenho(){
        
       super.autoDesenho();
        
    } 
    
}

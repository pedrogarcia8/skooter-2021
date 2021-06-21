package Modelo;

import Auxiliar.Desenhador;

public class BunchOfGrapes extends Elemento {
    
    public BunchOfGrapes(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bColetavel = true;
        this.iPontos = 250;
    }
    
    public void autoDesenho(){ 
        super.autoDesenho();
    }
    
    public String toString(){
        return "Uva";
    }
}
package Modelo;

import Auxiliar.Desenhador;

public class Papaya extends Elemento{
    
    public Papaya(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bColetavel = true;
        this.iPontos = 150;
    }
    
    public void autoDesenho(){ 
        super.autoDesenho();
    }
    
    public String toString(){
        return "Papaya";
    }
}

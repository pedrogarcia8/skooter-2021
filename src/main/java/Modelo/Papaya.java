package Modelo;

import Auxiliar.Desenhador;

public class Papaya extends Elemento{
    public Papaya(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bColetavel = true;
        this.iPontos = 200;
    }
    
    public void autoDesenho(){ 
        super.autoDesenho();
        
        //if(this.moveUp())
           // Desenhador.getTelaDoJogo().removeElemento(this);
    }
    
    public String toString(){
        return "Papaya";
    }
}

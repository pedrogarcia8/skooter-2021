package Modelo;

import Auxiliar.Desenhador;

public class Strawberry extends Elemento{
    public Strawberry(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bColetavel = true;
        this.iPontos = 400;
    }
    
    public void autoDesenho(){ 
        super.autoDesenho();
        
        //if(this.moveDown())
           // Desenhador.getTelaDoJogo().removeElemento(this);
    }
    
    public String toString(){
        return "Morango";
    }
}

package Modelo;

import Auxiliar.Desenhador;

public class Strawberry extends Elemento{
    public Strawberry(String sNomeImagePNG) {
        super(sNomeImagePNG);
    }
    
    public void autoDesenho(){ 
        super.autoDesenho();
        
        //if(this.moveDown())
           // Desenhador.getTelaDoJogo().removeElemento(this);
    }
    
}

package Modelo;

import Auxiliar.Desenhador;

public class Papaya extends Elemento{
    public Papaya(String sNomeImagePNG) {
        super(sNomeImagePNG);
    }
    
    public void autoDesenho(){ 
        super.autoDesenho();
        
        //if(this.moveUp())
           // Desenhador.getTelaDoJogo().removeElemento(this);
    }
}

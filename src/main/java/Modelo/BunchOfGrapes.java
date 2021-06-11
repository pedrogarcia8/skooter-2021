package Modelo;

import Auxiliar.Desenhador;

public class BunchOfGrapes extends Elemento {
    public BunchOfGrapes(String sNomeImagePNG) {
        super(sNomeImagePNG);
    }
    
    public void autoDesenho(){ 
        super.autoDesenho();
        
       // if(this.moveUp())
         //   Desenhador.getTelaDoJogo().removeElemento(this);
    }
}

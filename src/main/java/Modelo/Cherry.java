package Modelo;

import Auxiliar.Desenhador;

public class Cherry extends Elemento{
    public Cherry(String sNomeImagePNG) {
        super(sNomeImagePNG);
    }
    
    public void autoDesenho(){ 
        super.autoDesenho();
        
        //if(this.moveDown())
         //   Desenhador.getTelaDoJogo().removeElemento(this);
    }
    
}
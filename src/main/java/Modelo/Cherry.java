package Modelo;

import Auxiliar.Desenhador;

public class Cherry extends Elemento{
    public Cherry(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bColetavel = true;
        this.iPontos = 50;
    }
    
    public void autoDesenho(){ 
        super.autoDesenho();
        
        //if(this.moveDown())
         //   Desenhador.getTelaDoJogo().removeElemento(this);
    }
    
    public String toString(){
        return "Cereja";
    }
}
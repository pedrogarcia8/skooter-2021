package Modelo;

public class Arrow extends Elemento{
    
    public Arrow(String sNomeImagePNG, String type) {
        super(sNomeImagePNG);
        this.bTransponivel = false;
        this.arrowType = type;
        this.arrow = true;
    }
    
    public void autoDesenho(){ 
        super.autoDesenho();  
    }
    
}

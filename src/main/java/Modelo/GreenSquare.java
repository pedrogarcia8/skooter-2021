package Modelo;

public class GreenSquare extends Elemento{
    
    public GreenSquare(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bTransponivel = false;
        this.destroy = true;
        this.movable = true;
    }
    
    public void autoDesenho(){ 
        super.autoDesenho();
    }
}

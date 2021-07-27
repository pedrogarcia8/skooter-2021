package Modelo;

public class BlackDiamondSquare extends Elemento{
    
    public BlackDiamondSquare(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bTransponivel = false;
        this.destroy = true;
        this.movable = true;
    }
    
    public void autoDesenho(){ 
        super.autoDesenho();
    }
}

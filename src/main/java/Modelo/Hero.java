package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Hero extends Elemento implements Serializable{
    //UP|RIGHT|DOWN|LEFT
    private String sDirection;
    
    public Hero(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.sDirection = "UP";
    }

    public void voltaAUltimaPosicao(){
        this.pPosicao.volta();
    }

    public String getDirection() {
        return this.sDirection;
    }
    
    //Determina pra qual direção o herói está "olhando"
    public void setDirection(String sDirection){
        this.sDirection = sDirection;
    }
}

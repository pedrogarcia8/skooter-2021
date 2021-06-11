package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Robo extends Elemento implements Serializable{
    private boolean bRight;

    public Robo(String sNomeImagePNG) {
        super(sNomeImagePNG);
        bRight = true;
        this.bTransponivel = false;
        this.bMortal = true;
    }
    
    public void autoDesenho(){
        /*if(bRight){
            this.setPosicao(pPosicao.getLinha()+1, pPosicao.getColuna());
        }else{
            this.setPosicao(pPosicao.getLinha()-1, pPosicao.getColuna());
        }
        
        /*if(bRight){
            this.setPosicao(pPosicao.getLinha(), pPosicao.getColuna()-1);
        }else{
            this.setPosicao(pPosicao.getLinha(), pPosicao.getColuna()+1);
        }
        
        if(bRight){
            this.setPosicao(pPosicao.getLinha()+1, pPosicao.getColuna());
        }else{
            this.setPosicao(pPosicao.getLinha(), pPosicao.getColuna()-1);
        }*/
        
        super.autoDesenho();
        bRight = !bRight;
    }
}

package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import Controler.Tela;
import Auxiliar.Posicao;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;

public abstract class Elemento implements Serializable {
    protected ImageIcon iImage;
    protected Posicao pPosicao;
    protected boolean bTransponivel; /*Pode passar por cima*/
    protected boolean bMortal; /*Se encostar, morre*/
    protected boolean bColetavel; //Se é coletável
    protected boolean destroy; //Se apertar espaço destroi
    protected boolean arrow; // Se é uma seta
    protected int iPontos; //Quantos pontos este elemento vale
    protected String arrowType; // indicação da seta LEFT, RIGHT, UP ou DOWN
       
    protected Elemento(String sNomeImagePNG) {
        this.pPosicao = new Posicao(1, 1);
        this.bTransponivel = true;
        this.bMortal = false;
        this.bColetavel = false;
        this.arrow = false;
        this.iPontos = 0;
        try {
            iImage = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH + sNomeImagePNG);
            Image img = iImage.getImage();
            BufferedImage bi = new BufferedImage(Consts.CELL_SIDE, Consts.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            g.drawImage(img, 0, 0, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
            iImage = new ImageIcon(bi);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Posicao getPosicao() {
        return pPosicao;
    }

    public boolean isbTransponivel() {
        return bTransponivel;
    }

    public void setbTransponivel(boolean bTransponivel) {
        this.bTransponivel = bTransponivel;
    }

    public boolean setPosicao(int linha, int coluna) {
        return pPosicao.setPosicao(linha, coluna);
    }

    public boolean moveUp() {
        return this.pPosicao.moveUp();
    }

    public boolean moveDown() {
        return this.pPosicao.moveDown();
    }

    public boolean moveRight() {
        return this.pPosicao.moveRight();
    }

    public boolean moveLeft() {
        return this.pPosicao.moveLeft();
    }
    
   public void autoDesenho(){
        Desenhador.desenhar(this.iImage, pPosicao.getColuna(), pPosicao.getLinha());        
    }   
   
   public boolean getDestroy(){
       return this.destroy;
   }
   
   public boolean getMortal(){
       return this.bMortal;
   }
   
   public boolean isArrow(){
       return this.arrow;
   }
   
   public String getArrowType(){
       return this.arrowType;
   }
   
   public boolean ehColetavel(){
       return this.bColetavel;
   }
   
   public int getPontos(){
       return this.iPontos;
   }
}

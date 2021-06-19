package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import java.util.ArrayList;
import java.util.Random;


public class Monster extends Elemento{
    private Random r;
    private int iIntevalo = 0;
    
    public Monster(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bMortal = true;
        this.bTransponivel = false;
        r = new Random();
    }
    
    public void autoDesenho(){
        this.iIntevalo++;
        if(this.iIntevalo == Consts.DIFICULDADE * 3){
            this.iIntevalo = 0;
            int iDirecao = r.nextInt(4);

            switch(iDirecao){
                case 0:
                    this.moveRight();
                    break;
                case 1:
                    this.moveDown();
                    break;
                case 2:
                    this.moveLeft();
                    break;
                case 3:
                    this.moveUp();
                    break;
            }

            if(!Desenhador.getTelaDoJogo().ehPosicaoValida(this)){
                this.voltaAUltimaPosicao();
            }
        }
        
        super.autoDesenho();
        
    }
    
    public void voltaAUltimaPosicao(){
        this.pPosicao.volta();
    }
}

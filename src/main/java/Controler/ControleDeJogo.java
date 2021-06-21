package Controler;

import Auxiliar.Consts;
import Modelo.Elemento;
import Modelo.Hero;
import Auxiliar.Posicao;
import java.util.ArrayList;

public class ControleDeJogo {
    private int iVida;
    private int iPontuacao;
    private boolean bPerdeuVida;
    
    ControleDeJogo(){
        this.iVida = Consts.NUM_VIDAS;
        this.iPontuacao = 0;
        this.bPerdeuVida = false;
    }
    
    public void desenhaTudo(ArrayList<Elemento> e){
        for(int i = 0; i < e.size(); i++){
            e.get(i).autoDesenho();
        }
    }
    public void processaTudo(ArrayList<Elemento> e){
        Hero hHero = (Hero)e.get(0); /*O heroi (protagonista) eh sempre o primeiro do array*/
        Elemento eTemp;
        /*Processa todos os demais em relacao ao heroi*/
        for(int i = 1; i < e.size(); i++){
            eTemp = e.get(i); /*Pega o i-esimo elemento do jogo*/
            /*Verifica se o heroi se sobrepoe ao i-ésimo elemento*/
            if(hHero.getPosicao().estaNaMesmaPosicao(eTemp.getPosicao()))
                /*Nem todos os elementos podem ser transpostos pelo heroi*/
                if(eTemp.isbTransponivel()){
                    if(eTemp.ehColetavel()){
                        this.iPontuacao += eTemp.getPontos();
                        System.out.println("\nItem coletado: " + eTemp.toString());
                        System.out.println("Pontuação atual: " + this.iPontuacao + "\n");
                    }
                    e.remove(eTemp);
                }
        }
    }
    public boolean ehPosicaoValida(ArrayList<Elemento> e, Elemento umElemento){
        Elemento eTemp;
        Hero hHero = (Hero)e.get(0);
        
        //Quando o monstro avança sobre o personagem
        if(umElemento.getMortal()){
            if(umElemento.getPosicao().estaNaMesmaPosicao(hHero.getPosicao())){
                removeVida();
                this.bPerdeuVida = !this.bPerdeuVida;
                System.out.println("\nVocê perdeu uma vida!");
                if(this.iVida > 0)
                    System.out.println("Vidas restantes: " + this.getVida() + "\n");
                
                return false;
            }
        }
        
        /*Validacao da posicao de todos os elementos com relacao a Posicao p*/
        for(int i = 1; i < e.size(); i++){ /*Olha todos os elementos*/
            eTemp = e.get(i); /*Pega o i-esimo elemento do jogo*/
            if(eTemp == umElemento){
                continue;
            }
            if(!eTemp.isbTransponivel())
                if(eTemp.getPosicao().estaNaMesmaPosicao(umElemento.getPosicao())){
                    //Quando o personagem avança sobre o monstro
                    if((umElemento == hHero && eTemp.getMortal())){
                        removeVida();
                        this.bPerdeuVida = !this.bPerdeuVida;
                        System.out.println("\nVocê perdeu uma vida!");
                        if(this.iVida > 0)
                            System.out.println("Vidas restantes: " + this.getVida() + "\n");
                    }
                    return false; /*A posicao p é invalida, pois ha um elemento (i-esimo eTemp) intransponivel lá*/
                }
        }
        return true;
    }

    public void removeVida() {
        this.iVida--;
    }
    
    public int getVida(){
        return this.iVida;
    }
    
    public boolean estaMorto(){
        if(getVida() < 1) return true;
        else return false;
    }

    public void resetaVida() {
        this.iVida = Consts.NUM_VIDAS;
        this.bPerdeuVida = false;
    }
    
    public boolean perdeuVida(){
        if(this.bPerdeuVida == true){
            this.bPerdeuVida = false;
            return true;
        }
        return false;
    }
    
    public void resetaPontuacao(){
        this.iPontuacao = 0;
    }
    
    public int getPontuacao(){
        return this.iPontuacao;
    }
    
    public boolean faseTerminou(ArrayList<Elemento> e){
        boolean terminou = true;
        for(int i = 1; i < e.size(); i++){
            if(e.get(i).ehColetavel() == true){
                terminou = false;
                break;
            }
        }
        return terminou;
    }
}

package Controler;

import Modelo.*;
import Auxiliar.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import java.util.zip.*;

public class Tela extends javax.swing.JFrame implements KeyListener {

    private Hero hHero;
    private Monster blueRobot;
    private Monster yellowRobot;
    private Monster pinkRobot;
    private Monster greenRobot;
    private ArrayList<Elemento> eElementos;
    private ControleDeJogo cControle = new ControleDeJogo();
    private Graphics g2;
    private Fase fase;
    /**
     * Creates new form
     */
    public Tela() {
        Desenhador.setCenario(this); /*Desenhador funciona no modo estatico*/
        initComponents();
 
        this.addKeyListener(this);   /*teclado*/
        
        /*Cria a janela do tamanho do cenario + insets (bordas) da janela*/
        this.setSize(Consts.RES * Consts.CELL_SIDE + getInsets().left + getInsets().right,
                Consts.RES * Consts.CELL_SIDE + getInsets().top + getInsets().bottom);

        /*Este array vai guardar os elementos graficos*/
        this.eElementos = new ArrayList<Elemento>(121);
      
        this.fase = new Fase();
        this.eElementos = this.fase.getElementosFase(1);
        this.hHero = this.fase.getHero(); 
        this.blueRobot = this.fase.getBlueRobot();
        this.yellowRobot = this.fase.getYellowRobot(); 
        this.pinkRobot = this.fase.getPinkRobot();
        this.greenRobot = this.fase.getGreenRobot();
    }

/*--------------------------------------------------*/
    public void addElemento(Elemento umElemento) {
        eElementos.add(umElemento);
    }

    public void removeElemento(Elemento umElemento) {
        eElementos.remove(umElemento);
    }

    public Graphics getGraphicsBuffer(){
        return g2;
    }
    
    /*Este metodo eh executado a cada Consts.FRAME_INTERVAL milissegundos*/    
    public void paint(Graphics gOld) {
        Graphics g = this.getBufferStrategy().getDrawGraphics();
        /*Criamos um contexto gráfico*/
        g2 = g.create(getInsets().left, getInsets().top, getWidth() - getInsets().right, getHeight() - getInsets().top);

        /*Desenha cenário*/
        for (int i = 0; i < Consts.RES; i++) {
            for (int j = 0; j < Consts.RES; j++) {
                try {
                    /*Linha para alterar o background*/
                    Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "background.png");
                    g2.drawImage(newImage,j*Consts.CELL_SIDE, i*Consts.CELL_SIDE, Consts.CELL_SIDE, Consts.CELL_SIDE, null);

                } catch (IOException ex) {
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        /*Aqui podem ser inseridos novos processamentos de controle*/
        if (!this.eElementos.isEmpty()) {
            this.cControle.desenhaTudo(eElementos);
            this.cControle.processaTudo(eElementos);
            if(this.cControle.getPontuacao() >= Consts.PONTOS_PROX_FASE){
                this.cControle.resetaPontuacao();
                this.resetaFase(this.fase.getFaseAtual()+1);     
            }else if(this.cControle.estaMorto()){
                this.cControle.resetaVida();
                this.cControle.resetaPontuacao();
                this.resetaFase(1);
                System.out.println("\nO heroi morreu! Reiniciando o jogo!\n");
            }else if(this.cControle.perdeuVida()){
                this.cControle.resetaPontuacao();
                this.resetaFase(this.fase.getFaseAtual());
            }
        }

        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }

    public void go() {
        TimerTask redesenhar = new TimerTask() {
            public void run() {
                repaint(); /*(executa o metodo paint)*/
            }
        };        
        
        /*Redesenha (executa o metodo paint) tudo a cada Consts.FRAME_INTERVAL milissegundos*/
        Timer timer = new Timer();
        timer.schedule(redesenhar, 0, Consts.FRAME_INTERVAL);
        
    }

    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        /*Movimento do heroi via teclado*/
        if (tecla == KeyEvent.VK_UP || tecla == KeyEvent.VK_W) {
            moveSquare(e, "UP");   
            this.moveArrow("UP");
            hHero.moveUp();
        } else if (tecla == KeyEvent.VK_DOWN || tecla == KeyEvent.VK_S) {
            moveSquare(e, "DOWN");
            this.moveArrow("DOWN");
            hHero.moveDown();
        } else if (tecla == KeyEvent.VK_LEFT || tecla == KeyEvent.VK_A) {
            moveSquare(e, "LEFT");
            this.moveArrow("LEFT");
            hHero.moveLeft();
        } else if (tecla == KeyEvent.VK_RIGHT || tecla == KeyEvent.VK_D) {
            moveSquare(e, "RIGHT");
            this.moveArrow("RIGHT");
            hHero.moveRight();
        } else if (tecla == KeyEvent.VK_R) {
            this.eElementos = this.fase.getElementosFase(1);
            this.hHero = this.fase.getHero();
        } else if(tecla == KeyEvent.VK_E){
            System.out.println("\nFim de jogo!");
            System.out.println("Pontuação: " + this.cControle.getPontuacao());
            System.exit(0);
        } else if(tecla >= KeyEvent.VK_1 && tecla <= Consts.NUM_FASES + 48){
            this.eElementos = this.fase.getElementosFase(tecla - 48);
            this.hHero = this.fase.getHero();
        } else if(tecla == KeyEvent.VK_SPACE){
            for(int i = 0; i < this.eElementos.size(); i++){
                if(this.eElementos.get(i).getDestroy()){
                    if((this.hHero.getPosicao().getLinha()+1 == this.eElementos.get(i).getPosicao().getLinha()) &&
                       (this.hHero.getPosicao().getColuna()  == this.eElementos.get(i).getPosicao().getColuna())){
                        Desenhador.getTelaDoJogo().removeElemento(this.eElementos.get(i));
                        break;
                    }else{
                        if((hHero.getPosicao().getLinha()-1 == this.eElementos.get(i).getPosicao().getLinha()) &&
                           (hHero.getPosicao().getColuna()  == this.eElementos.get(i).getPosicao().getColuna())){
                            Desenhador.getTelaDoJogo().removeElemento(this.eElementos.get(i));
                            break;
                        }else{
                            if((hHero.getPosicao().getLinha()     == this.eElementos.get(i).getPosicao().getLinha()) &&
                               (hHero.getPosicao().getColuna()+1  == this.eElementos.get(i).getPosicao().getColuna())){
                                Desenhador.getTelaDoJogo().removeElemento(this.eElementos.get(i));
                                break;
                            }else{
                                if((hHero.getPosicao().getLinha()     == this.eElementos.get(i).getPosicao().getLinha()) &&
                                   (hHero.getPosicao().getColuna()-1  == this.eElementos.get(i).getPosicao().getColuna())){
                                    Desenhador.getTelaDoJogo().removeElemento(this.eElementos.get(i));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        /*Se o heroi for para uma posicao invalida, sobre um elemento intransponivel, volta para onde estava*/
        if (!cControle.ehPosicaoValida(this.eElementos,hHero)) {
            hHero.voltaAUltimaPosicao();
        }

        this.setTitle("-> Cell: " + (hHero.getPosicao().getColuna()) + ", " + (hHero.getPosicao().getLinha()));
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POO2021");
        setAutoRequestFocus(false);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
    
    public void moveSquare(KeyEvent e, String tecla){
        for(int i = 0; i < this.eElementos.size(); i++){
            if(this.eElementos.get(i).getDestroy()){
                if((this.hHero.getPosicao().getLinha()+1 == this.eElementos.get(i).getPosicao().getLinha())  &&
                   (this.hHero.getPosicao().getColuna()  == this.eElementos.get(i).getPosicao().getColuna()) && 
                   ("DOWN".equals(tecla))){         
                        if(this.verificaSePodeMover("DOWN")){
                            this.eElementos.get(i).moveDown();
                            break;
                        }
                    }else{
                        if((this.hHero.getPosicao().getLinha()-1 == this.eElementos.get(i).getPosicao().getLinha())  &&
                           (hHero.getPosicao().getColuna()       == this.eElementos.get(i).getPosicao().getColuna()) && 
                           ("UP".equals(tecla))){
                                if(this.verificaSePodeMover("UP")){
                                    this.eElementos.get(i).moveUp();
                                    break;
                                }
                        }else{
                            if((hHero.getPosicao().getLinha()     == this.eElementos.get(i).getPosicao().getLinha())  &&
                               (hHero.getPosicao().getColuna()+1  == this.eElementos.get(i).getPosicao().getColuna()) && 
                               ("RIGHT".equals(tecla))){
                                    if(this.verificaSePodeMover("RIGHT")){
                                        this.eElementos.get(i).moveRight();
                                        break;
                                    }
                            }else{
                                if((hHero.getPosicao().getLinha()     == this.eElementos.get(i).getPosicao().getLinha())  &&
                                   (hHero.getPosicao().getColuna()-1  == this.eElementos.get(i).getPosicao().getColuna()) && 
                                   ("LEFT".equals(tecla))){
                                        if(this.verificaSePodeMover("LEFT")){
                                            this.eElementos.get(i).moveLeft();
                                            break;
                                        }
                                }
                            }
                        }
                    }
            }
        }
    }
    
    private void resetaFase(int fase){
        this.eElementos = this.fase.getElementosFase(fase);
        this.hHero = this.fase.getHero();
    }
        
    private void pressR(KeyEvent e) throws AWTException{
        Robot robot = new Robot();
        robot.delay(150);
        robot.keyPress(e.VK_R);
    }
    
    private boolean verificaSePodeMover(String direcao){
        int i = 0;
        for(i = 0; i < this.eElementos.size(); i++){
            if("DOWN".equals(direcao)){
                if(this.eElementos.get(i).getPosicao().getLinha() == this.hHero.getPosicao().getLinha()+2 &&
                   this.eElementos.get(i).getPosicao().getColuna() == this.hHero.getPosicao().getColuna())
                        break;
            }else if("UP".equals(direcao)){
                if(this.eElementos.get(i).getPosicao().getLinha() == this.hHero.getPosicao().getLinha()-2 &&
                   this.eElementos.get(i).getPosicao().getColuna() == this.hHero.getPosicao().getColuna())
                        break;
            }else if("LEFT".equals(direcao)){
                if(this.eElementos.get(i).getPosicao().getLinha() == this.hHero.getPosicao().getLinha() &&
                   this.eElementos.get(i).getPosicao().getColuna() == this.hHero.getPosicao().getColuna()-2)
                        break;
            }else{
                if(this.eElementos.get(i).getPosicao().getLinha() == this.hHero.getPosicao().getLinha() &&
                   this.eElementos.get(i).getPosicao().getColuna() == this.hHero.getPosicao().getColuna()+2)
                        break;
            }           
        }
        
        if(i == this.eElementos.size())
            return true;
        else
            return false;
    }
    
    private void moveArrow(String tecla){
        for(int i = 0; i < this.eElementos.size(); i++){
            if(this.eElementos.get(i).isArrow()){
                if(((this.hHero.getPosicao().getLinha() == this.eElementos.get(i).getPosicao().getLinha())  &&
                   (this.hHero.getPosicao().getColuna()-1  == this.eElementos.get(i).getPosicao().getColuna()) && 
                   ("LEFT".equals(this.eElementos.get(i).getArrowType()))) 
                        && ("LEFT".equals(tecla))){
                        this.hHero.setPosicao(this.hHero.getPosicao().getLinha(), this.hHero.getPosicao().getColuna()-1);
                        break;
                }else if(((this.hHero.getPosicao().getLinha() == this.eElementos.get(i).getPosicao().getLinha())  &&
                         (this.hHero.getPosicao().getColuna()+1  == this.eElementos.get(i).getPosicao().getColuna()) &&
                          ("RIGHT".equals(this.eElementos.get(i).getArrowType()))) 
                          && ("RIGHT".equals(tecla))){
                            this.hHero.setPosicao(this.hHero.getPosicao().getLinha(), this.hHero.getPosicao().getColuna()+1);
                            break;
                }else if(((this.hHero.getPosicao().getLinha()-1 == this.eElementos.get(i).getPosicao().getLinha())  &&
                         (this.hHero.getPosicao().getColuna()  == this.eElementos.get(i).getPosicao().getColuna()) && 
                          ("UP".equals(this.eElementos.get(i).getArrowType())))
                          && ("UP".equals(tecla))){
                            this.hHero.setPosicao(this.hHero.getPosicao().getLinha()-1, this.hHero.getPosicao().getColuna());
                            break;
                }else if(((this.hHero.getPosicao().getLinha()+1 == this.eElementos.get(i).getPosicao().getLinha())  &&
                         (this.hHero.getPosicao().getColuna()  == this.eElementos.get(i).getPosicao().getColuna()) && 
                          ("DOWN".equals(this.eElementos.get(i).getArrowType())))
                          && ("DOWN".equals(tecla))){
                            this.hHero.setPosicao(this.hHero.getPosicao().getLinha()+1, this.hHero.getPosicao().getColuna());
                            break;
                }
            }
        }
    }

    public boolean ehPosicaoValida(Monster monster) {
        return cControle.ehPosicaoValida(this.eElementos, monster);
    }
}

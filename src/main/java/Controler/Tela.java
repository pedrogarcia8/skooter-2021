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
      
        /*Fase primeiraFase = new Fase(1);
        this.eElementos = primeiraFase.getFase();
        this.hHero = primeiraFase.getHero(); 
        this.blueRobot = primeiraFase.getBlueRobot();
        this.yellowRobot = primeiraFase.getYellowRobot(); 
        this.pinkRobot = primeiraFase.getPinkRobot();
        this.greenRobot = primeiraFase.getGreenRobot();*/
        
        Fase segundaFase = new Fase(2);
        this.eElementos = segundaFase.getFase();
        this.hHero = segundaFase.getHero();
        this.hHero = segundaFase.getHero(); 
        this.blueRobot = segundaFase.getBlueRobot();
        this.yellowRobot = segundaFase.getYellowRobot(); 
        this.pinkRobot = segundaFase.getPinkRobot();
        this.greenRobot = segundaFase.getGreenRobot();
        
        /*Fase terceiraFase = new Fase(3);
        this.eElementos = terceiraFase.getFase();
        this.hHero = terceiraFase.getHero();
        this.hHero = terceiraFase.getHero(); 
        this.blueRobot = terceiraFase.getBlueRobot();
        this.yellowRobot = terceiraFase.getYellowRobot(); 
        this.pinkRobot = terceiraFase.getPinkRobot();
        this.greenRobot = terceiraFase.getGreenRobot();*/
        
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
        /*Movimento do heroi via teclado*/
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            moveGreenSquare(e, "UP");   
            try {
                killHero(e);
            } catch (AWTException ex) {
                System.out.println(ex);
            }
            this.moveArrow("UP");
            hHero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            moveGreenSquare(e, "DOWN");
            try {
                killHero(e);
            } catch (AWTException ex) {
                System.out.println(ex);
            }
            this.moveArrow("DOWN");
            hHero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moveGreenSquare(e, "LEFT");
            try {
                killHero(e);
            } catch (AWTException ex) {
                System.out.println(ex);
            }
            this.moveArrow("LEFT");
            hHero.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moveGreenSquare(e, "RIGHT");
            try {
                killHero(e);
            } catch (AWTException ex) {
                System.out.println(ex);
            }
            this.moveArrow("RIGHT");
            hHero.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            Fase primeiraFase = new Fase(1);
            this.eElementos = primeiraFase.getFase();
            hHero = primeiraFase.getHero();
        }else if(e.getKeyCode() == KeyEvent.VK_SPACE){
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
        if (!cControle.ehPosicaoValida(this.eElementos,hHero.getPosicao())) {
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
    
    public void moveGreenSquare(KeyEvent e, String tecla){
        for(int i = 0; i < this.eElementos.size(); i++){
            if(this.eElementos.get(i).getDestroy()){
                if((this.hHero.getPosicao().getLinha()+1 == this.eElementos.get(i).getPosicao().getLinha())  &&
                   (this.hHero.getPosicao().getColuna()  == this.eElementos.get(i).getPosicao().getColuna()) && 
                   (tecla == "DOWN")){         
                        if(this.verificaSePodeMover("DOWN")){
                            this.eElementos.get(i).moveDown();
                            break;
                        }
                    }else{
                        if((this.hHero.getPosicao().getLinha()-1 == this.eElementos.get(i).getPosicao().getLinha())  &&
                           (hHero.getPosicao().getColuna()       == this.eElementos.get(i).getPosicao().getColuna()) && 
                           (tecla == "UP")){
                                if(this.verificaSePodeMover("UP")){
                                    this.eElementos.get(i).moveUp();
                                    break;
                                }
                        }else{
                            if((hHero.getPosicao().getLinha()     == this.eElementos.get(i).getPosicao().getLinha())  &&
                               (hHero.getPosicao().getColuna()+1  == this.eElementos.get(i).getPosicao().getColuna()) && 
                               (tecla == "RIGHT")){
                                    if(this.verificaSePodeMover("RIGHT")){
                                        this.eElementos.get(i).moveRight();
                                        break;
                                    }
                            }else{
                                if((hHero.getPosicao().getLinha()     == this.eElementos.get(i).getPosicao().getLinha())  &&
                                   (hHero.getPosicao().getColuna()-1  == this.eElementos.get(i).getPosicao().getColuna()) && 
                                   (tecla == "LEFT")){
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
    
    public void killHero(KeyEvent e) throws AWTException{
        for(int i = 0; i < this.eElementos.size(); i++){
            if(this.eElementos.get(i).getMortal()){
                    if(((this.hHero.getPosicao().getLinha()+1 == this.eElementos.get(i).getPosicao().getLinha()) &&
                       (this.hHero.getPosicao().getColuna() == this.eElementos.get(i).getPosicao().getColuna())) &&
                       (e.getKeyCode() == KeyEvent.VK_UP)){
                            this.pressR(e);
                            break;
                    }else if(((this.hHero.getPosicao().getLinha()-1 == this.eElementos.get(i).getPosicao().getLinha()) &&
                            (this.hHero.getPosicao().getColuna() == this.eElementos.get(i).getPosicao().getColuna())) && 
                            (e.getKeyCode() == KeyEvent.VK_DOWN)){
                              this.pressR(e);
                              break;
                    }else if(((this.hHero.getPosicao().getLinha() == this.eElementos.get(i).getPosicao().getLinha()) &&
                            (this.hHero.getPosicao().getColuna()+1 == this.eElementos.get(i).getPosicao().getColuna())) && 
                            (e.getKeyCode() == KeyEvent.VK_RIGHT)){
                              this.pressR(e);
                              break;    
                    }else if(((this.hHero.getPosicao().getLinha() == this.eElementos.get(i).getPosicao().getLinha()) &&
                      (this.hHero.getPosicao().getColuna()-1 == this.eElementos.get(i).getPosicao().getColuna())) &&
                      (e.getKeyCode() == KeyEvent.VK_LEFT)){
                                this.pressR(e);
                                break;
                    } 
            }
        }
    }
    
    private void pressR(KeyEvent e) throws AWTException{
        Robot robot = new Robot();
        robot.delay(150);
        robot.keyPress(e.VK_R);
    }
    
    private boolean verificaSePodeMover(String direcao){
        int i = 0;
        for(i = 0; i < this.eElementos.size(); i++){
            if(direcao == "DOWN"){
                if(this.eElementos.get(i).getPosicao().getLinha() == this.hHero.getPosicao().getLinha()+2 &&
                   this.eElementos.get(i).getPosicao().getColuna() == this.hHero.getPosicao().getColuna())
                        break;
            }else if(direcao == "UP"){
                if(this.eElementos.get(i).getPosicao().getLinha() == this.hHero.getPosicao().getLinha()-2 &&
                   this.eElementos.get(i).getPosicao().getColuna() == this.hHero.getPosicao().getColuna())
                        break;
            }else if(direcao == "LEFT"){
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
    
    private void moveMonster(Monster monster){
        boolean right = false;
        boolean left = false;
        boolean up = false;
        boolean down = false;
        int i;
        
        for(i = 0; i < this.eElementos.size(); i++){
            if((monster.getPosicao().getLinha()-1 == this.eElementos.get(i).getPosicao().getLinha())  &&
            (monster.getPosicao().getColuna()  == this.eElementos.get(i).getPosicao().getColuna())){
                System.out.println("DOWN");
                if(monster.getPosicao().getLinha() != 10){
                    down = true;
                    right = false;
                    left = false;
                    up = false;
                    break;
                }     
            }
        }
        for(i = 0; i < this.eElementos.size(); i++){
            if((monster.getPosicao().getLinha()+1 == this.eElementos.get(i).getPosicao().getLinha())  &&
              (monster.getPosicao().getColuna()  == this.eElementos.get(i).getPosicao().getColuna())){
                System.out.println("UP");
                if(monster.getPosicao().getLinha() != 0){
                    up = true;
                    down = false;
                    right = false;
                    left = false;
                    break;
                }
            }             
        }
        for(i = 0; i < this.eElementos.size(); i++){
            if((monster.getPosicao().getLinha()   == this.eElementos.get(i).getPosicao().getLinha())  &&
            (monster.getPosicao().getColuna()-1  == this.eElementos.get(i).getPosicao().getColuna())){
                System.out.println("RIGHT");
                if(monster.getPosicao().getColuna() != 10){
                    right = true;
                    down = false;
                    left = false;
                    up = false;
                    break;
                }
            }     
        }
        for(i = 0; i < this.eElementos.size(); i++){
            if((monster.getPosicao().getLinha()   == this.eElementos.get(i).getPosicao().getLinha())  &&
            (monster.getPosicao().getColuna()+1  == this.eElementos.get(i).getPosicao().getColuna())){
                System.out.println("LEFT");
                if(monster.getPosicao().getColuna() != 0){
                    left = true;
                    down = false;
                    right = false;
                    up = false;
                    break;
                }
            }     
        }
        
        System.out.println("UP: "+up+" DOWN: "+down+" LEFT: "+left+" RIGHT: "+right);
        System.out.println("--------------------------------------");
        
        if(right && !left && !up && !down)
            monster.moveRight();
        else if(!right && left && !up && !down)
            monster.moveLeft();
        else if(!right && !left && up && !down)
            monster.moveUp();
        else if(!right && !left && !up && down)
            monster.moveDown();
    }
    
    private void moveAllMonsters(){
        this.moveMonster(this.pinkRobot);
        this.moveMonster(this.blueRobot);
        this.moveMonster(this.yellowRobot);
        this.moveMonster(this.greenRobot);
    }
    
    private void moveArrow(String tecla){
        for(int i = 0; i < this.eElementos.size(); i++){
            if(this.eElementos.get(i).isArrow()){
                if(((this.hHero.getPosicao().getLinha() == this.eElementos.get(i).getPosicao().getLinha())  &&
                   (this.hHero.getPosicao().getColuna()-1  == this.eElementos.get(i).getPosicao().getColuna()) && 
                   (this.eElementos.get(i).getArrowType() == "LEFT")) 
                        && (tecla == "LEFT")){
                        this.hHero.setPosicao(this.hHero.getPosicao().getLinha(), this.hHero.getPosicao().getColuna()-1);
                        break;
                }else if(((this.hHero.getPosicao().getLinha() == this.eElementos.get(i).getPosicao().getLinha())  &&
                         (this.hHero.getPosicao().getColuna()+1  == this.eElementos.get(i).getPosicao().getColuna()) &&
                          (this.eElementos.get(i).getArrowType() == "RIGHT")) 
                          && (tecla == "RIGHT")){
                            this.hHero.setPosicao(this.hHero.getPosicao().getLinha(), this.hHero.getPosicao().getColuna()+1);
                            break;
                }else if(((this.hHero.getPosicao().getLinha()-1 == this.eElementos.get(i).getPosicao().getLinha())  &&
                         (this.hHero.getPosicao().getColuna()  == this.eElementos.get(i).getPosicao().getColuna()) && 
                          (this.eElementos.get(i).getArrowType() == "UP"))
                          && (tecla == "UP")){
                            this.hHero.setPosicao(this.hHero.getPosicao().getLinha()-1, this.hHero.getPosicao().getColuna());
                            break;
                }else if(((this.hHero.getPosicao().getLinha()+1 == this.eElementos.get(i).getPosicao().getLinha())  &&
                         (this.hHero.getPosicao().getColuna()  == this.eElementos.get(i).getPosicao().getColuna()) && 
                          (this.eElementos.get(i).getArrowType() == "DOWN"))
                          && (tecla == "DOWN")){
                            this.hHero.setPosicao(this.hHero.getPosicao().getLinha()+1, this.hHero.getPosicao().getColuna());
                            break;
                }
            }
        }
    }
}

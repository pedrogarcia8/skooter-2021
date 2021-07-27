package Modelo;

import Controler.Tela;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//Jogo feito por:
//Pedro Garcia
//Thiago Marafeli

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Tela tTela = new Tela();
                    tTela.setVisible(true);
                    tTela.createBufferStrategy(2);
                    tTela.go();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}


package Auxiliar;

import java.io.File;

/**
 *
 * @author Junio
 */ 
public class Consts {
    public static final int CELL_SIDE = 60;
    public static final int RES = 11;
    public static final int FRAME_INTERVAL = 100;   
    public static final int TIMER_DISPARO = 20; /*Em numero de frames (redesenhos)*/
    public static final int DIFICULDADE = 1; /*2 - facil, 1 - medio, 0 - dificil*/
    public static final int NUM_VIDAS = 3;
    public static final int NUM_FASES = 5;
    public static final int PONTOS_PROX_FASE = 700; /*Pontos necessarios para passar de fase*/
    public static final String PATH = File.separator+"imgs"+File.separator;
}

package org.example;

import javafx.event.ActionEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Random;

public class Game {
    GraphicsContext gc;
    GraphicsContext overlay;

    private LittleMac lmac;
    private GlassJoe glassJoe;

    boolean paused = false;
    boolean dead = false;
    boolean win = false;
    Random r = new Random();
    int score = 0;
    int chanceOfBlock = 30;
    static String  s1 = "";


    public Game(GraphicsContext gc, GraphicsContext overlay) {
        this.gc = gc;
        this.overlay = overlay;
        gc.drawImage(new Image(getClass().getResource("/images/boxingring.png").toString()), 0,0, 750,600);
        overlay.setGlobalAlpha(.8);

        lmac = new LittleMac(10, 10, 35, 70, new Image(getClass().getResource("/images/littlemacspritesheet.png").toString()), gc, 375 ,350);
        glassJoe = new GlassJoe(9,10,45, 110, new Image(getClass().getResource("/images/glassjoespritesheet.png").toString()), gc, 375, 225);
        lmac.enemy = glassJoe;
        glassJoe.enemy = lmac;
    }

    public void play(ActionEvent actionEvent){
        paused = false;
        overlay.clearRect(0,0, 650, 500);
    }

    public int getEnemyHealth(){
        return glassJoe.getHealth();
    }

    public void update(){
        if(!paused && !dead && !win){
            gc.clearRect(0,0, 650, 500);
            gc.drawImage(new Image(getClass().getResource("/images/boxingring.png").toString()), 0,0, 750,600);
            glassJoe.draw();
            lmac.draw();

            if(r.nextInt(1000) < 10 ){
                glassJoe.punch();
                chanceOfBlock-=5;
            }


            dead = lmac.getHealth() <= 0;
        }
        else if(dead){
            killed();
        }
        else if(win){
            win();
        }
    }



    public void punch(){
        lmac.punch();

        if(r.nextInt(100) < 50 ) {
            glassJoe.block();
        }
        else{
            score+=5;

            if(chanceOfBlock < 100){
                chanceOfBlock+=5;
            }
        }

//        glassJoe.gotPunched();
//        System.out.println(glassJoe.getHealth());

        //Have a chance of glass joe block


        win = glassJoe.getHealth() <= 0;
        if(win)
            win();
    }


    public void block(){
        lmac.block();
    }

    public int getScore(){
        return score;
    }

    public int getHealth(){
        return lmac.getHealth();
    }

    public void pause(ActionEvent actionEvent) {
        overlay.clearRect(0,0, 650, 500);
        paused = true;
        overlay.setGlobalAlpha(.8);
        overlay.setFill(Color.BLACK);
        overlay.fillRect(0,0,650, 500);
    }

    public void win(){
        overlay.clearRect(0,0, 750, 600);
        overlay.setGlobalAlpha(.9);
        overlay.setFill(Color.BLACK);
        overlay.drawImage(new Image(getClass().getResource("/images/youwin.png").toString()), 300, 150, 162, 150);
//        overlay.fillText("WIN", 10, 10);
        overlay.fillRect(0,0,750, 600);
    }

    public void killed(){
        overlay.clearRect(0,0, 750, 600);
        overlay.setGlobalAlpha(.9);
        overlay.setFill(Color.BLACK);
        overlay.fillRect(0,0,750, 600);
        overlay.drawImage(new Image(getClass().getResource("/images/knockout.png").toString()), 200, 150, 350, 200);
    }
}

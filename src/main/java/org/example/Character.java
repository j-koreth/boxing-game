package org.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

enum Animation {
    PUNCH,
    HURT,
    BLOCK,
    KO
}

abstract class Character {
    int xoffset;
    int yoffset;
    int width;
    int height;
    int health = 10;
    Image spritesheet;
    GraphicsContext gc;
    Character enemy;

    int xPosition;
    int yPosition;

    ArrayList<Animation> animationQueue = new ArrayList<>();
    boolean blocked = false;
    Animation currentAnimation;
    boolean punched = false;

    int count;



    public Character(int xoffset, int yoffset, int width, int height, Image spritesheet, GraphicsContext gc, int xPosition, int yPosition) {
        this.xoffset = xoffset;
        this.yoffset = yoffset;
        this.width = width;
        this.height = height;
        this.spritesheet = spritesheet;
        this.gc = gc;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public void gotPunched(){
        punched = true;
    }

    public void hurt() {

        animationQueue.clear();
        animationQueue.add(0, Animation.HURT);
        health-=1;
        punched = false;

//        System.out.println(health);
        if(health == 0){
            ko();
        }
    }

    public void ko(){
        animationQueue.add(Animation.KO);
    }

    public int getHealth() {
        return health;
    }

    public void punch(){
        if(health != 0)
            animationQueue.add(Animation.PUNCH);
    }

    public void block(){
        if(health != 0)
            animationQueue.add(Animation.BLOCK);
    }

    public void draw(){
        if(punched){
            if(blocked){
                if(currentAnimation == Animation.BLOCK){
//                    blockAnimation();
                    System.out.println("BLOCKED");
                    punched = false;
                }
            }
            else{
//                System.out.println(animationQueue.size());
//                System.out.println(animationQueue);
//                System.out.println(punched);
//                System.out.println(currentAnimation);
//                System.out.println("HURT");
                hurt();
            }
        }
        if(!animationQueue.isEmpty() || blocked){
            Animation toDo;

            if(blocked){
                toDo = currentAnimation;
            }
            else{
                toDo = animationQueue.remove(0);
            }

            switch(toDo){
                case PUNCH:
                    punchAnimation();
                    break;
                case BLOCK:
                    blockAnimation();
                    break;
                case HURT:
                    hurtAnimation();
                    break;
                case KO:
                    koAnimation();
                    break;
            }
        }
        else{
            idleAnimation();
        }
    }

    protected abstract void blockAnimation();

    protected abstract void punchAnimation();

    public void endOfPunch(){
        enemy.gotPunched();
    }

    abstract protected void idleAnimation();

    abstract protected void hurtAnimation();

    abstract protected void koAnimation();
}



package org.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GlassJoe extends Character{
    public GlassJoe(int xoffset, int yoffset, int width, int height, Image spritesheet, GraphicsContext gc, int xPosition, int yPosition) {
        super(xoffset, yoffset, width, height, spritesheet, gc, xPosition, yPosition);
//        health = 4;
    }

    @Override
    protected void blockAnimation() {
        if(!blocked){
            count = 0;
            blocked = true;
            currentAnimation = Animation.BLOCK;
        }

        if(count < 20){
            if(count < .3 * 20){
                gc.drawImage(spritesheet,xoffset, yoffset + height*4, width, height, xPosition,yPosition, width * 2,height * 2);
            }
            else if(count < .7 * 20){
                gc.drawImage(spritesheet,xoffset + width, yoffset + height*4, width, height, xPosition,yPosition, width * 2,height * 2);
            }
            else{
                gc.drawImage(spritesheet,xoffset, yoffset + height*4, width, height, xPosition,yPosition, width * 2,height * 2);
            }
            count++;
        }
        else{
            count = 0;
            currentAnimation = null;
            blocked = false;
        }
    }

    @Override
    protected void punchAnimation() {
        if(!blocked){
            count = 0;
            blocked = true;
            currentAnimation = Animation.PUNCH;
        }

        if(count < 30){
            if(count < .1 * 30){
                gc.drawImage(spritesheet,xoffset, yoffset + height, width, height, xPosition - 2,yPosition -2, width * 2,height * 2);
            }
            else if(count < .2 * 30){
                gc.drawImage(spritesheet,xoffset + width - 10 , yoffset + height, width, height, xPosition - 2,yPosition -2, width * 2,height * 2);
//                gc.drawImage(spritesheet,xoffset + width*2, yoffset + height*3 - 10, width, height, xPosition,yPosition, width * 2,height * 2);
            }
            else if( count < .35 * 30){
                gc.drawImage(spritesheet,xoffset + width*2 - 16, yoffset + height, width, height, xPosition - 5,yPosition -5, width * 2,height * 2);
            }
            else if(count < .6 * 30){
                gc.drawImage(spritesheet,xoffset + width*3 - 15, yoffset + height, width, height, xPosition - 5,yPosition -5, width * 2,height * 2);
            }
            else if(count < .8 * 30){
                gc.drawImage(spritesheet,xoffset + width*4 - 15, yoffset + height, width, height, xPosition - 5,yPosition -5, width * 2,height * 2);
            }
            else{
                gc.drawImage(spritesheet,xoffset + width*5 - 15, yoffset + height, width, height, xPosition - 5,yPosition -5, width * 2,height * 2);
            }
            count++;
        }
        else{
            endOfPunch();
            blocked = false;
            currentAnimation = null;
            count = 0;
        }
    }

    @Override
    protected void idleAnimation() {
        count++;
        if(blocked){
            System.out.println("WTF");
        }
        if( count  % 60 < .2 * 60){
            gc.drawImage(spritesheet,xoffset, yoffset, width, height, xPosition,yPosition, width * 2,height * 2);
        }
        else if(count  % 60 < .4 * 60){
            gc.drawImage(spritesheet,xoffset + width, yoffset, width, height, xPosition,yPosition, width * 2,height * 2);
        }
        else if(count  % 60 < .6 * 60) {
            gc.drawImage(spritesheet,xoffset + width * 2, yoffset, width, height, xPosition,yPosition, width * 2,height * 2);
        }
        else if(count  % 60 < .9 * 60){
            gc.drawImage(spritesheet,xoffset + width * 3, yoffset, width, height, xPosition,yPosition, width * 2,height * 2);
        }
        else{
            gc.drawImage(spritesheet,xoffset + width * 4, yoffset, width, height, xPosition,yPosition, width * 2,height * 2);
        }
    }

    @Override
    protected void hurtAnimation() {
        if(!blocked){
            count = 0;
            blocked = true;
            currentAnimation = Animation.HURT;
        }

        if(count < 10) {
            if (count < .3 * 10) {
                gc.drawImage(spritesheet, xoffset , yoffset + height*5, width, height, xPosition, yPosition, width * 2, height * 2);
            } else if (count < .75 * 10) {
                gc.drawImage(spritesheet, xoffset + width, yoffset + height*5, width, height, xPosition, yPosition, width * 2, height * 2);}
            else {
                gc.drawImage(spritesheet, xoffset, yoffset + height*5, width, height, xPosition, yPosition, width * 2, height * 2);
            }
            count++;
        }
        else{
            currentAnimation = null;
            count = 0;
            blocked = false;
            punched = false;
        }

    }

    @Override
    protected void koAnimation() {
//        System.out.println("CALLED KO Animation");

        if(!blocked){
            count = 0;
            blocked = true;
            currentAnimation = Animation.KO;
        }

            if(count < 7) {
            if (count < .3 * 7) {
                gc.drawImage(spritesheet, xoffset , yoffset + height*6, width, height, xPosition, yPosition, width * 2, height * 2);
            } else if (count < .7 * 7) {
                gc.drawImage(spritesheet, xoffset + width, yoffset + height*6, width + 15, height, xPosition, yPosition, width * 2, height * 2);}
            else {
                gc.drawImage(spritesheet, xoffset + width*2 + 15, yoffset + height*6, width + 20, height, xPosition, yPosition, width * 2, height * 2);
            }
            count++;
        }
        else{
            gc.drawImage(spritesheet, xoffset + width*2 + 15, yoffset + height*6, width + 20, height, xPosition, yPosition, width * 2, height * 2);
//            blocked = false;
//            currentAnimation = null;
//            count = 0;
            punched = false;
        }
    }
}

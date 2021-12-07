package org.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class LittleMac extends Character{


    public LittleMac(int xoffset, int yoffset, int width, int height, Image spritesheet, GraphicsContext gc, int xPosition, int yPosition) {
        super(xoffset, yoffset, width, height, spritesheet, gc, xPosition, yPosition);
    }

    protected void idleAnimation(){
        count++;
        if(blocked){
            System.out.println("WTF");
        }
        if( count  % 60 < .65 * 60){
            gc.drawImage(spritesheet,xoffset + width, yoffset, width, height, xPosition,yPosition, width * 2,height * 2);
        }
        else if(count  % 60 < .75 * 60){
            gc.drawImage(spritesheet,xoffset, yoffset, width, height, xPosition,yPosition, width * 2,height * 2);
        }
        else if(count  % 60 < .9 * 60){
            gc.drawImage(spritesheet,xoffset + width, yoffset, width, height, xPosition,yPosition, width * 2,height * 2);
        }
        else{
            gc.drawImage(spritesheet,xoffset, yoffset, width, height, xPosition,yPosition, width * 2,height * 2);
        }
    }

    @Override
    protected void hurtAnimation() {

    }

    @Override
    protected void koAnimation() {

    }

    protected void blockAnimation(){
        if(!blocked){
            count = 0;
            blocked = true;
            currentAnimation = Animation.BLOCK;
        }

        if(count < 20){
            if(count < .3 * 20){
                gc.drawImage(spritesheet,xoffset, yoffset + height*2, width, height, xPosition,yPosition, width * 2,height * 2);
            }
            else if(count < .7 * 20){
                gc.drawImage(spritesheet,xoffset + width, yoffset + height*2 , width, height, xPosition,yPosition, width * 2,height * 2);
            }
            else{
                gc.drawImage(spritesheet,xoffset, yoffset + height*2, width, height, xPosition,yPosition, width * 2,height * 2);
            }
            count++;
        }
        else{
            count = 0;
            currentAnimation = null;
            blocked = false;
        }
    }

    protected void punchAnimation(){
        if(!blocked){
            count = 0;
            blocked = true;
            currentAnimation = Animation.PUNCH;
        }

        if(count < 10){
            if(count < .2 * 10){
                gc.drawImage(spritesheet,xoffset, yoffset + height*3 + 5 + 10, width, height, xPosition - 2,yPosition -2, width * 2,height * 2);
            }
            else if(count < .3 * 10){
                gc.drawImage(spritesheet,xoffset + width, yoffset + height*3 + 10, width, height, xPosition - 2,yPosition -2, width * 2,height * 2);
//                gc.drawImage(spritesheet,xoffset + width*2, yoffset + height*3 - 10, width, height, xPosition,yPosition, width * 2,height * 2);
            }
            else if( count < .6 * 10){
                gc.drawImage(spritesheet,xoffset + width*2, yoffset + height*3 + 10, width, height, xPosition - 5,yPosition -5, width * 2,height * 2);
            }
            else if(count < .8 * 10){
                gc.drawImage(spritesheet,xoffset + width, yoffset + height*3 + 10, width, height, xPosition - 2,yPosition -2, width * 2,height * 2);
            }
            else{
                gc.drawImage(spritesheet,xoffset, yoffset + height*3 + 5 + 10, width, height, xPosition - 2,yPosition -2, width * 2,height * 2);
            }
            count++;
        }
        else{
            currentAnimation = null;
            count = 0;
            blocked = false;
            endOfPunch();
        }
    }

}




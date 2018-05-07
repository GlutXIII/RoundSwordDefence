package com.roundsworddefence.game.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.roundsworddefence.game.gameObjects.abstractGameObjects.GameActions;
import com.roundsworddefence.game.gameObjects.abstractGameObjects.GameObject;
import com.roundsworddefence.game.utils.Position;
import com.roundsworddefence.game.utils.Size;

import static com.roundsworddefence.game.utils.Images.PLAYER_SIMPLE_IMG_PATH;
import static com.roundsworddefence.game.utils.Images.playerAnimationList;


public class Player extends GameObject implements GameActions {

    public boolean isAttacking;
    private float trackX;
    private float trackY;
    private float velocity;
    private Position prevPosition;
    private Integer power;
    private Integer frame;


    public void changePosition(Integer newXMouse, Integer newYMouse) {
        trackX = newXMouse - this.getPosition().getX();
        trackY = newYMouse - this.getPosition().getY();
        float ratio = (float) Math.sqrt(Math.pow(trackX, 2) + Math.pow(trackY, 2)) / this.velocity;
        float velocityX;
        float velocityY;

        velocityX = trackX / ratio;
        velocityY = trackY / ratio;
        if ((-1 < trackX || trackX < 1) && (-1 < trackY || trackY < 1)) {

            if (Math.abs(this.getPosition().getX() - newXMouse) < 10 && Math.abs(this.getPosition().getY() - newYMouse) < 10) {
                this.getPosition().setX(newXMouse);
                this.getPosition().setY(newYMouse);
            } else {

                this.setPrevPosition(new Position());
                this.getPrevPosition().setX(this.getPosition().getX());
                this.getPrevPosition().setY(this.getPosition().getY());

                this.getPosition().setX(this.getPosition().getX() + velocityX);
                this.getPosition().setY(this.getPosition().getY() + velocityY);
                //COLLISION
                this.getCollisionRect().move(this.getPosition().getX()  ,this.getPosition().getY() );
            }
        }
    }

    public void startAttack(){
        isAttacking = true;
    }
    public void nextFrame(){
        this.img = new Texture(playerAnimationList.get(frame));
        frame = (frame + 1) % 8;
    }

    public Position getPrevPosition() {
        return prevPosition;
    }

    public void setPrevPosition(Position prevPosition) {
        this.prevPosition = prevPosition;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    @Override
    public void init() {
        img = new Texture(PLAYER_SIMPLE_IMG_PATH);
        this.setPrevPosition(new Position());
        this.isAttacking = false;
        this.getPosition().setX(0);
        this.getPosition().setY(0);
        this.setSize(new Size());
        this.getSize().setHeight(25);
        this.getSize().setWidth(25);
        this.frame = 0;
        this.power = 5;
        this.setHealth(100);
        this.velocity = 20;
        this.initAbstract();
    }


}

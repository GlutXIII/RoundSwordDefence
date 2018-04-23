package com.roundsworddefence.game;

import com.badlogic.gdx.graphics.Texture;

public class Player extends GameObject implements GameActions {

    private float trackX;
    private float trackY;
    private float velocity;
    private Position prevPosition;


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
                this.setPrevPosition(this.getPosition());

                this.getPosition().setX(this.getPosition().getX() + velocityX);
                this.getPosition().setY(this.getPosition().getY() + velocityY);
                //COLLISION
                this.getCollisionRect().move(this.getPosition().getX(),this.getPosition().getY());
            }
        }
    }

    public Position getPrevPosition() {
        return prevPosition;
    }

    public void setPrevPosition(Position prevPosition) {
        this.prevPosition = prevPosition;
    }

    @Override
    public void init() {
        img = new Texture("player.jpg");
        this.setPrevPosition(new Position());
        this.getPosition().setX(0);
        this.getPosition().setY(0);
        this.setSize(new Size());
        this.getSize().setHeight(20);
        this.getSize().setWidth(20);
        this.velocity = 20;
    }


}

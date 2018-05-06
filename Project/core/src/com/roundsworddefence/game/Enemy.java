package com.roundsworddefence.game;

import com.badlogic.gdx.graphics.Texture;

import static com.roundsworddefence.game.Images.ENEMY_SIMPLE_IMG_PATH;

public class Enemy extends GameObject implements GameActions {

    private float trackX;
    private float trackY;
    private int power;

    private float velocity;
    private Position prevPosition;

    @Override
    public void init() {
        this.img = new Texture(ENEMY_SIMPLE_IMG_PATH);
        this.setSize(new Size());
        this.getSize().setHeight(25);
        this.getSize().setWidth(25);
        generatePosition();
        this.velocity = 1;
        this.power = 1;
        this.setHealth(100);
        this.initAbstract();
    }
    private void generatePosition(){
        this.setPosition(new Position());
        this.getPosition().setY(0);
        this.getPosition().setX(0);
    }

    public void moveToBase(float baseY, float baseX){
        trackX = baseX - this.getPosition().getX();
        trackY = baseY - this.getPosition().getY();
        float ratio = (float) Math.sqrt(Math.pow(trackX, 2) + Math.pow(trackY, 2)) / this.velocity;
        float velocityX;
        float velocityY;

        velocityX = trackX / ratio;
        velocityY = trackY / ratio;
        if ((-1 < trackX || trackX < 1) && (-1 < trackY || trackY < 1)) {

            if (Math.abs(this.getPosition().getX() - baseX) < 10 && Math.abs(this.getPosition().getY() - baseY) < 10) {
                this.getPosition().setX(baseX);
                this.getPosition().setY(baseY);
            } else {

/*                this.setPrevPosition(new Position());
                this.getPrevPosition().setX(this.getPosition().getX());
                this.getPrevPosition().setY(this.getPosition().getY());*/

                this.getPosition().setX(this.getPosition().getX() + velocityX);
                this.getPosition().setY(this.getPosition().getY() + velocityY);
                //COLLISION
                this.getCollisionRect().move(this.getPosition().getX()  ,this.getPosition().getY() );
            }
        }
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}

package com.roundsworddefence.game.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.roundsworddefence.game.gameObjects.abstractGameObjects.GameActions;
import com.roundsworddefence.game.gameObjects.abstractGameObjects.GameObject;
import com.roundsworddefence.game.utils.Position;
import com.roundsworddefence.game.utils.Size;

import java.util.Random;

import static com.roundsworddefence.game.Game.height;
import static com.roundsworddefence.game.Game.width;
import static com.roundsworddefence.game.utils.Images.ENEMY_SIMPLE_IMG_PATH;

public class Enemy extends GameObject implements GameActions {

    private float trackX;
    private float trackY;
    private int power;

    private float velocity;

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

    /**
     * Method generate random position for enemy
     */
    private void generatePosition(){
        this.setPosition(new Position());
        Random generator = new Random();
        Integer decision = generator.nextInt(4);
        switch (decision){
            case 0:
                this.getPosition().setX(0);
                this.getPosition().setY(generator.nextInt(height));
                break;
            case 1:
                this.getPosition().setY(0);
                this.getPosition().setX(generator.nextInt(width));
                break;
            case 2:
                this.getPosition().setX(width);
                this.getPosition().setY(generator.nextInt(height));
                break;
            case 3:
                this.getPosition().setY(height);
                this.getPosition().setX(generator.nextInt(width));
                break;
        }

    }

    /**
     * Method responsible for enemy movement "they have only one purpose of live"
     * @param baseY
     * @param baseX
     */
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

                this.getPosition().setX(this.getPosition().getX() + velocityX);
                this.getPosition().setY(this.getPosition().getY() + velocityY);
                //COLLISION
                this.getCollisionRect().move(this.getPosition().getX()  ,this.getPosition().getY() );
            }
        }
    }

    /**
     * Getter for power
     * @return int
     */
    public int getPower() {
        return power;
    }

    /**
     * Setter for power
     * @param power
     */
    public void setPower(int power) {
        this.power = power;
    }
}

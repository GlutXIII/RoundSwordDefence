package com.roundsworddefence.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.roundsworddefence.game.Game.height;

public class GameObject {

    private CollisionRect collisionRect;
    private Size size;
    private Position position;
    protected Texture img;

    public void initAbstract(){
        collisionRect = new CollisionRect(this.position.getX(),this.position.getY(),this.size.getWidth(),this.size.getHeight());
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(this.img,this.getPosition().getX() /*-this.getSize().getWidth()/2*/ ,height - this.getPosition().getY() /*-this.getSize().getHeight()/2*/);
        batch.end();
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public void setCollisionRect(CollisionRect collisionRect) {
        this.collisionRect = collisionRect;
    }
}

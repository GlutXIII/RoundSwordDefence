package com.roundsworddefence.game.gameObjects.abstractGameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.roundsworddefence.game.utils.CollisionRect;
import com.roundsworddefence.game.utils.Position;
import com.roundsworddefence.game.utils.Size;

import static com.roundsworddefence.game.Game.height;

public class GameObject {

    private Integer health;
    private CollisionRect collisionRect;
    private Size size;
    private Position position;
    public Texture img;
    private ShapeRenderer shapeRenderer;

    public void initAbstract() {
        shapeRenderer = new ShapeRenderer();
        collisionRect = new CollisionRect(this.position.getX() , height - this.position.getY() , this.size.getWidth(), this.size.getHeight());
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
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        drawHealth();
        batch.begin();
        batch.draw(this.img,this.getPosition().getX()/* -this.getSize().getWidth()/2*/ ,height - this.getPosition().getY() - this.size.getHeight() /*-this.getSize().getHeight()/2*/);
        batch.end();
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public void setCollisionRect(CollisionRect collisionRect) {
        this.collisionRect = collisionRect;
    }

    public void drawHealth(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        if(this.health > 0) {
            shapeRenderer.rect(this.getPosition().getX(), height - this.getPosition().getY() + 10, this.getSize().getWidth() * ((float) this.health / (float) 100), 5);
        }
        shapeRenderer.end();
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }
}

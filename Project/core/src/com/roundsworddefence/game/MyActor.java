package com.roundsworddefence.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MyActor extends Actor {

    private ShapeRenderer shapeRenderer;
    static private boolean projectionMatrixSet;

    public MyActor(){
        shapeRenderer = new ShapeRenderer();
        projectionMatrixSet = false;
    }

    public void draw(SpriteBatch batch, float alpha){
        batch.end();
        if(!projectionMatrixSet){
            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        }
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(0, 0, 50, 50);
        shapeRenderer.end();
        batch.begin();

    }
}
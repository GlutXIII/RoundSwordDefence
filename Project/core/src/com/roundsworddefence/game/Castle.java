package com.roundsworddefence.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.roundsworddefence.game.Images.CASTLE_IMG_PATH;

public class Castle extends GameObject implements GameActions {


    private ShapeRenderer shapeRenderer;

    @Override
    public void init() {
        this.shapeRenderer = new ShapeRenderer();

        this.setHealth(100);
        this.setPosition(new Position());
        this.getPosition().setY(400);
        this.getPosition().setX(600);
        this.setSize(new Size());
        this.getSize().setWidth(100);
        this.getSize().setHeight(100);
        this.initAbstract();
        this.img = new Texture(Gdx.files.internal(CASTLE_IMG_PATH));
    }
}

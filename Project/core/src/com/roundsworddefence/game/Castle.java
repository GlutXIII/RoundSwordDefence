package com.roundsworddefence.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Castle extends GameObject implements GameActions {

    private int health;


    @Override
    public void init() {
        this.health = 100;
        this.setPosition(new Position());
        this.getPosition().setY(400);
        this.getPosition().setX(600);
        this.setSize(new Size());
        this.getSize().setWidth(100);
        this.getSize().setHeight(100);

        this.img = new Texture(Gdx.files.internal("Castle.png"));
    }
}

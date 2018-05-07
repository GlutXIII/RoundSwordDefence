package com.roundsworddefence.game.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.roundsworddefence.game.gameObjects.abstractGameObjects.GameActions;
import com.roundsworddefence.game.gameObjects.abstractGameObjects.GameObject;
import com.roundsworddefence.game.utils.Position;
import com.roundsworddefence.game.utils.Size;

import static com.roundsworddefence.game.utils.Images.CASTLE_IMG_PATH;

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

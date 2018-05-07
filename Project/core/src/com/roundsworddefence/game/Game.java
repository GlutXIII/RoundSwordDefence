package com.roundsworddefence.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.roundsworddefence.game.gameObjects.Castle;
import com.roundsworddefence.game.gameObjects.Enemy;
import com.roundsworddefence.game.gameObjects.Player;
import com.roundsworddefence.game.gameObjects.abstractGameObjects.GameObject;
import com.roundsworddefence.game.utils.Position;

import java.util.ArrayList;
import java.util.List;

import static com.roundsworddefence.game.utils.Images.BACKGROUND_IMG_PATH;
import static com.roundsworddefence.game.utils.Images.END_GAME_IMG_PATH;
import static com.roundsworddefence.game.utils.Images.PLAYER_SIMPLE_IMG_PATH;

/**
 * libGDX game loop based on http://gafferongames.com/game-physics/fix-your-timestep/
 */
public class Game extends ApplicationAdapter {
    /**
     * Something like canvas
     */
    private SpriteBatch batch;
    /**
     * Result of the game
     */
    private double seconds;
    /**
     * Main game hero
     */
    private Player player;
    /**
     * Castle to defend
     */
    private Castle castle;

    /**
     * Window width
     */
    public static final int width = 1200;
    /**
     * Window height
     */
    public static final int height = 800;

    private long startTime;
    private long gameTime = System.nanoTime();
    /**
     * Reaper
     */
    private List<GameObject> deadEnemyList = new ArrayList<GameObject>();

    /**
     * Stopwatch responsible for creating waves of enemies
     */
    private int enemyStopwatch;
    /**
     * Stopwatch responsible for animating hero attack //TODO remove all stopwatches in future bad practise
     */
    private int playerAnimationStopwatch;

    /**
     * List of all game objects
     */
    private List<GameObject> gameObjectList;

    /**
     * Coordinates pointed by mouse for hero movement
     */
    private int xMouse;
    private int yMouse;

    /**
     * Function responsible for initialization
     */
    @Override
    public void create() {
        batch = new SpriteBatch();

        init();
    }

    
    /**
     * Main game loop
     */
    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (castle.getHealth() < 0) {
            gameOver();
        } else {
            mainGame();
        }
    }

    /**
     * Main game function in which all game objects are updated and rendered
     */
    private void mainGame() {

        drawBackground();
        prepareInputProcessor();

        attackWListener();
        playerMoveAndCollision();
        iterateOverEnemies(); //TODO should be in game object loop

        gameObjectList.removeAll(deadEnemyList);


        renderGameObjects(gameObjectList);
        renderTime(width - 100, height - 50);
        if (enemyStopwatch % 200 == 0) {
            addEnemy();
        }
        updateStopwatches();
    }

    /**
     * Update stopwatches
     */
    private void updateStopwatches() {
        enemyStopwatch++;
        playerAnimationStopwatch++;
    }

    /**
     * Function handle game objects which are instance of enemy
     */
    private void iterateOverEnemies() {
        for (GameObject gameObject : gameObjectList
                ) {
            if (gameObject instanceof Enemy) {
                Enemy enemy = (Enemy) gameObject;
                reaper(enemy);
                handleEnemyCollision(enemy);
            }
        }
    }

    /**
     * Method handle collision for enemy
     * @param enemy
     */
    private void handleEnemyCollision(Enemy enemy) {
        if (!enemy.getCollisionRect().collidesWith(player.getCollisionRect())) {
            if (!enemy.getCollisionRect().collidesWith(castle.getCollisionRect())) {
                enemy.moveToBase(castle.getPosition().getY(), castle.getPosition().getX());
            }
        } else {
            if (player.isAttacking) {
                enemy.setHealth(enemy.getHealth() - player.getPower());
            }
        }
        if (enemy.getCollisionRect().collidesWith(castle.getCollisionRect())) {
            castle.setHealth(castle.getHealth() - enemy.getPower());
        }
    }

    /**
     * Take care of dead enemies
     * @param enemy
     */
    private void reaper(Enemy enemy) {
        if (enemy.getHealth() < 0) {
            deadEnemyList.add(enemy);
        }
    }

    /**
     * Listen for w key for one of attacks for player
     */
    private void attackWListener() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.startAttack();
            if (playerAnimationStopwatch % 5 == 0) {

                player.nextFrame();
            }

        } else {
            player.isAttacking = false;
            player.img = new Texture(PLAYER_SIMPLE_IMG_PATH);
        }
    }

    /**
     * Method draw background
     */
    private void drawBackground() {
        batch.begin();
        batch.draw(new Texture(BACKGROUND_IMG_PATH), 0, 0);
        batch.end();
    }

    /**
     * Method handle player move and collision
     */
    private void playerMoveAndCollision() {
        if (player.getPosition().getX() != xMouse && player.getPosition().getY() != yMouse) {

            if (player.getCollisionRect().collidesWith(castle.getCollisionRect())) {
                player.setPosition(player.getPrevPosition());
                player.getCollisionRect().move(player.getPosition().getX(), player.getPosition().getY());
                xMouse = (int) player.getPrevPosition().getX();
                yMouse = (int) player.getPrevPosition().getY();
            } else {
                player.changePosition(xMouse, yMouse);
            }
        }
    }

    /**
     * Method loops through all game objects and renders them
     * @param gameObjectList
     */
    private void renderGameObjects(List<GameObject> gameObjectList) {
        for (GameObject gameObject : gameObjectList
                ) {
            gameObject.render(batch);
        }
    }

    /**
     * Method initialize all game stuff
     */
    private void init() {


        startTime = System.nanoTime();
        gameObjectList = new ArrayList<GameObject>();


        Enemy enemy = new Enemy();
        enemy.init();

        gameObjectList.add(enemy);

        player = new Player();
        player.setPosition(new Position());
        player.getPosition().setX(0);
        player.getPosition().setY(0);
        player.init();

        castle = new Castle();
        castle.init();

        gameObjectList.add(player);
        gameObjectList.add(castle);

    }

    /**
     * Method handle end of game
     */
    private void gameOver() {

        batch.begin();
        BitmapFont font = new BitmapFont();
        font.setColor(Color.RED);
        batch.draw(new Texture(END_GAME_IMG_PATH), 0, 0);
        batch.end();
        renderTime(500, height - 400);

    }

    /**
     * Method renders time in position x,y
     * @param x
     * @param y
     */
    private void renderTime(float x, float y) {
        if (castle.getHealth() > 0) {
            gameTime = (System.nanoTime() - startTime);
        }

        seconds = (double) gameTime / 1000000000.0;
        batch.begin();
        batch.setProjectionMatrix(batch.getProjectionMatrix());
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.draw(batch, "Score:", x, y + 20);
        font.draw(batch, String.valueOf(seconds), x, y);
        batch.end();
    }

    /**
     * Method add new enemy to game
     */
    private void addEnemy() {

        Enemy enemy = new Enemy();
        enemy.init();
        gameObjectList.add(enemy);


    }

    /**
     * Method prepare mouse listener
     */
    private void prepareInputProcessor() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {


                return true; // return true to indicate the event was handled
            }

            @Override
            public boolean touchUp(int x, int y, int pointer, int button) {
                xMouse = x;
                yMouse = y;
                // your touch up code here
                return true; // return true to indicate the event was handled
            }
        });
    }

}
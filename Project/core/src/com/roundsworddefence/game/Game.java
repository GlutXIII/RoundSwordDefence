package com.roundsworddefence.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

import static com.roundsworddefence.game.Images.BACKGROUND_IMG_PATH;
import static com.roundsworddefence.game.Images.END_GAME_IMG_PATH;

/**
 * libGDX game loop based on http://gafferongames.com/game-physics/fix-your-timestep/
 *
 */
public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	private Player player;
	private Castle castle;
	private List<Enemy> enemyList = new ArrayList<Enemy>();

	public static final int width = 1200;
	public static final int height = 800;

	private long nanosPerLogicTick = 250000000; // ~ dt
	private double gameTimeInSeconds;
	private long startTime;
	private long gameTime = System.nanoTime();

	private long accumulator;
	private List<GameObject> gameObjectList;

	private int xMouse;
	private int yMouse;


	@Override
	public void create () {
		batch = new SpriteBatch();

		init();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(castle.getHealth()< 0){
			gameOver();
		}else {

			batch.begin();
			batch.draw(new Texture(BACKGROUND_IMG_PATH), 0, 0);
			batch.end();

			prepareInputProcessor();
/*			long newTime = System.nanoTime();
			long frameTime = newTime - currentTime;

			if (frameTime > 250000000) {
				frameTime = 250000000;
			}
			currentTime = newTime;
			accumulator += frameTime;

			while (accumulator >= nanosPerLogicTick) {
				accumulator -= nanosPerLogicTick;
			}*/

			playerMoveAndCollision();
			for (Enemy enemy : enemyList
					) {
				if (!enemy.getCollisionRect().collidesWith(player.getCollisionRect())) {
					if (!enemy.getCollisionRect().collidesWith(castle.getCollisionRect())) {
						enemy.moveToBase(castle.getPosition().getY(), castle.getPosition().getX());
					}
				}
				if (enemy.getCollisionRect().collidesWith(castle.getCollisionRect())) {
					castle.setHealth(castle.getHealth() - enemy.getPower());
				}
			}


			renderGameObjects(gameObjectList);
			renderTime(width - 100 ,height - 50);
		}
	}

	private void playerMoveAndCollision() {
		if (player.getPosition().getX() != xMouse && player.getPosition().getY() != yMouse) {

			if (player.getCollisionRect().collidesWith(castle.getCollisionRect())) {
				player.setPosition(player.getPrevPosition());
				player.getCollisionRect().move(player.getPosition().getX()  ,player.getPosition().getY());
				xMouse = (int) player.getPrevPosition().getX();
				yMouse = (int) player.getPrevPosition().getY();
			} else{
				player.changePosition(xMouse, yMouse);
			}
		}
	}

	private void renderGameObjects(List<GameObject> gameObjectList){
		for (GameObject gameObject: gameObjectList
			 ) {
			gameObject.render(batch);
		}
	}
	private void init(){


		startTime = System.nanoTime();
		gameObjectList = new ArrayList<GameObject>();


		Enemy enemy = new Enemy();
		enemy.init();
		enemyList.add(enemy);
		gameObjectList.addAll(enemyList);

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

	private void gameOver(){

			batch.begin();
			BitmapFont font =new BitmapFont();
			font.setColor(Color.RED);
			batch.draw(new Texture(END_GAME_IMG_PATH),0,0);
			batch.end();
		    renderTime(500, height - 400);

	}

	private void renderTime(float x, float y){
		if(castle.getHealth()>0) {
			gameTime = (System.nanoTime() - startTime);
		}

		double seconds = (double)gameTime / 1000000000.0;
		batch.begin();
		batch.setProjectionMatrix(batch.getProjectionMatrix());
		BitmapFont font =new BitmapFont();
		font.setColor(Color.BLACK);
		font.draw(batch,"Score:", x, y +20 );
		font.draw(batch, String.valueOf(seconds), x, y);
		batch.end();
	}

	/**
	 * Methods prepare mouse listener
	 */
	private void prepareInputProcessor() {
		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean touchDown (int x, int y, int pointer, int button) {


				return true; // return true to indicate the event was handled
			}

			@Override
			public boolean touchUp (int x, int y, int pointer, int button) {
				xMouse = x;
				yMouse = y;
				// your touch up code here
				return true; // return true to indicate the event was handled
			}
		});
	}

}
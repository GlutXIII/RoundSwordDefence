package com.roundsworddefence.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

/**
 * libGDX game loop based on http://gafferongames.com/game-physics/fix-your-timestep/
 *
 */
public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	private Player player;
	private Castle castle;

	public static final int width = 1200;
	public static final int height = 800;

	private long nanosPerLogicTick = 250000000; // ~ dt
	private long currentTime = System.nanoTime();

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
		prepareInputProcessor();

		long newTime = System.nanoTime();
		long frameTime = newTime - currentTime;

		if (frameTime > 250000000) {
			frameTime = 250000000;
		}
		currentTime = newTime;
		accumulator += frameTime;

		while (accumulator >= nanosPerLogicTick) {
			accumulator -= nanosPerLogicTick;
		}
		if(player.getCollisionRect().collidesWith(castle.getCollisionRect())){
			player.setPosition(player.getPrevPosition());
		}
		else {
			if (player.getPosition().getX() != xMouse && player.getPosition().getY() != yMouse) {
				player.changePosition(xMouse, yMouse);
			}
		}

		renderGameObjects(gameObjectList);

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
	private void renderGameObjects(List<GameObject> gameObjectList){
		for (GameObject gameObject: gameObjectList
			 ) {
			gameObject.render(batch);
		}
	}
	private void init(){
		gameObjectList = new ArrayList<GameObject>();

		player = new Player();
		player.setPosition(new Position());
		player.getPosition().setX(0);
		player.getPosition().setY(0);
		player.init();
		player.initAbstract();

		castle = new Castle();
		castle.init();
		castle.initAbstract();

		gameObjectList.add(player);
		gameObjectList.add(castle);

	}


}
package com.roundsworddefence.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.roundsworddefence.game.Game;

import static com.roundsworddefence.game.Game.height;
import static com.roundsworddefence.game.Game.width;

public class DesktopLauncher {


	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = width;
		config.height = height;
		config.foregroundFPS = 60;
		new LwjglApplication(new Game(), config);


	}
}

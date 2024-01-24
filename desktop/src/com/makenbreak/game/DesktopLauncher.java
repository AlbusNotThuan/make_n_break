package com.makenbreak.game;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.makenbreak.game.MakeNBreakGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Make 'n' Break");
		config.setWindowedMode(800, 600);
		config.setResizable(false);
		new Lwjgl3Application(new MakeNBreakGame(), config);
	}
}



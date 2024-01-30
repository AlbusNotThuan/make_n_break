package com.makeandbreak.game;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.makeandbreak.game.MakeAndBreak;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		Graphics.DisplayMode dm = Lwjgl3ApplicationConfiguration.getDisplayMode();
		config.setWindowedMode(dm.width/7*3, dm.height/5*3);
		config.setForegroundFPS(60);
		config.setTitle("MakeAndBreak");
		config.setResizable(false);
		new Lwjgl3Application(new MakeAndBreak(), config);
	}
}

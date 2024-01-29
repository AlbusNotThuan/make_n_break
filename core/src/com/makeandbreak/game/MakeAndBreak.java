package com.makeandbreak.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.io.FileNotFoundException;

import Screens.ClassicScreen;
import Screens.MainMenuScreen;

public class MakeAndBreak extends Game {
	public SpriteBatch batch;
	public ShapeRenderer shape;
	public int WIDTH = Gdx.graphics.getWidth();
	public int HEIGHT = Gdx.graphics.getHeight();
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainMenuScreen(this));
		shape = new ShapeRenderer();
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

	}
}

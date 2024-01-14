package com.makeandbreak.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import Screens.PlayManager;
import Screens.PlayScreen;

public class MakeAndBreak extends Game {
	public SpriteBatch batch;
	public ShapeRenderer shape;
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayManager(this));
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

package com.makenbreak.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import Classes.Box;
import Classes.ColorSelector;
import Classes.CustomButton;
import Classes.Grid;

public class ClassicScreen extends ApplicationAdapter implements Screen, InputProcessor {
    private OrthographicCamera gamecam;
    private SpriteBatch batch;
    private BitmapFont font;
    private Viewport gameport;
    Box box = new Box();
    private Stage stage;


    public Game game;
    public ClassicScreen(Game game){
        this.game = game;
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(1520 , 1200, gamecam);
//        Gdx.input.setInputProcessor(this);

        gamecam.setToOrtho(false, 1520,1200);
        gamecam.translate(-760,-600);

        stage = new Stage();
        Grid grid = new Grid();
        grid.setPosition(480,0);
        stage.addActor(grid);
        Button.ButtonStyle style3 = new Button.ButtonStyle();

        //Color Selector
        ColorSelector redSelector = new ColorSelector(Color.RED, "red.png");
        redSelector.setPosition(-400, 0);
        redSelector.setSize(80,250);
        stage.addActor(redSelector);

        ColorSelector blueSelector = new ColorSelector(Color.BLUE, "blue.png");
        blueSelector.setPosition(-300,0);
        blueSelector.setSize(80,250);
        stage.addActor(blueSelector);

        ColorSelector greenSelector = new ColorSelector(Color.GREEN, "green.png");
        greenSelector.setPosition(-500,0);
        greenSelector.setSize(80,250);
        stage.addActor(greenSelector);

        InputProcessor inputProcessor = new InputAdapter(){
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.SPACE){
                    for (int i = 0; i < grid.getRows(); i++) {
                        for (int j = 0; j < grid.getColumns(); j++) {
                            CustomButton button = grid.getButton(i, j);
                            button.setColor(Color.WHITE);
                        }
                    }
                    return true;
                }
                return false;
            }
        };
        InputMultiplexer Multiplexer = new InputMultiplexer();
        Multiplexer.addProcessor(stage);
        Multiplexer.addProcessor(inputProcessor);
        Multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(Multiplexer);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Create a return button
        TextButton returnButton = createButton("Return to Menu", -750, 450);

        // Add a click listener to the return button
        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        // Add the return button to the stage
        stage.addActor(returnButton);
    }


    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
        gamecam.update();
    }

    @Override
    public void render(float delta) {

        stage.setViewport(gameport);
        stage.draw();

        Gdx.gl.glClearColor(0.7f, 0.7f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    private TextButton createButton(String text, float x, float y) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = font;

        TextButton button = new TextButton(text, style);
        button.setPosition(x, y);
        style.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("blue.jpg"))));
        button.setWidth(200);
        button.setHeight(40);

        style.fontColor = null;

        TextButton buttonn = new TextButton(text, style);
        buttonn.setPosition(x, y);
        buttonn.setWidth(1000);
        buttonn.setHeight(1000);

        return button;
    }
}
package Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.makeandbreak.game.MakeAndBreak;

import java.io.FileNotFoundException;

import Classes.Box;
import Classes.ColorSelector;
import Classes.CustomButton;
import Classes.Grid;

public class PlayManager extends ApplicationAdapter implements Screen, InputProcessor {
    final MakeAndBreak game;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    Box box = new Box();
    private Stage stage;

    public PlayManager(MakeAndBreak game) throws FileNotFoundException {
        this.game = game;
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(1520 , 1200, gamecam);
//        Gdx.input.setInputProcessor(this);

        gamecam.setToOrtho(false, 1520,1200);
        gamecam.translate(-760,-600);
//        gamecam.setToOrtho(false, 1520, 1200);
//        gamecam.position.set(gamecam.viewportWidth / 2, gamecam.viewportHeight / 2, 0);
//        gamecam.update();
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
                grid.checkMatrix();
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

//        grid.Text2Array("example.txt");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Grid
        stage.setViewport(gameport);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
        gamecam.update();
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
}

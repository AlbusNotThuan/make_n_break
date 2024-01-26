package Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
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
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE,Color.BLACK, Color.GRAY, Color.PURPLE, Color.WHITE};

        for (int i = 0; i < colors.length; i++) {
            String colorName = getColorName(colors[i]);
            ColorSelector colorSelector = new ColorSelector(colors[i], colorName + ".png");
            colorSelector.setPosition(-20 - (i * 100), 0);
            colorSelector.setSize(80, 250);
            stage.addActor(colorSelector);
        }




        InputProcessor inputProcessor = new InputAdapter(){
            @Override
            public boolean keyDown(int keycode) {
                try {
                    if(grid.checkMatrix()){
                        System.out.println("Correct");
                    } else {
                        System.out.println("Dumbass");
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                if (keycode == Input.Keys.SPACE){
                    for (int i = 0; i < grid.getRows(); i++) {
                        for (int j = 0; j < grid.getColumns(); j++) {
                            CustomButton button = grid.getButton(i, j);
                            button.setColor(Color.YELLOW);
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

        grid.Text2Array();
    }

    @Override
    public void show() {

    }

    private String getColorName(Color color) {
        if (Color.RED.equals(color)) {
            return "red";
        } else if (Color.BLUE.equals(color)) {
            return "blue";
        } else if (Color.GREEN.equals(color)) {
            return "green";
        } else if (Color.ORANGE.equals(color)) {
            return "orange";
        } else if (Color.BLACK.equals(color)) {
            return "black";
        } else if (Color.GRAY.equals(color)) {
            return "gray";
        } else if (Color.PURPLE.equals(color)) {
            return "purple";
        } else if (Color.WHITE.equals(color)) {
            return "white";
        } else {
            return "unknown";
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(4/255f, 105/255f, 30/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


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

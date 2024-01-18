package Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.makeandbreak.game.MakeAndBreak;

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

    public PlayManager(MakeAndBreak game) {
        this.game = game;
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(1520 , 1200, gamecam);
        Gdx.input.setInputProcessor(this);

        gamecam.setToOrtho(false, 1520,1200);
        gamecam.translate(-760,-600);
//        gamecam.setToOrtho(false, 1520, 1200);
//        gamecam.position.set(gamecam.viewportWidth / 2, gamecam.viewportHeight / 2, 0);
//        gamecam.update();
        stage = new Stage();
        Grid table = new Grid();

        stage.addActor(table);
//        Button.ButtonStyle style3 = new Button.ButtonStyle();
//        ColorSelector red = new ColorSelector(Color.RED);
//        red.setPosition(400, 0);
//        stage.addActor(red);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Testing Rectangle
//        game.shape.setProjectionMatrix(gamecam.combined);
//        game.shape.begin(ShapeRenderer.ShapeType.Line);
//        game.shape.setColor(1,0,0,1);
//
//        if (Gdx.input.isKeyPressed(Input.Keys.UP)) box.y += 20;
//        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) box.y -= 20;
//        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) box.x -= 20;
//        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) box.x += 20;
//
//        game.shape.rect(box.x, box.y, box.width, box.height);
//        game.shape.end();


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

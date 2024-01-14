package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.makeandbreak.game.MakeAndBreak;

import Classes.Box;

public class PlayScreen implements Screen {
    private MakeAndBreak game;
    Texture texture;
    public OrthographicCamera gamecam;
    private Viewport gameport;
    Box box = new Box();

    //
    float x = 0;
    float y = 0;

    public PlayScreen(MakeAndBreak game){
        this.game = game;
        texture = new Texture("workinprogress.png");
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(1520 , 1200, gamecam);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(gamecam.combined);
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) y += 20;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) y -= 20;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) x -= 20;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x += 20;

        game.batch.begin();
        game.batch.draw(texture , x ,y);
        game.batch.end();

        game.shape.setProjectionMatrix(gamecam.combined);
        game.shape.begin(ShapeRenderer.ShapeType.Line);
        game.shape.setColor(1,0,0,1);
        game.shape.circle(box.x, box.y, 10);
        game.shape.end();

    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
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
}

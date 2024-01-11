package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.makenbreak.game.MakeandBreak;

public class PlayScreen implements Screen {
    private MakeandBreak game;
    Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    public PlayScreen(MakeandBreak game){
        this.game = game;
        texture = new Texture("testing.gif");
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(1600, 1600, gamecam);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(gamecam.combined);

        game.batch.begin();
        game.batch.draw(texture, 0, 00);
        game.batch.end();

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

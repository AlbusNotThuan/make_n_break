package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.makeandbreak.game.MakeAndBreak;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

public class fPlayScreen implements Screen {
    private MakeAndBreak game;
    Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    public PlayScreen(MakeAndBreak game){
        this.game = game;
        texture = new Texture("workinprogress.png");
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(1600 , 800, gamecam);
    }
    @Override
    public void create() {

        Stage stage;
        Table table;

            public WhiteBorderedTable(Stage stage) {
                this.stage = stage;
                this.table = new Table();
                table.setFillParent(true);
                table.setBackground(new Skin().getDrawable("white"));

                for (int x = 0; x < 9; x++) {
                    for (int y = 0; y < 6; y++) {
                        final int row = y;
                        final int col = x;

                        Label label = new Label("(" + col + "," + row + ")", new Skin());
                        label.setAlignment(Align.center);
                        table.add(label).width(30).height(30).pad(5);
                    }
                    table.row();
                }

                stage.addActor(table);
            }
        }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        game.batch.draw(texture , 0 ,0);
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

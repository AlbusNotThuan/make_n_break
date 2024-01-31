package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.makeandbreak.game.MakeAndBreak;

import java.io.FileNotFoundException;


public class BufferScreen implements Screen  {
    private final MakeAndBreak game;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Stage stage;
    public BufferScreen(MakeAndBreak game){
        this.game = game;
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(1520 , 600, gamecam);
        gamecam.setToOrtho(false, 1520,600);
        gamecam.translate(-1520/2,-600/2);
        this.stage = new Stage(gameport, game.batch);
    }
    @Override
    public void show() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ready.atlas"));
        Skin skin = new Skin(Gdx.files.internal("ready.json"),atlas);


        ImageButton.ImageButtonStyle letsbuildStyle = new ImageButton.ImageButtonStyle();
        letsbuildStyle = skin.get("letsbuild", ImageButton.ImageButtonStyle.class);

        System.out.println(game.WIDTH);
        ImageButton letsbuildButton = new ImageButton(letsbuildStyle);
        letsbuildButton.setPosition(0,0);
//        letsbuildButton.setPosition(game.WIDTH / 2 - letsbuildButton.getWidth() / 2, game.HEIGHT / 2 - letsbuildButton.getHeight() / 2);
        letsbuildButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Change the screen to ClassicScreen when the button is clicked
                try {
                    game.setScreen(new ClassicScreen(game));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        stage.addActor(letsbuildButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(4/255f, 105/255f, 30/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.setViewport(gameport);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
        stage.dispose();
    }
}

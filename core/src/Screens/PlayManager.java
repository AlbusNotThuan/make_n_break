package Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.makeandbreak.game.MakeAndBreak;

import Classes.Box;
import Classes.CustomButton;

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

//        BitmapFont font = new BitmapFont();
//        Texture texture =  new Texture(Gdx.files.internal("3.png"));
//        TextureRegion textureRegion = new TextureRegion(texture);
//        TextureRegionDrawable drawable = new TextureRegionDrawable(textureRegion);
//
//        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
//        buttonStyle.up = drawable;
//        buttonStyle.checked = drawable;
//        buttonStyle.down = drawable;
//        buttonStyle.font = font;

        Table table = new Table();
//        for (int i = 0; i < 3; i++){
//            for (int j = 0; j < 3; j++){
//                TextButton button = new TextButton("Button " + (i * 3 + j + 1), buttonStyle);
//                table.add(button).width(100).height(100).pad(10);
//            }
//            table.row();
//        }

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                CustomButton button = new CustomButton();
                table.add(button).width(100).height(100).pad(10);
                button.addListener(new InputListener(){
                    public void touchUp(InputEvent event, float x, float y){
                        button.isPressed();
                    }
                });
            }
            table.row();
        }

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Testing Rectangle
        game.shape.setProjectionMatrix(gamecam.combined);
        game.shape.begin(ShapeRenderer.ShapeType.Line);
        game.shape.setColor(1,0,0,1);

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) box.y += 20;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) box.y -= 20;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) box.x -= 20;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) box.x += 20;

        game.shape.rect(box.x, box.y, box.width, box.height);
        game.shape.end();


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

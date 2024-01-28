package Screens;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.*;
import com.makeandbreak.game.MakeAndBreak;

import Screens.MainMenuScreen;

public class GameOverScreen extends ScreenAdapter {
    private final MakeAndBreak game;
    private Stage stage;
    private SpriteBatch batch;
    private BitmapFont font;

    public GameOverScreen(MakeAndBreak game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Create title label
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, null);
        Label titleLabel = new Label("Game Over", labelStyle);
        titleLabel.setPosition(Gdx.graphics.getWidth() / 2 - titleLabel.getWidth() / 2 -100, Gdx.graphics.getHeight() / 2 + 100);
        titleLabel.setFontScale(4);
        stage.addActor(titleLabel);
        // Create buttons
        TextButton replayButton = createButton("Replay", 300, Gdx.graphics.getHeight() -350);

        // Add click listeners to the buttons
        replayButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        // Add buttons to the stage
        stage.addActor(replayButton);
    }

    private TextButton createButton(String text, float x, float y) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = font;

        TextButton button = new TextButton(text, style);
        button.setPosition(x, y);
        style.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("blueBackground.jpg"))));
        button.setWidth(200); // Set your desired width
        button.setHeight(40); // Set your desired height

        style.fontColor = null;

        TextButton buttonn = new TextButton(text, style);
        buttonn.setPosition(x, y);
        buttonn.setWidth(200);
        buttonn.setHeight(40);

        return button;
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        // Draw the background
        batch.draw(new Texture(Gdx.files.internal("background rule.jpg")), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        stage.dispose();
    }
}
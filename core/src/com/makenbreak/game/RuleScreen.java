package com.makenbreak.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class RuleScreen extends ScreenAdapter {
    private final Game game;
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;
    private Texture backgroundTexture;
    private Stage stage;

    public RuleScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Load the background texture
        backgroundTexture = new Texture("background rule.jpg");  // Replace with your background image

        // Create the stage for UI elements
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Create a return button
        TextButton returnButton = createButton("Return to Menu", -40, 550);

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
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // Draw the background
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Draw the rules text
        font.draw(batch, "Make 'n' Break Rule:", 10, Gdx.graphics.getHeight() - 100);
        font.draw(batch, "Objective:\n" +
                "The goal of Make 'n' Break is to earn points by successfully recreating various structures within a specified time limit.\n" +
                "\n" +
                "Gameplay:\n" +
                "The game is played in rounds.\n" +
                "At the start of each round, a player flips the top card from the stack to reveal a structure that needs to be replicated.\n" +
                "All players or teams simultaneously attempt to build the structure shown on the card using their building blocks.\n" +
                "The round ends when the timer runs out.\n" +
                "\n" +
                "Scoring:\n" +
                "Players score points based on the accuracy and completeness of their structures.\n" +
                "If a player or team successfully replicates the structure, they score the full points indicated on the card.\n" +
                "Incomplete or inaccurate structures may earn partial points.\n" +
                "No points are awarded for structures that are significantly different from the target.\n" +
                "\n" +
                "Winning:\n" +
                "The game typically consists of several rounds.\n" +
                "The player or team with the highest total score at the end of the agreed-upon number of rounds wins.", 30, Gdx.graphics.getHeight() - 120);

        batch.end();

        // Draw the stage (UI elements)
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        backgroundTexture.dispose();  // Dispose of the background texture
        stage.dispose();
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
        buttonn.setWidth(400);
        buttonn.setHeight(500);

        return button;
    }
}

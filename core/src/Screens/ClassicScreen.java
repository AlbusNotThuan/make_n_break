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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.makeandbreak.game.MakeAndBreak;

import java.io.FileNotFoundException;

import Classes.ColorSelector;
import Classes.CustomButton;
import Classes.Grid;
import Classes.Player;
import Classes.Quiz;

public class ClassicScreen extends ApplicationAdapter implements Screen, InputProcessor {
    final MakeAndBreak game;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Stage stage;
    private Texture img;
    private Quiz quiz = new Quiz();
    private boolean change = true;

    private BitmapFont font;
    private float timeCount; // Timer variable
    private int worldTimer; // Initial timer value in seconds
    private boolean timeUp; // true when the world timer reaches 0
    private Label countdownLabel;
    private Label timeLabel;
    private final Player player1 = new Player();
    public ClassicScreen(MakeAndBreak game) throws FileNotFoundException {
        //Gamecam
        this.game = game;
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(game.WIDTH , game.HEIGHT, gamecam);
        gamecam.setToOrtho(false, game.WIDTH,game.HEIGHT);
        gamecam.translate(-game.WIDTH/2,-game.HEIGHT/2);

        //Init Stage
        stage = new Stage();
        Grid grid = new Grid();
        grid.setPosition(-200,-100);
        stage.addActor(grid);

        //Color Selector
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE,Color.BLACK, Color.GRAY, Color.PURPLE, Color.WHITE};

        for (int i = 0; i < colors.length; i++) {
            String colorName = getColorName(colors[i]);
            ColorSelector colorSelector = new ColorSelector(colors[i], colorName + ".png");
            colorSelector.setPosition(30 + i%4*75, -300 + i/4*140); // Adjust these values as needed
            colorSelector.setSize(50, 120);
            stage.addActor(colorSelector);
        }

        //Quiz
        quiz.returnQuiz();

        //Handle Input
        InputProcessor inputProcessor = new InputAdapter(){
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.SPACE){
                    try {
                        if(grid.checkMatrix(quiz.getQuizFiles()[1])){
                            System.out.println("Correct");
                            quiz.currentQuiz = quiz.returnQuiz();
                            change = true;
                            player1.setPoints();
                        } else {
                            System.out.println("Dumbass");
                        }
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
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

//        grid.Text2Array();


        //Quiz
        game.batch = new SpriteBatch();


        //Timer
        //define a table used to organize our hud's labels
        Table table = new Table();
        table.setPosition(-700,-350);
        //Top-Align table
        table.top();
        //make the table fill the entire stage
        table.setFillParent(true);
        // Initialize timer variables
        worldTimer = 180; // Initial time in seconds
        timeCount = 0;
        timeUp = false;
        //load font
        font=new BitmapFont(Gdx.files.internal("normalFont_1.fnt"));
        //define our labels using the String, and a Label style consisting of a font and color
        countdownLabel = new Label(String.format("%02d", worldTimer), new Label.LabelStyle(font, Color.BLACK));
        //countdownLabel.setFontScale(3);
        timeLabel = new Label("TIME", new Label.LabelStyle(font, Color.BLACK));
        //timeLabel.setFontScale(3);
        //add our labels to our table, padding the top, and giving them all equal width with
        Group group = new Group();
        group.addActor(countdownLabel);
        group.addActor(timeLabel);
        //group.scaleBy(2f,2f);
        table.add(timeLabel).expandX().padTop(10);
        //add a second row to our table
        table.row();
        //table.add(scoreLabel).expandX();
        table.add(countdownLabel).expandX();

        //add our table to the stage
        stage.addActor(table);

    }
    //public BitmapFont getFont(){return font;}
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
    public void resize(int width, int height) {
        gameport.update(width, height);
        gamecam.update();
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(4/255f, 105/255f, 30/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Cards
        game.batch.begin();
        if (change) {
            // Dispose of the old texture
            if (img != null) {
                img.dispose();
            }

            // Load the new texture
            String[] quizFiles = quiz.getQuizFiles();
            img = new Texture(quizFiles[0]);
            change = false;
        }

        game.batch.draw(img, 420, 350, 456/4*3, 320/4*3);
        game.batch.end();

        //Grid
        stage.setViewport(gameport);
        stage.draw();

    }

    private void update(float delta) {
        timeCount += delta;
        if(timeCount >= 1){
            if (worldTimer > 0) {
                worldTimer--;
            } else {
                timeUp = true;
                gameOver();
            }
            countdownLabel.setText(String.format("%02d", worldTimer));
            timeCount = 0;
        }
    }

    private void gameOver() {
        game.setScreen(new GameOverScreen(game));
    }

    @Override
    public void create() {
        img = new Texture(quiz.getQuizFiles()[0]);
        super.create();
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
        img.dispose();
        font.dispose();
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

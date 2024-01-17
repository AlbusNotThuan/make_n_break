package Classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class CustomButton extends ImageButton {

    private boolean clicked;
    private int id;
    private Grid grid;
    private static final ImageButtonStyle buttonStyle;
    static {
        //Texture to draw
        Texture texture =  new Texture(Gdx.files.internal("3.png"));
        TextureRegion textureRegion = new TextureRegion(texture);
        TextureRegionDrawable drawable = new TextureRegionDrawable(textureRegion);

        Texture texture1 = new Texture(Gdx.files.internal("4.png"));
        TextureRegion textureRegion1 = new TextureRegion(texture1);
        TextureRegionDrawable drawable1 = new TextureRegionDrawable(textureRegion1);

        buttonStyle = new ImageButtonStyle();
        buttonStyle.up = drawable;
        buttonStyle.checked = drawable1;
    }
    public CustomButton(ImageButtonStyle style, Grid table, int id) {
        super(style);
    }

    public CustomButton(Grid grid, int id){
        super(buttonStyle);
        this.id = id;
        this.grid = grid;
        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                return super.touchDown(event, x, y, pointer, button);

            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Button clicked");
                int row = id/6;
                int col = id%6;
                if (row > 0) {
                    grid.getButton(row - 1, col).setChecked(isChecked());
                }
                if (row < 5) {
                    grid.getButton(row + 1, col).setChecked(isChecked());
                }
                super.clicked(event, x, y);
            }
        });
    }

    @Override
    public boolean isPressed() {


        return super.isPressed();
    }

    @Override
    public ClickListener getClickListener() {
        System.out.println("haahhaha");
        return super.getClickListener();
    }

    public boolean isChecked() {

        return super.isChecked();

    }
}

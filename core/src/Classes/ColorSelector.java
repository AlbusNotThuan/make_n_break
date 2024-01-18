package Classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ColorSelector extends ImageButton {
    private Color color;
    private static final ImageButtonStyle buttonStyle;
    static {
        //Texture to draw
        Texture texture =  new Texture(Gdx.files.internal("red.png"));
        TextureRegion textureRegion = new TextureRegion(texture);
        TextureRegionDrawable drawable = new TextureRegionDrawable(textureRegion);

        buttonStyle = new ImageButtonStyle();
        buttonStyle.up = drawable;
    }

    public ColorSelector(Color color) {
        super(buttonStyle);
        this.color = color;
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GlobalState.selectedColor = color;
            }
        });
    }
}

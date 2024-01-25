package Classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class CustomButton extends ImageButton {
    private int id;
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
    public CustomButton(ImageButtonStyle style) {
        super(style);
    }
    public void setID(int id){
        this.id = id;
    }

    public int getID(){
        return this.id;
    }

    public CustomButton(){
        super(buttonStyle);

    }

    @Override
    public boolean isPressed() {
        return super.isPressed();
    }

    public boolean isChecked(Table table) {
        return super.isChecked();

    }
}

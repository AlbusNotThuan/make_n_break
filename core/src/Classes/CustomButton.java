package Classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class CustomButton extends ImageButton {

    private boolean clicked;
    private int id;
    private Grid grid;
    private Color selectedColor;
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
//                System.out.println("Button clicked");
                boolean wasChecked = !isChecked();
                int row = id / 6;
                int col = id % 6;
                //horizontally
                if (button == Input.Buttons.RIGHT){
                    if (col > 0 && col < 5){
                        boolean rightCheck = grid.getButton(row, col + 1).isChecked();
                        boolean leftCheck = grid.getButton(row, col - 1).isChecked();
                        System.out.println(rightCheck);
                        System.out.println(leftCheck);
                        System.out.println(wasChecked);
                        if ((rightCheck == leftCheck) && (rightCheck == !wasChecked)){
                            setChecked(wasChecked);
                            grid.getButton(row, col + 1).setChecked(wasChecked);
                            grid.getButton(row, col - 1).setChecked(wasChecked);
                            System.out.println("bbb");
                        } else {
                            System.out.println("Invalid move");
                            setChecked(!wasChecked);
                        }
                    } else {
                        System.out.println("Invalid move (outter)");
                        setChecked(!wasChecked);
                    }

                }else if (button == Input.Buttons.LEFT){
                    //vertically
                    if (row > 0 && row < 5) {
                        boolean topChecked = grid.getButton(row - 1, col).isChecked();
                        boolean bottomChecked = grid.getButton(row + 1, col).isChecked();
                        System.out.println(topChecked);
                        System.out.println(bottomChecked);
                        System.out.println(wasChecked);
                        if ((topChecked == bottomChecked) && (topChecked == !wasChecked)) {
                            setChecked(wasChecked);
                            grid.getButton(row - 1, col).setChecked(wasChecked);
                            grid.getButton(row + 1, col).setChecked(wasChecked);
                            System.out.println("aaa");
//                        setColor(GlobalState.selectedColor);
                        } else {
                            System.out.println("Invalid move");
                            setChecked(!wasChecked);
                        }
                    } else {
                        System.out.println("Invalid move (outter)");
                        setChecked(!wasChecked);
                    }
                }
                return super.touchDown(event, x, y, pointer, button);

            }

//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                System.out.println("Button clicked");
//                boolean wasChecked = isChecked();
//                int row = id / 6;
//                int col = id % 6;
//                //horizontally
//                if (event.getButton() == Input.Buttons.RIGHT){
//                    if (col > 0 && col < 5){
//                        boolean rightCheck = grid.getButton(row, col + 1).isChecked();
//                        boolean leftCheck = grid.getButton(row, col - 1).isChecked();
//                        if ((rightCheck == leftCheck) && (rightCheck == !wasChecked)){
//                            setChecked(wasChecked);
//                            grid.getButton(row, col + 1).setChecked(wasChecked);
//                            grid.getButton(row, col - 1).setChecked(wasChecked);
//                        } else {
//                            System.out.println("Invalid move");
//                            setChecked(!wasChecked);
//                        }
//                    }
//
//                }else if (event.getButton() == Input.Buttons.LEFT){
//                    //vertically
//                    if (row > 0 && row < 5) {
//                        boolean topChecked = grid.getButton(row - 1, col).isChecked();
//                        boolean bottomChecked = grid.getButton(row + 1, col).isChecked();
//                        if ((topChecked == bottomChecked) && (topChecked == !wasChecked)) {
//                            setChecked(wasChecked);
//                            grid.getButton(row - 1, col).setChecked(wasChecked);
//                            grid.getButton(row + 1, col).setChecked(wasChecked);
////                        setColor(GlobalState.selectedColor);
//                        } else {
//                            System.out.println("Invalid move");
//                            setChecked(!wasChecked);
//                        }
//                    } else {
//                        System.out.println("Invalid move");
//                        setChecked(!wasChecked);
//                    }
//                }
//                    super.clicked(event, x, y);
//            }


            ///END
        });
    }

    public void setColor(Color color) {
        ImageButtonStyle style = getButtonStyle(color);
        this.setStyle(style);
    }

    private static ImageButtonStyle getButtonStyle(Color color) {
        ImageButtonStyle style = new ImageButtonStyle();

        // Set the image of the button based on the color
        String imagePath;
        if (color == Color.RED) {
            imagePath = "red.png";
        } else if (color == Color.GREEN) {
            imagePath = "green.png";
        } else if (color == Color.BLUE) {
            imagePath = "blue.png";
        } else {
            imagePath = "white.png";
        }

        Texture texture = new Texture(Gdx.files.internal(imagePath));
        TextureRegion textureRegion = new TextureRegion(texture);
        TextureRegionDrawable drawable = new TextureRegionDrawable(textureRegion);

        style.up = drawable;

        return style;
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

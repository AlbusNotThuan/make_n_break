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
    public Color selectedColor = Color.WHITE;
    private static final ImageButtonStyle buttonStyle;
    static {
        //Default Texture
        Texture texture =  new Texture(Gdx.files.internal("white.png"));
        TextureRegion textureRegion = new TextureRegion(texture);
        buttonStyle = new ImageButtonStyle();
        buttonStyle.up =  new TextureRegionDrawable(textureRegion);
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
                boolean wasChecked = !isChecked();
                Color wasColor = getSelectedColor();
                int row = id / 6;
                int col = id % 6;
                //horizontally
                if (button == Input.Buttons.RIGHT){
                    if (col > 0 && col < 5){
                        Color rightColor = grid.getButton(row, col + 1).getSelectedColor();
                        Color leftColor = grid.getButton(row, col - 1).getSelectedColor();
                        if ((rightColor == leftColor) && (rightColor == wasColor)){
                            if (rightColor == Color.WHITE){
                                setColor(GlobalState.selectedColor);
                                selectedColor = GlobalState.selectedColor;

                                grid.getButton(row, col - 1).setColor(GlobalState.selectedColor);
                                grid.getButton(row, col - 1).selectedColor = GlobalState.selectedColor;

                                grid.getButton(row, col + 1).setColor(GlobalState.selectedColor);
                                grid.getButton(row, col + 1).selectedColor = GlobalState.selectedColor;
                            } else {
                                Color local = Color.WHITE;
                                setColor(local);
                                selectedColor = local;
                                grid.getButton(row, col - 1).setColor(local);
                                grid.getButton(row, col - 1).selectedColor = local;

                                grid.getButton(row , col+1).setColor(local);
                                grid.getButton(row , col+1).selectedColor = local;
                            }
                        } else {
                            System.out.println("Invalid move");
                        }
                    } else {
                        System.out.println("Invalid move (outter)");
                    }

                }else if (button == Input.Buttons.LEFT){
                    //vertically
                    if (row > 0 && row < 5) {
                        Color topColor = grid.getButton(row - 1, col).getSelectedColor();
                        Color bottomColor = grid.getButton(row + 1, col).getSelectedColor();
                        if ((topColor == bottomColor) && (topColor == wasColor)) {
                            if (topColor == Color.WHITE){
                                setColor(GlobalState.selectedColor);
                                selectedColor = GlobalState.selectedColor;
                                grid.getButton(row - 1, col).setColor(GlobalState.selectedColor);
                                grid.getButton(row - 1, col).selectedColor = GlobalState.selectedColor;

                                grid.getButton(row + 1, col).setColor(GlobalState.selectedColor);
                                grid.getButton(row + 1, col).selectedColor = GlobalState.selectedColor;
                            } else {
                                Color local = Color.WHITE;
                                setColor(local);
                                selectedColor = local;
                                grid.getButton(row - 1, col).setColor(local);
                                grid.getButton(row - 1, col).selectedColor = local;

                                grid.getButton(row + 1, col).setColor(local);
                                grid.getButton(row + 1, col).selectedColor = local;
                            }
                        } else {
                            System.out.println("Invalid move");
                        }
                    } else {
                        System.out.println("Invalid move (outter)");
                    }
                }
//                return super.touchDown(event, x, y, pointer, button);
                return false;

            }


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
        } else if (color == Color.WHITE) {
            imagePath = "white.png";
        } else {
            imagePath = "4.png";
        }

        Texture texture = new Texture(Gdx.files.internal(imagePath));
        TextureRegion textureRegion = new TextureRegion(texture);

        style.up = new TextureRegionDrawable(textureRegion);

        return style;
    }

    @Override
    public boolean isPressed() {
        return super.isPressed();
    }

    public boolean isChecked() {
        return super.isChecked();
    }

    public Color getSelectedColor() {
        return this.selectedColor;
    }
}

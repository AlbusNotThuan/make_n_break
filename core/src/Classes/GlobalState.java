package Classes;

import com.badlogic.gdx.graphics.Color;

public class GlobalState {
    public static Color selectedColor = Color.WHITE;
    public Color getColor(){
        return selectedColor;
    }
    public void setColor(Color color){
        selectedColor = color;
    }
}

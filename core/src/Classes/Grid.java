package Classes;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Grid extends Table {
    private CustomButton[][] buttons;

    public CustomButton getButton(int row, int col) {
        if (row >= 0 && row < buttons.length && col >= 0 && col < buttons[row].length) {
            return buttons[row][col];
        } else {
            throw new IllegalArgumentException("Invalid row or column");
        }
    }
    public Grid(){
        super();
        buttons = new CustomButton[6][6]; // Initialize the array

        // Create a 6x6 grid of buttons
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                buttons[i][j] = new CustomButton(this,i*6+j);
                add(buttons[i][j]).width(80).height(80).pad(5);
            }
            row();
        }
    }

}

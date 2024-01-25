package Classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.FileHandler;

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
    public boolean checkMatrix(){
        ArrayList<Color> currentGrid = new ArrayList<Color>();
        for (CustomButton[] row : buttons){
            for (CustomButton button : row){
                currentGrid.add(button.selectedColor);
            }
        }
        for (Color color : currentGrid){
            System.out.println(color.toString());
        }
        return false;
    }

    public void Text2Array() throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<String>();
        FileHandle file = Gdx.files.internal("cards/example.txt");
        String text = file.readString();
            BufferedReader reader = new BufferedReader(file.reader());

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Now 'lines' contains all lines from the file
        for (String textLine : lines) {
            System.out.println(textLine);
        }
    }

}

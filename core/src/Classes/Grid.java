package Classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Grid extends Table {
    private CustomButton[][] buttons;
    public final int COLNUM = 6;
    public final int ROWNUM = 7;

    public CustomButton getButton(int row, int col) {
        if (row >= 0 && row < buttons.length && col >= 0 && col < buttons[row].length) {
            return buttons[row][col];
        } else {
            throw new IllegalArgumentException("Invalid row or column");
        }
    }
    public Grid(){
        super();
        buttons = new CustomButton[ROWNUM][COLNUM]; // Initialize the array

        // Create a 6x6 grid of buttons
        for (int i = 0; i < ROWNUM; i++) {
            for (int j = 0; j < COLNUM; j++) {
                buttons[i][j] = new CustomButton(this,i*ROWNUM+j);
                add(buttons[i][j]).width(80).height(80).pad(5);
            }
            row();
        }
    }
    public boolean checkMatrix() throws FileNotFoundException {
        ArrayList<String> currentGrid = new ArrayList<String>();
        ArrayList<String> sampleArray = null;
        try {
            sampleArray = Text2Array();
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Return false or handle the exception as needed
        }
        for (CustomButton[] row : buttons){
            for (CustomButton button : row){
                currentGrid.add(button.selectedColor.toString());
                System.out.println(button.selectedColor.toString());
            }
        }

//        //Print grid
//        for (Color color : currentGrid){
//            System.out.println(color.toString());
//        }


        return currentGrid.equals(sampleArray);
    }

    public ArrayList<String> Text2Array() throws FileNotFoundException {
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

//        // Print lines
//        for (String textLine : lines) {
//            System.out.println(textLine);
//        }
        return lines;
    }

}

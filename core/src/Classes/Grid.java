package Classes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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

//    public void Text2Array(String filepath) throws FileNotFoundException {
//        File file = new File("Screens/example.txt");
//        ArrayList<String> result = new ArrayList<>();
//        Scanner scanner = new Scanner(file);
//        while (scanner.hasNextLine()){
//            String line = scanner.nextLine();
//            result.add(line);
//        }
//        for (String line : result){
//            System.out.println(line);
//        }
//    }

}
package Classes;

public class Player {
    private int points = 0;
    private String name;
    public Player(String name){
        this.name = name;
    }
    public Player(){

    }
    public void setPoints(){
        this.points = this.points + 3;
    }
    public int getPoints() {
        return points;
    }

}

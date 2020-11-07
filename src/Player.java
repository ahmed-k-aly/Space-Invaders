import java.awt.*;

public class Player {
    // Player class
    private final double PADDLE_WIDTH = 0.08;
    private final double PADDLE_HEIGHT = 0.01;
    private double x = 0;
    private final double y = 0.01;
    private final Color color; // Invader Color
    private double health = 100;

    public Player(Color color){
        this.color = color;
        StdDraw.setPenColor(color);
        StdDraw.filledRectangle(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        StdDraw.setPenColor(StdDraw.BLACK);
    }

    public boolean isHit(double x,double y){
        // returns true if the passed x and y parameters are inside the Player's paddle
        boolean checkX = getX1()<x && getX2()> x;
        boolean checkY = getY2()<x && getY1()> y;
        return  checkX && checkY;
    }

    public void moveTo(double x){
        // Moves player to the passed x-coordinate
        this.x = x;
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.filledRectangle(this.x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        StdDraw.setPenColor(Color.black);
    }

    public double getCentreX() {
        return x;
    }

    public double getX1() {
        return x-PADDLE_WIDTH;
    }


    public double getX2() {
        return x+PADDLE_WIDTH;
    }

    public double getY1() {
        return y+PADDLE_HEIGHT;
    }

    public double getY2() {
        return y-PADDLE_HEIGHT;
    }

    public double getCentreY() {
        return y;
    }

    public void  loseHealth(int hp){
        // Subtracts player's HP
        health += hp;
    }

    public boolean isDead(){
        // returns true if player's HP is zero
        return health <= 0;
    }

    public double getHealth() {
        return health;
    }

    public PlayerLasers shoot(double x, double y){
        return new PlayerLasers(x,y+PADDLE_HEIGHT);
    }

}

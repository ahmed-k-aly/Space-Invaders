import java.awt.*;

public class Player {
    // Player class
    private final double SIZE = 0.032;
    private double x = 0;
    private final double y = 0.04;
    private final Color color; // Invader Color
    private final int healthMax = 100;
    private int health = healthMax;

    public Player(Color color) {
        this.color = color;
        StdDraw.setPenColor(color);
        StdDraw.filledSquare(x, y, SIZE);
        StdDraw.picture(x, y, "icons/space-invaders.png");
        createHealthBar();
        StdDraw.setPenColor(StdDraw.BLACK);
    }

    public boolean isHit(double x, double y) {
        // returns true if the passed x and y parameters are inside the Player's paddle
        boolean checkX = getX1() < x && getX2() > x;
        boolean checkY = getY2() < x && getY1() - 0.01 > y;
        return checkX && checkY;
    }

    public void moveTo(double x) {
        // Moves player to the passed x-coordinate
        this.x = x;
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        //StdDraw.filledSquare(x, y, SIZE);
        createHealthBar();
        StdDraw.picture(x, y, "icons/space-invaders.png");
        StdDraw.setPenColor(Color.black);
    }

    private void createHealthBar() {
        double ratio = SIZE / (double) healthMax;
        StdDraw.setPenColor(Color.green);
        StdDraw.filledRectangle(x, 0, SIZE, 0.01);
        StdDraw.setPenColor(Color.RED);
        double width = ratio * (healthMax - health);
        StdDraw.filledRectangle(x + SIZE - width, 0, width, 0.01);
    }

    public double getCentreX() {
        return x;
    }

    public double getX1() {
        return x - SIZE;
    }

    public double getX2() {
        return x + SIZE;
    }

    public double getY1() {
        return y + SIZE;
    }

    public double getY2() {
        return y - SIZE;
    }

    public double getCentreY() {
        return y;
    }

    public void loseHealth(int hp) {
        // Subtracts player's HP
        if (health <= 0) {
            health = 0;
            return;
        }
        health += hp;
    }

    public void boostHealth(double healthBoost) {
        if (health >= 100) {
            health = 100;
            return;
        }
        this.health += healthBoost;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isDead() {
        // returns true if player's HP is zero
        return health <= 0;
    }

    public double getHealth() {
        return health;
    }

    public PlayerLasers shoot(double x, double y) {
        return new PlayerLasers(x, y + 0.032);
    }

}

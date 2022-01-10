
/*
@author Ahmed Aly
@date: 2020-11-16
This class is used to design the boss and handle the boss's logic 
*/

import java.awt.*;

public class Boss {
    private double x;
    private final double y = 0.6;
    private final double WIDTH = 0.07;
    private final double HEIGHT = 0.055;
    private final int healthMax = 100;
    private int health = healthMax;
    private double bossSpeed = 0.02;

    public Boss(double x) {
        this.x = x;
        StdDraw.picture(x, y, "icons/mothership.png");
    }


    public void loseHealth(int hp) {
        // Subtracts player's HP
        health += hp;
    }

    private void reverseDirectionCheck() {
        if (hitLeftWall() || hitRightWall()) {
            bossSpeed *= -1;
        }
    }

    public void boostHealth(double healthBoost) {
        this.health += healthBoost;
    }

    private void createHealthBar() {
        /* create boss health bar */
        double ratio = WIDTH / (double) healthMax;
        StdDraw.setPenColor(Color.green);
        StdDraw.filledRectangle(x, y + 0.05, WIDTH, 0.006);
        StdDraw.setPenColor(Color.RED);
        double width = ratio * (healthMax - health);
        StdDraw.filledRectangle(x + WIDTH - width, y + 0.05, width, 0.006);
    }

    public int getHealth() {
        /* get the health of the boss */
        return health;
    }

    public double getSIZE() {
        /* get the size of the boss figure */
        return WIDTH;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void move() {
        /* move the boss*/
        this.x += bossSpeed;
        reverseDirectionCheck();
        StdDraw.picture(x, y, "icons/mothership.png");
        createHealthBar();
    }

    public double getBossSpeed() {
        /* returns the boss speed*/
        return bossSpeed;
    }

    public void setBossSpeed(double bossSpeed) {
        this.bossSpeed = bossSpeed;
    }

    public double getX1() {
        return getCoordinates()[0];
    }

    public double getX2() {
        return getCoordinates()[1];
    }

    public double getY1() {
        return getCoordinates()[2];
    }

    public double getY2() {
        return getCoordinates()[3];
    }

    public boolean isHit(double otherX, double otherY) {
        // returns true if passed coordinates are within the objects hitbox
        boolean checkX = getX1() < otherX && getX2() > otherX;
        boolean checkY = getY2() <= otherY && getY1() >= otherY;
        return checkX && checkY;
    }


    public boolean isDead() {
        // returns true if player's HP is zero
        return health <= 0;
    }

    public double[] getCoordinates() {
        // returns x1, x2, y1, and y2 positions in an array
        double eastX = x - WIDTH; // Eastern edge
        double westX = x + WIDTH; // Western edge
        double northY = y + HEIGHT; // Northern edge
        double southY = y - HEIGHT; // Southern edge
        return new double[]{eastX, westX, northY, southY};
    }

    public boolean hitLeftWall() {
        return getCoordinates()[0] <= 0;
    }

    public boolean hitRightWall() {
        return getCoordinates()[1] >= 1;
    }

    public boolean hitTopWall() {
        return getCoordinates()[2] >= 1;
    }

    public boolean hitBottomWall() {
        return getCoordinates()[3] <= 0;
    }

    public boolean hitAnyWall() {
        return hitBottomWall() || hitLeftWall() || hitRightWall() || hitTopWall();
    }

    public BossLasers shoot(double x, double y) {
        return new BossLasers(x, y - WIDTH);
    }
}


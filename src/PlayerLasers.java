/* 
@author Ahmed Aly
Class that handles the logic and creation of the lasers of the player
*/

import java.awt.*;

public class PlayerLasers {
    private final double x;
    private double y;
    private final double LASER_LENGTH = 0.01;
    private double laserSpeed = 0.02;
    private Color color;
    public PlayerLasers(double x, double y, Color color) {
        this.x = x;
        this.y = y + LASER_LENGTH;
        this.color = color;
        StdDraw.setPenColor(color);
        StdDraw.rectangle(x, y, 0.001, LASER_LENGTH);
        StdDraw.setPenColor(Color.black);
    }

    public void move() {
        /* move the laser */
        this.y += laserSpeed;
        StdDraw.setPenColor(color);
        StdDraw.rectangle(x, y + LASER_LENGTH, 0.001, LASER_LENGTH);
    }

    public void setColor(Color color) {
        /* sets the color of the laser */
        this.color = color;
    }

    public double getX() {
        /* get X coordinate */
        return x;
    }

    public void setLaserSpeed(double laserSpeed) {
        /* sets the laser speed */
        this.laserSpeed = laserSpeed;
    }

    public double getLaserSpeed() {
        /* gets the laser speed */
        return laserSpeed;
    }

    public double getY() {
        /* get Y coordinate */
        return y;
    }

    public double getY1() {
        /* get bottom most y coordinate of the laser rectangle */
        return y + LASER_LENGTH;
    }

    public double getY2() {
        /* get bottom most y coordinate of the laser rectangle */
        return y - LASER_LENGTH;
    }
}


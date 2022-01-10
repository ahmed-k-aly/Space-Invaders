/* 
@author Ahmed Aly
@date 2020-11-16
This class handles the logic of the invader's laser
*/
import java.awt.*;

public class InvaderLasers {
    // All Invader lasers should be objects of this class
    private final double x;
    private double y;
    private final double LASER_LENGTH = 0.01;
    private final Color color;
    private double laserSpeed;

    public InvaderLasers(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        StdDraw.setPenColor(color);
        StdDraw.rectangle(x, y + LASER_LENGTH, 0.001, LASER_LENGTH);
    }

    public void setLaserSpeed(int diff) {
        if (diff == 1) laserSpeed = -0.04;
        else if (diff == 2) laserSpeed = -0.06;
        else laserSpeed = -0.02;
    }

    public double getLaserSpeed() {
        return laserSpeed;
    }

    public void move() {
        this.y += laserSpeed;
        StdDraw.setPenColor(color);
        StdDraw.rectangle(x, y + LASER_LENGTH, 0.001, LASER_LENGTH);
    }

    public double getX() {
        return x;
    }

    public double getCentreY() {
        return y;
    }

    public double getY1() {
        return y + LASER_LENGTH;
    }

    public double getY2() {
        return y - LASER_LENGTH;
    }


}
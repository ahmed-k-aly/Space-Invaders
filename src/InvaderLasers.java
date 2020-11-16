import java.awt.*;

public class InvaderLasers {
    // All Invader lasers should be objects of this class
    private final double x;
    private double y;
    private final double LASER_LENGTH = 0.01;
    private Color color;
    private double laserSpeed = -0.02;

    public InvaderLasers(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        StdDraw.setPenColor(color);
        StdDraw.rectangle(x, y + LASER_LENGTH, 0.001, LASER_LENGTH);
    }

    public void setLaserSpeed(double laserSpeed) {
        this.laserSpeed = laserSpeed;
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
import java.awt.*;

public class PlayerLasers {
    private final double x;
    private double y;
    private final double LASER_LENGTH = 0.01;
    private double laserSpeed = 0.02;

    public PlayerLasers(double x, double y) {
        this.x = x;
        this.y = y + LASER_LENGTH;
        StdDraw.setPenColor(Color.green);
        StdDraw.rectangle(x, y, 0.001, LASER_LENGTH);
        StdDraw.setPenColor(Color.black);
    }

    public void move() {
        this.y += laserSpeed;
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.rectangle(x, y + LASER_LENGTH, 0.001, LASER_LENGTH);
    }

    public double getX() {
        return x;
    }

    public void setLaserSpeed(double laserSpeed) {
        this.laserSpeed = laserSpeed;
    }

    public double getLaserSpeed() {
        return laserSpeed;
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


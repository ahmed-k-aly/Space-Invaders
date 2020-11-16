import java.awt.*;

public class BossLasers {
    private final double x;
    private double y;
    private final double LASER_LENGTH = 0.05;
    private double laserSpeed = -0.01;

    public BossLasers(double x, double y) {
        this.x = x;
        this.y = y;
        StdDraw.filledRectangle(x, y + LASER_LENGTH, 0.001, LASER_LENGTH);
    }

    public void move() {
        this.y += laserSpeed;
        StdDraw.setPenColor(Color.red);
        StdDraw.filledRectangle(x, y + LASER_LENGTH, 0.005, LASER_LENGTH);
    }

    public void setLaserSpeed(double laserSpeed) {
        this.laserSpeed = laserSpeed;
    }

    public double getLaserSpeed() {
        return laserSpeed;
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

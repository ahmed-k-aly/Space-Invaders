import java.awt.*;

public class PlayerLasers {
    private final double x;
    private double y;
    private final double LASER_LENGTH = 0.01;

    public PlayerLasers(double x, double y) {
        this.x = x;
        this.y = y;
        StdDraw.setPenColor(Color.green);
        StdDraw.rectangle(x, y + LASER_LENGTH, 0.001, LASER_LENGTH);
        StdDraw.setPenColor(Color.black);
    }

    public void move(double dy){
        this.y += dy;
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.rectangle(x, y + LASER_LENGTH, 0.001, LASER_LENGTH);
    }

    public double getX() {
        return x;
    }

    public double getCentreY() {
        return y;
    }

    public double getY1(){ return y+LASER_LENGTH; }

    public double getY2(){ return y-LASER_LENGTH; }
}


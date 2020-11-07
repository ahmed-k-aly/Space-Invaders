import java.awt.*;

public class InvaderLasers {
    // All Invader lasers should be objects of this class
    private final double x;
    private double y;
    private final double LASER_LENGTH = 0.01;

    public InvaderLasers(double x, double y) {
        this.x = x;
        this.y = y;
        StdDraw.setPenColor(Color.black);
        StdDraw.rectangle(x, y + LASER_LENGTH, 0.001, LASER_LENGTH);
    }

    public void move(double dy){
        this.y += dy;
        StdDraw.setPenColor(StdDraw.BLACK);
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
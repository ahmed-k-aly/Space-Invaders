import java.awt.*;

public class Invader {
    // Invader class
    private double x;
    private double y;
    private final double SIZE = 0.03;
    private final String[] invaderType = {"icons/alien.png", "icons/alien2.png", "icons/alien3.png"};
    private final int invaderTypeIndex = (int) (Math.random() * 3);
    private final Color color;
    private double invaderSpeed;


    public Invader(double x, double y) {
        this.x = x;
        this.y = y;
        if (invaderTypeIndex == 0) color = Color.CYAN;
        else if (invaderTypeIndex == 2) color = Color.GREEN;
        else color = Color.MAGENTA;
        StdDraw.picture(x, y, invaderType[invaderTypeIndex]);
        StdDraw.setPenColor(Color.black);
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

    public void setY(int y) {
        this.y = y;
    }

    public void move() {
        this.y += invaderSpeed;
        if (getY1() < 0) y = 1;
        StdDraw.picture(x, y, invaderType[invaderTypeIndex]);
    }

    public void setInvaderSpeed(int diff) {
        if (diff == 1) invaderSpeed = -0.008;
        else if (diff == 2) invaderSpeed = -0.013;
        else invaderSpeed = -0.005;
    }

    public double getInvaderSpeed() {
        return invaderSpeed;
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
        boolean checkY = getY2() <= otherY;
        return checkX && checkY;
    }

    public double[] getCoordinates() {
        // returns x1, x2, y1, and y2 positions in an array
        double eastX = x - SIZE; // Eastern edge
        double westX = x + SIZE; // Western edge
        double northY = y + SIZE; // Northern edge
        double southY = y - SIZE; // Southern edge
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

    public InvaderLasers shoot(double x, double y) {
        return new InvaderLasers(x, y - SIZE, color);
    }
}



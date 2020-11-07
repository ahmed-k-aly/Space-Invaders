import java.awt.*;

public class Invader {
    // Invader class
    private double x;
    private double y;
    private final Color color; // Invader Color
    private final double SIZE = 0.02;

    public Invader(double x, double y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
        StdDraw.setPenColor(color);
        StdDraw.filledSquare(x, y, SIZE);
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

    public void move(double dx, double dy){
        this.x += dx;
        this.y += dy;
        StdDraw.setPenColor(color);
        StdDraw.filledSquare(x,y, SIZE);
        StdDraw.setPenColor(Color.black);
    }

    public double getX1(){
        return getCoordinates()[0];
    }

    public double getX2(){
        return getCoordinates()[1];
    }

    public double getY1(){
        return getCoordinates()[2];
    }

    public double getY2(){
        return getCoordinates()[3];
    }

    public boolean isHit(double otherX, double otherY) {
        // returns true if passed coordinates are within the objects hitbox
        boolean checkX = getX1() < otherX && getX2() > otherX;
        boolean checkY = getY2()< otherY && getY1() > otherY;
        return  checkX && checkY;
    }

    public double[] getCoordinates(){
        // returns x1, x2, y1, and y2 positions in an array
        double eastX = x-SIZE ; // Eastern edge
        double westX = x + SIZE ; // Western edge
        double northY = y + SIZE; // Northern edge
        double southY = y - SIZE ; // Southern edge
        return new double[]{eastX, westX, northY, southY};
    }

    public boolean hitLeftWall(){
        return getCoordinates()[0] <= 0;
    }
    public boolean hitRightWall(){
        return getCoordinates()[1] >= 1;
    }
    public boolean hitTopWall(){
        return getCoordinates()[2] >= 1;
    }
    public boolean hitBottomWall(){
        return getCoordinates()[3] <= 0;
    }

    public boolean hitAnyWall(){
        return hitBottomWall() || hitLeftWall() || hitRightWall() || hitTopWall();
    }

    public InvaderLasers shoot(double x, double y){
        return new InvaderLasers(x,y-SIZE);
    }
    }



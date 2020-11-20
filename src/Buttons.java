import java.awt.*;

public class Buttons {
    private final double x;
    private final double y;
    private final String message;
    private Color color = Color.gray;
    public final double buttonWidth = 0.15;
    Font font = new Font("Arial", Font.BOLD, 12);
    public final double buttonHeight = 0.03;

    public Buttons(double x, double y, String message){
        StdDraw.setFont(font);
        this.x = x;
        this.y = y;
        this.message = message;
        StdDraw.setPenColor(color);
        StdDraw.filledRectangle(x,y,buttonWidth,buttonHeight);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(x,y,message);
    }

    public Buttons(double x, double y, String message, Color color, double size){
        StdDraw.setFont(font);
        this.x = x;
        this.y = y;
        this.message = message;
        this.color = color;
        StdDraw.setPenColor(color);
        StdDraw.filledRectangle(x,y, size, buttonHeight);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(x,y,message);
    }



    public double getX1() {
        return x - buttonWidth;
    }

    public double getX2() {
        return x + buttonWidth;
    }

    public double getY1() {
        return y + buttonHeight;
    }

    public double getY2() {
        return y - buttonHeight;
    }

    public boolean isButtonPressed(){
        boolean checkX = getX1() < StdDraw.mouseX() && getX2() > StdDraw.mouseX();
        boolean checkY = getY2() < StdDraw.mouseY() && getY1() > StdDraw.mouseY();
        return checkX && checkY && StdDraw.isMousePressed();
    }


}

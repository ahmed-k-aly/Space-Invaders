/* 
@author Ahmed Aly
@date 2020-11-16
This class stores the data of the game
*/
import java.awt.*;

public class GameData {
    private double mouseSensitivity;
    private String playerAppearance;
    private int difficulty;
    private Color laserColor = Color.green;
    private boolean godMode;

    public GameData(){}

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setLaserColor(Color laserColor) {
        this.laserColor = laserColor;
    }

    public void setMouseSensitivity(double mouseSensitivity) {
        this.mouseSensitivity = mouseSensitivity;
    }

    public void setPlayerAppearance(String playerAppearance) {
        this.playerAppearance = playerAppearance;
    }

    public void toggleGodMode() {
        this.godMode = true;
    }

    public void deToggleGodMode() {
        this.godMode = false;
    }


    public Color getLaserColor() {
        return laserColor;
    }

    public double getMouseSensitivity() {
        return mouseSensitivity;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getPlayerAppearance() {
        return playerAppearance;
    }

    public boolean isGodMode() {
        return godMode;
    }
}


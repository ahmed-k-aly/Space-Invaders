public class Level {
    // Sets the game based on the level the player is at

    private final int level;
    private int numInvaders;
    private final double invaderSpeed;
    private int numBosses;
    private final double bossSpeed;
    // TODO: Add difficulty factor
    // TODO: add method to display level

    public Level(int level, boolean isBossLevel){
        this.level = level;
        if(isBossLevel){
            numInvaders = 0;
            invaderSpeed = 0;
            numBosses = level%10;
            bossSpeed = 0.001;
        }
        else {
            numBosses = 0;
            bossSpeed = 0;
            numInvaders = level*2;
            invaderSpeed = 0.01; // Do some equation to calculate speed based on difficulty
        }
    }




    public void invaderKilled(){
        // decrements the value of numInvader by 1 when Invader killed
        numInvaders--;
    }


    public void bossKilled(){
        // decrements the value of numBosses by 1 when boss killed
        numBosses--;
    }


    public boolean levelComplete(){
        return (numBosses == 0 && numInvaders == 0);
    }


    public int getLevel() {
        return level;
    }

    public int getNumInvaders() {
        return numInvaders;
    }

    public double getInvaderSpeed() {
        return invaderSpeed;
    }

    public double getBossSpeed() {
        return bossSpeed;
    }

    public int getNumBosses() {
        return numBosses;
    }

}

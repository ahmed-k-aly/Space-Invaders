public class Level {
    // Sets the game based on the level the player is at
    private int level;
    private int numInvaders;
    private int numBosses;
    private Invader[] invaders;
    private Boss[] bosses;
    private int score = 0;
    private int difficulty;

    public Level() {
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setBosses(Boss[] bosses) {
        this.bosses = bosses;
    }

    public Boss[] getBosses() {
        return bosses;
    }

    public boolean isBossLevel() {
        return (level % 5 == 0 && (numBosses > 0 || numInvaders > 0));
    }

    public void setInvaders(Invader[] invaders) {
        this.invaders = invaders;
    }

    public Invader[] getInvaders() {
        return invaders;
    }

    public void setNumBosses() {
        if (level % 5 == 0) this.numBosses = level / 5;
        else this.numBosses = 0;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setNumInvaders() {
        this.numInvaders = (2 * level) - numBosses * 5;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void invaderKilled() {
        // decrements the value of numInvader by 1 when Invader killed
        numInvaders--;
        score+=20 + 20*difficulty;
    }

    public void bossKilled() {
        // decrements the value of numBosses by 1 when boss killed
        numBosses--;
        score+=100 + 100*difficulty;
    }

    public int getScore() {
        return score;
    }

    public boolean levelComplete() {
        return ((numBosses == 0 && numInvaders >=0));
    }

    public int getLevel() {
        return level;
    }

    public int getNumInvaders() {
        return numInvaders;
    }

    public int getNumBosses() {
        return numBosses;
    }

}

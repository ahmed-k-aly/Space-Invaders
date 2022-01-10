/* 
@author Ahmed Aly
@date 2020-11-16
This is the main class that the game starts from.
*/
import java.awt.*;


public class Project {

    private static final Player PLAYER = new Player();
    private static Level levelData = new Level(); // Creates the Data for the level
    private static final GameData gameData = new GameData();

    private static InvaderLasers[] invaderShoot() { // Pass an array of all invaders
        // Shoots Enemy bullet and then returns an array of the recently shot bullets
        Invader[] invaders = levelData.getInvaders();
        InvaderLasers[] bullet = new InvaderLasers[invaders.length];
        int counter = 0;
        if (Math.random() > 0.97 - 0.015* gameData.getDifficulty()) { // Chance to shoot any bullet to give a grace period
            for (int i = 0; i < invaders.length; i++) {
                Invader invader = invaders[i];
                if (invader.getCoordinates()[2] < 1 && invader.getCoordinates()[2] > 0) {
                    if (Math.random() > 0.5) { // Chance of Invader shooting, so that not all invaders shoot all the time
                        bullet[counter] = invader.shoot(invader.getX(), invader.getCoordinates()[3]);
                        counter++;
                    }
                }
            }
        }
        InvaderLasers[] bulletAdjusted = new InvaderLasers[counter];
        counter = 0;
        for (int i = 0; i < bullet.length; i++) {
            // Adjusts the bullet array so that here are no nulls
            if (bullet[i] != null) {
                bulletAdjusted[counter] = bullet[i];
                counter++;
            }
        }
        return bulletAdjusted;
    }

    private static BossLasers[] bossShoot() { // Pass an array of all invaders
        // Shoots Enemy bullet and then returns an array of the recently shot bullets
        Boss[] bosses = levelData.getBosses();
        BossLasers[] bullet = new BossLasers[bosses.length];
        int counter = 0;
        if (Math.random() > 0.96) { // Chance to shoot any bullet to give a grace period
            for (int i = 0; i < bosses.length; i++) {
                Boss boss = bosses[i];
                if (boss.getCoordinates()[2] < 1 && boss.getCoordinates()[2] > 0) {
                    if (Math.random() > 0.5) { // Chance of Invader shooting, so that not all invaders shoot all the time
                        bullet[counter] = boss.shoot(boss.getX(), boss.getY2());
                        counter++;
                    }
                }
            }
        }
        BossLasers[] bulletAdjusted = new BossLasers[counter];
        counter = 0;
        for (int i = 0; i < bullet.length; i++) {
            // Adjusts the bullet array so that here are no nulls
            if (bullet[i] != null) {
                bulletAdjusted[counter] = bullet[i];
                counter++;
            }
        }
        return bulletAdjusted;
    }

    private static InvaderLasers[] activeInvaderLasersArrayInit(InvaderLasers[] arr) {
        // Returns an array with only the active bullets. Cleaner
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getCentreY() > 0 && (!PLAYER.isHit(arr[i].getX(), arr[i].getY2()))) counter++;
        }
        InvaderLasers[] b = new InvaderLasers[counter];
        counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getCentreY() > 0 && (!PLAYER.isHit(arr[i].getX(), arr[i].getY2()))) {
                b[counter] = arr[i];
                counter++;
            }
        }
        return b;
    }

    private static InvaderLasers[] invaderLasersArrayMerger(InvaderLasers[] a, InvaderLasers[] b) {
        // Merges two InvaderLasers arrays together end on front. returns the merged array
        // Main use: add the lasers recently hit in the frame to the main lasers array
        a = activeInvaderLasersArrayInit(a);
        b = activeInvaderLasersArrayInit(b);
        InvaderLasers[] z = new InvaderLasers[a.length + b.length];
        for (int i = 0; i < a.length; i++) {
            z[i] = a[i];
        }
        for (int i = 0; i < b.length; i++) {
            z[i + a.length] = b[i];
        }
        return z;
    }

    private static BossLasers[] activeBossLasersArrayInit(BossLasers[] arr) {
        // Returns an array with only the active bullets. Cleaner
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getCentreY() > 0 && (!PLAYER.isHit(arr[i].getX(), arr[i].getY2()))) counter++;
        }
        BossLasers[] b = new BossLasers[counter];
        counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getCentreY() > 0 && (!PLAYER.isHit(arr[i].getX(), arr[i].getY2()))) {
                b[counter] = arr[i];
                counter++;
            }
        }
        return b;
    }

    private static BossLasers[] bossLasersArrayMerger(BossLasers[] a, BossLasers[] b) {
        // Merges two BossLasers arrays together end on front. returns the merged array
        // Main use: add the lasers recently hit in the frame to the main lasers array
        a = activeBossLasersArrayInit(a);
        b = activeBossLasersArrayInit(b);
        BossLasers[] z = new BossLasers[a.length + b.length];
        for (int i = 0; i < a.length; i++) {
            z[i] = a[i];
        }
        for (int i = 0; i < b.length; i++) {
            z[i + a.length] = b[i];
        }
        return z;
    }

    private static PlayerLasers[] activePlayerLasersArrayInit(PlayerLasers[] arr) {
        // Returns an array with only the active bullets. Cleaner
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getY2() < 1) counter++;
        }
        PlayerLasers[] b = new PlayerLasers[counter];
        counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getY2() < 1) {
                b[counter] = arr[i];
                counter++;
            }
        }
        return b;
    }

    private static PlayerLasers[] playerLasersArrayMerger(PlayerLasers[] a, PlayerLasers[] b) {
        // Merges two InvaderLasers arrays together end on front. returns the merged array
        // Main use: add the lasers recently hit in the frame to the main lasers array
        a = activePlayerLasersArrayInit(a);
        b = activePlayerLasersArrayInit(b);
        PlayerLasers[] z = new PlayerLasers[a.length + b.length];
        for (int i = 0; i < a.length; i++) {
            z[i] = a[i];
        }
        for (int i = 0; i < b.length; i++) {
            z[i + a.length] = b[i];
        }
        return z;
    }

    private static void moveInvaderLasers(InvaderLasers[] bullet) {
        // Moves invaders lasers down with a speed of 0.02 pixels per frame
        for (int i = 0; i < bullet.length; i++) {
            bullet[i].setLaserSpeed(gameData.getDifficulty());
            bullet[i].move();
        }
    }

    private static void moveBossLasers(BossLasers[] bullet) {
        // Moves invaders lasers down with a speed of 0.02 pixels per frame
        for (int i = 0; i < bullet.length; i++) {
            bullet[i].move();
        }
    }

    private static void movePlayerLasers(PlayerLasers[] bullet) {
        // Moves player lasers up with a speed of 0.02 pixels per frame.
        for (int i = 0; i < bullet.length; i++) {
            bullet[i].move();
        }
    }

    private static void initInvadersArray() {
        // Initializes the Invaders array
        int numInvaders = levelData.getNumInvaders();
        Invader[] invaders = new Invader[numInvaders];
        for (int i = 0; i < invaders.length; i++) {
            double x = Math.random() * ((1 - 0.02) - (0.02)) + 0.02;
            double y = Math.random() * (2 - 1) + 1;
            invaders[i] = new Invader(x, y);
        }
        levelData.setInvaders(invaders);
    }

    private static void initBossesArray() {
        // Initializes the Bosses array
        int numBosses = levelData.getNumBosses();
        Boss[] bosses = new Boss[numBosses];
        double spaceBtwnBosses = 1.0/numBosses;
        Boss fake = new Boss(-1);
        double x = fake.getSIZE();
        for (int i = 0; i < bosses.length; i++) {
            bosses[i] = new Boss(x);
            double width = bosses[i].getSIZE();
            x+=spaceBtwnBosses;
        }


        levelData.setBosses(bosses);
    }

    private static void moveInvaders() {
        // Moves the invaders.
        Invader[] invaders = levelData.getInvaders();
        for (int i = 0; i < invaders.length; i++) {
            invaders[i].setInvaderSpeed(gameData.getDifficulty());
            invaders[i].move();
        }
    }

    private static void moveBosses() {
        // Moves the invaders.
        Boss[] bosses = levelData.getBosses();
        for (int i = 0; i < bosses.length; i++) {
            bosses[i].move();
        }
    }

    private static void checkInvadersLasersHit(InvaderLasers[] lasers) {
        // Checks if player is hit from invaders
        for (int i = 0; i < lasers.length; i++) {
            if (PLAYER.isHit(lasers[i].getX(), lasers[i].getY2())) {
                PLAYER.loseHealth(-10);
            }
        }
    }

    private static void checkBossLasersHit(BossLasers[] lasers) {
        // Checks if player is hit from invaders
        for (int i = 0; i < lasers.length; i++) {
            if (PLAYER.isHit(lasers[i].getX(), lasers[i].getY2())) {
                PLAYER.loseHealth(-33);
            }
        }
    }

    private static PlayerLasers[] playerShoots(Time stopwatch, int dt) {
        stopwatch.stopStopwatch();
        if (stopwatch.timePassed() > dt) {
            PlayerLasers[] bullet = new PlayerLasers[1];
            bullet[0] = PLAYER.shoot(PLAYER.getCentreX(), PLAYER.getY1(), gameData.getLaserColor());
            stopwatch.startStopwatch();
            return bullet;
        }
        return new PlayerLasers[0];
    }

    private static PlayerLasers[] checkPlayerLasersHit(PlayerLasers[] playerLasers) {
        /*
        Checks if an Invader is hit then removes the hit invader and the bullet from the arrays
        returns the playerLasers array and invaders array as objects of a class that's used only for this.
        */
        Invader[] invaders = levelData.getInvaders();
        int hitInvadersIndex = 0;
        int hitBulletIndex = 0;
        int hitBossIndex = 0;
        int counter = 0;
        for (int invader = 0; invader < invaders.length; invader++) {
            for (int bullet = 0; bullet < playerLasers.length; bullet++) {
                if (invaders[invader].isHit(playerLasers[bullet].getX(), playerLasers[bullet].getY1())) {
                    hitInvadersIndex = invader; // gives the indexes to be removed at once
                    hitBulletIndex = bullet;
                    counter++;
                }
            }
        }
        int counter2 = 0;
        Boss[] bosses = levelData.getBosses();
        int hitBulletIndex1 = 0;
        for (int boss = 0; boss < bosses.length; boss++) {
            for (int bullet = 0; bullet < playerLasers.length; bullet++) {
                if (bosses[boss].isHit(playerLasers[bullet].getX(), playerLasers[bullet].getY1())) {
                    bosses[boss].loseHealth(-10);
                    hitBulletIndex1 = bullet;
                    counter2++;
                }
            }
        }

        if (counter > 0) { // No Bullet hit
            invaders = remove(invaders, hitInvadersIndex);
            playerLasers = remove(playerLasers, hitBulletIndex);
            levelData.setInvaders(invaders);
        }
        if (counter2 > 0) {
            playerLasers = remove(playerLasers, hitBulletIndex1);
        }
        return playerLasers;
    }

    private static Invader[] remove(Invader[] arr, int index) {
        // Helper method that removes given index from the array
        if (index < 0 || index >= arr.length) {
            return arr;
        }
        Invader[] newArr = new Invader[arr.length - 1];
        for (int i = 0; i < index; i++) { // Copies all elements before the index
            newArr[i] = arr[i];
        }
        for (int i = index; i < arr.length - 1; i++) { // Copies all the elements after the index
            newArr[i] = arr[i + 1];
        }
        Project.levelData.invaderKilled();
        return newArr;
    }

    private static PlayerLasers[] remove(PlayerLasers[] arr, int index) {
        if (index < 0 || index >= arr.length) {
            return arr;
        }

        PlayerLasers[] newArr = new PlayerLasers[arr.length - 1];
        for (int i = 0; i < index; i++) { // Copies all elements before the index
            newArr[i] = arr[i];
        }

        for (int i = index; i < arr.length - 1; i++) { // Copies all the elements after the index
            newArr[i] = arr[i + 1];
        }

        return newArr;
    }

    private static void levelCompletedCheck() {
        // Checks if level is complete
        // Changes level if level complete
        if (levelData.levelComplete()) {
            levelData.setLevel(levelData.getLevel() + 1);
            initLevel();
            PLAYER.boostHealth(10);
        }
    }

    private static Boss[] remove(Boss[] arr, int index) {
        // removes passed index from the array then returns it
        if (index < 0 || index >= arr.length) {
            return arr;
        }
        Boss[] newArr = new Boss[arr.length - 1];
        for (int i = 0; i < index; i++) { // Copies all elements before the index
            newArr[i] = arr[i];
        }

        for (int i = index; i < arr.length - 1; i++) { // Copies all the elements after the index
            newArr[i] = arr[i + 1];
        }

        return newArr;
    }


    public static void removeBoss() {
        Boss[] bosses = levelData.getBosses();
        Boss[] bosses1 = levelData.getBosses();
        for (int i = 0; i < bosses.length; i++) {
            if (bosses[i].isDead()) {
                bosses1 = remove(bosses, i);
                levelData.bossKilled();
            }
        }
        levelData.setBosses(bosses1);
    }

    public static void colorButtons(){
        StdDraw.pause(60);
        while (true){
            double x =0.5;
            double y = .03 ;
            double mas = 0.2;

            Buttons red = new Buttons(x,mas + y, "Red");
            Buttons blue = new Buttons( x, mas+ 3*y, "Blue");
            Buttons green = new Buttons(x,mas + 5*y, "Green");
            Buttons yellow = new Buttons(x, mas + 7*y, "Yellow");
            Buttons black = new Buttons(x,mas + 9*y, "Black");
            Buttons white = new Buttons(x,mas + 11*y, "White");
            Buttons cyan = new Buttons(x, mas + 13*y, "Cyan");
            Buttons pink = new Buttons(x, mas + 15*y, "Pink");
            Buttons magenta = new Buttons(x, mas + 17*y, "Magenta");
            if (red.isButtonPressed()){
                gameData.setLaserColor(Color.red);
                StdDraw.pause(60);
                break;
            }
            else if (blue.isButtonPressed()){
                gameData.setLaserColor(Color.blue);
                StdDraw.pause(60);
                break;
            }else if (green.isButtonPressed()){
                gameData.setLaserColor(Color.green);
                StdDraw.pause(60);
                break;
            }else if (yellow.isButtonPressed()){
                gameData.setLaserColor(Color.yellow);
                StdDraw.pause(60);
                break;
            }else if (white.isButtonPressed()){
                gameData.setLaserColor(Color.white);
                StdDraw.pause(60);
                break;
            }else if (black.isButtonPressed()){
                gameData.setLaserColor(Color.black);
                StdDraw.pause(60);
                break;
            }
            else if (cyan.isButtonPressed()){
                gameData.setLaserColor(Color.cyan);
                StdDraw.pause(60);
                break;
            }
            else if (pink.isButtonPressed()){
                gameData.setLaserColor(Color.pink);
                StdDraw.pause(60);
                break;
            }
            else if (magenta.isButtonPressed()){
                gameData.setLaserColor(Color.magenta);
                StdDraw.pause(60);
                break;
            }
            updateScreen(10);
        }
    }


    public static void initLevel() {
        // Initializes the level
        levelData.setNumInvaders();
        levelData.setNumBosses();
        initInvadersArray(); // Initializes invaders array in Level class
        initBossesArray();
    }

    public static void displayLevel(){
        // Displays current Level
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(0.07, 0.02, "Level: " + levelData.getLevel());
    }

    public static void displayScore(){
        // Displays Current score
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(0.6, 0.02, "Score: " + levelData.getScore());
    }

    public static void gameOver(){
        // Prints message after death
        StdDraw.disableDoubleBuffering();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledSquare(0.5,0.5,0.5);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.5,0.5,"GAME OVER");
        StdDraw.text(0.5,0.46,"Press Enter to play again ");
        StdDraw.text(0.5,0.42,"Press ESC to exit");
    }

    public static int prompt(int hs){
        // Prompts player to play again or end game
        // if player plays again, resets everything and breaks
        if (hs < levelData.getScore()) hs = levelData.getScore();
        StdDraw.text(0.5,0.6,"HighScore: " + hs);
        while (true){
            if (StdDraw.isKeyPressed(10)) { // press enter
                resetEverything();
                return hs;
            }
            else if (StdDraw.isKeyPressed(27)) System.exit(0); // esc pressed
        }
    }

    public static void resetEverything(){
        // Sets player health back to 100
        // Changes level object
        PLAYER.setHealth(100);
        levelData = new Level();
    }


    public static void main(String[] args) {
        mainMenu(); // initiate main menu
        game(0); // start the game
    }

    public static void difficultyMenu(){
        // Creates menu to choose difficulty
        StdDraw.pause(60);
        while (true){
            Buttons easy = new Buttons(0.5, 0.6,"Easy");
            Buttons normal = new Buttons(0.5, 0.6 - easy.buttonHeight*2,"Normal");
            StdDraw.setPenColor(Color.WHITE);
            Buttons hard = new Buttons(0.5, 0.6 - easy.buttonHeight*4,"Invading Russia in winter");
            if (easy.isButtonPressed()){
                gameData.setDifficulty(0);
                break;
            }
            else if (normal.isButtonPressed()){
                gameData.setDifficulty(1);
                break;
            }
            else if (hard.isButtonPressed()){
                gameData.setDifficulty(2);
                break;
            }
            updateScreen(20);
        }
    }

    public static void mainMenu(){
        // Creates a main Menu before the game.
        // Controls Difficulty.
        // Exits game or Starts game.
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        while (true) {
            Buttons playGame = new Buttons(0.5, 0.5, "Play Game");
            double buttonHeight = playGame.buttonHeight;
            Buttons difficulty = new Buttons(0.5,0.5-buttonHeight*2, "Difficulty");
            Buttons laserColor = new Buttons(0.5,0.5-buttonHeight*4, "Laser Color");
            Buttons exit = new Buttons(0.5,0.5-buttonHeight*6,"Exit");
            if (playGame.isButtonPressed()) {
                StdDraw.disableDoubleBuffering();
                return;
            }
            else if (exit.isButtonPressed()){
                System.exit(0);
            }
            else if (difficulty.isButtonPressed()){
                difficultyMenu();
            }
            else if (laserColor.isButtonPressed()){
                colorButtons();
            }
            updateScreen(10);
        }
    }


    public static void startMenu(int highscore){
        /* Create a start menu with buttons
                 Allow to exit game
                 Allow to toggle cheats
                 Allow to restart game
        */
        while (true) {
            String onOff;
            if (gameData.isGodMode()){
                onOff = "on";
            }
            else { onOff = "off";
            }
            Buttons godMode = new Buttons(0.5, 0.5, "godMode: " + onOff);
            Buttons resume = new Buttons(0.5, 0.5 + godMode.buttonHeight * 4, "Resume");
            Buttons laserColor = new Buttons(0.5, 0.5 + godMode.buttonHeight*2, "Laser Color");
            Buttons restart = new Buttons(0.5, 0.5 - godMode.buttonHeight * 2, "Restart Game");
            Buttons exit = new Buttons(0.5, 0.5 - godMode.buttonHeight * 4, "Exit");

            if (exit.isButtonPressed()) {
                System.exit(0);
            } else if (resume.isButtonPressed()) {
                break;
            } else if (restart.isButtonPressed()) {
                game(highscore);
            } else if (godMode.isButtonPressed() && gameData.isGodMode()){
                gameData.deToggleGodMode();
                break;
            }
            else if (godMode.isButtonPressed()){
                gameData.toggleGodMode();
                break;
            }
            else if (laserColor.isButtonPressed()){
                colorButtons();
            }
            updateScreen(10);
        }
    }

    public static void game(int highScore){
        // method responsible for running the game logic
        levelData.setDifficulty(gameData.getDifficulty()); // set game difficulty
        levelData.setLevel(1); // set starting level
        initLevel(); // initializes level
        StdDraw.enableDoubleBuffering(); // enables double buffering
        PlayerLasers[] playerLasers = new PlayerLasers[0];
        InvaderLasers[] invaderLasers = new InvaderLasers[0];
        BossLasers[] bossLasers = new BossLasers[0];
        int timePerFrame = 10; // sets frame time
        Time stopwatch = new Time(); // initialize a time object
        stopwatch.startStopwatch(); // start stopwatch
        int coolDownAfterPlayerShoots = 360; // time in milli seconds between each player bullet
        while (true) {
            if (gameData.isGodMode()){
                // If player toggles god mode, refills health each frame
                PLAYER.setHealth(100);
            }

            if (StdDraw.isKeyPressed(27)){
                // Opens start meu if esc is pressed
                startMenu(highScore);
            }
            PLAYER.moveTo(StdDraw.mouseX()); // Moves the player's paddle to x-coordinate of the mouse
            moveInvaders();
            PlayerLasers[] newPlayerLasers = new PlayerLasers[0]; // inits player lasers arr
            if (StdDraw.isKeyPressed(32) || StdDraw.isMousePressed()){
                // Shoot if Spacebar pressed or Mouse pressed. Also cooldowns
                newPlayerLasers = playerShoots(stopwatch, coolDownAfterPlayerShoots);
            }
            if (levelData.isBossLevel()) {
                // Handles boss systems in boss level
                checkBossLasersHit(bossLasers); // checks if the boss's lasers hit the player
                moveBosses(); // moves the boss
                BossLasers[] bossLasers1 = bossShoot(); // handles the boss's weapon systems
                bossLasers = bossLasersArrayMerger(bossLasers, bossLasers1); // helper method that merges arrays
                moveBossLasers(bossLasers); // moves the bossLasers
            }
            InvaderLasers[] newInvaderLasers = invaderShoot(); // makes the minion shoot
            playerLasers = playerLasersArrayMerger(playerLasers, newPlayerLasers); // helper method that merges arrays
            invaderLasers = invaderLasersArrayMerger(invaderLasers, newInvaderLasers); // helper method that merges arrays
            moveInvaderLasers(invaderLasers); // moves the lasers of the invaders
            checkInvadersLasersHit(invaderLasers); // checks if the invaders lasers hit
            movePlayerLasers(playerLasers); // moves the player lasers
            playerLasers = checkPlayerLasersHit(playerLasers); // moves the player lasers 
            removeBoss(); // removes the boss if not boss level
            displayLevel(); // displays level on screen
            displayScore(); // displays score on screen
            if (PLAYER.isDead()){
                // Terminates game if player died
                break;
            }
            levelCompletedCheck(); // checks if level is complete
            updateScreen(timePerFrame); // updates the screen (handles the stdDraw logic)
        }
        gameOver();
        highScore = prompt(highScore); // Base Condition
        game(highScore); // infinite recursive call
    }


    private static void updateScreen(int t) {
        // Updates the screen each t milliseconds
        StdDraw.show();
        StdDraw.pause(t);
        StdDraw.clear(Color.GRAY);
        StdDraw.picture(0.5, 0.5, "icons/bg.png", 1, 1);
    }
}

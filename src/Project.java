import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Project {
    static Player player = new Player(StdDraw.BOOK_BLUE);


    public static void println(String str){
        // Lazy programmer: Shortcut to print a string.
        System.out.println(str);
    }


    public static InvaderLasers[] enemyShoot(Invader[] invaders){ // Pass an array of all invaders
        // Shoots Enemy bullet and then returns an array of the recently shot bullets
        InvaderLasers[] bullet = new InvaderLasers[invaders.length];
        int counter = 0;
        if (Math.random()>0.95){ // Chance to shoot any bullet to give a grace period
            for (int i = 0; i < invaders.length; i++) {
                Invader invader = invaders[i];
                if(invader.getCoordinates()[2] < 1 && invader.getCoordinates()[2] > 0){
                    if (Math.random()>0.5){ // Chance of Invader shooting, so that not all invaders shoot all the time
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


    public static InvaderLasers[] activeInvaderLasersArrayInit(InvaderLasers[] arr){
        // Returns an array with only the active bullets. Cleaner
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getCentreY() > 0 && (!player.isHit(arr[i].getX(), arr[i].getY2()))) counter++;
        }
        InvaderLasers[] b = new InvaderLasers[counter];
        counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getCentreY() > 0 && (!player.isHit(arr[i].getX(), arr[i].getY2()))) {
                b[counter] = arr[i];
                counter++;
            }
        }
        return b;
    }


    public static InvaderLasers[] invaderLasersArrayMerger(InvaderLasers[] a, InvaderLasers[] b){
        // Merges two InvaderLasers arrays together end on front. returns the merged array
        // Main use: add the lasers recently hit in the frame to the main lasers array
        a = activeInvaderLasersArrayInit(a);
        b = activeInvaderLasersArrayInit(b);
        InvaderLasers[] z = new InvaderLasers[a.length+ b.length];
        for (int i = 0; i < a.length; i++) {
            z[i] = a[i];
        }
        for (int i = 0; i < b.length; i++) {
            z[i + a.length] = b[i];
        }
        return z;
    }

    public static PlayerLasers[] activePlayerLasersArrayInit(PlayerLasers[] arr){
        // Returns an array with only the active bullets. Cleaner
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getY2() < 1) counter++;
        }
        PlayerLasers[] b = new PlayerLasers[counter];
        counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getY2() < 1 ) {
                b[counter] = arr[i];
                counter++;
            }
        }
        return b;
    }


    public static PlayerLasers[] playerLasersArrayMerger(PlayerLasers[] a, PlayerLasers[] b){
        // Merges two InvaderLasers arrays together end on front. returns the merged array
        // Main use: add the lasers recently hit in the frame to the main lasers array
        a = activePlayerLasersArrayInit(a);
        b = activePlayerLasersArrayInit(b);
        PlayerLasers[] z = new PlayerLasers[a.length+ b.length];
        for (int i = 0; i < a.length; i++) {
            z[i] = a[i];
        }
        for (int i = 0; i < b.length; i++) {
            z[i + a.length] = b[i];
        }
        return z;
    }


    public static void moveInvaderLasers(InvaderLasers[] bullet){
        // Moves enemy bullets down with a speed of 0.01 pixels per frame
        for (int i = 0; i < bullet.length; i++) {
            if (bullet[i] == null) {
                continue;
            }
            bullet[i].move(-0.01);
        }
    }
    public static void movePlayerLasers(PlayerLasers[] bullet){
        // Moves enemy bullets down with a speed of 0.01 pixels per frame
        for (int i = 0; i < bullet.length; i++) {
            if (bullet[i] == null) {
                continue;
            }
            bullet[i].move(0.01);
        }
    }

    public static Invader[] initInvadersArray(int n){
        // Initializes the Invaders array
        Invader[] invaders = new Invader[n];
        for (int i = 0; i < invaders.length; i++) {
            double x = 0.9*Math.random();
            double y = 1-0.2*Math.random();
            invaders[i] = new Invader(x, y, Color.CYAN);
        }
        return invaders;
    }


    public static double moveInvaders(Invader[] invaders, double dy){
        // Moves the invaders and returns dy to track their speed
        double dx = 0;
        for (int i = 0; i < invaders.length ; i++) {
            if (invaders[i].hitTopWall() || invaders[i].hitBottomWall()) {dy*=-1;}
            invaders[i].move(dx,dy);
        }
        return dy;
    }


    public static void checkInvadersLasersHit(InvaderLasers[] lasers){
        // Checks if player is hit from invaders
        for (int i = 0; i < lasers.length; i++) {
            if (player.isHit(lasers[i].getX(), lasers[i].getY2())){
                player.loseHealth(-5);
            }
        }
    }


    public static PlayerLasers[] playerShoots(int keycode){
        PlayerLasers[] bullet = new PlayerLasers[1];
        bullet[0] = player.shoot(player.getCentreX(), player.getY1());
        return bullet;
    }

    public static void checkArgs(int n){
        if (n > 3 || n < 1){
            System.err.println("Wrong difficulty setting.\n"+"Difficulty can only be 1, 2, or 3");
            System.exit(0);
        }
    }

    public static Invader[] checkPlayerLasersHit(Invader[] invaders, PlayerLasers[] playerLasers){
        if (invaders.length < 1 || playerLasers.length < 1) return invaders;
        int hitInvadersIndex=0;
        int counter = 0;
        for (int invader = 0; invader < invaders.length; invader++) {
            for (int bullet = 0; bullet < playerLasers.length; bullet++) {
                if (invaders[invader].isHit(playerLasers[bullet].getX(),playerLasers[bullet].getY1())){
                    hitInvadersIndex = invader; // gives the indexes to be removed at once
                    counter++;
                }
            }
        }
        if (counter<1) return invaders;
        counter = 0;
        Invader[] invaders1 = new Invader[invaders.length-1];
        for (int i = 0; i < invaders1.length; i++) {
            if (i == hitInvadersIndex) i++;
            invaders1[counter] = invaders[i];
            counter++;
        }
        System.out.println("Original: " + Arrays.toString(invaders)+'\n');
        System.out.println(hitInvadersIndex);
        System.out.println("New: " + Arrays.toString(invaders1));
        return invaders1;
    }



    public static void main(String[] args) {
       // int difficultyIndex = Integer.parseInt(args[0]);
        //checkArgs(difficultyIndex);
        int level = 1;
        int numInvaders = 4; // Still to be determined based on level
        Invader[] invaders = initInvadersArray(numInvaders);
        StdDraw.enableDoubleBuffering();
        double invaderSpeedX = 0.0;
        double invaderSpeedY = -0.0025;
        PlayerLasers[] playerLasers = new PlayerLasers[0];
        InvaderLasers[] invaderLasers = new InvaderLasers[0];
        Level levelData = new Level(level, false);
        long start = 0; // stopwatch
        while (true){
        PlayerLasers[] newPlayerLasers = new PlayerLasers[0];
            checkInvadersLasersHit(invaderLasers);
            player.moveTo(StdDraw.mouseX()); // Moves the player's paddle to x-coordinate of the mouse
            if (levelData.levelComplete()){ //Todo: Decompose this if condition in a method
                // Changes level if level complete
                // TODO: Make level change animation
                level++;
                levelData = new Level(level, level%5==0); // Boss level each 5 rounds
                invaders = initInvadersArray(numInvaders);
                println("Zebbbbby");

            }

            invaderSpeedY = moveInvaders(invaders, invaderSpeedY);
            long end = System.currentTimeMillis();
            if (StdDraw.isKeyPressed(32)) { // Spacebar pressed
                if (end-start > 360){
                    newPlayerLasers = playerShoots(32);
                    start = System.currentTimeMillis();
                }
            }
            InvaderLasers[] newInvaderLasers = enemyShoot(invaders);

            playerLasers = playerLasersArrayMerger(playerLasers, newPlayerLasers);
            invaderLasers = invaderLasersArrayMerger(invaderLasers, newInvaderLasers);
            movePlayerLasers(playerLasers);
            moveInvaderLasers(invaderLasers);
            invaders = checkPlayerLasersHit(invaders, playerLasers);
            updateScreen(20);
        }
    }

    public static void updateScreen(int t){
        // Updates the screen each t milliseconds
        StdDraw.pause(t);
        StdDraw.show();
        StdDraw.clear();

    }
}

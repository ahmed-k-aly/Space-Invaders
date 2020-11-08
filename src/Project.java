import java.awt.*;
import java.util.Arrays;

public class Project {
    private static final Player player = new Player(StdDraw.BOOK_BLUE);

    public static void println(String str) {
        // Lazy programmer: Shortcut to print a string.
        System.out.println(str);
    }

    private static void println(int str) {
        // Lazy programmer: Shortcut to print an int.
        System.out.println(str);
    }

    private static void println(double str) {
        // Lazy programmer: Shortcut to print an int.
        System.out.println(str);
    }

    private static InvaderLasers[] enemyShoot(Invader[] invaders) { // Pass an array of all invaders
        // Shoots Enemy bullet and then returns an array of the recently shot bullets
        InvaderLasers[] bullet = new InvaderLasers[invaders.length];
        int counter = 0;
        if (Math.random() > 0.95) { // Chance to shoot any bullet to give a grace period
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

    private static InvaderLasers[] activeInvaderLasersArrayInit(InvaderLasers[] arr) {
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
        // Moves enemy bullets down with a speed of 0.01 pixels per frame
        for (int i = 0; i < bullet.length; i++) {
            if (bullet[i] == null) {
                continue;
            }
            bullet[i].move(-0.01);
        }
    }

    private static void movePlayerLasers(PlayerLasers[] bullet) {
        // Moves enemy bullets down with a speed of 0.01 pixels per frame
        for (int i = 0; i < bullet.length; i++) {
            if (bullet[i] == null) {
                continue;
            }
            bullet[i].move(0.01);
        }
    }

    private static Invader[] initInvadersArray(int n) {
        // Initializes the Invaders array
        Invader[] invaders = new Invader[n];
        for (int i = 0; i < invaders.length; i++) {
            double x = Math.random() * (0.8 - 0.2) + 0.2;
            double y = Math.random() * (0.8 - 0.5) + 0.5;
            invaders[i] = new Invader(x, y, Color.CYAN);
        }
        return invaders;
    }

    private static double moveInvaders(Invader[] invaders, double dy) {
        // Moves the invaders and returns dy to track their speed
        double dx = 0;
        for (int i = 0; i < invaders.length; i++) {
            if (invaders[i].hitTopWall() || invaders[i].hitBottomWall()) {
                dy *= -1;
            }
            invaders[i].move(dx, dy);
        }
        return dy;
    }

    private static void checkInvadersLasersHit(InvaderLasers[] lasers) {
        // Checks if player is hit from invaders
        for (int i = 0; i < lasers.length; i++) {
            if (player.isHit(lasers[i].getX(), lasers[i].getY2())) {
                player.loseHealth(-5);
            }
        }
    }


    private static PlayerLasers[] playerShoots() {
        PlayerLasers[] bullet = new PlayerLasers[1];
        bullet[0] = player.shoot(player.getCentreX(), player.getY1());
        return bullet;
    }

    private static void checkArgs(int n) {
        if (n > 3 || n < 1) {
            System.err.println("Wrong difficulty setting.\n" + "Difficulty can only be 1, 2, or 3");
            System.exit(0);
        }
    }

    private static InvadersAndPlayerLasersArrs checkPlayerLasersHit(Invader[] invaders, PlayerLasers[] playerLasers, Level levelData) {
        int hitInvadersIndex = 0;
        int hitBulletIndex = 0;
        int counter = 0;
        for (int invader = 0; invader < invaders.length; invader++) {
            for (int bullet = 0; bullet < playerLasers.length; bullet++) {
                if (invaders[invader].isHit(playerLasers[bullet].getX(), playerLasers[bullet].getY1())) {
                    hitInvadersIndex = invader; // gives the indexes to be removed at once
                    levelData.invaderKilled();
                    hitBulletIndex = bullet;
                    counter++;
                }
            }
        }
        if (counter < 1) return new InvadersAndPlayerLasersArrs(invaders, playerLasers);
        invaders = remove(invaders, hitInvadersIndex);
        playerLasers = remove(playerLasers, hitBulletIndex);
        InvadersAndPlayerLasersArrs invadersAndPlayerLasers = new InvadersAndPlayerLasersArrs(invaders, playerLasers);
        System.out.println(hitInvadersIndex);
        return invadersAndPlayerLasers;
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


    final static class InvadersAndPlayerLasersArrs {
        // Nested class used to return two different types of objects from a method
        private final Invader[] invaders;
        private final PlayerLasers[] playerLasers;

        private InvadersAndPlayerLasersArrs(Invader[] invaders, PlayerLasers[] playerLasers) {
            this.invaders = invaders;
            this.playerLasers = playerLasers;
        }

        private Invader[] getInvaders() {
            return invaders;
        }

        private PlayerLasers[] getPlayerLasers() {
            return playerLasers;
        }
    }

    public static void main(String[] args) {
        // int difficultyIndex = Integer.parseInt(args[0]);
        //checkArgs(difficultyIndex);
        int level = 1;
        Level levelData = new Level(level, false);
        int numInvaders = levelData.getNumInvaders(); // Still to be determined based on level
        Invader[] invaders = initInvadersArray(numInvaders);
        StdDraw.enableDoubleBuffering();
        double invaderSpeedX = 0.0;
        double invaderSpeedY = -0.0025;
        PlayerLasers[] playerLasers = new PlayerLasers[0];
        InvaderLasers[] invaderLasers = new InvaderLasers[0];
        long start = 0; // stopwatch
        while (true) {
            PlayerLasers[] newPlayerLasers = new PlayerLasers[0];
            checkInvadersLasersHit(invaderLasers);

            if (invaders.length > 0)
                player.moveTo(invaders[0].getX()); // Moves the player's paddle to x-coordinate of the mouse
            if (levelData.levelComplete()) { //Todo: Decompose this if condition in a method
                // Changes level if level complete
                // TODO: Make level change animation
                level++;
                levelData = new Level(level, false); // Boss level each 5 rounds
                invaders = initInvadersArray(levelData.getNumInvaders());
            }

            invaderSpeedY = moveInvaders(invaders, invaderSpeedY);
            long end = System.currentTimeMillis();
            if (StdDraw.isKeyPressed(32)) { // Spacebar pressed
                if (end - start > 360) { // Cooldown time
                    newPlayerLasers = playerShoots();
                    start = System.currentTimeMillis();
                }
            }
            InvaderLasers[] newInvaderLasers = enemyShoot(invaders);
            playerLasers = playerLasersArrayMerger(playerLasers, newPlayerLasers);
            invaderLasers = invaderLasersArrayMerger(invaderLasers, newInvaderLasers);
            movePlayerLasers(playerLasers);
            moveInvaderLasers(invaderLasers);
            InvadersAndPlayerLasersArrs invadersAndPlayerLasers = checkPlayerLasersHit(invaders, playerLasers, levelData);
            invaders = invadersAndPlayerLasers.getInvaders();
            playerLasers = invadersAndPlayerLasers.getPlayerLasers();
            updateScreen(10);
        }
    }

    private static void updateScreen(int t) {
        // Updates the screen each t milliseconds
        StdDraw.show();
        StdDraw.pause(t);
        StdDraw.clear();

    }
}

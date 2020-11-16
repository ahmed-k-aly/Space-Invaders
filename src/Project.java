import java.awt.*;

public class Project {
    private static final Player PLAYER = new Player(StdDraw.BOOK_BLUE);
    private static final Level levelData = new Level(); // Creates the Data for the level

    public static void println(String str) {
        // Lazy programmer: Shortcut to print a string.
        System.out.println(str);
    }

    private static void println(Boolean str) {
        System.out.println(str);
    }

    private static void println(int str) {
        // Lazy programmer: Shortcut to print an str.
        System.out.println(str);
    }

    private static void println(double str) {
        // Lazy programmer: Shortcut to print an int.
        System.out.println(str);
    }

    private static InvaderLasers[] invaderShoot() { // Pass an array of all invaders
        // Shoots Enemy bullet and then returns an array of the recently shot bullets
        Invader[] invaders = levelData.getInvaders();
        InvaderLasers[] bullet = new InvaderLasers[invaders.length];
        int counter = 0;
        if (Math.random() > 0.96) { // Chance to shoot any bullet to give a grace period
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
        // Initializes the Invaders array
        int numBosses = levelData.getNumBosses();
        Boss[] bosses = new Boss[numBosses];
        for (int i = 0; i < bosses.length; i++) {
            double width = 0.07;
            double x = Math.random() * ((1 - width) - (width)) + width;
            // TODO: Add a way to keep distance between bosses
            bosses[i] = new Boss(x, Color.RED);
        }
        levelData.setBosses(bosses);
    }

    private static void moveInvaders() {
        // Moves the invaders.
        Invader[] invaders = levelData.getInvaders();
        for (int i = 0; i < invaders.length; i++) {
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

    private static PlayerLasers[] playerShoots(Time stopwatch) {
        stopwatch.stopStopwatch();
        if (stopwatch.timePassed() > 360) {
            PlayerLasers[] bullet = new PlayerLasers[1];
            bullet[0] = PLAYER.shoot(PLAYER.getCentreX(), PLAYER.getY1());
            stopwatch.startStopwatch();
            return bullet;
        }
        return new PlayerLasers[0];
    }

    private static void checkArgs(int n) {
        if (n > 3 || n < 1) {
            System.err.println("Wrong difficulty setting.\n" + "Difficulty can only be 1, 2, or 3");
            System.exit(0);
        }
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
                    if (bosses[boss].isDead()) levelData.bossKilled();
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
        if (levelData.levelComplete(PLAYER)) {
            // Changes level if level complete
            // TODO: Make level change animation
            levelData.setLevel(levelData.getLevel() + 1);
            initLevel();
            PLAYER.boostHealth(10);
        }
    }


    private static Boss[] remove(Boss[] arr, int index) {
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
            if (bosses[i].isDead()) bosses1 = remove(bosses, i);
        }
        levelData.setBosses(bosses1);
    }


    public static void initLevel() {
        levelData.setNumInvaders();
        levelData.setNumBosses();
        initInvadersArray(); // Initializes invaders array in Level class
        initBossesArray();
    }

    public static void main(String[] args) {
        int difficultyIndex = Integer.parseInt(args[0]);
        checkArgs(difficultyIndex);
        levelData.setLevel(1);
        initLevel();
        StdDraw.enableDoubleBuffering();
        PlayerLasers[] playerLasers = new PlayerLasers[0];
        InvaderLasers[] invaderLasers = new InvaderLasers[0];
        BossLasers[] bossLasers = new BossLasers[0];
        Time stopwatch = new Time();
        stopwatch.startStopwatch();
        boolean flag = true;
        while (true) {
            PLAYER.moveTo(StdDraw.mouseX()); // Moves the player's paddle to x-coordinate of the mouse
            PlayerLasers[] newPlayerLasers = new PlayerLasers[0];
            moveInvaders();
            if (StdDraw.isKeyPressed(32) || StdDraw.isMousePressed())// Shoot if Spacebar pressed or Mouse pressed. Also cooldowns
            {
                newPlayerLasers = playerShoots(stopwatch);
            }
            if (levelData.isBossLevel()) {
                checkBossLasersHit(bossLasers);
                moveBosses();
                BossLasers[] bossLasers1 = bossShoot();
                bossLasers = bossLasersArrayMerger(bossLasers, bossLasers1);
                moveBossLasers(bossLasers);
            }
            InvaderLasers[] newInvaderLasers = invaderShoot();
            playerLasers = playerLasersArrayMerger(playerLasers, newPlayerLasers);
            invaderLasers = invaderLasersArrayMerger(invaderLasers, newInvaderLasers);
            moveInvaderLasers(invaderLasers);
            checkInvadersLasersHit(invaderLasers);
            playerLasers = checkPlayerLasersHit(playerLasers);
            movePlayerLasers(playerLasers);
            levelCompletedCheck();
            removeBoss();
            updateScreen(10, flag);
        }
    }

    private static void updateScreen(int t, boolean flag) {
        // Updates the screen each t milliseconds
        StdDraw.show();
        StdDraw.pause(t);
        StdDraw.clear();
        StdDraw.picture(0.5, 0.5, "icons/bg.png", 1, 1);
    }
}

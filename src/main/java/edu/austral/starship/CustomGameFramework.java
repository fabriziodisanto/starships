package edu.austral.starship;

import edu.austral.starship.base.collision.CollisionEngine;
import edu.austral.starship.base.collision.Collisionable;
import edu.austral.starship.base.framework.GameFramework;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.framework.WindowSettings;
import edu.austral.starship.controllers.AsteroidController;
import edu.austral.starship.controllers.BulletController;
import edu.austral.starship.controllers.StarshipController;
import edu.austral.starship.models.Starship;
import edu.austral.starship.models.guns.AutomaticGun;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.event.KeyEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CustomGameFramework implements GameFramework {
    private StarshipController starshipController;
    private AsteroidController asteroidController;
    private BulletController bulletController;
    private PImage background;
    private List<Collisionable> collisionableList;
    private CollisionEngine collisionEngine;
    private int playerNumber;
    private int[] playersUp;
    private int[] playersDown;
    private int[] playersLeft;
    private int[] playersRight;
    private int[] playersShot;
    private int[] playersChange;
    private int MAXHEIGHT;
    private int MAXWIDTH;
    private int level = 1;
    private int cyclesInThisLevel = 0;

    public static String getFileContent(FileInputStream fis, String encoding) throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new InputStreamReader(fis, encoding))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            return sb.toString();
        }
    }

    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        try {
            this.collisionEngine = new CollisionEngine();
            // two player configuration
            FileInputStream fis = new FileInputStream("src/main/resources/2Pconfiguration");
            // one player configuration
//            FileInputStream fis = new FileInputStream("src/main/resources/1Pconfiguration");
            String data = getFileContent(fis, "UTF-8");
            fis.close();
            String[] config = data.split(",");
            MAXHEIGHT = Integer.valueOf(config[0]);
            MAXWIDTH = Integer.valueOf(config[1]);
            playerNumber = Integer.valueOf(config[2]);
            playersUp = new int[playerNumber];
            playersDown = new int[playerNumber];
            playersLeft = new int[playerNumber];
            playersRight = new int[playerNumber];
            playersShot = new int[playerNumber];
            playersChange = new int[playerNumber];
            ArrayList<Starship> starships = new ArrayList<>(playerNumber);
            for (int i = 0; i < playerNumber; i++) {
                String playerName = config[(3 + i*7)];
                playersUp[i] = (int) config[(4 + i*7)].charAt(0);
                playersDown[i] = (int) config[(5 + i*7)].charAt(0);
                playersLeft[i] = (int) config[(6 + i*7)].charAt(0);
                playersRight[i] = (int) config[(7 + i*7)].charAt(0);
                playersShot[i] = (int) config[(8 + i*7)].charAt(0);
                playersChange[i] = (int) config[(9 + i*7)].charAt(0);
                starships.add(new Starship(new AutomaticGun(), 250 + i*100, 250 + i*100, playerName));
            }
            windowsSettings.setSize(MAXHEIGHT, MAXWIDTH);
            createControllers(starships);
            loadImages(imageLoader);
            createCollisionables();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createCollisionables() {
        collisionableList = new ArrayList<>();
        collisionableList.addAll(starshipController.getStarships());
        collisionableList.addAll(bulletController.getBulletList());
        collisionableList.addAll(asteroidController.getAsteroidList());
    }

    private void createControllers(ArrayList<Starship> starships) {
        this.starshipController = new StarshipController(starships, MAXHEIGHT, MAXWIDTH);
        this.asteroidController = new AsteroidController(MAXHEIGHT, MAXWIDTH);
        this.bulletController = starshipController.getBulletController();
    }

    private void loadImages(ImageLoader imageLoader) {
        PImage asteroid = imageLoader.load("src/main/resources/Asteroid.png");
        PImage starship = imageLoader.load("src/main/resources/starship.png");
        PImage[] bullets = new PImage[3];
        bullets[0] = imageLoader.load("src/main/resources/normalBullet.png");
        bullets[1] = imageLoader.load("src/main/resources/multiBullet.png");
        bullets[2] = imageLoader.load("src/main/resources/powerBullet.png");
        asteroidController.getAsteroidView().setpImage(asteroid);
        starshipController.getStarshipView().setpImage(starship);
        bulletController.getBulletView().setpImageList(bullets);
        background = imageLoader.load("src/main/resources/Galaxy.jpg");
        background.resize(MAXHEIGHT, MAXWIDTH);
    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        checkIfMoves(keySet);
        graphics.background(background);
        checkCollisions();
        starshipControllerMethods(graphics, timeSinceLastDraw);
        calcIfAsteroidIsGenerated();
        asteroidControllerMethods(graphics, timeSinceLastDraw);
        bulletControllerMethod(graphics, timeSinceLastDraw);
        drawHud(graphics);
        checkEndGame(graphics);
        if(cyclesInThisLevel == 300){
            level++;
            cyclesInThisLevel = 0;
        }
        cyclesInThisLevel++;
    }

    private void checkEndGame(PGraphics graphics) {
        if(starshipController.endGame()){
            System.out.println("LEVEL REACHED: " + level);
            for (Starship starship : starshipController.getStarships()) {
                System.out.println("-----------------------------------------");
                System.out.println("PLAYER: " + starship.getName());
                System.out.println("POINTS: " + starship.getPoints());
                System.out.println("-----------------------------------------");
            }
            graphics.dispose();
            System.exit(0);
        }
    }

    private void checkIfMoves(Set<Integer> keySet) {
        for (int i = 0; i < playerNumber; i++) {
            if (keySet.contains(playersUp[i])) {
                starshipController.moveForward(i);
            }
            if (keySet.contains(playersLeft[i])) {
                starshipController.rotateLeft(i);
            }
            if (keySet.contains(playersDown[i])){
                starshipController.breakSpeed(i);
            }
            if (keySet.contains(playersRight[i])) {
                starshipController.rotateRight(i);
            }
        }
    }

    private void checkCollisions() {
        collisionEngine.checkCollisions(collisionableList);
        collisionableList.clear();
        collisionableList.addAll(starshipController.getStarships());
        collisionableList.addAll(bulletController.getBulletList());
        collisionableList.addAll(asteroidController.getAsteroidList());
    }

    private void drawHud(PGraphics graphics) {
        int i = 1;
        for (Starship starship : starshipController.getStarships()) {
            graphics.text("PLAYER: " + starship.getName() + "\nPOINTS:" + starship.getPoints() + "\nLIVES:" + starship.getLives() , 20 + (i-1) * 100, MAXHEIGHT - 30);
            i++;
        }
    }

    private void bulletControllerMethod(PGraphics graphics, float timeSinceLastDraw) {
        bulletController.update(timeSinceLastDraw/100);
        bulletController.getBulletView().draw(graphics);
    }

    private void asteroidControllerMethods(PGraphics graphics, float timeSinceLastDraw) {
        asteroidController.update(timeSinceLastDraw/100);
        asteroidController.getAsteroidView().draw(graphics);
    }

    private void calcIfAsteroidIsGenerated() {
        if(Math.random() < 0.05 * level){
            asteroidController.generateRandomAsteroid();
        }
    }

    private void starshipControllerMethods(PGraphics graphics, float timeSinceLastDraw) {
        starshipController.sumBulletPoints();
        starshipController.move(timeSinceLastDraw/100);
        starshipController.bounceAgainstWall();
        starshipController.getStarshipView().draw(graphics);
    }

    @Override
    public void keyPressed(KeyEvent event) {
    }

    @Override
    public void keyReleased(KeyEvent event) {
        for (int i = 0; i < playerNumber; i++) {
            if (event.getKeyCode() == playersShot[i]) {
                starshipController.shotBullets(i);
            }
            if (event.getKeyCode() == playersChange[i]) {
                starshipController.changeWeapon(i);
            }
        }
    }
}

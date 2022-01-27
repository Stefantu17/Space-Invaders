import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;
import java.util.Arrays;

public class Sketch1 extends PApplet {

  /** 
  * Game design
  * @author Stefan. T
  */

  // Global variables
  public PImage ship;
  public PImage alien;
  public PImage alienUp;
  public PFont font; 
  public int seconds;
  public int score = 0;
  public int lives = 3;
  public float shipX = 275;
  public float bulletX;
  public float bulletY = 490;
  public float xDistance = 0;
  public float yDistance = 0;
  public float moving = 0;
  public float alienBulletX;
  public float alienBulletY;
  public float[][][] alienArmy = new float[3][5][2];
  public boolean left;
  public boolean right = true;
  public boolean reset = false;
  public boolean rightPressed = false;
  public boolean leftPressed = false;
  public boolean spacePressed = false;
  public boolean bulletActive = false;
  public boolean alienBulletActive = false;

  public void settings() {

    // Application settings
    size(600, 600);
  }

  public void setup() {

    // Set background to black
    background(0);

    // Font settings
    font = createFont("Comic Sans MS", 30, true);
    textFont(font);

    // Load images
    ship = loadImage("../assets/ship.png");
    ship.resize(50,50);
    alien = loadImage("../assets/alien.png");
    alien.resize(50,50);
    alienUp = loadImage("../assets/alienUp.png");
    alienUp.resize(50,50);

    // Set x-coordinate for each alien
    for (int i = 0; i < alienArmy.length; i++) {
      for (int j = 0; j < alienArmy[i].length; j++) {
        alienArmy[i][j][0] = 75 + xDistance;
        xDistance += 100;
      } 
      xDistance = 0;
    }

    // Set y-coordinate for each alien
    for (int i = 0; i < alienArmy.length; i++) {
      for (int j = 0; j < alienArmy[i].length; j++) {
        alienArmy[i][j][1] = 150 + yDistance;
      } 
      yDistance += 100;
    }
  }

  public void draw() {

    // Redraw background as black
    background(0);

    // Score and lives counter
    fill(255);
    text("Score: " + score, 35, 50);
    text("Lives: " + lives, 450, 50);

    // Timer
    seconds = second();

    // Draw Ship
    image(ship, shipX, 500);

    // Input logic
    if (leftPressed == true && shipX >= 20) {
      shipX -= 5;
    }
    if (rightPressed == true && shipX <= 520) {
      shipX += 5;
    }
    if (spacePressed == true && bulletY == 490) {
      bulletX = shipX + 19;
      bulletActive = true;
    }

    // Draw bullet and logic
    if (bulletActive == true) {
      bullet();
      if (bulletY <= -10) {
        bulletY = 490;
        bulletActive = false;
      }
    }

    // Draw alien bullet and logic
    if (alienBulletActive == true) {
      alienBullet();
      if (alienBulletX >= shipX && alienBulletX <= shipX + 50 && alienBulletY >= 500) {
        lives--;
        alienBulletActive = false;
      }
      else if (alienBulletY >= 610) {
        alienBulletActive = false;
      }
    }

    // Draw Aliens
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 5; j++) {
        if (i == 0 || i == 2) {
          // Check if bullet hit alien
          if ((bulletX >= alienArmy[i][j][0] + moving && bulletX <= alienArmy[i][j][0] + 50 + moving) && (bulletY == alienArmy[i][j][1] + 50)) {
            alienArmy[i][j][0] = -50;
            alienArmy[i][j][1] = -50;
            // Randomize counterattack
            if ((int)random(2) == 1) {
              alienBulletX = bulletX;
              alienBulletY = bulletY;
              alienBulletActive = true;
            }
            bulletY -= 1000;
            score += 10;
          }
          else {
            // Alien waving animation and movement
            if (seconds % 2 == 0) {
              image(alien, alienArmy[i][j][0]  + moving, alienArmy[i][j][1]);
            }
            else {
              image(alienUp, alienArmy[i][j][0]  + moving, alienArmy[i][j][1]);
            }
          }
        }
        else {
          // Check if bullet hit alien
          if ((bulletX >= alienArmy[i][j][0] - moving && bulletX <= alienArmy[i][j][0] + 50 - moving) && (bulletY == alienArmy[i][j][1] + 50)) {
            alienArmy[i][j][0] = -50;
            alienArmy[i][j][1] = -50;
            // Randomize Counterattack
            if ((int)random(2) == 1) {
              alienBulletX = bulletX;
              alienBulletY = bulletY;
              alienBulletActive = true;
            }
            bulletY -= 1000;
            score += 10;
  
          }
          else {
            // Alien waving animation and movement
            if (seconds % 2 == 0) {
              image(alien, alienArmy[i][j][0] - moving, alienArmy[i][j][1]);
            }
            else {
              image(alienUp, alienArmy[i][j][0] - moving, alienArmy[i][j][1]);
            }
          }
        }
      }
      // Alien boundarys 
      if (moving == 25){      
        left = true;
        right = false;
      }
      else if (moving == -25) {
        right = true;
        left = false;
      }
      if (right == true) {
        moving += 0.25;
      }
      else if (left == true) {
        moving -= 0.25;
      }
    }

    // Check if all aliens have been eliminated 
    if ((Arrays.deepEquals(alienArmy[0], alienArmy[1])) && (Arrays.deepEquals(alienArmy[1], alienArmy[2]))) {
      // Respawn and reset
      respawn();
      bulletY -= 1000;
      score += 40;
    }

    // Death checker
    if (lives == 0) {
      exit();
    }
  }

  public void keyPressed() {
    
    // Move left
    if (keyCode == LEFT || key == 'a') {
      leftPressed = true;
    }
    // Move right
    if (keyCode == RIGHT || key == 'd') {
      rightPressed = true;
    }
    // Fire bullet
    if (key == ' ') {
      spacePressed = true;
    }
  }

  public void keyReleased() {

    if (keyCode == LEFT || key == 'a') {
      leftPressed = false;
    }
    if (keyCode == RIGHT || key == 'd') {
      rightPressed = false;
    }
    if (key == ' ') {
      spacePressed = false;
    }
  }

  // Draw and move bullet up
  public void bullet() {
    fill(255);
    rect(bulletX, bulletY, 10, 10);
    bulletY -= 5;
  }

  // Draw and move alien bullet down
  public void alienBullet() {
    fill(255, 0, 0);
    rect(alienBulletX, alienBulletY, 10, 10);
    alienBulletY += 3;
  }

  // Reset all alien coordinates to default
  public void respawn() {
    xDistance = 0;
    yDistance = 0;
    for (int i = 0; i < alienArmy.length; i++) {
      for (int j = 0; j < alienArmy[i].length; j++) {
        alienArmy[i][j][0] = 75 + xDistance;
        xDistance += 100;
      } 
      xDistance = 0;
    }

    for (int i = 0; i < alienArmy.length; i++) {
      for (int j = 0; j < alienArmy[i].length; j++) {
        alienArmy[i][j][1] = 150 + yDistance;
      } 
      yDistance += 100;
    }
  }
}

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;
import java.util.Arrays;

public class Sketch1 extends PApplet {

  public PImage ship;
  public PImage alien;
  public PImage alienUp;
  public float shipX = 275;
  public float[][][] alienArmy = new float[3][5][2];
  public boolean bulletActive = false;
  public boolean alienBulletActive = false;
  public float bulletX;
  public float bulletY = 490;
  public float xDistance = 0;
  public float yDistance = 0;
  public float moving = 0;
  public boolean left;
  public boolean right = true;
  public boolean reset = false;
  public int s;
  public PFont font; 
  public int score;
  public float alienBulletX;
  public float alienBulletY;
  public int lives = 3;

  public void settings() {
    size(600, 600);
  }

  public void setup() {
    background(0);
    ship = loadImage("../assets/ship.png");
    ship.resize(50,50);
    alien = loadImage("../assets/alien.png");
    alien.resize(50,50);
    alienUp = loadImage("../assets/alienUp.png");
    alienUp.resize(50,50);

    font = createFont("Comic Sans MS", 30, true);
    textFont(font);

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

  public void draw() {

    // Redraw Background
    background(0);

    // Score
    fill(255);
    text("Score: " + score, 35, 50);
    text("Lives: " + lives, 450, 50);

    // Timer
    s = second();

    // Draw Ship
    image(ship, shipX, 500);

    // Draw Bullet
    if (bulletActive == true) {
      bullet();
      if (bulletY <= -10) {
        bulletY = 490;
        bulletActive = false;
      }
    }

    if (alienBulletActive == true) {
      alienBullet();
      if (alienBulletX >= shipX && alienBulletX <= shipX + 50 && alienBulletY == 550) {
        lives--;
        alienBulletActive = false;
      }
      else if (alienBulletY >= 610) {
        alienBulletActive = false;
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 5; j++) {
        if (i == 0 || i == 2) {
          if ((bulletX >= alienArmy[i][j][0] + moving && bulletX <= alienArmy[i][j][0] + 50 + moving) && (bulletY == alienArmy[i][j][1])) {
            alienArmy[i][j][0] = -50;
            alienArmy[i][j][1] = -50;
            if ((int)random(2) == 1) {
              alienBulletX = bulletX;
              alienBulletY = bulletY;
              alienBulletActive = true;
            }
            bulletY -= 1000;
            score += 10;
          }
          else {
            if (s % 2 == 0) {
              image(alien, alienArmy[i][j][0]  + moving, alienArmy[i][j][1]);
            }
            else {
              image(alienUp, alienArmy[i][j][0]  + moving, alienArmy[i][j][1]);
            }
          }
        }
        else {
          if ((bulletX >= alienArmy[i][j][0] - moving && bulletX <= alienArmy[i][j][0] + 50 - moving) && (bulletY == alienArmy[i][j][1])) {
            alienArmy[i][j][0] = -50;
            alienArmy[i][j][1] = -50;
            if ((int)random(2) == 1) {
              alienBulletX = bulletX;
              alienBulletY = bulletY;
              alienBulletActive = true;
            }
            bulletY -= 1000;
            score += 10;
  
          }
          else {
            if (s % 2 == 0) {
              image(alien, alienArmy[i][j][0] - moving, alienArmy[i][j][1]);
            }
            else {
              image(alienUp, alienArmy[i][j][0] - moving, alienArmy[i][j][1]);
            }
          }
        }
      }
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
    if ((Arrays.deepEquals(alienArmy[0], alienArmy[1])) && (Arrays.deepEquals(alienArmy[1], alienArmy[2]))) {
      respawn();
      bulletY -= 1000;
      score += 40;
    }
    if (lives == 0) {
      exit();
    }
  }

  public void keyPressed() {
    
    if (keyCode == LEFT) {
      if (shipX >= 20) {
        shipX -= 5;
      }
    }

    if (keyCode == RIGHT) {
      if (shipX <= 520) {
        shipX += 5;
      }
    }

    if (key == ' ' && bulletY == 490) {
      bulletX = shipX + 19;
      bulletActive = true;
    }
  }

  public void bullet() {
    fill(255);
    rect(bulletX, bulletY, 10, 10);
    bulletY -= 5;
  }

  public void alienBullet() {
    fill(255, 0, 0);
    rect(alienBulletX, alienBulletY, 10, 10);
    alienBulletY += 3;
  }

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

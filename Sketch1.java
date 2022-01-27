import processing.core.PApplet;
import processing.core.PImage;

public class Sketch1 extends PApplet {

  public PImage ship;
  public PImage alien;
  public float shipX = 300;
  public float[][][] alienArmy = new float[3][5][2];
  public boolean bulletActive = false;
  public float bulletX;
  public float bulletY = 490;
  public float xDistance = 0;
  public float yDistance = 0;
  public float moving = 0;
  public boolean left;
  public boolean right = true;

  public void settings() {
    size(600, 600);
  }

  public void setup() {
    background(0);
    ship = loadImage("../assets/ship.png");
    ship.resize(50,50);
    alien = loadImage("../assets/alien.png");
    alien.resize(50,50);

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

    // Draw Ship
    image(ship, shipX, 500);

    // Draw Bullet
    if (bulletActive == true) {
      bullet();
      if (bulletY == -10) {
        bulletY = 490;
        bulletActive = false;
      }
    }

    for (float[][] row : alienArmy) {
      for (float[] column : row) {
        image(alien, column[0]  + moving, column[1]);
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
}

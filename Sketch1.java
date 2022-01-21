import processing.core.PApplet;
import processing.core.PImage;

public class Sketch1 extends PApplet {

  public PImage ship;
  public float shipX = 300;
  public boolean bulletActive = false;
  public float bulletX;
  public float bulletY = 490;

  public void settings() {
    size(600, 600);
  }

  public void setup() {
    background(0);
    ship = loadImage("../assets/ship.png");
    ship.resize(50,50);
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

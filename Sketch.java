import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;
import java.util.Arrays;

public class Sketch extends PApplet {

  /** 
  * Space Invasion
  * @author Stefan. T & John. M
  */

  // Global variables
  public PImage ship;
  public PImage alien;
  public PImage alienUp;
  public PFont font;
  public PImage background;
  public PImage play; 
  public PImage quit;
  public PImage spaceinvasion;
  public PImage credits; 
  public int seconds;
  public int score;
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
  public boolean onPlay = false;
  public boolean onExit = false;

  /** 
  * Application size
  *
  * @param None
  * @return void
  *
  */
  public void settings() {
  
    // Application settings
    size(600, 600);
  }

  /** 
  * Set up images and game elements
  *
  * @param None
  * @return void
  *
  */
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
    background = loadImage("../assets/background.jpg");
    background.resize(600, 600);
    play = loadImage("../assets/playbutton.png");
    play.resize(75, 75);
    quit = loadImage("../assets/powerbutton.png");
    quit.resize(75, 75);
    spaceinvasion = loadImage("../assets/spaceinvasion.png");
    spaceinvasion.resize(200, 150);
    credits = loadImage("../assets/credits.png");
    credits.resize(500, 25);

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

  /** 
  * Draws game to the screen, runs repetitively
  *
  * @param None
  * @return void
  *
  */
  public void draw() {

    // Menu
    if (onPlay == false) {
      
      // Set background and add credits
      image(background, 0, 0);
      image(credits, 5, 550);
      
      // Play button
      fill(255, 255, 255);
      rect(325,150,200,100); 
      image(play, 390, 165);

      // Game title
      fill(255, 255, 255);
      ellipse(100,100,350,350);
      image(spaceinvasion, 25, 50);
      
      // Exit button
      fill(255, 255, 255);
      rect(325,300,200,100);
      image(quit, 387, 313);
    }
  
    // Game
    if (onPlay == true) {

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

      // Input commands
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
        if (alienBulletX >= shipX && alienBulletX <= shipX + 50 && alienBulletY >= 500 && alienBulletY <= 525) {
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
              // Set specific alien off screen
              alienArmy[i][j][0] = -50;
              alienArmy[i][j][1] = -50;
              // Randomize counterattack
              if ((int)random(2) == 1) {
                alienBulletX = bulletX;
                alienBulletY = bulletY;
                alienBulletActive = true;
              }
              // Reset, add score
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
              // Set specific alien off screen
              alienArmy[i][j][0] = -50;
              alienArmy[i][j][1] = -50;
              // Randomize Counterattack
              if ((int)random(2) == 1) {
                alienBulletX = bulletX;
                alienBulletY = bulletY;
                alienBulletActive = true;
              }
              // Reset, add score
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

      // Quit program once all lives lost
      if (lives == 0) {
        exit();
      }
    }
  }

  /** 
  * Activates movement and bullet spawn when activated by keypress
  *
  * @param None
  * @return void
  *
  */
  public void keyPressed() {
    
    // Move left key pressed
    if (keyCode == LEFT || key == 'a') {
      leftPressed = true;
    }
    // Move right key pressed
    if (keyCode == RIGHT || key == 'd') {
      rightPressed = true;
    }
    // Space button key pressed
    if (key == ' ') {
      spacePressed = true;
    }
  }

  /** 
  * Stops movement and allows for other keys to be pressed simultaneously, works with the method above
  *
  * @param None
  * @return void
  *
  */
  public void keyReleased() {

    // Move left key released
    if (keyCode == LEFT || key == 'a') {
      leftPressed = false;
    }
    // Move right key released
    if (keyCode == RIGHT || key == 'd') {
      rightPressed = false;
    }
    // Space button key released
    if (key == ' ') {
      spacePressed = false;
    }
  }

  /** 
  * Checks for mouse input and activates buttons based on position of mouseX and mouseY
  *
  * @param None
  * @return void
  *
  */
  public void mousePressed() {

    // Play button clicked
    if ((mouseX >= 325 && mouseX <= 525) && (mouseY >= 150 && mouseY <= 250)){
      onPlay = true;
    }
    // Exit button clicked
    if (onPlay == false) {
      if ((mouseX >= 325 && mouseX <= 525) && (mouseY >= 300 && mouseY <= 400)){
        exit();
      }   
    } 
  }

  /** 
  * Creates and launches bullet on command, works with loop in draw() method to be run continuously
  *
  * @param None
  * @return void
  *
  */
  public void bullet() {

    fill(255);
    rect(bulletX, bulletY, 10, 10);
    bulletY -= 5;
  }

  /** 
  * Creates and launches alien bullet on death (subject to chance), works with loop in draw() method to be run continuously
  *
  * @param None
  * @return void
  *
  */
  public void alienBullet() {

    fill(255, 0, 0);
    rect(alienBulletX, alienBulletY, 10, 10);
    alienBulletY += 3;
  }

  /** 
  * Resets all alien coordinates once army has been wiped out
  *
  * @param None
  * @return void
  *
  */
  public void respawn() {

    // Reset spacing
    xDistance = 0;
    yDistance = 0;

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
}
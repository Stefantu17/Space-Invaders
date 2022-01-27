import processing.core.PApplet;
import processing.core.PImage;

public class Sketch2 extends PApplet {
  // setting image variables
  PImage background;
  
  PImage play; 
  PImage quit;
  PImage spaceinvasion;
  PImage credits;
 

  // rectangle variables
  public boolean onPlay = false;
  public boolean onExit = false;
  
  
  public void settings() {
    size(600, 600);
  }

  public void setup(){
    // loading and resizing images
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
  }
  
  public void draw() {  
    // background and credits 

    background(87);

    fill(255, 255, 255);
    rect(110, 150, 200, 100);

    image(background, 0, 0);
    image(credits, 5, 550);
    
    // play button
    fill(255, 255, 255);
    rect(325,150,200,100); 
    image(play, 390, 165);

    // game logo
    fill(255, 255, 255);
    ellipse(100,100,350,350);
    image(spaceinvasion, 25, 50);
    
    // exit button
    fill(255, 255, 255);
    rect(325,300,200,100);
    image(quit, 387, 313);  
  }

  public void mousePressed() {
    // play button press
    if ((mouseX >= 325 && mouseX <= 525) && (mouseY >= 150 && mouseY <= 250)){
      System.out.println("hello world");
      onPlay = true;
    }
    
    // exit button press
    if ((mouseX >= 325 && mouseX <= 525) && (mouseY >= 300 && mouseY <= 400)){
      exit();

    }   
  }
}

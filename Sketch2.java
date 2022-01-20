import processing.core.PApplet;
import processing.core.PImage;

public class Sketch2 extends PApplet {
  PImage background;
  PImage settings;
  PImage play; 

  public void settings() {
    size(600, 600);
  }

  public void setup(){
  background = loadImage("../assets/background.jpg");
  background.resize(600, 600);
  settings = loadImage("../assets/settings.png");
  settings.resize(75, 75);
  play = loadImage("../assets/playbutton.png");
  play.resize(75, 75);
  }
  
  public void draw() {   
    image(background, 0, 0);
    
    fill(255, 255, 255);
    rect(325,150,200,100); 
    image(play, 390, 165);

    fill(255, 255, 255);
    ellipse(100,100,350,350);
    
    fill(255, 255, 255);
    rect(325,275,200,100);
    image(settings, 387, 286); 
    
    fill(255, 255, 255);
    rect(325,400,200,100);
  }
}
import processing.core.PApplet;
import processing.core.PImage;

public class Sketch2 extends PApplet {
  PImage Background;
  PImage Settings; 

  public void settings() {
    size(1200, 900);
  }

  public void setup(){
   Background = loadImage("../assets/CPTBackground.jpg");
   Settings = loadImage("../assets/settings.png");
  }
  
  public void draw() {   
    image(Background, 0, 0);
    
    fill(30, 166, 37);
    rect(800,600,300,200); 
    image(Settings, 600, 450); 
    
    fill(38, 38, 36);
    ellipse(250,250,750,750);
    
    fill(30, 166, 37);
    rect(800,100,300,200);
    
    fill(30, 166, 37);
    rect(800,350,300,200);
  }
}
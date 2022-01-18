import processing.core.PApplet;
import processing.core.PImage;

public class Sketch2 extends PApplet {
PImage Background;
PImage Settings; 

  boolean background = true;
  

  public void settings() {
	  // put your size call here
    size(1200, 900);
  }

  
  public void setup(){
    
  }
  
  public void draw() {   
    size(1200,900);
    Background = loadImage("../assets/CPTBackground.jpg");
    
    background(0);
    image(Background, 0, 0);
    
    fill(38, 38, 36);
    ellipse(250,250,750,750);
    
    
    fill(30, 166, 37);
    rect(800,100,300,200);
    
    fill(30, 166, 37);
    rect(800,350,300,200);
    
    // settings button
    size(300,300);
    Settings = loadImage("../assets/126472.png");
    

    fill(30, 166, 37);
    rect(800,600,300,200);
    
  }

boolean isMouseOver(int x, int y, int w, int h){
  if(mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y +h){
    return true;
  }
  return false;
}

public void mousePressed(){
  if(isMouseOver(100,100,400,400) == true){
    background = !background;
  }
}
}
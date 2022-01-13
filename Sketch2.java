import processing.core.PApplet;
import processing.core.PImage;

public class Sketch2 extends PApplet {
PImage Background;

  boolean background = true;

  public void settings() {
	  // put your size call here
    size(1200, 900);
  }

  
  public void setup(){
    size(1200,900);
    Background = loadImage("../assets/CPTBackground.jpg");
  }
  
  public void draw() {   
    ellipse(100,100,400,400);
    
    rect(300,250,150,50);
    rect(300,325,150,50);
    rect(300,400,150,50);

    background(0);
    image(Background, 0, 0);
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
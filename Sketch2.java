import processing.core.PApplet;
import processing.core.PImage;

public class Sketch2 extends PApplet {
  // setting image variables
  PImage background;
  PImage settings;
  PImage play; 
  PImage quit;
  PImage spaceinvasion;
  PImage credits;
  PImage settingsexit;

  // rectangle variables
  public boolean onPlay = false;
  public boolean onSettings = false;
  public boolean onExit = false;
  
  public void settings() {
    size(600, 600);
  }

  public void setup(){
    // loading and resizing images
    background = loadImage("../assets/background.jpg");
    background.resize(600, 600);
    settings = loadImage("../assets/settings.png");
    settings.resize(75, 75);
    play = loadImage("../assets/playbutton.png");
    play.resize(75, 75);
    quit = loadImage("../assets/powerbutton.png");
    quit.resize(75, 75);
    spaceinvasion = loadImage("../assets/spaceinvasion.png");
    spaceinvasion.resize(200, 150);
    credits = loadImage("../assets/credits.png");
    credits.resize(500, 25);
    settingsexit = loadImage("../assets/settingsexit.png");
    settingsexit.resize(50,50);
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

    // settings button
    fill(255, 255, 255);
    rect(325,275,200,100);
    image(settings, 387, 286); 
    
    // exit button
    fill(255, 255, 255);
    rect(325,400,200,100);
    image(quit, 387, 415);

    
  }

  public void mousePressed() {
    // play button press
    if ((mouseX >= 325 && mouseX <= 200) && (mouseY >= 150 && mouseY <= 100)){
      onPlay = true;
    }

    // settings button press
    if ((mouseX >= 325 && mouseX <= 525) && (mouseY >= 275 && mouseY <= 375)){
      background(87, 87, 87);

      fill(255, 255, 255);
      rect(25, 500, 125, 75);
      image(settingsexit, 60, 515);
      
      fill(255, 255, 255);
      rect(75, 75, 200, 50);

      fill(255, 255, 255);
      rect(75, 175, 200, 50);

      fill(255, 255, 255);
      rect(75, 275, 200, 50);

      fill(255, 255, 255);
      rect(75, 375, 200, 50);

      redraw();
      noLoop();
    }
    
      // exit button press
    if ((mouseX >= 325 && mouseX <= 525) && (mouseY >= 400 && mouseY <= 500)){
      exit();

    }
    // settingsexit button press
    if ((mouseX >= 25 && mouseX <= 150) && (mouseY >= 500 && mouseY <= 575)){
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

    // settings button
    fill(255, 255, 255);
    rect(325,275,200,100);
    image(settings, 387, 286); 
    
    // exit button
    fill(255, 255, 255);
    rect(325,400,200,100);
    image(quit, 387, 415);
      
    redraw();
    loop();

    
    
    }
  }
}
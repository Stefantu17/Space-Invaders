import processing.core.PApplet;

/**
 * "ICS3U CPT Fabroa Semester 1"
 * @author: Stefan. T & John. M
 *	
 * Date: 01/29/2022
 */

class Main {
  public static void main(String[] args) {
    
    String[] processingArgs = {"MySketch"};
	  // Sketch mySketch = new Sketch();
	  Sketch1 mySketch = new Sketch1();
	  // Sketch2 mySketch = new Sketch2();
	  
	  PApplet.runSketch(processingArgs, mySketch);
  }
}

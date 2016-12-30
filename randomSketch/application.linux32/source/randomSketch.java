import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class randomSketch extends PApplet {

public void setup() {
  
  for (int i = 0; i < 10000; i++){
    float x = random(width);
    float y = random(height);
    float r = random (100,255);
    float g = random (100,255);
    float b = random(100,255);
    
    noStroke();
    fill(r,g,b,100);
    ellipse(x,y,16,16);
  }
  save("output.png");
  exit();
}
  public void settings() {  size(640, 360); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "randomSketch" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

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

public void setup(){
  
  noFill();
  stroke(255);
  strokeWeight(2);
}

public void draw(){
  background(0);
  translate(width/2,height/2);
  beginShape();
  
  int layers = floor(random(3,10));
  
  for (int layer = 0; layer < layers; ++layer){
    
    float n1 = random(-100,100);
    float n2 = random(0,100);
    float n3 = random(0,1000);
    float m = random(0,10);
    float t = random(0,2*PI);
    float a = cos(t);
    float b = sin(t);
  
    for(float theta = 0; theta < 2 * PI; theta += 0.1f){
        float rad = superFormula(theta,a,b,m,n1,n2,n3);
        float x = rad * cos(theta) * 50;
        float y = rad * sin(theta) * 50;
        vertex(x,y);
    }
  }
  endShape();
  save("output.png");
  exit();
}



public float superFormula(float theta,float a,float b,float m,float n1,float n2,float n3){
  float leftOfPlusSign = pow(abs(cos(m * theta / 4.0f) / a), n2);
  float rightOfPlusSign = pow(abs(sin(m * theta / 4.0f) / b),n3);
  return pow(leftOfPlusSign + rightOfPlusSign, -1.0f / n1);
}
  public void settings() {  size (500,500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "randomSketch" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

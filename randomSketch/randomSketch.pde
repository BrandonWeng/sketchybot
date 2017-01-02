void setup(){
  size (640,360);
  noFill();
  stroke(255);
  strokeWeight(2);
}

void draw(){
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
  
    for(float theta = 0; theta < 2 * PI; theta += 0.1){
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

float superFormula(float theta,float a,float b,float m,float n1,float n2,float n3){
  float leftOfPlusSign = pow(abs(cos(m * theta / 4.0) / a), n2);
  float rightOfPlusSign = pow(abs(sin(m * theta / 4.0) / b),n3);
  return pow(leftOfPlusSign + rightOfPlusSign, -1.0 / n1);
}
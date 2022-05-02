package core;

import java.util.*;

public class Punto {
  private float x;
  private float y;

  public Punto(float x, float y){
    this.x = x;
    this.y = y;
  }

  public double distanciaHasta(Punto destino) {
    float resX = Math.abs(this.x - destino.getX());
    float resY = Math.abs(this.y - destino.getY());
    return Math.sqrt(Math.pow(resX,2) + Math.pow(resY,2));
  }

  public String toString() {
    String str = this.x + "," + this.y;
    return str;
  }

  //Getters y setters

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
  }
}

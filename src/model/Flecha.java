package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Flecha {
private int posX,posY;
private int type;
private PImage arrowU,arrowD,arrowL,arrowR;
private PApplet app;
public Flecha(int posX, int posY, int type, PApplet app) {	
	this.posX = posX;
	this.posY = posY;
	this.type = type;
	this.app = app;
	arrowU = app.loadImage("res/img/Arriba.png");
	arrowD = app.loadImage("res/img/Abajo.png");
	arrowL = app.loadImage("res/img/Izquierda.png");
	arrowR = app.loadImage("res/img/Derecha.png");

}
public void draw() {
	switch(type) {
	//ARRIBA
	case 1:
		app.image(arrowU,posX,posY);
		break;
	//ABAJO
	case 2:
		app.image(arrowD,posX,posY);
		break;
	//IZQUIERDA
	case 3:
		app.image(arrowL,posX,posY);
		break;
	//DERECHA
	case 4:
		app.image(arrowR,posX,posY);
		break;
	}
}
public int getPosX() {
	return posX;
}
public void setPosX(int posX) {
	this.posX = posX;
}
public int getPosY() {
	return posY;
}
public void setPosY(int posY) {
	this.posY = posY;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}



}

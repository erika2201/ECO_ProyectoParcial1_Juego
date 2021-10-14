package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Flecha {
private float posX,posY;
private int type;
private PImage arrowU,arrowD,arrowL,arrowR;
private PApplet app;
private float spd;
private boolean isMov,p1Scored,p2Scored;
public Flecha(int posX, int posY, int type, PApplet app) {	
	this.posX = posX;
	this.posY = posY;
	this.type = type;
	this.app = app;
	arrowU = app.loadImage("res/img/Arriba.png");
	arrowD = app.loadImage("res/img/Abajo.png");
	arrowL = app.loadImage("res/img/Izquierda.png");
	arrowR = app.loadImage("res/img/Derecha.png");
	spd = (float) 5.30342205323;
	isMov=false;
	p1Scored=false;
	p2Scored=false;

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
public void move() {
	if(isMov) {
	posY+=spd;
	}
}
public boolean isMov() {
	return isMov;
}
public void setMov(boolean isMov) {
	this.isMov = isMov;
}
public float getPosX() {
	return posX;
}
public void setPosX(int posX) {
	this.posX = posX;
}
public float getPosY() {
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
public boolean isP1Scored() {
	return p1Scored;
}
public void setP1Scored(boolean p1Scored) {
	this.p1Scored = p1Scored;
}
public boolean isP2Scored() {
	return p2Scored;
}
public void setP2Scored(boolean p2Scored) {
	this.p2Scored = p2Scored;
}



}

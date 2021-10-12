package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Cuy {
private int player;
private PImage cuyUp1,cuyUp2,cuyDown1,cuyDown2,cuyLeft1,cuyLeft2,cuyRight1,cuyRight2,cuyRojo,cuyAzul;
private PApplet app;
public Cuy(int player, PApplet app) {
	this.player = player;
	this.app = app;
	cuyRojo = app.loadImage("res/img/CuyoRojo.png");
	cuyAzul = app.loadImage("res/img/CuyoAzul.png");
	cuyUp1 = app.loadImage("res/img/CuyoRojoArriba.png");
	cuyDown1 = app.loadImage("res/img/CuyoRojoAbajo.png");
	cuyLeft1 = app.loadImage("res/img/CuyoRojoIzquierdo.png");
	cuyRight1 = app.loadImage("res/img/CuyoRojoDerecho.png");
	cuyUp2 = app.loadImage("res/img/CuyoAzulArriba.png");
	cuyDown2 = app.loadImage("res/img/CuyoAzulAbajo.png");
	cuyLeft2 = app.loadImage("res/img/CuyoAzulIzquierdo.png");
	cuyRight2 = app.loadImage("res/img/CuyoAzulDerecho.png");
}
public void draw() {
	if(player==1) {
		app.image(cuyRojo,218,387);
	}
	if(player==2) {
		app.image(cuyAzul,898,387);
	}
}

}

package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Cuy {
	private int player;
	private PImage cuyUp1, cuyUp2, cuyDown1, cuyDown2, cuyLeft1, cuyLeft2, cuyRight1, cuyRight2, cuyRojo, cuyAzul;
	private PApplet app;
	private int azulDir,rojoDir;

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
		azulDir=0;
		rojoDir=0;
	}

	public void draw() {
		if (player == 2) {
			switch(azulDir) {
			case 87:
				app.image(cuyUp2, 898, 387);
				break;
			case 65:
				app.image(cuyLeft2, 898-53, 387);
				break;
			case 68:
				app.image(cuyRight2, 898, 387);
				break;
			case 83:
				app.image(cuyDown2, 898, 387);
				break;
				default:
				app.image(cuyAzul, 898, 387);
			}
			
		}
		if (player == 1) {
			switch(rojoDir) {
			case 38:
				app.image(cuyUp1, 218, 387);
				break;
			case 37:
				app.image(cuyLeft1, 218-53, 387);
				break;
			case 39:
				app.image(cuyRight1, 218, 387);
				break;
			case 40:
				app.image(cuyDown1, 218, 387);
				break;
				default:
				app.image(cuyRojo, 218, 387);
			}
			
		}
	}
	public void dance() {
		if (player == 1) {
			switch (app.keyCode) {
			//ARRIBA
			case 38	: 
			rojoDir=38;
				break;
			//IZQUIERDA
			case 37:
				rojoDir=37;
				break;
			//DERECHA
			case 39: 
				rojoDir=39;
				break;
			//ABAJO
			case 40:
				rojoDir=40;
				break;
			}
			}
		if (player == 2) {
			switch (app.keyCode) {
			//ARRIBA
			case 87: 
			azulDir=87;
				break;
			//IZQUIERDA
			case 65:
			azulDir=65;
				break;
			//DERECHA
			case 68: 
				azulDir=68;
				break;
			//ABAJO
			case 83:
				azulDir=83;
				break;
			}
			}

}
}

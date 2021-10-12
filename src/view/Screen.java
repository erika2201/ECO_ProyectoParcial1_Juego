package view;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Screen {

	protected PApplet app;
	protected PImage Init, Instruction, Connect, Connect2, Game, Winner1, Winner2;

	public Screen(PApplet app) {
		this.app = app;
		Init = app.loadImage("res/img/Inicio.png");
		Instruction = app.loadImage("res/img/Instrucciones.png");
		Connect = app.loadImage("res/img/Conexion.png");
		Game = app.loadImage("res/img/Juego.png");
		Winner1 = app.loadImage("res/img/Ganador1.png");
		Winner2 = app.loadImage("res/img/Ganador2.png");
	}
	
	 public abstract void draw ();

}
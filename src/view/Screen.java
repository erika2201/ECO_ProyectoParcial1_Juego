package view;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Screen {

	protected PApplet app;
	protected PImage Init, Instruction, Connect, Connect2, Game, Winner1, Winner2;

	public Screen(PApplet app) {
		this.app = app;
		Init = app.loadImage("img/Inicio.png");
		Instruction = app.loadImage("img/Instrucciones.png");
		Connect = app.loadImage("img/Conexion.png");
		Connect2 = app.loadImage("img/ConexionLista.png");
		Game = app.loadImage("img/Juego.png");
		Winner1 = app.loadImage("img/Ganador1.png");
		Winner2 = app.loadImage("img/Ganador2.png");
	}
	
	 public abstract void draw ();

}
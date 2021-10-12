package view;

import model.Cuy;
import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.SoundFile;

public class CuyMain extends PApplet {
	int pantalla;
	ConnectScreen connect;
	GameScreen game;
	InitScreen start;
	InstructionScreen instruct;
	Winner1Screen winp1;
	Winner2Screen winp2;
	Boolean p1HasConnect, p2HasConnect, p1HasWon, p2HasWon;
	SoundFile song;
	PImage conectado1,conectado2,esperarConex1,esperarConex2,aBailar;
	Cuy p1,p2;

	public static void main(String[] args) {
		PApplet.main(CuyMain.class.getName());

	}

	@Override
	public void settings() {
		size(1200, 700);

	}

	@Override
	public void setup() {
		pantalla = 3;
		connect = new ConnectScreen(this);
		game = new GameScreen(this);
		start = new InitScreen(this);
		instruct = new InstructionScreen(this);
		winp1 = new Winner1Screen(this);
		winp2 = new Winner2Screen(this);
		song = new SoundFile(this,"res/sound/Song.mp3");
		conectado1 = loadImage ("res/img/Conectado1.png");
		conectado2 = loadImage ("res/img/Conectado2.png");
		esperarConex1 = loadImage ("res/img/EsperarConex1.png");
		esperarConex2 = loadImage ("res/img/EsperarConex2.png");
		aBailar = loadImage ("res/img/ABailar.png");
		p1HasConnect= false; 
		p2HasConnect = false; 
		p1HasWon = true;
		p2HasWon = false;
		p1 = new Cuy(1,this);
		p2 = new Cuy(2,this);
		//song.play();
	}

	@Override
	public void draw() {
		changeScreen();
		System.out.println(mouseX+" "+mouseY);
	}

	public void changeScreen() {
		switch (pantalla) {
		case 0:
			start.draw();
			break;
		case 1:
			instruct.draw();
			break;
		case 2:
			connect.draw();
			if(!p1HasConnect) {
					image(esperarConex1,0,0);	
				}else {
					image(conectado1,0,0);	
				}
			if(!p2HasConnect) {
					image(esperarConex2,0,0);	
			}else {
					image(conectado2,0,0);	
			}
			if(p1HasConnect&&p2HasConnect) {
				image(aBailar,0,0);
			}
			break;
		case 3:
			game.draw();
			p1.draw();
			p2.draw();
			break;
		case 4:
			if(p1HasWon) {
			winp1.draw();
			}else if(p2HasWon) {
			winp2.draw();	
			}
			break;
		
		}
	
	}
public void mousePressed() {
	p1HasConnect=!p1HasConnect;
	p2HasConnect=!p2HasConnect;
		switch(pantalla) {
		case 0:
			//DE INICIO A INSTRUCCIONES
			if((463<mouseX&&mouseX<730)&&(371<mouseY&&mouseY<473)) {
				pantalla=1;
			}
			//DE INICIO A SALIR
			if((463<mouseX&&mouseX<730)&&(523<mouseY&&mouseY<625)) {
				exit();
			}
			break;
		case 1:
			//DE INSTRUCCIONES A CONEXION
			if((814<mouseX&&mouseX<1076)&&(562<mouseY&&mouseY<670)) {
				pantalla=2;
			}
			break;
		case 2:
			//DE CONEXION A JUGAR
			if(p1HasConnect&&p2HasConnect&&(467<mouseX&&mouseX<730)&&(498<mouseY&&mouseY<601)) {
				pantalla=3;
			}
			break;
		case 4:
			//DE VICTORIA A INICIO
			if((47<mouseX&&mouseX<310)&&(378<mouseY&&mouseY<643)) {
				pantalla=3;
			}
			//DE VICTORIA A JUGAR
			if((380<mouseX&&mouseX<643)&&(378<mouseY&&mouseY<643)) {
				pantalla=0;
			}

			break;
		}
	}
}
package view;





import java.util.Timer;
import java.util.TimerTask;

import model.Cuy;
import model.Flecha;
import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.SoundFile;

public class CuyMain extends PApplet {

	int pantalla, flechaActual;
	Screen connect, game, start, instruct, winp1, winp2;
	boolean p1HasConnect, p2HasConnect, p1HasWon, p2HasWon;
	SoundFile song;
	PImage conectado1, conectado2, esperarConex1, esperarConex2, aBailar;
	PImage btnPlay, btnExit, btnContinue, btnDance,btnPlayAgain, btnBackMenu;
	Cuy p1, p2;
	Flecha[] flechitas;
	Timer timer;
	TimerTask task;

	public static void main(String[] args) {
		PApplet.main(CuyMain.class.getName());

	}

	@Override
	public void settings() {
		size(1200, 700);

	}

	@Override
	public void setup() {
		pantalla = 0;
		flechaActual=0;
		connect = new ConnectScreen(this);
		game = new GameScreen(this);
		start = new InitScreen(this);
		instruct = new InstructionScreen(this);
		winp1 = new Winner1Screen(this);
		winp2 = new Winner2Screen(this);
		song = new SoundFile(this, "res/sound/Song.mp3");
		conectado1 = loadImage("res/img/Conectado1.png");
		conectado2 = loadImage("res/img/Conectado2.png");
		esperarConex1 = loadImage("res/img/EsperarConex1.png");
		esperarConex2 = loadImage("res/img/EsperarConex2.png");
		aBailar = loadImage("res/img/ABailar.png");
		p1HasConnect = false;
		p2HasConnect = false;
		p1HasWon = false;
		p2HasWon = false;
		p1 = new Cuy(1, this);
		p2 = new Cuy(2, this);
		timer = new Timer();
		
		
		//BOTONES
		btnPlay = loadImage("res/img/BtnPlay.png");
		btnExit = loadImage("res/img/BtnExit.png");
		btnContinue = loadImage("res/img/BtnContinue.png");
		btnDance = loadImage("res/img/BtnDance.png");
		btnPlayAgain = loadImage("res/img/BtnPlayAgain.png");
		btnBackMenu = loadImage("res/img/BtnBackMenu.png");
	
		
		flechitas = new Flecha[26];
		createArrows();
		moveSetup();
		
		
	}

	public void createArrows() {
		int tipo;

		for (int i = 0; i < flechitas.length; i++) {
		tipo = (int) random(1,5);
		Flecha f = new Flecha(width/2-(112/2),-96,tipo,this);
		if(i!=0) {
			tipo = (int) random(1,5);
			Flecha f1 = new Flecha(width/2-(112/2),-96,tipo,this);
			while(flechitas[i-1].getType()==tipo) {
				tipo = (int) random(1,5);
				f1 = new Flecha(width/2-(112/2),-96,tipo,this);
			}	
			
			flechitas[i] = f1;
		}else {
			
			flechitas[i] = f;
			
		}
		}
	}
	public void drawArrows() {
		for (int i = 0; i < flechitas.length; i++) {
			flechitas[i].draw();
		}
	}
	public void moveSetup() {
		task = new TimerTask() {
			public void run() {
					
					flechitas[flechaActual].setMov(true);
				flechaActual++;
					if(flechaActual==26) {
						gameover();
					}
			
					
			}
		};
	}
	
	public void moveArrows() {
		for (int i = 0; i < flechitas.length; i++) {
			flechitas[i].move();
		}
	}
	@Override
	public void draw() {
		changeScreen();
		buttonSelect();
		
		//System.out.println(flechitas.length);
		//System.out.println(mouseX + " " + mouseY);
	
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
			if (!p1HasConnect) {
				image(esperarConex1, 0, 0);
			} else {
				image(conectado1, 0, 0);
			}
			if (!p2HasConnect) {
				image(esperarConex2, 0, 0);
			} else {
				image(conectado2, 0, 0);
			}
			if (p1HasConnect && p2HasConnect) {
				image(aBailar, 0, 0);
			}
			break;
		case 3:
			game.draw();
			p1.draw();
			p2.draw();
			drawArrows();
			moveArrows();
			break;
		case 4:
			if (p1HasWon) {
				winp1.draw();
			} else if (p2HasWon) {
				winp2.draw();
			}
			break;
		}

	}
public void gameover() {
	timer.cancel();
}
	public void buttonSelect() {
		switch (pantalla) {
		case 0:
			if ((463 < mouseX && mouseX < 730) && (371 < mouseY && mouseY < 473)) {
				image(btnPlay, 0, 0);
			}
			if ((463 < mouseX && mouseX < 730) && (523 < mouseY && mouseY < 625)) {
				image(btnExit, 0, 0);
			}
			break;
		case 1:
			if ((814 < mouseX && mouseX < 1076) && (562 < mouseY && mouseY < 670)) {
				image(btnContinue, 0, 0);
			}
			break;
		case 2:
			if (p1HasConnect && p2HasConnect && (467 < mouseX && mouseX < 730) && (498 < mouseY && mouseY < 601)) {
				image(btnDance, 0, 0);
			}
			break;
		case 4:
			if ((47 < mouseX && mouseX < 310) && (378 < mouseY && mouseY < 643)) {
				image(btnPlayAgain, 0, 0);
			}
			
			if ((380 < mouseX && mouseX < 643) && (378 < mouseY && mouseY < 643)) {
				image(btnBackMenu, 0, 0);
			}
			break;

		default:
			break;
		}
	}

	public void mousePressed() {
		
		/*
		 p1HasConnect = !p1HasConnect;
		p2HasConnect = !p2HasConnect;
		*/
		switch (pantalla) {
		case 0:
			// DE INICIO A INSTRUCCIONES
			if ((463 < mouseX && mouseX < 730) && (371 < mouseY && mouseY < 473)) {
				pantalla = 3;
				song.play();
				
				timer.scheduleAtFixedRate(task, 337,2400);
			}
			// DE INICIO A SALIR
			if ((463 < mouseX && mouseX < 730) && (523 < mouseY && mouseY < 625)) {
				exit();
			}
			break;
		case 1:
			// DE INSTRUCCIONES A CONEXION
			if ((814 < mouseX && mouseX < 1076) && (562 < mouseY && mouseY < 670)) {
				pantalla = 2;
			}
			break;
		case 2:
			// DE CONEXION A JUGAR
			if (p1HasConnect && p2HasConnect && (467 < mouseX && mouseX < 730) && (498 < mouseY && mouseY < 601)) {
				pantalla = 3;
				song.play();
			}
			break;
		case 4:
			// DE VICTORIA A JUGAR
			if ((47 < mouseX && mouseX < 310) && (378 < mouseY && mouseY < 643)) {
				pantalla = 3;
			}
			// DE VICTORIA A INICIO
			if ((380 < mouseX && mouseX < 643) && (378 < mouseY && mouseY < 643)) {
				pantalla = 0;
			}

			break;
		}
	}
public void keyPressed() {
	p1.dance();
	p2.dance();
}
	
}

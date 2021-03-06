package view;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.Gson;

import model.Cuy;
import model.Flecha;
import model.Message;
import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.SoundFile;

public class CuyMain extends PApplet implements IObserver{

	int pantalla, flechaActual, puntaje1,puntaje2;
	Screen connect, game, start, instruct, winp1, winp2;
	boolean p1HasConnect, p2HasConnect, p1HasWon, p2HasWon;
	SoundFile song;
	PImage conectado1, conectado2, esperarConex1, esperarConex2, aBailar,vida;
	PImage btnPlay, btnExit, btnContinue, btnDance, btnPlayAgain, btnBackMenu;
	Cuy p1, p2;
	Flecha[] flechitas;
	ArrayList<Integer> vidasP1;
	ArrayList<Integer> vidasP2;
	Timer timerF;
	Timer timerL;
	TimerTask task,task2;
	

	// MULTICLIENTE
	private TCPLauncher launcher;

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
		puntaje1=0;
		puntaje2=0;
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
		vida = loadImage("res/img/Vida.png");
		p1HasConnect = false;
		p2HasConnect = false;
		p1HasWon = false;
		p2HasWon = false;
		p1 = new Cuy(0, this);
		p2 = new Cuy(1, this);
		timerF = new Timer();
		timerL = new Timer();

		// BOTONES
		btnPlay = loadImage("res/img/BtnPlay.png");
		btnExit = loadImage("res/img/BtnExit.png");
		btnContinue = loadImage("res/img/BtnContinue.png");
		btnDance = loadImage("res/img/BtnDance.png");
		btnPlayAgain = loadImage("res/img/BtnPlayAgain.png");
		btnBackMenu = loadImage("res/img/BtnBackMenu.png");

		flechitas = new Flecha[26];
		
		vidasP1 = new ArrayList<Integer>();
		vidasP1.add(1);
		vidasP1.add(1);
		vidasP1.add(1);
		vidasP2 = new ArrayList<Integer>();
		vidasP2.add(1);
		vidasP2.add(1);
		vidasP2.add(1);
		createArrows();
		moveSetup();
		lifeSetup();
		
		
		// MULTICLIENTE
		launcher = TCPLauncher.getInstance();
		launcher.setCuyMain(this);
		launcher.start();

	}
	public void reset() {
		pantalla = 3;
		flechaActual=0;
		puntaje1=0;
		puntaje2=0;
		p1 = new Cuy(0, this);
		p2 = new Cuy(1, this);
		flechitas = new Flecha[26];
		vidasP1 = new ArrayList<Integer>();
		vidasP1.add(1);
		vidasP1.add(1);
		vidasP1.add(1);
		vidasP2 = new ArrayList<Integer>();
		vidasP2.add(1);
		vidasP2.add(1);
		vidasP2.add(1);
		timerF = new Timer();
		timerL = new Timer();
		createArrows();
		moveSetup();
		lifeSetup();
		song.play();
		timerF.scheduleAtFixedRate(task, 337,2400);
	}

	public void createArrows() {
		int tipo;

		for (int i = 0; i < flechitas.length; i++) {
			tipo = (int) random(1, 5);
			Flecha f = new Flecha(width / 2 - (112 / 2), -96, tipo, this);
			if (i != 0) {
				tipo = (int) random(1, 5);
				Flecha f1 = new Flecha(width / 2 - (112 / 2), -96, tipo, this);
				while (flechitas[i - 1].getType() == tipo) {
					tipo = (int) random(1, 5);
					f1 = new Flecha(width / 2 - (112 / 2), -96, tipo, this);
				}
				flechitas[i] = f1;
			} else {

				flechitas[i] = f;
			}
		}
	}

	public void drawArrows() {
		for (int i = 0; i < flechitas.length; i++) {
			flechitas[i].draw();
		}
	}
	public void drawLives() {
		for (int i = 0; i < vidasP1.size(); i++) {
			if(vidasP1.get(i)!=0){
			image(vida,260+(40*i),105);
			}
		}
		for (int i = 0; i < vidasP2.size(); i++) {
			if(vidasP2.get(i)!=0){
			image(vida,995+(40*i),105);
			}
		}
	}

	public void moveSetup() {
		task = new TimerTask() {
			public void run() {
				flechitas[flechaActual].setMov(true);
				flechaActual++;
				if(flechaActual==1) {
					timerL.scheduleAtFixedRate(task2, 2299, 2400);
				}
				if (flechaActual == 26) {
					gameover();
				}

			}
		};
	}
	public void lifeSetup() {
		task2 = new TimerTask() {
			public void run() {
				//System.out.println(flechitas[flechaActual-1].getPosY());
				if(flechitas[flechaActual-1].isP1Scored()==false&&flechitas[flechaActual-1].getPosY()>=630) {
					vidasP1.remove(vidasP1.size()-1);
					}
					if(flechitas[flechaActual-1].isP2Scored()==false&&flechitas[flechaActual-1].getPosY()>=630) {
						vidasP2.remove(vidasP2.size()-1);
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

		// System.out.println(flechitas.length);
		// System.out.println(mouseX + " " + mouseY);
		
		

	}
	public void conexion() {
		if(launcher.getSesiones().size()==1) {
			p1HasConnect=true;
		}
		if(launcher.getSesiones().size()==2) {
			p2HasConnect=true;
		}
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
			conexion();
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
			p1.draw(launcher.getSesiones().get(0),launcher.getSesiones().get(0).getMessage());
			p2.draw(launcher.getSesiones().get(1),launcher.getSesiones().get(1).getMessage());
			
			drawArrows();
			drawLives();
			moveArrows();
			winByLives();
			textSize(16);
			fill(255);
		//*/*/*/*/*/*Pintar puntajes
			text(puntaje1,158,124);
			text(puntaje2,894,124);
		//*/*/*/*/*/*/*/*/*/*/*/*/*/*/*
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

	public void winByLives() {
		
		if(vidasP1.size()==0) {
			timerF.cancel();
			timerL.cancel();
			song.stop();
			p2HasWon=true;
			pantalla=4;
		}else if(vidasP2.size()==0) {
			timerF.cancel();
			timerL.cancel();
			song.stop();
			p1HasWon=true;
			pantalla=4;
		}
		
	}
public void gameover() {
	timerF.cancel();
	timerL.cancel();
	
	if(puntaje1>puntaje2) {
		p1HasWon=true;
		pantalla=4;
		
	}else if(puntaje2>puntaje1) {
		p2HasWon=true;
		pantalla=4;
	}
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
		 * p1HasConnect = !p1HasConnect; p2HasConnect = !p2HasConnect;
		 */
		switch (pantalla) {
		case 0:
			// DE INICIO A INSTRUCCIONES
			if ((463 < mouseX && mouseX < 730) && (371 < mouseY && mouseY < 473)) {
				pantalla = 1;
				
	
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
				reset();
				
			}
			break;
		case 4:
			// DE VICTORIA A JUGAR
			if ((47 < mouseX && mouseX < 310) && (378 < mouseY && mouseY < 643)) {
				reset();
			}
			// DE VICTORIA A INICIO
			if ((380 < mouseX && mouseX < 643) && (378 < mouseY && mouseY < 643)) {
				pantalla = 0;
			}

			break;
		}
	}


public void score1(Message m) {
	//System.out.println(m.getKey());

	if((flechaActual-1)>=0) {

	switch(m.getKey()) {
	//*/*/*/*/*/*/*/*/*JUGADOR 1*/*/*/*/*/*/*/*/*/
	case "UP":

		//+*+*+*+*+*FLECHA ARRIBA+*+*+*+*+*+*+*+*+*+*
		//***PRIMERO, COMPRUEBA QUE LA FLECHA ACTUAL SEA LA DIRECCION CORRECTA
		if(flechitas[flechaActual-1].getType()==1) {
		
		//***DESPUES, COMPUREBA QUE LA FLECHA SE ENCUENTRE ACTUALMENTE DENTRO DEL CIRCULO
			if(flechitas[flechaActual-1].getPosY()>518&&flechitas[flechaActual-1].getPosY()<630) {
			
		//***POR ULTIMO, VERIFICA QUE NO SE HAYAN MARCADO PUNTOS YA POR ESA MISMA FLECHA
			if(flechitas[flechaActual-1].isP1Scored()==false) {
				
				puntaje1+=100;
				flechitas[flechaActual-1].setP1Scored(true);
			}
			}
		}	
		break;
	case "LEFT":

		//+*+*+*+*+*FLECHA IZQUIERDA+*+*+*+*+*+*+*+*+*+*
		//***PRIMERO, COMPRUEBA QUE LA FLECHA ACTUAL Y LA DIRECCION DE INPUT DEL JUGADOR SEA LA MISMA
			if(flechitas[flechaActual-1].getType()==3) {
			//***DESPUES, COMPUREBA QUE LA FLECHA SE ENCUENTRE ACTUALMENTE DENTRO DEL CIRCULO
				if(flechitas[flechaActual-1].getPosY()>518&&flechitas[flechaActual-1].getPosY()<630) {
			//***POR ULTIMO, VERIFICA QUE NO SE HAYAN MARCADO PUNTOS YA POR ESA MISMA FLECHA
				if(flechitas[flechaActual-1].isP1Scored()==false) {
					puntaje1+=100;
					flechitas[flechaActual-1].setP1Scored(true);
				}
				}
			}
		break;
	case "RIGHT":
	
		//+*+*+*+*+*FLECHA DERECHA+*+*+*+*+*+*+*+*+*+*
				//***PRIMERO, COMPRUEBA QUE LA FLECHA ACTUAL Y LA DIRECCION DE INPUT DEL JUGADOR SEA LA MISMA
					if(flechitas[flechaActual-1].getType()==4) {
					//***DESPUES, COMPUREBA QUE LA FLECHA SE ENCUENTRE ACTUALMENTE DENTRO DEL CIRCULO
						if(flechitas[flechaActual-1].getPosY()>518&&flechitas[flechaActual-1].getPosY()<630) {
					//***POR ULTIMO, VERIFICA QUE NO SE HAYAN MARCADO PUNTOS YA POR ESA MISMA FLECHA
						if(flechitas[flechaActual-1].isP1Scored()==false) {
							puntaje1+=100;
							flechitas[flechaActual-1].setP1Scored(true);
						}
						}
					}
		break;
	case "DOWN":
	
		//+*+*+*+*+*FLECHA ABAJO+*+*+*+*+*+*+*+*+*+*
		//***PRIMERO, COMPRUEBA QUE LA FLECHA ACTUAL Y LA DIRECCION DE INPUT DEL JUGADOR SEA LA MISMA
			if(flechitas[flechaActual-1].getType()==2) {
			//***DESPUES, COMPUREBA QUE LA FLECHA SE ENCUENTRE ACTUALMENTE DENTRO DEL CIRCULO
				if(flechitas[flechaActual-1].getPosY()>518&&flechitas[flechaActual-1].getPosY()<630) {
			//***POR ULTIMO, VERIFICA QUE NO SE HAYAN MARCADO PUNTOS YA POR ESA MISMA FLECHA
				if(flechitas[flechaActual-1].isP1Scored()==false) {
					puntaje1+=100;
					flechitas[flechaActual-1].setP1Scored(true);
				}
				}
			}
		break;
	}
	}
		
	
	

}
public void score2(Message m) {
	switch(m.getKey()) {
	//*/*/*/*/*/*/*/*/*JUGADOR 1*/*/*/*/*/*/*/*/*/
	case "UP":
		//+*+*+*+*+*FLECHA ARRIBA+*+*+*+*+*+*+*+*+*+*
		//***PRIMERO, COMPRUEBA QUE LA FLECHA ACTUAL Y LA DIRECCION DE INPUT DEL JUGADOR SEA LA MISMA
		if(flechitas[flechaActual-1].getType()==1) {
		//***DESPUES, COMPUREBA QUE LA FLECHA SE ENCUENTRE ACTUALMENTE DENTRO DEL CIRCULO
			if(flechitas[flechaActual-1].getPosY()>518&&flechitas[flechaActual-1].getPosY()<630) {
		//***POR ULTIMO, VERIFICA QUE NO SE HAYAN MARCADO PUNTOS YA POR ESA MISMA FLECHA
			if(flechitas[flechaActual-1].isP2Scored()==false) {
				puntaje2+=100;
				flechitas[flechaActual-1].setP2Scored(true);
			}
			}
		}	
		break;
	case "LEFT":
		//+*+*+*+*+*FLECHA IZQUIERDA+*+*+*+*+*+*+*+*+*+*
		//***PRIMERO, COMPRUEBA QUE LA FLECHA ACTUAL Y LA DIRECCION DE INPUT DEL JUGADOR SEA LA MISMA
			if(flechitas[flechaActual-1].getType()==3) {
			//***DESPUES, COMPUREBA QUE LA FLECHA SE ENCUENTRE ACTUALMENTE DENTRO DEL CIRCULO
				if(flechitas[flechaActual-1].getPosY()>518&&flechitas[flechaActual-1].getPosY()<630) {
			//***POR ULTIMO, VERIFICA QUE NO SE HAYAN MARCADO PUNTOS YA POR ESA MISMA FLECHA
				if(flechitas[flechaActual-1].isP2Scored()==false) {
					puntaje2+=100;
					flechitas[flechaActual-1].setP2Scored(true);
				}
				}
			}
		break;
	case "RIGHT":
		//+*+*+*+*+*FLECHA DERECHA+*+*+*+*+*+*+*+*+*+*
				//***PRIMERO, COMPRUEBA QUE LA FLECHA ACTUAL Y LA DIRECCION DE INPUT DEL JUGADOR SEA LA MISMA
					if(flechitas[flechaActual-1].getType()==4) {
					//***DESPUES, COMPUREBA QUE LA FLECHA SE ENCUENTRE ACTUALMENTE DENTRO DEL CIRCULO
						if(flechitas[flechaActual-1].getPosY()>518&&flechitas[flechaActual-1].getPosY()<630) {
					//***POR ULTIMO, VERIFICA QUE NO SE HAYAN MARCADO PUNTOS YA POR ESA MISMA FLECHA
						if(flechitas[flechaActual-1].isP2Scored()==false) {
							puntaje2+=100;
							flechitas[flechaActual-1].setP2Scored(true);
						}
						}
					}
		break;
	case "DOWN":
		//+*+*+*+*+*FLECHA ABAJO+*+*+*+*+*+*+*+*+*+*
		//***PRIMERO, COMPRUEBA QUE LA FLECHA ACTUAL Y LA DIRECCION DE INPUT DEL JUGADOR SEA LA MISMA
			if(flechitas[flechaActual-1].getType()==2) {
			//***DESPUES, COMPUREBA QUE LA FLECHA SE ENCUENTRE ACTUALMENTE DENTRO DEL CIRCULO
				if(flechitas[flechaActual-1].getPosY()>518&&flechitas[flechaActual-1].getPosY()<630) {
			//***POR ULTIMO, VERIFICA QUE NO SE HAYAN MARCADO PUNTOS YA POR ESA MISMA FLECHA
				if(flechitas[flechaActual-1].isP2Scored()==false) {
					puntaje2+=100;
					flechitas[flechaActual-1].setP2Scored(true);
				}
				}
			}
		break;
	}
		
	
	

}
	
	//MULTICLIENTE/OBSERVER
	public void onMessage(Session s, String msg) {
		
		//System.out.println("Mensaje lleg? de:"+ s.getID() + ":" + msg);
		//DESERIALIZAR 
		Gson gson = new Gson();
		Message direccion = gson.fromJson(msg, Message.class);
		s.setMessage(direccion);
		score1(launcher.getSesiones().get(0).getMessage());
		score2(launcher.getSesiones().get(1).getMessage());
		
	}

}

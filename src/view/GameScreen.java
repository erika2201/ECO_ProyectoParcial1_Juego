package view;

import processing.core.PApplet;

public class GameScreen extends Screen {

	public GameScreen(PApplet app) {
		super(app);
	}

	@Override
	public void draw() {
		app.image(Game, 0, 0);
		
	}


}

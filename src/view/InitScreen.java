package view;

import processing.core.PApplet;

public class InitScreen extends Screen {

	public InitScreen (PApplet app) {
		super(app);
	}

	@Override
	public void draw() {
		app.image(Init, 0, 0);
		
	}

}

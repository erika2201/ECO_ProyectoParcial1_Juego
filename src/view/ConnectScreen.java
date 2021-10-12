package view;

import processing.core.PApplet;

public class ConnectScreen extends Screen {

	public ConnectScreen (PApplet app) {
		super(app);
	}

	@Override
	public void draw() {
		app.image(Connect, 0, 0);
		
	}

}

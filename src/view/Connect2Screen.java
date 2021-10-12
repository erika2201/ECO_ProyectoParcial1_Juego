package view;

import processing.core.PApplet;

public class Connect2Screen extends Screen {

	public Connect2Screen (PApplet app) {
		super(app);
	}

	@Override
	public void draw() {
		app.image(Connect2, 0, 0);
		
	}

}

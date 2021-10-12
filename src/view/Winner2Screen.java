package view;

import processing.core.PApplet;

public class Winner2Screen extends Screen {

	public Winner2Screen(PApplet app) {
		super(app);
	}

	@Override
	public void draw() {
		app.image(Winner2, 0, 0);

	}

}

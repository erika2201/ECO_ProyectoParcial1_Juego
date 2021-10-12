package view;

import processing.core.PApplet;

public class Winner1Screen extends Screen {

	public Winner1Screen(PApplet app) {
		super(app);
	}

	@Override
	public void draw() {
		app.image(Winner1, 0, 0);

	}

}


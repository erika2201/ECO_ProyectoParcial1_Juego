package view;

import processing.core.PApplet;

public class InstructionScreen extends Screen {

	public InstructionScreen (PApplet app) {
		super(app);
	}

	@Override
	public void draw() {
		app.image(Instruction, 0, 0);
		
	}

}

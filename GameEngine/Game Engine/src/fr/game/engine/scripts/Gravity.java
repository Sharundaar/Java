package fr.game.engine.scripts;

import fr.game.engine.entity.Entity;

public class Gravity extends Script {

	final private float GRAVITY = 0.05f;
	
	public Gravity() {
		super("Gravity");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Entity target, float dt) {
		// TODO Auto-generated method stub
		target.translate(0, GRAVITY * dt);
	}

}

package fr.game.engine.scripts;

import fr.game.engine.entity.Entity;

public abstract class Script {
	protected String name;
	
	public Script(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract void execute(Entity target, float dt);
	
}

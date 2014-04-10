package fr.game.engine.game;

import java.util.LinkedList;
import fr.game.engine.entity.Entity;

public class SceneManager {
	
	private LinkedList<Entity> entities;
	
	public SceneManager() {
		entities = new LinkedList<Entity>();
	}
	
	public void update(float dt) {
		for(Entity ent : entities) {
			ent.update(dt);
		}
	}
	
	public void add(Entity ent) {
		if(ent != null)
			entities.add(ent);
	}
	
	public LinkedList<Entity> getEntities() {
		return entities;
	}
}

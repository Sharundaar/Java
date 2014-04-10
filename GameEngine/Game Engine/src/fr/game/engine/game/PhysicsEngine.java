package fr.game.engine.game;

import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import fr.game.engine.entity.Entity;
import fr.game.engine.entity.PhysicsEntity;

public class PhysicsEngine {
	public LinkedList<PhysicsEntity> entities;
	
	public float gravity;
	public Game game;
	
	
	public PhysicsEngine(Game game) {
		this.game = game;
		entities = new LinkedList<>();
		gravity = 0.01962f;
		
	}
	
	
	private Rectangle2D.Float projection1 = new Rectangle2D.Float();
	private Rectangle2D.Float projection2 = new Rectangle2D.Float();
	
	/**
	 * Update all physical entity registered
	 * @param dt time passed since last update
	 */
	public void update(float dt) {
		/* Adding gravity */
		for(PhysicsEntity ent : entities) {
			if(!ent.isGhost())
				addGravity(ent);
		}
		
		/* Checking collisions between entities */
		for(PhysicsEntity ent : entities) {	
			
			if(ent.isGhost())
				continue;
			
			for(PhysicsEntity ent2 : entities) {
				if(ent == ent2 || ent2.isGhost())
					continue;
				
				projection1.setRect(ent.getCollisionMask());
				projection2.setRect(ent2.getCollisionMask());
				projection1.x += ent.getDX() * dt;
				projection1.y += ent.getDY() * dt;
				projection2.x += ent2.getDX() * dt;
				projection2.y += ent2.getDY() * dt;
				
				if(projection1.intersects(projection2)) {
					ent.applyForce(-ent.getDX(), -ent.getDY());
					ent2.applyForce(-ent2.getDX(), -ent2.getDY());
					
					ent.collideWith(ent2);
					ent2.collideWith(ent);
				}
			}
		}
		
		/* Displacing the entities */
		for(PhysicsEntity ent : entities) {
			ent.translate(ent.getDX() * dt, ent.getDY() * dt);
		}
	}
	
	/**
	 * This function adds gravity to each entity
	 */
	public void addGravity(PhysicsEntity ent) {
		ent.applyForce(0, gravity);
	}
	
	/**
	 * Check for collisions between two entities
	 * @param e1 a physical entity
	 * @param e2 an other physical entity
	 * @return true if e1 collide with e2, false otherwise
	 */
	public boolean checkCollision(PhysicsEntity e1, PhysicsEntity e2) {
		return e1.getCollisionMask().intersects(e2.getCollisionMask());
	}
	
	public void removeEntity(Entity ent) {
		if(ent instanceof PhysicsEntity)
			entities.remove(ent);
	}
	
	public PhysicsEntity createEntity() {
		PhysicsEntity result = new PhysicsEntity(game);
		entities.add(result);
		return result;
	}
}

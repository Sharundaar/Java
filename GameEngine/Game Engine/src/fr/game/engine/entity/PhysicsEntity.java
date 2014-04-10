package fr.game.engine.entity;

import java.awt.geom.Rectangle2D;

import fr.game.engine.game.Game;

public class PhysicsEntity extends Entity {

	private float dx;
	private float dy;
	private float mass;
	private boolean ghost;
	
	public Rectangle2D.Float collisionMask;
	
	public float getMass() {
		return mass;
	}
	
	public void setMass(float mass) {
		if(mass >= 0)
			this.mass = mass;
	}
	
	public PhysicsEntity(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		collisionMask = new Rectangle2D.Float();
		ghost = false;
		mass = 0;
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
	}
	
	public Rectangle2D.Float getCollisionMask() {
		return collisionMask;
	}
	
	public void collideWith(PhysicsEntity ent) {
		
	}
	
	/**
	 * Apply a force to the center of gravity of the entity
	 * @param x coordinate of this force vector
	 * @param y coordinate of this force vector
	 */
	public void applyForce(float x, float y) {
		dx += x;
		dy += y;
	}

	public boolean isGhost() {
		// TODO Auto-generated method stub
		return ghost;
	}
	
	public float getDX() {
		return dx;
	}
	
	public float getDY() {
		return dy;
	}
}

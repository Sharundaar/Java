package fr.game.engine.entity;

import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import fr.game.engine.game.Game;
import fr.game.engine.scripts.Script;

/**
 * This class define an Entity<br>
 * An entity is anything that can be put on a scene<br>
 * 
 * @author Fireflown
 *
 */
public class Entity {
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	protected float angle;
	protected float scale;
	protected Sprite sprite;
	protected Rectangle2D.Float rect;
	protected Game game;
	protected LinkedList<Script> scripts;
	
	
	public Entity(Game game) {
		this.game = game;
		sprite = null;
		rect = new Rectangle2D.Float();
		scripts = new LinkedList<>();
		x=0;
		y=0;
		width=0;
		height=0;
		scale=0;
		angle=0;
	}
	
	public Entity(Game game, float x, float y, float width, float height, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		rect = new Rectangle2D.Float();
		rect.setRect((x + width / 2f), (y + height / 2f), width, height); 
		this.sprite = sprite;
		this.game = game;
		
		scripts = new LinkedList<>();
	}
	
	public void addScript(Script s) {
		if(s != null)
			scripts.add(s);
	}
	
	public void removeScript(Script s) {
		if(s != null)
			scripts.remove(s);
	}
	
	public void update(float dt) {
		for(Script s : scripts) {
			s.execute(this, dt);
		}
	}
	
	public void setWidth(float width) {
		if(width > 0) {
			this.width = width;
			rect.x = x - width / 2f;
			rect.width = width;
		}
	}
	
	public void setHeight(float height) {
		if(height > 0) {
			this.height = height;
			rect.y = y - height / 2f;
			rect.height = height;
		}
	}
	
	public void translate(float dx, float dy) {
		x += dx;
		y += dy;
		
		rect.x += dx;
		rect.y += dy;
	}
	
	public void scale(float s) {
		scale += s;
		
		x *= s;
		y *= s;
		width *= s;
		height *= s;
		
		rect.setRect(x, y, width, height);
	}
	
	public void scaleX(float sx) {
		x *= sx;
		width *= sx;
		
		rect.setRect(x, y, width, height);
	}
	
	public void scaleY(float sy) {
		y *= sy;
		height *= sy;
		
		rect.setRect(x, y, width, height);
	}
	
	public void rotate(float rad) {
		angle += rad;
	}
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	/**
	 * 
	 * @return X coordinate of this entity
	 */
	public float getPosX() {
		return x;
	}
	
	/**
	 * 
	 * @return Y coordinate of this entity
	 */
	public float getPosY() {
		return y;
	}
	
	/**
	 * Return the boundaries of this entity
	 * @return boundaries attribute
	 */
	public Rectangle2D.Float getBounds() {
		return rect;
	}
	
	/**
	 * Getter for the sprite of this entity
	 * @return sprite attribute
	 */
	public Sprite getSprite() {
		return sprite;
	}
	
	/**
	 * Compute the local rendering algorithm for this entity
	 */
	public void render() {
	}
	
	/**
	 * Warp the entity to the coordinate
	 * @param x new x coordinate of this entity
	 * @param y new y coordinate of this entity
	 */
	public void warp(float x, float y) {
		// TODO Auto-generated method stub
		this.x = x;
		this.y = y;
		rect.x = x - width / 2f;
		rect.y = y - height / 2f;
	}
}

package fr.game.engine.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import fr.game.engine.entity.Entity;
import fr.game.engine.entity.Sprite;
import fr.game.engine.game.Game;

/**
 * Defines a Camera that looks onto a scene.
 * @author Fireflown
 *
 */
public class Camera extends Entity {	
	/**
	 * {@link snapshot} : BufferedImage used to store a snapshot of the scene
	 */
	private BufferedImage snapshot;
	
	/**
	 * {@link renderer} : Graphics objects used to take the snapshot
	 */
	private Graphics2D renderer;
	
	/**
	 * Build a camera with passed property
	 * <br>
	 * Initialize the snapshot
	 * @param x : x-coordinate
	 * @param y : y-coordinate
	 * @param width : self
	 * @param height : self
	 * @param game : game instance link to the camera, should not be null !
	 */
	public Camera(float x, float y, float width, float height, Game game) {
		super(game, x, y, width, height, new Sprite("", 3000, 3000));
		
		sprite.setWidth((int) width);
		sprite.setHeight((int) height);
		snapshot = sprite.getImage();
		renderer = snapshot.createGraphics();
	}

	public void render() {
		// First we clear the buffer
		renderer.setColor(Color.white);
		renderer.clearRect(0, 0, sprite.getImage().getWidth(), sprite.getImage().getHeight());
		
		// Then we draw each entities on the buffer
		LinkedList<Entity> entities = game.getSceneManager().getEntities();
		double left = x - width / 2f, up = y - height / 2f;
		
		for(Entity ent : entities) {
			if(ent.getBounds().intersects(left, up, width, height)) {
				renderer.drawImage(ent.getSprite().getImage(), (int) (ent.getBounds().getX() - left), (int) (ent.getBounds().getY() - up), (int) (ent.getBounds().getMaxX() - left), (int) (ent.getBounds().getMaxY() - up), (int) ent.getSprite().getBoundX1(), (int) ent.getSprite().getBoundY1(), (int) ent.getSprite().getBoundX2(), (int) ent.getSprite().getBoundY2(), null);
				renderer.setColor(Color.red);
				// renderer.fillOval((int) (ent.getPosX() - left) - 1, (int) (ent.getPosY() - up) - 1, (int) (ent.getPosX() - left) + 1, (int) (ent.getPosY() - up) + 1);
			}
		}
		
		renderer.fillRect(400, 300, 1, 1);
	}
	
	@Override
	public void update(float dt) {
		
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public BufferedImage getSnapshot() {
		return snapshot;
	}
}

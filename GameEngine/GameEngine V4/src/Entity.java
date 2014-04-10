
public class Entity extends GameComponent {
	
	// Represent the bound of the entity
	private Rectangle bound;
	
	// X coordinate
	private float x;
	
	// Y coordinate
	private float y;
	
	// Graphical representation of the Entity
	private Sprite sprite;
	
	// Default constructor
	public Entity() {
		bound = new Rectangle();
	}
	
	// Getter for sprite
	public Sprite getSprite() {
		return sprite;
	}
	
	// Getter for bound
	public Rectangle getBound() {
		return bound;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	// Warp the entity to the coordinate (x,y)
	public void warp(float x, float y) {
		this.x = x;
		this.y = y;
		
		bound.x = x - bound.width / 2f;
		bound.y = y - bound.height / 2f;
	}
	
	// Translate the entity by the vector (dx, dy)
	public void translate(float dx, float dy) {
		x += dx;
		y += dy;
		bound.x += dx;
		bound.y += dy;
	}
	
	// Scale the entity by the value sx, sy
	public void scale(float sx, float sy) {
		bound.width *= sx;
		bound.height *= sy;
		
		bound.x = x - bound.width / 2f;
		bound.y = y - bound.height / 2f;
	}

	public void setSprite(Sprite s) {
		// TODO Auto-generated method stub
		sprite = s;
	}
}

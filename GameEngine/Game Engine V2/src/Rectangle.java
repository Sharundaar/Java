
public class Rectangle {
	public float x;
	public float y;
	public float width;
	public float height;
	
	public Rectangle() {
		x = y = width = height = 0f;
	}
	
	public Rectangle(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public float getMaxX() {
		return x + width;
	}
	
	public float getMaxY() {
		return y + height;
	}
}

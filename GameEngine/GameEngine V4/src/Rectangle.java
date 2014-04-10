public class Rectangle {
	public float x, y, width, height;
	
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
	
	public boolean isColliding(Rectangle rect) {
		float x1 = x, x2 = getMaxX(), x3 = rect.x, x4 = rect.getMaxX();
		float y1 = y, y2 = getMaxY(), y3 = rect.y, y4 = rect.getMaxY();
		
		return (((x3 < x2 && x3 > x1) || (x4 < x2 && x4 > x1)) && ((y3 < y2 && y3 > y1) || (y4 < y2 && y4 > y1)));
	}
}

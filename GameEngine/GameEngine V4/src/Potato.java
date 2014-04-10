
public class Potato extends Entity {
	public Potato() {
		super();
		dx = 0;
		dy = 0;
	}
	
	public float dx, dy;
	
	public void update(float dt) {
		translate(dx, dy);
	}
}

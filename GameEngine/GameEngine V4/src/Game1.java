import java.awt.event.KeyEvent;


public class Game1 extends Game{
	public Game1() {
		super();
	}
	
	Potato ent, ent2;
	
	protected void load() {
		ent = new Potato();
		Sprite s = new Sprite();
		s.loadImage("C:\\Users\\Fireflown\\Pictures\\Sprite\\Link1.bmp");
		ent.setSprite(s);
		ent.getBound().width = 50;
		ent.getBound().height = 50;
		ent.scale(1, 1);
		ent.translate(30, 30);
		
		ent2 = new Potato();
		ent2.setSprite(s);
		ent2.getBound().width = 50;
		ent2.getBound().height = 50;
		ent2.scale(1, 1);
		ent2.translate(500, 30);
		
		ent.dy = 0;
		ent2.dy = 0;
		
		ent.dx = 2;
		ent2.dx = -2;
	}
	
	protected void update(float dt) {
		/*if(control.isKeyPressed(KeyEvent.VK_RIGHT)) {
			ent.translate(0.01f * dt, 0);
		}*/
		
		ent.update(dt);
		ent2.update(dt);
		
		if(ent.getBound().x < 0 || ent.getBound().x > 800) {
			ent.translate(-ent.dx, -ent.dy);
			ent.dx = -ent.dx;
		}
		
		if(ent.getBound().y < 0 || ent.getBound().y > 600) {
			ent.translate(-ent.dx, -ent.dy);
			ent.dy = -ent.dy;
		}
		
		if(ent2.getBound().x < 0 || ent2.getBound().x > 800) {
			ent2.translate(-ent2.dx, -ent2.dy);
			ent2.dx = -ent2.dx;
		}
		
		if(ent2.getBound().y < 0 || ent2.getBound().y > 600) {
			ent2.translate(-ent2.dx, -ent2.dy);
			ent2.dy = -ent2.dy;
		}
		
		if(ent.getBound().isColliding(ent2.getBound())) {
			ent.translate(-ent.dx, -ent.dy);
			ent2.translate(-ent2.dx, -ent2.dy);
			/*
			float dirx = (ent.getX() - ent2.getX()), diry = (ent.getX() - ent2.getX());
			float n = (float) Math.sqrt(dirx * dirx + diry * diry);
			dirx = dirx / n;
			diry = diry / n;
			
			ent.dx = 2 * dirx;
			ent.dy = 2 * diry;
			
			ent2.dx = 2 * -dirx;
			ent2.dy = 2 * -diry;
			*/
			
			ent.dx = -ent.dx;
			ent2.dx = -ent2.dx;
		}
		
	}
	
	protected void draw() {
		spriteBatch.clearScreen();
		
		spriteBatch.drawEntity(ent);
		spriteBatch.drawEntity(ent2);
		window.draw();
	}
}

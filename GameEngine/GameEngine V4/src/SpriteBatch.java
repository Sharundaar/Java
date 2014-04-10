import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class SpriteBatch {
	Sprite sprite;
	BufferedImage image;
	Graphics2D g;
	
	public final static int BUFFER_WIDTH = 2000, BUFFER_HEIGHT = 2000;
	
	public SpriteBatch() {
		sprite = new Sprite();
		image = new BufferedImage(BUFFER_WIDTH, BUFFER_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		g = image.createGraphics();
		sprite.setImage(image);
		sprite.getBound().width = 800;
		sprite.getBound().height = 600;
	}
	
	public void drawSprite(Rectangle position, Sprite s) {
		g.drawImage(s.getImage(), (int) position.x, (int) position.y, (int) position.getMaxX(), (int) position.getMaxY(),
				(int) s.getBound().x, (int) s.getBound().y, (int) s.getBound().getMaxX(), (int) s.getBound().getMaxY(), null);
	}
	
	public void drawEntity(Entity ent) {
		drawSprite(ent.getBound(), ent.getSprite());
	}
	
	public Graphics2D getGraphics() {
		return g;
	}

	public Sprite getSprite() {
		// TODO Auto-generated method stub
		return sprite;
	}

	public void clearScreen() {
		// TODO Auto-generated method stub
		g.setColor(Color.black);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
	}
}

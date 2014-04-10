import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;


public class SpriteBatch {
	protected BufferedImage buffer;
	protected Graphics2D batch;
	
	protected Sprite sprite;
	
	public final int BUFFER_WIDTH = 1000;
	public final int BUFFER_HEIGHT = 1000;
	
	public SpriteBatch() {
		buffer = new BufferedImage(BUFFER_WIDTH, BUFFER_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		batch = buffer.createGraphics();
		sprite = new Sprite(buffer);
	}
	
	public Sprite getBuffer() {
		return sprite;
	}
	
	public boolean drawImage(Image image, float dx1, float dy1, float dx2, float dy2, float sx1, float sy1, float sx2, float sy2) {
		return batch.drawImage(image, (int) dx1, (int) dy1, (int) dx2, (int) dy2, (int) sx1, (int) sy1, (int) sx2, (int) sy2, null);
	}
}

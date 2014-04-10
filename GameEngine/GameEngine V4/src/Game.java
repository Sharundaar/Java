import java.awt.Graphics2D;


public class Game {
	
	protected boolean running;
	protected static final long UPDATE_LIMITER = 10 * 1000000;
	protected static final long NANOSECOND = 1000000;
	
	protected GameWindow window;
	protected SpriteBatch spriteBatch;
	protected Control control;
	
	public Game() {
		// Allocating memory
		window = new GameWindow();
		spriteBatch = new SpriteBatch();
		window.getViewport().setSprite(spriteBatch.getSprite());
		control = new Control();
		window.addKeyListener(control);
		
		// Calling functions in the right order
		initialize();
		load();
		gameLoop();
		unload();
	}
	
	protected void initialize() {
		
	}
	
	protected void load() {
	}
	
	private void gameLoop() {
		long currentTime;
		long lastTime;
		float dt;
		
		running = true;
		lastTime = System.nanoTime();
		while(running) {
			currentTime = System.nanoTime();
			dt = currentTime - lastTime;
			lastTime = currentTime;
			
			update(dt / NANOSECOND);
			draw();
			
			try {
				long w = (UPDATE_LIMITER - (System.nanoTime() - lastTime)) / NANOSECOND;
				Thread.sleep(w >= 0 ? w : 0);
			} catch (InterruptedException e) {
			}
		}
	}
	
	protected void update(float dt) {
		
	}
	
	protected void draw() {
		
	}
	
	protected void unload() {
		
	}
	
	public Control getControl() {
		return control;
	}
}


public class Game {
	protected GameWindow window;
	
	protected SpriteBatch spriteBatch;
	protected boolean running;
	
	public Game() {
		running = false;
		window = new GameWindow();
		spriteBatch = new SpriteBatch();
		
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
			
			update(dt);
			render();
			
			try {
				Thread.sleep((long) (10000000 - System.nanoTime() + lastTime) / 1000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	protected void update(float dt) {
		
	}
	
	protected void render() {
		
	}
	
	protected void unload() {
		
	}
}

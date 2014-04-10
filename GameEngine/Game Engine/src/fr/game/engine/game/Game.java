package fr.game.engine.game;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import fr.game.engine.scripts.ScriptBank;
import fr.game.engine.view.GameWindow;

public class Game {
	protected SceneManager sceneManager;
	protected GameWindow window;
	protected Control control;
	protected SpriteBank spriteBank;
	protected ScriptBank scriptBank;
	protected PhysicsEngine physicsEngine;
	
	protected boolean running;
	protected Logger log;
	
	
	public Game() {
		log = Logger.getLogger("Game");
		try {
			FileHandler fh = new FileHandler("game.log");
			log.addHandler(fh);
			SimpleFormatter format = new SimpleFormatter();
			fh.setFormatter(format);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("Creating the game window");
		window = new GameWindow();
		log.info("Creating the scene handler");
		sceneManager = new SceneManager();
		log.info("Creating physics engine");
		physicsEngine = new PhysicsEngine(this);
		log.info("Creating the control handler");
		control = new Control();
		window.addKeyListener(control);
		log.info("Creating the bank objects");
		scriptBank = new ScriptBank();
		spriteBank = new SpriteBank();
		
		initialize();
		load();
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				loop();
			}});
		t.start();
	}
	
	protected void initialize() {
		
	}
	
	protected void load() {
		
	}
	
	private void loop() {
		log.info("Starting the game loop");
		
		long now, before;
		float dt;
		running = true;
		
		before = System.nanoTime();
		
		do {
			now = System.nanoTime();
			dt = (now - before) / 1000000f;
			before = now;
			
			update(dt);
			draw();
			
			try {Thread.sleep((10000000 - (System.nanoTime() - before)) / 1000000);}catch(Exception e) {}
		} while(running);
		
		log.info("Unloading the engine");
		window.dispose();
	}
	
	protected void update(float dt) {
		
	}
	
	protected void draw() {
		window.render();
	}
	
	public SceneManager getSceneManager() {
		return sceneManager;
	}
	
	public Control getControl() {
		return control;
	}
}

package fr.game.engine.game;

import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Logger;

import fr.game.engine.entity.Sprite;

public class SpriteBank {
	Hashtable<String, Sprite> sprites;
	
	public SpriteBank() {
		sprites = new Hashtable<String, Sprite>();
	}
	
	public void addSprite(Sprite sprite) {
		if(sprite != null)
			sprites.put(sprite.getName(), sprite);
	}
	
	public void removeSprite(String name) {
		sprites.remove(name);
	}
	
	public Sprite getSprite(String name) {
		return sprites.get(name);
	}
	
	public void loadSprite(String name, String path) {
		try {
			addSprite(new Sprite(name, Sprite.loadImage(path)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Logger.getLogger("Game").severe("Unable to load the image '"+path+"'");
			e.printStackTrace();
		}
	}

	public void loadSprites() {
		// TODO Auto-generated method stub
		loadSprite("Mario", "C:\\Users\\Fireflown\\Pictures\\Sprite\\mario\\mario animation sheet.png");
		loadSprite("LinkA", "C:\\Users\\Fireflown\\Pictures\\Sprite\\Link1.bmp");
		loadSprite("LinkB", "C:\\Users\\Fireflown\\Pictures\\Sprite\\Link2.bmp");
		loadSprite("LinkB", "C:\\Users\\Fireflown\\Pictures\\Sprite\\Link4.bmp");
		loadSprite("Ground", "C:\\Users\\Fireflown\\Pictures\\Sprite\\mario\\Ground.png");
	}
}

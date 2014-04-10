package fr.game.engine.entity;

import java.io.IOException;

import fr.game.engine.game.Game;

public class EntityFactory {
	
	static public Entity createBasicEntity(Game game, String name, String imagePath) {
		Entity result = null;
		
		try {
			result = new Entity(game, 0, 0, 100, 100, new Sprite("default", Sprite.loadImage(imagePath)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	static public Mario createMario(Game game, String imagePath) {
		Mario result = null;
		try {
			result = new Mario(game, 0, 0, 100, 100, new Sprite("default", Sprite.loadImage(imagePath)));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}

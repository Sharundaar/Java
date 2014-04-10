package fr.game.engine.view;

import fr.game.engine.entity.Entity;
import fr.game.engine.game.Game;

public class FollowCamera extends Camera{
	Entity followed;
	
	public FollowCamera(Entity ent, float width, float height, Game game) {
		super(ent.getPosX(), ent.getPosY(), width, height, game);
		// TODO Auto-generated constructor stub
		followed = ent;
	}
	
	@Override
	public void update(float dt) {
		x = followed.getPosX();
		y = followed.getPosY();
	}
	

}

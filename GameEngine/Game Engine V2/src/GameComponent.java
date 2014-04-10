
public abstract class GameComponent {
	protected Game game;
	
	public GameComponent(Game game) {
		this.game = game;
	}
	
	public abstract void update(float dt);
}

import javax.swing.JFrame;


public class GameWindow extends JFrame {
	private Viewport viewport;
	
	public GameWindow() {
		super();
		
		this.setTitle("toto");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 600);
		
		viewport = new Viewport();
		this.setContentPane(viewport);
		
		
		this.setVisible(true);
	}
	
	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}
	
	public Viewport getViewport() {
		return viewport;
	}

	public void draw() {
		// TODO Auto-generated method stub
		viewport.repaint();
	}
}

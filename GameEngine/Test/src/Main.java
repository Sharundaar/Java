import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class Main implements KeyListener {
	static JFrame frame = new JFrame();
	static JLabel label = new JLabel("No Key Pressed");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main main = new Main();
		frame.setTitle("Key Tests");
		frame.add(label);
		frame.addKeyListener(main);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(50, 30);
		frame.setVisible(true);
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		label.setText(""+arg0.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

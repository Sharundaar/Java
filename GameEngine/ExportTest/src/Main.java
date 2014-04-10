import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Display display = new Display();
		try {
			display.setImage(ImageIO.read(new File("image.png")));
		} catch(IOException e) {
			e.printStackTrace();
		}
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setLayout(new FlowLayout());
		frame.add(display);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}

}

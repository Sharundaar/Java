import java.awt.image.BufferedImage;


public class Camera {
	private BufferedImage render;
	private int width = 512;
	private int height = 512;
	
	public Camera() {
		render = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}
	
	
	public BufferedImage render() {
		for(int x=0 ; x<width ; ++x) {
			for(int y=0 ; y<height ; ++y) {
				render.setRGB(x, y, ((0x0 + x) * y) % 256);
			}
		}
		
		return render;
	}


	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}

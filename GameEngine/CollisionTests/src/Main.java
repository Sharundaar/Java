import javax.swing.JFrame;


public class Main {

	public static void tick() {
		
	}
	
	public static void main(String[] args) {
		Clock clk = new Clock();
		
		clk.update();
		System.out.println("tick = "+clk.getTick());
		
		clk.update();
		System.out.println("tick = "+clk.getTick());
		
	}

}

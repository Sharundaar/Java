import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;


public final class Interpolation {
	
	public static float interpolateLinear(float x1, float y1, float x2, float y2, float x) {
		if(x2 == x1)
			return Float.POSITIVE_INFINITY;
		
		float a = (y2-y1) / (x2-x1);
		float b = y2 - x2 * a;
		
		return a*x + b;
	}
	
	
	/*
	 * returns a value between x1 and x2
	 *  pre : x1 < x2, 0 <= t <= 1
	 *  t = 0 gets x1, t = 1 gets x2
	 */
	public static float interpolate(float x1, float x2, float t) {
		return t*(x2 - x1) + x1;
	}
	
	/* 
	 * returns a value on the line passing by (x1, y1) and (x2, y2)
	 * 	pre : x1 < x2, 0 <= t <= 1
	 * 	t = 0 gets y1, t = 1 gets y2
	 */
	public static float interpolate(float x1, float y1, float x2, float y2, float t) {
		float x = interpolate(x1, x2, t);
		return interpolate(y1, y2, (x - x1) / (x2 - x1));
	}


	public static float interpolateLinear(float x1, float y1, float x2, float y2, float z1,
			float z2, float z3, float z4, float x, float y) {
		// TODO Auto-generated method stub
		float v1 = interpolateLinear(x1, z1, x2, z2, x);
		float v2 = interpolateLinear(x1, z3, x2, z4, x);
		
		return interpolateLinear(y1, v1, y2, v2, y);
	}
	
	public static BufferedImage interpolate(BufferedImage img1, BufferedImage img2, float t) {
		if(img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight() || img1.getType() != img2.getType())
			return null;
		
		BufferedImage result = new BufferedImage(img1.getWidth(), img1.getHeight(), img1.getType());
		WritableRaster rast1 = img1.getRaster();
		WritableRaster rast2 = img2.getRaster();
		WritableRaster rast3 = result.getRaster();
		
		
		for(int x = 0 ; x<img1.getWidth() ; ++x) {
			for(int y=0 ; y<img2.getHeight() ; ++y) {
				// result.setRGB(x, y, (int) interpolate(0, img1.getRGB(x, y), 1, img2.getRGB(x, y), t));
				float[] arr1 = null, arr2 = null, arr3 = null;
				arr1 = rast1.getPixel(x, y, arr1);
				arr2 = rast2.getPixel(x, y, arr2);
				arr3 = rast3.getPixel(x, y, arr3);
				for(int i=0 ; i<arr1.length ; ++i) {
					arr3[i] = interpolate(0, arr1[i], 1, arr2[i], t);
				}
				rast3.setPixel(x, y, arr3);
			}
		}
		return result;
	}
	
	public static BufferedImage interpolate(BufferedImage img1, BufferedImage img2, BufferedImage result, float t) {
		if(img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight() || img1.getType() != img2.getType() || result.getWidth() != img1.getWidth() || result.getHeight() != img1.getHeight() || result.getType() != img1.getType())
			return null;
		
		WritableRaster rast1 = img1.getRaster();
		WritableRaster rast2 = img2.getRaster();
		WritableRaster rast3 = result.getRaster();
		
		
		for(int x = 0 ; x<img1.getWidth() ; ++x) {
			for(int y=0 ; y<img2.getHeight() ; ++y) {
				// result.setRGB(x, y, (int) interpolate(0, img1.getRGB(x, y), 1, img2.getRGB(x, y), t));
				float[] arr1 = null, arr2 = null, arr3 = null;
				arr1 = rast1.getPixel(x, y, arr1);
				arr2 = rast2.getPixel(x, y, arr2);
				arr3 = rast3.getPixel(x, y, arr3);
				for(int i=0 ; i<arr1.length ; ++i) {
					arr3[i] = interpolate(0, arr1[i], 1, arr2[i], t);
				}
				rast3.setPixel(x, y, arr3);
			}
		}
		return result;
	}
}

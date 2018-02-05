import java.awt.Color;
import java.awt.Graphics;

public class SquareWithCircles extends Shape {
	
	private static final double  OFFSET_RACTANGEL =1.2;
	private static final double  OFFSET_X_RIGHT_CIRCLE = 1.5;
	private static final int  OFFSET_X_LEFT_CIRCLE = 6;
	private static final double  OFFSET_y_CIRCLE = 3.7;
	private static final int  DEVISIOR_RADIUSE_CIRCLE = 3;
	
	private int x;
	private int y;
	private int radius;
	
	
	public SquareWithCircles(Color color) {
		super(color);
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}


	public int getCenterY() {
		return y;
	}

	public int getCenterX() {
		return x;
	}


	public int getRadius() {
		return radius;
	}
	

	
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(x, y, (int)(radius/OFFSET_RACTANGEL), (int)(radius/OFFSET_RACTANGEL));
		g.fillOval((int)(x+radius/OFFSET_X_RIGHT_CIRCLE),(int)(y+radius/OFFSET_y_CIRCLE),radius/DEVISIOR_RADIUSE_CIRCLE,radius/DEVISIOR_RADIUSE_CIRCLE);
		g.fillOval((int)(x-radius/OFFSET_X_LEFT_CIRCLE),(int)(y+radius/OFFSET_y_CIRCLE),radius/DEVISIOR_RADIUSE_CIRCLE,radius/DEVISIOR_RADIUSE_CIRCLE);
		
	}
	


}

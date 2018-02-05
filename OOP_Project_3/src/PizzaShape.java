import java.awt.Color;
import java.awt.Graphics;

public class PizzaShape extends Shape {

	private static final int ANGLE_ADDER =44; //spaces between slices
	private static final int SLICE_ANGLE_PIZZA = 30;
	private static final int MAX_ANGLE = 360;
	
	private int x;
	private int y;
	private int radius;
	
	public PizzaShape(Color color) {
		super(color);
	}


	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	
	public int getRadius() {
		return radius;
	}
	
	public void setX(int x) {
		this.x = x;	
	}


	public void setY(int y) {
		this.y = y;
		
	}

	public int getCenterX() {
		return x;
	}
	
	public int getCenterY() {
		return y;
	}
	

	
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		for (int i = 0; i < MAX_ANGLE; i+=ANGLE_ADDER) {
			g.fillArc(x,y, radius, radius, i, SLICE_ANGLE_PIZZA);
		}
		
	}


}

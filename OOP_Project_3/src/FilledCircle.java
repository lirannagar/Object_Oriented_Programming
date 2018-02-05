import java.awt.Color;
import java.awt.Graphics;

public class FilledCircle extends  Shape{

	private int radius;
	private int x;
	private int y;


	public FilledCircle(Color color) {
		super(color);
	}

	public void setRadius(int radius) {
		this.radius = radius;

	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
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
		g.fillOval(x,y,radius,radius);
	}


}

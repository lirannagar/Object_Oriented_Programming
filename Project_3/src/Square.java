import java.awt.Color;
import java.awt.Graphics;

public class Square extends Shape{

	private int width;
	private int xTopLeft;
	private int yTopLeft;

	public Square(Color color) {
		super(color);
	}

	public void setWidth(int slotMachinLength) {
		this.width = slotMachinLength;
	}

	public void setTopLeftY(int yTopLeft) {
		this.yTopLeft = yTopLeft;
	}

	public void setTopLeftX(int xTopLeft){
		this.xTopLeft = xTopLeft;
	}
	
	public int getTopLeftX() {
		return xTopLeft;
	}
	
	public int getTopLeftY() {
		return yTopLeft;
	}

	public int getWidth() {
		return width;
	}

	public int getCenterY() {
		return yTopLeft;
	}

	public int getCenterX() {
		return xTopLeft;
	}

	public void draw(Graphics g) {
		g.drawRect(xTopLeft, yTopLeft, width,  width);

	}



}

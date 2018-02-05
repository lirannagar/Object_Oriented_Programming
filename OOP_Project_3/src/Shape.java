import java.awt.Color;

public abstract class  Shape  implements iDrawnShape {

	private Color color;


	public Shape(Color color){
		setColor(color);	
	}


	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor(){
		return color;
	}

	
	/**
	 * Checking if equal Shape by color and Class
	 * */
	public boolean equals(Object object){
			if(this.color.equals(getColor()) && this.getClass().getName().equals( object.getClass().getName() ))
				return true;
		return false;
	}
	

	

	public abstract int  getCenterX();


	public abstract int getCenterY();

}

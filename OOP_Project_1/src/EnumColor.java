import javafx.scene.paint.Color;

public enum EnumColor {

	START(Color.BLACK), 
	EDITANDCREATE(Color.BLUE), 
	NEXTANDLAST(Color.GREEN),
	PREANDFIRST(Color.RED);
	
	private final Color color;


	EnumColor(Color color) {
		 
		this.color = color;
	}

	
	public Color getColor() {
		return this.color;
	}
}




import javax.swing.JApplet;
import javax.swing.JFrame;





public class HW3_LiranNagar  extends JApplet	{

	public static JFrame f = new JFrame("HW3");
	


	@Override
	public void init() {
		setSize(WIDTH_AND_HEIGHT, WIDTH_AND_HEIGHT);
		add(new PaintAndRestore());
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH_AND_HEIGHT = 700;


	public static void main(String[] args)  {


		f.add(new HW3_LiranNagar());  //active
		PaintAndRestore p = new PaintAndRestore();
		f.add(p);
		activeFrame();
		



	}






	public static void activeFrame( ) {
		f.setResizable(false);
		f.setSize(WIDTH_AND_HEIGHT, WIDTH_AND_HEIGHT);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);	

	}








}

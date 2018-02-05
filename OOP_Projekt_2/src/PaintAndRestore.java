
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;



public class PaintAndRestore extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int theX;
	private int theY;
	private ArrayList<Integer> arrayCordinateX = new ArrayList<Integer>();
	private ArrayList<Integer> arrayCordinateY = new ArrayList<Integer>();
	private ControlPanel control ;
	private int counter;
	private boolean drawAllowed = true;
	private boolean reStart = false;
	private boolean flag = false; 
	private boolean deleteDraw = false; 



	public PaintAndRestore(){
		setFocus() ;
		//Click mouse to draw
		addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				flag = true; 
				theX = e.getX();
				theY = e.getY();
				repaint();
				saveTheCordinates(e);
			}
			//Save the Cordinates of the painting in the arrays
			private void saveTheCordinates(MouseEvent e) {
				arrayCordinateX.add(e.getX());
				arrayCordinateY.add(e.getY());
			}
		});

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				//if user clicked on Ctrl&Space open controlPanel and block drawing
				if((e.getKeyCode() == KeyEvent.VK_SPACE)&& ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 )){
					drawAllowed = false;
					control = new ControlPanel();
					//Restoring the painting
					
					//if user clicked on Enter at keyboard
					control.addKeyListener(new KeyAdapter() {
						public void keyPressed(KeyEvent e){
							if(e.getKeyCode() == KeyEvent.VK_ENTER)
								restoringPainting();
							}
						});
					//if user clicked on the OK Button
					control.getOkButton().addActionListener(new ActionListener() { 
						public void actionPerformed(ActionEvent e) { 
							if((e.getSource() ==control.getOkButton())) {
								restoringPainting();
							}
						} 
					} );
				}
			}
		} );
	}



	public ControlPanel getControl(){
		return control;
	}


	public void setFocus() { 
		setFocusable(true); 
		requestFocusInWindow();
	}

	public void paintComponent(Graphics g) {
		if(deleteDraw){
			super.paintComponent(g);
			deleteDraw = false;
		}
		g.setColor(Color.red);
		g.setFont( new Font ("Arial",Font.BOLD,30));
		g.drawString("RECORDING...", 10,30);
		if(flag){	
			if(drawAllowed){
				g.setColor(Color.BLACK);
				g.fillOval(theX, theY, 10, 10);
			}
		}
		if(reStart){
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			g.setFont( new Font ("Arial",Font.BOLD,30));
			g.drawString("RESTORING...", 10,30);

			for (int j = 0; j <=counter; j++) {
				g.setColor(getControl().getColor());
				g.fillOval(arrayCordinateX.get(j), arrayCordinateY.get(j),getControl().getThickness(), getControl().getThickness());
				if(j == arrayCordinateX.size()-1){
					//when the Restoration end
					arrayCordinateX = new ArrayList<Integer>();
					arrayCordinateY = new ArrayList<Integer>();
					//when the user want to draw again
					drawAllowed = true;
					reStart = false;
					deleteDraw = true;
				}
			}

		}

	}


	public void restoringPainting(){
		Timer 	timer= new Timer();
		counter = 0;
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (counter < arrayCordinateX.size() - 1) {
					counter++;
					repaint();
				}
				else
					timer.cancel();
			}
		}, 0,getControl().getSpeedSize());
		reStart = true;	
		control.dispose();
	}
















}













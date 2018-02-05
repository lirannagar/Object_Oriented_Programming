import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class SlotMachinePanel extends JPanel implements ISlotMachine,ActionListener {


	private final static double SLOT_MACHINE_WIDTH_FRACTION = 0.8;
	private final static int    RADIUS_DIVISOR = 2;
	private final static double SLOT_MACHINE_HEIGHT_DIVISOR = 1.4;
	private final static double SLOT_LITTLE_SQUARE = 0.2;
	private final static int    DIVISOR_LITTLE_SQUARE = 3;
	private final static double OFFSET_LEFT_SQUARE = 2.5;
	private final static int    OFFSET_RIGHT_SQUARE = 5;
	private final static int    OFFSET_MID_SQUARE = 10;
	private final static int    FIRST_CUBICLE = 1;
	private final static int    SECOND_CUBICLE = 2;
	private final static int    THIRD_CUBICLE = 3;



	private Square mainMachineCell = new Square(Color.black);
	private Square rightCell = new Square(Color.black);
	private Square leftCell = new Square(Color.black);
	private Square midCell = new Square(Color.black);
	private JButton buttonPlay  = new JButton("Play");
	private JLabel labelPlayMassage  = new JLabel("Click to play", (int) CENTER_ALIGNMENT);
	private boolean ifTheButtomClicked = false;
	private boolean ifWin = false;




	/**
	 * Check if the draws are equals, and give the final result of the game
	 */
	@Override
	public void play() {

		if(ifWin){
			labelPlayMassage.setText("You WON!!!");
			ifWin = false;
		}
		else
			labelPlayMassage.setText("You LOST!!!");
			
	}





	/**
	 * Cell the play method if the button clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonPlay){
			ifTheButtomClicked =  true;
			repaint();
		}


	}


	/**
	 * Creating the Label at the bottom of the frame
	 */
	public JLabel getLabel(){
		return labelPlayMassage;
	}




	@Override
	public Shape getShapeAtIndex(int index) {

		int	ran= (int)(Math.random()*3); 

		switch(index){
		case FIRST_CUBICLE:
			return createShape(ran,leftCell);
			
		case SECOND_CUBICLE:
			return createShape(ran,midCell);
			
		case THIRD_CUBICLE: 
			return createShape(ran,rightCell);
		}
		return null;
	}

	/**
	 * Creating the button
	 */
	public JButton getButton(){
		return buttonPlay;
	}




	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int panelWidth = getWidth();
		int panelHeight = getHeight();

		//get the shortest length when resized
		int minPanelLength = Math.min(panelWidth, panelHeight);


		double slotMachineWidth = minPanelLength * SLOT_MACHINE_WIDTH_FRACTION;

		int xCenter = panelWidth / 2;
		int yCenter = panelHeight / 2;

		//set the squares for the slot machine skeleton
		setSquares((int) slotMachineWidth, xCenter, yCenter);
		g.setColor(Color.black);
		//g.drawString(startString, panelWidth/2, panelHeight-4);
		mainMachineCell.draw(g);
		leftCell.draw(g);
		rightCell.draw(g);
		midCell.draw(g);

		// if the user click the button, the squares start filled
		if(ifTheButtomClicked){
			fillTheSquaresAndChackEquals(g);
			play();
		}



	}
	/**
	 * Fill the three cubicles with the draws
	 */
	private void fillTheSquaresAndChackEquals(Graphics g) {
		Shape shapOne =  getShapeAtIndex(FIRST_CUBICLE);
		Shape shapTwo = getShapeAtIndex(SECOND_CUBICLE);
		Shape shapTree = getShapeAtIndex(THIRD_CUBICLE);
		shapOne.draw(g);
		shapTwo.draw(g);
		shapTree.draw(g);
		if(shapOne.equals(shapTwo) &&  shapTwo.equals(shapTree) && shapOne.equals(shapTree))
			ifWin = true;
	}


	private void setSquares(int slotMachinLength,int xCenter, int yCenter) {
		mainMachineCell.setWidth(slotMachinLength);
		mainMachineCell.setTopLeftX(xCenter - (slotMachinLength / 2));
		int mainBottomY = yCenter - slotMachinLength / 2;
		mainMachineCell.setTopLeftY((int)(mainBottomY/SLOT_MACHINE_HEIGHT_DIVISOR));

		leftCell.setWidth((int)(SLOT_LITTLE_SQUARE*slotMachinLength));
		leftCell.setTopLeftX((int)(xCenter - (slotMachinLength /OFFSET_LEFT_SQUARE)));
		int mainLeftY = yCenter - slotMachinLength /DIVISOR_LITTLE_SQUARE;
		leftCell.setTopLeftY((int)(mainLeftY/SLOT_MACHINE_HEIGHT_DIVISOR));

		rightCell.setWidth((int)(SLOT_LITTLE_SQUARE*slotMachinLength));
		rightCell.setTopLeftX((int)(xCenter + (slotMachinLength /OFFSET_RIGHT_SQUARE)));
		int mainRightY = yCenter - slotMachinLength /DIVISOR_LITTLE_SQUARE;
		rightCell.setTopLeftY((int)(mainRightY/SLOT_MACHINE_HEIGHT_DIVISOR));

		midCell.setWidth((int)(SLOT_LITTLE_SQUARE*slotMachinLength));
		midCell.setTopLeftX((int)(xCenter - (slotMachinLength /OFFSET_MID_SQUARE)));
		int mainMidY = yCenter - slotMachinLength /DIVISOR_LITTLE_SQUARE;
		midCell.setTopLeftY((int)(mainMidY/SLOT_MACHINE_HEIGHT_DIVISOR));
	}

	/**
	 * sets a FilledCircle size to fit the center of a cell
	 */
	private Shape createShape(int index, Square cell) {

		switch (index) {
		case 0:
			FilledCircle fc = new FilledCircle(Color.red);
			setFilledCircle(fc, cell);
			return fc;

		case 1:
			SquareWithCircles swc = new SquareWithCircles(Color.BLACK);
			setSquareWithCircles(swc, cell);
			return swc;

		case 2:
			PizzaShape ps = new PizzaShape(Color.orange);
			setPizza(ps, cell);
			return ps;
		}
		return null;
	}

	/**
	 * sets a FilledCircle size to fit the center of a cell
	 */
	private void setFilledCircle(FilledCircle fc, Square cell){
		int radius = cell.getWidth() / RADIUS_DIVISOR;
		fc.setRadius(radius);
		fc.setX(cell.getCenterX()+radius / 2);
		fc.setY(cell.getCenterY()+radius / 2);
	}

	/**
	 * sets a PizzaShape size to fit the center of a cell
	 */
	private void setPizza(PizzaShape ps, Square cell){
		int radius = cell.getWidth() / RADIUS_DIVISOR;
		ps.setRadius(radius);
		ps.setX(cell.getCenterX()+radius / 2);
		ps.setY(cell.getCenterY()+radius / 2);
	}

	/**
	 * sets a SquareWithCircles size to fit the center of a cell
	 */
	private void setSquareWithCircles(SquareWithCircles swc, Square cell){
		int radius = cell.getWidth() / RADIUS_DIVISOR;
		swc.setRadius(radius);
		swc.setX(cell.getCenterX()+radius / 2);
		swc.setY(cell.getCenterY()+radius / 2);
	}











}

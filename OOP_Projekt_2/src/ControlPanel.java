import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class ControlPanel extends JFrame implements ActionListener,ContorolNeeds{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int WIDTH =270;
	private final int HEIGHT =270; 
	private final int DEFAULT_THICKNESS_ARRAY =0;
	private final int BIG_THICKNESS_ARRAY =1;
	private final int SMALL_THICKNESS_ARRAY =2;
	private final int REGULAR_SIZE = 10;
	private final int BIG_SIZE = 20;
	private final int SMALL_SIZE = 5;
	private final int MAX_SPEED_INDEX = 3;
	private final int MIDDLE_SPEED_INDEX = 2;
	private final int MIN_SPEED_INDEX = 1;
	private final int SPACING =1;
	private final int MAX_SPEED = 10;
	private final int MIN_SPEED = 70;
	private final int MID_SPEED = 25;


	private JComboBox<String> thickOptions;
	private JSlider speed = new JSlider();
	private  ButtonGroup group = new ButtonGroup();
	private JButton okButton = new JButton("OK");
	private Color color;
	private int speedSize;
	private int thicknessSize; 
	private JRadioButton red = new JRadioButton("Red");
	private JRadioButton black = new JRadioButton("Black");
	private JRadioButton blue = new JRadioButton("Blue");
	private JRadioButton green = new JRadioButton("Green");
	private JPanel mainPanel = new JPanel();  //panel that responsible to move all the members to the left except the OK button





	public ControlPanel(){
		add(mainPanel);
		FlowLayout lay=	new FlowLayout() ;
		lay.setAlignment(FlowLayout.LEFT);
		setLocationRelativeTo(null);
		mainPanel.setLayout(lay);
		setRadioButton();
		setThicknessOptions();
		setSpeedSlider();
		setOkButton();
		setDefaultSetting();
	}


	public void setFocus() { 
		setFocusable(true); 
		requestFocusInWindow();
	}

	public void setOkButton() {
		JPanel okPanel = new JPanel();
		okPanel.add(okButton);
		add(okPanel,BorderLayout.SOUTH);

	}

	public JButton getOkButton(){
		return okButton;
	}


	public void setSpeedSlider() {
		JPanel panelControlSlider = new JPanel();
		mainPanel.add(panelControlSlider);
		setSpeedSize(MAX_SPEED);
		//Sate default speed
		speed.setMaximum(MAX_SPEED_INDEX);
		speed.setMinimum(MIN_SPEED_INDEX);
		speed.setMajorTickSpacing(SPACING);
		speed.setPaintTicks(true);
		speed.setPaintLabels(true);
		panelControlSlider.add(speed);
		panelControlSlider.setBorder(new TitledBorder("Speed"));
		//the speed listener
		speed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				setFocus();
				if (!speed.getValueIsAdjusting()) {
					if( speed.getValue() == MIN_SPEED_INDEX){
						setSpeedSize(MIN_SPEED);
					}else if (speed.getValue() == MIDDLE_SPEED_INDEX){
						setSpeedSize(MID_SPEED);
					}else if (speed.getValue() == MAX_SPEED_INDEX){
						setSpeedSize(MAX_SPEED);
					}
				}
			}
		});
	}





	public void setRadioButton() {
		JPanel panelControlRadioButton = new JPanel();
		mainPanel.add(panelControlRadioButton);
		group.add(red);
		group.add(black);
		group.add(blue);
		group.add(green);
		black.addActionListener(this);
		black.setSelected(true);
		black.setMnemonic(KeyEvent.VK_B);
		red.addActionListener(this);
		red.setMnemonic(KeyEvent.VK_R);
		blue.addActionListener(this);
		blue.setMnemonic(KeyEvent.VK_L);
		green.addActionListener(this);
		green.setMnemonic(KeyEvent.VK_G);
		panelControlRadioButton.add(black);
		panelControlRadioButton.add(red);
		panelControlRadioButton.add(blue);
		panelControlRadioButton.add(green);
		panelControlRadioButton.setBorder(new TitledBorder("Color"));
	}



	public void setThicknessOptions() {
		JPanel panelControlThick = new JPanel();
		mainPanel.add(panelControlThick);
		String[] sizesString = {"Default thickness","Big thickness","Small thickness"};
		thickOptions = new JComboBox<String>(sizesString);
		//Sate default size
		setThickness(REGULAR_SIZE);
		thickOptions.addActionListener(this);
		panelControlThick.add(thickOptions);
	}






	private void setDefaultSetting() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(WIDTH, HEIGHT );
		setLocationRelativeTo(HW3_LiranNagar.f);
		setVisible(true);

	}







	@Override
	public void actionPerformed(ActionEvent e) {
		setFocus(); //if RadioButtons touched
		if(e.getSource() == red){
			setColor(Color.red);
		}else if(e.getSource() == blue){
			setColor(Color.blue);
		}else if(e.getSource() == green){
			setColor(Color.green);
		}else if(e.getSource() == black){
			setColor(Color.black);
			setFocus();//if ComboBox touched
		}else if(thickOptions.getSelectedIndex() ==DEFAULT_THICKNESS_ARRAY ){
			setThickness(REGULAR_SIZE);
		}else if(thickOptions.getSelectedIndex() ==BIG_THICKNESS_ARRAY ){
			setThickness(BIG_SIZE);
		}else if(thickOptions.getSelectedIndex() ==SMALL_THICKNESS_ARRAY ){
			setThickness(SMALL_SIZE);
		}
	}

	
	public void setColor(Color color){
		this.color = color;
	}

	public void setSpeedSize(int speedSize){
		this.speedSize = speedSize;
	}

	public int getSpeedSize(){
		return speedSize;
	}

	public void setThickness(int thicknessSize){
		this.thicknessSize = thicknessSize;
	}

	public int getThickness(){
		return thicknessSize;
	}

	public Color getColor(){
		return color;
	}





}

import java.awt.BorderLayout;
import java.awt.GridLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;



public class SlotMachineFrame extends JFrame  {

	private	SlotMachinePanel smp = new SlotMachinePanel();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public SlotMachineFrame(int w, int h){
	
		JPanel buttonPanel = new JPanel(new GridLayout(1, 1));	
		smp.add(buttonPanel);
		
		buttonPanel.add(smp.getButton());
		smp.getButton().addActionListener(smp);
		
		this.add(smp);
		this.setSize(w, h);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(smp.getButton(),BorderLayout.WEST);
		this.add(smp.getLabel(),BorderLayout.SOUTH);
		
		
		this.setVisible(true);
	}



		
	}











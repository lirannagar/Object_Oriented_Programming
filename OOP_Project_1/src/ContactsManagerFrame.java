import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javafx.scene.paint.Color;




public class ContactsManagerFrame extends JFrame implements IFinalStringSymbol,IFinalOutPutFromUser,IFinalInteger,IOptionFinalListener,IContactView, IContactRegistrable  {




	private static final long serialVersionUID = 1L;
	private String firstNameInputFromUser;
	private String lastNameInputFromUser;
	private String phoneNumberInputFromUser;
	private String pathFill;
	private JLabel 	lastNameLabel = new JLabel(LAST_NAME_LABEL_STRING);;
	private JLabel nameLabel = new JLabel(FIRST_NAME_LABEL_STRING);;
	private JTextField nameText = new JTextField(LENGTH_OF_TEXTFIELD);
	private JTextField lastNameText = new JTextField(LENGTH_OF_TEXTFIELD);
	private JTextField filePathTextField = new JTextField(LENGTH_OF_TEXTFIELD);
	private JTextField phoneText = new JTextField(LENGTH_OF_TEXTFIELD);
	private JButton nextContactButton = new JButton(NEXT_SYMBOL);
	private JButton lastBotton = new JButton(LAST_CONTACT_STRING_BUTTON);
	private JButton previousContactButton = new JButton(PREVIOUS_SYMBOL);
	private JButton exportBotton = new JButton(EXPORT_STRING_BUTTON);
	private String[] sizesString = {FIRST_FORMAT_FILE,SECOND_FORMAT_FILE,THIRD_FORMAT_FILE};
	private JComboBox<String> formatOptions = new JComboBox<String>(sizesString);
	private JButton firstBotton = new JButton(FIRST_CONTACT_STRING_BUTTON);
	private JLabel nameMinLabel = new JLabel(FIRST_NAME_LABEL_STRING);
	private JLabel firstNameData  = new JLabel(EMPTY_DATA);
	private JLabel lastNameMidLabel = new JLabel(LAST_NAME_LABEL_STRING);
	private JLabel lastNameData  = new JLabel(EMPTY_DATA);
	private JLabel PhoneMidLabel = new JLabel(PHONE_NUMBER_LABEL_STRING);
	private JLabel phoneData  = new JLabel(EMPTY_DATA);
	private JButton editContactBotton = new JButton(EDIT_CONTACT_STRING_BUTTON);
	private JButton updateBotton = new JButton(UPDATE_STRING_BUTTON);
	private JButton createBotton = new JButton(CREATE_STRING_BUTTON);
	private JLabel filePathLebel = new JLabel(FILE_PATH_LABEL_STRING);
	private JButton loadFileButton = new JButton(LOAD_FILE_STRING_BUTTON);
	private JLabel phoneLabel = new JLabel(PHONE_NUMBER_LABEL_STRING);
	private String[] fieldStrings = {FIRST_OPTION_SORT,SECOND_OPTION_SORT,THIRD_OPTION_SORT};
	private String[] fieldToSortStrings = {FIRST_FIELD_TO_SORT,SECOND_FIELD_TO_SORT,THIRD_FIELD_TO_SORT};
	private String[]  organizationSortOptions = {ASCENDING_STRING,DESCENING_STRING};
	private JButton doSortButton = new JButton(SORT_BUTTON_STRING);
	private JComboBox<String> sortFieldComboBox = new JComboBox<String>(fieldStrings);
	private JComboBox<String> fieldToSort = new JComboBox<String>(fieldToSortStrings);
	private JComboBox<String> organizationSortOne = new JComboBox<String>(organizationSortOptions);
	private JComboBox<String> organizationSortTwo = new JComboBox<String>(organizationSortOptions);
	private JButton showButton = new JButton(SHOW_BUTTON_STRING);
	private ArrayList<IListener> listeners = new ArrayList<IListener>();
	private boolean ifFileEmpty= false;
	private boolean ifFileExist= false;
	private boolean falseBoolean;
	private boolean trueBoolen =true;






	public ContactsManagerFrame() throws IOException  {
		//Main panel one		
		JPanel mainPanelOne = new JPanel(); 
		add(mainPanelOne,BorderLayout.NORTH);	
		firstPanel(mainPanelOne);
		//Main panel two		
		JPanel mainPaneltwo = new JPanel(); 
		add(mainPaneltwo,BorderLayout.CENTER);
		JPanel panelTwo = new JPanel(); 
		secondPanel(panelTwo);
		JPanel panelThree = new JPanel(); 
		thirdPanel(panelThree);
		mainPaneltwo.add(panelTwo);
		mainPaneltwo.add(panelThree);
		//Main panel three
		JPanel mainPanelthree = new JPanel(); 
		JPanel panelFour = new JPanel(); 
		mainPanelthree.setLayout(new GridLayout(ROWS_FOR_MAIN_PANEL_THREE,COLUMN_FOR_MAIN_PANEL_THREE,SPACES_FOR_MAIN_PANEL_THREE,SPACES_FOR_MAIN_PANEL_THREE));
		add(mainPanelthree,BorderLayout.SOUTH);
		fourPanel(panelFour);
		JPanel panelFive = new JPanel(); 
		mainPanelthree.add(panelFour);
		mainPanelthree.add(panelFive);
		fivePanel(panelFive);


	}
	private void firstPanel(JPanel panelOne) throws IOException {	
		panelOne.setLayout(new GridLayout(ROWS_OF_FIRST_PANEL, COLUMN_OF_FIRST_PANEL, SPACES_BETWEEN_LABEL_FIRST_PANEL, SPACES_BETWEEN_LABEL_FIRST_PANEL));
		panelOne.add(nameLabel);
		panelOne.add(nameText);
		panelOne.add(lastNameLabel);
		panelOne.add(lastNameText);
		panelOne.add(phoneLabel);
		panelOne.add(phoneText);
		panelOne.add(createBotton);
		panelOne.add(updateBotton);
		updateBotton.setEnabled(false);
		//setUp Contact
		createContact();
		updateContact();


	}	

	private void secondPanel(JPanel panelTwo) {
		JPanel panelLeft =new JPanel();
		panelTwo.add(panelLeft);
		secondPanelLeft(panelLeft);
		JPanel panelMid =new JPanel();
		panelTwo.add(panelMid);
		secondPanelMid(panelMid);
		JPanel panelRight =new JPanel();
		panelTwo.add(panelRight);
		secondPanelRight(panelRight);
	}
	private void secondPanelLeft(JPanel panelLeft) {
		panelLeft.setLayout(new GridLayout(ROWS_OF_BUTTONS, COLUMN_OF_BUTTONS,SPACES_BETWEEN_BUTTONS,SPACES_BETWEEN_BUTTONS));
		previousContactButtonClicked();
		firstButtonClicked();
		previousContactButton.setPreferredSize(new Dimension(HIGHT_AND_WIDTH_OF_SECOUND_PANEL_BUTTONS, HIGHT_AND_WIDTH_OF_SECOUND_PANEL_BUTTONS));
		panelLeft.add(previousContactButton);
		panelLeft.add(firstBotton);
	}
	private void secondPanelMid(JPanel panelMid) {
		panelMid.setLayout(new GridLayout(ROWS_OF_SECOND_PANEL_MID, COLUMN_OF_SECOND_PANEL_MID, SPACES_SECOND_PANEL_MID_WITDH, SPACES_SECOND_PANEL_MID_HIGH));
		panelMid.add(nameMinLabel);
		panelMid.add(firstNameData);
		panelMid.add(lastNameMidLabel);
		panelMid.add(lastNameData);
		panelMid.add(PhoneMidLabel);
		panelMid.add(phoneData);
		panelMid.add(editContactBotton);
		editButtonContact();
	}
	private void secondPanelRight(JPanel panelRight) {
		panelRight.setLayout(new GridLayout(ROWS_OF_BUTTONS, COLUMN_OF_BUTTONS,SPACES_BETWEEN_BUTTONS,SPACES_BETWEEN_BUTTONS));
		nextContactButton();
		lastContactButton();
		nextContactButton.setPreferredSize(new Dimension(HIGHT_AND_WIDTH_OF_SECOUND_PANEL_BUTTONS, HIGHT_AND_WIDTH_OF_SECOUND_PANEL_BUTTONS));
		panelRight.add(nextContactButton);
		panelRight.add(lastBotton);
	}
	private void thirdPanel(JPanel panelThree) {
		JPanel thirdPanelLeft = new JPanel();
		panelThree.add(thirdPanelLeft);
		thirdPanelLeft(thirdPanelLeft);
		JPanel thirdPanelMid = new JPanel();
		panelThree.add(thirdPanelMid);
		thirdPanelMid(thirdPanelMid);
		JPanel thirdPanelRight= new JPanel();
		panelThree.add(thirdPanelRight);
		thirdPanelRight(thirdPanelRight);
	}
	private void thirdPanelLeft(JPanel thirdPanelLeft) {
		thirdPanelLeft.add(formatOptions);
		formatOptions.setPreferredSize(new Dimension(WIDTH_OF_FORMAT_OPTION_BUTTON,HIGHT_OF_FORMAT_OPTION_BUTTON ));
	}
	private void thirdPanelMid(JPanel thirdPanelMid) {
		thirdPanelMid.setLayout(new GridLayout(ROWS_OF_THIRD_PANEL_MID, COLUMN_OF_THIRD_PANEL_MID));
		thirdPanelMid.add(exportBotton);
		exportBotton.setPreferredSize(new Dimension(HIGHT_AND_WIDTH_OF_EXPORT_BUTTON, HIGHT_AND_WIDTH_OF_EXPORT_BUTTON));
		exportButtonClicked();
	}
	private void thirdPanelRight(JPanel thirdPanelRight) {
		thirdPanelRight.setLayout(new GridLayout(ROWS_OF_THIRD_PANEL_RIGHT, COLUMN_OF_THIRD_PANEL_RIGHT,SPACES_BETWEEN_THIRD_PANEL_RIGHT,SPACES_BETWEEN_THIRD_PANEL_RIGHT));	
		loadFileButton.setPreferredSize(new Dimension(WIDTH_OF_LEAD_BUTTON, HIGHT_OF_LEAD_BUTTON));
		thirdPanelRight.add(filePathLebel);
		thirdPanelRight.add(filePathTextField);
		thirdPanelRight.add(loadFileButton);
		loadButtonClicked();
		textFieldPathSaver();			
	}



	private void fourPanel(JPanel panelFour) {
		panelFour.setLayout(new GridLayout(ROWS_OF_FOUR_PANEL, COLUMN_OF_FOUR_PANEL,SPACES_OF_FOUR_PANEL,SPACES_OF_FOUR_PANEL));
		panelFour.add(sortFieldComboBox);
		panelFour.add(fieldToSort);
		panelFour.add(organizationSortOne);
		panelFour.add(doSortButton);
		doSort();

	}

	private void fivePanel(JPanel panelFive) {
		panelFive.setLayout(new GridLayout(ROWS_OF_FIVE_PANEL, COLUMN_OF_FIVE_PANEL,SPACES_OF_FIVE_PANEL,SPACES_OF_FIVE_PANEL));
		panelFive.add(organizationSortTwo);
		panelFive.add(showButton);
		showContact();
	}



	private void showContact() {

		showButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(!ifFileEmpty){
						String organization =organizationSortOptions[organizationSortTwo.getSelectedIndex()];
						for (IListener listener : listeners) {
							listener.timerStart(organization);
						}
					}
					else
						System.out.println(WRONG_MSG_ONE);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

	}
	private void doSort() {
		doSortButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				try {
					for (IListener listener : listeners) {
						listener.chackIfFileEmpty();;
					}
					if(!ifFileEmpty){
						switch(fieldStrings[sortFieldComboBox.getSelectedIndex()]){
						case SECOND_OPTION_SORT:
							for (IListener listener : listeners) {
								listener.responsibleContactSort(SORT_BY_COUNT,SECOND_OPTION_SORT, fieldToSortStrings[fieldToSort.getSelectedIndex()], organizationSortOptions[organizationSortOne.getSelectedIndex()]);
							}
							break;
						case THIRD_OPTION_SORT:
							for (IListener listener : listeners) {
								listener.responsibleContactSort(REVERSE, null, null, null);
							}
							break;
						case FIRST_OPTION_SORT:
							if(organizationSortOptions[organizationSortOne.getSelectedIndex()] == ASCENDING_STRING){
								for (IListener listener : listeners) {
									listener.responsibleContactSort(SORT_BY_FIELD, FIRST_OPTION_SORT, fieldToSortStrings[fieldToSort.getSelectedIndex()], ASCENDING_STRING);
								}
							}
							else{
								for (IListener listener : listeners) {
									listener.responsibleContactSort(SORT_BY_FIELD,FIRST_OPTION_SORT, fieldToSortStrings[fieldToSort.getSelectedIndex()], DESCENING_STRING);
								}
							}
							break;
						}					
						if(THIRD_OPTION_SORT != fieldStrings[sortFieldComboBox.getSelectedIndex()]){
							for (IListener listener : listeners) {
								listener.responsibleContactsChanges( null, null, null, NEXT_CONTACT);
							}
						}
						for (IListener listener : listeners) {
							listener.responsibleContactsChanges( null, null, null, FIRST_CONTACT);
						}
					}else
						System.out.println(WRONG_MSG_ONE);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

	}
	private void textFieldPathSaver() {
		filePathTextField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {

				String input = filePathTextField.getText();
				setFilePath(input);
			}
			@Override
			public void focusGained(FocusEvent e) {
			}
		});
	}
	private void loadButtonClicked() {
		loadFileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if(getFilePath()!=null){
						int convertIdToInteger = Integer.parseInt(getFilePath());
						String getFormateChoosen = sizesString[formatOptions.getSelectedIndex()];
						for (IListener listener : listeners) {
							listener.chackIfTheObjectExist(convertIdToInteger, getFormateChoosen);
						}
						if(ifFileExist){
							for (IListener listener : listeners) {
								listener.importFromFile(convertIdToInteger, getFormateChoosen);
							}
							for (IListener listener : listeners) {
								listener.responsibleContactsChanges( null, null, null, EDIT);
							}
							for (IListener listener : listeners) {
								listener.responsibleContactsChanges( null, null, null, VALUES_CONTACT);
							}
						}else
							System.out.println(WRONG_MSG_TWO);
					}else
						System.out.println(WRONG_MSG_SIX);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}	
			}
		});
	}
	private void exportButtonClicked() {
		exportBotton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String foramtSelected = sizesString[(int) formatOptions.getSelectedIndex()];
				try {
					for (IListener listener : listeners) {
						listener.exportContactToFile(foramtSelected, firstNameData.getText(), lastNameData.getText(), phoneData.getText());
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}


	private void updateContact() {
		updateBotton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setContactFromUser();
					if(chackLegalInput()){

						for (IListener listener : listeners) {
							listener.responsibleContactsChanges(getFirstNameInputFromUser(), getLastNameInputFromUser(), getPhoneNumberInputFromUser(), UPDATE_CONTACT);
						}
						updateDataContactFields(getFirstNameInputFromUser(), getLastNameInputFromUser(), getPhoneNumberInputFromUser());
						for (IListener listener : listeners) {
							listener.getBoolenTrue();
						}
					}else
						System.out.println(WRONG_MSG_THREE);

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});	
	}
	private void createContact() {
		createBotton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					setContactFromUser();

					if(chackLegalInput()){
						clearTextField();
						for (IListener listener : listeners) {
							listener.responsibleContactsChanges(getFirstNameInputFromUser(), getLastNameInputFromUser(), getPhoneNumberInputFromUser(), CREATE_CONTACT);
						}
					}else
						System.out.println(WRONG_MSG_THREE);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}


		});	
	}
	private void lastContactButton() {
		lastBotton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					for (IListener listener : listeners) {
						listener.responsibleContactsChanges( null, null, null, LAST_CONTACT);
					}

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}



	private void nextContactButton() {

		nextContactButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {	
					for (IListener listener : listeners) {
						listener.responsibleContactsChanges( null, null, null, NEXT_CONTACT);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

	}
	private void editButtonContact() {
		editContactBotton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					for (IListener listener : listeners) {
						listener.chackIfFileEmpty();;
					}
					if(!ifFileEmpty){
						for (IListener listener : listeners) {
							listener.responsibleContactsChanges( null, null, null, EDIT);
						}
						for (IListener listener : listeners) {
							listener.getBoolenFalse();
						}

						for (IListener listener : listeners) {
							listener.responsibleContactsChanges( null, null, null, EDIT);
						}
					}else
						System.out.println(WRONG_MSG_ONE);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});	
	}
	private void firstButtonClicked() {
		firstBotton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					for (IListener listener : listeners) {
						listener.responsibleContactsChanges( null, null, null, FIRST_CONTACT);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	private void previousContactButtonClicked() {
		previousContactButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					for (IListener listener : listeners) {
						listener.responsibleContactsChanges( null, null, null, PREVIOUS_CONTACT);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});	
	}

	private boolean chackLegalInput(){
		if((getFirstNameInputFromUser().equals(EMPTY_DATA)||getLastNameInputFromUser().equals(EMPTY_DATA) || getPhoneNumberInputFromUser().equals(EMPTY_DATA)))
			return false;
		return true;
	}
	private void disableRestOfButtons() {
		firstBotton.setEnabled(falseBoolean);
		lastBotton.setEnabled(falseBoolean);
		previousContactButton.setEnabled(falseBoolean);
		nextContactButton.setEnabled(falseBoolean);
		exportBotton.setEnabled(falseBoolean);
		loadFileButton.setEnabled(falseBoolean);
		createBotton.setEnabled(falseBoolean);
		showButton.setEnabled(falseBoolean);
		doSortButton.setEnabled(falseBoolean);
	}
	private void enableRestOfButtons() {
		firstBotton.setEnabled(trueBoolen);
		lastBotton.setEnabled(trueBoolen);
		previousContactButton.setEnabled(trueBoolen);
		nextContactButton.setEnabled(trueBoolen);
		exportBotton.setEnabled(trueBoolen);
		loadFileButton.setEnabled(trueBoolen);
		createBotton.setEnabled(trueBoolen);
		showButton.setEnabled(trueBoolen);
		doSortButton.setEnabled(trueBoolen);
	}
	private void updateDataContactFields(String name, String lastName, String phoneNumber) {
		firstNameData.setText(name);
		lastNameData.setText(lastName);
		phoneData.setText(phoneNumber);
	}
	private void clearTextField() {
		nameText.setText(EMPTY_DATA);
		lastNameText.setText(EMPTY_DATA);
		phoneText.setText(EMPTY_DATA);

	}
	public void setFilePath(String input) {
		this.pathFill = input;
	}

	public String getFilePath(){
		return pathFill;
	}
	public String getFirstNameInputFromUser() {
		return firstNameInputFromUser;
	}

	public void setFirstNameInputFromUser(String firstNameInputFromUser) {
		this.firstNameInputFromUser = firstNameInputFromUser;
	}

	public String getLastNameInputFromUser() {
		return lastNameInputFromUser;
	}

	public void setLastNameInputFromUser(String lastNameInputFromUser) {
		this.lastNameInputFromUser = lastNameInputFromUser;
	}

	public String getPhoneNumberInputFromUser() {
		return phoneNumberInputFromUser;
	}

	public void setPhoneNumberInputFromUser(String phoneNumberInputFromUser) {
		this.phoneNumberInputFromUser = phoneNumberInputFromUser;
	}


	public void registerListener(ActionListener listener) {
		listeners.add((IListener) listener);

	}
	private void setContactFromUser() {
		nameText.setFocusable(true);
		lastNameText.setFocusable(true);
		phoneText.setFocusable(true);
		setFirstNameInputFromUser(nameText.getText());
		setLastNameInputFromUser(lastNameText.getText());
		setPhoneNumberInputFromUser(phoneText.getText());

	}
	public void init() {

		setResizable(false);
		setSize(WIDTH_FRAME, HIGH_FRAMEE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	@Override
	public void registerListener(IListener iListener) {
		listeners.add(iListener);

	}

	@Override
	public void  printMsg(String str) {
		System.out.println(str);
	}


	@Override
	public void showWordContact(String[] words) {
		firstNameData.setText(words[FIRST_NAME]);
		lastNameData.setText(words[LAST_NAME]);
		phoneData.setText(words[PHONE_NUMBER]);
	}
	@Override
	public void showOnTextField(String[] words) {
		nameText.setText(words[FIRST_NAME]);
		lastNameText.setText(words[LAST_NAME]);
		phoneText.setText(words[PHONE_NUMBER]);
	}
	@Override
	public void valuesContact(String[] words) {
		setFirstNameInputFromUser(words[FIRST_NAME]);
		setLastNameInputFromUser(words[LAST_NAME]);
		setPhoneNumberInputFromUser(words[PHONE_NUMBER]);
	}
	@Override
	public void ifEmpty(boolean EmptyOrNot) {
		ifFileEmpty = EmptyOrNot;

	}
	@Override
	public void ifExist(boolean existOrNot) {
		ifFileExist = existOrNot;
		
	}
	@Override
	public void showColor(Color c) {
	}
	@Override
	public void booleanFlaseAndEditAction(boolean falseB) {
		//When use clicked "edit" disable rest of bottoms 
		this.falseBoolean = falseB;
		updateBotton.setEnabled(trueBoolen);
		disableRestOfButtons();
		
	}
	@Override
	public void booleanTrueAndUpdateAction(boolean trueB) {
		//When use clicked "update" the clear the textfields and enable rest of bottoms 
		this.trueBoolen = trueB;
		updateBotton.setEnabled(falseBoolean);
		enableRestOfButtons();
		clearTextField();
		
	}






}


import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Platform;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ViewJFX implements IFinalStringSymbol,IFinalOutPutFromUser,IFinalInteger,IOptionFinalListener,IContactView, IContactRegistrable  {

	private ArrayList<IListener> listeners = new ArrayList<IListener>();
	private GridPane mainPane;
	private GridPane leftPaneOne;
	private GridPane leftPaneTwo;
	private GridPane leftPaneThree;
	private GridPane leftPaneFour;
	private GridPane rightPaneOne;
	private GridPane rightPaneTwo;
	private GridPane rightPaneThree;
	private GridPane rightPaneFour;
	
	
	private String firstNameInputFromUser;
	private String lastNameInputFromUser;
	private String phoneNumberInputFromUser;
	private Label labelFirstName = new Label(FIRST_NAME_LABEL_STRING);
	private Label labelLastName = new Label(LAST_NAME_LABEL_STRING);
	private Label labelPhoneNumber = new Label(PHONE_NUMBER_LABEL_STRING );
	private Button createButton = new Button(CREATE_STRING_BUTTON);
	private Button previusButton = new Button(PREVIOUS_SYMBOL);
	private Button firstButton = new Button(FIRST_CONTACT_STRING_BUTTON);
	private Label labelFirstNameTwo = new Label(FIRST_NAME_LABEL_STRING);
	private Label labelLastNameTwo = new Label(LAST_NAME_LABEL_STRING);
	private Label labelPhoneNumberTwo = new Label(PHONE_NUMBER_LABEL_STRING);
	private Label dataFirstName = new Label(EMPTY_DATA +"xxx");
	private Label datalLastName = new Label(EMPTY_DATA+"xxx");
	private Label datalPhoneNumber = new Label(EMPTY_DATA+"xxx");
	private Button editContactBotton = new Button(EDIT_CONTACT_STRING_BUTTON);
	private ObservableList<String> sizesString = FXCollections.observableArrayList(FIRST_FORMAT_FILE,SECOND_FORMAT_FILE,THIRD_FORMAT_FILE);
	private ComboBox<String> formatOptions = new ComboBox<String>(sizesString);
	private Button exportBotton = new Button(EXPORT_STRING_BUTTON);
	private ObservableList<String> fieldStrings = FXCollections.observableArrayList(FIRST_OPTION_SORT,SECOND_OPTION_SORT,THIRD_OPTION_SORT);
	private ObservableList<String> fieldToSortStrings = FXCollections.observableArrayList(FIRST_FIELD_TO_SORT,SECOND_FIELD_TO_SORT,THIRD_FIELD_TO_SORT);
	private ObservableList<String>  organizationSortOptions = FXCollections.observableArrayList(ASCENDING_STRING,DESCENING_STRING);
	private ComboBox<String> sortFieldComboBox = new ComboBox<String>(fieldStrings);
	private ComboBox<String> fieldToSort = new ComboBox<String>(fieldToSortStrings);
	private ComboBox<String> organizationSortOne = new ComboBox<String>(organizationSortOptions);
	private Button showButton = new Button(SHOW_BUTTON_STRING);
	private TextField nameText = new TextField();
	private TextField lastNameText = new TextField();
	private TextField phoneText = new TextField();
	private Button updateButton = new Button(UPDATE_STRING_BUTTON);
	
	private Button nextContactButton = new Button(NEXT_SYMBOL);
	private Button lastBotton = new Button(LAST_CONTACT_STRING_BUTTON);
	
	private Label filePathLebel = new Label(FILE_PATH_LABEL_STRING);
	private TextField filePathTextField = new TextField();
	private Button loadFileButton = new Button(LOAD_FILE_STRING_BUTTON);
	
	private Button doSortButton = new Button(SORT_BUTTON_STRING);
	private ComboBox<String> organizationSortTwo = new ComboBox<String>(organizationSortOptions);
	private boolean ifFileEmpty= false;
	private boolean ifFileExist= false;
	private boolean falseBoolean=true;
	private boolean trueBoolen=true ;
	private Font font = Font.font(FONT, FontWeight.BOLD, FontPosture.REGULAR,SIZE_FONT);
	

	public ViewJFX(Stage primaryStage) {
		mainPane = new GridPane();
		leftPaneOneSater();
		mainPane.add(leftPaneOne,0,0);
		leftPaneTwoSater();
		mainPane.add(leftPaneTwo, 0, 1);
		leftPaneThreeSater();
		mainPane.add(leftPaneThree, 0, 2);
		leftPaneFourSater();
		mainPane.add(leftPaneFour, 0,3);
		rightPaneOneSater();
		mainPane.add(rightPaneOne, 1, 0);
		rightPaneTwoSater();
		mainPane.add(rightPaneTwo, 1, 1);
		rightPaneThreeSater();
		mainPane.add(rightPaneThree, 1, 2);
		rightPaneFourSater();
		mainPane.add(rightPaneFour, 1, 3);
		Scene scene = new Scene(mainPane,SIZE_JAVAFX, SIZE_JAVAFX);
		primaryStage.setOnCloseRequest(e -> System.exit(0));
		primaryStage.setTitle("JavaFX");
		primaryStage.setScene(scene);
		primaryStage.show();
	}







	private void leftPaneOneSater() {
		leftPaneOne = new GridPane();
		leftPaneOne.setVgap(30);
		leftPaneOne.setPadding(new Insets(10,0,0, 30));
		leftPaneOne.add(labelFirstName,0,0);
		leftPaneOne.add(labelLastName,0,1);
		leftPaneOne.add(labelPhoneNumber,0,2);
		leftPaneOne.add(createButton, 0, 3);
		createButton.setMaxWidth(SIZE_BUTTON_JAVAFX);
		createButton.setOnAction(e -> createButton() );
		
	}


	private void leftPaneTwoSater() {
		leftPaneTwo = new GridPane();
		GridPane leftPanel = new GridPane();
		leftPaneTwo.add(leftPanel, 0, 0);
		leftPanel.setVgap(30);
		leftPanel.setPadding(new Insets(20, 10, 10, 30));
		leftPanel.add(previusButton, 0, 0);
		leftPanel.add(firstButton, 0, 1);
		previusButton.setMaxSize(SIZE_BUTTON_JAVAFX, SIZE_BUTTON_JAVAFX);
		previusButton.setOnAction(e -> previusButtonContact());
		firstButton.setMaxSize(SIZE_BUTTON_JAVAFX, SIZE_BUTTON_JAVAFX);
		firstButton.setOnAction(e -> firstButtonContact());
		GridPane rightPanel = new GridPane();
		leftPaneTwo.add(rightPanel, 1, 0);
		rightPanel.setVgap(15);
		rightPanel.setHgap(10);
		rightPanel.setPadding(new Insets(20, 10, 10, 30));
		rightPanel.add(labelFirstNameTwo,0,0);
		rightPanel.add(labelLastNameTwo,0,1);
		rightPanel.add(labelPhoneNumberTwo,0,2);
		rightPanel.add(editContactBotton,0,3);
		editContactBotton.setOnAction(e -> editButton());
		rightPanel.add(dataFirstName,1,0);
		rightPanel.add(datalLastName,1,1);
		rightPanel.add(datalPhoneNumber,1,2);
	}
	













	private void leftPaneThreeSater() {
		leftPaneThree = new GridPane();
		leftPaneThree.setVgap(15);
		leftPaneThree.setHgap(10);
		leftPaneThree.setPadding(new Insets(20, 10, 10, 30));
		leftPaneThree.add(formatOptions, 0, 0);
		leftPaneThree.add(exportBotton, 1, 0);
		exportBotton.setOnAction(e -> setExport());
		formatOptions.getSelectionModel().selectFirst();
	}
	private void leftPaneFourSater() {
		leftPaneFour = new GridPane();
		leftPaneFour.setVgap(15);
		leftPaneFour.setHgap(10);
		leftPaneFour.setPadding(new Insets(100, 10, 10, 30));
		leftPaneFour.add(sortFieldComboBox, 0, 0);
		leftPaneFour.add(fieldToSort, 1, 0);
		leftPaneFour.add(organizationSortOne, 0, 1);
		leftPaneFour.add(showButton, 1, 1);
		showButton.setOnAction(e -> showContact());
		sortFieldComboBox.getSelectionModel().selectFirst();
		fieldToSort.getSelectionModel().selectFirst();
		organizationSortOne.getSelectionModel().selectFirst();
	}
	private void rightPaneOneSater() {
		rightPaneOne = new GridPane();
		rightPaneOne.setVgap(20);
		rightPaneOne.setPadding(new Insets(10, 10, 10, 30));
		rightPaneOne.add(nameText, 0, 0);
		rightPaneOne.add(lastNameText, 0, 1);
		rightPaneOne.add(phoneText, 0, 2);
		rightPaneOne.add(updateButton, 0, 3);
		updateButton.setOnAction(e -> setUpdateButton());
		updateButton.setMaxSize(SIZE_BUTTON_JAVAFX, SIZE_BUTTON_JAVAFX);
		updateButton.setDisable(true);
	}
	private void rightPaneTwoSater() {
		rightPaneTwo = new GridPane();
		rightPaneTwo.setVgap(30);
		rightPaneTwo.setHgap(10);
		rightPaneTwo.setPadding(new Insets(20, 10, 10, 100));
		rightPaneTwo.add(nextContactButton, 0, 0);
		rightPaneTwo.add(lastBotton, 0, 1);
		nextContactButton.setMaxSize(SIZE_BUTTON_JAVAFX, SIZE_BUTTON_JAVAFX);
		lastBotton.setMaxSize(SIZE_BUTTON_JAVAFX, SIZE_BUTTON_JAVAFX);
		nextContactButton.setOnAction(e -> nextButtonContact());
		lastBotton.setOnAction(e -> lastButtonContact());
	}
	private void rightPaneThreeSater() {
		rightPaneThree = new GridPane();
		rightPaneThree.setVgap(10);
		rightPaneThree.setHgap(10);
		rightPaneThree.add(filePathLebel, 0, 0);
		rightPaneThree.add(filePathTextField, 0, 1);
		rightPaneThree.add(loadFileButton, 0, 2);
		loadFileButton.setOnAction(e -> setLoadButton());
	}
	private void rightPaneFourSater() {
		rightPaneFour = new GridPane();
		rightPaneFour.setVgap(10);
		rightPaneFour.setHgap(10);
		rightPaneFour.setPadding(new Insets(100, 10, 10, 10));
		rightPaneFour.add(organizationSortTwo, 0, 0);
		rightPaneFour.add(doSortButton, 1, 0);
		organizationSortTwo.getSelectionModel().selectFirst();
		doSortButton.setOnAction(e -> setSort());
	}
	private void createButton() {
		
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

	private void previusButtonContact() {
		try {	
			for (IListener listener : listeners) {
				listener.responsibleContactsChanges( null, null, null, PREVIOUS_CONTACT);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void nextButtonContact() {
		try {	
			for (IListener listener : listeners) {
				listener.responsibleContactsChanges( null, null, null, NEXT_CONTACT);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void firstButtonContact() {
		try {	
			for (IListener listener : listeners) {
				listener.responsibleContactsChanges( null, null, null, FIRST_CONTACT);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	private void lastButtonContact() {
		try {	
			for (IListener listener : listeners) {
				listener.responsibleContactsChanges( null, null, null, LAST_CONTACT);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	private void editButton() {
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
	private void setUpdateButton() {
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
	

	private void setLoadButton() {
		
		try {
			
				filePathTextField.focusedProperty();
				if(filePathTextField.getText()!=null){
					
				int convertIdToInteger = Integer.parseInt(filePathTextField.getText());
				int index = formatOptions.getSelectionModel().getSelectedIndex();
				String getFormateChoosen = sizesString.get(index);
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
	

	private void setSort() {
		try {
			for (IListener listener : listeners) {
				listener.chackIfFileEmpty();;
			}
			if(!ifFileEmpty){
				switch(fieldStrings.get(sortFieldComboBox.getSelectionModel().getSelectedIndex())){
				case SECOND_OPTION_SORT:
					for (IListener listener : listeners) {
						listener.responsibleContactSort(SORT_BY_COUNT,SECOND_OPTION_SORT, fieldToSortStrings.get(fieldToSort.getSelectionModel().getSelectedIndex()), organizationSortOptions.get(organizationSortOne.getSelectionModel().getSelectedIndex()));
					}
					break;
				case THIRD_OPTION_SORT:
					for (IListener listener : listeners) {
						listener.responsibleContactSort(REVERSE, null, null, null);
					}
					break;
				case FIRST_OPTION_SORT:
					if(organizationSortOptions.get(organizationSortOne.getSelectionModel().getSelectedIndex()) == ASCENDING_STRING){
						for (IListener listener : listeners) {
							listener.responsibleContactSort(SORT_BY_FIELD, FIRST_OPTION_SORT, fieldToSortStrings.get(fieldToSort.getSelectionModel().getSelectedIndex()), ASCENDING_STRING);
						}
					}
					else{
						for (IListener listener : listeners) {
							listener.responsibleContactSort(SORT_BY_FIELD,FIRST_OPTION_SORT, fieldToSortStrings.get(fieldToSort.getSelectionModel().getSelectedIndex()), DESCENING_STRING);
						}
					}
					break;
				}				
				if(THIRD_OPTION_SORT != fieldStrings.get(sortFieldComboBox.getSelectionModel().getSelectedIndex())){
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



	private void showContact() {
		try {
			if(!ifFileEmpty){
				String organization =organizationSortOptions.get(organizationSortTwo.getSelectionModel().getSelectedIndex());
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
	
	

	private void setExport() {
		String foramtSelected = sizesString.get(formatOptions.getSelectionModel().getSelectedIndex());
		try {
			for (IListener listener : listeners) {
				listener.exportContactToFile(foramtSelected, dataFirstName.getText(), datalLastName.getText(), datalPhoneNumber.getText());
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}






		
	
	







	private void updateDataContactFields(String name, String lastName, String phoneNumber) {
		dataFirstName.setText(name);
		datalLastName.setText(lastName);
		datalPhoneNumber.setText(phoneNumber);
	}






	private void disableRestOfButtons() {
		firstButton.setDisable(trueBoolen);
		lastBotton.setDisable(trueBoolen);
		previusButton.setDisable(trueBoolen);
		nextContactButton.setDisable(trueBoolen);
		exportBotton.setDisable(trueBoolen);
		loadFileButton.setDisable(trueBoolen);
		createButton.setDisable(trueBoolen);
		showButton.setDisable(trueBoolen);
		doSortButton.setDisable(trueBoolen);
	}
	
	private void enableRestOfButtons() {
		firstButton.setDisable(falseBoolean);
		lastBotton.setDisable(falseBoolean);
		previusButton.setDisable(falseBoolean);
		nextContactButton.setDisable(falseBoolean);
		exportBotton.setDisable(falseBoolean);
		createButton.setDisable(falseBoolean);
		loadFileButton.setDisable(falseBoolean);
		showButton.setDisable(falseBoolean);
		doSortButton.setDisable(falseBoolean);
	}







	private void setContactFromUser() {
		nameText.setFocusTraversable(true);
		lastNameText.setFocusTraversable(true);
		phoneText.setFocusTraversable(true);
		setFirstNameInputFromUser(nameText.getText());
		setLastNameInputFromUser(lastNameText.getText());
		setPhoneNumberInputFromUser(phoneText.getText());
		
	}
	
	
	private void clearTextField() {
		nameText.setText(EMPTY_DATA);
		lastNameText.setText(EMPTY_DATA);
		phoneText.setText(EMPTY_DATA);
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
	
	private boolean chackLegalInput(){
		if((getFirstNameInputFromUser().equals(EMPTY_DATA)||getLastNameInputFromUser().equals(EMPTY_DATA) || getPhoneNumberInputFromUser().equals(EMPTY_DATA)))
			return false;
		return true;
	}

	private void setFontToLabels() {
		dataFirstName.setFont(font);
		datalLastName.setFont(font);
		datalPhoneNumber.setFont(font);
	}

	


	@Override
	public void showWordContact(String[] words) {
	Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				dataFirstName.setText(words[FIRST_NAME]);
				datalLastName.setText(words[LAST_NAME]);
				datalPhoneNumber.setText(words[PHONE_NUMBER]);
				setFontToLabels();
			}

		
		});
	
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
	public void printMsg(String str) {
		System.out.println(str);
	}

	@Override
	public void ifEmpty(boolean EmptyOrNot) {
		ifFileEmpty = EmptyOrNot;

	}
	
	@Override
	public void ifExist(boolean existOrNot) {
		this.ifFileExist = existOrNot;
		
	}


	@Override
	public void registerListener(IListener iListener) {
		listeners.add(iListener);

	}
	
	public void registerListener(ActionListener listener) {
		listeners.add((IListener) listener);

	}







	@Override
	public void showColor(Color c) {
		dataFirstName.setTextFill(c);
		datalLastName.setTextFill(c);
		datalPhoneNumber.setTextFill(c);
	}







	@Override
	public void booleanFlaseAndEditAction(boolean falseB) {
		//When use clicked "edit" disable rest of bottoms 
		this.falseBoolean = falseB;
		 updateButton.setDisable(falseBoolean);
		 disableRestOfButtons();
			
		
	}







	@Override
	public void booleanTrueAndUpdateAction(boolean trueB) {
		//When use clicked "update" the clear the textfields and enable rest of bottoms 
		this.trueBoolen = trueB;
		 updateButton.setDisable(trueBoolen);
		 enableRestOfButtons();
		 clearTextField();
		
	}




}

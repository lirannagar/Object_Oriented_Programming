import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Controller implements IListener,IOptionFinalListener{



	private ArrayList<IContactView> views = new ArrayList<IContactView>();
	private ContactsManager contactManagerModel;


	public void setModel(ContactsManager model){
		this.contactManagerModel = model;
		registerIfPossible(contactManagerModel);
	}
	
	public void addView(IContactView view) {
		
		this.views.add(view);
		registerIfPossible(view);
		
	}
	
	public void registerIfPossible(Object o) {
		if (o instanceof IContactRegistrable) {
			IContactRegistrable registrable = (IContactRegistrable) o;
			registrable.registerListener(this);
		}

	}
	
	@Override
	public void chackIfTheObjectExist(int convertIdToInteger, String getFormateChoosen) {
		contactManagerModel.chackObject(convertIdToInteger,getFormateChoosen);
		
	}

	
	@Override
	public void responsibleContactsChanges(String firstName,String lastName,String phoneNumber, String opertion) throws IOException{
		switch(opertion){
		case CREATE_CONTACT:
			contactManagerModel.createNewContact( firstName, lastName, phoneNumber);
			break;
		case NEXT_CONTACT:
			contactManagerModel.getNextContact();
			break;
		case PREVIOUS_CONTACT:
			contactManagerModel.getPreviuseContact();
			break;
		case LAST_CONTACT:
			contactManagerModel.getLastContact();
			break;
		case FIRST_CONTACT:
			contactManagerModel.getFirstContact();
			break;
		case UPDATE_CONTACT:
			contactManagerModel.updateContact( firstName, lastName, phoneNumber);
			break;
		case EDIT:
			contactManagerModel.getContactToTextField();
			break;
		case VALUES_CONTACT:
			contactManagerModel.getField();
			break;
		case CONTACT_VALUE_IMPORT:
			contactManagerModel.getValues();
			break;
		}
	}
	@Override
	public void responsibleContactSort(String oprtionToSort,String fieldOptionSort, String fieldToSort, String organization) throws IOException{
		switch(oprtionToSort){
		
		case REVERSE:
			contactManagerModel.doReverse(fieldOptionSort);
			break;
		case SORT_BY_FIELD:
			contactManagerModel.doSortContact(fieldOptionSort, fieldToSort, organization);
			break;
		case SORT_BY_COUNT:
			contactManagerModel.sortByCount(fieldToSort, organization);
			break;
		}	
	}
	@Override
	public void exportContactToFile(String format ,String firstName ,String lastName, String phoneNumber  )throws IOException{
		contactManagerModel.export(format, firstName, lastName, phoneNumber);
	}
	@Override
	public void importFromFile(int id, String format) throws ClassNotFoundException, IOException{
		contactManagerModel.importContactFromFile(id, format);
	}
	
	
	@Override
	public void chackIfFileExist(){
		try {
			contactManagerModel.ifFileEmpty();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void chackIfFileEmpty() throws IOException{
		contactManagerModel.ifFileEmpty();
	}

	@Override
	public void printMsgWrongInput(String str) {
		for (IContactView listener : views) {
			listener.printMsg(str);
			//avoid to print duplicate MSG to the two views
			break;
		}
		
	}


	@Override
	public void inputContactField(String firstName,String lastName, String phoneNumber) {
		String[] words = {firstName,lastName,phoneNumber};
		for (IContactView listener : views) {
			listener.showWordContact(words);
		}	
	}

	@Override
	public void showToField(String firstName,String lastName, String phoneNumber) {
		String[] words = {firstName,lastName,phoneNumber};
		for (IContactView listener : views) {
			listener.showOnTextField(words);
		}
		
		
		
	}

	@Override
	public void getContactValues(String firstName, String lastName, String phoneNumber) {
		String[] words = {firstName,lastName,phoneNumber};
		for (IContactView listener : views) {
			listener.valuesContact(words);
		}	
	}

	@Override
	public void getTheResoult(boolean resoult) {
		for (IContactView listener : views) {
			listener.ifEmpty(resoult);
		}
		
	}
	
	@Override
	public void getResultIfExist(boolean ifExist) {
		for (IContactView listener : views) {
			listener.ifExist(ifExist);
		}
		
	}
	

	@Override
	public void timerStart(String organization) throws IOException {
		contactManagerModel.startTimerRunning(organization);
	}

	public void init() throws IOException {
		contactManagerModel.startTheAppWithFirstContact();	
	}

	@Override
	public void updateColor(Color c) {
		for (IContactView listener : views) {
			listener.showColor(c);
		}
	}

	@Override
	public void getBoolenTrue() {
		for (IContactView listener : views) {
			listener.booleanTrueAndUpdateAction(true);
		}
		
	}

	@Override
	public void getBoolenFalse() {
		for (IContactView listener : views) {
			listener.booleanFlaseAndEditAction(false);
		}
		
	}
	

	







}

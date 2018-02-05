import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import javax.swing.Timer;





public class ContactsManager implements Serializable,IContactRegistrable,IFinalOutPutFromUser,IFinalStringSymbol,IFinalInteger {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public static final String TYPE = "rw";



	private RandomAccessFile raf;
	private int pointer = 0;
	private int counterId = 1;
	private Contact c =new Contact();

	private FileListIterator<IContact> iterator; 
	private File f;
	private Stack<Contact> contactListStack ;
	private Set<Contact> setList ;
	private ArrayList<Contact> arrayListHelper;
	private ArrayList<Contact> arrayListContact;
	private Comparator<Contact> comByFirstName = new ComperatorContactFieldsByFirstName();
	private Comparator<Contact> comByLastName = new ComperatorContactFieldsByLastName();
	private Comparator<Contact> comByPhoneNumber = new ComperatorContactFieldsByPhoneNumber();
	private Comparator<Contact> compareAllContacts;
	private ArrayList<IListener> listeners = new ArrayList<IListener>();
	private Timer timer;
	
	
	

	
	
	


	public ContactsManager(String fileName) throws IOException {
		f = new File(fileName);
		raf = new RandomAccessFile(f,TYPE);
		iterator = new FileListIterator<IContact>(raf);

	}




	public void sortByCount(String fieldToSort, String organization) throws IOException {
		iterator = new FileListIterator<IContact>(raf);
		contactListStack = new Stack<Contact> ();
		arrayListContact = new ArrayList<Contact>();
		arrayListHelper = new ArrayList<Contact>();
		while(iterator.hasNext()){
			c = (Contact) iterator.next();
			arrayListContact.add(c);
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		initMap(map,fieldToSort);
		Comparator<Entry<String, Integer>> comp = new ComparatorCount<Entry<String, Integer>>() ;
		@SuppressWarnings("unused")
		Set<Entry<String, Integer>> ts = new TreeSet<Entry<String, Integer>>(comp);
		orderTheList(map,fieldToSort,comp);
		putArrayInFile(organization);
	}



	public void doReverse(String fieldOptionSort) throws IOException{
		iterator = new FileListIterator<IContact>(raf);
		contactListStack = new Stack<Contact> ();
		while(iterator.hasNext()){
			c = (Contact) iterator.next();
			contactListStack.add(c);
		}
		raf.setLength(0);
		for (@SuppressWarnings("unused")
		Iterator<Contact> iter = contactListStack.iterator(); iterator.hasNext();) 
			iterator.add(contactListStack.pop());	
	}





	public void doSortContact(String fieldOptionSort, String fieldToSort, String organization) throws IOException {
		iterator = new FileListIterator<IContact>(raf);
		contactListStack = new Stack<Contact> ();
		compareAllContacts = new ComperatorContactFields(fieldToSort);
		setList = new TreeSet<Contact>(compareAllContacts);
		while(iterator.hasNext()){
			c = (Contact) iterator.next();
			setList.add(c);
		}
		Iterator<Contact> iter = setList.iterator();
		while(iter.hasNext())
			contactListStack.add(iter.next());
		if(fieldOptionSort == ContactsManagerFrame.FIRST_OPTION_SORT && fieldToSort== ContactsManagerFrame.FIRST_FIELD_TO_SORT ){
			Collections.sort(contactListStack,comByFirstName);
		}else if (fieldOptionSort == ContactsManagerFrame.FIRST_OPTION_SORT && fieldToSort== ContactsManagerFrame.SECOND_FIELD_TO_SORT ){
			Collections.sort(contactListStack,comByLastName);
		}else if(fieldOptionSort == ContactsManagerFrame.FIRST_OPTION_SORT && fieldToSort== ContactsManagerFrame.THIRD_FIELD_TO_SORT )
			Collections.sort(contactListStack,comByPhoneNumber);
		putArrayInFile(organization);
	}


	private void orderTheList(Map<String, Integer> map,String fieldToSort, Comparator<Entry<String, Integer>> comp) {
		Set<Entry<String, Integer>> ts = new TreeSet<Entry<String, Integer>>(comp);
		for (Entry<String, Integer> entry : map.entrySet()) {
			ts.add(entry);
		}
		for (Entry<String, Integer> entry : map.entrySet()) {
			for (int i = 0; i < arrayListContact.size(); i++) {
				switch(fieldToSort){
				case ContactsManagerFrame.FIRST_FIELD_TO_SORT:
					if(arrayListContact.get(i).getFirstName() == entry.getKey() && entry.getValue() ==1 )
						arrayListHelper.add(arrayListContact.get(i));
					break;
				case ContactsManagerFrame.SECOND_FIELD_TO_SORT:
					if(arrayListContact.get(i).getLastName() == entry.getKey()&& entry.getValue() ==1 )
						arrayListHelper.add(arrayListContact.get(i));
					break;
				case ContactsManagerFrame.THIRD_FIELD_TO_SORT:
					if(arrayListContact.get(i).getPhoneNumber() == entry.getKey()&& entry.getValue() ==1 )
						arrayListHelper.add(arrayListContact.get(i));
					break;
				}
			}
		}
		for (Entry<String, Integer> entry : ts) {
			for (int i = 0; i < arrayListContact.size(); i++) {
				switch(fieldToSort){
				case ContactsManagerFrame.FIRST_FIELD_TO_SORT:
					if(arrayListContact.get(i).getFirstName() == entry.getKey() && entry.getValue() !=1 )
						contactListStack.add(arrayListContact.get(i));
					break;
				case ContactsManagerFrame.SECOND_FIELD_TO_SORT:
					if(arrayListContact.get(i).getLastName() == entry.getKey()&& entry.getValue() !=1 )
						contactListStack.add(arrayListContact.get(i));
					break;
				case ContactsManagerFrame.THIRD_FIELD_TO_SORT:
					if(arrayListContact.get(i).getPhoneNumber() == entry.getKey() && entry.getValue() !=1)
						contactListStack.add(arrayListContact.get(i));
					break;
				}


			}
		}

		for (int i = 0; i < arrayListHelper.size(); i++) {
			contactListStack.add(arrayListHelper.get(i));
		}
	}



	private void initMap(Map<String, Integer> map, String fieldToSort) {
		for (Contact c : arrayListContact) {
			String string = "";
			switch (fieldToSort) {
			case ContactsManagerFrame.FIRST_FIELD_TO_SORT:
				string = c.getFirstName();
				break;
			case ContactsManagerFrame.SECOND_FIELD_TO_SORT:
				string = c.getLastName();
				break;
			case ContactsManagerFrame.THIRD_FIELD_TO_SORT:
				string = c.getPhoneNumber();
				break;
			}
			Integer count = map.get(string);
			if (count == null)
				map.put(string , 1);
			else 
				map.put(string , count + 1);
		}
	}
	private void putArrayInFile(String organization) throws IOException {
		iterator = new FileListIterator<IContact>(raf);
		raf.setLength(0);
		Iterator<Contact> itera = contactListStack.iterator();
		while(itera.hasNext()){
			if(ContactsManagerFrame.ASCENDING_STRING == organization)
				iterator.add(itera.next());
			else
				iterator.add(contactListStack.pop());
		}
	}
	public int getIdFromFile() {
		return c.getId();
	}
	public String getFirstNameFromFile() {
		return c.getFirstName();
	}

	public String getLastNameFromFile() {
		return c.getLastName();
	}

	public String getPhoneNumberFromFile() {
		return c.getPhoneNumber();
	}
	public void createNewContact( String firstName, String lastName, String phoneNumber ) throws IOException{
		int id = counterId++;
		 c = new Contact(id, firstName, lastName, phoneNumber);
		iterator.add(c);	
		while(iterator.hasNext())
			c = (Contact) iterator.next();
			for (IListener listener : listeners) {
				listener.inputContactField(c.getFirstName(),c.getLastName(), c.getPhoneNumber());
			}
			for(IListener listener : listeners){
				listener.updateColor(EnumColor.EDITANDCREATE.getColor());
			}
	}
	public void updateContact(String firstName, String lastName, String phoneNumber) throws IOException{
		c = new Contact(caculateCurrantId(), firstName, lastName, phoneNumber);
		iterator.set(c);
		for(IListener listener : listeners){
			listener.updateColor(EnumColor.EDITANDCREATE.getColor());
		}
	}
	public long getLenghFile() throws IOException{
		return raf.length();
	}
	public boolean ifEmptyFile() throws IOException{
		if(raf.length() == 0){
			return true;

		}
		return false;
	}
	public boolean ifLastContact() throws IOException{
		if(iterator.hasNext())
			return false;
		return true;
	}
	public boolean ifFirstContact(){
		if(iterator.hasPrevious())
			return false;
		return true;
	}
	public String getNextFirstName() throws IOException {
		return c.getFirstName();
	}
	public String getNextLastName() throws IOException {
		return c.getLastName();
	}
	public String getNextPhoneNumber() throws IOException {
		return c.getPhoneNumber();
	}
	public void setPointerDown(int feildSize){
		this.pointer -= feildSize;
	}
	public void setPointerUp(int feildSize){
		this.pointer +=feildSize;
	}
	public int getPointer(){
		return pointer;
	}
	public void setPointer(int pointer){
		this.pointer = pointer;
	}
	public int getObjectSize(){
		return c.getObjectSize();
	}
	public void export(String format, String FirstName, String LastName, String phoneNumber) throws IOException{
		if(!ifEmptyFile()){
			int id =c.getId();
			File exportFile = new File(id + "." + format);
			Contact contact = new Contact( id,FirstName, LastName, phoneNumber);
			contact.export(format, exportFile);
			for (IListener listener : listeners) {
				listener.printMsgWrongInput(SUCCESS_MSG_EXPORT);
			}
		}else{
			for (IListener listener : listeners) {
				listener.printMsgWrongInput(WRONG_MSG_ONE);
			}
		}
	}
	public void importContactFromFile(int id, String format) throws ClassNotFoundException, IOException{
		File f = new File(id+"."+format);
		switch(format){
		case ContactsManagerFrame.FIRST_FORMAT_FILE:
			Scanner in = new Scanner(f);
			setPointer(id *c.getObjectSize());
			c.setId(Integer.parseInt(in.nextLine()));
			c.setFirstName(in.nextLine());
			c.setLastName(in.nextLine());
			c.setPhoneNumber(in.nextLine());
			in.close();
			break;		
		case ContactsManagerFrame.SECOND_FORMAT_FILE:
			ObjectInputStream objectReader = new ObjectInputStream(new FileInputStream(f));
			setPointer(id *c.getObjectSize());
			c = (Contact) objectReader.readObject();
			objectReader.close();
			break;
		case ContactsManagerFrame.THIRD_FORMAT_FILE:
			ObjectInputStream byteReader = new ObjectInputStream(new FileInputStream(f));
			setPointer(id *c.getObjectSize());
			c.setId(byteReader.readInt());
			c.setFirstName(byteReader.readUTF());
			c.setLastName(byteReader.readUTF());
			c.setPhoneNumber(byteReader.readUTF());
			byteReader.close();
			break;
		}

	}

	public void getNextContact() throws IOException {
		if(!ifEmptyFile()){
			if(!ifLastContact()){
				c = (Contact) iterator.next();
				for (IListener listener : listeners) {
					listener.updateColor(EnumColor.NEXTANDLAST.getColor());		
				}
				stringContactFields();
			}else{
				for (IListener listener : listeners) {
					listener.printMsgWrongInput(WRONG_MSG_FIVE);
				}
			}
		}else{
			for (IListener listener : listeners) {
				listener.printMsgWrongInput(WRONG_MSG_ONE);
			}
		}

	}
	public void getPreviuseContact() throws IOException {
		if(!ifEmptyFile()){
			if(!ifFirstContact()){
				c = (Contact) iterator.previous();
				stringContactFields();
				
				for (IListener listener : listeners) {
					listener.updateColor(EnumColor.PREANDFIRST.getColor());		
				}
			}else{
				for (IListener listener : listeners) {
					listener.printMsgWrongInput(WRONG_MSG_FOUR);
				}
			}
		}else{
			for (IListener listener : listeners) {
				listener.printMsgWrongInput(WRONG_MSG_ONE);
			}
		}
	}
	public void getFirstContact() throws IOException {
		if(!ifEmptyFile()){
			while(iterator.hasPrevious())
				c = (Contact) iterator.previous();
			stringContactFields();
			for (IListener listener : listeners) {
				listener.updateColor(EnumColor.PREANDFIRST.getColor());		
			}
		}else{
			for (IListener listener : listeners) {
				listener.printMsgWrongInput(WRONG_MSG_ONE);
			}
		}

	}
	
	


	public void getLastContact() throws IOException {
		
		
		if(!ifEmptyFile()){
			while(iterator.hasNext())
				c = (Contact) iterator.next();
			
			stringContactFields();
			
			
			for (IListener listener : listeners) {
				listener.updateColor(EnumColor.NEXTANDLAST.getColor());		
			}
		}
		else{
			for (IListener listener : listeners) {
				listener.printMsgWrongInput(WRONG_MSG_ONE);
			}

		}

	}
	public int  caculateCurrantId() {
		return (getPointer()/getObjectSize())+1;

	}



	public void stringContactFields(){
		for (IListener listener : listeners) {
			
			listener.inputContactField(c.getFirstName(),c.getLastName(), c.getPhoneNumber());
		}	
	}



	public void getContactToTextField(){
		for (IListener listener : listeners) {
			listener.showToField(c.getFirstName(),c.getLastName(), c.getPhoneNumber());
		}
		
		
	}






	@Override
	public void registerListener(IListener iListener) {
		listeners.add(iListener);

	}




	public void getField() {
		for (IListener listener : listeners) {
			listener.getContactValues(getFirstNameFromFile(),getLastNameFromFile(), getPhoneNumberFromFile());
		}

	}

	public void getValues() {
		for (IListener listener : listeners) {
			listener.getContactValues(c.getFirstName(),c.getLastName(), c.getPhoneNumber());
		}

	}




	public void ifFileEmpty() throws IOException {
		if(raf.length() == 0){
			for (IListener listener : listeners) {
				listener.getTheResoult(true);
			}
		}else{
			for (IListener listener : listeners) {
				listener.getTheResoult(false);
			}
		}

	}




	public void startTimerRunning(String organization) throws IOException {
		if(organization == ASCENDING_STRING)
			getFirstContact();
		else
			getLastContact();
		timer= new Timer(DELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(organization == ASCENDING_STRING){
						getNextContact();
						if(ifLastContact())
							timer.stop();
					}else{
						getPreviuseContact();
						if(ifFirstContact())
							timer.stop();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		timer.start();

	}




	public void startTheAppWithFirstContact() throws IOException {
		if(!ifEmptyFile()){
				c = (Contact) iterator.next();
				for (IListener listener : listeners) {
					listener.updateColor(EnumColor.START.getColor());		
				}
				stringContactFields();
			}
	}




	public void chackObject(int convertIdToInteger, String getFormateChoosen) {
		File f = new File(convertIdToInteger+"."+getFormateChoosen);
		if(f.exists()){
			for (IListener listener : listeners) {
				listener.getResultIfExist(true);
			}
		}else{
			for (IListener listener : listeners) {
				listener.getResultIfExist(false);
			}
		}
		
	}
	

	




















}

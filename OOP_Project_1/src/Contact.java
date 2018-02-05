import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Serializable;


public class Contact implements IContact,Serializable,IFinalContact {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static int runNumberId = 1;

	private int id;
	private String firstName;
	private String lastName;
	private String phoneNumber;



	public Contact(){
		this.id = runNumberId++;
	}

	public Contact(int id,String firstName,String lastName,String phoneNumber){
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setPhoneNumber(phoneNumber);
	
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;	
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;	
	}


	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}


	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	@Override
	public void writeObject(RandomAccessFile randomAccessFile) throws IOException {
		FixedLengthStringIO.writeFixedLengthString(getId()+"", PERMANENT_SIZE, randomAccessFile);
		FixedLengthStringIO.writeFixedLengthString(getFirstName(), PERMANENT_SIZE, randomAccessFile);
		FixedLengthStringIO.writeFixedLengthString(getLastName(), PERMANENT_SIZE, randomAccessFile);
		FixedLengthStringIO.writeFixedLengthString(getPhoneNumber(),PERMANENT_SIZE, randomAccessFile);
	}

	@Override
	public void export(String format, File file) throws IOException {
		if(format.equals(ContactsManagerFrame.FIRST_FORMAT_FILE)){
			PrintWriter pw = new PrintWriter(file);
			pw.println(getId());
			pw.println(getFirstName());
			pw.println(getLastName());
			pw.println(getPhoneNumber());
			pw.close();
		}else if(format.equals(ContactsManagerFrame.SECOND_FORMAT_FILE)){
			ObjectOutputStream objectWriter = new ObjectOutputStream(new FileOutputStream(file));
			objectWriter.writeObject(this);
			objectWriter.close();
		}else if(format.equals(ContactsManagerFrame.THIRD_FORMAT_FILE)){
			ObjectOutputStream byteWriter = new ObjectOutputStream(new FileOutputStream(file));
			byteWriter.writeInt(getId());
			byteWriter.writeUTF(getFirstName());
			byteWriter.writeUTF(getLastName());
			byteWriter.writeUTF(getPhoneNumber());
			byteWriter.close();
		}
	}


	@Override
	public String[] getUiData() {
		String[] uiData = { "" + getId(), getFirstName(), getLastName(),getPhoneNumber() };
		return uiData;
	}


	@Override
	public int getObjectSize() {
		return CONTACT_FIELD;
	}



}

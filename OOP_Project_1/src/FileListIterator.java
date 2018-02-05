import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ListIterator;

public class FileListIterator<T extends IContact> implements ListIterator<T>,IFinalContact{

	private RandomAccessFile raf;
	private int pointerFinalContact = 0;
	private int pointer = 0;
	private int pointerNextAndPreviusContact = 0;
	private boolean change= false;
	

	public FileListIterator( RandomAccessFile raf)throws IOException{
		this.raf = raf;
	}


	public void upPointer(){
		pointerFinalContact+=CONTACT_FIELD;
	}


	public void downPointer(){
		pointerFinalContact-=CONTACT_FIELD;
	}


	@SuppressWarnings("unchecked")
	private T initContactWorkaround(int id, String firstName, String lastName, String phoneNumber) throws IOException {
		return (T) new Contact(id, firstName, lastName, phoneNumber);
	}

	@Override
	public boolean hasNext() {
		try {
			if(raf.length() != CONTACT_FIELD* pointer)
				return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}



	@Override
	public boolean hasPrevious() {
		if( 1 == pointer)
			return false;
		return true;
	}
	
	@Override
	public T next() {
		String idStr;
		String firstName;
		String lastName;
		String	phoneNumber;
		try {
			if(change)
				pointerNextAndPreviusContact+=CONTACT_FIELD;
			raf.seek(pointerNextAndPreviusContact);
			idStr = FixedLengthStringIO.readFixedLengthString(PERMANENT_SIZE, raf);
			int id = Integer.parseInt(idStr.trim());
			firstName = FixedLengthStringIO.readFixedLengthString(PERMANENT_SIZE, raf);
			lastName = FixedLengthStringIO.readFixedLengthString(PERMANENT_SIZE, raf);
			phoneNumber = FixedLengthStringIO.readFixedLengthString(PERMANENT_SIZE, raf);
			change = true;
			nextId();
			return  initContactWorkaround( id, firstName.trim(),  lastName.trim(),  phoneNumber.trim());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public T previous() {
		String idStr;
		String firstName;
		String lastName;
		String	phoneNumber;
		try {
			previousId ();
			pointerNextAndPreviusContact-=CONTACT_FIELD;
			raf.seek(pointerNextAndPreviusContact);
			idStr = FixedLengthStringIO.readFixedLengthString(PERMANENT_SIZE, raf);
			int id = Integer.parseInt(idStr.trim());
			firstName = FixedLengthStringIO.readFixedLengthString(PERMANENT_SIZE, raf);
			lastName = FixedLengthStringIO.readFixedLengthString(PERMANENT_SIZE, raf);
			phoneNumber = FixedLengthStringIO.readFixedLengthString(PERMANENT_SIZE, raf);
		
			return  initContactWorkaround( id, firstName.trim(),  lastName.trim(),  phoneNumber.trim());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void nextId (){
		pointer++;
	}
	public void previousId (){
		pointer--;
	}



	@Override
	public void set(T e) {
		try {
			raf.seek((pointer-1)*e.getObjectSize());
			e.writeObject(raf);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	@Override
	public void add(T e) {
		try {
			raf.seek(raf.length());
			e.writeObject(raf);
			upPointer();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public int nextIndex() {
		return 0;
	}

	@Override
	public int previousIndex() {
		return 0;
	}

	@Override
	public void remove() {
	}

}

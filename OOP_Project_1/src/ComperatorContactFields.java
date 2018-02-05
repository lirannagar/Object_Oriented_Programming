import java.util.Comparator;

public class ComperatorContactFields implements Comparator<Contact> {

	private String field;
	
	public ComperatorContactFields(String field){
		this.field = field;
	}

	@Override
	public int compare(Contact o1, Contact o2) {
		switch(field){
		case ContactsManagerFrame.FIRST_FIELD_TO_SORT:
			if(o1.getFirstName().compareToIgnoreCase(o2.getFirstName())==0 )
				return 0;
			else
			return -1;
			
		case ContactsManagerFrame.SECOND_FIELD_TO_SORT:
			
			if(o1.getLastName().compareToIgnoreCase(o2.getLastName())==0 )
				return 0;
			else
			return -1;
		case ContactsManagerFrame.THIRD_FIELD_TO_SORT:
			if(o1.getPhoneNumber().compareToIgnoreCase(o2.getPhoneNumber())== 0)
				return 0;
			else
			return -1;
			
		}
		
		return 0;
	}

}

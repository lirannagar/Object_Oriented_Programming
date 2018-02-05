import java.util.Comparator;

public class ComperatorContactFieldsByFirstName implements Comparator<Contact> {


	
	@Override
	public int compare(Contact o1, Contact o2) {
		if(o1.getFirstName().compareToIgnoreCase(o2.getFirstName())>0 )
			return 1;
		return -1;
	}

}

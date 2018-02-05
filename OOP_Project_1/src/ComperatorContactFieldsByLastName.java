import java.util.Comparator;

public class ComperatorContactFieldsByLastName implements Comparator<Contact> {

	@Override
	public int compare(Contact o1, Contact o2) {
		if(o1.getLastName().compareTo(o2.getLastName())>0 )
			return 1;
		return -1;
	}

}

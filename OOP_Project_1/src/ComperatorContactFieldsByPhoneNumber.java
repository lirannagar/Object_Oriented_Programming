import java.util.Comparator;

public class ComperatorContactFieldsByPhoneNumber implements Comparator<Contact> {

	@Override
	public int compare(Contact o1, Contact o2) {
		if(o1.getPhoneNumber().compareTo(o2.getPhoneNumber())>0 )
			return 1;
		return -1;
	}

}

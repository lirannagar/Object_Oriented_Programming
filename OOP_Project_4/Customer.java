
public class Customer {
	public static int idCounter = 1;
	private int id;
	private String name;
	private int amountPurchases;

	public Customer(String name,int amountPurchases){
		id = idCounter++;
		setName(name);
		setAmountPurchases(amountPurchases);
	}

	public void setAmountPurchases(int amountPurchases) {
		this.amountPurchases = amountPurchases;
	}

	public void setName(String name) {
		this.name = name;	
	}


	public boolean equals(Object c){
		Customer cus = (Customer)c;
		if(this.id == cus.id)
			return true;
		else if( c.getClass().getName().equals(this.getClass().getName()) && cus.name.equals(this.name))
			return true;
		return false;
	}

	public int getAmountPurchases() {
		return amountPurchases;
	}

	public Customer findCustomer(int id,Customer[] customersWithOutDuplicate){
		for (int i = 0; i < HW1_LiranNagar.MAX_CUSTOMERS; i++) {
			if(id == customersWithOutDuplicate[i].id)
				return customersWithOutDuplicate[i];
		}
		return null;
		
	}



	public String toString(){
		String str = String.format("%2d - %-10s%10d",id,name,amountPurchases);
		return str;
	}

}

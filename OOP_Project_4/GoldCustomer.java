
public class GoldCustomer extends PreferredCustomer{
	

	public GoldCustomer( String name, int amountPurchases,int discount) {
		super(name, amountPurchases, discount);
	}

	public int getFinalAmountPurchases(){
		if(this.getAmountPurchases()>50000)
			return 50000;
		return this.getAmountPurchases();
	}

	public boolean equals(Object c){
		if(super.equals(c)){
			if(c instanceof GoldCustomer)
				return true;
		}
		return false;
	}




	public String toString(){
		return super.toString() ;

	}


}

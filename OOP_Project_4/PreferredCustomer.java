
public class PreferredCustomer extends Customer{
	
	

	private  int discount;



	public PreferredCustomer(String name, int amountPurchases, int discount) {
		super(name, amountPurchases);
		setDiscount(discount);
	}

	public int getFinalAmountPurchases(){
		if(this.getAmountPurchases()> 10000){
			int percent = (100-discount);
			int finalAmountPurchases = this.getAmountPurchases()*percent/100;
			return  finalAmountPurchases;
		}
		return this.getAmountPurchases();
	}


	public void setDiscount(int discount) {
		this.discount = discount;
	}


	public boolean equals(Object c){
		if(super.equals(c)){
			if(c instanceof PreferredCustomer){
				if(((PreferredCustomer) c).discount == this.discount)
					return true;
			}
		}
		return false;
	}



	public String toString(){
		return super.toString() + String.format("(discount %2d%%) %2s",discount,this.getClass().getSimpleName());
	}



}

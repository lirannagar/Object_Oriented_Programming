import java.util.InputMismatchException;
import java.util.Scanner;

public class HW1_LiranNagar {

	public static final int MAX_CUSTOMERS = 11;

	public static void main(String[] args) {
		Customer[] c = new Customer[MAX_CUSTOMERS];
		makeCustomerList(c);
		System.out.println("All Customers:");
		printDuplicate(c);
		System.out.println("\nAll customers without duplications:");
		Customer[]  customersWithOutDuplicate = new Customer[MAX_CUSTOMERS];
		print(c,customersWithOutDuplicate);
		System.out.println("\nCustomers and their payments (without duplications):");
		payments(customersWithOutDuplicate);
		System.out.print("Total revenues from all customers (without duplications):");
		totalRevenues(customersWithOutDuplicate);
		search(customersWithOutDuplicate);
	}



	public static void search(Customer[] customersWithOutDuplicate) {
		Scanner in = new Scanner(System.in);
		boolean exit = true;
		while(exit){
			try{
				System.out.println("Please enter id to search for:");
				int searchId = in.nextInt();
				System.out.println(customersWithOutDuplicate[0].findCustomer(searchId,customersWithOutDuplicate));
				exit = false;
			}
			catch(InputMismatchException exIfNotInteger){
				System.out.println( "==> Id must be intger");
				in.nextLine(); //Clean the buffer
			}	
			catch (NullPointerException   ifNull){
				System.out.println( "==> Id  not found");
			}
			catch(Exception exIfNegetiveNumber){
				System.out.println( "==> Id can not be less than 1");
			}	
		
		}
	}



	public static void totalRevenues(Customer[] customersWithOutDuplicate) {
		int totalRevenues = 0;
		for (int i = 0; i < customersWithOutDuplicate.length; i++) {
			if(customersWithOutDuplicate[i] != null){
				if(customersWithOutDuplicate[i] instanceof GoldCustomer)
					totalRevenues+= ((GoldCustomer)customersWithOutDuplicate[i]).getFinalAmountPurchases();
				else if(customersWithOutDuplicate[i] instanceof PreferredCustomer)	
					totalRevenues+= ((PreferredCustomer)customersWithOutDuplicate[i]).getFinalAmountPurchases();
				else
					totalRevenues+= customersWithOutDuplicate[i].getAmountPurchases();
			}
		}
		System.out.println(" "+totalRevenues);
	}



	public static void payments(Customer[]  customersWithOutDuplicate) {
		for (int i = 0; i < customersWithOutDuplicate.length; i++) {
			if(customersWithOutDuplicate[i] != null){
				System.out.print(customersWithOutDuplicate[i] + " ");
				if(customersWithOutDuplicate[i] instanceof GoldCustomer)
					System.out.printf("%15d",((GoldCustomer) customersWithOutDuplicate[i]).getFinalAmountPurchases());
				else if(customersWithOutDuplicate[i] instanceof PreferredCustomer)	
					System.out.printf("%10d",((PreferredCustomer) customersWithOutDuplicate[i]).getFinalAmountPurchases());
				else
					System.out.printf("%42d",customersWithOutDuplicate[i].getAmountPurchases());
				System.out.println();
			}
		}
	}




	public static void print(Customer[] c,Customer[]  customersWithOutDuplicate) {
		initHelperArray(c,customersWithOutDuplicate);
		deleteDuplicateCustomersFromArray(c,customersWithOutDuplicate);
		printCustomersWithOutDuplicate(customersWithOutDuplicate);
	}

	public static void printCustomersWithOutDuplicate(Customer[] customersWithOutDuplicate) {
		for (int i = 0; i < customersWithOutDuplicate.length; i++) {
			if(customersWithOutDuplicate[i] != null)
				System.out.println(customersWithOutDuplicate[i]);
		}
	}



	public static void deleteDuplicateCustomersFromArray(Customer[] c, Customer[] customersWithOutDuplicate) {
		for (int i = 0; i < customersWithOutDuplicate.length; i++) {
			for (int j = i+1; j < customersWithOutDuplicate.length; j++) {
				if(c[j] != null){
					if(c[i].equals(c[j]))
						customersWithOutDuplicate[j]=null;
				}
			}
		}
	}



	public static void initHelperArray(Customer[] c, Customer[] customersWithOutDuplicate) {
		for (int i = 0; i < customersWithOutDuplicate.length; i++) {
			customersWithOutDuplicate[i]= c[i];
		}
	}



	public static void printDuplicate(Customer[] c) {
		for (int i = 0; i < c.length; i++) {
			System.out.println(c[i]);
		}
	}

	public static void makeCustomerList(Customer[] c) {
		c[0] = new GoldCustomer("Rothschild",500000,30);
		c[1] = new GoldCustomer("Bill Gates", 300000,10);
		c[2] = new Customer("Tali", 20);
		c[3] = new PreferredCustomer("James Bond", 3000, 20);
		c[4] = new Customer("Rothschild", 500000);
		c[5] = new PreferredCustomer("Tali", 20, 20);
		c[6] = new GoldCustomer("Tali", 20,20);
		c[7] = new GoldCustomer("Rothschild", 500000,30);
		c[8] = new PreferredCustomer("Rothschild", 500000, 30);
		c[9] = new Customer("Rothschild", 500000);
		c[10]= new PreferredCustomer("Tali", 20, 20);
	}





}

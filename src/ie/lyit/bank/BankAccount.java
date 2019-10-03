/**
* Class: B.Sc. in Cloud Computing
* Instructor: Maria Boyle
* Description:  
* 	BankAccount abstract class used for sub class inheritance, 
* 	a component of a banking system which models The Bank of Ireland System.
* Date: 26/09/2019
* @author Dane Campbell - L00142041
* @version 1.0
*/

package ie.lyit.bank;


/** 
 * BankAccount abstract class used for sub class BankAccount inheritance.
 */
public abstract class BankAccount {

	/* class variable nextAccountNumber. */
	private static int nextAccountNumber=1;
	
	/* Instance variables – accountNo, balance, namePrimary, address, dateOpened */
	protected int accountNo;
	protected double balance;
	protected Name nameA;
	protected String addressA;
	protected Date dateOpened;
	
	/** 
	 * DEfault constructor, initializes balance to zero, 
	 * sets account to nextAccountNumber then increments nextAccountNumber,
	 * nameA to default Name object and dateOpened to default Date object.
	 */
	public BankAccount(){
		this.accountNo = nextAccountNumber++;
		this.balance = 0.0;
		this.nameA = new Name();
		this.addressA = "";
		this.dateOpened = new Date();
	}
	
	/** 
	 * initialization constructor, initializes balance to zero, 
	 * sets account to nextAccountNumber then increments nextAccountNumber,
	 * nameA to name param and dateOpened to dateOpened param.
	 */
	public BankAccount(Name name, String address, double balance, Date dateOpened){
		this.accountNo = nextAccountNumber++;
		this.balance = balance;
		this.nameA = name;
		this.addressA = address;
		this.dateOpened = dateOpened;
	}

	/** Returns a String value which will be used to display a BankAccount object. */
	@Override
	public String toString() {
		return "Account: " + accountNo + ", \t€" + balance + ", "
				+ "\nName: " + nameA + ", "
				+ "\naddress: "+addressA + ", "
				+ "\ndateOpened: " + dateOpened;
	}

	/** Takes a BankAccount object parameter and returns a boolean (true or false) to indicate equality. */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		if (accountNo != other.accountNo)
			return false;
		return true;
	}
	
	/* BEGIN: getters and setters */
	// accountNo ===
	public int getAccountNo() {
		return accountNo;
	}
	// name ===
	public Name getName() {
		return nameA;
	}

	public void setName(Name name) {
		this.nameA = name;
	}
	// address ===
	public String getAddress() {
		return addressA;
	}

	public void setAddress(String address) {
		this.addressA = address;
	}
	// balance ===
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	// dateOpened ===
	public Date getDateOpened() {
		return dateOpened;
	}
	/* END: getters and setters */ 

	
	
	
	
	
	
}

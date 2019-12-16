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

import java.io.Serializable;

/** 
 * BankAccount abstract class used for sub class BankAccount inheritance.
 */
public abstract class BankAccount implements Transactable, Serializable{

	/* class variable nextAccountNumber. */
	private static int nextAccountNumber=100000;
	
	/* Instance variables – accountNo, balance, namePrimary, address, dateOpened */
	protected Name name;
	protected int accountNo;
	protected double balance;
	protected String address;
	protected Date dateOpened;
	
	/** 
	 * DEfault constructor, initialises balance to zero, 
	 * sets account to nextAccountNumber then increments nextAccountNumber,
	 * nameA to default Name object and dateOpened to default Date object.
	 */
	public BankAccount(){
		this.accountNo = nextAccountNumber++;
		this.balance = 0.0;
		this.name = new Name();
		this.address = "";
		this.dateOpened = new Date();
	}
	
	/** 
	 * Initialisation constructor, initialises balance to zero, 
	 * sets account to nextAccountNumber then increments nextAccountNumber,
	 * nameA to name param and dateOpened to dateOpened param.
	 */
	public BankAccount(Name name, String address, double balance, Date dateOpened){
		this.accountNo = nextAccountNumber++;
		this.balance = balance;
		this.name = name;
		this.address = address;
		this.dateOpened = dateOpened;
	}

	/** Returns a String value which will be used to display a BankAccount object. */
	@Override
	public String toString() {
		return "Account: " + accountNo + ", \t€" + balance + ", "
				+ "\nName: " + name + ", "
				+ "\naddress: "+address + ", "
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
	public void setNextAccountNumber(int accNo) { // ***** added to set next account number when deserializing accounts array list *****
		nextAccountNumber = accNo;
	}
	// accountNo ===
	public int getAccountNo() {
		return accountNo;
	}
	// name ===
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}
	
	public void setTitle(String title) {
		this.name.setTitle(title);
	}
	
	public void setSurname(String surname) {
		this.name.setLastName(surname);
	}
	// address ===
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
	
	
	@Override
	public void deposit(double amount) {
		this.balance += amount;
		
	}

	@Override
	public void withdraw(double amount) throws IllegalArgumentException {
		if(this.balance >= amount)
			this.balance -= amount;
		else {
			throw new IllegalArgumentException("Withdraw amount exceeds current balance!");
		}
			
		
	}
	
	
	
	
	
}

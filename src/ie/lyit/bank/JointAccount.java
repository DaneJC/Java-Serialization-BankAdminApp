/**
* Class: B.Sc. in Cloud Computing
* Instructor: Maria Boyle
* Description:  
* 	JointAccount class ...
* Date: 26/09/2019
* @author Dane Campbell - L00142041
* @version 1.0
*/

package ie.lyit.bank;

public class JointAccount extends BankAccount{

	private Name nameB;
	private String addressB;
	
	public JointAccount() {
		super();
		nameA = new Name();
		addressA = "";	
	}
	
	public JointAccount(Name nameA, Name nameB, String addressA, String addressB, double balance, Date dateOpened) {
		super(nameA, addressA, balance, dateOpened);
		this.nameB = nameB;
		this.addressB = addressB;	
	}

	@Override
	public String toString() {
		return    "Account: "+ accountNo +"\t€"+ balance+ 
				"\n  NameA: " + nameA + ", Address: " + addressA +
				"\n  nameB: " + nameB + ", Address: " + addressB + 
				"\n Opened: " + dateOpened;
	}

	public Name getNameB() {
		return nameB;
	}

	public void setNameB(Name nameB) {
		this.nameB = nameB;
	}

	public String getAddressB() {
		return addressB;
	}

	public void setAddressB(String addressB) {
		this.addressB = addressB;
	}

	
	
	
	
	
	
}

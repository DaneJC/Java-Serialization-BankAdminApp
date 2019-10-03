package ie.lyit.testers;

import ie.lyit.bank.Date;
import ie.lyit.bank.JointAccount;
import ie.lyit.bank.Name;
import ie.lyit.utils.TestsPrinter;

public class JointAccountTester {
	
	/*    ***** TESTS/TASKS *****
	 * 1: Create JointAccount objects. 
	 * 2: Set their details.
	 * 3: Display them on the screen. 
	 * 4: Change some details, and display again.
	 * 5: Create two separate JointAccount objects with the same values and test for equality.
	*/

	public static void main(String[] args) {
		
		// TestsPrinter utilised to aid printing ascetics
		TestsPrinter print = new TestsPrinter(80);
		
		/* 1: Create JointAccount objects. */ 
		print.newTestTask("1: Create JointAccount objects.");
		print.process("Creating Date objects: accOne, accTwo");
		
		JointAccount accOne = new JointAccount(
					new Name("Mr", "Homer", "Simpson"),
					new Name("Ms", "Marge", "Simpson"),
					"1 Springfield Road, Springfield, OvertHere.",
					"2 Springfield Road, Springfield, OverThere.",
					1000.00,
					new Date(26, 9, 2019)
				);
		
		JointAccount accTwo = new JointAccount();
		
		/* 2: Set their details. */
		print.newTestTask("2: Set their details");
		print.process("Setting their details..");
		
		accTwo.setName(new Name("Mr", "Bart", "Simpson"));
		accTwo.setName(new Name("Ms", "Lisa", "Simpson"));
		accTwo.setAddress("1 Springfield Road, Springfield, OverHere.");
		accTwo.setAddressB("2 Springfield Road, Springfield, Overthere.");
		accTwo.setBalance(2000.00);
		
		print.newSegment("-");
		print.newSubTestTask("toString()");
		print.toStringTest("accOne.toString()", accOne.toString(),"???");	
		
		
		

	}

}

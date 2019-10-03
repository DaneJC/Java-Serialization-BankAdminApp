package ie.lyit.testers;

import java.util.ArrayList;

import ie.lyit.bank.CurrentAccount;
import ie.lyit.bank.Date;
import ie.lyit.bank.JointAccount;
import ie.lyit.bank.Name;
import ie.lyit.utils.TestsPrinter;

public class CurrentTester {

	public static void main(String[] args) {
		
		/*    ***** TESTS/TASKS *****
		 * 1: Create two separate Current objects with different initial values. 
		 * 2: Display these on the screen.
		 * 3: Test the two objects for equality. 
		 * 4: Create an ArrayList called currents which is capable of storing a list of Current objects.
		 * 5: Add 4 Current objects to this ArrayList and print out the whole list.
		 * 6: Add a currentSearch() method to the CurrentTester class.
		 * 		currentSearch() should take 2 parameters, a Current object, and an ArrayList of Currents, 
		 * 		and should return a boolean value (true or false) if the Current object is stored in the ArrayList.
		 * 		Add code to the main method to test this method.
		*/
		
		// TestsPrinter utilised to aid printing ascetics
		TestsPrinter print = new TestsPrinter(120);
		
		/* 1: Create two separate Current objects with different initial values.  */ 
		print.newTestTask("1: Create two separate Current objects with different initial values");
		print.process("Creating two CurrentAccount objects: accOne, accTwo");
		
		CurrentAccount accOne = new CurrentAccount(
					new Name("Mr", "Homer", "Simpson"),
					"1 Springfield Road, Springfield, OverHere.",
					1000.00,
					500,
					new Date(03, 10, 2019)
				);
		
		CurrentAccount accTwo = new CurrentAccount(
					new Name("Mrs", "Marge", "Simpson"),
					"1 Springfield Road, Springfield, OverThere.",
					2000.00,
					250,
					new Date(03, 10, 2019)
				);
		print.endTest();
		
		/* 2: Display these on the screen  */ 
		print.newTestTask("2: Display these on the screen");
		print.toStringTest(
				"accOne.toString()", 
				accOne.toString(),
				" Account: 1	€1000.0\n" + 
				"    Name: Mr Homer Simpson, Address: 1 Springfield Road, Springfield, OverHere.\n" + 
				"Overdaft: 500.0\n" + 
				"  Opened: 3/10/2019");	
		
		print.newSegment("-");
		print.toStringTest(
				"accTwo.toString()", 
				accTwo.toString(),
				"Account: 2	€2000.0\n" + 
				"    Name: Mrs Marge Simpson, Address: 1 Springfield Road, Springfield, OverThere.\n" + 
				"Overdaft: 250.0\n" + 
				"  Opened: 3/10/2019");
		print.endTest();
		
		/* 3: Test the two objects for equality. */
		print.newTestTask("3: Test the two objects for equality.  with equals()");
		print.newSubTestTask("Testing two CurrentAccount objects accOne + accTwo for equality with equals()");
		print.booleanTest("accOne.equals(accTwo)", accOne.equals(accTwo), false);
		print.newSegment("-");
		print.newSubTestTask("Testing duplicate CurrentAccount objects accOne + accOne for equality with equals()");
		print.booleanTest("accOne.equals(accOne)", accOne.equals(accOne), true);
		print.endTest();
		
		/* 4: Create an ArrayList called currents which is capable of storing a list of Current objects. */
		print.newTestTask("4: Create an ArrayList called currents which is capable of storing a list of Current objects.");
		print.process("Creating ArrayList called currents capable of storing a list of Current objects");
		ArrayList<CurrentAccount> currents = new ArrayList<CurrentAccount>();
		print.endTest();
		
		/* 5: Add 4 CurrentAccount objects to this ArrayList and print out the whole list */
		print.newTestTask("5: Add 4 CurrentAccount objects to this ArrayList and print out the whole list");
		print.process("Adding CurrentAccount objects to this ArrayList = accOne, accTwo, accThree, accFour.");
		currents.add(accOne);
		currents.add(accTwo);
		
		CurrentAccount accThree = new CurrentAccount(
				new Name("Mr", "Bart", "Simpson"),
				"1 Springfield Road, Springfield, OverThere.",
				10.00,
				50,
				new Date(03, 10, 2019)
			);
		CurrentAccount accFour = new CurrentAccount(
				new Name("Ms", "Lisa", "Simpson"),
				"1 Springfield Road, Springfield, OverThere.",
				20.00,
				100,
				new Date(03, 10, 2019)
			);
		currents.add(accThree);
		currents.add(accFour);
		
		print.newSegment("-");
		print.newSubTestTask("Print out the whole of currents list");
		for(CurrentAccount c: currents) {
			System.out.println(c.toString());
			print.newSegment("-   ");
		}
		print.endTest();
		
		/* 6: Add a currentSearch() method to the CurrentTester class. */
		print.newTestTask("6: Add a currentSearch() method to the CurrentTester class.");
		print.newSubTestTask("Testing currentSearch() on currentAccount object accOne which IS stored in currents ArrayList");
		print.booleanTest("nameSearch(objName_1, names)", currentSearch(accOne, currents), true);
		print.newSegment("-");
		print.process("Creating 5th CurrentAccount object for none present currentSearch() test..");
		CurrentAccount accFive = new CurrentAccount(
				new Name("Ms", "Maggie", "Simpson"),
				"1 Springfield Road, Springfield, OverThere.",
				5.00,
				10,
				new Date(03, 10, 2019)
			);
		
		print.newSubTestTask("Testing currentSearch() on currentAccount object accFive which IS NOT stored in currents ArrayList");
		print.booleanTest("nameSearch(objName_4, names)", currentSearch(accFive, currents), false);
		print.endTest();
		print.concludeTests();
		
		print.concludeTests();
		
	}

}

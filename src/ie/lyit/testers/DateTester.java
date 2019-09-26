/**
* Class: B.Sc. in Cloud Computing
* Instructor: Maria Boyle
* Description: 
* 	DateTester class containing main method to execute tester code on Date class.
* Date: 19/09/2019
* @author Dane Campbell - L00142041
* @version 1.0
*/

package ie.lyit.testers;

import ie.lyit.bank.Date;
import ie.lyit.bank.Name;
import ie.lyit.utils.TestsPrinter;

/** 
 * DateTester class containing main method to execute tester code on Date class. 
 */
public class DateTester {

	/*    ***** TESTS/TASKS *****
	 * 1: Create Date objects. 
	 * 2: Set their details.
	 * 3: Display them on the screen. 
	 * 4: Change some details, and display again.
	 * 5: Create two separate Name objects with the same values and test for equality.
	*/

	public static void main(String[] args) {
		
		// TestsPrinter utilised to aid printing ascetics
		TestsPrinter print = new TestsPrinter(94);
		
		/* 1: Create Name objects. */
		print.newTestTask("1: Create Date objects");
		print.process("Creating Date objects: objDate_1, objDate_2");
		
		Date objDate_1 = new Date();	// default constructor
		Date objDate_2 = new Date(19,9,2019);	// initialization constructor
		
		/* 2: Set their details. */
		print.newTestTask("2: Set their details");
		print.process("Setting their details..");
		
		objDate_1.setDay(1);	// set method
		objDate_1.setMonth(2);
		objDate_1.setYear(2000);

		
		/* 3: Display them on the screen.*/
		// get method
		print.newTestTask("3: Display them on the screen");
		print.newSubTestTask("getDay(), getMonth(), getYear()");
		
		System.out.println("=> Date [day: "+objDate_1.getDay()+", month: "+objDate_1.getMonth()+", year: "+objDate_1.getYear()+"]"
				+ " \t| Expected: Date [day: 01, month: 02, year: 2000]");
		System.out.println("=> Date [day: "+objDate_2.getDay()+", month: "+objDate_2.getMonth()+", year: "+objDate_1.getYear()+"]"
				+ " \t| Expected: Date [day: 19, month: 9, year: 2019]");
		
		
		
		// toString method
		print.newSegment("-");
		print.newSubTestTask("toString()");
		print.toStringTest("objDate_1.toString()", objDate_1.toString(),"Date [day: 1, month: 2, year: 2000]");	
		print.newSegment("-   ");
		print.toStringTest("objDate_2.toString()", objDate_2.toString(),"Date [day: 19, month: 9, year: 2019]");
		print.endTest();
		
		/* 4: Change some details, and display again. */
		print.newTestTask("4: Change some details, and display again.");
		print.newSubTestTask("Changing objDate_1 -> objDate_2 details..");
				
		objDate_1.setDay(25);	// set method
		objDate_1.setMonth(12);
		objDate_1.setYear(2000);
		
		objDate_2.setDay(5);	// set method
		objDate_2.setMonth(8);
		objDate_2.setYear(1988);

		print.newSubTestTask("Displaying updated details toString()");
		
		print.toStringTest("objDate_1.toString()", objDate_1.toString(), "Date [day: 25, month: 12, year: 2000]");	
		print.newSegment("-   ");
		print.toStringTest("objDate_2.toString()", objDate_2.toString(), "Date [day: 5, month: 8, year: 1988]");
		print.endTest();
		
		/* 5: Create two separate Name objects with the same values and test for equality. */
		print.newTestTask("5: Create two separate Name objects with the same values and test for equality");
		print.process("Creating 2 new duplicate Name objects: objDate_3, objDate_4 => new Date(\"12\", \"12\", \"2012\")");
		
		Date objDate_3 = new Date(12 ,12, 2012);	// Initialization constructor
		Date objDate_4 = new Date(12 ,12, 2012);
		print.newSegment("-");
		print.newSubTestTask("Testing duplicate objects objDate_3 + objDate_4 for equality with equals()");
		print.booleanTest("objDate_3.equals(objName_6)", objDate_3.equals(objDate_4), true);
		print.newSegment("-");
		print.newSubTestTask("Testing duplicate objects objDate_1 + objDate_4 for equality with equals()");
		print.booleanTest("objDate_1.equals(objName_6)", objDate_1.equals(objDate_4), false);
		print.endTest();
		print.concludeTests();

	}

}

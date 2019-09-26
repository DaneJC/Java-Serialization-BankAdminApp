/**
* Class: B.Sc. in Cloud Computing
* Instructor: Maria Boyle
* Description: 
* 	NameTester class containing main method to execute tester code on Name class.
* Date: 19/09/2019
* @author Dane Campbell - L00142041
* @version 1.0
*/

package ie.lyit.testers;

import java.util.ArrayList;

import ie.lyit.bank.Name;
import ie.lyit.utils.TestsPrinter;

/** 
 * NameTester class containing main method to execute tester code on Name class. 
 */
public class NameTester {
	
	/*    ***** TESTS/TASKS *****
	 * 1: Create Name objects. 
	 * 2: Set their details.
	 * 3: Display them on the screen. 
	 * 4: Change some details, and display again.
	 * 5: Create two separate Name objects with the same values and test for equality.
	 * 6: Test the two Name objects to see if they are male or female.
	 * 7: Create an ArrayList called names which is capable of storing a list of Name objects. 
	 * 8: Add 3 Name objects to this ArrayList and print out the whole list.
	 * 9: Add a nameSearch() method to the NameTester class: 
	 * 		nameSearch() should take 2 parameters, a Name parameter, and an ArrayList of Names,
	 * 		and should return a boolean value (true or false) if the Name is stored in the ArrayList. 
	 * 		Add code to the main method to test this method.
	*/

	public static void main(String[] args) {
		
		// TestsPrinter utilised to aid printing ascetics
		TestsPrinter print = new TestsPrinter(125);
		
		/* 1: Create Name objects. */
		print.newTestTask("1: Create Name objects");
		print.process("Creating Name objects: objName_1, objName_2, objName_3, objName_4..");
		
		Name objName_1 = new Name("mR", "dAne   ", "  campBell");	// Initialization constructor + sanitise method
		Name objName_2 = new Name("Ms", "  rOSe", "dUNnE");
		Name objName_3 = new Name();	// default constructor
		Name objName_4 = new Name();
		
		/* 2: Set their details. */
		print.newTestTask("2: Set their details");
		print.process("Setting their details..");
		
		objName_3.setTitle("Miss");	// set method
		objName_3.setFirstName("Judy");
		objName_3.setLastName("Smith");
		
		objName_4.setTitle("Mrs");
		objName_4.setFirstName("Caroline");
		objName_4.setLastName("Ward");
		
		/* 3: Display them on the screen.*/
		// get method
		print.newTestTask("3: Display them on the screen");
		print.newSubTestTask("getTitle(), getFrstName(), getLastName()");
		
		System.out.println("=> Name [title: "+objName_1.getTitle()+", firstName: "+objName_1.getFirstName()+", lastName: "+objName_1.getLastName()+"]"
				+ " \t| Expected: Name [title: Mr, firstName: Dane, lastName: Campbell]");
		
		System.out.println("=> Name [title: "+objName_2.getTitle()+", firstName: "+objName_2.getFirstName()+", lastName: "+objName_2.getLastName()+"]"
				+ " \t| Expected: Name [title: Ms, firstName: Rose, lastName: Dunne]");
		
		System.out.println("=> Name [title: "+objName_3.getTitle()+", firstName: "+objName_3.getFirstName()+", lastName: "+objName_3.getLastName()+"]"
				+ " \t| Expected: Name [title: Miss, firstName: Judy, lastName: Smith]");
		
		System.out.println("=> Name [title: "+objName_4.getTitle()+", firstName: "+objName_4.getFirstName()+", lastName: "+objName_4.getLastName()+"]"
				+ " \t| Expected: Name [title: Mrs, firstName: Caroline, lastName: Ward]");
		
		// toString method
		print.newSegment("-");
		print.newSubTestTask("toString()");
		print.toStringTest("objName_3.toString()", objName_3.toString(),"Name [title: Miss, firstName: Judy, lastName: Smith]");	
		print.newSegment("-   ");
		print.toStringTest("objName_4.toString()", objName_4.toString(),"Name [title: Mrs, firstName: Caroline, lastName: Ward]");
		print.endTest();
		
		/* 4: Change some details, and display again. */
		print.newTestTask("4: Change some details, and display again.");
		print.newSubTestTask("Changing objName_3 -> objName_4 details..");
				
		objName_3.setTitle("Miss");	// set method
		objName_3.setFirstName("MarY ");
		objName_3.setLastName(" wIMMs");
		
		objName_4.setTitle("Mrs");
		objName_4.setFirstName("aNn");
		objName_4.setLastName("Goan");

		print.newSubTestTask("Displaying updated details toString()");
		
		print.toStringTest("objName_3.toString()", objName_3.toString(), "Name [title: Miss, firstName: Mary, lastName: Wimms]");	
		print.newSegment("-   ");
		print.toStringTest("objName_4.toString()", objName_4.toString(), "Name [title: Mrs, firstName: Ann, lastName: Goan]");
		print.endTest();
		
		/* 5: Create two separate Name objects with the same values and test for equality. */
		print.newTestTask("5: Create two separate Name objects with the same values and test for equality");
		print.process("Creating 2 new duplicate Name objects: objName_5, objName_6 => new Name(\"Mr\", \"Micheal\", \"Higgins\")..");
		
		Name objName_5 = new Name("Mr", "Micheal", "Higgins");	// Initialization constructor + sanitise method
		Name objName_6 = new Name("Mr", "Micheal", "Higgins");
		
		print.newSegment("-");
		print.newSubTestTask("Testing duplicate objects objName_5 + objName_6 for equality with equals()");
		print.booleanTest("objName_5.equals(objName_6): ", objName_5.equals(objName_6), true);
		print.newSegment("-");
		print.newSubTestTask("Testing duplicate objects objName_1 + objName_6 for equality with equals()");
		print.booleanTest("objName_1.equals(objName_6)", objName_1.equals(objName_6), false);
		print.endTest();
		
		/* 6: Test the two Name objects to see if they are male or female. */
		print.newTestTask("6: Test the two Name objects to see if they are male or female");
		print.newSubTestTask("Testing male object with isFemale()");
		print.booleanTest("objName_5.isFemale()", objName_5.isFemale(), false);
		print.newSegment("-");
		print.newSubTestTask("Testing female object with isFemale()");
		print.booleanTest("objName_2.isFemale()", objName_2.isFemale(), true);
		print.endTest();
		
		/* 7: Create an ArrayList called names which is capable of storing a list of Name objects. */
		print.newTestTask("7: Create an ArrayList called names which is capable of storing a list of Name objects");
		print.process("Creating an ArrayList called names => ArrayList<Name> names = new ArrayList<Name>();");

		ArrayList<Name> names = new ArrayList<Name>();
		
		/* 8: Add 3 Name objects to this ArrayList and print out the whole list. */
		print.newTestTask("8: Add 3 Name objects to this ArrayList and print out the whole list");
		print.newSubTestTask("Adding 3 Name objects to names ArrayList => names.add(objName_1), names.add(objName_2), names.add(objName_3)");
		names.add(objName_1);
		names.add(objName_2);
		names.add(objName_3);
		
		print.newSubTestTask("Displaying new names ArrayList");
		for(Name n : names) {
			System.out.println(n.toString());
		}
		print.endTest();
		
		/* 9: Add a nameSearch() method to the NameTester class */
		print.newTestTask("9: Add a nameSearch() method to the NameTester class");
		print.newSubTestTask("Testing nameSearch() on Name object objName_1 which IS  stored in names ArrayList");
		print.booleanTest("nameSearch(objName_1, names)", nameSearch(objName_1, names), true);
		print.newSegment("-");
		print.newSubTestTask("Testing nameSearch() on Name object objName_4 which IS NOT stored in names ArrayList");
		print.booleanTest("nameSearch(objName_4, names)", nameSearch(objName_4, names), false);
		print.endTest();
		print.concludeTests();
	}
	
	/*** 
	 * nameSearch() takes 2 parameters, a Name parameter, and an ArrayList of Names,
	 * and returns a boolean value (true or false) if the Name is stored in the ArrayList parameter.
	 */
	private static boolean nameSearch(Name name, ArrayList<Name> names) {
		boolean nameStored = false;
		for(Name n: names) {
			if(n.equals(name)) {
				nameStored = true;
				break;
			}
			else nameStored = false;
		}
		return nameStored;
		
	}

}

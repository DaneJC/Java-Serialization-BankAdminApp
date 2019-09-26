/**
* Class: B.Sc. in Cloud Computing
* Instructor: Maria Boyle
* Description: 
* 	TestsPrinter class designed to provide familiar, consistent & standardised console outputs for class testing.
* 	Also, each tester class contains a numbered list of tests/tasks to be carried out, 
* 	utilising this TestsPrinter class also ensures each of these test/tasks have in fact been carried out
* 	as each new test/task is logged and the total displayed at the end of testing which, 
* 	when referenced to the numbered list of the tester class confirms if all tests/task are conducted or not.   
* Date: 19/09/2019
* @author Dane Campbell - L00142041
* @version 1.0
*/

package ie.lyit.utils;

public class TestsPrinter{
	
	private String hrBold, hrNorm, hrSegment, hrSubSegment, hrAlert;
	private int hrLength, testsPassed, testsFailed, total;
	
	public TestsPrinter(int horizontalRuleLength) {
		hrLength = horizontalRuleLength;
		hrBold = initHorizontalRules("=", horizontalRuleLength);
		hrNorm = initHorizontalRules("-", horizontalRuleLength);
		hrSegment = initHorizontalRules("- ", horizontalRuleLength/2);
//		hrSubSegment = initHorizontalRules("- ", horizontalRuleLength/4);
		hrAlert = initHorizontalRules("*", horizontalRuleLength);
		testsPassed = 0;
		testsFailed = 0; 
		total = 0;
	}
	
	private String initHorizontalRules(String symbol, int length) {
		StringBuilder s = new StringBuilder();
		for(int i=0; i<length; i++) {
			s.append(symbol);
		}
		return s.toString();
	}
	
	private void incPasses() {
		testsPassed += 1;
	}
	
	private void incFails() {
		testsFailed += 1;
	}
	
	private void incTotal() {
		total += 1;
	}
	
	public void process(String note) {
		System.out.println("# "+note);
	}
	
	public void newTestTask(String description) {
		System.out.println(hrBold);
		System.out.println("+ TEST/TASK: "+description+":");
		System.out.println(hrNorm);
		incTotal();
	}
	
	public void newSubTestTask(String description) {
		System.out.println("# : "+description+":");
		System.out.println(hrSegment);
//		incTotal();
	}
	
	public void newSegment(String symbol) {
		StringBuilder s = new StringBuilder();
		int divider = hrLength;
		if(symbol.length()>1)
			divider=hrLength/symbol.length();
		
		for(int i=0; i<divider; i++) {
			s.append(symbol);
		}
		System.out.println(s.toString());
	}
	
	public void endTest() {
		System.out.println(hrBold);
	}
	
	public void booleanTest(String syntax, boolean result, boolean expectedResult) {
		System.out.println(syntax+": => "+result+" \t| Expected: "+expectedResult);
//		if(result == expectedResult)
//			incPasses();
//		else incFails();
	}
	
	public void toStringTest(String syntax, String result, String expectedResult) {
		System.out.println(syntax+":\n=> "+result+" \t| Expected: "+expectedResult);
//		if(result == expectedResult)
//			incPasses();
//		else incFails();
	}
	
	public void concludeTests() {
		System.out.println("\n"+hrAlert);
		System.out.println("TESTS COMPLETE:");
//		System.out.println("PASSED: "+testsPassed);
//		System.out.println("FAILED: "+testsFailed);
		System.out.println("---------------");
		System.out.println("TOTAL:  "+total);
		System.out.println(hrAlert);
	}

	
}

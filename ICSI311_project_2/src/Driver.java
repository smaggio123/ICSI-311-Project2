import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Driver Class
 * Tests all of the other classes
 * @author Steven Maggio
 *
 */
public class Driver {
	
	//Used to hold dashed lines that make the output look nicer
	private static String divider="";
	
	//The number of inputs per analyzer
	private final static int NUMBER_OF_INPUTS=20;
	
	/**
	 * Main method.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//file manager manages the test case files
		FileManager fm = new FileManager(NUMBER_OF_INPUTS);
		
		//Uncommenting these lines will cause the program to make the test case files. Uncomment if you changed the value of NUMBER_OF_INPUTS.
		//fm.makeLexicalTestFiles();
		//fm.makeSyntaxTestFiles();
		
		//Uncomment below this comment to update the files if you changed any values in the array lists in fileManager
		/*
		//Makes sure that files are updated
		fm.writeToLexicalTestFiles();
		System.out.println("*Updated all lexical analyzer test cases*");
		//Makes sure that files are updated
		fm.writeToSyntaxTestFiles();
		System.out.println("*Updated all syntax analyzer test cases*");
		*/
		
		//initializes the divider string to have 100 of "-"
		for(int j=0;j<100;j++) {
			divider+="-";
		}
		
		//Uncommenting these lines will cause the program to print the inputs found in all of the files for each analyzer
		//fm.printLexicalInput();
		//fm.printSyntaxInput();
		
		
		//Tests the LexicalAnalyzer class
		testLexicalAnalyzer();
		//Tests the SyntaxAnalyzer class
		testSyntaxAnalyzer();
		
	}
	
	/**
	 * Tests the LexicalAnalyzer class
	 * @throws IOException
	 */
	public static void testLexicalAnalyzer() throws IOException {
		//Initializes the lexical analyzer to have the file id of the file to parse through
		for(int i=1;i<=NUMBER_OF_INPUTS;i++) {
			LexicalAnalyzer analyzer = new LexicalAnalyzer(i);
			analyzer.start();
			System.out.println();
		}
	}
	
	/**
	 * Tests the SyntaxAnalyzer class
	 * @throws IOException
	 */
	public static void testSyntaxAnalyzer() throws IOException {
		//Initializes the syntax analyzer to have the file id of the file to parse through
		for(int i=1;i<=NUMBER_OF_INPUTS;i++) {
			//For nicer output
			System.out.println();
			System.out.println("Start of tree:"+i);
			System.out.println(divider);
			
			
			
			SyntaxAnalyzer analyzer = new SyntaxAnalyzer(i);
			analyzer.start();
			
			//For nicer output
			System.out.println(divider);
			System.out.println("End of tree:"+i);
			System.out.println();
		}
	}

}

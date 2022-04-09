import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is the file manager.
 * The purpose of this class is to make it easy to edit all of the test case files.
 * You can print all of the inputs from all of the files by calling printSyntaxInput() and printLexicalInput() in the driver class
 * @author Steven Maggio
 *
 */
public class FileManager {
	//Stores test cases for syntax analyzer class
	private ArrayList<String>syntaxTestCases;
	//Stores test cases for lexical analyzer class
	private ArrayList<String>lexicalTestCases;
	//Number of inputs
	private int numberOfInputs;
	
	/**
	 * Initializes array lists
	 * @throws IOException
	 */
	public FileManager(int inputCount) throws IOException {
		syntaxTestCases = new ArrayList<String>();
		lexicalTestCases= new ArrayList<String>();
		initializeSyntaxList();
		initializeLexicalList();
		numberOfInputs=inputCount;
	}
	
	/**
	 * Makes the test case files for the syntax analyzer
	 */
	public void makeSyntaxTestFiles() {
		for(int i=1;i<=numberOfInputs;i++) {
		try {
		      File file = new File("src/syntaxAnalysisTestCases/testCase"+i+".txt");
		      if (file.createNewFile()) {
		        System.out.println("File "+i+" created.");
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		    }
		}
	}
	
	/**
	 * Makes the test case files for the lexical analyzer
	 */
	public void makeLexicalTestFiles() {
		for(int i=1;i<=numberOfInputs;i++) {
			
		try {
		      File file = new File("src/lexicalAnalysisTestCases/testCase"+i+".txt");
		      if (file.createNewFile()) {
		        System.out.println("File "+i+" created.");
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		    }
		}
	}
	
	/**
	 * Writes test cases to the syntax analyzer test case files
	 * @throws IOException
	 */
	public void writeToSyntaxTestFiles() throws IOException {
		int index=0;
		for(int i=1;i<=syntaxTestCases.size();i++) {
			File file = new File("src/syntaxAnalysisTestCases/testCase"+i+".txt");
			FileWriter myWriter = new FileWriter(file);
			myWriter.write(syntaxTestCases.get(index));
			index++;
			myWriter.close();
		}
		
	}
	
	/**
	 * Writes test cases to the lexical analyzer test case files
	 * @throws IOException
	 */
	public void writeToLexicalTestFiles() throws IOException {
		int index=0;
		for(int i=1;i<=lexicalTestCases.size();i++) {
			File file = new File("src/lexicalAnalysisTestCases/testCase"+i+".txt");
			FileWriter myWriter = new FileWriter(file);
			myWriter.write(lexicalTestCases.get(index));
			index++;
			myWriter.close();
		}
		
	}
	
	/**
	 * Inserts syntax analyzer test cases into array list
	 */
	private void initializeSyntaxList() {
		syntaxTestCases.add("(sum+47)/total");
		syntaxTestCases.add("(sum-difference)/5");
		syntaxTestCases.add("5-2");
		syntaxTestCases.add("3*4-1");
		syntaxTestCases.add("someVar-sum");
		syntaxTestCases.add("(1-2)*(five-four)");
		syntaxTestCases.add("(age+10)/count");
		syntaxTestCases.add("5+(count-outliers)");
		syntaxTestCases.add("(a*a)+(b*b)+(c*c)");
		syntaxTestCases.add("(12+(32-(count-sum)))");
		syntaxTestCases.add("5");
		syntaxTestCases.add("(1+two-(3*four)/5)");
		syntaxTestCases.add("running+out+of+variable+name+ideas");
		syntaxTestCases.add("(intersect+union+intersect/3)");
		syntaxTestCases.add("(nameCount+ageCount-totalNumberOfPeople)/dayOfTHeWeek");
		syntaxTestCases.add("1+2+3+4-5-6-7*8*9*10/11/12/13");
		syntaxTestCases.add("12 + 24 -32 +22/1");
		syntaxTestCases.add("(7-(sum*(2)/2)+(3+4)-count)");
		syntaxTestCases.add("(sum + 47 * a) / total - 8");
		syntaxTestCases.add("out - of * ideas /four / variable +names");
		
	}
	
	/**
	 * Inserts lexical analyzer test cases into array list
	 */
	private void initializeLexicalList() {
		lexicalTestCases.add("(sum+47)/total");
		lexicalTestCases.add("(sum-difference)/5");
		lexicalTestCases.add("5-2");
		lexicalTestCases.add("3*4-1");
		lexicalTestCases.add("someVar-sum");
		lexicalTestCases.add("(1-2)*(five-four)");
		lexicalTestCases.add("(age+10)/count");
		lexicalTestCases.add("5+(count-outliers)");
		lexicalTestCases.add("(a*a)+(b*b)+(c*c)");
		lexicalTestCases.add("boolean greater = a > b");
		lexicalTestCases.add("age=5");
		lexicalTestCases.add("switch(charClass)");
		lexicalTestCases.add("float age;");
		lexicalTestCases.add("int age;");
		lexicalTestCases.add("1+2;do");
		lexicalTestCases.add("age=32;while(true)");
		lexicalTestCases.add("math=1+2;if(true)");
		lexicalTestCases.add("bool keepPlaying;else()");
		lexicalTestCases.add("5 + two -for /sum");
		lexicalTestCases.add("out - of * ideas /four / variable +names");
		
	}
	
	/**
	 * Prints test cases in array list
	 */
	public void printSyntaxInput() {
		System.out.println("Syntax input is:");
		for(int i=0;i<syntaxTestCases.size();i++) {
			System.out.println((i+1)+". "+syntaxTestCases.get(i));
		}
	}
	
	/**
	 * Prints test cases in array list
	 */
	public void printLexicalInput() {
		System.out.println("Lexical input is:");
		for(int i=0;i<lexicalTestCases.size();i++) {
			System.out.println((i+1)+". "+lexicalTestCases.get(i));
		}
	}
}

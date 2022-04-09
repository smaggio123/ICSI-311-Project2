import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Syntax analyzer class
 * makes a parse tree with the given input files
 * @author Steven Maggio
 * @extends LexicalAnalyzer
 *
 */
public class SyntaxAnalyzer extends LexicalAnalyzer{
	
	/**
	 * Initializes the name of the file to be parsed through
	 * Also initializes the array list that holds the token values of reserved words
	 * @param fileId The id of the file to be parsed through
	 * @throws IOException
	 */
	public SyntaxAnalyzer(int fileId) throws IOException {	
			super(fileId);
			String path="src/syntaxAnalysisTestCases/testCase"+fileId+".txt";
			File file = new File(path);
			fileReader = new BufferedReader(new FileReader(file));		
		
	}
	
	/**
	 * Called to start the analysis process
	 * @throws IOException
	 */
	public void start() throws IOException {
		getChar();
		//While scanned lexemes are valid
		while (nextToken!=EOF) {
			lex();
			expr();
		}
		
		
		fileReader.close();
	}
	
	/**
	 * Expression grammar of the tree
	 * @throws IOException
	 */
	public void expr() throws IOException {
		System.out.println("Enter <expr>\n");
		
		term();
		//While '+' or '-' is found
		while(this.nextToken == this.ADD_OP||this.nextToken == this.SUB_OP) {
			this.lex();
			term();
		}
		
		System.out.println("Exit <expr>\n");
	}
	
	
	
	/**
	 * Term grammar of the tree
	 * @throws IOException
	 */
	public void term() throws IOException {
		System.out.println("Enter <term>\n");
		
		factor();
		//While '*' or '/' is found
		while(this.nextToken == this.MULT_OP||this.nextToken == this.DIV_OP) {
			this.lex();
			factor();
		}
		System.out.println("Exit <term>\n");
	}
	
	
	
	/**
	 * Factor grammar of the tree
	 * @throws IOException
	 */
	public void factor() throws IOException {
		System.out.println("Enter <factor>\n");
		//While IDENT or INT_LIT is found
		if(this.nextToken ==this.IDENT||this.nextToken==this.INT_LIT) {
			this.lex();
		}
		else {
			//Determining if '(' or ')' is found, calls error otherwise
			if(this.nextToken==this.LEFT_PAREN) {
				this.lex();
				expr();
				if(this.nextToken == this.RIGHT_PAREN) {
					this.lex();
				}
				else {
					error(1);
				}
			}else {
				error(2);
			}
		}
		
		System.out.println("Exit <factor>\n");
	}
	
	
	/**
	 * Prints that there is an error when running through the parse tree
	 * @param ERROR_ID The programmer assigned number to figure out where the error was thrown in the code.
	 */
	public void error(int ERROR_ID) {
		System.out.printf("Error occurred in %d while parsing!\n",ERROR_ID);
	}
}

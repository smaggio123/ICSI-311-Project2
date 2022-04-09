import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * lexical analyzer class
 * Parses through the files and tokenizes the lexemes
 * @author Steven Maggio
 *
 */
public class LexicalAnalyzer {
	int charClass; //The data type of the character (LETTER,DIGIT,UNKNOWN)
	Character [] lexeme = new Character[100]; //Holds lexeme
	char nextChar; //The next character scanned
	int lexLen; //index in the lexeme array
	int nextToken; //token of the scanned lexeme
	BufferedReader fileReader;
	/* Character classes */
	public final int  LETTER =0;
	public final int  DIGIT =1;
	public final int  UNKNOWN =99;
	/* Token codes */
	public final int  INT_LIT =10;
	public final int  IDENT =11;
	public final int  ASSIGN_OP =20;
	public final int  ADD_OP =21;
	public final int  SUB_OP =22;
	public final int  MULT_OP =23;
	public final int  DIV_OP =24;
	public final int  LEFT_PAREN =25;
	public final int  RIGHT_PAREN =26;
	public final int  SEMICOLON=27;
	public final int  LESS_THAN_OP=28;
	public final int  GREATER_THAN_OP=29;
	public final int  FORLOOP=30;
	public final int  IFSTMT=31;
	public final int  ELSESTMT=32;
	public final int  WHILELOOP=33;
	public final int  DOLOOP=34;
	public final int  INTTYPE=35;
	public final int  FLOATTYPE=36;
	public final int  SWITCHSTMT=37;
	
	public final int EOF=-1;
	
	//Used to indicate that a reserved word has been identified
	boolean quit=false; //If a reserved word is found, the program exits on this input
	
	
	/**
	 * Initializes the name of the file to be parsed through
	 * Also initializes the array list that holds the token values of reserved words
	 * @param fileId The id of the file to be parsed through
	 * @throws IOException
	 */
	public LexicalAnalyzer(int fileId) throws IOException {	
			String path="src/lexicalAnalysisTestCases/testCase"+fileId+".txt";
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
		}
		
		
		fileReader.close();
	}

	/**
	 * Tokenizes the next char in the file that is not white space
	 * @return nextToken Token of the next char that is read
	 * @throws IOException
	 */
	public int lex() throws IOException {
		// TODO Auto-generated method stub
		lexLen=0;
		getNonBlank();
		//if the program read a reserved word
		if(quit==true) {
			//set next token to EOF to indicate to the program to stop scanning
			nextToken=EOF;
			return nextToken;
		}
		//Determining the proper action to take based on the assigned token
		switch(charClass) {
		case LETTER:
			addChar();
			getChar();
			//Determining if there are more letters/numbers following the letter to determine if it is a name
			while(charClass == LETTER || charClass ==DIGIT) {
				addChar();
				getChar();
			}
			//Get name of the identifier/reserved word
			String str="";
			boolean keyWordUsed=false;
			if(lexeme!=null) {
				int i=0;
				while(lexeme[i]!=null&&lexeme[i]>0) {
					str+=""+lexeme[i];
					i++;
				}
				//Determine of the word is reserved
				keyWordUsed=isKeyWord(str);
			}
			//If not, it is an identifier
			if(keyWordUsed==false) {
				nextToken = IDENT;				
			}
			else {
				//else stop program from further scanning this input
				quit=true;
			}
			break;
			
		case DIGIT:
			addChar();
			getChar();
			//Determine if the number has more digits
			while(charClass == DIGIT) {
				addChar();
				getChar();
			}
			nextToken = INT_LIT;
			break;
		//If scanned lexeme is not a letter or digit
		case UNKNOWN:
			//looks up if the lexeme is a recognizable symbol
			lookup(nextChar);
			getChar();
			 break;
		//If scanned lexeme is not recognized
		case EOF:
			 nextToken = EOF;
			 lexeme[0] = 'E';
			 lexeme[1] = 'O';
			 lexeme[2] = 'F';
			 lexeme[3] = 0;
			 break;

		}
		//Getting name of lexeme stored in the array
		String str="";
		if(lexeme!=null) {
			int i=0;
			while(lexeme[i]!=null&&lexeme[i]>0) {
				str+=""+lexeme[i];
				i++;
			}			
		}
		//Printing token value and name of lexeme
		if(str!="") {
			System.out.printf("Next token is: %d, Next lexeme is %s\n", nextToken, str);
			
		}
		return nextToken;
	}

	/**
	 * Determines if word is a reserved word and tokenizes it if so.
	 * @param s Word being reviewed
	 * @return true if word is reserved, false otherwise
	 */
	public boolean isKeyWord(String s) {
		// TODO Auto-generated method stub
		if(s.equals("for")) {
			nextToken=FORLOOP;
			return true;
		}
		else if(s.equals("if")) {
			nextToken=IFSTMT;
			return true;
		}
		else if(s.equals("else")) {
			nextToken=ELSESTMT;
			return true;
		}
		else if(s.equals("while")) {
			nextToken=WHILELOOP;
			return true;
		}
		else if(s.equals("do")) {
			nextToken=DOLOOP;
			return true;
		}
		else if(s.equals("int")) {
			nextToken=INTTYPE;
			return true;
		}
		else if(s.equals("float")) {
			nextToken=FLOATTYPE;
			return true;
		}
		else if(s.equals("switch")) {
			nextToken=SWITCHSTMT;
			return true;
		}
		else {
			return false;	
		}
	}

	/**
	 * Determines if scanned lexeme is a recognizable symbol
	 * @param ch Character being reviewed
	 * @return nextToken Token of the next lexeme
	 */
	public int lookup(char ch) {
		// TODO Auto-generated method stub
		switch(ch){
	 case '(':
		 addChar();
		 nextToken = LEFT_PAREN;
		 break;
	 case ')':
		 addChar();
		 nextToken = RIGHT_PAREN;
		 break;
	 case ';':
		 addChar();
		 nextToken = SEMICOLON;
		 break;
	 case '<':
		 addChar();
		 nextToken = LESS_THAN_OP;
		 break;
	 case '>':
		 addChar();
		 nextToken = GREATER_THAN_OP;
		 break;
	 case '+':
		 addChar();
		 nextToken = ADD_OP;
		 break;
	 case '-':
		 addChar();
		 nextToken = SUB_OP;
		 break;
	 case '*':
		 addChar();
		 nextToken = MULT_OP;
		 break;
	 case '/':
		 addChar();
		 nextToken = DIV_OP;
		 break;
	 case '=':
		 addChar();
		 nextToken = ASSIGN_OP;
		 break;
	 default:
		 addChar();
		 nextToken =EOF;
		 break;
		}
		return nextToken;
	}

	/**
	 * Adds char to the next available spot in the array
	 */
	public void addChar() {
		// TODO Auto-generated method stub
		if(lexLen<=98) {
			lexeme[lexLen++]=nextChar;
			lexeme[lexLen]=0;
		}
	}

	/**
	 * Scans file until a non white space character is found
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public void getNonBlank() throws IOException {
		// TODO Auto-generated method stub
		while(Character.isSpace(nextChar)) {
			//makes a new line to separate the different inputs if there is more than one line in a file
			if((int)nextChar==10) {
				System.out.println();
			}
			getChar();
		}
	}

	/**
	 * Gets next character in the file
	 * @throws IOException
	 */
	public void getChar() throws IOException {
		int nextCharacter=fileReader.read();
		nextChar=(char)nextCharacter;
		//Determines the data type of the scanned character
		if(nextCharacter!=-1) {
			//If character is a letter
			if(Character.isAlphabetic(nextChar)) {
				charClass=LETTER;
			}
			//If character is a digit
			else if(Character.isDigit(nextChar)) {
				charClass=DIGIT;
			}
			//If character is of unknown data type
			else {
				charClass=UNKNOWN;
			}			
		}
		//If at end of file
		else {
			charClass=EOF;
		}
		
	}
	
}

import java.util.List;

public class Converter{
	
	//instance variables
	private String userInfix; //the user input
	private char[] infixChar; //converted to a CharArray for parse()
	private List<String> tokenList; //parse method returns ArrayList<String>
	private String postfix = ""; // empty string for output
	private Stack<String> stack = new ArrayStack<String>(); //empty stack


	
	//constructor
	public Converter(String userInfix){
		this.userInfix = userInfix;
		this.infixChar = userInfix.toCharArray();
		this.tokenList = ParserHelper.parse(infixChar); //tokenizing the userInfix which was turned into infixChar
	}

	public String toPostFix(){

		for(int i = 0; i < tokenList.size(); i++)
		{
			char firstDigit = tokenList.get(i).charAt(0); 
			String c = tokenList.get(i);

			// for operands
			if (Character.isDigit(firstDigit)){
				 postfix += tokenList.get(i) + " ";   // checks if it's a number, adds to string
			}else if(stack.isEmpty())
			{
				stack.push(c);
			}else if(c.equals("(")){
				stack.push(c);
			}else if(c.equals(")")){
				String temp = stack.top(); 
				while(!stack.isEmpty() && !temp.equals("(")){
					postfix += temp + " "; 
					stack.pop(); 
					temp = stack.top();
				}
				if (!stack.isEmpty() && temp.equals("(")){
					stack.pop();
					}
				
			}else if (c.equals("*") || c.equals("/") || c.equals("+") || c.equals("^") || c.equals("-")){
				if(stack.isEmpty() || precedence(c) < precedence(stack.top())){  //empty or higher precedence
					stack.push(c);
				}else{
					do{ 
						postfix += stack.top() + " "; 
						stack.pop();
					} while (!stack.isEmpty() && precedence(c) >= precedence(stack.top())); //not empty and lower precedence
				
					stack.push(c);
				}

			}// close else if
			
			if(i == tokenList.size() - 1){
				do{
					postfix += stack.top() + " "; 
					stack.pop();
				}while(!stack.isEmpty());
			}
		} //close for loop

		return postfix; 
	} //Post fix close

	private int precedence(String c){
		if (c.equals("(")) {
			return 4; 
		}else if(c.equals("+") || c.equals("-")){
			return 3;
		}else if(c.equals("/") || c.equals("*")){
			return 2;
		}else if (c.equals("^")){
			return 1;
		}else                                //if (c.equals(")") || c.equals("(")){
			return 0; 
		}
}
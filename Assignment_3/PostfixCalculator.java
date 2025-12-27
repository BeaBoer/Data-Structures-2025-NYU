import java.util.Arrays;
import java.util.List;

public class PostfixCalculator{
	
	public static double evaluate(String postfix){
	    Stack<Double> stack = new ArrayStack<Double>(); //stack
		double num = 0; 
		List<String> tokens = Arrays.asList(postfix.split(" "));

		for(int i = 0; i < tokens.size(); i++)
		{
			double temp1 = 0;
			double temp2 = 0; 
			char firstDigit = tokens.get(i).charAt(0); 
			String c = tokens.get(i);
			
			//operands
			if (Character.isDigit(firstDigit)){
				double numberC = Double.parseDouble(c);
				stack.push(numberC); 
			}else {
			temp2 = stack.top(); 
			stack.pop(); 
			temp1 = stack.top(); 
			stack.pop();
			
			//operators
			if(c.equals("+")) {
				num = temp1+temp2;
				stack.push(num); 
			}
			if(c.equals("-")){
				num = temp1-temp2; 
				stack.push(num);
			}
			if(c.equals("*")){
				num = temp1*temp2; 
				stack.push(num);
			}
			if(c.equals("/")){
				num = temp1/temp2; 
				stack.push(num);

				if(temp2 == 0){
					 throw new ArithmeticException("Dividing by zero is not allowed.");
				}
			}
			if(c.equals("^")){
				num = Math.pow(temp1, temp2); 
				stack.push(num); 
			}	
		  }
		}
		return stack.pop(); 
		
		
	}
	
	
}
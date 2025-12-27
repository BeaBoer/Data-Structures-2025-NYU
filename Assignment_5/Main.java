import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			System.out.println("Type your expression: ");
            String infix = scanner.nextLine();
            if (infix.isEmpty()) break;  // Exit if input is empty
          
            Converter converter = new Converter(infix);
            String postfix = converter.toPostFix(); //taken the infix, converted to postfix
            ExpressionTree tree = new ExpressionTree(postfix); //taken the postfix into the tree
                        
            System.out.print("Prefix: ");
            tree.preorderTrav();
            System.out.println();
            
            System.out.print("Infix: ");
            tree.inorderTrav();
            System.out.println();
            
            System.out.print("Postfix: ");
            tree.postorderTrav();
            System.out.println();
		}
		scanner.close();
	}
}

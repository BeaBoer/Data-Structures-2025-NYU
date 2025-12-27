import java.util.Arrays;
import java.util.List;

public class ExpressionTree{
	
	private Node<String> root; // for buildTree()
	Stack<Node<String>> stack = new ArrayStack<Node<String>>(); //stack
	private String postfix;
	
	//constructor
	public ExpressionTree(String postfix) {  
		this.postfix = postfix; 
		this.root = buildTree(postfix);
	}
	
	
	//create the tree, return the root Node
	private Node<String> buildTree(String postfix){
		
		List<String> tokens = Arrays.asList(postfix.split(" "));
		
		for(int i = 0; i < tokens.size(); i++)
		{
			char firstDigit = tokens.get(i).charAt(0); //used to check for number
			String c = tokens.get(i); //c is current token
			
			if (Character.isDigit(firstDigit)){ //if the firstDigit is a number
				Node<String> num = new Node (c); 
				stack.push(num);		
			}else {
				Node<String> right = stack.pop();
				Node<String> left = stack.pop(); 
				Node<String> op = new Node(c, left, right); 
				stack.push(op);
			}
			
		} // end of for loop
		
		if (!stack.isEmpty()) {
	        return stack.top();
	    } else {
	        return null;
	    }
	} 
	
	//preorder traversal
	public void preorderTrav() {
		preorderTrav(root);
	}
	private void preorderTrav(Node<String> node) {
		if(node == null) { return; }
		System.out.print(node.element);
		preorderTrav(node.leftChild);
		preorderTrav(node.rightChild);
	}
	
	//inorder traversal
	public void inorderTrav() {
		inorderTrav(root);
	}
	private void inorderTrav(Node<String> node) {
		if(node == null) { return; }
		
		boolean leaf = (node.leftChild != null || node.rightChild != null);
		if (leaf){
			System.out.print("(");	
		}
		
		inorderTrav(node.leftChild);
		System.out.print(node.element);
		inorderTrav(node.rightChild);
		
		if (leaf){
			System.out.print(")");	
		}
	}
	
	//postorder traversal
	public void postorderTrav() {
		postorderTrav(root);
	}
	private void postorderTrav(Node<String> node) {
		if(node == null) { return; }
		postorderTrav(node.leftChild);
		postorderTrav(node.rightChild);
		System.out.print(node.element);
	}
	
}//end of Expression Tree
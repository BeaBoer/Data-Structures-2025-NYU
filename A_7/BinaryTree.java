import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer; 
// BinaryTree.java

public class BinaryTree<E> {
    private TreeNode<E> root;
    
    public BinaryTree(TreeNode<E> root) {
        this.root = root;
    }
    
    public double findClosest(E target) {
        if (root == null) {
            throw new NoSuchElementException("Element " + target + " not found!");
        }
        
        BinaryHeap<TreeNode<E>> bheap = new BinaryHeap<>();
        root.distance = 0.0;
        bheap.insert(root);
        
        while (!bheap.isEmpty()) {
            TreeNode<E> q = bheap.deleteMin();
            
            if (q.element.equals(target)) {
                return q.distance;
            }
            
            if (q.leftChild != null) {
                double childOriginalDistance = q.leftChild.distance;
                q.leftChild.distance = q.distance + childOriginalDistance;
                bheap.insert(q.leftChild);
            }
            
            if (q.rightChild != null) {
                double childOriginalDistance = q.rightChild.distance;
                q.rightChild.distance = q.distance + childOriginalDistance;
                bheap.insert(q.rightChild);
            }
        }
        
        throw new NoSuchElementException("Element " + target + " not found!");
    }
    
    public static BinaryTree<String> buildTree(String input) {
        StringTokenizer token = new StringTokenizer(input);
        ArrayStack<TreeNode<String>> stack = new ArrayStack<>();
        
        while (token.hasMoreTokens()) {

            String temp = token.nextToken();
                      
            if (temp.equals("(")) {
                continue;
            } else if (temp.equals(")")) {
                
                TreeNode<String> rightChild = stack.pop();
                TreeNode<String> leftChild = stack.pop();
                TreeNode<String> parent = stack.pop();
              
                parent.leftChild = leftChild;
                parent.rightChild = rightChild;
                
                stack.push(parent);
            } else {
                String element = temp;
                double distance = Double.parseDouble(token.nextToken());
                TreeNode<String> newNode = new TreeNode<>(element);
                newNode.distance = distance;
                stack.push(newNode);
              }
        }
    
        TreeNode<String> root = stack.pop();
        root.distance = 0.0;
        return new BinaryTree<>(root);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your space and parentheses-separated tree below:");
        String input = scanner.nextLine();
    
        BinaryTree<String> btree = buildTree(input);
            
            try {
                double distance = btree.findClosest("*");
                System.out.println("Found '*' at distance " + distance + ".");
            } catch (NoSuchElementException e) {
                System.out.println("Exception in thread \"main\" java.util.NoSuchElementException: Element * not found!");
            }
        
        scanner.close();
    }
}


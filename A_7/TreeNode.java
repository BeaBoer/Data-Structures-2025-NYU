public class TreeNode<E> implements Comparable<TreeNode<E>> {
    public E element;
    public TreeNode<E> leftChild;
    public TreeNode<E> rightChild;
    public double distance; // distance from parent
    
    public TreeNode(E element) {
        this.element = element;
        this.distance = 0.0;
    }
    
    public String toString() {
        return element.toString();
    }
    
    public int compareTo(TreeNode<E> other) {
        // Compare based on distance from root
        return Double.compare(this.distance, other.distance);
    }
}
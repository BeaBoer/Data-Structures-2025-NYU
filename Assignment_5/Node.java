/** Node of a binary tree **/
public class Node<E> {
	
	public E element;
	public Node<E> leftChild;
	public Node<E> rightChild;
	
	public Node (E element) {
		this (element, null, null);
	}
	
	public Node (E element, Node<E> l, Node<E> r) {
		this.element = element;
		leftChild = l;
		rightChild = r;
	}
	
	public String toString() {
		return element.toString();
	}
}

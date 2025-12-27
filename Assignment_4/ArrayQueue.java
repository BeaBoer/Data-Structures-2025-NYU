public class ArrayQueue<E> implements Queue<E> {
// instance variables
	private static final int CAPACITY = 1000;
	private E[ ] data;
	private int f = 0;
	private int sz = 0;
	
	// constructors
	public ArrayQueue( ) {this(CAPACITY);}
	// generic array used for storage // index of the front element
	// current number of elements
	public ArrayQueue(int capacity) {
		data = (E[ ]) new Object[capacity];
	}
	
	// methods
	// Returns the number of elements in the queue.
	public int size() { return sz; }
	// Tests whether the queue is empty.
	public boolean isEmpty() { return (sz == 0); }
	
	// Inserts an element at the rear of the queue. ∗/
	public void enqueue(E e) throws IllegalStateException {
		if (sz == data.length) throw new IllegalStateException("Queue is full");
		int avail = (f + sz) % data.length; // use modular arithmetic
		data[avail] = e;
	    sz++;
	}

	// Returns, but does not remove, the first element of the queue (null if empty).
	public E first( ) {
		if (isEmpty()) return null;
		return data[f];
	}
	
	// Removes and returns the first element of the queue (null if empty).
	public E dequeue( ) {
		if (isEmpty()) return null;
		E answer = data[f];
		data[f] = null;
		f = (f + 1) % data.length;
		sz--;
		return answer;
	}
}
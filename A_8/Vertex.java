import java.util.ArrayList;

public class Vertex<E> {
  public E element;
  public ArrayList<Vertex<E>> neighbors;
  public boolean known;
  public boolean inStack;

  public Vertex(E element) {
    this.element = element;
    this.neighbors = new ArrayList<>();
    this.known = false;
    this.inStack = false;
  }

  public void addNeighbor(Vertex<E> neighbor) {
    neighbors.add(neighbor);
  }

  public String toString() {
    return element.toString();
  }
}
import java.util.ArrayList;

public class Graph<E> {
  private ArrayList<Vertex<E>> vertices;

  public Graph() {
    this.vertices = new ArrayList<>();
  }

  public void addDirectedEdge(E source, E destination) {
    Vertex<E> sourceVertex = findVertex(source);
    if (sourceVertex == null) {
      sourceVertex = new Vertex<>(source);
      vertices.add(sourceVertex);
    }
    
    Vertex<E> desVertex = findVertex(destination);
    if (desVertex == null) {
      desVertex = new Vertex<>(destination);
      vertices.add(desVertex);
    }
    
    sourceVertex.addNeighbor(desVertex);
  }

  public void addVertex(E element) {
     if (findVertex(element) == null) {
      vertices.add(new Vertex<>(element));
    }
  }

  public Vertex<E> findVertex(E element) {
    for(Vertex<E> vertex : vertices){
      if (vertex.element.equals(element)){
        return vertex; 
      }
    }
    return null;
  }

  public boolean isCyclic() {
     for (Vertex<E> u : vertices) {
      u.known = false;
      u.inStack = false;
    }
    
    for (Vertex<E> u : vertices) {
      if (!u.known) {
        if (DFS_Cyclic(u)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean DFS_Cyclic(Vertex<E> u) {
    u.inStack = true;
    
    for (Vertex<E> v : u.neighbors) {
        
        if (v.inStack) {
            return true;  
        }
        
        if (!v.known) {
            if (DFS_Cyclic(v)) {
                return true;
            }
        } 
        
    }
    
    u.inStack = false;
    u.known = true;
    return false;
}

  public static void main(String[] args) {
    Graph<String> graph1 = new Graph<>();
    graph1.addDirectedEdge("A", "B");
    graph1.addDirectedEdge("A", "C");
    graph1.addDirectedEdge("B", "D");
    graph1.addDirectedEdge("C", "E");
    graph1.addDirectedEdge("D", "E");
    System.out.println("Is graph 1 cyclic? " + graph1.isCyclic());

    Graph<String> graph2 = new Graph<>();
    graph2.addDirectedEdge("P", "Q");
    graph2.addDirectedEdge("Q", "R");
    graph2.addDirectedEdge("R", "P");
    graph2.addDirectedEdge("S", "Q");
    System.out.println("Is graph 2 cyclic? " + graph2.isCyclic());

    Graph<Integer> graph3 = new Graph<>();
    graph3.addDirectedEdge(1, 2);
    graph3.addDirectedEdge(2, 3);
    graph3.addDirectedEdge(3, 1);
    graph3.addDirectedEdge(4, 5);
    graph3.addDirectedEdge(5, 6);
    System.out.println("Is graph 3 cyclic? " + graph3.isCyclic());
  }
}
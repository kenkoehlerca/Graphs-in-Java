
package graphs;


/**
 *
 * @author Ken Koehler
 */
import java.util.*;
public class Graph {
  
  private LinkedList[] myEdges;
  private boolean directed;
  private boolean weighted;
  
  
  public Graph(int n, boolean directed, boolean weighted) {
    myEdges = new LinkedList[n];
    this.directed = directed;
    this.weighted = weighted;
  }
  public void addEdge(Edge e) {
    
    if (directed==true) {
      //directed - add only once
      AddEdgeWork(e);
    }
    else {
      //undirected - add to 2 elements in array

      AddEdgeWork(e); 
      
      //swap edges
      int tmp1 = e.getlVertice();
      int tmp2 = e.getrVertice();
      
      //create new edge object with edges swapped
      Edge e2 = new Edge(tmp2,tmp1,false,e.getWeight());

      AddEdgeWork(e2);
      
    }
  }
  
  private void AddEdgeWork(Edge e) {
    LinkedList l;
    if (myEdges[e.getlVertice()] == null) {
      l = new LinkedList();
      l.add(e);
      myEdges[e.getlVertice()] = l;
    }
    else {
      l = myEdges[e.getlVertice()];
      l.add(e);
      myEdges[e.getlVertice()] = l;
    }    
  }
  public int getAdjacentList(int v, List adjList) {
    return 1;
  }
  
  public Edge getEdge(int v1, int v2) {
    Edge e=null;  
    if (myEdges[v1]==null) {
      e = null;
    }
    else {
      LinkedList ln = myEdges[v1];
      for (Iterator it = ln.iterator(); it.hasNext();) {
        e = (Edge)it.next();
        if (e.getlVertice()==v1 && e.getrVertice()==v2) {
          break;
        }
        e = null;
      }
    }
    
    return e;
  }
  
  public int getNumVertices() {
    int x = myEdges.length;
    return x;
  }
  
  public int getNumEdges() {
    int y = 0;
    for (LinkedList item : myEdges) {
      if (item!=null) {
        y += item.size();
      }
    }
    return y;
  }
  
  public boolean isDirected() {
    return directed;
  }
  
  public boolean isWeighted() {
    return weighted;
  }
  public void PrintGraph() {
    for (int x = 0; x < myEdges.length; x++) {
      if (myEdges[x] != null) {
        LinkedList l = myEdges[x];
        for(int i = 0, n = l.size(); i < n; i++) {
          Edge e = (Edge)l.get(i);
          System.out.println(" l:"+e.getlVertice() + " r:"+e.getrVertice());
        }
      }
    }
  }
  public LinkedList GetElement(int x) {
    return this.myEdges[x];
  }
  public LinkedList[] GetGraph() {
    return this.myEdges;
  }
}

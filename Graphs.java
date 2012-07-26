
package graphs;

/**
 *
 * @author Ken
 */
import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
public class Graphs {

  Graph g;
  boolean directed;
  boolean weighted;
  boolean[] vertices;
  FileWriter fstream;
  BufferedWriter out;
  
  public static void main(String[] args) {
    Graphs p = new Graphs();
    p.InitOutputFile();
    p.Tasks();
    p.CloseOutputFile();
  }
  
  private void InitOutputFile() {
    try {
      fstream = new FileWriter("c:\\graphs_output.txt");
      out = new BufferedWriter(fstream);
    }
    catch (Exception e) {
      System.err.println("Error: "+e.getMessage());
    }
  }
  private void WriteOutputFile(String s) {
    try {
      out.write(s);
    }
    catch (Exception e) {
      System.err.println("Error: "+ e.getMessage());
    }
  }
  private void CloseOutputFile() {
    try {
      out.close();
    }
    catch (Exception e) {
      System.err.println("Error: "+ e.getMessage());
    }
  }
  private void Tasks() {
    
    //1) file 1
    WriteOutputFile("FILE 1: "+System.getProperty("line.separator"));
    File("c:\\input1.txt");
    //2) print # of vertices, the number of edges, whether directed and/or weighted;
    String s = "vertices: " + g.getNumVertices()+ " edges: "+g.getNumEdges()+ " directed: " + g.isDirected() + " weighted: " + g.isWeighted()+System.getProperty("line.separator");
    WriteOutputFile(s);
            
    //3a) invoke getEdge(0,3) and print the edge data if it exists
    invokeGetEdge(0,3);
    
    //3b) invoke getEdge(1,0) and print the edge data if it exists
    invokeGetEdge(1,0);
    
    BFS(0);
    DFS(0);
    
    g = null;
    
    WriteOutputFile(System.getProperty("line.separator"));  
    WriteOutputFile(System.getProperty("line.separator"));
    
    //1) file 2
    WriteOutputFile("FILE 2: "+System.getProperty("line.separator"));    
    File("c:\\input2.txt");
    //2) print # of vertices, the number of edges, whether directed and/or weighted;
    s="vertices: " + g.getNumVertices()+ " edges: "+g.getNumEdges()+ " directed: " + g.isDirected() + " weighted: " + g.isWeighted() + System.getProperty("line.separator");
    WriteOutputFile(s);
    
    //3a) invoke getEdge(0,3) and print the edge data if it exists
    invokeGetEdge(0,3);
    
    //3b) invoke getEdge(1,0) and print the edge data if it exists
    invokeGetEdge(1,0);
    
    BFS(0);
    DFS(0);    
    
  }

  private void InitVertices() {
    vertices = new boolean[g.getNumVertices()];
    for (int x =0; x < vertices.length; x++) {
      vertices[x]= false;
    }    
  }
  private void DFS(int start) {
   InitVertices();
   LinkedList l = g.GetElement(start);
   Edge e = (Edge)l.getFirst();
   RDFS(e);
  }
  private void RDFS(Edge e) {
    WriteOutputFile("RDFS: "+e.getlVertice() + " "+ e.getrVertice()+System.getProperty("line.separator"));
    
    vertices[e.getlVertice()] = true;
    vertices[e.getrVertice()] = true;
  
    LinkedList ll = g.GetElement(e.getrVertice());
    if (ll != null) {
      Iterator<Edge> il = ll.iterator();
      while (il.hasNext()) {
        Edge edge = il.next();
        int skip = 0;
        if (vertices[edge.getlVertice()]==true) {skip = 1;}
        if (skip ==0) {
          RDFS(edge);
        }
        skip = 0;
        if (vertices[edge.getrVertice()]==true) {skip = 1;}
        if (skip ==0) {
          RDFS(edge);
        }      
      }
    }
    
    ll = g.GetElement(e.getlVertice());
    if (ll != null) {
      Iterator<Edge> il = ll.iterator();
      while (il.hasNext()) {
        Edge edge = il.next();
        int skip = 0;
        if (vertices[edge.getlVertice()]==true) {skip = 1;}
        if (skip ==0) {
          RDFS(edge);
        }
        skip = 0;
        if (vertices[edge.getrVertice()]==true) {skip = 1;}
        if (skip ==0) {
          RDFS(edge);
        }      
      }
    }     
    
  }
  private void BFS(int start) {

    InitVertices();
    vertices[start] = true;
    
    WriteOutputFile("BFS: ");
    
    Queue<Integer> queue = new LinkedList<Integer>(); 
    queue.add(start);
    while (1==1) {
      
      if (queue.isEmpty()== true) {break;}
      
      int i = queue.remove();
      WriteOutputFile(" "+i+ " ");
      LinkedList l = g.GetElement(i);
      if (l != null) {
        Iterator<Edge> le = l.iterator();
        while (le.hasNext()) {
          Edge edge = le.next();
          
          if (vertices[edge.getlVertice()]==false) {
            vertices[edge.getlVertice()]= true;
            queue.add(edge.getlVertice());
          }
          if (vertices[edge.getrVertice()]==false) {
            vertices[edge.getrVertice()]= true;           
            queue.add(edge.getrVertice());
          }          
        }
      }
    }
    WriteOutputFile(System.getProperty("line.separator"));
  }
  private void invokeGetEdge(int x, int y) {
    Edge e = g.getEdge(x, y);
    if (e==null) {
      WriteOutputFile("edge data "+ x +" & "+y+" does not exist"+System.getProperty("line.separator"));
    }
    else {
      WriteOutputFile("edge data: " + e.getlVertice() + " "+e.getrVertice() + " directed: "+e.isIsDirected() + " weight: "+e.getWeight()+System.getProperty("line.separator"));

    }
    
  }
  private void File(String fileName) {
    try {
      FileInputStream fstream = new FileInputStream(fileName);
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String strLine;
      int header = 1;
      String delims = "[ ]+";
      while ((strLine = br.readLine()) != null) {
        if (header--  ==  1) {
          //header record
          String[] parts = strLine.split(delims);
          int vertices = Integer.parseInt(parts[0]);
          if (parts[1].equals("U")) {
            directed = false;
          }
          else {
            directed = true;
          }
          if (parts[2].equals("N")) {
            weighted=false;
          }
          else {
            weighted=true;
          }
          g = new Graph(vertices,directed,weighted);
        }
        else {
          String[] parts = strLine.split(delims);
          int lVertice = Integer.parseInt(parts[0]);
          int rVertice = Integer.parseInt(parts[1]);
          Double weight = -1.0;
          if (weighted==true) {
            weight = Double.parseDouble(parts[2]);
          }
          Edge e = new Edge(lVertice, rVertice,directed,weight);
          g.addEdge(e);
        }
        
      }
    }
    catch (Exception e) {
      System.err.println("Error: "+e.getMessage());
    }
  }
}

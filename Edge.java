/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package graphs;

/**
 *
 * @author Ken
 */
public class Edge {
  private int lVertice;
  private int rVertice;
  private boolean isDirected;
  private double weight;

  public Edge(int lVertice, int rVertice, boolean isDirected, double weight) {
    this.lVertice = lVertice;
    this.rVertice = rVertice;
    this.isDirected = isDirected;
    this.weight = weight;
  }
  public boolean isIsDirected() {
    return isDirected;
  }

  public void setIsDirected(boolean isDirected) {
    this.isDirected = isDirected;
  }

  public int getlVertice() {
    return lVertice;
  }

  public void setlVertice(int lVertice) {
    this.lVertice = lVertice;
  }

  public int getrVertice() {
    return rVertice;
  }

  public void setrVertice(int rVertice) {
    this.rVertice = rVertice;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }
  
  
}

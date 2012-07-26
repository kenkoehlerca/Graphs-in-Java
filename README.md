This was another fun project in Advanced Data Structures in Java.

Tasks are as follows:
1) Create an Edge class. It contains the attributes: 2 integers representing the vertices, a boolean indicating whether or not the edge is directed,
and a double representing the weight.  A weight of -1 indicates no weight.
2) Create a Graph class.  This must represent any graphy, directed or undirected, weighted or not weighted.

Use a modified adjacency list representation known as an Edge list.
Use an array of Lists; the lists will contain Edge objects.

Assume a fixed number of vertices, which will simply be integers from 0 to N-1.

You may implement your own lists or use List classes from Java.

Implement both a breath first and depth first traversal algorithm.  These algorithms must be separate from the Graph class and may be part of the 
main program Graphs.java.  The functions will print the nodes in the traversal order.

A parameter will provide the starting vertex.

Depth-First traversal must be recursive.

Main program Graphs.java does the following:
1) Read a data file and construct a graph object.
2) Using the Graph methods, print the number of vertices, the number of edges, and whether or not the graph is directed and/or weighted.
3) a) Invoke getEdge(0,3) and print the edge data if it exists. b) Invoke getEdge(1,0) and print the edge data if it exists
4) Perform a breadth-first traversal on the graph starting at vertex 0.
5) Perform a depth-first traversal on the graphy starting at vertext 0.


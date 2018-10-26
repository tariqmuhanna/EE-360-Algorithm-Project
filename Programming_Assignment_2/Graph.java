package Programming_Assignment_2;
import java.util.ArrayList;
import java.util.*;

public class Graph {
	private ArrayList<Node> vertices; //this is a list of all vertices, populated by Driver class.
	private Heap minHeap; 	//this is the heap to use for Dijkstra
    private int size;
    private ArrayList<Boolean> check;

	public Graph(int numVertices) {
		minHeap = new Heap();
		vertices = new ArrayList<Node>();
		size = numVertices;
		for (int i=0; i<size-1; i++) {
		    check.add(false);
        }
    // feel free to add anything else you may want	
	}

    /**
     * findShortestPathLength
     * @param root
     * @param x
     * @return
     * Returns the distance of the shortest path from root to x
     */
    public int findShortestPathLength(Node root, Node x) {
        int INFINITY = Integer.MAX_VALUE;
        while(size>0) {
            Node extracted_min = minHeap.extractMin();
            check.set(extracted_min.getNodeName(), true);

            for (int i=0; i < extracted_min.getNeighbors().size(); i++) {
                Node adjacent_node = extracted_min.getNeighbors().get(i);
                int weight = extracted_min.getWeights().get(i);

                if (!check.get(adjacent_node.getNodeName())) {
                    int newKey =  [extractedVertex].distance + edge.weight ;
                    int currentKey = minHeap[destination].distance;
                }
            }
        }

	    return 0;
    }

  // findAShortestPath
  //
  // Returns a list of nodes represent one of the shortest paths
  // from root to x
  public ArrayList<Node> findAShortestPath(Node root, Node x){
	  
	  
	  return null;
  }

  // eachShortestPathLength
  //
  // Returns an ArrayList of Nodes, where minDistance of each node is the
  // length of the shortest path from it to the root. This ArrayList
  // should contain every Node in the graph. Note that 
  // root.getMinDistance() = 0
  public ArrayList<Node> findEveryShortestPathLength(Node root) {
	  return null;
  }
  
  //returns edges and weights in a string.
  public String toString() {
    String o = "";
    for(Node v: vertices) {
      boolean first = true;
      o += "Node ";
      o += v.getNodeName();
      o += " has neighbors: ";
      ArrayList<Node> ngbr = v.getNeighbors();
      for(Node n : ngbr) {
        o += first ? n.getNodeName() : ", " + n.getNodeName();
        first = false;
      }
      first = true;
      o += " with weights ";
      ArrayList<Integer> wght= v.getWeights();
      for (Integer i : wght) {
        o += first ? i : ", " + i;
        first = false;
      }		
      o += System.getProperty("line.separator");
    
    }

    return o;
  }
  
///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

  public Heap getHeap() {
    return minHeap;
  }
    
  public ArrayList<Node> getAllNodes(){
    return vertices;
  }
     
	//used by Driver class to populate each Node with correct neighbors and corresponding weights
	public void setEdge(Node curr, Node neighbor, Integer weight) {
		curr.setNeighborAndWeight(neighbor, weight);
	}
    //This is used by Driver.java and sets vertices to reference an ArrayList of all nodes.
  public void setAllNodesArray(ArrayList<Node> x) {
    vertices = x;	
  }    
}

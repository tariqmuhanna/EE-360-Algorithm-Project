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
        check = new ArrayList<>();
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
        for (int i=0; i<size; i++) {
            check.add(false);
        }
        while(size>0) {
            Node extracted_min = minHeap.extractMin();
            check.set(extracted_min.getNodeName(), true);
            size--;

            if (extracted_min.getNodeName() == x.getNodeName())
                if (extracted_min.getMinDistance() == Integer.MAX_VALUE)
                    return -1;
                else
                    return 0;

            for (int i=0; i < extracted_min.getNeighbors().size(); i++) {
                Node adjacent_node = extracted_min.getNeighbors().get(i);
                int weight = extracted_min.getWeights().get(i);

                // Solution
                if (adjacent_node.getNodeName() == x.getNodeName())
                    return extracted_min.getMinDistance()+weight;

                if (!check.get(adjacent_node.getNodeName())) {
                    String s_index = String.valueOf(adjacent_node.getNodeName());
                    int index = minHeap.toString().indexOf(s_index)/2;

                    int newKey = extracted_min.getMinDistance() + weight ;
                    int currentKey = minHeap.toArrayList().get(index).getMinDistance();//adjacent_node.getMinDistance();

                    // If distance of node u > node n, update u.d
                    if (currentKey > newKey) {
                        adjacent_node.setMinDistance(newKey);
//                        int index_node = minHeap.index.get(adjacent_node.getNodeName());
//                        minHeap.toArrayList().get(index_node).setMinDistance(newKey);
                        s_index = String.valueOf(adjacent_node.getNodeName());
                        index = minHeap.toString().indexOf(s_index)/2;
                        minHeap.toArrayList().get(index).setMinDistance(newKey);
                        minHeap.minHeapify(index);
                    }
                }
            }
        }

	    return -1;
    }

  // findAShortestPath
  //
  // Returns a list of nodes represent one of the shortest paths
  // from root to x
    public ArrayList<Node> findAShortestPath(Node root, Node x){
        int[] parent = new int[5];
        parent[0] = -1;
        ArrayList<Node> node_dump = new ArrayList<>();
        for (int i=0; i < minHeap.toArrayList().size(); i++)
            node_dump.add(minHeap.toArrayList().get(i));


        for (int i=0; i<size; i++) {
            check.add(false);
        }
        while(size>0) {
            Node extracted_min = minHeap.extractMin();
//            node_dump.add(extracted_min);
            check.set(extracted_min.getNodeName(), true);
            size--;

            if (extracted_min.getNodeName() == x.getNodeName())
                if (extracted_min.getMinDistance() == Integer.MAX_VALUE)
                    return null;
                else
                    return unwindParent(parent, extracted_min.getNodeName(), node_dump);

            for (int i=0; i < extracted_min.getNeighbors().size(); i++) {
                Node adjacent_node = extracted_min.getNeighbors().get(i);
                int weight = extracted_min.getWeights().get(i);

                if (!check.get(adjacent_node.getNodeName())) {
                    String s_index = String.valueOf(adjacent_node.getNodeName());
                    int index = minHeap.toString().indexOf(s_index)/2;

                    int newKey = extracted_min.getMinDistance() + weight ;
                    int currentKey = minHeap.toArrayList().get(index).getMinDistance();//adjacent_node.getMinDistance();

                    // If distance of node u > node n, update u.d
                    if (currentKey > newKey) {
                        parent[adjacent_node.getNodeName()] = extracted_min.getNodeName();
                        adjacent_node.setMinDistance(newKey);

                        s_index = String.valueOf(adjacent_node.getNodeName());
                        index = minHeap.toString().indexOf(s_index)/2;
                        minHeap.toArrayList().get(index).setMinDistance(newKey);
                        minHeap.minHeapify(index);
                    }
                }
            }
        }

  	  return null;
  }

  private ArrayList<Node> unwindParent(int[] arr, int start, ArrayList<Node> node) {
      ArrayList<Node> ans = new ArrayList<>();
      if (start == -1)
          return null;

      int i=0;
      while (i < node.size()) {
          ans.add(0, node.get(start));
          start = arr[start];
          i++;
          if (start==-1)
              break;
      }
      return ans;
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

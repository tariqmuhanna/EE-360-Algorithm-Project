package Programming_Assignment_2;
import java.util.ArrayList;
import java.util.Collections;


public class Heap {

	private ArrayList<Node> minHeap; // do not remove
	public ArrayList<Integer> ind;

	public Heap() {
		minHeap = new ArrayList<Node>(); // do not remove
		ind = new ArrayList<>();
	}

	public ArrayList<Node> getMinHeapList() {
		return minHeap;
	}

	// buildHeap
	//
	// Given an ArrayList of Nodes, build a minimum heap keyed on each
	// Node's minDistance
	//
	// Time Complexity Requirement: theta(n)
	public void buildHeap(ArrayList<Node> nodes) {
//		Collections.copy(minHeap,nodes);
		for (int i=0; i<nodes.size(); i++) {
			minHeap.add(nodes.get(i));
			ind.add(i);
		}

		for (int i = (nodes.size()-1)/2; i>=0; i--)
			heapifyDown(i);
//		for (int i=0; i<nodes.size(); i++) {
//			minHeap.add(nodes.get(i));
//			ind.add(i);
//			minHeapify(i);
////			heapifyDown(i);
//		}
	}

	public void minHeapify(int i) {
		int parent_idx = i/2;
		int curr_idx = i;
		while (curr_idx > 0 && minHeap.get(parent_idx).getMinDistance()
				> minHeap.get(curr_idx).getMinDistance()) {

			swap(curr_idx, parent_idx);
			curr_idx=parent_idx;
			parent_idx/=2;

		}
	}

	private void swap(int i, int parent) {
		Node temp = minHeap.get(parent);
		int i_temp = parent;
		ind.set(minHeap.get(parent).getNodeName(),i);
		ind.set(minHeap.get(i).getNodeName(),i_temp);
		minHeap.set(parent,minHeap.get(i));
		minHeap.set(i,temp);
	}

	private int right(int i) { return 2 * i + 2; }

	private int left(int i) { return 2 * i + 1; }

	// insertNode
	//
	// Insert a Node into the heap
	//
	// Time Complexity Requirement: theta(log(n))
	public void insertNode(Node in) {

	}

	// findMin
	//
	// Returns the minimum element of the heap
	//
	// Time Complexity Requirement: theta(1)
	public Node findMin() {
		if (minHeap.size()>0)
			return minHeap.get(0);
		return null;
	}

	/**
	 * extractMin
	 * Removes and returns the minimum element of the heap
	 * Time Complexity Requirement: theta(log(n))
	 * @return
	 */
	public Node extractMin() {
		if (minHeap.size()==1)
			return minHeap.remove(minHeap.size()-1);

		Node min = minHeap.get(0);
		Node last = minHeap.remove(minHeap.size()-1);
		minHeap.set(0,last);
		ind.set(last.getNodeName(),0);
		ind.set(min.getNodeName(),-1);
		heapifyDown(0);
		return min;
	}

	private void heapifyDown(int i) {
		int left = left(i);
		int right = right(i);
		int smallest = -1;

		// find the smallest key between current node and its children.
		if (left <= minHeap.size() - 1 &&
				minHeap.get(left).getMinDistance() < minHeap.get(i).getMinDistance()) {
			smallest = left;
		}
		else { smallest = i; }

		if (right <= minHeap.size() - 1 &&
				minHeap.get(right).getMinDistance() < minHeap.get(i).getMinDistance()) {
			smallest = right;
		}

		// if the smallest key is not the current key then bubble-down it.
		if (smallest != i) {
			swap(i, smallest);
			heapifyDown(smallest);
		}
	}

	public String toString() {
		String output = "";
		for(int i = 0; i < minHeap.size(); i++) {
			output+= minHeap.get(i).getNodeName() + " ";
		}
		return output;
	}

///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

	public ArrayList<Node> toArrayList() {
		return minHeap;
	}
}

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Map.Entry;

public class UCS extends Algorithm {
    // for implementing the priority queue: automaticallly sorts the queue by using .compareTo
	class queueNode implements Comparable<queueNode>{
		int element;
		float dist;
		String path;

		queueNode(int element, float dist){
			this.element = element;
			this.dist = dist;
			this.path = "";
		}

		@Override
		public int compareTo(queueNode that) {
			return Float.compare(this.dist, that.dist);
		};

		@Override
		public boolean equals(Object o) {
			if (o == this) return true;
			if (!(o instanceof queueNode)) return false;
			queueNode q = (queueNode) o;
			return Integer.compare(this.element, q.element) == 0;
		}
	}

	public void run(int startNode, int endNode)  {
		queueNode curNode = new queueNode(startNode, 0);
		queueNode childNode;
        PriorityQueue<queueNode> priorityQueue = new PriorityQueue<queueNode>();
		Set<Integer> explored = new HashSet<Integer>();

		// add startNode to the queue
		curNode.path = Integer.toString(startNode);
		priorityQueue.add(curNode);

		// continue until all nodes have been expanded
		while(priorityQueue.size() != 0){

			// set current node to top of the priority queue
			curNode = priorityQueue.poll();

			// node chosen to expand is goal state, search is complete
			if (curNode.element == endNode) {
				System.out.println("\nShortest Path: " + curNode.path);
				System.out.println("Shortest Distance: " + curNode.dist + "\n"); 
				return;
			}

			// current node has not previously been expanded
			if (!explored.contains(curNode.element)){

				// mark current node as expanded
				explored.add(curNode.element);
            
				// Examine each neighbour of the expanded node
				for (Entry<Integer,adjNode> mapNode: adjMatrix.get(curNode.element).entrySet()){
					childNode = new queueNode(mapNode.getKey(), mapNode.getValue().dist + curNode.dist);
					childNode.path = curNode.path + "->" + childNode.element;
					
					if (!priorityQueue.contains(childNode)) { // since queueNode.equals only compares .element, will match regardless of dist
						priorityQueue.add(childNode);
					} else { // element has previously been queued
						for(queueNode node: priorityQueue){
							if (node.equals(childNode) && node.dist > childNode.dist){ // if path in queue is longer than this new path, replace entry
								priorityQueue.remove(childNode); // since queueNode.equals only compares .element, will remove old node with same element
								priorityQueue.add(childNode);
								break;
							}
						}
					}
				}
			}
			
		}
		System.out.println("No path found");
	}
}





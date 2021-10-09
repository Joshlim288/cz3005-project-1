import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Map.Entry;

public class AStar extends Algorithm{
    // for implementing the priority queue: automaticallly sorts the queue by using .compareTo
	class queueNode implements Comparable<queueNode>{
		int element;
		float totalDist;
		int totalCost;
        double heuristic;
		String path;

		queueNode(int element, float dist, int cost, double heuristic){
			this.element = element;
			this.totalDist = dist;
			this.totalCost = cost;
            this.heuristic = heuristic;
			this.path = "";
		}

		@Override
		public int compareTo(queueNode that) {
			return Double.compare(this.totalDist+this.heuristic, that.totalDist+that.heuristic);
		};

		@Override
		public boolean equals(Object o) {
			if (o == this) return true;
			if (!(o instanceof queueNode)) return false;
			queueNode q = (queueNode) o;
			return Integer.compare(this.element, q.element) == 0;
		}
	}
    private double getHeuristic(int startNode, int endNode){
        int h = Math.abs(coordMap.get(endNode).get(1) - coordMap.get(startNode).get(1)); // | y2 - y1 |
        int w = Math.abs(coordMap.get(endNode).get(0) - coordMap.get(startNode).get(0)); // | x2 - x1 |
        double dist = Math.sqrt(Math.pow((double)h, 2) + Math.pow((double)w, 2)); // sqrt(a^2 + b^2)
        return dist;
    }
	public void run(int startNode, int endNode)  {
		queueNode curNode = new queueNode(startNode, 0, 0, getHeuristic(startNode, endNode));
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
				System.out.println("\nShortest path: " + curNode.path);
				System.out.println("Shortest distance: " + curNode.totalDist); 
                System.out.println("Total energy cost: " + curNode.totalCost  + "\n");
				return;
			}

			// current node has not previously been expanded
			if (!explored.contains(curNode.element)){

				// mark current node as expanded
				explored.add(curNode.element);
            
				// Examine each neighbour of the expanded node
				for (Entry<Integer,adjNode> mapNode: adjList.get(curNode.element).entrySet()){
					if (mapNode.getValue().cost + curNode.totalCost > budget) { // budget exceeded
						continue;
					}
                    if (mapNode.getValue().dist == -1 || mapNode.getValue().cost == -1) {
                        System.out.println("-1");
                    }
					childNode = new queueNode(
                                        mapNode.getKey(), 
                                        mapNode.getValue().dist + curNode.totalDist, 
										mapNode.getValue().cost + curNode.totalCost, 
                                        getHeuristic(mapNode.getKey(), endNode));

					childNode.path = curNode.path + "->" + childNode.element;
					
					if (!priorityQueue.contains(childNode)) { // since queueNode.equals only compares .element, will match regardless of dist
						priorityQueue.add(childNode);
					} else { // element has previously been queued
						for(queueNode node: priorityQueue){
							if (node.equals(childNode) && node.totalDist+node.heuristic > childNode.totalDist+childNode.heuristic){ // if path in queue is longer than this new path, replace entry
								priorityQueue.remove(node); // will remove old node with same element
								priorityQueue.add(childNode); // add the new node into the queue
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

import java.util.ArrayList;

public class BFS extends Algorithm{

    public void run(int startNode, int endNode) {
        // Mark all the vertices as not visited(By default set as false)
        boolean visited[] = new boolean[nodes.size()];
        int prev[] = new int[nodes.size()];
        int dist[] = new int[nodes.size()];


        // Create a queue for BFS
        ArrayList<Node> queue = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            visited[i] = false;
            prev[i] = -1;
            dist[i] = Integer.MAX_VALUE;
        }

        // Mark the current node as visited and enqueue it
        visited[startNode] = true ;
        dist[startNode] = 0;
        queue.add(nodes.get(startNode));

        Node curNode;
        while (queue.size() != 0)
        {
            // Expand the next vertex on queue
            curNode = queue.remove(0);

            // Examine each neighbour of the expanded node
            for (int adjNode: curNode.neighbours.keySet()){ //adjMatrix.get(curNode).keySet()
                if (!visited[adjNode])
                {
                    visited[adjNode] = true;
                    dist[adjNode] = dist[curNode.nodeID] + 1;
                    prev[adjNode] = curNode.nodeID;
                    queue.add(nodes.get(adjNode));

                    if (adjNode == endNode){
                        String shortestPath = Integer.toString(endNode);
                        curNode = nodes.get(endNode);
                        while(prev[curNode.nodeID] != -1){
                            curNode = nodes.get(prev[curNode.nodeID]);
                            shortestPath = Integer.toString(curNode.nodeID) + "->" + shortestPath;
                        }
                        System.out.println("\nResults:");
                        System.out.println("Shortest path: " + shortestPath);
                        System.out.println("Shortest distance: " + dist[endNode] + "\n");
                        return;
                    }
                }
            }
        }

        System.out.println("No route found");
    }
}

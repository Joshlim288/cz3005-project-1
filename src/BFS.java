import java.util.ArrayList;

public class BFS extends Algorithm{
    
    public void run(int startNode, int endNode) {
        // Mark all the vertices as not visited(By default set as false)
        boolean visited[] = new boolean[coordMap.size()];
        int prev[] = new int[coordMap.size()];
        int dist[] = new int[coordMap.size()];
        
        
        // Create a queue for BFS
        ArrayList<Integer> queue = new ArrayList<Integer>();
        
        for (int i = 0; i < coordMap.size(); i++) {
            visited[i] = false;
            prev[i] = -1;
            dist[i] = Integer.MAX_VALUE;
        }

        // Mark the current node as visited and enqueue it
        visited[startNode] = true ;
        dist[startNode] = 0;
        queue.add(startNode);
        
        int curNode;
        while (queue.size() != 0)
        {
            // Expand the next vertex on queue
            curNode = queue.remove(0);
            
            // Examine each neighbour of the expanded node
            for (int adjNode: adjMatrix.get(curNode).keySet()){
                if (!visited[adjNode])
                {
                    visited[adjNode] = true;
                    dist[adjNode] = dist[curNode] + 1;
                    prev[adjNode] = curNode;
                    queue.add(adjNode);

                    if (adjNode == endNode){
                        String shortestPath = Integer.toString(endNode);
                        curNode = endNode;
                        while(prev[curNode] != -1){
                            curNode = prev[curNode];
                            shortestPath = Integer.toString(curNode) + "->" + shortestPath;
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

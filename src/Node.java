import java.util.HashMap;
import java.util.Map;

public class Node {
    public static class AdjNode {
        public float dist = -1;
        public int cost = -1;
    }
    public int nodeID;
//    public int xCoord;
//    public int yCoord;
    public int[] coords;
    public Map<Integer, AdjNode> neighbours; // int[] = [dist, cost]

    public Node(int nodeID, int[] coords) {
        this.nodeID = nodeID;
        this.coords = coords;
        this.neighbours = new HashMap<Integer, AdjNode>();
    }
}

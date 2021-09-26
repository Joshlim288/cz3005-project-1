import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Algorithm {
    public static HashMap<Integer, Node> nodes;
//    public static Map<Integer, int[]> coordMap;
//    public static Map<Integer, Map<Integer, adjNode>> adjMatrix;

    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public static void initializeData() {
        nodes = new HashMap<Integer, Node>();

        String jsonString;

        // Coord.json
        try {
            jsonString = readFileAsString("src/data/Coord.json");
            Map<Integer, int[]> coordMap = new Gson().fromJson(
                jsonString, new TypeToken<HashMap<Integer, int[]>>() {}.getType()
            );
            for (Map.Entry<Integer, int[]> entry : coordMap.entrySet()) {
                nodes.put(entry.getKey(), new Node(entry.getKey(), entry.getValue()));
            }
        } catch(Exception e) {
            System.out.println("Loading of Coord.json failed");
        }

        // G.json
        try {
            jsonString = readFileAsString("src/data/G.json");
            Map<Integer, ArrayList<Integer>> gMap = new Gson().fromJson(
                jsonString, new TypeToken<HashMap<Integer, ArrayList<Integer>>>() {}.getType()
            );
            for (Map.Entry<Integer, ArrayList<Integer>> entry : gMap.entrySet()) {
                // for each node, set its neighbours
                Node node = nodes.get(entry.getKey());
                for (int neighbour : entry.getValue()) {
                    node.neighbours.put(neighbour, new Node.AdjNode());
                }
            }
//            Map<Integer, int[]> innerMap;
//            adjMatrix = new HashMap<Integer,Map<Integer, adjNode>>();
//            for (Map.Entry<Integer,ArrayList<Integer>> entry : gMap.entrySet()){
//                innerMap = new HashMap<Integer, adjNode>();
//                for (Integer dest: entry.getValue()){
//                    innerMap.put(dest, new adjNode());
//                }
//                adjMatrix.put(entry.getKey(), innerMap);
//            }
        } catch(Exception e) {
            System.out.println("Loading of G.json failed");
        }

        // Cost.json
        try {
            jsonString = readFileAsString("src/data/Cost.json");
            Map<String, Integer> tempMap = new Gson().fromJson(
                jsonString, new TypeToken<HashMap<String, Integer>>() {}.getType()
            );
            String[] adj;
            for (Map.Entry<String, Integer> mapping :tempMap.entrySet()) {
                // for each adj[0], enter distance data for its neighbour adj[1]
                adj = mapping.getKey().split(",");
                Node node = nodes.get(Integer.parseInt(adj[0]));
                node.neighbours.get(Integer.parseInt(adj[1])).dist = mapping.getValue();
            }
        } catch(Exception e) {
            System.out.println("Loading of Cost.json failed");
        }

        // Dist.json
        try {
            jsonString = readFileAsString("src/data/Dist.json");
            Map<String, Float> tempMap = new Gson().fromJson(
                    jsonString, new TypeToken<HashMap<String, Float>>() {}.getType()
            );

            String[] adj;
            for (Map.Entry<String, Float> mapping :tempMap.entrySet()) {
                // for each adj[0], enter distance data for its neighbour adj[1]
                adj = mapping.getKey().split(",");
                Node node = nodes.get(Integer.parseInt(adj[0]));
                node.neighbours.get(Integer.parseInt(adj[1])).dist = mapping.getValue();
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Loading of data completed");
    }

    public abstract void run(int startNode, int endNode);
}

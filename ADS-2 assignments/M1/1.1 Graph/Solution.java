import java.util.Scanner;
import java.util.Arrays;
interface Graph {
    public int V();
    public int E();
    public void addEdge(int v, int w);
    public Iterable<Integer> adj(int v);
    public boolean hasEdge(int v, int w);
}
class AdjacencyList implements Graph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj; 
    public AdjacencyList(int V, int E) {
        this.V = V;
        this.E = E;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }
    public int V() {
        return V;
    }
    public int E() {
        return E;
    }
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
    public boolean hasEdge(int v, int w) {
        for (int i = 0; i < adj[v].size(); i++) {
            if (i == w) {
                return true;
            }
        }
        return false;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
// class AdjacencyMatrix implements Graph {
//     private final int V;
//     private int E;
//     private int[][] matrix; 
//     public AdjacencyMatrix(int V) {
//         this.V = V;
//         this.E = 0;
//         adj = (Bag<Integer>[]) new Bag[V];
//         for (int v = 0; v < V; v++) {
//             adj[v] = new Bag<Integer>();
//         }
//     }
//     public int V() {
//         return V;
//     }
//     public int E() {
//         return E;
//     } 

/**
 * class for Solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //empty constructor.
    }
    /**
     * Client program.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String type = scan.nextLine();
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        if (edges == 0) {
            System.out.println("No edges");
        }
        String[] names = scan.nextLine().split(",");
        // System.out.println(Arrays.toString(names));
        while (edges > 0) {
            String[] connections = scan.nextLine().split(" ");
            switch (type) {
            case "List":
             AdjacencyList al = new AdjacencyList(vertices, edges);
             System.out.println(al);
            // case "Matrix":
            //  AdjacencyMatrix am = new AdjacencyMatrix();
            // }
                // System.out.println(Arrays.toString(connections));
            }
        }
    }
}

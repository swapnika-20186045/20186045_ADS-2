import java.util.Scanner;
import java.util.Arrays;
interface Graph {
    public int V();
    public int E();
    public void addEdge(int v, int w);
    public Iterable<Integer> adj(int v);
    public boolean hasEdge(int v, int w);
}
class GraphADT implements Graph {
    private int V;
    private int E;
    private Bag<Integer>[] adj;
    GraphADT() {
        //empty constructor.
    }
    public GraphADT(int V) {
        this.V = V;
        this.E = 0;
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
    public void listdisplay(int v, int w, String[] tokens) throws Exception {
        if (w <= 1 && v <= 1) {
            System.out.println(V() + " vertices" + ", " + E() + " edges");
            throw new Exception("No edges");
        } else {
            System.out.println(V() + " vertices" + ", " + E() + " edges");
            for (int i = 0; i < tokens.length; i++) {
            String str = "";
            str = tokens[i] + ": ";
            for (int k : adj(i)) {
                str = str + tokens[k] + " ";
            }
            System.out.println(str);
            }
        }
    }
     public void matrixdisplay(int v, int w) throws Exception {
        if (w <= 1 && v <= 1) {
            System.out.println(V() + " vertices" + ", " + E() + " edges");
            throw new Exception("No edges");
        } else {
            System.out.println(V() + " vertices" + ", " + E() + " edges");
            int[][] disp = new int[v][v];
            for (int i = 0; i  < v; i++) {
                for (int j = 0; j < v; j++) {
                    if (hasEdge(i, j)) {
                        disp[i][j] = 1;
                    }
                }
            }

            for (int i = 0; i < v; i++) {
                for (int j = 0; j < v; j++) {
                    System.out.print(disp[i][j] + " ");
                }
                System.out.println();
            }
            
        }
    }
}
// class AdjacencyMatrix implements Graph {
//     private final int V;
//     private int E;
//     private boolean[][] matrix;
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
        GraphADT gobj = new GraphADT();
        String input = scan.nextLine();
        int v = Integer.parseInt(scan.nextLine());
        int e = Integer.parseInt(scan.nextLine());
        String keynames = scan.nextLine();
        String[] tokens = keynames.split(",");
        gobj = new GraphADT(v);
        while(scan.hasNext()) {
            String connect = scan.nextLine();
            String[] connections = connect.split(" ");
            gobj.addEdge(Integer.parseInt(connections[0]),
                Integer.parseInt(connections[1]));
        }
        switch (input) {
            case "List":
            try {
                gobj.listdisplay(v, e, tokens);
            } catch (Exception p) {
                System.out.println(p.getMessage());
            }
            break;
            case "Matrix":
            try {
                gobj.matrixdisplay(v, e);
            } catch (Exception p) {
                System.out.println(p.getMessage());
            }
            break;
        }
    }
}

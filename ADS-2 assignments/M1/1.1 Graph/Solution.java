import java.util.Scanner;
import java.util.Arrays;
/**
 * Interface for graph.
 */
interface Graph {
    public int V();
    public int E();
    public void addEdge(int v, int w);
    public Iterable<Integer> adj(int v);
    public boolean hasEdge(int v, int w);
}
/**
 * Class for graph adt.
 */
class GraphADT implements Graph {
    /**
     * declaration of variable.
     */
    private int V;
    /**
     * declaration of variable.
     */
    private int E;
    /**
     * declaration of variable.
     */
    private Bag<Integer>[] adj;
    /**
     * Constructs the object.
     */
    GraphADT() {
        //empty constructor.
    }
    /**
     * Constructs the object.
     *
     * @param      V     { parameter_description }
     */
    public GraphADT(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }
    /**
     * no. of vertices.
     *
     * @return     { description_of_the_return_value }
     */
    public int V() {
        return V;
    }
    /**
     * no. of edges.
     *
     * @return     { description_of_the_return_value }
     */
    public int E() {
        return E;
    }
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(int v, int w) {
        if (v == w) {
            return;
        }
        if (!hasEdge(v, w)) {
            E++;
        }
        adj[v].add(w);
        adj[w].add(v);
    }
    /**
     * adjacent vertices.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(int v, int w) {
        for (int i = 0; i < adj[v].size(); i++) {
            if (i == w) {
                return true;
            }
        }
        return false;
    }
    /**
     * print list adjacency.
     *
     * @param      v          { parameter_description }
     * @param      w          { parameter_description }
     * @param      tokens     The tokens
     *
     * @throws     Exception  { exception_description }
     */
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
    /**
     * print adjacency matrix.
     *
     * @param      v          { parameter_description }
     * @param      w          { parameter_description }
     *
     * @throws     Exception  { exception_description }
     */
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
        while (scan.hasNext()) {
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

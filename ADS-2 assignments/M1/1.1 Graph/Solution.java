import java.util.Scanner;
/**
 * Class for graph matrix.
 */
class AdjacencyMatrix {
    /**
     * array declaration.
     */
    private String[] tokens;
    /**
     * 2d array declaration.
     */
    private int[][] matrix;
    /**
     * variable declaration.
     */
    private int v;
    /**
     * variable declaration.
     */
    private int e;
    /**
     * Constructs the object.
     */
    AdjacencyMatrix() {
        e = 0;
    }
    /**
     * Constructs the object.
     *
     * @param      scan  The scan
     */
    AdjacencyMatrix(final Scanner scan) {
        this.v = Integer.parseInt(scan.nextLine());
        matrix = new int[v][v];
        int edge = Integer.parseInt(scan.nextLine());
        tokens = scan.nextLine().split(",");
        for (int i = 0; i < edge; i++) {
            String[] inputs = scan.nextLine().split(" ");
            addEdge(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
    }
    /**
     * Adds an edge.
     *
     * @param      v1    the int.
     * @param      w1    the int.
     */
    public void addEdge(final int v1, final int w1) {
        if (v1 != w1) {
            if (!hasEdge(v1, w1)) {
                matrix[v1][w1] = 1;
                matrix[w1][v1] = 1;
                e++;
            }
        }
    }
    /**
     * Determines if it has edge.
     *
     * @param      v1    the int.
     * @param      w1    the int.
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v1, final int w1) {
        if (matrix[v1][w1] == 1) {
            return true;
        }
        return false;
    }
    /**
     * display method.
     */
    public void printMatrix() {
        String str = "";
        str += v + " vertices, " + e + " edges" + "\n";
        if (e > 0) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    str += matrix[i][j] + " ";
                }
                str += "\n";
            }
            System.out.println(str);
        } else {
            str += "No edges";
            System.out.println(str);
        }
    }
}
/**
 * List of graphs.
 */
class AdjacencyList {
    /**
     * variable declaration.
     */
    private int v;
    /**
     * variable declaration.
     */
    private int e;
    /**
     * array declaration.
     */
    private Bag<Integer>[] adj;
    /**
     * array declaration.
     */
    private String[] tokens;
    /**
     * Constructs the object.
     */
    AdjacencyList() {
    }
    /**
     * Constructs the object.
     *
     * @param      scan  The scan
     */
    AdjacencyList(final Scanner scan) {
        this.v = Integer.parseInt(scan.nextLine());
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Integer>();
        }
        int edges = Integer.parseInt(scan.nextLine());
        tokens = scan.nextLine().split(",");
        for (int i = 0; i < edges; i++) {
            String[] inputs = scan.nextLine().split(" ");
            addEdge(Integer.parseInt(inputs[0]),
                    Integer.parseInt(inputs[1]));
        }
    }
    /**
     * method for vertices.
     *
     * @return  vertices.
     */
    public int v() {
        return v;
    }
    /**
     * method for edges.
     *
     * @return edges.
     */
    public int e() {
        return e;
    }
    /**
     * Adds an edge.
     *
     * @param      v1    the int.
     * @param      w1    the int.
     */
    public void addEdge(final int v1, final int w1) {
        if (v1 != w1) {
            adj[v1].add(w1);
            adj[w1].add(v1);
            e++;
        } else {
            return;
        }
    }
    /**
     * method for adjacent vertex.
     *
     * @param      v1    the int.
     *
     * @return adjacent vertex.
     */
    public Iterable<Integer> adj(final int v1) {
        return adj[v1];
    }
    /**
     * Determines if it has edge.
     *
     * @param      v1    the int.
     * @param      w1    the int.
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v1, final int w1) {
        return true;
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String printlist() {
        StringBuilder s = new StringBuilder();
        s.append(v + " vertices, " + e + " edges" + "\n");
        if (e > 0) {
            for (int i = 0; i < v; i++) {
                s.append(tokens[i] + ": ");
                for (int j : adj[i]) {
                    s.append(tokens[j] + " ");
                }
                s.append("\n");
            }
            return s.toString();
        } else {
            s.append("No edges");
            return s.toString();
        }
    }
}
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
    }
    /**
     * Client program.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String type = scan.nextLine();
        switch (type) {
        case "List":
            AdjacencyList al = new AdjacencyList(scan);
            System.out.println(al);
            break;
        case "Matrix":
            AdjacencyMatrix am = new AdjacencyMatrix(scan);
            am.printMatrix();
            break;
        default :
            break;
        }
    }
}

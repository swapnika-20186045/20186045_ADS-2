import java.util.Scanner;
/**
 * Class for graph matrix.
 */
class AdjacencyMatrix {
    /**
     *the tokens array is to store all the keys.
     */
    private String[] tokens;
    /**
     *matrix to store the realtion between two vertices.
     */
    private int[][] matrix;
    /**
     *the variable to store number of vertices.
     */
    private int vertices;
    /**
     *the variable to store edges of graph.
     */
    private int edges;
    /**
     *the constructor to initialize the class variables.
     */
    AdjacencyMatrix() {
        edges = 0;
    }
    /**
     *the override constructor to maintian the complete graph design.
     * @param      scan  The scan
     */
    AdjacencyMatrix(final Scanner scan) {
        vertices = Integer.parseInt(scan.nextLine());
        matrix = new int[vertices][vertices];
        int edge = Integer.parseInt(scan.nextLine());
        tokens = scan.nextLine().split(",");
        for (int i = 0; i < edge; i++) {
            String[] inputs = scan.nextLine().split(" ");
            addEdge(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
    }
    /**
     *the method is to add an edge between two vertices.
     * @param      vertexOne  The vertex one
     * @param      vertexTwo  The vertex two
     * because we use has next method.
     */
    public void addEdge(final int vertexOne, final int vertexTwo) {
        if (vertexOne != vertexTwo) {
            if (!hasEdge(vertexOne, vertexTwo)) {
                matrix[vertexOne][vertexTwo] = 1;
                matrix[vertexTwo][vertexOne] = 1;
                edges++;
            }
        }
    }
    /**
     *the method is check whether there is a connection between two given.
     *vertices the time complexity is O(1)
     * @param      vertexOne  The vertex one
     * @param      vertexTwo  The vertex two
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int vertexOne, final int vertexTwo) {
        if (matrix[vertexOne][vertexTwo] == 1) {
            return true;
        }
        return false;
    }
    /**
     *the method is to printMat the string format of graph the time complexity
     *will be O(N^2)
     *N is the vertices here.
     */
    public void printMat() {
        String str = "";
        str += vertices + " vertices, " + edges + " edges" + "\n";
        if (edges > 0) {
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
     *the variable to store number.
     *of vertices.
     */
    private int vertices;
    /**
    *the variable to store number.
    *of edges.
    */
    private int edges;
    /**
     *the array to store bag of arrays.
     */
    private Bag<Integer>[] adj;
    /**
     *the variable to store all keys.
     */
    private String[] tokens;
    /**
     *an empty constructor.
     */
    AdjacencyList() {
        //empty constructor.
    }
    /**
     *the constructor is to initialize the input given for the graph.
     *
     * @param      scan  The scan
     */
    AdjacencyList(final Scanner scan) {
        vertices = Integer.parseInt(scan.nextLine());
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Integer>();
        }
        int edge = Integer.parseInt(scan.nextLine());
        tokens = scan.nextLine().split(",");
        for (int i = 0; i < edge; i++) {
            String[] inputs = scan.nextLine().split(" ");
            addEdge(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
    }
    /**
     *returns the vertices of graph.
     *
     * @return vertices of graph
     */
    public int vertices() {
        return vertices;
    }
    /**
     *returns the edges of graph.
     *
     * @return edges of graph
     */
    public int edges() {
        return edges;
    }
    /**
     *the method is to add an edge between.
     *two vertices.
     * @param      vertexOne  The vertex one
     * @param      vertexTwo  The vertex two
     * the time complexity is O(E)
     * E denotes the number of edges we have in graph.
     * because we use has next method.
     */
    public void addEdge(final int vertexOne,
                        final int vertexTwo) {
        if (vertexOne == vertexTwo) {
            return;
        }
        if (!hasEdge(vertexOne, vertexTwo)) {
            edges++;
        }
        adj[vertexOne].add(vertexTwo);
        adj[vertexTwo].add(vertexOne);
    }
    /**
     *the method is to maintian a iteratable for.
     *bag
     *returns an interator.
     * @param      vertex  The vertex
     *
     * @return  iterator.
     */
    public Iterable<Integer> adj(final int vertex) {
        return adj[vertex];
    }
    /**
     *the method is check whether there is a.
     *connection between two given vertices.
     *the time complexity is O(E)
     *E is the number of edges in graph.
     * @param      vertexOne  The vertex one
     * @param      vertexTwo  The vertex two
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int vertexOne,
                           final int vertexTwo) {
        for (int each : adj(vertexOne))  {
            if (each == vertexTwo) {
                return true;
            }
        }
        return false;
    }
    /**
     *the method is to print the string format.
     *of graph.
     *the time complexity will be O(N^2)
     *@return string format.
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(vertices + " vertices, " + edges + " edges" + "\n");
        if (edges > 0) {
            for (int i = 0; i < vertices; i++) {
                str.append(tokens[i] + ": ");
                for (int j : adj[i]) {
                    str.append(tokens[j] + " ");
                }
                str.append("\n");
            }
            return str.toString();
        } else {
            str.append("No edges");
            return str.toString();
        }
    }
}
/**
 * Class for solution.
 */
public final class Solution {
    /**
     *empty constructor.
     */
    private Solution() {
        //empty constructor.
    }
    /**
     *the main to read the input.
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
            am.printMat();
            break;
        default:
            break;
        }
    }
}





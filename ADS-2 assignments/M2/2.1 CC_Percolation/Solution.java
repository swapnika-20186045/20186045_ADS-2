import java.util.Scanner;
/**
 * Class for graph.
 */
class Graph {
    /**
     * matrix declaration.
     */
    private int[][] grid;
    /**
     * declaration of variable.
     */
    private int vertices;
    /**
     * declaration of variable.
     */
    private int edges;
    /**
     * Constructs the object.
     *
     * @param      vertices  The vertices
     */
    Graph(final int vertices) {
        grid = new int[vertices][vertices];
    }
    public int vertices() {
        return vertices;
    }
    public void addEdge(final int vertexOne, final int vertexTwo) {
        if (vertexOne != vertexTwo) {
            if (!hasEdge(vertexOne, vertexTwo)) {
                grid[vertexOne][vertexTwo] = 1;
                // grid[vertexTwo][vertexOne] = 1;
                edges++;
            }
        }
    }
    public boolean hasEdge(final int vertexOne, final int vertexTwo) {
        if (grid[vertexOne][vertexTwo] == 1) {
            return true;
        }
        return false;
    }
    // public void connected(final int v1, final int w1) {
    //     grid[v1][w1] = 1;
    // }
    public int[] adj(final int v) {
        return grid[v];
    }
}
class ConnectedComponents {
    private boolean[] marked;
    private int[] id;
    private int count;
    ConnectedComponents(Graph g, int s) {
        marked = new boolean[g.vertices()];
        id  = new int[g.vertices()];
        for (int i = 0; i < g.vertices(); i++) {
            marked[i] = false;
            if (!marked[i]) {
                dfs(g, i);
                count++;
            }
        }
    }
    public int count() {
        return count;
    }
    public int id(int v) {
        return id[v];
    }
    private void dfs(Graph g, int v) {
        marked[v] = true;
        id[v] = count;
        for (int each : g.adj(v)) {
            if (!marked[each]) {
                dfs(g, each);
            }
        }
    }
    public boolean percolates() {
        if (count > 1) {
            return false;
        } else {
            return true;
        }
    }
}
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
        String num = scan.nextLine();
        Graph gobj = new Graph(Integer.parseInt(num));
        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] tokens = line.split(" ");
            gobj.addEdge(Integer.parseInt(tokens[0]) - 1, Integer.parseInt(tokens[1]) - 1);
        }
        ConnectedComponents obj = new ConnectedComponents(gobj, Integer.parseInt(num));
        System.out.println(obj.percolates());
    }
}

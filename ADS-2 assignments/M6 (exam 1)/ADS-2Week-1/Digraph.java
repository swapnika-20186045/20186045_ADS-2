/************************************************************
 *  Compilation:  javac Digraph.java
 *  Execution:    java Digraph filename.txt
 *  Dependencies: Bag.java In.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/42digraph/tinyDG.txt
 *                https://algs4.cs.princeton.edu/42digraph/mediumDG.txt
 *                https://algs4.cs.princeton.edu/42digraph/largeDG.txt
 *
 *  A graph, implemented using an array of lists.
 *  Parallel edges and self-loops are permitted.
 *
 *  % java Digraph tinyDG.txt
 *  13 vertices, 22 edges
 *  0: 5 1
 *  1:
 *  2: 0 3
 *  3: 5 2
 *  4: 3 2
 *  5: 4
 *  6: 9 4 8 0
 *  7: 6 9
 *  8: 6
 *  9: 11 10
 *  10: 12
 *  11: 4 12
 *  12: 9
 *
 ***************************************************************/

/**
 * Class for digraph.
 */
public class Digraph {
    /**
     * { var_description }.
     */
    private static final String NEWLINE = System.getProperty(
            "line.separator");
    /**
     * { var_description }.
     */
    private final int vertices;           // number of vertices in this digraph
    /**
     * { var_description }.
     */
    private int edges;                 // number of edges in this digraph
    /**
     * { var_description }.
     */
    private Bag<Integer>[] adj;    // adj[v] = adjacency list for vertex v
    /**
     * { var_description }.
     */
    private int[] indegree;        // indegree[v] = indegree of vertex v

    /**
     * Initializes an empty digraph with <em>V</em> vertices.
     *
     * @param  verticess the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public Digraph(final int verticess) {
        this.vertices = verticess;
        if (vertices < 0) {
            throw new IllegalArgumentException(
            "Number of vertices in a Digraph must be nonnegative");
        }
        this.edges = 0;
        indegree = new int[vertices];
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int v = 0; v < vertices; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     * Initializes a new digraph that is a deep copy of the specified digraph.
     *
     * @param  dg the digraph to copy
     */
    public Digraph(final Digraph dg) {
        this(dg.getVertices());
        this.edges = dg.getEdges();
        for (int v = 0; v < vertices; v++) {
            this.indegree[v] = dg.indegree(v);
        }
        for (int v = 0; v < dg.getVertices(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : dg.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }

    /**
     * Returns the number of vertices in this digraph.
     *
     * @return the number of vertices in this digraph
     */
    public int getVertices() {
        return vertices;
    }

    /**
     * Returns the number of edges in this digraph.
     *
     * @return the number of edges in this digraph
     */
    public int getEdges() {
        return edges;
    }
    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (vertices - 1));
        }
    }

    /**
     * Adds the directed edge v→w to this digraph.
     *
     * @param  v the tail vertex
     * @param  w the head vertex
     * @throws IllegalArgumentException unless both
     * {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(final int v, final int w) {
        //System.out.println("Called");
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        edges++;
    }

    /**
     * Returns the vertices adjacent from vertex {@code v} in this digraph.
     *
     * @param  v the vertex
     * @return the vertices adjacent from vertex {@code v} in this digraph,
     * as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(final int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(final int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * Returns the reverse of the digraph.
     *
     * @return the reverse of the digraph
     */
    public Digraph reverse() {
        Digraph reverse = new Digraph(vertices);
        for (int v = 0; v < vertices; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number
     * of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " vertices, " + edges + " edges " + NEWLINE);
        for (int v = 0; v < vertices; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}



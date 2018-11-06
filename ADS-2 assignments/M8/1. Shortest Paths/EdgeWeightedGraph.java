/**
 * Class for edge weighted graph.
 */
class EdgeWeightedGraph {
    /**
     * { var_description }.
     */
    private static final String NEWLINE = System.getProperty(
            "line.separator");
    /**
     * { var_description }.
     */
    private final int vs;
    /**
     * { var_description }.
     */
    private int es;
    /**
     * { var_description }.
     */
    private Bag<Edge>[] adj;
    /**
     * Initializes an empty edge-weighted graph with {@code V} vertices
     * and 0 edges.
     *
     * @param  vss the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    EdgeWeightedGraph(final int vss) {
        this.vs = vss;
        if (vs < 0) {
            throw new IllegalArgumentException(
                "Number of vertices must be nonnegative");
        }
        this.es = 0;
        adj = (Bag<Edge>[]) new Bag[vs];
        for (int v = 0; v < vs; v++) {
            adj[v] = new Bag<Edge>();
        }
    }

    /**
     * Initializes a new edge-weighted graph that is a deep copy of {@code G}.
     *
     * @param  ewg the edge-weighted graph to copy
     */
    EdgeWeightedGraph(final EdgeWeightedGraph ewg) {
        this(ewg.ver());
        this.es = ewg.edg();
        for (int v = 0; v < ewg.ver(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Edge> reverse = new Stack<Edge>();
            for (Edge e : ewg.adj[v]) {
                reverse.push(e);
            }
            for (Edge e : reverse) {
                adj[v].add(e);
            }
        }
    }


    /**
     * Returns the number of vertices in this edge-weighted graph.
     *
     * @return the number of vertices in this edge-weighted graph
     */
    public int ver() {
        return vs;
    }

    /**
     * Returns the number of edges in this edge-weighted graph.
     *
     * @return the number of edges in this edge-weighted graph
     */
    public int edg() {
        return es;
    }
    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        if (v < 0 || v >= vs) {
            throw new IllegalArgumentException("vertex " + v
                + " is not between 0 and " + (vs - 1));
        }
    }

    /**
     * Adds the undirected edge {@code e} to this edge-weighted graph.
     *
     * @param  e the edge
     * @throws IllegalArgumentException unless both endpoints are between
     * {@code 0} and {@code V-1}
     */
    public void addEdge(final Edge e) {
        int v = e.either();
        int w = e.other(v);
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        es++;
    }

    /**
     * Returns the edges incident on vertex {@code v}.
     *
     * @param  v the vertex
     * @return the edges incident on vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Edge> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int degree(final int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns all edges in this edge-weighted graph.
     * To iterate over the edges in this edge-weighted graph, use foreach
     * notation:
     * {@code for (Edge e : G.edges())}.
     *
     * @return all edges in this edge-weighted graph, as an iterable
     */
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < vs; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                } else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) {
                        list.add(e);
                    }
                    selfLoops++;
                }
            }
        }
        return list;
    }

    /**
     * Returns a string representation of the edge-weighted graph.
     * This method takes time proportional to <em>E</em> + <em>V</em>.
     *
     * @return the number of vertices <em>V</em>, followed by the number
     * of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists of edges
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vs + " " + es + NEWLINE);
        for (int v = 0; v < vs; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}

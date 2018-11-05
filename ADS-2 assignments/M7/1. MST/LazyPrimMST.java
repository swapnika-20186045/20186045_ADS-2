/**
 * Class for lazy primitive mst.
 */
class LazyPrimMST {
    /**
     * { var_description }.
     */
    private static final double FLOATING_POINT_EPSILON = 1E-12;
    /**
     * { var_description }.
     */
    private double weight;       // total weight of MST
    /**
     * { var_description }.
     */
    private Queue<Edge> mst;     // edges in the MST
    /**
     * { var_description }.
     */
    private boolean[] marked;    // marked[v] = true iff v on tree
    /**
     * { var_description }.
     */
    private MinPQ<Edge> pq;      // edges with one endpoint in tree

    /**
     * Compute a minimum spanning tree (or forest) of an edge-weighted graph.
     * @param ewg the edge-weighted graph
     */
    public LazyPrimMST(final EdgeWeightedGraph ewg) {
        mst = new Queue<Edge>();
        pq = new MinPQ<Edge>();
        marked = new boolean[ewg.ver()];
        for (int v = 0; v < ewg.ver(); v++)
        // run Prim from all vertices to
            if (!marked[v]) prim(ewg, v);
            // get a minimum spanning forest

        // check optimality conditions
        assert check(ewg);
    }

    // run Prim's algorithm
    private void prim(final EdgeWeightedGraph ewg, final int s) {
        scan(ewg, s);
        while (!pq.isEmpty()) {
        // better to stop when mst has V-1 edges
            Edge e = pq.delMin();
            // smallest edge on pq
            int v = e.either(), w = e.other(v);
            // two endpoints
            assert marked[v] || marked[w];
            if (marked[v] && marked[w]) continue;
            // lazy, both v and w already scanned
            mst.enqueue(e);
            // add e to MST
            weight += e.weight();
            if (!marked[v]) scan(ewg, v);
            // v becomes part of tree
            if (!marked[w]) scan(ewg, w);
            // w becomes part of tree
        }
    }

    /**
     * { function_description }.
     *
     * @param      ewg   The ewg
     * @param      v     { parameter_description }
     */
    private void scan(final EdgeWeightedGraph ewg, final int v) {
        assert !marked[v];
        marked[v] = true;
        for (Edge e : ewg.adj(v))
            if (!marked[e.other(v)]) pq.insert(e);
    }

    /**
     * Returns the edges in a minimum spanning tree (or forest).
     * @return the edges in a minimum spanning tree (or forest) as
     *    an iterable of edges
     */
    public Iterable<Edge> edges() {
        return mst;
    }

    /**
     * Returns the sum of the edge weights in a minimum spanning tree.
     * (or forest).
     * @return the sum of the edge weights in a minimum spanning tree
     * (or forest)
     */
    public double weight() {
        return weight;
    }

    /**
     * { function_description }.
     *
     * @param      ewg   The ewg
     *
     * @return     { description_of_the_return_value }
     */
    private boolean check(final EdgeWeightedGraph ewg) {

        // check weight
        double totalWeight = 0.0;
        for (Edge e : edges()) {
            totalWeight += e.weight();
        }
        if (Math.abs(totalWeight - weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf(
                "Weight of edges does not equal weight(): %f vs. %f\n",
                totalWeight, weight());
            return false;
        }

        // check that it is acyclic
        UF uf = new UF(ewg.ver());
        for (Edge e : edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest
        for (Edge e : ewg.edges()) {
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w)) {
                System.err.println("Not a spanning forest");
                return false;
            }
        }

        // check that it is a minimal spanning forest
        //(cut optimality conditions)
        for (Edge e : edges()) {

            // all edges in MST except e
            uf = new UF(ewg.ver());
            for (Edge f : mst) {
                int x = f.either(), y = f.other(x);
                if (f != e) uf.union(x, y);
            }

            // check that e is min weight edge in crossing cut
            for (Edge f : ewg.edges()) {
                int x = f.either(), y = f.other(x);
                if (!uf.connected(x, y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println("Edge " + f +
                                           " violates cut optimality conditions");
                        return false;
                    }
                }
            }

        }

        return true;
    }
}

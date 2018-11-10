/**
 * Class for edge.
 */
class Edge implements Comparable<Edge> {
    /**
     * { var_description }.
     */
    private final int v;
    /**
     * { var_description }.
     */
    private final int w;
    /**
     * { var_description }.
     */
    private final double weight;
    /**
     * Initializes an edge between vertices {@code v} and {@code w} of the.
     * given
     * {@code weight}.
     *
     * @param      vv       one vertex
     * @param      ww       the other vertex
     * @param      weightt  the weight of this edge
     * @throws     IllegalArgumentException
     * if either {@code v} or {@code w} is a
     *         negative integer
     * @throws     IllegalArgumentException
     * if {@code weight} is {@code NaN}
     */
    Edge(final int vv, final int ww, final double weightt) {
        if (vv < 0) {
            throw new IllegalArgumentException(
                "vertex index must be a nonnegative integer");
        }
        if (ww < 0) {
            throw new IllegalArgumentException(
                "vertex index must be a nonnegative integer");
        }
        if (Double.isNaN(weightt)) {
            throw new IllegalArgumentException("Weight is NaN");
        }
        this.v = vv;
        this.w = ww;
        this.weight = weightt;
    }

    /**
     * Returns the weight of this edge.
     *
     * @return     the weight of this edge
     */
    public double weight() {
        return weight;
    }

    /**
     * Returns either endpoint of this edge.
     *
     * @return     either endpoint of this edge
     */
    public int either() {
        return v;
    }
    /**
     * Returns the endpoint of this edge that is different from the given
     * vertex.
     *
     * @param      vertex  one endpoint of this edge
     *
     * @return     the other endpoint of this edge
     * @throws     IllegalArgumentException
     * if the vertex is not one of the endpoints
     * of this edge
     */
    public int other(final int vertex) {
        if (vertex == v) {
            return w;
        } else if (vertex == w) {
            return v;
        } else {
            throw new IllegalArgumentException("Illegal endpoint");
        }
    }
    /**
     * Compares two edges by weight. Note that {@code compareTo()} is not
     * consistent with {@code equals()}, which uses the reference equality
     * implementation inherited from {@code Object}.
     *
     * @param      that  the other edge
     *
     * @return     a negative integer, zero, or positive integer depending on
     *             whether the weight of this is less than, equal to, or greater
     *             than the argument edge
     */
    @Override
    public int compareTo(final Edge that) {
        return Double.compare(this.weight, that.weight);
    }

    /**
     * Returns a string representation of this edge.
     *
     * @return     a string representation of this edge
     */
    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }
}

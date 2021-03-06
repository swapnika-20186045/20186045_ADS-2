/**
 * Page Rank.
 */
import java.util.Scanner;
/**
 * import arrays.
 */
import java.util.Arrays;
/**
 * Class for page rank.
 */
class PageRank {
    /**
     * declaration of variable.
     */
    private Digraph graph;
    /**
     * declaration of variable.
     */
    private int value;
    /**
     * declaration of variable.
     */
    private Digraph reverse;
    /**
     * declaration of number.
     */
    private final int x = 1000;
    /**
     * Constructs the object.
     *
     * @param      graphh  The graphh
     */
    PageRank(final Digraph graphh) {
        this.graph = graphh;
        value = (graph.getVertices());
        reverse = graph.reverse();
    }
    // /**
    //  * Gets the pr.
    //  *
    //  * @param      vertex  The vertex
    //  *
    //  * @return     The pr.
    //  */
    // public double getPR(final int vertex) {
    //     double pr = 1.0 / value;
    //     return prCal(pr, vertex);
    // }
    /**
     * calculation of pr.
     *
     **/
    public void prCal() {
        double[] prarr = new double[value];
        double[] prarr1 = new double[value];

        for (int i = 0; i < value; i++) {
            prarr[i] = 1.0 / value;
        }
        //1000 * V - due to nested for-loop.(worst-case).
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < value; j++) {
                double rank = 0.0;
                for (int k : reverse.adj(j)) {
                    rank += prarr[k] / (double) (graph.outdegree(k));
                }
                prarr1[j] = rank;
            }
            if (Arrays.equals(prarr, prarr1)) {
                break;
            } else {
                prarr = prarr1.clone();
            }
        }
        for (int i = 0; i < value; i++) {
            System.out.print(i + " - " + prarr1[i] + "\n");
        }
    }
}
/**
 * Class for web search.
 */
class WebSearch {

}
/**
 * Class for solution.
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
        // read the first line of the input to get the number of vertices
        Scanner scan = new Scanner(System.in);
        int vertices = scan.nextInt(); scan.nextLine();
        // iterate count of vertices times
        // to read the adjacency list from std input
        // and build the graph

        Digraph d4PR = new Digraph(vertices);
        Digraph d = new Digraph(vertices);
        //V + E - to create no. of vertices and add no. of edges.
        for (int i = 0; i < vertices; i++) {
            String[] tokens = scan.nextLine().split(" ");
            if (tokens.length == 1) {
                for (int j = 0; j < vertices; j++) {
                    if (j != i) {
                        d4PR.addEdge(i, j);
                    }
                }
            } else {
                for (int j = 1; j < tokens.length; j++) {
                    d.addEdge(Integer.parseInt(tokens[0]),
                              Integer.parseInt(tokens[j]));
                    d4PR.addEdge(Integer.parseInt(tokens[0]),
                                 Integer.parseInt(tokens[j]));
                }
            }
        }
        System.out.println(d);
        // Create page rank object and pass the graph object to the
        // constructor
        // print the page rank object
        PageRank p = new PageRank(d4PR);
        p.prCal();
        // This part is only for the final test case

        // File path to the web content
        String file = "WebContent.txt";

        // instantiate web search object
        // and pass the page rank object and the file path to the constructor

        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky

    }
}





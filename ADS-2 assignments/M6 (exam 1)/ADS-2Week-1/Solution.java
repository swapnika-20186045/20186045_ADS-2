/**
 * Page Rank.
 */
import java.util.Scanner;
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
    private double value;
    /**
     * declaration of variable.
     */
    private Digraph reverse;
    /**
     * Constructs the object.
     *
     * @param      graphh  The graphh
     */
    PageRank(final Digraph graphh) {
        this.graph = graphh;
        value = (graph.V());
        reverse = graph.reverse();
        System.out.println(graph);
    }
    /**
     * Gets the pr.
     *
     * @param      vertex  The vertex
     *
     * @return     The pr.
     */
    public double getPR(final int vertex) {
        double pr = 1.0 / value;
        return PrCal(pr, vertex);
    }
    /**
     * calculation of pr.
     *
     * @param      pr      { parameter_description }
     * @param      vertex  The vertex
     *
     * @return     { description_of_the_return_value }
     */
    public double PrCal(final double pr, final int vertex) {
        double[] prarr = new double[graph.V()];
        for (int i = 0; i < prarr.length; i++) {
            prarr[i] = pr;
        }
        double[] prarr1 = new double[graph.V()];
        for (int i = 0; i < prarr.length; i++) {
            prarr1[i] = prarr[i];
        }
        for (int iter = 0; iter < 1000; iter++) {
            for (int i = 0; i < prarr.length; i++) {
                prarr[i] = prarr1[i];
            }
            for (int j = 0; j < graph.V(); j++) {
                Iterable<Integer> it = reverse.adj(j);
                double rank = 0.0;
                for (Integer i : it) {
                    rank += prarr[i] / graph.outdegree(i);
                }
                prarr1[j] = rank;
            }
        }
        return prarr1[vertex];
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
        int vertices = scan.nextInt();
        // iterate count of vertices times
        // to read the adjacency list from std input
        // and build the graph
        scan.nextLine();

        Digraph d = new Digraph(vertices);
        for (int i = 0; i < vertices; i++) {
            String[] tokens = scan.nextLine().split(" ");
            if (tokens.length == 1) {
                for (int j = 0; j < vertices; j++) {
                    d.addEdge(i, j);
                }
            } else {
                for (int j = 1; j < tokens.length; j++) {
                    d.addEdge(Integer.parseInt(tokens[0]),
                        Integer.parseInt(tokens[j]));
                }
            }
        }
        // Create page rank object and pass the graph object to the constructor
        // print the page rank object
        PageRank p = new PageRank(d);
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + " - " + p.getPR(i));
        }
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




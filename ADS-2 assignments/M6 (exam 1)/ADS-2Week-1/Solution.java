/**
 * Page Rank.
 */
import java.util.Scanner;
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
     * Constructs the object.
     *
     * @param      graphh  The graphh
     */
    PageRank(final Digraph graphh) {
        this.graph = graphh;
        value = (graph.V());
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
    //     return PrCal(pr, vertex);
    // }
    /**
     * calculation of pr.
     *
     * @param      pr      { parameter_description }
     * @param      vertex  The vertex
     *
     * @return     { description_of_the_return_value }
     */
    public void PrCal() {
        double[] prarr = new double[value];
        double[] prarr1 = new double[value];
        for (int i = 0; i < value; i++) {
            prarr[i] = 1.0 / value;
        }
        for (int i = 0; i < 1000; i++) {
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
        int vertices = scan.nextInt();
        // iterate count of vertices times
        // to read the adjacency list from std input
        // and build the graph
        scan.nextLine();

        Digraph d4PR = new Digraph(vertices);
        Digraph d = new Digraph(vertices);
        for (int i = 0; i < vertices; i++) {
            String[] tokens = scan.nextLine().split(" ");
            if (tokens.length == 1) {
                for (int j = 0; j < vertices; j++) {
                    if (j != i) d4PR.addEdge(i, j);
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
        // Create page rank object and pass the graph object to the constructor
        // print the page rank object
        PageRank p = new PageRank(d4PR);
        p.PrCal();
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




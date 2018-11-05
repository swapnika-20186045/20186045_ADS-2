/**
 * MST.
 */
import java.util.Scanner;
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
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph eg = new EdgeWeightedGraph(vertices);
        while (scan.hasNext()) {
            String[] strarr = scan.nextLine().split(" ");
            Edge ed = new Edge(Integer.parseInt(strarr[0]),
                Integer.parseInt(strarr[1]), Double.parseDouble(strarr[2]));
            eg.addEdge(ed);
        }
        LazyPrimMST prim = new LazyPrimMST(eg);
        System.out.printf("%.5f\n", prim.weight());
    }
}
